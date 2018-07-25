package com.danfeng.service;

import java.util.List;

import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.GoodsEntity;
import com.danfeng.entity.GoodsTypeEntity;
import com.danfeng.entity.OrderEntity;

public interface GoodsTypeService {


	List<GoodsTypeEntity> getGoodsTypeList();

	void insert(GoodsTypeEntity goodsTypeEntity);

	void update(GoodsTypeEntity goodsTypeEntity);

	void delete(Integer typeId);

}
