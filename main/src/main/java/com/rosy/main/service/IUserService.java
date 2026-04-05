package com.rosy.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.main.domain.dto.user.UserLoginRequest;
import com.rosy.main.domain.dto.user.UserRegisterRequest;
import com.rosy.main.domain.dto.user.UserUpdateRequest;
import com.rosy.main.domain.entity.User;
import com.rosy.main.domain.vo.LoginUserVO;
import com.rosy.main.domain.vo.UserVO;

public interface IUserService extends IService<User> {

    /**
     * 用户注册
     */
    long register(UserRegisterRequest registerRequest);

    /**
     * 用户登录
     */
    LoginUserVO login(UserLoginRequest loginRequest);

    /**
     * 获取当前登录用户
     */
    User getCurrentUser();

    /**
     * 根据用户名获取用户
     */
    User getUserByUsername(String username);

    /**
     * 获取用户VO
     */
    UserVO getUserVO(User user);

    /**
     * 更新用户信息
     */
    boolean updateUser(UserUpdateRequest updateRequest);
}