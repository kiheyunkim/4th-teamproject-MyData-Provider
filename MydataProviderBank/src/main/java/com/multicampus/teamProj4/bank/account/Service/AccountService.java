package com.multicampus.teamProj4.bank.account.Service;

import com.multicampus.teamProj4.bank.account.entity.AccountType;

public interface AccountService {
	public Long getBalance(long id, String password);
	public Boolean addAccount(String password, AccountType type, String identify);
}
