package com.jzo2o.foundations.service;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.foundations.mapper.ServeMapper;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import org.springframework.util.Assert;

@SpringBootTest
@Slf4j
public class ServerMapperTest {

    @Resource
    private ServeMapper serveMapper;

    @Test
    void test_queryServeListByRegionId() {
        List<ServeResDTO> serveResDTOS = serveMapper.queryServeListByRegionId(1686303222843662337L);
        Assert.notEmpty((Collection<?>) serveResDTOS,"列表为空");
    }
}
