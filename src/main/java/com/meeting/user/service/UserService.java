package com.meeting.user.service;

import com.meeting.user.entity.User;

/**
 * 用户服务接口，定义用户相关的业务方法
 */
public interface UserService {

    User register(User user);

    User login(String username, String password);

    User findById(Long id);

    User updateUser(User user);
}
