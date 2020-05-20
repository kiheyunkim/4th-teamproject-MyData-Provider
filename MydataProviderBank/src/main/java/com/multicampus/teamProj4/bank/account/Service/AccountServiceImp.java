package com.multicampus.teamProj4.bank.account.Service;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.multicampus.teamProj4.bank.account.Repository.AccountRepository;
import com.multicampus.teamProj4.bank.account.entity.AccountEntity;

//@Service
public class AccountServiceImp implements AccountService {
	private AccountRepository accountRepository;

	public AccountServiceImp(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional
	public Long getBalance(long id, String password) {
		AccountEntity account = null;
		try {
			account = accountRepository.getOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return account.getBalance();
	}
}
