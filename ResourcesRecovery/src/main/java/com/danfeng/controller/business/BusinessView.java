package com.danfeng.controller.business;

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
@RequestMapping("/business")
public class BusinessView {

	@Autowired
	private UserMapper userMapper;
	
	
	@RequestMapping(value = "/**", method = RequestMethod.GET)
	public String all() {
		return "business/index";
	}
	
	
	@RequestMapping(value = "/add-address", method = RequestMethod.GET)
	public String addAddress() {
		return "business/add-address";
	}
	@RequestMapping(value = "/address", method = RequestMethod.GET)
	public String address() {
		return "business/address";
	}
	@RequestMapping(value = "/select-address", method = RequestMethod.GET)
	public String selectAddress() {
		return "business/select-address";
	}
	
	@RequestMapping(value = "/assess", method = RequestMethod.GET)
	public String assess() {
		return "business/assess";
	}
	@RequestMapping(value = "/center", method = RequestMethod.GET)
	public String center() {
		return "business/center";
	}
	@RequestMapping(value = "/dd-daijiedan", method = RequestMethod.GET)
	public String ddDaijiedan() {
		return "business/dd-daijiedan";
	}
	@RequestMapping(value = "/dd-daishangmen", method = RequestMethod.GET)
	public String ddDaishangmen() {
		return "business/dd-daishangmen";
	}
	@RequestMapping(value = "/dd-quxiao", method = RequestMethod.GET)
	public String ddQuxiao() {
		return "business/dd-quxiao";
	}
	@RequestMapping(value = "/hand-release", method = RequestMethod.GET)
	public String handRelease() {
		return "business/hand-release";
	}
	@RequestMapping(value = "/hotel-pay", method = RequestMethod.GET)
	public String hotelPay() {
		return "business/hotel-pay";
	}
	
	@RequestMapping(value = "/hotel-torder", method = RequestMethod.GET)
	public String hotelTorder() {
		return "business/hotel-torder";
	}
	
	@RequestMapping(value = "/huifu", method = RequestMethod.GET)
	public String huifu() {
		return "business/huifu";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "business/index";
	}
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "business/join";
	}
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order() {
		return "business/order";
	}
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public String pay() {
		return "business/pay";
	}
	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public String price() {
		return "business/price";
	}
	
	@RequestMapping(value = "/recharge", method = RequestMethod.GET)
	public String recharge() {
		return "business/recharge";
	}
	@RequestMapping(value = "/release", method = RequestMethod.GET)
	public String release() {
		return "business/release";
	}
	@RequestMapping(value = "/sc-track", method = RequestMethod.GET)
	public String scTrack() {
		return "business/sc-track";
	}
	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public String setup() {
		return "business/setup";
	}
	
	@RequestMapping(value = "/tixian-success", method = RequestMethod.GET)
	public String tixianSuccess() {
		return "business/tixian-success";
	}
	@RequestMapping(value = "/torder", method = RequestMethod.GET)
	public String torder() {
		return "business/torder";
	}
	@RequestMapping(value = "/tuikuan", method = RequestMethod.GET)
	public String tuikuan() {
		return "business/tuikuan";
	}
	@RequestMapping(value = "/txguanli", method = RequestMethod.GET)
	public String txguanli() {
		return "business/txguanli";
	}
	@RequestMapping(value = "/xiadan-success", method = RequestMethod.GET)
	public String xiadanSuccess() {
		return "business/xiadan-success";
	}
	@RequestMapping(value = "/dd-wancheng", method = RequestMethod.GET)
	public String ddWancheng() {
		return "business/dd-wancheng";
	}
	@RequestMapping(value = "/about-us", method = RequestMethod.GET)
	public String aboutUs() {
		return "business/about-us";
	}
	
	@RequestMapping(value = "/update-address", method = RequestMethod.GET)
	public String updateAddress() {
		return "business/update-address";
	}
	@RequestMapping(value = "/news-detail-1", method = RequestMethod.GET)
	public String newsDetail1() {
		return "business/news-detail-1";
	}
	@RequestMapping(value = "/news-detail-2", method = RequestMethod.GET)
	public String newsDetail2() {
		return "business/news-detail-2";
	}
	@RequestMapping(value = "/news-detail-3", method = RequestMethod.GET)
	public String newsDetail3() {
		return "business/news-detail-3";
	}
	@RequestMapping(value = "/news-detail-4", method = RequestMethod.GET)
	public String newsDetail4() {
		return "business/news-detail-4";
	}
	@RequestMapping(value = "/news-help", method = RequestMethod.GET)
	public String helpView() {
		return "business/news-help";
	}
	@RequestMapping(value = "/price-compute", method = RequestMethod.GET)
	public String priceCompute() {
		return "business/price-compute";
	}
	@RequestMapping(value = "/price-select", method = RequestMethod.GET)
	public String priceSelect() {
		return "business/price-select";
	}
	@RequestMapping(value = "/dd-confirm", method = RequestMethod.GET)
	public String ddConfirm() {
		return "business/dd-confirm";
	}
	
}
