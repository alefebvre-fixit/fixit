package com.fixit.service.impl;

import java.util.List;

import com.fixit.model.project.ProjectComment;
import com.fixit.service.ProjectCommentService;

public class MongoProjectCommentService implements ProjectCommentService {

	public static final String USERNAME = "username";
	public static final String PROJECT_ID = "projectId";

/*	private JacksonDBCollection<ProjectComment, String> getCommentsCollection() {
		return MongoDBPersistence.getCommentsCollection();
	}*/

	@Override
	public ProjectComment getComment(String commentId) {
		return null;
/*		return getCommentsCollection().findOneById(commentId);
*/	}

	@Override
	public ProjectComment saveComments(ProjectComment comment) {
		return null;
/*		WriteResult<ProjectComment, String> result = null;
		if (comment.getId() == null) {
			result = getCommentsCollection().insert(comment);
			comment.setId(result.getSavedId());
		} else {
			result = getCommentsCollection().updateById(comment.getId(),
					comment);
		}
		return comment;*/
	}

	@Override
	public void deleteComment(String commentId) {
/*		getCommentsCollection().removeById(commentId);
*/	}

	@Override
	public List<ProjectComment> getProjectComments(String projectId,
			int offset, int length) {
		return null;
/*		DBCursor<ProjectComment> cursor = getCommentsCollection().find().is(
				PROJECT_ID, projectId);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);*/
	}

	@Override
	public int getProjectCommentSize(String projectId) {
		return 0;
/*		return getCommentsCollection().find().is(
				PROJECT_ID, projectId).count();*/
	}

}
