package com.fixit.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.fixit.dao.UserRepository;
import com.fixit.model.SpringUser;

/**
 * Created by saeed on 9/March/15 AD.
 */

@Named
public class SpringUserServiceImpl implements SpringUserService {

	@Inject
	private UserRepository userRepository;

	@Override
	public void addUser(SpringUser user) {
		userRepository.save(user);
	}
}