package com.jzo2o.customer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.customer.model.domain.BankAccount;
import com.jzo2o.customer.model.dto.request.BankAccountUpsertReqDTO;

/**
 * <p>
 * 银行账户 服务类
 * </p>
 *
 *
 *          
 */
public interface IBankAccountService extends IService<BankAccount> {

    /**
     * 新增或更新
     *
     * @param bankAccountUpsertReqDTO 银行账号新增或更新模型
     */
    void upsert(BankAccountUpsertReqDTO bankAccountUpsertReqDTO);
}
