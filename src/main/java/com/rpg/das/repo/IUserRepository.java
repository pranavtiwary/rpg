package com.rpg.das.repo;

import com.rpg.das.entity.User;

public interface IUserRepository {

	public void saveUser(User user);
	public User findUserByName(String name);
	public void deleteuser(User user);
	public User findUserByNameAndPwd(User user);
}
