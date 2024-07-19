package com.istream.ihr.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class PreAuthEntryPoint implements AuthenticationEntryPoint {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.AuthenticationEntryPoint#commence(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {
		System.out.println("Authentication exceptin:"+authEx);
		throw authEx;
	   // response.sendRedirect("http://localhost:8080/iHR/authError.html");
	    //response.sendRedirect("http://ihr-env.us-east-1.elasticbeanstalk.com/authError.html");
	}

}
