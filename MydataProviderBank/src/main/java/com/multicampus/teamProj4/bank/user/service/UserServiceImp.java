package com.multicampus.teamProj4.bank.user.service;

import com.multicampus.teamProj4.bank.user.reposiroty.UserRepository;

public class UserServiceImp implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
