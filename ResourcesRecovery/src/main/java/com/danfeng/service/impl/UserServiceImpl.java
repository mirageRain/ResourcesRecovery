package com.danfeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.danfeng.entity.AuthoritiesEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;
import com.danfeng.mapper.AuthoritiesMapper;
import com.danfeng.mapper.UserInfoMapper;
import com.danfeng.mapper.UserMapper;
import com.danfeng.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private AuthoritiesMapper authoritiesMapper;

	@Override
	@Transactional
	public void updateUser(UserEntity user) {

		int effectedNum1 = 0, effectedNum2 = 0, effectedNum3 = 0;

		try {
			effectedNum1 = userMapper.update(user);
			effectedNum2 = userInfoMapper.update(user.getUserInfoEntity());

			effectedNum3 = authoritiesMapper.update(user.getAuthoritiesEntityList().get(0));

			if (effectedNum1 <= 0 || effectedNum2 <= 0 || effectedNum3 <= 0)
				throw new RuntimeException("更新失敗");
		} catch (Exception e) {

			throw new RuntimeException("更新失敗 " + e.getMessage());
		}

	}
	
	@Override
	@Transactional
	public void updateUserInfo(UserInfoEntity userInfo) {

		int effectedNum1 = 0;

		try {
			effectedNum1 = userInfoMapper.update(userInfo);


			if (effectedNum1 <= 0)
				throw new RuntimeException("更新失敗");
		} catch (Exception e) {

			throw new RuntimeException("更新失敗 " + e.getMessage());
		}

	}
	
	@Override
	@Transactional
	public void insertUser(UserEntity user) {

		int effectedNum1 = 0, effectedNum2 = 0, effectedNum3 = 0;
		UserInfoEntity userInfoEntity;
		AuthoritiesEntity authoritiesEntity;

		try {
			effectedNum1 = userMapper.insert(user);
			
			//将用户ID赋值
			userInfoEntity=user.getUserInfoEntity();
			userInfoEntity.setUserId(user.getUserId());
			effectedNum2 = userInfoMapper.insert(userInfoEntity);

			authoritiesEntity=user.getAuthoritiesEntityList().get(0);
			authoritiesEntity.setUserId(user.getUserId());
			effectedNum3 = authoritiesMapper.insert(authoritiesEntity);

			if (effectedNum1 <= 0 || effectedNum2 <= 0 || effectedNum3 <= 0)
				throw new RuntimeException("插入失敗");
		} catch (Exception e) {

			throw new RuntimeException("插入失敗 " + e.getMessage());
		}

	}

	
	@Override
	@Transactional
	public void deleteUser(Integer userId) {

		int effectedNum1 = 0, effectedNum2 = 0, effectedNum3 = 0;

		try {
			effectedNum1 = userMapper.delete(userId);
			effectedNum2 = userInfoMapper.delete(userId);
			effectedNum3 = authoritiesMapper.delete(userId);

			if (effectedNum1 <= 0 || effectedNum2 <= 0 || effectedNum3 <= 0)
				throw new RuntimeException("删除失敗");
		} catch (Exception e) {

			throw new RuntimeException("删除失敗 " + e.getMessage());
		}

	}
	

	@Override
	public UserEntity getUserByUserId(Integer userId) {
		
		UserEntity UserEntity=null;
		try {
			UserEntity =userMapper.getUserByUserId(userId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return UserEntity;
	}
	
	@Override
	public UserInfoEntity getUserInfoByUserId(Integer userId) {
		
		UserInfoEntity userInfoEntity=null;
		try {
			userInfoEntity =userInfoMapper.selectUserInfoByUserId(userId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return userInfoEntity;
	}
	
	@Override
	public boolean userNameValidator(String username, Integer userId) {
		
		if(userId==null){
			if (StringUtils.isEmpty(username)) {
				throw new RuntimeException("用户名不能为空");
				// 检测用户名是否和未更新前一致
			} else if (StringUtils.startsWith(username, "df_") || userMapper.validateUsernameByUsername(username)> 0) {
				throw new RuntimeException("用户名已被占用");
			} else
				return true;
		}else{
			UserEntity userEntity = new UserEntity();
			userEntity.setUserId(userId);
			userEntity.setUsername(username);
			if (StringUtils.isEmpty(username)) {
				throw new RuntimeException("用户名不能为空");
				// 检测用户名是否和未更新前一致
			} else if ((!userMapper.getUsernameByUserId(userId).equals(username.trim())
					&& StringUtils.startsWith(username, "df_")) || userMapper.validateUsername(userEntity) > 0) {
				throw new RuntimeException("用户名已被占用");
			} else
				return true;
		}
		

	}

}
