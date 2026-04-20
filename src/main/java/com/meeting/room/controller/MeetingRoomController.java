package com.meeting.room.controller;

import com.meeting.room.entity.MeetingRoom;
import com.meeting.room.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会议室控制器，处理会议室相关的HTTP请求
 */
@RestController
@RequestMapping("/api/room")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping
    public Map<String, Object> createRoom(@RequestBody MeetingRoom room) {
        Map<String, Object> result = new HashMap<>();
        try {
            MeetingRoom createdRoom = meetingRoomService.createRoom(room);
            result.put("success", true);
            result.put("data", createdRoom);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateRoom(@PathVariable Long id, @RequestBody MeetingRoom room) {
        Map<String, Object> result = new HashMap<>();
        try {
            room.setId(id);
            MeetingRoom updatedRoom = meetingRoomService.updateRoom(room);
            result.put("success", true);
            result.put("data", updatedRoom);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteRoom(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            meetingRoomService.deleteRoom(id);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getRoomById(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        try {
            MeetingRoom room = meetingRoomService.findById(id);
            result.put("success", true);
            result.put("data", room);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping
    public Map<String, Object> getAllRooms() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<MeetingRoom> rooms = meetingRoomService.findAll();
            result.put("success", true);
            result.put("data", rooms);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    @GetMapping("/available")
    public Map<String, Object> getAvailableRooms() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<MeetingRoom> rooms = meetingRoomService.findAvailableRooms();
            result.put("success", true);
            result.put("data", rooms);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }
}
