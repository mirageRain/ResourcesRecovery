package com.danfeng.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danfeng.support.SimpieResponse;

@RestController
public class SecurityController {

	private RequestCache requestCache= new HttpSessionRequestCache();
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@RequestMapping("/user_login")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpieResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		SavedRequest savedRequest=requestCache.getRequest(request, response);
		
		if(savedRequest != null){
			String targetUrl =savedRequest.getRedirectUrl();
			logger.info("请求为："+targetUrl);
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")){
				redirectStrategy.sendRedirect(request, response, "/admin_sign.html");
			}
			
		}
		return new SimpieResponse("访问的服务需要身份认证，请引导用户到登录页");
	}
}
