package com.danfeng.service;

import com.danfeng.entity.UserEntity;
import com.danfeng.entity.UserInfoEntity;

public interface UserService {

 void updateUser(UserEntity user);

boolean userNameValidator(String username, Integer userId);

void insertUser(UserEntity user);

void deleteUser(Integer userId);

UserInfoEntity getUserInfoByUserId(Integer userId);

void updateUserInfo(UserInfoEntity userInfo);

UserEntity getUserByUserId(Integer userId);
}
