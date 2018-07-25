package com.danfeng.service;

import javax.servlet.http.HttpServletRequest;

import com.danfeng.entity.ServerInfoEntity;

public interface ServerInfoService {

	ServerInfoEntity getServerInfo(HttpServletRequest request);
	
}
