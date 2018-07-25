package com.danfeng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;

import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;
import com.danfeng.sqlProvider.UserSqlProvider;

public interface UserMapper {

	
	
	
	
	@Select("SELECT * FROM users ")
	@Results({
			@Result(id=true,property="userId",column="user_id"),
	        @Result(property="authoritiesEntityList",column="user_id",javaType=List.class,
	        many=@Many(select="com.danfeng.mapper.AuthoritiesMapper.selectAuthorityByUserId")),
	        @Result(property="userInfoEntity",column="user_id",javaType=UserInfoEntity.class,
	        one=@One(select="com.danfeng.mapper.UserInfoMapper.selectUserInfoByUserId"))        
	   })
	List<UserEntity> getAllUser();

	@Select("SELECT * FROM users WHERE username = #{username}")
	@Results({
        @Result(id=true,property="userId",column="user_id"),
        @Result(property="registerIp",column="register_ip"),
        @Result(property="lastLoginIp",column="last_login_ip"),
        @Result(property="authoritiesEntityList",column="user_id",javaType=List.class,
        many=@Many(select="com.danfeng.mapper.AuthoritiesMapper.selectAuthorityByUserId")),
        @Result(property="userInfoEntity",column="user_id",javaType=UserInfoEntity.class,
        one=@One(select="com.danfeng.mapper.UserInfoMapper.selectUserInfoByUserId"))        
    })
	UserEntity getUserByUsername(String username);

	@Select("SELECT * FROM users LEFT JOIN user_info on users.user_id = user_info.user_id where users.user_id = #{userId}")
    @Results({
        @Result(id=true,property="userId",column="user_id"),
        @Result(property="registerIp",column="register_ip"),
        @Result(property="lastLoginIp",column="last_login_ip"),
        @Result(property="authoritiesEntityList",column="user_id",javaType=List.class,
        many=@Many(select="com.danfeng.mapper.AuthoritiesMapper.selectAuthorityByUserId")),
        @Result(property="userInfoEntity",column="user_id",javaType=UserInfoEntity.class,
        one=@One(select="com.danfeng.mapper.UserInfoMapper.selectUserInfoByUserId"))        
    })
    UserEntity getUserByUserId(Integer userId);

	@Select("SELECT imageUrl FROM userconnection WHERE user_id = #{userId}")
	String getUserImgByUserId(Integer userId);

	
	@Insert("INSERT INTO users(username,password,type,register_ip,register_time) "
			+ "VALUES(#{username}, #{password}, #{type}, #{registerIp}, NOW())")
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	int insert(UserEntity user);

	@UpdateProvider(type=UserSqlProvider.class,  
			method="updateUser")  
	int update(UserEntity user);

	@Select("SELECT max(user_id) from users")
	Integer getMaxUserId();
	
	@Select("SELECT count(username) from users where username = #{username} and user_id != #{userId}")
	Integer validateUsername(UserEntity user);
	
	@Select("SELECT count(username) from users where username = #{username}")
	Integer validateUsernameByUsername(String username);
	
	@Select("SELECT username from users where  user_id = #{userId}")
	String getUsernameByUserId( Integer userId);
	
	@Delete("DELETE FROM users WHERE user_id =#{id}")
	int delete(Integer id);
	
	
}
