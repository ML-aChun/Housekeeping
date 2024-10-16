package com.jzo2o.orders.dispatch.strategys;

import com.jzo2o.orders.dispatch.model.dto.ServeProviderDTO;

import java.util.List;


public interface IProcessRule {

    /**
     * 根据派单规则过滤服务人员
     * @param serveProviderDTOS
     * @return
     */
    List<ServeProviderDTO> filter(List<ServeProviderDTO> serveProviderDTOS);

    /**
     * 获取下一级规则
     *
     * @return
     */
    IProcessRule next();
}
