package com.danfeng.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserMoneyDto implements Serializable {

	
	private static final long serialVersionUID = 2794684792558033919L;
	
	@NotNull(message = "类型不合法")
	@Min(value = 0, message = "类型不合法")
	@Max(value = 1, message = "类型不合法")
	private Integer changeType;
	
	@NotNull(message = "更改金额不合法")
	@Min(value = 0, message = "更改金额不合法")
	private Double changeMoney;
	public Integer getChangeType() {
		return changeType;
	}
	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	public Double getChangeMoney() {
		return changeMoney;
	}
	public void setChangeMoney(Double changeMoney) {
		this.changeMoney = changeMoney;
	}
	

	
}
