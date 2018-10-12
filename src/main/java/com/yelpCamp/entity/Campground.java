package com.yelpCamp.entity;

public class Campground {
    private int id;
    private String name;
    private String info;
    private String author;

    public Campground(int id, String name, String info, String author) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.author = author;
    }

    public Campground(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
    
    
}