package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.fixit.model.Contribution;
import com.fixit.service.ContributionService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoContributionService implements ContributionService {

	public static final String CONTRIBUTOR = "contributor";

	private DBObject convertToDBId(String contributionId) {
		return new BasicDBObject("_id", new ObjectId(contributionId));
	}

	/*
	private JacksonDBCollection<Contribution, String> getContributionsCollection() {
		return MongoDBPersistence.getContributionsCollection();
	}
	*/

	@Override
	public Contribution getContribution(String contributionId) {
		return null;
		
		/*
		return getContributionsCollection().findOne(
				convertToDBId(contributionId));
	
		*/
	}

	@Override
	public Contribution saveContribution(Contribution contribution) {
		return null;
		/*
		WriteResult<Contribution, String> result = null;
		if (contribution.id == null) {
			result = getContributionsCollection().insert(contribution);
			contribution.id = result.getSavedObject().id;
		} else {
			result = getContributionsCollection().update(
					DBQuery.is("_id", new ObjectId(contribution.id)),
					contribution);
		}
		
		return contribution;
		*/
	}

	@Override
	public void deleteContribution(String contributionId) {
		//getContributionsCollection().remove(convertToDBId(contributionId));
	}

	@Override
	public int countContributionsByOwner(String username) {
		return 0;
		
		/*
		return getContributionsCollection().find().is(CONTRIBUTOR, username)
				.count();
		*/
	}

	@Override
	public List<Contribution> getUserContributions(String username, int offset,
			int length) {
		return new ArrayList<Contribution>();
		
		/*
		DBCursor<Contribution> cursor = getContributionsCollection().find().is(
				CONTRIBUTOR, username);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);
		*/
	}

	@Override
	public List<Contribution> getProjectContributions(String username,
			String projectId) {
		return new ArrayList<Contribution>();

//		return getContributionsCollection().find().is(CONTRIBUTOR, username)
//				.is(MongoProjectService.PROJECT_ID, projectId).toArray();
	}

	@Override
	public List<Contribution> getCardContributions(String cardId) {
		return new ArrayList<Contribution>();
//		return getContributionsCollection().find()
//				.is(MongoProjectService.CARD_ID, cardId).toArray();
	}

	@Override
	public List<Contribution> getProjectContributions(String projectId) {
		return new ArrayList<Contribution>();

//		return getContributionsCollection().find()
//				.is(MongoProjectService.PROJECT_ID, projectId).toArray();

	}

	@Override
	public int countUserContributionForCard(String username, String cardId) {
		return 0;
//		return getContributionsCollection().find().is(CONTRIBUTOR, username)
//				.is(MongoProjectService.CARD_ID, cardId).count();
	}

	@Override
	public List<Contribution> getUserContributionForCard(String username,
			String cardId, int offset, int length) {
		return new ArrayList<Contribution>();

/*		DBCursor<Contribution> cursor = getContributionsCollection().find()
				.is(CONTRIBUTOR, username)
				.is(MongoProjectService.CARD_ID, cardId);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);
*/	}

	@Override
	public int countOtherContributionForCard(String username, String cardId) {
		return 0;
		
//		
//		return getContributionsCollection().find()
//				.notEquals(CONTRIBUTOR, username)
//				.is(MongoProjectService.CARD_ID, cardId).count();
	}

	@Override
	public List<Contribution> getOtherContributionForCard(String username,
			String cardId, int offset, int length) {
		return new ArrayList<Contribution>();

//		DBCursor<Contribution> cursor = getContributionsCollection().find()
//				.notEquals(CONTRIBUTOR, username)
//				.is(MongoProjectService.CARD_ID, cardId);
//		if (offset > 0) {
//			cursor.skip(offset);
//		}
//		if (length > 0) {
//			cursor.limit(length);
//		}
//		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);
	}

	@Override
	public List<Contribution> getUserContributionForCard(String username,
			String cardId) {
		return getUserContributionForCard(username, cardId, 0, 0);
	}
}
