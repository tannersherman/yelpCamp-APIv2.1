package com.yelpCamp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yelpCamp.dao.ApplicationUserDao;
import com.yelpCamp.entity.ApplicationUser;

@Service
public class ApplicationUserService implements UserDetailsService{
	
	@Autowired
    private ApplicationUserDao applicationUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = getApplicationUserByUsername(username);
		return new User(applicationUser.getUsername(), applicationUser.getPassword(), AuthorityUtils.createAuthorityList(applicationUser.getRole()));
	}
	
	public ApplicationUser getApplicationUserByUsername(String username) {
		return applicationUserDao.getApplicationUserByUsername(username);
	}

	public void createApplicationUser(ApplicationUser applicationUser) {
		this.applicationUserDao.createApplicationUser(applicationUser);
		
	}

	public Collection<ApplicationUser> getAllUsers() {
		return this.applicationUserDao.getAllUsers();
	}

}
