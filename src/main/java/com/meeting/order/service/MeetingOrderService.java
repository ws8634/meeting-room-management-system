package com.meeting.order.service;

import com.meeting.order.entity.MeetingOrder;

import java.util.List;

/**
 * 会议工单服务接口，定义工单相关的业务方法
 */
public interface MeetingOrderService {

    MeetingOrder createOrder(MeetingOrder order);

    MeetingOrder updateOrder(MeetingOrder order);

    void deleteOrder(Long id);

    MeetingOrder findById(Long id);

    List<MeetingOrder> findAll();

    List<MeetingOrder> findByUserId(Long userId);

    List<MeetingOrder> findByRoomId(Long roomId);

    List<MeetingOrder> findByStatus(String status);

    MeetingOrder payOrder(Long id);

    MeetingOrder cancelOrder(Long id);
}
