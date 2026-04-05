package com.meeting.order.repository;

import com.meeting.order.entity.MeetingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会议工单数据访问层，提供工单相关的数据库操作
 */
@Repository
public interface MeetingOrderRepository extends JpaRepository<MeetingOrder, Long> {

    List<MeetingOrder> findByUserId(Long userId);

    List<MeetingOrder> findByRoomId(Long roomId);

    List<MeetingOrder> findByStatus(String status);
}
