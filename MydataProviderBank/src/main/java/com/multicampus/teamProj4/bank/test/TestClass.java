package com.multicampus.teamProj4.bank.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.multicampus.teamProj4.bank.account.Repository.AccountRepository;
import com.multicampus.teamProj4.bank.account.Service.AccountService;
import com.multicampus.teamProj4.bank.account.Service.AccountServiceImp;
import com.multicampus.teamProj4.bank.account.entity.AccountEntity;
import com.multicampus.teamProj4.bank.config.SpringConfiguration;
import com.multicampus.teamProj4.bank.login.dao.LoginDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		classes = {SpringConfiguration.class},
		loader = AnnotationConfigContextLoader.class)

public class TestClass {

	@Autowired
	private ApplicationContext applicationContext;
	
	private AccountRepository accountRepository;
		
	
	@Before
	public void init() {
		try {
			accountRepository = applicationContext.getBean(AccountRepository.class);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(accountRepository);
		//System.out.println(loginDao);
	}
	
	@Test
	public void find() {
		accountRepository.findById(1L);
		
		//accountService.getBalance(1, "1234");
	}
	
}
