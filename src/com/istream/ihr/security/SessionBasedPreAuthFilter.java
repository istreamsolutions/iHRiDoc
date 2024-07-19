/**
 * 
 */
package com.istream.ihr.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.util.StringUtils;

import com.istream.ihr.vo.SessionInfo;
import com.istream.ihr.util.Constants;

/**
 * @author preeti
 *
 */
public class SessionBasedPreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter#getPreAuthenticatedCredentials(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		String[] credentials = new String[2];		
		SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute(Constants.SESSION_INFO);
		if(sessionInfo==null) {
			return credentials;
		}
		credentials[0]="test";
		credentials[1]=sessionInfo.getEmail();		
		return credentials;		
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter#getPreAuthenticatedPrincipal(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute(Constants.SESSION_INFO);
		if(sessionInfo == null || StringUtils.isEmpty(sessionInfo.getEmail())) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

}
