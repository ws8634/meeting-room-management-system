package com.meeting.room.service;

import com.meeting.room.entity.MeetingRoom;

import java.util.List;

/**
 * 会议室服务接口，定义会议室相关的业务方法
 */
public interface MeetingRoomService {

    MeetingRoom createRoom(MeetingRoom room);

    MeetingRoom updateRoom(MeetingRoom room);

    void deleteRoom(Long id);

    MeetingRoom findById(Long id);

    List<MeetingRoom> findAll();

    List<MeetingRoom> findAvailableRooms();
}
