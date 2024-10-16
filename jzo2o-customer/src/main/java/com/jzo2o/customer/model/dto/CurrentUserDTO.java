package com.jzo2o.customer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserDTO {

    /**
     * 当前用户id
     */
    private Long id;
    /**
     * 用户名/昵称
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型
     */
    private Integer userType;


}
