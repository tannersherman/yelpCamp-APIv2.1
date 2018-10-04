package com.yelpCamp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yelpCamp.entity.Campground;
import com.yelpCamp.service.CampgroundService;

@RestController	
@RequestMapping("/campgrounds")
public class CampgroundController {

	@Autowired
	private CampgroundService campgroundService;
	
	@Autowired
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Campground> getAllCampgrounds(){
		return campgroundService.getAllCampgrounds();
	}
	
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Campground getCampgroundById(@PathVariable("id") int id){
        return campgroundService.getCampgroundById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCampgroundById(@PathVariable("id") int id){
        campgroundService.deleteCampgroundById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCampground(@RequestBody Campground campground){
        campgroundService.updateCampground(campground);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertCampground(@RequestBody Campground campground){
        campgroundService.insertCampground(campground);
    }
	

}
