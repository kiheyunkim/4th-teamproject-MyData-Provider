package com.multicampus.teamProj4.bank.test;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.multicampus.teamProj4.bank.config.ServletConfiguration;
import com.multicampus.teamProj4.bank.config.SpringConfiguration;
import com.multicampus.teamProj4.bank.login.dao.LoginDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class, ServletConfiguration.class})

public class TestClass {

	@Autowired
	private ApplicationContext applicationContext;
	
	private LoginDao loginDao;
		
	@Before
	public void init() {
		loginDao = applicationContext.getBean(LoginDao.class);
		System.out.println(loginDao);
	}
	
	@Test
	@Transactional
	public void insert() {
		loginDao.addFingerPrint("1234567890", "4321");
	}
	
	@Test
	@Transactional
	public void get() {
		System.out.println(loginDao.getFingerPrint("1234"));
	}
	
}
