package com.yelpCamp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.yelpCamp.entity.ApplicationUser;
import com.yelpCamp.service.ApplicationUserService;

import io.jsonwebtoken.Jwts;
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private final ApplicationUserService applicationUserService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, 
									ApplicationUserService applicationUserService) {
		super(authenticationManager);
		this.applicationUserService = applicationUserService;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain)
									throws IOException, ServletException {
		String header = request.getHeader(Constants.HEADER_STRING);
		if(header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken usernamePasswordAuth = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
		chain.doFilter(request, response);
			
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(Constants.HEADER_STRING);
		if(token == null) {return null;}
		String username = Jwts.parser().setSigningKey(Constants.SECRET)
				.parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();
		UserDetails userDetails = applicationUserService.loadUserByUsername(username);
		ApplicationUser applicationUser = applicationUserService.getApplicationUserByUsername(username);
		return username != null? new UsernamePasswordAuthenticationToken(applicationUser, null,userDetails.getAuthorities()) : null;
		
	}
		
	
}
