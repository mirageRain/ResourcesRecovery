package com.danfeng.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.security.SocialUserDetails;

public class MySocialUser extends MyUser implements SocialUserDetails {


	

	public MySocialUser(Integer myUserId, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(myUserId, username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

	public MySocialUser(Integer myUserId, String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super(myUserId, username, password, authorities);
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return getUsername();
	}

}
