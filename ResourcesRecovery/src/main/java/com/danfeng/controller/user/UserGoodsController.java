package com.danfeng.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danfeng.entity.GoodsEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.GoodsServiceImpl;
import com.danfeng.service.impl.OrderServiceImpl;
import com.danfeng.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user/goods")
public class UserGoodsController {
	
	@Autowired
	private GoodsServiceImpl goodsServiceImpl;
	
	@GetMapping("")
	public Map<String, Object> getMyCollectAddress(@AuthenticationPrincipal MyUser user) {
		Map<String, Object> map = new HashMap<>();
		List<GoodsEntity> goodsList = null;
		try {
			goodsList = goodsServiceImpl.getGoodsList();
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		map.put("data", goodsList);
		return map;
	}
}
