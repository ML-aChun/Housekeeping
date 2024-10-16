package com.jzo2o.market.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.market.model.domain.CouponWriteOff;

/**
 * <p>
 * 优惠券核销表 服务类
 * </p>
 *
 *  
 *         
 */
public interface ICouponWriteOffService extends IService<CouponWriteOff> {


    /**
     * 根据获取活动id统计核销量
     * @param activityId
     * @return
     */
    Integer countByActivityId(Long activityId);
}