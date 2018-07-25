package com.danfeng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.danfeng.entity.AuthoritiesEntity;

public interface AuthoritiesMapper {

	@Select("select * from authorities where user_id = #{userId}")
	@Results({
        @Result(id=true,property="userId",column="user_id"),
        @Result(property="authorityId",column="authority_id"),
    })
	List<AuthoritiesEntity> selectAuthorityByUserId(Integer userId);
	
	@Insert("INSERT INTO authorities(user_id,authorities) " + "VALUES(#{userId}, #{authorities})")
	int insert(AuthoritiesEntity authoritiesEntity);
	
	@Update("UPDATE authorities SET authorities = #{authorities} where user_id = #{userId}")
	int update(AuthoritiesEntity authoritiesEntity);
	
	@Delete("DELETE FROM authorities WHERE user_id =#{id}")
	int delete(Integer id);
}
