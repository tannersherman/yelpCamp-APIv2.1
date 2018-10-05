package com.yelpCamp.dao;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yelpCamp.entity.Campground;

@Repository("MySQL")
@Qualifier("MySQLData")
public class MySQLDao implements CampgroundDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static class campgroundRowMapper implements RowMapper<Campground> {

		public Campground mapRow(ResultSet resultSet, int i) throws SQLException {
			Campground campground = new Campground();
			campground.setId(resultSet.getInt("id"));
			campground.setName(resultSet.getString("name"));
			campground.setInfo(resultSet.getString("info"));
			return campground;
		}
		
	}

	public Collection<Campground> getAllCampgrounds() {
		// SELECT column_name(s) FROM table_name
		
		final String sql = "SELECT id, name, info FROM campgrounds";
		List<Campground> campgrounds = jdbcTemplate.query(sql, new campgroundRowMapper());
		return campgrounds;
	}

	public Campground getCampgroundById(int id) {
		// SELECT column_name(s) FROM table_name where id  = value
		
		final String sql = "SELECT id, name, info FROM campgrounds where id = ?";
		Campground campground = jdbcTemplate.queryForObject(sql, new campgroundRowMapper(), id);
		return campground;
	}

	public void deleteCampgroundById(int id) {
		// DELETE FROM table_name WHERE some_column = some_value
		final String sql = "DELETE FROM campgrounds WHERE id = ?";
		jdbcTemplate.update(sql, id);
		
	}

	public void updateCampground(Campground campground) {
		// UPDATE table_name SET column1=value, column2=value2 WHERE some_column = some_value
		final String sql = "UPDATE campgrounds SET name = ?, info = ? WHERE id = ?";
		int id = campground.getId();
		final String name = campground.getName();
		final String info = campground.getInfo();
		jdbcTemplate.update(sql, new Object[] {name, info, id}); 
		
	}

	public void insertCampground(Campground campground) {
		// INSERT INTO table_name (column1, column2, column3) VALUES (value1, value2, value3)
		
		final String sql = "INSERT INTO campgrounds (name, info) VALUES (?,?)";
		final String name = campground.getName();
		final String info = campground.getInfo();
		jdbcTemplate.update(sql, new Object[] {name, info}); 
		
	}
	
}
