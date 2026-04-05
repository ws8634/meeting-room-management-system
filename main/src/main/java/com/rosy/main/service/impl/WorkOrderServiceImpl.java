package com.rosy.main.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.common.enums.ErrorCode;
import com.rosy.common.exception.BusinessException;
import com.rosy.main.domain.dto.workorder.WorkOrderCancelRequest;
import com.rosy.main.domain.dto.workorder.WorkOrderPayRequest;
import com.rosy.main.domain.entity.Reservation;
import com.rosy.main.domain.entity.User;
import com.rosy.main.domain.entity.WorkOrder;
import com.rosy.main.domain.enums.WorkOrderStatus;
import com.rosy.main.domain.vo.WorkOrderVO;
import com.rosy.main.mapper.WorkOrderMapper;
import com.rosy.main.service.IReservationService;
import com.rosy.main.service.IUserService;
import com.rosy.main.service.IWorkOrderService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements IWorkOrderService {

    @Resource
    private IUserService userService;

    @Resource
    private IReservationService reservationService;

    /**
     * 创建工单
     */
    @Override
    public long createWorkOrder(Long userId, Long roomId, Long reservationId, String roomName, Double unitPrice) {
        // 获取预约信息
        Reservation reservation = reservationService.getById(reservationId);
        if (reservation == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "预约不存在");
        }

        // 计算时长（小时）
        LocalDateTime startTime = reservation.getStartTime();
        LocalDateTime endTime = reservation.getEndTime();
        Duration duration = Duration.between(startTime, endTime);
        double hours = duration.toMinutes() / 60.0;

        // 计算总金额
        double totalAmount = hours * unitPrice;

        // 生成工单号
        String orderNo = "WO" + DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss") + RandomUtil.randomNumbers(6);

        // 创建工单
        WorkOrder workOrder = new WorkOrder();
        workOrder.setOrderNo(orderNo);
        workOrder.setUserId(userId);
        workOrder.setRoomId(roomId);
        workOrder.setReservationId(reservationId);
        workOrder.setStartTime(startTime);
        workOrder.setEndTime(endTime);
        workOrder.setHours(hours);
        workOrder.setUnitPrice(unitPrice);
        workOrder.setTotalAmount(totalAmount);
        workOrder.setStatus((byte) WorkOrderStatus.PENDING.getCode());

        // 保存工单
        boolean saved = save(workOrder);
        if (!saved) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "创建工单失败");
        }

        return workOrder.getId();
    }

    /**
     * 支付工单
     */
    @Override
    public boolean payWorkOrder(WorkOrderPayRequest payRequest) {
        // 获取工单
        WorkOrder workOrder = getById(payRequest.getOrderId());
        if (workOrder == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "工单不存在");
        }

        // 校验工单状态
        if (workOrder.getStatus() != WorkOrderStatus.PENDING.getCode()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "工单状态错误，无法支付");
        }

        // 更新工单状态
        workOrder.setStatus((byte) WorkOrderStatus.PAID.getCode());
        workOrder.setPaymentTime(LocalDateTime.now());

        return updateById(workOrder);
    }

    /**
     * 取消工单
     */
    @Override
    public boolean cancelWorkOrder(WorkOrderCancelRequest cancelRequest) {
        // 获取工单
        WorkOrder workOrder = getById(cancelRequest.getOrderId());
        if (workOrder == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "工单不存在");
        }

        // 校验工单状态
        if (workOrder.getStatus() != WorkOrderStatus.PENDING.getCode()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "工单状态错误，无法取消");
        }

        // 更新工单状态
        workOrder.setStatus((byte) WorkOrderStatus.CANCELLED.getCode());
        workOrder.setCancelTime(LocalDateTime.now());

        return updateById(workOrder);
    }

    /**
     * 获取工单VO
     */
    @Override
    public WorkOrderVO getWorkOrderVO(WorkOrder workOrder) {
        if (workOrder == null) {
            return null;
        }

        WorkOrderVO vo = BeanUtil.copyProperties(workOrder, WorkOrderVO.class);

        // 获取用户信息
        User user = userService.getById(workOrder.getUserId());
        if (user != null) {
            vo.setUserName(user.getUsername());
        }

        // 设置状态描述
        WorkOrderStatus status = WorkOrderStatus.getByCode(workOrder.getStatus());
        if (status != null) {
            vo.setStatusDesc(status.getDesc());
        }

        return vo;
    }

    /**
     * 获取用户的工单列表
     */
    @Override
    public List<WorkOrderVO> getUserWorkOrders(Long userId) {
        List<WorkOrder> workOrders = lambdaQuery()
                .eq(WorkOrder::getUserId, userId)
                .orderByDesc(WorkOrder::getCreateTime)
                .list();

        return workOrders.stream()
                .map(this::getWorkOrderVO)
                .collect(Collectors.toList());
    }

    /**
     * 获取所有工单列表（管理员）
     */
    @Override
    public List<WorkOrderVO> getAllWorkOrders() {
        List<WorkOrder> workOrders = lambdaQuery()
                .orderByDesc(WorkOrder::getCreateTime)
                .list();

        return workOrders.stream()
                .map(this::getWorkOrderVO)
                .collect(Collectors.toList());
    }
}