package com.danfeng.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.danfeng.entity.AuthoritiesEntity;

public class SpringUtil {

	public static List<GrantedAuthority> createAuthorityList(List<AuthoritiesEntity> list) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(list.size());

		for (AuthoritiesEntity role : list) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthorities()));
		}

		return authorities;
	}
}
