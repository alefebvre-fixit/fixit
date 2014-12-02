package com.fixit.service.impl;

import java.util.List;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;
import play.modules.mongojack.MongoDB;

import com.fixit.model.Card;
import com.fixit.model.Contribution;
import com.fixit.model.Project;
import com.fixit.service.ProjectService;

public class MongoProjectService extends BaseProjectService implements ProjectService {

	private static JacksonDBCollection<Project, String> coll = MongoDB
			.getCollection("Project", Project.class, String.class);

	@Override
	public List<Project> getAll() {
		Logger.debug("MongoProjectService.getAll()");

		return coll.find().toArray();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoProjectService.delete(String id) id=" + id);

		coll.removeById(id);
	}

	@Override
	public String create(Project project) {
		Project result = save(project);
		return result.getId();
	}

	@Override
	public Project save(Project project) {
		WriteResult<Project, String> result = null;
		assignCardIds(project);
		project.incrementVersion();
		if (project.getId() == null) {
			Logger.debug("MongoProjectService.save.insert()");
			result = coll.insert(project);
			project.setId(result.getSavedId());
		} else {
			Logger.debug("MongoProjectService.save.updateById(String id) id=" + project.id);

			result = coll.updateById(project.id, project);
		}

		return project;
	}



	@Override
	public Project load(String id) {
		Logger.debug("MongoProjectService.load(String id) id=" + id);
		Project result = coll.findOneById(id);
		return result;
	}

	@Override
	public List<Project> loadByOwner(String owner) {
		Logger.debug("loadByOwner(String owner) owner=" + owner);

		List<Project> result = coll.find().is("username", owner).toArray();

		return result;
	}

}
