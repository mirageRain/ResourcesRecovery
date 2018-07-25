package com.danfeng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.danfeng.entity.CollectAddressEntity;
import com.danfeng.entity.GoodsEntity;

public interface GoodsMapper {

	@Select("select * from goods where enable=1 and goods_id = #{goodsId}")
	@Results({ @Result(id = true, property = "goodsId", column = "goods_id"),
			@Result(property = "childTypeId", column = "child_type_id"),
			@Result(property = "unitId", column = "unit_id") })
	GoodsEntity selectGoodsByGoodsId(int goodsId);
	
	@Select("select * from goods where enable=1 ")
	@Results({ @Result(id = true, property = "goodsId", column = "goods_id"),
			@Result(property = "childTypeId", column = "child_type_id"),
			@Result(property = "unitId", column = "unit_id") })
	List<GoodsEntity> selectGoodsList();

	@Insert("INSERT INTO goods(child_type_id,unit_id,name,image_url,price) " + "VALUES(#{childTypeId}, #{unitId}, #{name},#{imageUrl}, #{price})")
	@Options(useGeneratedKeys = true, keyProperty = "goodsId")
	int insert(GoodsEntity goodEntity);

	@Update("UPDATE goods SET child_type_id = #{childTypeId},unit_id =  #{unitId} ,name = #{name} ,image_url=#{imageUrl},price = #{price} where goods_id=#{goodsId} ")
	int update(GoodsEntity goodEntity);

	@Update("UPDATE goods SET enable = 0 where goods_id=#{goodsId}")
	int deleteGoodByGoodsId(int goodsId);

	
	
	

}
