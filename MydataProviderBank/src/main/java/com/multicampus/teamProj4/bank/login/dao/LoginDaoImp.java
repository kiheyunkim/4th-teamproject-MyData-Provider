package com.multicampus.teamProj4.bank.login.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.multicampus.teamProj4.bank.login.entity.LoginEntity;

@Repository
public class LoginDaoImp implements LoginDao{

	private SessionFactory sessionFactory;
	
	public LoginDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addFingerPrint(String pin, String fingerprint){
	}

	@Override
	public String getFingerPrint(String pinNumber) {
		return null;
	}

	@Override
	public void deleteFingerPrint(String pin, String fingerprint){
	}

}
