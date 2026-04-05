# 会议室管理系统

基于 SpringBoot 3.4.x 开发的企业级会议室管理解决方案，支持会议室资源管理、预约申请审批、会议签到、消息通知等全生命周期管理功能。

## 核心功能

- **会议室管理**：会议室信息维护、容量配置、设备管理、状态监控
- **预约管理**：在线预约申请、时间段冲突检测、审批流程、预约取消
- **会议签到**：扫码签到、迟到/缺席标记、签到统计
- **消息通知**：审批结果通知、会议提醒、取消通知
- **使用统计**：会议室利用率分析、预约趋势报表

## 技术栈

- SpringBoot 3.4.x
- MyBatis Plus
- MySQL
- Redis
- Lombok
- Maven

## 项目结构

```
meeting-room-management-system/
├── main/
│   ├── src/main/java/com/rosy/main/
│   │   ├── domain/
│   │   │   ├── entity/          # 实体类
│   │   │   │   ├── MeetingRoom.java    # 会议室
│   │   │   │   ├── Reservation.java    # 预约
│   │   │   │   ├── SignIn.java         # 签到
│   │   │   │   └── Notification.java   # 通知
│   │   │   ├── dto/             # 数据传输对象
│   │   │   ├── vo/              # 视图对象
│   │   │   └── enums/           # 枚举类
│   │   ├── controller/          # 控制器
│   │   ├── service/             # 服务层
│   │   └── mapper/              # 数据访问层
│   └── pom.xml
└── README.md
```

## 快速开始

1. 克隆项目
```bash
git clone <repo-url>
```

2. 配置数据库（MySQL）
```sql
-- 创建数据库
create database meeting_room_db character set utf8mb4 collate utf8mb4_unicode_ci;
```

3. 修改配置文件 `application.yml` 中的数据库连接信息

4. 运行项目
```bash
mvn spring-boot:run
```

## 接口文档

启动后访问：http://localhost:8080/swagger-ui.html

## 作者

Rosy
