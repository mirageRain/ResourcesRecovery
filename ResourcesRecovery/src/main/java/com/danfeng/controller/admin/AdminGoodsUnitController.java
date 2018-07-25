package com.danfeng.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.danfeng.dto.GoodsUnitDto;
import com.danfeng.entity.GoodsUnitEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.GoodsUnitServiceImpl;

@RestController
@RequestMapping("/admin/goods_unit")
public class AdminGoodsUnitController {
	
	@Autowired
	private GoodsUnitServiceImpl goodsUnitServiceImpl;
	
	//增加规格
			@PostMapping("")
			public Map<String, Object> getCollectAddress(@Valid @RequestBody GoodsUnitDto goodsUnitDto,
					BindingResult errors) {
				
				Map<String, Object> map = new HashMap<>();
				GoodsUnitEntity goodsUnitEntity=new GoodsUnitEntity();
				
				if (errors.getErrorCount() > 0) {
					map.put("success", false);
					map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
					return map;
				}
						
				try {
					goodsUnitEntity.setName(goodsUnitDto.getName());
					
				} catch (Exception e) {
					map.put("success", false);
					map.put("errMsg", "数据格式错误");
					return map;
				}
				
				try {
					goodsUnitServiceImpl.insert(goodsUnitEntity);
				} catch (Exception e) {
					map.put("success", false);
					map.put("errMsg", e.getMessage());
					return map;
				}
				
				map.put("success", true);
				return map;
			}
			
		
	@GetMapping("")
	public Map<String, Object> getGoodsUnitList(@AuthenticationPrincipal MyUser user) {
		Map<String, Object> map = new HashMap<>();
		List<GoodsUnitEntity> goodsUnitList = null;
		try {
			goodsUnitList = goodsUnitServiceImpl.getGoodsUnitList();
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		map.put("data", goodsUnitList);
		return map;
	}
	
	@PutMapping("/{goodsUnitId:\\d+}")
	public Map<String, Object> updateCollectAddress(@Valid @RequestBody GoodsUnitDto goodsUnitDto,
			BindingResult errors, @AuthenticationPrincipal MyUser user, @PathVariable Integer goodsUnitId) {

		Map<String, Object> map = new HashMap<>();
		GoodsUnitEntity goodsUnitEntity=new GoodsUnitEntity();

		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}
		try {
			goodsUnitEntity.setUnitId(goodsUnitId);
			goodsUnitEntity.setName(goodsUnitDto.getName());
			

		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "数据格式错误");
			return map;
		}

		try {
			goodsUnitServiceImpl.update(goodsUnitEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}

		map.put("success", true);
		return map;
	}

	// 删除地址
	@DeleteMapping("/{goodsUnitId:\\d+}")
	public Map<String, Object> deleteCollectAddress(@AuthenticationPrincipal MyUser user, @PathVariable Integer goodsUnitId) {
		Map<String, Object> map = new HashMap<>();

		if (goodsUnitId == null || goodsUnitId <= 0) {
			map.put("success", false);
			map.put("errMsg", "分类ID不合法");
			return map;
		}
		try {
			goodsUnitServiceImpl.delete(goodsUnitId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		return map;
	}
	
	
}
