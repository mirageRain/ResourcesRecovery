package com.danfeng.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.social.UserIdSource;
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
import com.danfeng.util.AddressUtil;

@RestController
@RequestMapping("/user/collect_address")
public class UserCollectAddressController {

	@Autowired
	private CollectAddressMapper collectAddressMapper;

	@Autowired
	private CollectAddressServiceImpl collectAddressServiceImpl;

	// 增加地址
	@PostMapping("")
	public Map<String, Object> addCollectAddress(@Valid @RequestBody CollectAddressDto CollectAddressDto,
			BindingResult errors, @AuthenticationPrincipal MyUser user) {

		Map<String, Object> map = new HashMap<>();
		CollectAddressEntity collectAddressEntity = new CollectAddressEntity();

		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}
		try {
			collectAddressEntity.setUserId(user.getMyUserId());
			collectAddressEntity.setApplyName(CollectAddressDto.getApplyName());
			collectAddressEntity.setPhone(CollectAddressDto.getPhone());
			Map<String, String> CollectAddress = AddressUtil.getAddressBytownCode(CollectAddressDto.getTownCode());
			collectAddressEntity.setProvince(CollectAddress.get("province"));
			collectAddressEntity.setCity(CollectAddress.get("city"));
			collectAddressEntity.setDistrict(CollectAddress.get("district"));
			collectAddressEntity.setTown(CollectAddress.get("town"));
			collectAddressEntity.setTownCode(CollectAddressDto.getTownCode());
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

	/**
	 * 查询登录账号的地址列表
	 * 
	 * @param user
	 *            session中的用户信息对象
	 * @return 信息的JSON对象，其中success为成功状态，errMsg为错误信息 data为地址列表对象
	 * 
	 */
	@GetMapping("")
	public Map<String, Object> getMyCollectAddress(@AuthenticationPrincipal MyUser user) {
		Map<String, Object> map = new HashMap<>();
		List<CollectAddressEntity> collectAddressList = null;
		try {
			collectAddressList = collectAddressServiceImpl.getCollectAddressByUserId(user.getMyUserId());
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		map.put("data", collectAddressList);
		return map;
	}

	@GetMapping("/{addressId:\\d+}")
	public Map<String, Object> getMyCollectAddressByAddressId(@AuthenticationPrincipal MyUser user,
			@PathVariable Integer addressId) {
		Map<String, Object> map = new HashMap<>();
		CollectAddressEntity collectAddress = null;
		try {
			collectAddress = collectAddressServiceImpl.getCollectAddressByAddressId(addressId);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		map.put("data", collectAddress);
		return map;
	}

	// 获取登录用户默认的地址ID
	@GetMapping("/default")
	public Map<String, Object> getDefaultCollectAddress(@AuthenticationPrincipal MyUser user) {
		Map<String, Object> map = new HashMap<>();
		Integer defaultCollectAddressId = null;
		try {
			defaultCollectAddressId = collectAddressServiceImpl.getDefaultCollectAddressByUserId(user.getMyUserId());
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		map.put("data", defaultCollectAddressId);
		return map;
	}

	// 更改登录用户 的默认地址
	@PutMapping("/default/{addressId:\\d+}")
	public Map<String, Object> updateDefaultCollectAddress(@AuthenticationPrincipal MyUser user,
			@PathVariable Integer addressId) {
		Map<String, Object> map = new HashMap<>();
		CollectAddressEntity collectAddressEntity = new CollectAddressEntity();
		try {
			collectAddressEntity.setCollectAddressId(addressId);
			collectAddressEntity.setUserId(user.getMyUserId());
			collectAddressEntity.setIsDefault(1);
			collectAddressServiceImpl.updateDefaultAddddress(collectAddressEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		return map;
	}

	@PutMapping("/{addressId:\\d+}")
	public Map<String, Object> updateCollectAddress(@Valid @RequestBody CollectAddressDto CollectAddressDto,
			BindingResult errors, @AuthenticationPrincipal MyUser user, @PathVariable Integer addressId) {

		Map<String, Object> map = new HashMap<>();
		CollectAddressEntity collectAddressEntity = new CollectAddressEntity();

		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}
		try {
			collectAddressEntity.setCollectAddressId(addressId);
			collectAddressEntity.setUserId(user.getMyUserId());
			collectAddressEntity.setApplyName(CollectAddressDto.getApplyName());
			collectAddressEntity.setPhone(CollectAddressDto.getPhone());
			Map<String, String> CollectAddress = AddressUtil.getAddressBytownCode(CollectAddressDto.getTownCode());
			collectAddressEntity.setProvince(CollectAddress.get("province"));
			collectAddressEntity.setCity(CollectAddress.get("city"));
			collectAddressEntity.setDistrict(CollectAddress.get("district"));
			collectAddressEntity.setTown(CollectAddress.get("town"));
			collectAddressEntity.setTownCode(CollectAddressDto.getTownCode());
			collectAddressEntity.setAddress(CollectAddressDto.getAddress());
			collectAddressEntity.setIsDefault(CollectAddressDto.getIsDefault());

		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "数据格式错误");
			return map;
		}

		try {
			collectAddressServiceImpl.update(collectAddressEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}

		map.put("success", true);
		return map;
	}

	// 删除地址
	@DeleteMapping("/{addressId:\\d+}")
	public Map<String, Object> deleteCollectAddress(@AuthenticationPrincipal MyUser user,
			@PathVariable Integer addressId) {

		Map<String, Object> map = new HashMap<>();

		if (addressId == null || addressId <= 0) {
			map.put("success", false);
			map.put("errMsg", "地址ID不合法");
			return map;
		}
		CollectAddressEntity collectAddressEntity = new CollectAddressEntity();
		try {
			collectAddressEntity.setCollectAddressId(addressId);
			collectAddressEntity.setUserId(user.getMyUserId());
			collectAddressServiceImpl.delete(collectAddressEntity);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		return map;
	}

}
