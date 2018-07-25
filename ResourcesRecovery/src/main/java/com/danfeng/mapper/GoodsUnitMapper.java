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

public interface GoodsUnitMapper {

	@Select("select * from goods_unit where unit_id = #{unitId}")
	@Results({ @Result(id = true, property = "unitId", column = "unit_id") })
	GoodsUnitEntity selectUnitByUnitId(int unitId);
	
	@Select("select * from goods_unit")
	@Results({ @Result(id = true, property = "unitId", column = "unit_id") })
	List<GoodsUnitEntity> selectUnitList();

	@Insert("INSERT INTO goods_unit(name) " + "VALUES(#{name})")
	@Options(useGeneratedKeys = true, keyProperty = "unitId")
	int insert(GoodsUnitEntity unitEntity);

	@Update("UPDATE goods_unit SET name = #{name}  where unit_id=#{unitId} ")
	int update(GoodsUnitEntity unitEntity);
	
	@Delete("delete from goods_unit where unit_id=#{unitId}")
	int delete(int unitId);
}
