package com.danfeng.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;
import com.danfeng.sqlProvider.UserInfoSqlProvider;
import com.danfeng.sqlProvider.UserSqlProvider;

public interface UserInfoMapper {

	@Select("select * from user_info where user_id = #{userId}")
	@Results({ @Result(id = true, property = "uinfoId", column = "uinfo_id"),
			@Result(property = "userId", column = "user_id"), @Result(property = "idCard", column = "id_card"),
			@Result(property = "payPassword", column = "pay_password"),
			@Result(property = "imgUrl", column = "img_url"),
			@Result(property = "displayName", column = "display_name") })
	UserInfoEntity selectUserInfoByUserId(Integer userId);

	@UpdateProvider(type=UserInfoSqlProvider.class,  
			method="insertUserInfo") 
	@Options(useGeneratedKeys = true, keyProperty = "uinfoId")
	int insert(UserInfoEntity userinfo);
	
	@UpdateProvider(type=UserInfoSqlProvider.class,  
			method="updateUserInfo")  
	int update(UserInfoEntity userinfo);
	
	@Delete("DELETE FROM user_info WHERE user_id =#{id}")
	int delete(Integer id);
}
