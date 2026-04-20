package com.meeting.room.service.impl;

import com.meeting.room.entity.MeetingRoom;
import com.meeting.room.repository.MeetingRoomRepository;
import com.meeting.room.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议室服务实现类，实现会议室相关的业务逻辑
 */
@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    @Override
    public MeetingRoom createRoom(MeetingRoom room) {
        return meetingRoomRepository.save(room);
    }

    @Override
    public MeetingRoom updateRoom(MeetingRoom room) {
        return meetingRoomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        meetingRoomRepository.deleteById(id);
    }

    @Override
    public MeetingRoom findById(Long id) {
        return meetingRoomRepository.findById(id).orElse(null);
    }

    @Override
    public List<MeetingRoom> findAll() {
        return meetingRoomRepository.findAll();
    }

    @Override
    public List<MeetingRoom> findAvailableRooms() {
        return meetingRoomRepository.findByAvailable(true);
    }
}
