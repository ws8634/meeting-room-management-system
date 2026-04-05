package com.meeting.config;

import com.meeting.room.entity.MeetingRoom;
import com.meeting.room.repository.MeetingRoomRepository;
import com.meeting.user.entity.User;
import com.meeting.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 数据初始化类，用于启动时初始化测试数据
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRealName("系统管理员");
            admin.setPhone("13800138000");
            admin.setRole("ADMIN");
            userRepository.save(admin);

            User user = new User();
            user.setUsername("user");
            user.setPassword("user123");
            user.setRealName("普通用户");
            user.setPhone("13800138001");
            user.setRole("USER");
            userRepository.save(user);
        }

        if (meetingRoomRepository.count() == 0) {
            MeetingRoom room1 = new MeetingRoom();
            room1.setName("小会议室A");
            room1.setDescription("可容纳10人，配备投影仪");
            room1.setCapacity(10);
            room1.setHourlyRate(new BigDecimal("100.00"));
            meetingRoomRepository.save(room1);

            MeetingRoom room2 = new MeetingRoom();
            room2.setName("中会议室B");
            room2.setDescription("可容纳20人，配备视频会议系统");
            room2.setCapacity(20);
            room2.setHourlyRate(new BigDecimal("200.00"));
            meetingRoomRepository.save(room2);

            MeetingRoom room3 = new MeetingRoom();
            room3.setName("大会议室C");
            room3.setDescription("可容纳50人，配备音响系统");
            room3.setCapacity(50);
            room3.setHourlyRate(new BigDecimal("500.00"));
            meetingRoomRepository.save(room3);
        }
    }
}
