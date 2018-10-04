package com.yelpCamp.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.yelpCamp.entity.Campground;

@Repository

public class CampgroundDao {
	
	private static Map<Integer, Campground> campgrounds;

    static {
        campgrounds = new HashMap<Integer, Campground>(){
            {
                put(1, new Campground(1, "Cloud's Rest", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Dolor morbi non arcu risus quis varius quam quisque id."));
                put(2, new Campground(2, "Low Bottom", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Netus et malesuada fames ac turpis egestas."));
                put(3, new Campground(3, "Mirkwood", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Posuere sollicitudin aliquam ultrices sagittis."));
            }
        };
    }

    public Collection<Campground> getAllCampgrounds(){
        return this.campgrounds.values();
    }

    public Campground getCampgroundById(int id){
        return this.campgrounds.get(id);
    }

    public void deleteCampgroundById(int id) {
        this.campgrounds.remove(id);
    }

    public void updateCampground(Campground campground){
        Campground s = campgrounds.get(campground.getId());
        s.setName(campground.getName());
        s.setInfo(campground.getInfo());
        campgrounds.put(campground.getId(), campground);
    }

    public void insertCampground(Campground campground) {
        this.campgrounds.put(campground.getId(), campground);
    }
}
