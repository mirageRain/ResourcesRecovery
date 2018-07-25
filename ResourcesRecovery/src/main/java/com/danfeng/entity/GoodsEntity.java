package com.danfeng.entity;

import java.io.Serializable;

public class GoodsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8364893835705014999L;

	private Integer goodsId;
	private Integer childTypeId;
	private Integer unitId;
	private String name;
	private String imageUrl;
	private Double price;
	private Integer enable;

	public GoodsEntity(Integer childTypeId, Integer unitId, String name, Double price, Integer enable) {
		super();
		this.childTypeId = childTypeId;
		this.unitId = unitId;
		this.name = name;
		this.price = price;
		this.enable = enable;
	}

	public GoodsEntity() {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
