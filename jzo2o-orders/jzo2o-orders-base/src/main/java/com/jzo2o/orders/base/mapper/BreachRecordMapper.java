package com.jzo2o.orders.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzo2o.orders.base.model.domain.BreachRecord;

/**
 * <p>
 * 违约记录 Mapper 接口
 * </p>
 *
 *
 * 
 */
public interface BreachRecordMapper extends BaseMapper<BreachRecord> {

    /**
     * 批量添加违约记录
     * @param breachRecords
     * @return
     */
//    @Insert("<script>INSERT INTO breach_record(id,serve_provider_id, serve_provider_type, behavior_type, breach_reason, " +
//            "serve_item_name, serve_address, served_user_id, served_phone, breach_time, breach_day, " +
//            "orders_id, orders_serve_id) VALUE <foreach collection='breachRecords' item='record' separator=','>" +
//            "(#{record.id},#{record.serveProviderId},#{record.serveProviderType},#{record.behaviorType},#{record.breachReason}," +
//            "#{record.serveItemName},#{record.serveAddress},#{record.servedUserId},#{record.servedPhone},#{record.breachTime},#{record.breachDay}," +
//            "#{record.ordersId},#{record.ordersServeId})</foreach></script>")
//    Integer batchAdd(@Param("breachRecords")List<BreachRecord> breachRecords);

}