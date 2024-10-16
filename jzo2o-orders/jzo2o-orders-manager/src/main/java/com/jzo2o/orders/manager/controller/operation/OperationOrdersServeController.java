package com.jzo2o.orders.manager.controller.operation;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.orders.manager.model.dto.request.OrdersServePageQueryByServeProviderReqDTO;
import com.jzo2o.orders.manager.model.dto.response.ServeProviderServeResDTO;
import com.jzo2o.orders.manager.service.IOrdersServeManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 
 */
@RestController("orders-operation")
@Api(tags = "运营端-服务单相关接口")
@RequestMapping("/operation/ordersServe")
public class OperationOrdersServeController {

    @Resource
    private IOrdersServeManagerService ordersServeManagerService;

    @GetMapping("/pageQueryByServeProvider")
    @ApiOperation("查询服务人员/机构服务数据")
    public PageResult<ServeProviderServeResDTO> pageQueryByServeProvider(OrdersServePageQueryByServeProviderReqDTO ordersServePageQueryByCurrentUserReqDTO) {
        return ordersServeManagerService.pageQueryByServeProvider(ordersServePageQueryByCurrentUserReqDTO);
    }
}
