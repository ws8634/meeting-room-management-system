package com.meeting.order.service.impl;

import com.meeting.order.entity.MeetingOrder;
import com.meeting.order.repository.MeetingOrderRepository;
import com.meeting.order.service.MeetingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 会议工单服务实现类，实现工单相关的业务逻辑
 */
@Service
public class MeetingOrderServiceImpl implements MeetingOrderService {

    @Autowired
    private MeetingOrderRepository meetingOrderRepository;

    @Override
    public MeetingOrder createOrder(MeetingOrder order) {
        Duration duration = Duration.between(order.getStartTime(), order.getEndTime());
        long hours = duration.toHours();
        if (hours < 1) {
            hours = 1;
        }
        order.setDurationHours((int) hours);
        order.setTotalAmount(order.getHourlyRate().multiply(new BigDecimal(hours)));
        return meetingOrderRepository.save(order);
    }

    @Override
    public MeetingOrder updateOrder(MeetingOrder order) {
        return meetingOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        meetingOrderRepository.deleteById(id);
    }

    @Override
    public MeetingOrder findById(Long id) {
        return meetingOrderRepository.findById(id).orElse(null);
    }

    @Override
    public List<MeetingOrder> findAll() {
        return meetingOrderRepository.findAll();
    }

    @Override
    public List<MeetingOrder> findByUserId(Long userId) {
        return meetingOrderRepository.findByUserId(userId);
    }

    @Override
    public List<MeetingOrder> findByRoomId(Long roomId) {
        return meetingOrderRepository.findByRoomId(roomId);
    }

    @Override
    public List<MeetingOrder> findByStatus(String status) {
        return meetingOrderRepository.findByStatus(status);
    }

    @Override
    public MeetingOrder payOrder(Long id) {
        MeetingOrder order = findById(id);
        if (order == null) {
            throw new RuntimeException("工单不存在");
        }
        if (!"PENDING".equals(order.getStatus())) {
            throw new RuntimeException("工单状态不允许支付");
        }
        order.setStatus("PAID");
        return meetingOrderRepository.save(order);
    }

    @Override
    public MeetingOrder cancelOrder(Long id) {
        MeetingOrder order = findById(id);
        if (order == null) {
            throw new RuntimeException("工单不存在");
        }
        if (!"PENDING".equals(order.getStatus())) {
            throw new RuntimeException("工单状态不允许取消");
        }
        order.setStatus("CANCELLED");
        return meetingOrderRepository.save(order);
    }
}
