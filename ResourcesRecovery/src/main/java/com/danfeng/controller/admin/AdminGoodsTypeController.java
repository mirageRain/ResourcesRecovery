package com.danfeng.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
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
import com.danfeng.dto.GoodsTypeDto;
import com.danfeng.dto.OrderDto;
import com.danfeng.dto.UserDto;
import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.GoodsTypeEntity;
import com.danfeng.entity.GoodsUnitEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.mapper.CollectAddressMapper;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.CollectAddressServiceImpl;
import com.danfeng.service.impl.GoodsTypeServiceImpl;
import com.danfeng.service.impl.GoodsUnitServiceImpl;

@RestController
@RequestMapping("/admin/goods_type")
public class AdminGoodsTypeController {

	
	@Autowired
	private GoodsTypeServiceImpl goodsTypeServiceImpl;
	
	//增加分类
		@PostMapping("")
		public Map<String, Object> getCollectAddress(@Valid @RequestBody GoodsTypeDto goodsTypeDto,
				BindingResult errors) {
			
			Map<String, Object> map = new HashMap<>();
			GoodsTypeEntity goodsTypeEntity=new GoodsTypeEntity();
			
			if (errors.getErrorCount() > 0) {
				map.put("success", false);
				map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
				return map;
			}
					
			try {
				goodsTypeEntity.setName(goodsTypeDto.getName());
				
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", "数据格式错误");
				return map;
			}
			
			try {
				goodsTypeServiceImpl.insert(goodsTypeEntity);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			
			map.put("success", true);
			return map;
		}
	
	
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
	
	@PutMapping("/{goodsTypeId:\\d+}")
	public Map<String, Object> updateCollectAddress(@Valid @RequestBody GoodsTypeDto goodsTypeDto,
			BindingResult errors, @AuthenticationPrincipal MyUser user, @PathVariable Integer goodsTypeId) {

		Map<String, Object> map = new HashMap<>();
		GoodsTypeEntity goodsTypeEntity=new GoodsTypeEntity();

		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}
		try {
			goodsTypeEntity.setTypeId(goodsTypeId);
			goodsTypeEntity.setName(goodsTypeDto.getName());
			

		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "数据格式错误");
			return map;
		}

		try {
			goodsTypeServiceImpl.update(goodsTypeEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}

		map.put("success", true);
		return map;
	}

	// 删除地址
	@DeleteMapping("/{goodsTypeId:\\d+}")
	public Map<String, Object> deleteCollectAddress(@AuthenticationPrincipal MyUser user, @PathVariable Integer goodsTypeId) {
		Map<String, Object> map = new HashMap<>();

		if (goodsTypeId == null || goodsTypeId <= 0) {
			map.put("success", false);
			map.put("errMsg", "分类ID不合法");
			return map;
		}
		try {
			goodsTypeServiceImpl.delete(goodsTypeId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		return map;
	}
	
}
