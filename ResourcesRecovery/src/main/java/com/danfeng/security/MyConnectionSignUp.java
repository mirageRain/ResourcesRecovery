package com.danfeng.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.danfeng.dto.UserDto;
import com.danfeng.entity.AuthoritiesEntity;
import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;
import com.danfeng.mapper.AuthoritiesMapper;
import com.danfeng.mapper.UserInfoMapper;
import com.danfeng.mapper.UserMapper;
import com.danfeng.util.WebUtils;

@Component
public class MyConnectionSignUp implements ConnectionSignUp {

	@Autowired
	private UserMapper UserMapper;
	@Autowired
	private UserInfoMapper UserInfoMapper;
	@Autowired
	private AuthoritiesMapper authoritiesMapper;
	@Autowired 
	protected HttpSession session;  
	
	@Override
	@Transactional
	public String execute(Connection<?> connection) {

		try {
			String ip =(String) session.getAttribute("userIp");
			Integer maxUserId = UserMapper.getMaxUserId();
			String username = "df_" + (maxUserId == null ? 1 : maxUserId + 1);
			UserEntity user = new UserEntity(username, null, 2, ip, null);
			UserMapper.insert(user);
			Integer userId = user.getUserId();
			
			UserInfoEntity userinfo = new UserInfoEntity(userId, connection.getDisplayName(), connection.getImageUrl());
			System.out.println(userinfo.getImgUrl());
			UserInfoMapper.insert(userinfo);
			AuthoritiesEntity authority = new AuthoritiesEntity(userId, "ROLE_USER");
			authoritiesMapper.insert(authority);
			return String.valueOf(userId);
		} catch (Exception e) {
			throw new RuntimeException("注册失败");
		}
		
		
	}

}
