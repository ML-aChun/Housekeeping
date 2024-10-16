package com.jzo2o.foundations.model.dto.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("服务类型下服务项信息")
public class ServeItemByServeTypeIdResDTO {
    @ApiModelProperty("服务项名称")
    private String serveItemName;
}
