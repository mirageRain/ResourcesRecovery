package com.danfeng.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.danfeng.entity.CollectItemEntity;

public class OrderSettlementDto implements Serializable {

	private static final long serialVersionUID = 5125824671934785283L;

	private Integer orderId;
	private Integer userId;
	private Integer collectAddressId;
	
	private String describe;
	private Integer state;
	private Date applyTime;
	private Date accomplishTime;
	private Integer collectTimeType;
	private Date expectTime;
	
	
	private Integer enable;
	
	@NotNull
	private List<CollectItemEntity> collectItem;

	public OrderSettlementDto() {
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
