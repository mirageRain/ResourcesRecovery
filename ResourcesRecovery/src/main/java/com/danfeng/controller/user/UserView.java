package com.danfeng.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.danfeng.entity.UserEntity;
import com.danfeng.mapper.UserMapper;
import com.danfeng.security.MyUser;

@Controller
@RequestMapping("/user")
public class UserView {

	@Autowired
	private UserMapper userMapper;
	
	
	@RequestMapping(value = "/**", method = RequestMethod.GET)
	public String all() {
		return "user/index";
	}
	
	
	@RequestMapping(value = "/add-address", method = RequestMethod.GET)
	public String addAddress() {
		return "user/add-address";
	}
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public String address() {
		return "user/address";
	}
	@RequestMapping(value = "/select-address", method = RequestMethod.GET)
	public String selectAddress() {
		return "user/select-address";
	}
	
	@RequestMapping(value = "/assess", method = RequestMethod.GET)
	public String assess() {
		return "user/assess";
	}
	@RequestMapping(value = "/center", method = RequestMethod.GET)
	public String center(@AuthenticationPrincipal MyUser user,Model model) {
		UserEntity userEntity=userMapper.getUserByUserId(user.getMyUserId());
		 model.addAttribute("id", user.getMyUserId());
		 model.addAttribute("name", userEntity.getUserInfoEntity().getDisplayName());
		 model.addAttribute("imgUrl", userEntity.getUserInfoEntity().getImgUrl());
		 model.addAttribute("phone", userEntity.getUserInfoEntity().getPhone());
		 model.addAttribute("stat", userEntity.getUserInfoEntity().getStar());
		return "user/center";
	}
	@RequestMapping(value = "/dd-daijiedan", method = RequestMethod.GET)
	public String ddDaijiedan() {
		return "user/dd-daijiedan";
	}
	@RequestMapping(value = "/dd-daishangmen", method = RequestMethod.GET)
	public String ddDaishangmen() {
		return "user/dd-daishangmen";
	}
	@RequestMapping(value = "/dd-quxiao", method = RequestMethod.GET)
	public String ddQuxiao() {
		return "user/dd-quxiao";
	}
	@RequestMapping(value = "/dd-wancheng", method = RequestMethod.GET)
	public String ddWancheng() {
		return "user/dd-wancheng";
	}
	
	@RequestMapping(value = "/hand-release", method = RequestMethod.GET)
	public String handRelease() {
		return "user/hand-release";
	}
	@RequestMapping(value = "/hotel-pay", method = RequestMethod.GET)
	public String hotelPay() {
		return "user/hotel-pay";
	}
	
	@RequestMapping(value = "/hotel-torder", method = RequestMethod.GET)
	public String hotelTorder() {
		return "user/hotel-torder";
	}
	
	@RequestMapping(value = "/huifu", method = RequestMethod.GET)
	public String huifu() {
		return "user/huifu";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "user/index";
	}
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order() {
		return "user/order";
	}
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String pay() {
		return "user/pay";
	}
	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public String price() {
		return "user/price";
	}
	
	@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public String recharge() {
		return "user/recharge";
	}
	@RequestMapping(value = "/release", method = RequestMethod.GET)
	public String release() {
		return "user/release";
	}
	@RequestMapping(value = "/sc-track", method = RequestMethod.GET)
	public String scTrack() {
		return "user/sc-track";
	}
	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public String setup(@AuthenticationPrincipal MyUser user,Model model) {
		UserEntity userEntity=userMapper.getUserByUserId(user.getMyUserId());
		 model.addAttribute("id", user.getMyUserId());
		 model.addAttribute("name", userEntity.getUserInfoEntity().getDisplayName());
		 model.addAttribute("imgUrl", userEntity.getUserInfoEntity().getImgUrl());
		 model.addAttribute("phone", userEntity.getUserInfoEntity().getPhone());
		 model.addAttribute("stat", userEntity.getUserInfoEntity().getStar());
		return "user/setup";
	}
	
	@RequestMapping(value = "/tixian-success", method = RequestMethod.GET)
	public String tixianSuccess() {
		return "user/tixian-success";
	}
	@RequestMapping(value = "/torder", method = RequestMethod.GET)
	public String torder() {
		return "user/torder";
	}
	@RequestMapping(value = "/tuikuan", method = RequestMethod.GET)
	public String tuikuan() {
		return "user/tuikuan";
	}
	@RequestMapping(value = "/txguanli", method = RequestMethod.GET)
	public String txguanli() {
		return "user/txguanli";
	}
	@RequestMapping(value = "/xiadan-success", method = RequestMethod.GET)
	public String xiadanSuccess() {
		return "user/xiadan-success";
	}
	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public String aboutUs() {
		return "user/about-us";
	}
	
	@RequestMapping(value = "/update-address", method = RequestMethod.GET)
	public String updateAddress() {
		return "user/update-address";
	}
	@RequestMapping(value = "/news-detail-1", method = RequestMethod.GET)
	public String newsDetail1() {
		return "user/news-detail-1";
	}
	@RequestMapping(value = "/news-detail-2", method = RequestMethod.GET)
	public String newsDetail2() {
		return "user/news-detail-2";
	}
	@RequestMapping(value = "/news-detail-3", method = RequestMethod.GET)
	public String newsDetail3() {
		return "user/news-detail-3";
	}
	@RequestMapping(value = "/news-detail-4", method = RequestMethod.GET)
	public String newsDetail4() {
		return "user/news-detail-4";
	}
	@RequestMapping(value = "/news-help", method = RequestMethod.GET)
	public String helpView() {
		return "user/news-help";
	}
	@RequestMapping(value = "/price-compute", method = RequestMethod.GET)
	public String priceCompute() {
		return "user/price-compute";
	}
	
	
	
}
