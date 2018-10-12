package com.yelpCamp.dao;

import java.util.Collection;

import com.yelpCamp.entity.ApplicationUser;


public interface ApplicationUserDao {
	ApplicationUser getApplicationUserByUsername(String username);
	
	void createApplicationUser(ApplicationUser applicationUser);

	Collection<ApplicationUser> getAllUsers();
}
