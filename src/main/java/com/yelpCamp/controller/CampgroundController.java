package com.yelpCamp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@GetMapping
	public Collection<Campground> getAllCampgrounds(){
		return campgroundService.getAllCampgrounds();
	}
	
	@GetMapping(value = "/{id}")
    public Campground getCampgroundById(@PathVariable("id") int id){
        return campgroundService.getCampgroundById(id);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteCampgroundById(@PathVariable("id") int id){
        campgroundService.deleteCampgroundById(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateCampground(@RequestBody Campground campground){
        campgroundService.updateCampground(campground);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_USER')")
    public void insertCampground(@RequestBody Campground campground){
        campgroundService.insertCampground(campground);
    }
	

}
