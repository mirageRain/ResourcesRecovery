package com.danfeng.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MyUser extends User {

	private Integer myUserId;
	public MyUser(Integer myUserId,String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.setMyUserId(myUserId);
		// TODO Auto-generated constructor stub
	}


	public MyUser(Integer myUserId, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.setMyUserId(myUserId);
	}


	public void setUserId(Integer myUserId) {
		this.setMyUserId(myUserId);
	}


	public Integer getMyUserId() {
		return myUserId;
	}


	public void setMyUserId(Integer myUserId) {
		this.myUserId = myUserId;
	}
	

}
