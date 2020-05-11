package com.rpg.service.impl;

import com.rpg.das.entity.User;
import com.rpg.das.repo.IUserRepository;
import com.rpg.das.repo.UserRepository;
import com.rpg.service.IUserService;

/**
 * User service
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserServiceImpl implements IUserService {
	
	private IUserRepository repo;
	
	public UserServiceImpl(){
		this.repo= new UserRepository();
	}

	@Override
	public void loginUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkIfNameTaken(String name) {
		return repo.findUserByName(name)==null?false:true;
	}
	@Override
	public void saveUser(User user){
		repo.saveUser(user);
	}

	@Override
	public void deleteUser(User user) {
		repo.deleteuser(user);
	}

	@Override
	public User findUserByNameAndPwd(User user) {
		return repo.findUserByNameAndPwd(user);
	}

}
