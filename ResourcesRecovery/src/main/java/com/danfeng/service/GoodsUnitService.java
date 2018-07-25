package com.danfeng.service;

import java.util.List;

import com.danfeng.entity.GoodsUnitEntity;


public interface GoodsUnitService {
	List<GoodsUnitEntity> getGoodsUnitList();

	void insert(GoodsUnitEntity goodsUnitEntity);

	void update(GoodsUnitEntity goodsUnitEntity);

	void delete(Integer UnitId);

}
