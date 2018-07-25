package com.danfeng.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.danfeng.dto.User;
import com.danfeng.dto.UserQueryCondition;
import com.danfeng.entity.UserEntity;
import com.danfeng.mapper.UserMapper;
import com.danfeng.security.MyUser;
import com.danfeng.util.IpUtil;

@Controller
@SessionAttributes(value="userIp",types={String.class})
public class UserController {

	private RequestCache requestCache= new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping (value="/user/login",method=RequestMethod.GET,produces = "text/html;charset=utf-8")
	public String login(HttpServletRequest request,Model model) throws ServletException, IOException{
			model.addAttribute("userIp", IpUtil.getIpAddr(request));
	      return "user_login";		
	}
		
}
