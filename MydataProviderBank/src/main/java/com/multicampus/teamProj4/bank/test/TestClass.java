package com.multicampus.teamProj4.bank.test;


import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.multicampus.teamProj4.bank.account.Exception.RepositoryException;
import com.multicampus.teamProj4.bank.account.Service.AccountService;
import com.multicampus.teamProj4.bank.account.entity.AccountType;
import com.multicampus.teamProj4.bank.config.SpringConfiguration;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {SpringConfiguration.class},
		loader = AnnotationConfigContextLoader.class)

public class TestClass {

	@Autowired
	private ApplicationContext applicationContext;
	
	private AccountService accountService;
		
	
	@Before
	public void init() {
		accountService = applicationContext.getBean(AccountService.class);			
		//System.out.println(loginDao);
	}
	
	@Test
	public void add() {
		try {
			accountService.addAccount("1234", AccountType.DEPOSIT, "12344");
		} catch (EntityNotFoundException e) {
			System.out.println("Not Found");
		} catch (RepositoryException e) {
			System.out.println("password Not Match");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void find() {
		try {
			accountService.getBalance(1234, "1234");
			
		} catch (EntityNotFoundException e) {
			System.out.println("Not Found");
		} catch (RepositoryException e) {
			System.out.println("password Not Match");
		}
	}
	
	@After
	public void flush() {
	}
	
}
