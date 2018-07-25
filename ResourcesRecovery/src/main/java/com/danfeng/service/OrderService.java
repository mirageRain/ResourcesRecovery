package com.danfeng.service;

import java.util.List;

import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.OrderEntity;

public interface OrderService {


	List<OrderEntity> getOrderByUserId(Integer userId);

	OrderEntity getOrderByOrderId(Integer orderId);

	void insert(OrderEntity orderEntity);

	void update(OrderEntity orderEntity);

	void delete(OrderEntity orderEntity);

	Integer getOrderCount(Integer userId);

	void takingOrder(OrderEntity orderEntity);

	void settlementOrder(OrderEntity orderEntity);

	void accomplishOrder(OrderEntity orderEntity);


	List<OrderEntity> getAllOrder();

	Integer getBusinessWaitTakingOrderCount(Integer userId);

	Integer getBusinessWaitHomeOrderCount(Integer userId);


}
