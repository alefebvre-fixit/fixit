package com.fixit.model.user;

import java.util.ArrayList;
import java.util.List;

public class Profile {

	private static final long serialVersionUID = 1L;

    private String name;

    private String website;
    private String biography;

    private String city;
    private String country;

    private List<String> interest = new ArrayList<String>();
    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getWebsite(){
        return this.website;
    }

    public void setWebsite(String website){
        this.website = website;
    }

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getInterest() {
		return interest;
	}

	public void setInterest(List<String> interest) {
		this.interest = interest;
	}

}
