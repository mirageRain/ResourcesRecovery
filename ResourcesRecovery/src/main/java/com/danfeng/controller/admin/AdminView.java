package com.danfeng.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.danfeng.dto.User;
import com.danfeng.dto.UserQueryCondition;
import com.danfeng.entity.UserEntity;
import com.danfeng.mapper.UserMapper;
import com.danfeng.security.MyUser;
import com.danfeng.util.IpUtil;

@Controller
@RequestMapping("/admin")
public class AdminView {

	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value="/query_all_user",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryAllUser(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("data", userMapper.getAllUser());
		return modelMap;
	}
	
	@RequestMapping(value = "/**", method = RequestMethod.GET)
	public String all() {
		return "admin/index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLogin() {
		return "admin/login";
	}

	@RequestMapping(value = "/college_message", method = RequestMethod.GET)
	public String collegeMessage() {
		return "admin/college_message";
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainView() {
		return "admin/main";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexView(@AuthenticationPrincipal MyUser user,Model model) {
		model.addAttribute("name", user.getUsername());
		return "admin/index";
	}

	@RequestMapping(value = "/user_message", method = RequestMethod.GET)
	public String userMessage() {
		return "admin/user_message";
	}

	@RequestMapping(value = "/score_input", method = RequestMethod.GET)
	public String scoreInput() {
		return "admin/score_input";
	}

	@RequestMapping(value = "/college_state", method = RequestMethod.GET)
	public String collegeState() {
		return "admin/college_state";
	}

	@RequestMapping(value = "/set_password", method = RequestMethod.GET)
	public String test5() {
		return "admin/set_password";
	}
	
	@RequestMapping(value = "/add_user_model", method = RequestMethod.GET)
	public String addUserModel() {
		return "admin/add_user_model";
	}
	@RequestMapping(value = "/update_user_model", method = RequestMethod.GET)
	public String updateUserModel() {
		return "admin/update_user_model";
	}
	
	@RequestMapping(value = "/goods_view", method = RequestMethod.GET)
	public String goodsView() {
		return "admin/goods";
	}
	
	@RequestMapping(value = "/goods_type_view", method = RequestMethod.GET)
	public String goodsTypeView() {
		return "admin/goods_type";
	}
	
	@RequestMapping(value = "/goods_unit_view", method = RequestMethod.GET)
	public String goodsUnitView() {
		return "admin/goods_unit";
	}
	
	@RequestMapping(value = "/update_goods_type_model", method = RequestMethod.GET)
	public String updateGoodsTypeModel() {
		return "admin/update_goods_type_model";
	}
	
	@RequestMapping(value = "/add_goods_type_model", method = RequestMethod.GET)
	public String addGoodsTypeModel() {
		return "admin/add_goods_type_model";
	}
	

	@RequestMapping(value = "/update_goods_unit_model", method = RequestMethod.GET)
	public String updateGoodsUnitModel() {
		return "admin/update_goods_unit_model";
	}
	
	@RequestMapping(value = "/add_goods_unit_model", method = RequestMethod.GET)
	public String addGoodsUnitModel() {
		return "admin/add_goods_unit_model";
	}
	
	@RequestMapping(value = "/update_goods_model", method = RequestMethod.GET)
	public String updateGoodsModel() {
		return "admin/update_goods_model";
	}
	
	@RequestMapping(value = "/add_goods_model", method = RequestMethod.GET)
	public String addGoodsModel() {
		return "admin/add_goods_model";
	}
	
	@RequestMapping(value = "/user_money", method = RequestMethod.GET)
	public String userMoney() {
		return "admin/user_money";
	}
	
	@RequestMapping(value = "/user_star", method = RequestMethod.GET)
	public String userStar() {
		return "admin/user_star";
	}
	@RequestMapping(value = "/update_user_money", method = RequestMethod.GET)
	public String updateUserMoney() {
		return "admin/update_user_money";
	}
	
	@RequestMapping(value = "/update_user_money_password", method = RequestMethod.GET)
	public String updateUserMoneyPassword() {
		return "admin/update_user_money_password";
	}
	
}
