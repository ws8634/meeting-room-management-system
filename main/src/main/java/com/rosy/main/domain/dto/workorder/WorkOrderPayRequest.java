package com.rosy.main.domain.dto.workorder;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class WorkOrderPayRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "工单ID不能为空")
    private Long orderId;

    @NotBlank(message = "支付方式不能为空")
    private String payMethod;
}
