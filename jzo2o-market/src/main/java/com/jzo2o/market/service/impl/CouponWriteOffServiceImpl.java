package com.jzo2o.market.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzo2o.market.mapper.CouponWriteOffMapper;
import com.jzo2o.market.model.domain.CouponWriteOff;
import com.jzo2o.market.service.ICouponWriteOffService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券核销表 服务实现类
 * </p>
 *
 *
 *         
 */
@Service
public class CouponWriteOffServiceImpl extends ServiceImpl<CouponWriteOffMapper, CouponWriteOff> implements ICouponWriteOffService {


    @Override
    public Integer countByActivityId(Long activityId) {
        return lambdaQuery().eq(CouponWriteOff::getActivityId, activityId)
                .count();
    }

}
