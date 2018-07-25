package com.danfeng.service;

import java.util.List;

import com.danfeng.entity.CollectAddressEntity;

public interface CollectAddressService {

	List<CollectAddressEntity> getCollectAddressByUserId(Integer userId);

	void insert(CollectAddressEntity collectAddressEntity);

	void update(CollectAddressEntity collectAddressEntity);

	void delete(CollectAddressEntity collectAddressEntity);

	void cleanIsDefault(Integer userId);

	CollectAddressEntity getCollectAddressByAddressId(Integer addressId);

	Integer getDefaultCollectAddressByUserId(Integer userId);

	void updateDefaultAddddress(CollectAddressEntity collectAddressEntity);
}
