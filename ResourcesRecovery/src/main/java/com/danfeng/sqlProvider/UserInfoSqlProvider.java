package com.danfeng.sqlProvider;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;


public class UserInfoSqlProvider {

	public String updateUserInfo(final UserInfoEntity userInfo) {
		return new SQL() {
			{
				UPDATE("user_info");
				if (userInfo.getDisplayName() != null) {
					SET("display_name = #{displayName}");
				}
				if (userInfo.getImgUrl() != null) {
					SET("img_url = #{imgUrl}");
				}
				if (userInfo.getMoney() != null) {
					SET("money = #{money}");
				}
				if (userInfo.getPayPassword() != null) {
					SET("pay_password = #{payPassword}");
				}
				if (userInfo.getPhone() != null) {
					SET("phone = #{phone}");
				}
				if (userInfo.getEmail() != null) {
					SET("email = #{email}");
				}
				
				if (userInfo.getIdCard() != null) {
					SET("id_card = #{idCard}");
				}
				if (userInfo.getStar() != null) {
					SET("star = #{star}");
				}
				WHERE("user_id = #{userId}");
			}
		}.toString();
	}
	
	public String insertUserInfo(final UserInfoEntity userInfo) {
		return new SQL() {
			{
				INSERT_INTO("user_info");
				VALUES("user_id ", "#{userId}");
				
				if (userInfo.getDisplayName() != null) {
					VALUES("display_name ", "#{displayName}");
				}
				if(userInfo.getImgUrl()!=null){
					VALUES("img_url ", "#{imgUrl}");
				}
				if (userInfo.getMoney() != null) {
					VALUES("money ", "#{money}");
				}
				if (userInfo.getPayPassword() != null) {
					VALUES("pay_password ", "#{payPassword}");
				}
				if (userInfo.getIdCard() != null) {
					VALUES("id_card ", "#{idCard}");
				}
				if (userInfo.getPhone() != null) {
					VALUES("phone ", "#{phone}");
				}
				if (userInfo.getEmail() != null) {
					VALUES("email ", "#{email}");
				}
				if (userInfo.getStar() != null) {
					VALUES("star ", "#{star}");
				}
				
			}
		}.toString();
	}

}
