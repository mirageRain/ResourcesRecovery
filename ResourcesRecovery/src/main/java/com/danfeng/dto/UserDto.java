package com.danfeng.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.danfeng.validator.UserNameConstraint;

public class UserDto {

	@Min(value = 1, message = "用户ID不合法")
	private Integer userId;
	
	@Pattern(regexp = "^ROLE_.*$", message = "权限格式不正确")
	private String authorities;  
	
	@NotBlank(message = "用户名不能为空！")
	private String username;
	
	private String password;
	
	private Integer enable;
	@NotNull(message = "状态不能为空！")
	private Integer state;
	@NotNull(message = "类型不能为空！")
	private Integer type;

	@Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{4,8}|(\\s&&[^\\f\\n\\r\\t\\v])*$", message = "手机号格式不正确")
	private String phone;

	@Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+|(\\s&&[^\\f\\n\\r\\t\\v])*$", message = "邮箱格式不正确")
	private String email;

	@NotBlank(message = "昵称不能为空！")
	private String displayName;

	@Pattern(regexp = "^(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)?|(\\s&&[^\\f\\n\\r\\t\\v])*$", message = "身份证号格式不正确")
	private String idCard;
	private String lastLoginIp;
	private Date lastLoginTime;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}