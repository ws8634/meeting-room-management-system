package com.meeting.room.repository;

import com.meeting.room.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会议室数据访问层，提供会议室相关的数据库操作
 */
@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {

    List<MeetingRoom> findByAvailable(Boolean available);
}
