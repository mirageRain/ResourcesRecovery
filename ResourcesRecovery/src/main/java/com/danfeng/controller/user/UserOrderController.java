package com.danfeng.controller.user;

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
import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/user/orders")
public class UserOrderController {

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	// 增加地址
		@PostMapping("")
		public Map<String, Object> addOrder(@Valid @RequestBody OrderDto orderDto,
				BindingResult errors, @AuthenticationPrincipal MyUser user) {

			Map<String, Object> map = new HashMap<>();
			OrderEntity orderEntity = new OrderEntity();

			if (errors.getErrorCount() > 0) {
				map.put("success", false);
				map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
				return map;
			}
			if(orderDto.getExpectTime().after(new Date(new Date().getTime()+1209600000))){
				map.put("success", false);
				map.put("errMsg", "请您选择两周之内的时间");
				return map;
			}
			
			if(orderDto.getExpectTime().getHours()>17||orderDto.getExpectTime().getHours()<8){
				map.put("success", false);
				map.put("errMsg", "请您选择在8：00-17：00之间的时间");
				return map;
			}
			try {
				
				orderEntity.setUserId(user.getMyUserId());
				orderEntity.setCollectAddressId(orderDto.getCollectAddressId());
				orderEntity.setDescribe(orderDto.getDescribe());
				orderEntity.setState(orderDto.getState());
				orderEntity.setApplyTime(orderDto.getApplyTime());
				orderEntity.setCollectTimeType(orderDto.getCollectTimeType());//暂时设置为2 1为尽快完成订单
				orderEntity.setExpectTime(orderDto.getExpectTime());
				orderEntity.setEnable(1);

			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", "数据格式错误");
				return map;
			}

			try {
				orderServiceImpl.insert(orderEntity);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}

			map.put("success", true);
			map.put("data", orderEntity.getOrderId());
			return map;
		}
		
		/**
		 * 查询登录账号的订单列表
		 * 
		 * @param user
		 *            session中的用户信息对象
		 * @return 信息的JSON对象，其中success为成功状态，errMsg为错误信息 data为地址列表对象
		 * 
		 */
		@GetMapping("")
		public Map<String, Object> getMyCollectAddress(@AuthenticationPrincipal MyUser user) {
			Map<String, Object> map = new HashMap<>();
			List<OrderEntity> orderList = null;
			try {
				orderList = orderServiceImpl.getOrderByUserId(user.getMyUserId());
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			map.put("success", true);
			map.put("data", orderList);
			return map;
		}
		
		@GetMapping("/count")
		public Map<String, Object> getOrdersCount(@AuthenticationPrincipal MyUser user) {
			Map<String, Object> map = new HashMap<>();
			Integer count = null;
			try {
				count = orderServiceImpl.getOrderCount(user.getMyUserId());
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			map.put("success", true);
			map.put("data", count);
			return map;
		}
		
		@GetMapping("/{orderId:\\d+}")
		public Map<String, Object> getMyCollectAddressByAddressId(@AuthenticationPrincipal MyUser user, @PathVariable Integer orderId) {
			Map<String, Object> map = new HashMap<>();
			OrderEntity orderEntity = null;
			try {
				orderEntity = orderServiceImpl.getOrderByOrderId(orderId);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			map.put("success", true);
			map.put("data", orderEntity);
			return map;
		}
		
		@PutMapping("/{orderId:\\d+}")
		public Map<String, Object> updateCollectAddress(@Valid @RequestBody OrderDto orderDto,
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
				orderEntity.setCollectAddressId(orderDto.getCollectAddressId());
				orderEntity.setDescribe(orderDto.getDescribe());
				orderEntity.setState(orderDto.getState());
				orderEntity.setCollectTimeType(2);//暂时设置为2 1为尽快完成订单
				orderEntity.setExpectTime(orderDto.getExpectTime());
				orderEntity.setEnable(1);

			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", "数据格式错误");
				return map;
			}

			try {
				orderServiceImpl.update(orderEntity);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}

			map.put("success", true);
			return map;
		}

		// 删除地址
		@DeleteMapping("/{orderId:\\d+}")
		public Map<String, Object> deleteCollectAddress(@AuthenticationPrincipal MyUser user, @PathVariable Integer orderId) {
			Map<String, Object> map = new HashMap<>();

			if (orderId == null || orderId <= 0) {
				map.put("success", false);
				map.put("errMsg", "订单ID不合法");
				return map;
			}
			OrderEntity orderEntity = new OrderEntity();
			try {
				orderEntity.setOrderId(orderId);
				orderEntity.setUserId(user.getMyUserId());
				orderServiceImpl.delete(orderEntity);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			map.put("success", true);
			return map;
		}

}
