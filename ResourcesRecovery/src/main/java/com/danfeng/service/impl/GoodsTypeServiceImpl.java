package com.danfeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.GoodsEntity;
import com.danfeng.entity.GoodsTypeEntity;
import com.danfeng.entity.OrderEntity;
import com.danfeng.mapper.CollectAddressMapper;
import com.danfeng.mapper.GoodsMapper;
import com.danfeng.mapper.GoodsTypeMapper;
import com.danfeng.mapper.OrderMapper;
import com.danfeng.service.CollectAddressService;
import com.danfeng.service.GoodsService;
import com.danfeng.service.GoodsTypeService;
import com.danfeng.service.OrderService;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService{

	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	
	@Override
	@Transactional
	public void insert(GoodsTypeEntity goodsTypeEntity) {
		int effectedNum=0;
		try {
			
			effectedNum=goodsTypeMapper.insert(goodsTypeEntity);
			
		} catch (Exception e) {
			throw new RuntimeException("新增失败");
		}
		if(effectedNum<=0) throw new RuntimeException("新增失败");
		
	}
	
	@Override
	public List<GoodsTypeEntity> getGoodsTypeList() {
		
		List<GoodsTypeEntity> result=null;
		try {
			result =goodsTypeMapper.selectTypeList();
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public void update(GoodsTypeEntity goodsTypeEntity) {
		int effectedNum=0;
		try {
			effectedNum=goodsTypeMapper.update(goodsTypeEntity);
		} catch (Exception e) {
			throw new RuntimeException("更新失败");
		}
		if(effectedNum<=0) throw new RuntimeException("更新失败");
		
	}
	

	@Override
	@Transactional
	public void delete(Integer typeId) {
		int effectedNum=0;
		try {
			
			effectedNum=goodsTypeMapper.delete(typeId);
		} catch (Exception e) {
			throw new RuntimeException("删除失败");
		}
		if(effectedNum<=0) throw new RuntimeException("删除失败");
		
	}
	
	
	

}
