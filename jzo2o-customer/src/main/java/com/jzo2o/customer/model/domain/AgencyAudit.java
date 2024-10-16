package com.jzo2o.customer.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 机构审核信息表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("agency_audit")
public class AgencyAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机构id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 审核状态，0：未审核，1：已审核
     */
    @TableField("audit_status")
    private Integer auditStatus;

    /**
     * 拒绝原因
     */
    @TableField("reject_reason")
    private String rejectReason;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private LocalDateTime auditTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 审核人id
     */
    @TableField("auditor_id")
    private Long auditorId;

    /**
     * 审核人姓名
     */
    @TableField("auditor_name")
    private String auditorName;


}
