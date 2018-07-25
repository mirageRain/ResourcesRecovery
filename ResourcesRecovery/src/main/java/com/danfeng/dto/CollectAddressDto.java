package com.danfeng.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CollectAddressDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429163784170830593L;

	@Min(value = 1, message = "用户ID不合法")
	private Integer collectAddressId;
	
	@Min(value = 1, message = "用户ID不合法")
	private Integer userId;
	
	@NotBlank(message = "预约人不能为空！")
	private String applyName;
	
	@Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{4,8}|(\\s&&[^\\f\\n\\r\\t\\v])*$", message = "手机号格式不正确")
	private String phone;
	
	@NotNull(message = "地址不合法")
	@Min(value = 100000000, message = "地址不合法")
	@Max(value = 999999999, message = "地址不合法")
	private Integer townCode;
	
	@NotBlank(message = "详细地址不能为空！")
	private String address;
	
	@Min(value = 0, message = "状态信息不合法")
	@Max(value = 1, message = "状态信息不合法")
	private Integer isDefault;

	/**
	 * @return the collectAddressId
	 */
	public Integer getCollectAddressId() {
		return collectAddressId;
	}

	/**
	 * @param collectAddressId
	 *            the collectAddressId to set
	 */
	public void setCollectAddressId(Integer collectAddressId) {
		this.collectAddressId = collectAddressId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the applyName
	 */
	public String getApplyName() {
		return applyName;
	}

	/**
	 * @param applyName
	 *            the applyName to set
	 */
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	

	/**
	 * @return the town
	 */
	public Integer getTownCode() {
		return townCode;
	}

	/**
	 * @param town
	 *            the town to set
	 */
	public void setTownCode(Integer townCode) {
		this.townCode = townCode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the isDefault
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault
	 *            the isDefault to set
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
