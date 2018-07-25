package com.danfeng.entity;

import java.io.Serializable;

public class CollectAddressEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429163784170830593L;

	private Integer collectAddressId;
	private Integer userId;
	private String applyName;
	private String phone;
	private String province;
	private String city;
	private String district;
	private String town;
	private Integer townCode;
	private String address;
	private Integer isDefault;

	

	public CollectAddressEntity(Integer collectAddressId, Integer userId, String applyName, String phone,
			String province, String city, String district, String town, Integer townCode, String address,
			Integer isDefault) {
		super();
		this.collectAddressId = collectAddressId;
		this.userId = userId;
		this.applyName = applyName;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.district = district;
		this.town = town;
		this.townCode = townCode;
		this.address = address;
		this.isDefault = isDefault;
	}
	
	public CollectAddressEntity( ){
		super();
	}
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
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town
	 *            the town to set
	 */
	public void setTown(String town) {
		this.town = town;
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

	public Integer getTownCode() {
		return townCode;
	}

	public void setTownCode(Integer integer) {
		this.townCode = integer;
	}


}
