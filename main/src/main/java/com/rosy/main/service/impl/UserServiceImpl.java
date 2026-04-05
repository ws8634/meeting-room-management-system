package com.rosy.main.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.common.enums.ErrorCode;
import com.rosy.common.exception.BusinessException;
import com.rosy.common.utils.JWTUtil;
import com.rosy.common.utils.PasswordUtil;
import com.rosy.main.domain.dto.user.UserLoginRequest;
import com.rosy.main.domain.dto.user.UserRegisterRequest;
import com.rosy.main.domain.dto.user.UserUpdateRequest;
import com.rosy.main.domain.entity.User;
import com.rosy.main.domain.vo.LoginUserVO;
import com.rosy.main.domain.vo.UserVO;
import com.rosy.main.mapper.UserMapper;
import com.rosy.main.service.IUserService;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 用户注册
     */
    @Override
    public long register(UserRegisterRequest registerRequest) {
        // 校验用户名是否已存在
        User existingUser = getUserByUsername(registerRequest.getUsername());
        if (existingUser != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名已存在");
        }

        // 创建用户
        User user = new User();
        BeanUtil.copyProperties(registerRequest, user);
        // 密码加密
        user.setPassword(PasswordUtil.encode(registerRequest.getPassword()));
        // 设置默认角色
        user.setUserRole("user");
        // 保存用户
        boolean saved = save(user);
        if (!saved) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败");
        }
        return user.getId();
    }

    /**
     * 用户登录
     */
    @Override
    public LoginUserVO login(UserLoginRequest loginRequest) {
        // 查找用户
        User user = getUserByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }

        // 校验密码
        if (!PasswordUtil.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }

        // 校验用户状态
        if ("ban".equals(user.getUserRole())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已被禁用");
        }

        // 生成登录VO
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setId(user.getId());
        loginUserVO.setUserName(user.getUsername());
        loginUserVO.setUserRole(user.getUserRole());
        return loginUserVO;
    }

    /**
     * 获取当前登录用户
     */
    @Override
    public User getCurrentUser() {
        // 从请求中获取用户信息
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        // 解析token
        Long userId = JWTUtil.getUserId(token);
        if (userId == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        // 查询用户
        User user = getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        return user;
    }

    /**
     * 根据用户名获取用户
     */
    @Override
    public User getUserByUsername(String username) {
        return lambdaQuery()
                .eq(User::getUsername, username)
                .one();
    }

    /**
     * 获取用户VO
     */
    @Override
    public UserVO getUserVO(User user) {
        return Optional.ofNullable(user)
                .map(u -> {
                    UserVO vo = BeanUtil.copyProperties(u, UserVO.class);
                    return vo;
                })
                .orElse(null);
    }

    /**
     * 更新用户信息
     */
    @Override
    public boolean updateUser(UserUpdateRequest updateRequest) {
        User user = getById(updateRequest.getId());
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }

        // 更新用户信息
        BeanUtil.copyProperties(updateRequest, user, "id", "username", "userRole");
        
        // 如果更新密码，需要加密
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            user.setPassword(PasswordUtil.encode(updateRequest.getPassword()));
        }

        return updateById(user);
    }

    /**
     * 获取HttpServletRequest
     */
    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes())
                    .getRequest();
        } catch (Exception e) {
            return null;
        }
    }
}