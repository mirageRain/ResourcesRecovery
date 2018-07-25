package com.danfeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.danfeng.entity.GoodsUnitEntity;
import com.danfeng.mapper.GoodsUnitMapper;
import com.danfeng.service.GoodsUnitService;


@Service
public class GoodsUnitServiceImpl implements GoodsUnitService{

	@Autowired
	private GoodsUnitMapper goodsUnitMapper;
	
	@Override
	@Transactional
	public void insert(GoodsUnitEntity goodsUnitEntity) {
		int effectedNum=0;
		try {
			
			effectedNum=goodsUnitMapper.insert(goodsUnitEntity);
			
		} catch (Exception e) {
			throw new RuntimeException("新增失败");
		}
		if(effectedNum<=0) throw new RuntimeException("新增失败");
		
	}
	
	@Override
	public List<GoodsUnitEntity> getGoodsUnitList() {
		
		List<GoodsUnitEntity> result=null;
		try {
			result =goodsUnitMapper.selectUnitList();
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	@Transactional
	public void update(GoodsUnitEntity goodsUnitEntity) {
		int effectedNum=0;
		try {
			effectedNum=goodsUnitMapper.update(goodsUnitEntity);
		} catch (Exception e) {
			throw new RuntimeException("更新失败");
		}
		if(effectedNum<=0) throw new RuntimeException("更新失败");
		
	}
	

	@Override
	@Transactional
	public void delete(Integer UnitId) {
		int effectedNum=0;
		try {
			
			effectedNum=goodsUnitMapper.delete(UnitId);
		} catch (Exception e) {
			throw new RuntimeException("删除失败");
		}
		if(effectedNum<=0) throw new RuntimeException("删除失败");
		
	}
	
	

}
