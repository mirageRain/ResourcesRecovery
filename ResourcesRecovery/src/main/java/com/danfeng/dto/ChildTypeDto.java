package com.danfeng.dto;

import java.io.Serializable;

public class ChildTypeDto implements Serializable {

	private static final long serialVersionUID = -2018117608586897557L;

	private Integer childTypeId;
	private Integer typeId;
	private String name;

	public ChildTypeDto(Integer childTypeId, Integer typeId, String name) {
		super();
		this.childTypeId = childTypeId;
		this.typeId = typeId;
		this.name = name;
	}

	public ChildTypeDto() {

	}

	public Integer getChildTypeId() {
		return childTypeId;
	}

	public void setChildTypeId(Integer childTypeId) {
		this.childTypeId = childTypeId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
