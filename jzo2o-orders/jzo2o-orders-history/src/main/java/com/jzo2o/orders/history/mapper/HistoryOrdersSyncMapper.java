package com.jzo2o.orders.history.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzo2o.orders.history.model.domain.HistoryOrdersSync;
import com.jzo2o.orders.history.model.domain.StatDay;
import com.jzo2o.orders.history.model.domain.StatHour;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单统计
 * </p>
 *
 * 
 *         
 */
public interface HistoryOrdersSyncMapper extends BaseMapper<HistoryOrdersSync> {

    List<StatDay> statForDay(@Param("queryDay") Integer queryDay);

    List<StatHour> statForHour(@Param("queryDay") Integer queryDay);

}
