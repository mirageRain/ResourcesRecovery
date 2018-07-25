package com.danfeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.mapper.CollectAddressMapper;
import com.danfeng.service.CollectAddressService;

@Service
public class CollectAddressServiceImpl implements CollectAddressService{

	@Autowired
	private CollectAddressMapper collectAddressMapper;
	
	@Override
	public List<CollectAddressEntity> getCollectAddressByUserId(Integer userId) {
		
		List<CollectAddressEntity> result=null;
		try {
			result =collectAddressMapper.queryCollectAddressByUserId(userId);;
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	//获取默认地址ID，如果没有默认地址则返回最新地址ID，如果地址列表为空返回null
	@Override
	public Integer getDefaultCollectAddressByUserId(Integer userId) {
		
		Integer result=null;
		try {
			result =collectAddressMapper.queryDefaultCollectAddressID(userId);
			if(result==null||result<=0)
				result=collectAddressMapper.queryMaxCollectAddressID(userId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	
	@Override
	public CollectAddressEntity getCollectAddressByAddressId(Integer addressId) {
		
		CollectAddressEntity result=null;
		try {
			result =collectAddressMapper.queryCollectAddressByAddressId(addressId);
		} catch (Exception e) {
			throw new RuntimeException("查询错误");
		}
		
		return result;
	}
	@Override
	@Transactional
	public void insert(CollectAddressEntity collectAddressEntity) {
		int effectedNum=0;
		try {
			if(collectAddressEntity.getIsDefault()==1)
				collectAddressMapper.cleanIsDefault(collectAddressEntity.getUserId());
			effectedNum=collectAddressMapper.insert(collectAddressEntity);
			
		} catch (Exception e) {
			throw new RuntimeException("增加地址失败");
		}
		if(effectedNum<=0) throw new RuntimeException("增加地址失败");
		
	}
	
	@Override
	@Transactional
	public void update(CollectAddressEntity collectAddressEntity) {
		int effectedNum=0;
		try {
			if(collectAddressEntity.getIsDefault()==1)
				collectAddressMapper.cleanIsDefault(collectAddressEntity.getUserId());
			effectedNum=collectAddressMapper.update(collectAddressEntity);
		} catch (Exception e) {
			throw new RuntimeException("增加地址失败");
		}
		if(effectedNum<=0) throw new RuntimeException("增加地址失败");
		
	}
	
	//更改默认地址
	@Override
	@Transactional
	public void updateDefaultAddddress(CollectAddressEntity collectAddressEntity ) {
		int effectedNum=0;
		try {
			if(collectAddressEntity.getIsDefault()==1)
				collectAddressMapper.cleanIsDefault(collectAddressEntity.getUserId());
			effectedNum=collectAddressMapper.updateDefaultAddress(collectAddressEntity);
		} catch (Exception e) {
			throw new RuntimeException("更改默认地址失败");
		}
		if(effectedNum<=0) throw new RuntimeException("更改默认失败");
		
	}
	
	@Override
	@Transactional
	public void cleanIsDefault(Integer userId) {
		int effectedNum=0;
		try {
			effectedNum=collectAddressMapper.cleanIsDefault(userId);
		} catch (Exception e) {
			throw new RuntimeException("清除默认地址失败");
		}
		if(effectedNum<=0) throw new RuntimeException("清除默认地址失败");
		
	}
	
	
	@Override
	@Transactional
	public void delete(CollectAddressEntity collectAddressEntity) {
		int effectedNum=0;
		try {
			effectedNum=collectAddressMapper.deleteCollectAddressByCollectAddressId(collectAddressEntity);
		} catch (Exception e) {
			throw new RuntimeException("您的订单还在使用此地址，请删除订单后再删除此地址");
		}
		if(effectedNum<=0) throw new RuntimeException("您的订单还在使用此地址，请删除订单后再删除此地址");
		
	}
	
	
	

}
