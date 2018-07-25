package com.danfeng.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderEntity implements Serializable {

	private static final long serialVersionUID = 5125824671934785283L;

	private Integer orderId;
	private Integer userId;
	private Integer collectAddressId;
	private String describe;
	private Integer state;//0异常 1未接单 、2已接单 待上门  3、已完成   4、以评价
	private Date applyTime; 
	private Date accomplishTime;
	private Integer collectTimeType;
	private Date expectTime;
	private Integer enable;
	private List<CollectItemEntity> collectItem;

	public OrderEntity(Integer userId, Integer collectAddressId, String describe, Integer state, Date applyTime,
			Date accomplishTime, Integer collectTimeType, Date expectTime) {
		super();
		this.userId = userId;
		this.collectAddressId = collectAddressId;
		this.describe = describe;
		this.state = state;
		this.applyTime = applyTime;
		this.accomplishTime = accomplishTime;
		this.collectTimeType = collectTimeType;
		this.expectTime = expectTime;
	}

	public OrderEntity() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCollectAddressId() {
		return collectAddressId;
	}

	public void setCollectAddressId(Integer collectAddressId) {
		this.collectAddressId = collectAddressId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAccomplishTime() {
		return accomplishTime;
	}

	public void setAccomplishTime(Date accomplishTime) {
		this.accomplishTime = accomplishTime;
	}

	public Integer getCollectTimeType() {
		return collectTimeType;
	}

	public void setCollectTimeType(Integer collectTimeType) {
		this.collectTimeType = collectTimeType;
	}

	public Date getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Date expectTime) {
		this.expectTime = expectTime;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public List<CollectItemEntity> getCollectItem() {
		return collectItem;
	}

	public void setCollectItem(List<CollectItemEntity> collectItem) {
		this.collectItem = collectItem;
	}

}
