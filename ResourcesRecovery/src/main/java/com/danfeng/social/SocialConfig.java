package com.danfeng.social;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import com.danfeng.security.MyJdbcUsersConnectionRepository;
import com.danfeng.social.weixin.api.Weixin;
import com.danfeng.social.weixin.connect.WeixinAdapter;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ConnectionSignUp connectionSignUp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.social.config.annotation.SocialConfigurerAdapter#
	 * getUsersConnectionRepository(org.springframework.social.connect.
	 * ConnectionFactoryLocator)
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

		 
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		repository.setConnectionSignUp(connectionSignUp);
		
//		Connection<Weixin> connection = new OAuth2Connection<Weixin>("weixin", "o6L1e0f0FBUnahtjbIG_UHS3UZqM", "k9j7Ai_bfPSAk3nlD6ywZjA",
//				"lqNUCTmAlTP7pu0zJgEpAvBgIGbJZ0FaOyINRjQY4eidExw3vU73U5tDCRMJoEE", (long) 7200, null, new WeixinAdapter("1"));
//		repository.createConnectionRepository("66").addConnection(connection);
//		
		return repository;
	}

	@Bean
	public SpringSocialConfigurer mySocialSecurityConfig() {
		MySocialSecurityConfig configurer = new MySocialSecurityConfig("/auth");
		return configurer;
	}

	/**
	 * 处理注册流程的工具类
	 * 
	 * @param factoryLocator
	 * @return
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator) {
		return new ProviderSignInUtils(factoryLocator, getUsersConnectionRepository(factoryLocator));
	}
}
