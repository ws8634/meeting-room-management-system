package com.rosy.main.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WorkOrderVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 工单ID
     */
    private Long id;

    /**
     * 工单号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 会议室ID
     */
    private Long roomId;

    /**
     * 会议室名称
     */
    private String roomName;

    /**
     * 预约ID
     */
    private Long reservationId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 时长（小时）
     */
    private Double hours;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 总金额
     */
    private Double totalAmount;

    /**
     * 状态：0-待支付，1-已支付，2-已取消
     */
    private Byte status;

    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}