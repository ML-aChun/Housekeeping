package com.jzo2o.customer.model.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务人员审核信息表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("worker_audit")
public class WorkerAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务人员id
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
