package com.danfeng.controller.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danfeng.dto.CollectAddressDto;
import com.danfeng.dto.OrderDto;
import com.danfeng.dto.OrderSettlementDto;
import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/business/orders")
public class BusinessOrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
		//收货员接单
		@PutMapping("/taking/{orderId:\\d+}")
		public Map<String, Object> updateCollectAddress( @AuthenticationPrincipal MyUser user, @PathVariable Integer orderId) {

			Map<String, Object> map = new HashMap<>();
			OrderEntity orderEntity=new OrderEntity();
			try {
				orderEntity.setUserId(user.getMyUserId());
				orderEntity.setOrderId(orderId);
				orderEntity.setState(2);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", "数据格式错误");
				return map;
			}

			try {
				orderServiceImpl.takingOrder(orderEntity);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}

			map.put("success", true);
			return map;
		}
		
		
		@GetMapping("")
		public Map<String, Object> getAllOrder(@AuthenticationPrincipal MyUser user) {
			Map<String, Object> map = new HashMap<>();
			List<OrderEntity> orderList = null;
			try {
				orderList = orderServiceImpl.getAllOrder();
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			map.put("success", true);
			map.put("data", orderList);
			return map;
		}
		
		@GetMapping("/wait_taking_order_count")
		public Map<String, Object> getBusinessWaitTakingOrderCount(@AuthenticationPrincipal MyUser user) {
			Map<String, Object> map = new HashMap<>();
			Integer count = null;
			try {
				count = orderServiceImpl.getBusinessWaitTakingOrderCount(user.getMyUserId());
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			map.put("success", true);
			map.put("data", count);
			return map;
		}
		
		@GetMapping("/wait_home_order_count")
		public Map<String, Object> getBusinessWaitHomeOrderCount(@AuthenticationPrincipal MyUser user) {
			Map<String, Object> map = new HashMap<>();
			Integer count = null;
			try {
				count = orderServiceImpl.getBusinessWaitHomeOrderCount(user.getMyUserId());
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			map.put("success", true);
			map.put("data", count);
			return map;
		}

		// 收货员进行账单结算
		@PutMapping("/statements/{orderId:\\d+}")
		public Map<String, Object> statementsOrder(@Valid @RequestBody OrderSettlementDto orderDto,
				BindingResult errors, @AuthenticationPrincipal MyUser user, @PathVariable Integer orderId) {

			Map<String, Object> map = new HashMap<>();
			OrderEntity orderEntity = new OrderEntity();

			if (errors.getErrorCount() > 0) {
				map.put("success", false);
				map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
				return map;
			}
			try {
				
				orderEntity.setUserId(user.getMyUserId());
				orderEntity.setOrderId(orderId);
				orderEntity.setState(3);
				orderEntity.setCollectItem(orderDto.getCollectItem());
				orderEntity.setAccomplishTime(new Date());
				orderEntity.setEnable(1);

			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", "数据格式错误");
				return map;
			}

			try {
				orderServiceImpl.settlementOrder(orderEntity);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}

			map.put("success", true);
			return map;
		}
		
		// 收货员进行订单完成操作，转账给用户
				@PutMapping("/accomplish/{orderId:\\d+}")
				public Map<String, Object> accomplishOrder( @AuthenticationPrincipal MyUser user, @PathVariable Integer orderId) {

					Map<String, Object> map = new HashMap<>();
					OrderEntity orderEntity = new OrderEntity();

					try {
						orderEntity.setOrderId(orderId);
						orderEntity.setAccomplishTime(new Date());
						orderServiceImpl.accomplishOrder(orderEntity);
					} catch (Exception e) {
						map.put("success", false);
						map.put("errMsg", e.getMessage());
						return map;
					}

					map.put("success", true);
					return map;
				}

}
