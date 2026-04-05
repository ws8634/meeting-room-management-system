package com.rosy.main.domain.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserUpdateRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户ID不能为空")
    private Long id;

    @Size(max = 50, message = "用户昵称长度不能超过50个字符")
    private String userName;

    private String userAvatar;

    @Size(max = 200, message = "用户简介长度不能超过200个字符")
    private String userProfile;

    @Size(min = 6, max = 32, message = "密码长度必须在6-32个字符之间")
    private String password;
}