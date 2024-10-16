package com.jzo2o.orders.history.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzo2o.orders.history.model.domain.HistoryOrders;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 */
public interface HistoryOrdersMapper extends BaseMapper<HistoryOrders> {

    Integer migrate(@Param("yesterDayStartTime") LocalDateTime yesterDayStartTime,
                    @Param("yesterDayEndTime") LocalDateTime yesterDayEndTime,
                    @Param("offset") Integer offset,
                    @Param("perNum") Integer perNum);
}
