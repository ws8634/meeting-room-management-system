package com.rosy.main.controller;

import com.rosy.main.domain.dto.workorder.WorkOrderCancelRequest;
import com.rosy.main.domain.dto.workorder.WorkOrderPayRequest;
import com.rosy.main.domain.vo.WorkOrderVO;
import com.rosy.main.service.IWorkOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workorder")
public class WorkOrderController {

    private final IWorkOrderService workOrderService;

    public WorkOrderController(IWorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    /**
     * 创建工单
     */
    @PostMapping("/create")
    public Map<String, Object> createWorkOrder(
            @RequestParam Long userId,
            @RequestParam Long roomId,
            @RequestParam Long reservationId,
            @RequestParam String roomName,
            @RequestParam Double unitPrice) {
        long orderId = workOrderService.createWorkOrder(userId, roomId, reservationId, roomName, unitPrice);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "创建工单成功");
        result.put("data", orderId);
        return result;
    }

    /**
     * 支付工单
     */
    @PostMapping("/pay")
    public Map<String, Object> payWorkOrder(@Valid @RequestBody WorkOrderPayRequest payRequest) {
        boolean paid = workOrderService.payWorkOrder(payRequest);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "支付成功");
        result.put("data", paid);
        return result;
    }

    /**
     * 取消工单
     */
    @PostMapping("/cancel")
    public Map<String, Object> cancelWorkOrder(@Valid @RequestBody WorkOrderCancelRequest cancelRequest) {
        boolean cancelled = workOrderService.cancelWorkOrder(cancelRequest);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "取消成功");
        result.put("data", cancelled);
        return result;
    }

    /**
     * 获取用户的工单列表
     */
    @GetMapping("/user/list")
    public Map<String, Object> getUserWorkOrders(@RequestParam Long userId) {
        List<WorkOrderVO> workOrders = workOrderService.getUserWorkOrders(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "获取成功");
        result.put("data", workOrders);
        return result;
    }

    /**
     * 获取所有工单列表（管理员）
     */
    @GetMapping("/admin/list")
    public Map<String, Object> getAllWorkOrders() {
        List<WorkOrderVO> workOrders = workOrderService.getAllWorkOrders();
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "获取成功");
        result.put("data", workOrders);
        return result;
    }
}