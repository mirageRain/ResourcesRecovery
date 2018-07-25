package com.danfeng.entity;

import java.io.Serializable;

public class GoodsTypeEntity implements Serializable {

	private static final long serialVersionUID = 4521684561061195248L;

	private Integer typeId;
	private String name;

	public GoodsTypeEntity(Integer typeId, String name) {
		super();
		this.typeId = typeId;
		this.name = name;
	}

	public GoodsTypeEntity() {

	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
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

}
