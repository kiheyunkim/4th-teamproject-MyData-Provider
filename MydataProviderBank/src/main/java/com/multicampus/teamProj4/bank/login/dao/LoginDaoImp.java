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
		Session session = sessionFactory.getCurrentSession();
		LoginEntity loginEntity = new LoginEntity(pin, fingerprint);
		session.persist(loginEntity);
		session.flush();
	}

	@Override
	public String getFingerPrint(String pinNumber) {
		Session session = sessionFactory.getCurrentSession();
		LoginEntity loginEntity = session.find(LoginEntity.class, pinNumber);
		return loginEntity == null ? null : loginEntity.getPinNumber();
	}

	@Override
	public void deleteFingerPrint(String pin, String fingerprint){
		Session session = sessionFactory.getCurrentSession();
		LoginEntity loginEntity = new LoginEntity(pin, fingerprint);
		session.delete(loginEntity);
	}

}
