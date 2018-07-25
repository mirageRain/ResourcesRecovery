package com.danfeng.sqlProvider;

import org.apache.ibatis.jdbc.SQL;
import com.danfeng.entity.UserEntity;

public class UserSqlProvider {

	public String updateUser(final UserEntity user) {
		return new SQL() {
			{
				UPDATE("users");
				if (user.getUsername() != null) {
					SET("username = #{username}");
				}
				if (user.getPassword() != null) {
					SET("password = #{password}");
				}
				if (user.getType() != null) {
					SET("type = #{type}");
				}
				if (user.getState() != null) {
					SET("state = #{state}");
				}
				WHERE("user_id = #{userId}");
			}
		}.toString();
	}

}
