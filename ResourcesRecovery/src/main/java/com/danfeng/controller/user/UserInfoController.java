package com.danfeng.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danfeng.dto.UserMoneyDto;
import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping("")
	public Map<String, Object> getUserInfo(@AuthenticationPrincipal MyUser user) {
		Map<String, Object> map = new HashMap<>();
		UserEntity userEntity = null;
		try {
			userEntity = userServiceImpl.getUserByUserId(user.getMyUserId());
			userEntity.setPassword(null);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		
		map.put("success", true);
		map.put("data", userEntity);
		return map;
	}
	
	//用户提现
			@PutMapping("/money")
			public Map<String, Object> changeMoney(@AuthenticationPrincipal MyUser user, @Valid @RequestBody UserMoneyDto UserMoneyDto,BindingResult errors) {
				Map<String, Object> map = new HashMap<>(); 
				if (errors.getErrorCount() > 0) {
					map.put("success", false);
					map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
					return map;
				}
				if (UserMoneyDto.getChangeMoney()<=0) {
					map.put("success", false);
					map.put("errMsg","提现金额不合法");
					return map;
				}

				// 检查数据更新
				try {
					UserInfoEntity userInfoEntity = userServiceImpl.getUserInfoByUserId(user.getMyUserId());
					double money=userInfoEntity.getMoney();
					money-=UserMoneyDto.getChangeMoney();
					if(money<0){
						map.put("success", false);
						map.put("errMsg","您的余额不足");
						return map;
					}else{
						userInfoEntity.setMoney(money);
						userServiceImpl.updateUserInfo(userInfoEntity);
					}
						
					
				} catch (RuntimeException e) {
					map.put("success", false);
					map.put("errMsg", e.getMessage());
					return map;
				}

				map.put("success", true);

				return map;
			}
			//修改昵称
			@PutMapping("/display_name")
			public Map<String, Object> changeDisplayName(@AuthenticationPrincipal MyUser user, @Valid @RequestBody String displayName) {
				Map<String, Object> map = new HashMap<>();
				if(displayName==null||"".equals(displayName.trim())){
					map.put("success", false);
					map.put("errMsg", "昵称不能为空");
					return map;
				}
				
				if(displayName.trim().length()>=18){
					map.put("success", false);
					map.put("errMsg", "昵称过长");
					return map;
				}
				
				try {
					UserInfoEntity userInfoEntity = new UserInfoEntity();
					userInfoEntity.setUserId(user.getMyUserId());
					userInfoEntity.setDisplayName(displayName.trim());
					userServiceImpl.updateUserInfo(userInfoEntity);
						
					
				} catch (RuntimeException e) {
					map.put("success", false);
					map.put("errMsg", e.getMessage());
					return map;
				}

				map.put("success", true);

				return map;
			}

	
}
