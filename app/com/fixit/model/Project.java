package com.fixit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

@Entity
public class Project {

	private static final long serialVersionUID = 1L;

	public String id;
	public double version = 0;

	@Id
	@ObjectId
	public String getId() {
		return id;
	}

	@Id
	@ObjectId
	public void setId(String id) {
		this.id = id;
	}

	public double getVersion(){
		return version;
	}
	
	public void incrementVersion(){
		this.version++; 
	}
	
	public String name;
	public String description;

	public String city;
	public String country;

	public String username;

	public List<Card> cards = new ArrayList<Card>();

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Card getCard(String cardId) {
		for (Card card : cards) {
			if (cardId.equals(card.getId())) {
				return card;
			}
		}
		return null;
	}

	public Contribution getContribution(String contributionId) {
		for (Card card : cards) {
			Contribution contribution = card.getContribution(contributionId);
			if (contribution != null) {
				return contribution;
			}
		}
		return null;
	}

	public boolean cancelContribution(String contributionId) {
		for (Card card : cards) {
			Contribution contribution = card.getContribution(contributionId);
			if (contribution != null) {
				return card.cancel(contribution);
			}
		}
		return false;
	}

	public static Project instanciate(User user) {
		Project project = new Project();
		project.username = user.username;
		project.country = user.getProfile().getCountry();
		project.city = user.getProfile().getCity();

		project.name = "new project";
		project.description = "";

		return project;
	}

	public static List<Project> all() {
		// return find.all();

		List<Project> result = new ArrayList<Project>();

		Project project = new Project();
		project.id = "1";
		project.name = "Project A";
		project.description = "First " + loremIpsum;
		project.country = "France";
		project.city = "Paris";
		project.username = User.PAUL_SMITH;

		result.add(project);

		Project project2 = new Project();
		project2.id = "2";
		project2.name = "Project B";
		project2.description = "Seccond " + loremIpsum;
		project2.city = "San Francisco";
		project2.country = "USA";
		project2.username = User.PAUL_SMITH;

		result.add(project2);

		Project project3 = new Project();
		project3.id = "3";
		project3.name = "Project C";
		project3.description = "Third " + loremIpsum;
		project3.city = "San Francisco";
		project3.country = "USA";
		project3.username = User.PAUL_SMITH;

		result.add(project3);

		Project project4 = new Project();
		project4.id = "4";
		project4.name = "Project D";
		project4.description = "Fourth " + loremIpsum;
		project4.city = "San Francisco";
		project4.country = "USA";
		project4.username = User.ANTOINE;
		result.add(project4);

		Project project5 = new Project();
		project5.id = "5";
		project5.name = "Project E";
		project5.description = "Fifth " + loremIpsum;
		project5.city = "San Francisco";
		project5.country = "USA";
		project5.username = User.ANTOINE;
		result.add(project5);

		return result;

	}

	private static String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
}
