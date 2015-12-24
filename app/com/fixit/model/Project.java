package com.fixit.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fixit.model.user.YaUser;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "Project")
public class Project {

	public static final String STATUS_NEW = "New";
	public static final String STATUS_PUBLISHED = "Published";
	public static final String STATUS_DRAFT = "Draft";

	@Id
	public String id;
	public double version = 0;
	private String status = STATUS_NEW;

	public String getId() {
		return id;
	}

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

	public boolean contribute(Contribution contribution,
			List<Contribution> contributions) {
		Card card = getCard(contribution.getCardId());
		if (card != null) {
			return card.contribute(contribution, contributions);
		}
		return false;
	}

	public static Project instanciate(YaUser user) {
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

}
