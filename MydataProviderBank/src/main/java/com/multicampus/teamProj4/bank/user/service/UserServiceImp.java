package com.multicampus.teamProj4.bank.user.service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.multicampus.teamProj4.bank.Exception.RepositoryException;
import com.multicampus.teamProj4.bank.Exception.RepositoryExceptionType;
import com.multicampus.teamProj4.bank.user.entity.UserEntity;
import com.multicampus.teamProj4.bank.user.reposiroty.UserRepository;

public class UserServiceImp implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public Boolean checkUser(String name, String birthDay, String indentifyStr) {
		UserEntity user = userRepository.getOne(indentifyStr);
		
		if(user == null) {
			throw new RepositoryException(RepositoryExceptionType.USER_NOT_EXIST);
		}
		
		return (name == user.getName() && birthDay == user.getBirthday()) ? true : false;
	}
	
}
