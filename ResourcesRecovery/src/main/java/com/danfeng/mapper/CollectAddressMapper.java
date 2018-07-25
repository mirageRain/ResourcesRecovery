package com.danfeng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.danfeng.entity.CollectAddressEntity;

public interface CollectAddressMapper {

	@Select("select * from collect_address where user_id = #{userId} order by is_default desc,collect_address_id")
	@Results({ @Result(id = true, property = "collectAddressId", column = "collect_address_id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "applyName", column = "apply_name"),
			@Result(property = "isDefault", column = "is_default") })
	List<CollectAddressEntity> queryCollectAddressByUserId(Integer userId);
	
	@Select("select collect_address_id from collect_address where user_id = #{userId} and is_default = 1")
	@Results({ @Result(id = true, property = "collectAddressId", column = "collect_address_id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "applyName", column = "apply_name"),
			@Result(property = "isDefault", column = "is_default") })
	Integer queryDefaultCollectAddressID(Integer userId);
	
	@Select("select max(collect_address_id) from collect_address where user_id = #{userId} ")
	@Results({ @Result(id = true, property = "collectAddressId", column = "collect_address_id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "applyName", column = "apply_name"),
			@Result(property = "isDefault", column = "is_default") })
	Integer queryMaxCollectAddressID(Integer userId);
	
	@Select("select * from collect_address where collect_address_id = #{AddressId}")
	@Results({ @Result(id = true, property = "collectAddressId", column = "collect_address_id"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "applyName", column = "apply_name"),
			@Result(property = "isDefault", column = "is_default") })
	CollectAddressEntity queryCollectAddressByAddressId(Integer addressId);

	@Insert("INSERT INTO collect_address(user_id,apply_name,phone,province,city,district,town,town_code,address,is_default) "
			+ "VALUES(#{userId}, #{applyName}, #{phone}, #{province}, #{city}, #{district}, #{town},#{townCode}, #{address}, #{isDefault})")
	@Options(useGeneratedKeys = true, keyProperty = "collectAddressId")
	int insert(CollectAddressEntity collectAddress);

	@Update("UPDATE collect_address SET apply_name= #{applyName}, phone =#{phone} ,province=#{province},city=#{city},district=#{district},town=#{town},town_code=#{townCode},address=#{address},is_default=#{isDefault} where collect_address_id=#{collectAddressId} and user_id=#{userId} ")
	int update(CollectAddressEntity collectAddress);
	
	@Update("UPDATE collect_address SET is_default=#{isDefault} where collect_address_id=#{collectAddressId} and user_id=#{userId} ")
	int updateDefaultAddress(CollectAddressEntity collectAddress);

	@Delete("delete from collect_address where user_id=#{userId}")
	int deleteCollectAddressByUserId(Integer userId);

	@Delete("delete from collect_address where collect_address_id=#{collectAddressId} and user_id=#{userId}")
	int deleteCollectAddressByCollectAddressId(CollectAddressEntity collectAddress);
	
	@Update("UPDATE collect_address SET is_default=0 where user_id=#{userId} ")
	int cleanIsDefault(Integer userId);
}
