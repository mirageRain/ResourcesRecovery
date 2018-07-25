package com.danfeng;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SpringSocialConfigurer mySocialSecurityConfig;

	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.apply(mySocialSecurityConfig)
		.and()
		.formLogin()
		.loginPage("/auth/weixin")
		//.loginPage("/user/login")
		.loginProcessingUrl("/login/from")
		.and()
		.authorizeRequests()
		.antMatchers("/static/admin/**","/static/user/**","/static/user2/**","/static/upload/**",
				"/admin_login.html","/user_login.html","/auth/qq","/signup","/hellome","/getIp","/user/login",
				"/admin/login","/users/**","/admin/server_info","/admin/admin_login","/test/**","/user/**","static/common/**","/static/common/**"
				).permitAll()
		
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/is_business").hasRole("USER")
		.antMatchers("/**","/me","/users/**","/user/**","/business/**").access("hasRole('ADMIN') or hasRole('USER')")
		.anyRequest()
		.permitAll()
		//.denyAll()
		.and()
		.csrf()
		.disable()
		.headers().frameOptions().sameOrigin()
		;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

	
}
