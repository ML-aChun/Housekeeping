package com.jzo2o.orders.dispatch.service;

import com.jzo2o.orders.dispatch.handler.DispatchJobHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class IDispatchDistributeServiceTest {

    @Resource
    private DispatchJobHandler dispatchDistributeJobHandler;


    @Test
    public void testDispatchDistribute() {
        dispatchDistributeJobHandler.dispatchDistributeJob();
    }

}