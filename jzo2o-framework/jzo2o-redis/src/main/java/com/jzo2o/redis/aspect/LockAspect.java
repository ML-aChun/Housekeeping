package com.jzo2o.redis.aspect;

import com.jzo2o.common.constants.ErrorInfo;
import com.jzo2o.common.expcetions.BadRequestException;
import com.jzo2o.common.expcetions.BizIllegalException;
import com.jzo2o.common.expcetions.RequestTimeoutException;
import com.jzo2o.common.utils.AspectUtils;
import com.jzo2o.common.utils.StringUtils;
import com.jzo2o.redis.annotations.Lock;
import com.jzo2o.redis.annotations.Lock_L;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 分布式锁工具类
 */
@Aspect
public class LockAspect {

    private final RedissonClient redissonClient;

    public LockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(lock)")
    public Object handleLock(ProceedingJoinPoint pjp, Lock_L lock) throws Throwable {
        //锁key的格式化字符，此字符串中有spEL表达式
        String formatter = lock.formatter();
        Method method = AspectUtils.getMethod(pjp);
        Object[] args = pjp.getArgs();
        //得到锁的key
        String redisKey = AspectUtils.parse(formatter, method, args);
        //获取锁阻塞等待的时间,如果是0表示去尝试获取锁，如果获取不到则结束
        long waitTime = 0;
        //阻塞等待获取锁
        if (lock.block()) {
            //根据时间单位转换成ms
            waitTime = lock.waitTime();
        }
        //加锁时长
        long time = lock.time();
        //启动看门狗自动续期
        if(lock.startDog()){
            time = -1;
            //如果设置自动续期必须在方法执行后释放锁
            if(!lock.unlock()){
                throw new BadRequestException(ErrorInfo.Msg.REQUEST_PARAM_ILLEGAL);
            }
        }
        //得到锁对象
        RLock rLock = redissonClient.getLock(redisKey);
        //尝试加锁
        boolean success = rLock.tryLock(waitTime,time, lock.unit());
        if (!success && !lock.block()) {
            //未阻塞要求的情况下未得到锁
            throw new BadRequestException(ErrorInfo.Msg.REQUEST_OPERATE_FREQUENTLY);
        }
        if (!success) {
            //阻塞情况下未得到锁，请求超时
            throw new RequestTimeoutException(ErrorInfo.Msg.REQUEST_TIME_OUT);
        }

        try {
            return pjp.proceed();
        } finally {
            if (lock.unlock()) {
                rLock.unlock();
            }
        }

    }

    //通过环绕加锁，方法执行前加锁，方法执行后根据注解使用解锁
    @Around("@annotation(properties)")
    public Object handleLock(ProceedingJoinPoint pjp, Lock properties) throws Throwable {
        if (!properties.autoUnlock() && properties.leaseTime() <= 0) {
            // 不手动释放锁时，必须指定leaseTime时间
            throw new BizIllegalException("leaseTime不能为空");
        }
        // 1.基于SPEL表达式解析锁的 name
        String name = getLockName(properties.name(), pjp);
        // 2.得到锁对象
        RLock rLock = properties.lockType().getLock(redissonClient, name);
        // 3.尝试获取锁
        boolean success = properties.lockStrategy().tryLock(rLock, properties);
        if (!success) {
            // 获取锁失败，结束
            return null;
        }
        try {
            // 4.执行被代理方法
            return pjp.proceed();
        } finally {
            // 5.释放锁
            if (properties.autoUnlock()) {
                rLock.unlock();
            }
        }
    }

    /**
     * SPEL的正则规则
     */
    private static final Pattern pattern = Pattern.compile("\\#\\{([^\\}]*)\\}");
    /**
     * 方法参数解析器
     */
    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    /**
     * 解析锁名称
     * @param name 原始锁名称
     * @param pjp 切入点
     * @return 解析后的锁名称
     */
    private String getLockName(String name, ProceedingJoinPoint pjp) {
        // 1.判断是否存在spel表达式
        if (StringUtils.isBlank(name) || !name.contains("#")) {
            // 不存在，直接返回
            return name;
        }
        // 2.构建context
        EvaluationContext context = new MethodBasedEvaluationContext(
                TypedValue.NULL, resolveMethod(pjp), pjp.getArgs(), parameterNameDiscoverer);
        // 3.构建解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 3.循环处理
        Matcher matcher = pattern.matcher(name);
        while (matcher.find()) {
            // 2.1.获取表达式
            String tmp = matcher.group();
            // 2.2.尝试解析
            Expression expression = parser.parseExpression("#" + matcher.group(1));
            Object value = expression.getValue(context);
            name = name.replace(tmp, ObjectUtils.nullSafeToString(value));
        }
        return name;
    }

    private Method resolveMethod(ProceedingJoinPoint pjp) {
        // 1.获取方法签名
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        // 2.获取字节码
        Class<?> clazz = pjp.getTarget().getClass();
        // 3.方法名称
        String name = signature.getName();
        // 4.方法参数列表
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        return tryGetDeclaredMethod(clazz, name, parameterTypes);
    }

    private Method tryGetDeclaredMethod(Class<?> clazz, String name, Class<?> ... parameterTypes){
        try {
            // 5.反射获取方法
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                // 尝试从父类寻找
                return tryGetDeclaredMethod(superClass, name, parameterTypes);
            }
        }
        return null;
    }

}
