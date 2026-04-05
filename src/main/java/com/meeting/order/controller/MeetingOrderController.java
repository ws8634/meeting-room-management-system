package com.meeting.order.controller;

import com.meeting.order.entity.MeetingOrder;
import com.meeting.order.service.MeetingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会议工单控制器，处理工单相关的HTTP请求
 */
@RestController
@RequestMapping("/api/order")
public class MeetingOrderController {

    @Autowired
    private MeetingOrderService meetingOrderService;

    @PostMapping
    public Map<String, Object> createOrder(@RequestBody MeetingOrder order) {
        Map<String, Object> result = new HashMap<>();
        try {
            MeetingOrder createdOrder = meetingOrderService.createOrder(order);
            result.put("success", true);
            result.put("data", createdOrder);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateOrder(@PathVariable Long id, @RequestBody MeetingOrder order) {
        Map<String, Object> result = new HashMap<>();
        try {
            order.setId(id);
            MeetingOrder updatedOrder = meetingOrderService.updateOrder(order);
            result.put("success", true);
            result.put("data", updatedOrder);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteOrder(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            meetingOrderService.deleteOrder(id);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getOrderById(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            MeetingOrder order = meetingOrderService.findById(id);
            result.put("success", true);
            result.put("data", order);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping
    public Map<String, Object> getAllOrders() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<MeetingOrder> orders = meetingOrderService.findAll();
            result.put("success", true);
            result.put("data", orders);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/user/{userId}")
    public Map<String, Object> getOrdersByUserId(@PathVariable Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<MeetingOrder> orders = meetingOrderService.findByUserId(userId);
            result.put("success", true);
            result.put("data", orders);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/room/{roomId}")
    public Map<String, Object> getOrdersByRoomId(@PathVariable Long roomId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<MeetingOrder> orders = meetingOrderService.findByRoomId(roomId);
            result.put("success", true);
            result.put("data", orders);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/status/{status}")
    public Map<String, Object> getOrdersByStatus(@PathVariable String status) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<MeetingOrder> orders = meetingOrderService.findByStatus(status);
            result.put("success", true);
            result.put("data", orders);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PostMapping("/{id}/pay")
    public Map<String, Object> payOrder(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            MeetingOrder paidOrder = meetingOrderService.payOrder(id);
            result.put("success", true);
            result.put("data", paidOrder);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PostMapping("/{id}/cancel")
    public Map<String, Object> cancelOrder(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            MeetingOrder cancelledOrder = meetingOrderService.cancelOrder(id);
            result.put("success", true);
            result.put("data", cancelledOrder);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
}
