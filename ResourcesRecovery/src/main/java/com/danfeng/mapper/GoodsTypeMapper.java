package com.danfeng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.danfeng.entity.GoodsTypeEntity;
import com.danfeng.entity.GoodsUnitEntity;

public interface GoodsTypeMapper {

	@Select("select * from goods_type where type_id = #{typeId}")
	@Results({ @Result(id = true, property = "typeId", column = "type_id") })
	GoodsTypeEntity selectTypeByTypeId(int typeId);
	
	@Select("select * from goods_type ")
	List<GoodsTypeEntity> selectTypeList();
	
	
	@Insert("INSERT INTO goods_type(name) " + "VALUES(#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "typeId")
	int insert(GoodsTypeEntity goodsTypeEntity);

	@Update("UPDATE goods_type SET name = #{name}  where type_id=#{typeId} ")
	int update(GoodsTypeEntity goodsTypeEntity);
	
	@Delete("delete from goods_type where type_id=#{typeId}")
	int delete(int typeId);
}
