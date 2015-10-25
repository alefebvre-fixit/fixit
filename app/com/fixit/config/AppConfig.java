package com.fixit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import play.Play;

import com.fixit.dao.UserRepository;
import com.fixit.service.YaService;
import com.fixit.service.impl.MongoUserService;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteConcern;

import controllers.YaController;

@Configuration
@EnableMongoRepositories(basePackageClasses = { UserRepository.class })
@ComponentScan(basePackageClasses = { YaController.class, YaService.class,
		MongoUserService.class })
public class AppConfig {

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {

		String username = Play.application().configuration()
				.getString("mongodb.username");
		String password = Play.application().configuration()
				.getString("mongodb.password");
		String database = Play.application().configuration()
				.getString("mongodb.database");

		String host = Play.application().configuration()
				.getString("mongodb.host");

		MongoClientURI uri = new MongoClientURI(Play.application()
				.configuration().getString("mongodb.uri"));

		return new SimpleMongoDbFactory(uri);

	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		mongoTemplate.setWriteConcern(WriteConcern.SAFE);

		return mongoTemplate;

	}

}