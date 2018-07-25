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
import com.danfeng.entity.GoodsEntity;
import com.danfeng.entity.GoodsTypeEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.GoodsServiceImpl;
import com.danfeng.service.impl.GoodsTypeServiceImpl;
import com.danfeng.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/user/goods_type")
public class UserGoodsTypeController {
	@Autowired
	private GoodsTypeServiceImpl goodsTypeServiceImpl;
	
	@GetMapping("")
	public Map<String, Object> getGoodsTypeList(@AuthenticationPrincipal MyUser user) {
		Map<String, Object> map = new HashMap<>();
		List<GoodsTypeEntity> goodsTypeList = null;
		try {
			goodsTypeList = goodsTypeServiceImpl.getGoodsTypeList();
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		map.put("data", goodsTypeList);
		return map;
	}
}
