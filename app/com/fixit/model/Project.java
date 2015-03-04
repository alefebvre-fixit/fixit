package com.fixit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

	public static final String STATUS_NEW = "New";
	public static final String STATUS_PUBLISHED = "Published";
	public static final String STATUS_DRAFT = "Draft";

	public String id;
	public double version = 0;
	private String status = STATUS_NEW;

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

	public double getVersion() {
		return version;
	}

	public void incrementVersion() {
		this.version++;
	}

	public String name;
	public String description;

	public String city;
	public String country;

	public String username;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVersion(double version) {
		this.version = version;
	}

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

	public boolean deleteCard(String cardId) {
		// TODO Change this implementation
		for (int i = 0; i < cards.size(); i++) {
			if (cardId.equals(cards.get(i).getId())) {
				cards.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean addCard(Card card) {
		// TODO Change this implementation
		if (card.getId() != null) {
			for (int i = 0; i < cards.size(); i++) {
				if (cards.get(i) != null) {
					if (card.getId().equals(cards.get(i).getId())) {
						cards.set(i, card);
						return true;
					}
				}
			}
		}
		cards.add(card);
		return true;
	}

	public int getContributions() {
		int result = 0;
		for (Card card : cards) {
			result += card.getContributions();
		}
		return result;
	}

	public boolean cancelContribution(Contribution contribution) {
		Card card = getCard(contribution.getCardId());
		if (card != null) {
			return card.cancel(contribution);
		}
		return false;
	}

	public boolean contribute(Contribution contribution, List<Contribution> contributions) {
		Card card = getCard(contribution.getCardId());
		if (card != null) {
			return card.contribute(contribution, contributions);
		}
		return false;
	}

	public static Project instanciate(User user) {
		Project project = new Project();
		project.username = user.getUsername();
		project.country = user.getProfile().getCountry();
		project.city = user.getProfile().getCity();

		project.name = "new project 2";
		project.description = "";

		return project;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static Map<String, Project> all() {
		// return find.all();

		Map<String, Project> result = new HashMap<String, Project>();

		Project project = new Project();
		project.id = "1";
		project.name = "Project A";
		project.description = "First " + loremIpsum;
		project.country = "France";
		project.city = "Paris";
		project.username = User.PAUL_SMITH;

		result.put(project.getId(), project);

		Project project2 = new Project();
		project2.id = "2";
		project2.name = "Project B";
		project2.description = "Seccond " + loremIpsum;
		project2.city = "San Francisco";
		project2.country = "USA";
		project2.username = User.PAUL_SMITH;

		result.put(project2.getId(), project2);

		Project project3 = new Project();
		project3.id = "3";
		project3.name = "Project C";
		project3.description = "Third " + loremIpsum;
		project3.city = "San Francisco";
		project3.country = "USA";
		project3.username = User.PAUL_SMITH;

		result.put(project3.getId(), project3);

		Project project4 = new Project();
		project4.id = "4";
		project4.name = "Project D";
		project4.description = "Fourth " + loremIpsum;
		project4.city = "San Francisco";
		project4.country = "USA";
		project4.username = User.ANTOINE;
		result.put(project4.getId(), project4);

		Project project5 = new Project();
		project5.id = "5";
		project5.name = "Project E";
		project5.description = "Fifth " + loremIpsum;
		project5.city = "San Francisco";
		project5.country = "USA";
		project5.username = User.ANTOINE;
		result.put(project5.getId(), project5);

		return result;

	}

	private static String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
}
