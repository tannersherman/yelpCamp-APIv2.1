package com.yelpCamp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yelpCamp.entity.ApplicationUser;


@Repository
@Qualifier("ApplicationUserMySQLDao")
public class ApplicationUserMySQLDao implements ApplicationUserDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static class UserRowMapper implements RowMapper<ApplicationUser> {

		public ApplicationUser mapRow(ResultSet resultSet, int i) throws SQLException {
			ApplicationUser applicationUser = new ApplicationUser();
			applicationUser.setUsername(resultSet.getString("username"));
			applicationUser.setPassword(resultSet.getString("password"));
			applicationUser.setRole(resultSet.getString("role"));
			return applicationUser;
		}
		
	}
	
	private static class AdminRowMapper implements RowMapper<ApplicationUser> {

		public ApplicationUser mapRow(ResultSet resultSet, int i) throws SQLException {
			ApplicationUser applicationUser = new ApplicationUser();
			applicationUser.setUsername(resultSet.getString("username"));
			applicationUser.setRole(resultSet.getString("role"));
			applicationUser.setPassword("**********");
			return applicationUser;
		}
		
	}
	
	public ApplicationUser getApplicationUserByUsername(String username) {
		// SELECT * FROM users where username = ?
	
		final String sql = "SELECT * FROM users where username = ?";
		ApplicationUser applicationUser = jdbcTemplate.queryForObject(sql, new UserRowMapper(), username);
		return applicationUser;
		}

	@Override
	public void createApplicationUser(ApplicationUser applicationUser) {
		// INSERT INTO users (username, password, role) VALUES (?,?,?)
		final String sql = "INSERT INTO users (username, password, role) VALUES (?,?,?)";
		final String username = applicationUser.getUsername();
		final String password = applicationUser.getPassword();
		final String role = applicationUser.getRole();
		jdbcTemplate.update(sql, new Object[] {username, password, role}); 
		
	}

	@Override
	public Collection<ApplicationUser> getAllUsers() {
		// SELECT column_name(s) FROM table_name
	
		final String sql = "SELECT username, role FROM users";
		List<ApplicationUser> users = jdbcTemplate.query(sql, new AdminRowMapper());
		return users;
	}
	

}
