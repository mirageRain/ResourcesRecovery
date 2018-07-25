package com.danfeng.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class GoodsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364893835705014999L;

	private Integer goodsId;
	@NotNull
	private Integer childTypeId;
	@NotNull
	private Integer unitId;
	@NotBlank
	private String name;
	@NotBlank
	private String imageBase64;
	@NotNull
	private Double price;
	private Integer enable;


	public GoodsDto() {
		super();
	}

	/**
	 * @return the goodsId
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId
	 *            the goodsId to set
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the childTypeId
	 */
	public Integer getChildTypeId() {
		return childTypeId;
	}

	/**
	 * @param childTypeId
	 *            the childTypeId to set
	 */
	public void setChildTypeId(Integer childTypeId) {
		this.childTypeId = childTypeId;
	}

	/**
	 * @return the unitId
	 */
	public Integer getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId
	 *            the unitId to set
	 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the enable
	 */
	public Integer getEnable() {
		return enable;
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getimageBase64() {
		return imageBase64;
	}

	public void setimageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

}
