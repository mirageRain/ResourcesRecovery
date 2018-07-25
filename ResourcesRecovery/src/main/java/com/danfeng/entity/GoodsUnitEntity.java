package com.danfeng.entity;

import java.io.Serializable;

public class GoodsUnitEntity implements Serializable{

	
	private static final long serialVersionUID = -256588275907572882L;
	
	private Integer unitId;
	private String name;
	
	public GoodsUnitEntity( String name) {
		super();
		this.name = name;
	}
	
	public GoodsUnitEntity(){
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
