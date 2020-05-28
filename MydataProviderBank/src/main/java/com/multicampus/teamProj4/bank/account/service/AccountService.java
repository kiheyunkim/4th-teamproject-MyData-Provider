package com.multicampus.teamProj4.bank.account.service;

import java.util.List;

import com.multicampus.teamProj4.bank.account.entity.AccountEntity;
import com.multicampus.teamProj4.bank.account.entity.AccountType;

public interface AccountService {
	public Boolean addAccount(String password, AccountType type, String identify);

	public Long getBalance(Long accountNum, String password);

	public Long getBalanceWithIdentifyStr(String identifyStr, String accountNumber);

	public Long addBalance(Long money, Long accountNum);

	public Long withdraw(Long accountNum, Long money, String password);

	public Long withdrawTo(Long accountNum, Long accountFrom, Long accountTo, String password);

	public List<AccountEntity> getUserAccounts(String identify);
	
	public String getIdentifyStr(Long accountNum, String password);
}
