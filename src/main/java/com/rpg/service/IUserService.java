package com.rpg.service;

import com.rpg.das.entity.User;

public interface IUserService {

	void loginUser();

	boolean checkIfNameTaken(String name);

	void saveUser(User user);

	void deleteUser(User user);

	User findUserByNameAndPwd(User user);

}
