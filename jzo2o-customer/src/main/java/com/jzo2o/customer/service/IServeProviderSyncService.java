package com.jzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.customer.model.domain.ServeProviderSync;

/**
 * <p>
 * 评分同步列表 服务类
 * </p>
 *
 *
 *          
 */
public interface IServeProviderSyncService extends IService<ServeProviderSync> {

    int add(Long id, Integer serveProviderType);

    /**
     * 更新评分
     * @param id
     * @param evaluationScore
     */
    void updateScore(Long id, Double evaluationScore);



}
