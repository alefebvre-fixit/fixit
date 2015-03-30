package com.fixit.service;

import java.util.List;

import com.fixit.model.project.ProjectComment;

public interface ProjectCommentService {

	public ProjectComment getComment(String commentId);

	public ProjectComment saveComments(ProjectComment comment);

	public void deleteComment(String commentId);

	public List<ProjectComment> getProjectComments(String projectId,
			int offset, int length);
	
	public int getProjectCommentSize(String projectId);
	
}
