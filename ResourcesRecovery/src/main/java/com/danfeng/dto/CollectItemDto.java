package com.danfeng.dto;

import java.io.Serializable;

public class CollectItemDto implements Serializable {

	private static final long serialVersionUID = 2425127619765953506L;

	private Integer collectItemId;
	private Integer orderId;
	private Integer goodsId;
	private Double number;

	public CollectItemDto(Integer orderId, Integer goodsId, Double number) {
		super();
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.number = number;
	}

	public CollectItemDto() {
		super();
	}

	/**
	 * @return the collectItemId
	 */
	public Integer getCollectItemId() {
		return collectItemId;
	}

	/**
	 * @param collectItemId
	 *            the collectItemId to set
	 */
	public void setCollectItemId(Integer collectItemId) {
		this.collectItemId = collectItemId;
	}

	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	 * @return the number
	 */
	public Double getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(Double number) {
		this.number = number;
	}

}
