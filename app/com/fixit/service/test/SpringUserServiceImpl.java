package com.fixit.service.test;

import javax.inject.Inject;
import javax.inject.Named;

import com.fixit.dao.SpringUserRepository;
import com.fixit.model.test.SpringUser;

/**
 * Created by saeed on 9/March/15 AD.
 */

@Named
public class SpringUserServiceImpl implements SpringUserService {

	@Inject
	private SpringUserRepository userRepository;

	@Override
	public void addUser(SpringUser user) {
		userRepository.save(user);
		
	}
}