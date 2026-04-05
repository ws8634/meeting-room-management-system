package com.rosy.common.utils;

import java.util.Base64;
import java.util.Date;

/**
 * JWT工具类
 * 
 * @author Rosy
 * @since 2026-04-05
 */
public class JWTUtil {

    private static final String SECRET = "meeting_room_management_system_secret_key";
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24小时

    /**
     * 生成token
     */
    public static String generateToken(Long userId) {
        // 简单实现：userId + 时间戳 + 简单加密
        String content = userId + "|" + (System.currentTimeMillis() + EXPIRATION_TIME);
        return Base64.getEncoder().encodeToString(content.getBytes());
    }

    /**
     * 从token中获取用户ID
     */
    public static Long getUserId(String token) {
        try {
            String content = new String(Base64.getDecoder().decode(token));
            String[] parts = content.split("\\|");
            if (parts.length != 2) {
                return null;
            }
            
            long expirationTime = Long.parseLong(parts[1]);
            if (System.currentTimeMillis() > expirationTime) {
                return null;
            }
            
            return Long.parseLong(parts[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证token
     */
    public static boolean validateToken(String token) {
        return getUserId(token) != null;
    }
}