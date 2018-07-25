/**
 * 
 */
package com.danfeng.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;


public class MySocialSecurityConfig extends SpringSocialConfigurer {

	private String filterProcessesUrl;
	
	public MySocialSecurityConfig(String filterProcessesUrl) {

		this.filterProcessesUrl=filterProcessesUrl;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}
}
