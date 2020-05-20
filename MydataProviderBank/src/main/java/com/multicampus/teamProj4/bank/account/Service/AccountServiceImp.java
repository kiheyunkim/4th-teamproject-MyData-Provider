package com.multicampus.teamProj4.bank.account.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.multicampus.teamProj4.bank.account.Exception.RepositoryException;
import com.multicampus.teamProj4.bank.account.Repository.AccountRepository;
import com.multicampus.teamProj4.bank.account.entity.AccountEntity;
import com.multicampus.teamProj4.bank.account.entity.AccountType;
import com.multicampus.teamProj4.bank.utils.RandomStringGenerator;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Service
public class AccountServiceImp implements AccountService {
	private AccountRepository accountRepository;

	public AccountServiceImp(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional
	public Long getBalance(long id, String password) {
		AccountEntity account = accountRepository.getOne(id);
		
		if(!account.getPassword().equals(password)) {
			throw new RepositoryException("password Not Match", 1L);
		}
		
		return account.getBalance();
	}
	
	@Transactional
	public Boolean addAccount(String password, AccountType type, String identify){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] newSalt = messageDigest.digest(RandomStringGenerator.getRandomString(100).getBytes("UTF-8"));
			String newSaltStr = Base64.encode(newSalt);
			byte[] newPassword = messageDigest.digest((password + newSaltStr).getBytes());
			String newPasswordStr = Base64.encode(newPassword);
			
			AccountEntity accountEntity = new AccountEntity();
			accountEntity.setIdentify(identify);
			accountEntity.setAccountType(type);
			accountEntity.setBalance(0L);
			accountEntity.setPassword(newPasswordStr);
			accountEntity.setSalt(newSaltStr);
			
			accountRepository.saveAndFlush(accountEntity);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return false;
		}	
		return true;
	}
	
	
	@Transactional
	public List<AccountEntity> getUserAccount(String identify) {
		return accountRepository.findByidentify(identify);
	}
	
	
	
	
}
