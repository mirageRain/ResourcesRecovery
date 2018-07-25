package com.danfeng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.danfeng.dto.User;
import com.danfeng.dto.UserDto;
import com.danfeng.dto.UserMoneyDto;
import com.danfeng.entity.AuthoritiesEntity;
import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;
import com.danfeng.mapper.UserInfoMapper;
import com.danfeng.mapper.UserMapper;
import com.danfeng.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	protected HttpSession session;

	//更新用户信息
	@PutMapping("/{id:\\d+}")
	public Map<String, Object> update(@PathVariable Integer id, @Valid @RequestBody UserDto user,
			BindingResult errors) {
		Map<String, Object> map = new HashMap<>();

		if (errors.getErrorCount() > 0) {
			map.put("success", false);
			map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
			return map;
		}

		// 检查username
		try {
			userServiceImpl.userNameValidator(user.getUsername(), id);
		} catch (RuntimeException e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}

		UserEntity userEntity = new UserEntity();
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		List<AuthoritiesEntity> authoritiesEntityList = new ArrayList<>();
		AuthoritiesEntity authoritiesEntity = new AuthoritiesEntity();

		// 检测数据装配
		try {
			// 填充user信息
			userEntity.setUserId(id);
			userEntity.setUsername(user.getUsername());
			
			if(user.getPassword()!=null) //检测是否修改密码
				if(!"".equals(user.getPassword().trim()))
					userEntity.setPassword(passwordEncoder.encode(user.getPassword().trim()));
			
			userEntity.setState(user.getState());
			userEntity.setType(user.getType());

			// 填充userInfo信息
			userInfoEntity.setUserId(id);
			userInfoEntity.setDisplayName(user.getDisplayName());
			userInfoEntity.setEmail(user.getEmail());
			userInfoEntity.setPhone(user.getPhone());
			userInfoEntity.setIdCard(user.getIdCard());
			
			

			// 填充权限信息
			authoritiesEntity.setUserId(id);
			authoritiesEntity.setAuthorities(user.getAuthorities());

			authoritiesEntityList.add(authoritiesEntity);
			userEntity.setUserInfoEntity(userInfoEntity);
			userEntity.setAuthoritiesEntityList(authoritiesEntityList);
		} catch (Exception e) {
			map.put("success", false);
			map.put("errMsg", "数据格式错误");
			return map;
		}
		// 检查数据更新
		try {
			userServiceImpl.updateUser(userEntity);
		} catch (RuntimeException e) {
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}

		map.put("success", true);

		return map;
	}
	
	
	//修改用户资金
		@PutMapping("/money/{userId:\\d+}")
		public Map<String, Object> changeMoney(@PathVariable Integer userId, @Valid @RequestBody UserMoneyDto UserMoneyDto,BindingResult errors) {
			Map<String, Object> map = new HashMap<>(); 
			if (errors.getErrorCount() > 0) {
				map.put("success", false);
				map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
				return map;
			}
			

			// 检查数据更新
			try {
				UserInfoEntity userInfoEntity = userServiceImpl.getUserInfoByUserId(userId);
				double money=userInfoEntity.getMoney();
						if(UserMoneyDto.getChangeType()==0){
							money+=UserMoneyDto.getChangeMoney();
						}else if(UserMoneyDto.getChangeType()==1){
							money-=UserMoneyDto.getChangeMoney();
						}
				userInfoEntity.setMoney(money);
				userServiceImpl.updateUserInfo(userInfoEntity);
			} catch (RuntimeException e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}

			map.put("success", true);

			return map;
		}

	//删除用户
	 @DeleteMapping("/{id:\\d+}")
	 public Map<String, Object> delete(@PathVariable Integer id){
		 System.out.println("in");
		 Map<String, Object> map = new HashMap<>();
		 if(id<=0||id==null){
			map.put("success", false);
			map.put("errMsg", "用户ID非法");
			return map;
		 }else{
			 try {
				 userServiceImpl.deleteUser(id);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", "用户正在参与会话，请终止后删除");
				return map;
			}
			 
		 }
		 map.put("success", true);	 
		return map;
	 }
	
	 @PostMapping()
	 public Map<String, Object> insert( @Valid @RequestBody UserDto user,
				BindingResult errors) {
			Map<String, Object> map = new HashMap<>();

			if (errors.getErrorCount() > 0) {
				map.put("success", false);
				map.put("errMsg", errors.getAllErrors().get(0).getDefaultMessage());
				return map;
			}

			// 检查username
			try {
				userServiceImpl.userNameValidator(user.getUsername(), null);
			} catch (RuntimeException e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
			
			// 检查密码
			if(user.getPassword()==null||"".equals(user.getPassword().trim())){
				map.put("success", false);
				map.put("errMsg", "密码不能为空");
				return map;
			}
			UserEntity userEntity = new UserEntity();
			UserInfoEntity userInfoEntity = new UserInfoEntity();
			List<AuthoritiesEntity> authoritiesEntityList = new ArrayList<>();
			AuthoritiesEntity authoritiesEntity = new AuthoritiesEntity();
			// 检测数据装配
			try {
				// 填充user信息
				userEntity.setUsername(user.getUsername());
				userEntity.setState(user.getState());
				userEntity.setType(user.getType());
				userEntity.setRegisterIp((String) session.getAttribute("userIp"));
				userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

				// 填充userInfo信息
				userInfoEntity.setDisplayName(user.getDisplayName());
				userInfoEntity.setEmail(user.getEmail());
				userInfoEntity.setPhone(user.getPhone());
				userInfoEntity.setIdCard(user.getIdCard());

				// 填充权限信息

				authoritiesEntity.setAuthorities(user.getAuthorities());

				authoritiesEntityList.add(authoritiesEntity);
				userEntity.setUserInfoEntity(userInfoEntity);
				userEntity.setAuthoritiesEntityList(authoritiesEntityList);
			} catch (Exception e) {
				map.put("success", false);
				map.put("errMsg", "数据格式错误");
				return map;
			}
			// 检查数据更新
			try {
				userServiceImpl.insertUser(userEntity);
			} catch (RuntimeException e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}

			map.put("success", true);

			return map;
		}
	//
	// @GetMapping("/{id:\\d+}")
	// public int test(@Valid @RequestBody UserEntity user){
	//
	// return 1;
	// }

}
