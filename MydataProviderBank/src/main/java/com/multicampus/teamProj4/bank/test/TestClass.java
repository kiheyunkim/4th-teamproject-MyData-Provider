package com.multicampus.teamProj4.bank.test;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Ignore;
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
@ContextConfiguration(classes = { SpringConfiguration.class }, loader = AnnotationConfigContextLoader.class)

public class TestClass {

	@Autowired
	private ApplicationContext applicationContext;

	private AccountService accountService;

	@Before
	public void init() {
		accountService = applicationContext.getBean(AccountService.class);
		// System.out.println(loginDao);
	}

	@Test
	public void addBal() {
		accountService.addBalance(10L, 4L);
	}

	@Test
	public void Withdraw() {
		accountService.withdrawTo(4L, 4L, 1L, "123");
	}

	@Test
	public void Withdraw2() {
		accountService.withdrawTo(4L, 400L, 1L, "1234");
	}

	@Test
	public void Withdraw3() {
		accountService.withdrawTo(40L, 1L, 1L, "1234");
	}

	@Test
	public void Withdraw4() {
		accountService.withdrawTo(4L, 1L, 1L, "1234");
	}
	
	@Test
	@Ignore
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
	@Ignore
	public void find() {
		try {

		} catch (EntityNotFoundException e) {
			System.out.println("Not Found");
		} catch (RepositoryException e) {
			System.out.println("password Not Match");
		}
	}

}
