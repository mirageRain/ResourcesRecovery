/**
 * 
 */
package com.danfeng.social.weixin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.web.servlet.View;

import com.danfeng.social.weixin.connect.WeixinConnectionFactory;


@Configuration
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

	private static final String WEIXINProviderId="weixin";
	private static final String WEIXINAppId="wx6641bc412e243857";
	private static final String WEIXINAppSecret="f45c4165973a124d9245726ee6bd1c20";
  //private static final String WEIXINAppId="wx326f518c63457808";
	
//	private static final String WEIXINAppId="wxd99431bbff8305a0";
//	private static final String WEIXINAppSecret="60f78681d063590a469f1b297feff3c4";
	

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
	 * #createConnectionFactory()
	 */
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		System.out.println("createConnectionFactory");
		return new WeixinConnectionFactory(WEIXINProviderId, WEIXINAppId,
				WEIXINAppSecret);
	}
	
}
