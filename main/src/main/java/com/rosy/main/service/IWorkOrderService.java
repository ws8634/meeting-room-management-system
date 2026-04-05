package com.rosy.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.main.domain.dto.workorder.WorkOrderCancelRequest;
import com.rosy.main.domain.dto.workorder.WorkOrderPayRequest;
import com.rosy.main.domain.entity.WorkOrder;
import com.rosy.main.domain.vo.WorkOrderVO;

import java.util.List;

public interface IWorkOrderService extends IService<WorkOrder> {

    /**
     * 创建工单
     */
    long createWorkOrder(Long userId, Long roomId, Long reservationId, String roomName, Double unitPrice);

    /**
     * 支付工单
     */
    boolean payWorkOrder(WorkOrderPayRequest payRequest);

    /**
     * 取消工单
     */
    boolean cancelWorkOrder(WorkOrderCancelRequest cancelRequest);

    /**
     * 获取工单VO
     */
    WorkOrderVO getWorkOrderVO(WorkOrder workOrder);

    /**
     * 获取用户的工单列表
     */
    List<WorkOrderVO> getUserWorkOrders(Long userId);

    /**
     * 获取所有工单列表（管理员）
     */
    List<WorkOrderVO> getAllWorkOrders();
}