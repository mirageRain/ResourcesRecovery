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
import com.danfeng.dto.UserDto;
import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.mapper.CollectAddressMapper;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.CollectAddressServiceImpl;

@RestController
@RequestMapping("/admin/collect_address")
public class AdminCollectAddressController {

	@Autowired
	private CollectAddressMapper collectAddressMapper;
	
	@Autowired
	private CollectAddressServiceImpl collectAddressServiceImpl;
	
	/**
	 * 查询用户地址列表API
	 * @param id 用户ID
	 * @return 信息的JSON对象，其中success为成功状态，errMsg为错误信息 data为地址列表对象
	 * 
	 */
	@GetMapping("/{id:\\d+}")
	public Map<String, Object> getCollectAddress(@PathVariable String id) {
		Map<String, Object> map = new HashMap<>();
		if(id==null||Integer.parseInt(id)<=0){
			map.put("success", false);
			map.put("errMsg", "ID不合法");
			return map;
		}
		
		List<CollectAddressEntity> collectAddressList=null;
		try {
			collectAddressList=collectAddressServiceImpl.getCollectAddressByUserId(Integer.parseInt(id));
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		
		map.put("success", true);
		map.put("data", collectAddressList);
		return map;
	}
	
//	@DeleteMapping("/me")
//	public void deleteCollectAddress(@AuthenticationPrincipal  MyUser user) {
//		collectAddressMapper.deleteCollectAddressByUserId(user.getMyUserId());
//	}
//	
//	@PostMapping("/me")
//	public List<CollectAddressEntity> getCollectAddress(@AuthenticationPrincipal  MyUser user) {
//		return collectAddressMapper.selectCollectAddressByUserId(user.getMyUserId());
//	}
//	
	//增加地址
	@PostMapping("/{id:\\d+}")
	public Map<String, Object> getCollectAddress(@Valid @RequestBody CollectAddressDto CollectAddressDto,
			BindingResult errors) {
		
		Map<String, Object> map = new HashMap<>();
		CollectAddressEntity collectAddressEntity=new CollectAddressEntity();
		
		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}
				
		try {
			collectAddressEntity.setCollectAddressId(CollectAddressDto.getCollectAddressId());
			collectAddressEntity.setUserId(CollectAddressDto.getUserId());
			collectAddressEntity.setApplyName(CollectAddressDto.getApplyName());
			collectAddressEntity.setPhone(CollectAddressDto.getPhone());
//			collectAddressEntity.setProvince(CollectAddressDto.getProvince());
//			collectAddressEntity.setCity(CollectAddressDto.getCity());
//			collectAddressEntity.setDistrict(CollectAddressDto.getDistrict());
//			collectAddressEntity.settown(CollectAddressDto.gettown());
			collectAddressEntity.setAddress(CollectAddressDto.getAddress());
			collectAddressEntity.setIsDefault(CollectAddressDto.getIsDefault());
			
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "数据格式错误");
			return map;
		}
		
		try {
			collectAddressServiceImpl.insert(collectAddressEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		
		map.put("success", true);
		return map;
	}
//	
}
