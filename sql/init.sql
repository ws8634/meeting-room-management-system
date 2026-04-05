-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `user_name` varchar(50) NOT NULL COMMENT '用户昵称',
  `user_avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `user_profile` varchar(200) DEFAULT NULL COMMENT '用户简介',
  `user_role` varchar(20) NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` tinyint DEFAULT '0' COMMENT '乐观锁版本号',
  `is_deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 创建工单表
CREATE TABLE IF NOT EXISTS `work_order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `order_no` varchar(30) NOT NULL COMMENT '工单号',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `room_id` bigint NOT NULL COMMENT '会议室ID',
  `reservation_id` bigint NOT NULL COMMENT '预约ID',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `hours` double NOT NULL COMMENT '时长（小时）',
  `unit_price` double NOT NULL COMMENT '单价',
  `total_amount` double NOT NULL COMMENT '总金额',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态：0-待支付，1-已支付，2-已取消',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `creator_id` bigint DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updater_id` bigint DEFAULT NULL COMMENT '更新者ID',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `version` tinyint DEFAULT '0' COMMENT '乐观锁版本号',
  `is_deleted` tinyint DEFAULT '0' COMMENT '逻辑删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_room_id` (`room_id`),
  KEY `idx_reservation_id` (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工单表';

-- 初始化管理员用户
INSERT INTO `user` (`username`, `password`, `user_name`, `user_role`, `create_time`, `update_time`)
VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', 'admin', NOW(), NOW());

-- 初始化普通用户
INSERT INTO `user` (`username`, `password`, `user_name`, `user_role`, `create_time`, `update_time`)
VALUES ('user1', 'e10adc3949ba59abbe56e057f20f883e', '测试用户1', 'user', NOW(), NOW());
