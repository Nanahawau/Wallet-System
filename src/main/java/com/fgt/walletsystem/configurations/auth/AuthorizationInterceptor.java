package com.fgt.walletsystem.configurations.auth;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	
	@Value("${clientId}")
	private String clientId;
	
	@Value("${clientSecret}")
	private String clientSecret;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getHeader("x-client-id");
		String secret = request.getHeader("x-client-secret");
		
		if (!clientId.equalsIgnoreCase(id) || !clientSecret.equalsIgnoreCase(secret)) {
			logger.debug("Client id: {} and client secret: {} not authorized!", id, secret);
			response.sendError(HttpStatus.SC_UNAUTHORIZED, "Invalid Client Credentials");
			return false;
		}
		
		logger.debug("Client id: {} and client secret: {} authorized!", id, secret);
		return true;
	}

}
