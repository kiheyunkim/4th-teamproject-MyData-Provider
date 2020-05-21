package com.multicampus.teamProj4.bank.login.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multicampus.teamProj4.bank.Exception.RepositoryException;
import com.multicampus.teamProj4.bank.Exception.RepositoryExceptionType;
import com.multicampus.teamProj4.bank.login.entity.LoginEntity;
import com.multicampus.teamProj4.bank.login.repository.LoginRepository;
import com.multicampus.teamProj4.bank.utils.RandomStringGenerator;

@Service
public class LoginServiceImp implements LoginService{
	private LoginRepository loginRepository;
	
	public LoginServiceImp(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	} 
	
	@Override
	@Transactional
	public Boolean registerLoginInfo(String id, String password, String identifyStr) {
		String randomString = RandomStringGenerator.getRandomString(100);
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] salt = messageDigest.digest(randomString.getBytes());
			String saltStr = Base64.getEncoder().encodeToString(salt);

			byte[] hashedPassword = messageDigest.digest((password + saltStr).getBytes());
			String hashedPasswordStr = Base64.getEncoder().encodeToString(hashedPassword);

			LoginEntity loginEntity = new LoginEntity(id, hashedPasswordStr, saltStr, identifyStr);
			loginRepository.save(loginEntity);

		} catch (NoSuchAlgorithmException e) {
			throw new RepositoryException(RepositoryExceptionType.LOGIN_ERROR_OCCURED); }
		 
		return true;
	}

	@Override
	@Transactional
	public String checkLoginInfo(String id, String password) {
		try {
			LoginEntity loginAccount = loginRepository.getOne(id);
			String passwordStr = loginAccount.getPassword();
			String saltStr = loginAccount.getSalt();
			
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hashedPassword = messageDigest.digest((password + saltStr).getBytes());
			String hashedPasswordStr = Base64.getEncoder().encodeToString(hashedPassword);
			if(!passwordStr.equals(hashedPasswordStr)) {
				throw new RepositoryException(RepositoryExceptionType.LOGIN_PASSWORD_NOT_MATCH);
			}
			
			return loginAccount.getIndentifyStr();
		} catch (NoSuchAlgorithmException e) {
			throw new RepositoryException(RepositoryExceptionType.LOGIN_ERROR_OCCURED);
		}
	}

	@Override
	@Transactional
	public String loginWithIdentifyStr(String identify) {
		LoginEntity account = loginRepository.findByUniqueStr(identify);
		
		return account.getIndentifyStr();
	}
}
