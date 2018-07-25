package com.danfeng.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class GoodsUnitDto implements Serializable{

	
	private static final long serialVersionUID = -256588275907572882L;
	
	private Integer unitId;
	
	@NotBlank(message = "单位名称不能为空！")
	private String name;
	
	public GoodsUnitDto( String name) {
		super();
		this.name = name;
	}
	
	public GoodsUnitDto(){
		super();
	}

	/**
	 * @return the unitId
	 */
	public Integer getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId the unitId to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
