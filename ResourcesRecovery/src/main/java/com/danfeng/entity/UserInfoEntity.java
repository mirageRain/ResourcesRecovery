package com.danfeng.entity;

import java.io.Serializable;

public class UserInfoEntity implements Serializable {

	
	private static final long serialVersionUID = 2794684792558033919L;
	private Integer uinfoId;
	private Integer userId;
	private String displayName;
	private String imgUrl;
	private String phone;
	private String email;
	private String idCard;
	private Double star;
	private Double money;
	private String payPassword;

	public UserInfoEntity() {
	}

	public UserInfoEntity(Integer userId, String displayName, String imgUrl) {
		super();
		this.userId = userId;
		this.displayName = displayName;
		this.imgUrl = imgUrl;
	}

	public Integer getUinfoId() {
		return uinfoId;
	}

	public void setUinfoId(Integer uinfoId) {
		this.uinfoId = uinfoId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Double getStar() {
		return star;
	}

	public void setStar(Double star) {
		this.star = star;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

}
