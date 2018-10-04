package com.yelpCamp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yelpCamp.dao.CampgroundDao;
import com.yelpCamp.entity.Campground;

@Service
public class CampgroundService {

    @Autowired
    private CampgroundDao campgroundDao;

    public Collection<Campground> getAllCampgrounds(){
        return this.campgroundDao.getAllCampgrounds();
    }

    public Campground getCampgroundById(int id){
        return this.campgroundDao.getCampgroundById(id);
    }

    public void deleteCampgroundById(int id) {
        this.campgroundDao.deleteCampgroundById(id);
    }

    public void updateCampground(Campground campground){
        this.campgroundDao.updateCampground(campground);
    }

    public void insertCampground(Campground campground) {
        this.campgroundDao.insertCampground(campground);
    }
}

