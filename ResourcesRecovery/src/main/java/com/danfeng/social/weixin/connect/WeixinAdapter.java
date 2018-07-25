package com.danfeng.social.weixin.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.danfeng.social.weixin.api.Weixin;
import com.danfeng.social.weixin.api.WeixinUserInfo;

/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * 
 * 
 *
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {
	
	private String openId;
	
	public WeixinAdapter() {}
	
	public WeixinAdapter(String openId){
		this.openId = openId;
	}

	/**
	 * @param api
	 * @return
	 */
	@Override
	public boolean test(Weixin api) {
		return true;
	}

	/**
	 * @param api
	 * @param values
	 * 
	 */
	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		System.out.println("setConnectionValues"+openId);
		WeixinUserInfo profile = api.getUserInfo(openId);
		
		System.out.println(profile.getHeadimgurl());
		values.setProviderUserId(profile.getOpenid());
		values.setDisplayName(profile.getNickname());
		values.setImageUrl(profile.getHeadimgurl());
	}

	/**
	 * @param api
	 * @return
	 */
	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		return null;
	}

	/**
	 * @param api
	 * @param message
	 */
	@Override
	public void updateStatus(Weixin api, String message) {
		//do nothing
	}

}
