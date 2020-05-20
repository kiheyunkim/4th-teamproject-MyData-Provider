package com.multicampus.teamProj4.bank.account.Service;

import java.util.List;

import com.multicampus.teamProj4.bank.account.entity.AccountEntity;
import com.multicampus.teamProj4.bank.account.entity.AccountType;

public interface AccountService {
	public Long getBalance(long id, String password);
	public Boolean addAccount(String password, AccountType type, String identify);
	public List<AccountEntity> getUserAccount(String identify);
}
