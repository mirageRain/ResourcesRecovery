package com.danfeng.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserEntity implements Serializable {

	
	private static final long serialVersionUID = 2660231873704195707L;
	private Integer userId;
	private String username;
	private String password;
	private Integer enable;
	private Integer state;
	private Integer type;
	private String registerIp;
	private String lastLoginIp;
	private Date registerTime;
	private Date lastLoginTime;
	private List<AuthoritiesEntity> authoritiesEntityList;
	private UserInfoEntity userInfoEntity;

	public UserEntity(String username, String password, Integer type, String registerIp, Date registerTime) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
		this.registerIp = registerIp;
		this.registerTime = registerTime;
	}

	public UserEntity() {
		super();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<AuthoritiesEntity> getAuthoritiesEntityList() {
		return authoritiesEntityList;
	}

	public void setAuthoritiesEntityList(List<AuthoritiesEntity> authoritiesEntityList) {
		this.authoritiesEntityList = authoritiesEntityList;
	}

	public UserInfoEntity getUserInfoEntity() {
		return userInfoEntity;
	}

	public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
		this.userInfoEntity = userInfoEntity;
	}

}