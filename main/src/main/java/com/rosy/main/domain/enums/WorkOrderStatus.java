package com.rosy.main.domain.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 工单状态枚举
 * 
 * @author Rosy
 * @since 2026-04-05
 */
public enum WorkOrderStatus {
    
    PENDING(0, "待支付"),
    PAID(1, "已支付"),
    CANCELLED(2, "已取消");

    private final int code;
    private final String desc;

    WorkOrderStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, WorkOrderStatus> CODE_MAP = new HashMap<>();

    static {
        for (WorkOrderStatus status : WorkOrderStatus.values()) {
            CODE_MAP.put(status.code, status);
        }
    }

    public static WorkOrderStatus getByCode(int code) {
        return CODE_MAP.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}