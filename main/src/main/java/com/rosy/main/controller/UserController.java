package com.rosy.main.controller;

import com.rosy.common.enums.ErrorCode;
import com.rosy.common.exception.BusinessException;
import com.rosy.common.utils.JWTUtil;
import com.rosy.main.domain.dto.user.UserLoginRequest;
import com.rosy.main.domain.dto.user.UserRegisterRequest;
import com.rosy.main.domain.dto.user.UserUpdateRequest;
import com.rosy.main.domain.vo.LoginUserVO;
import com.rosy.main.domain.vo.UserVO;
import com.rosy.main.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody UserRegisterRequest registerRequest) {
        long userId = userService.register(registerRequest);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "注册成功");
        result.put("data", userId);
        return result;
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody UserLoginRequest loginRequest) {
        LoginUserVO loginUserVO = userService.login(loginRequest);
        // 生成token
        String token = JWTUtil.generateToken(loginUserVO.getId());
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("data", loginUserVO);
        result.put("token", token);
        return result;
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/current")
    public Map<String, Object> getCurrentUser() {
        UserVO userVO = userService.getUserVO(userService.getCurrentUser());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "获取成功");
        result.put("data", userVO);
        return result;
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/update")
    public Map<String, Object> updateUser(@Valid @RequestBody UserUpdateRequest updateRequest) {
        boolean updated = userService.updateUser(updateRequest);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "更新成功");
        result.put("data", updated);
        return result;
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        // 实际项目中可能需要处理token失效
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "退出成功");
        return result;
    }
}