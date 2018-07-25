package com.danfeng.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class GoodsTypeDto implements Serializable {

	private static final long serialVersionUID = 4521684561061195248L;

	private Integer typeId;
	
	@NotBlank(message = "分类名称不能为空！")
	private String name;


	public GoodsTypeDto() {

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
