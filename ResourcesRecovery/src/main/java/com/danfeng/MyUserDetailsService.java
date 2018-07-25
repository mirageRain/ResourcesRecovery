package com.danfeng;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import com.danfeng.entity.UserEntity;
import com.danfeng.mapper.UserMapper;
import com.danfeng.security.MySocialUser;
import com.danfeng.security.MyUser;
import com.danfeng.util.SpringUtil;

@Component
public class MyUserDetailsService implements UserDetailsService,SocialUserDetailsService {

	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user;
		user=userMapper.getUserByUsername(username);
		if(user.getPassword()!=null)
			return new MyUser(user.getUserId(),username, user.getPassword(),SpringUtil.createAuthorityList(user.getAuthoritiesEntityList()));
		else return null;
	}
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		
		//UserEntity user=userMapper.getUserByUserId(userId);
		//logger.info("密码是："+user.getPassWord());
		String password =passwordEncoder.encode("123456");
		UserEntity user;
		user=userMapper.getUserByUserId(Integer.parseInt(userId));
		return new MySocialUser(user.getUserId(),user.getUsername(), password,SpringUtil.createAuthorityList(user.getAuthoritiesEntityList()));
	}

}
