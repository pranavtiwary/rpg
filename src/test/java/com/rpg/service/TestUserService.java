package com.rpg.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rpg.das.entity.User;
import com.rpg.service.impl.UserServiceImpl;

public class TestUserService {

	private IUserService service = null;

	@Before
	public void setup(){
		service = new UserServiceImpl();
	}

	@After
	public void testDown(){

	}

	@Test
	public void testWriteUser(){
		User user = new User("testing", "testing");
		service.saveUser(user);
		User userFromDb=service.findUserByNameAndPwd(user);
		assertEquals(user.getName(), userFromDb.getName());
		service.deleteUser(user);
		userFromDb=service.findUserByNameAndPwd(user);
		assertNull(userFromDb);

	}

}
