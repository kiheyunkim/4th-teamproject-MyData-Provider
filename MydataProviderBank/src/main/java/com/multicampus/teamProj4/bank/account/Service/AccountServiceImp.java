package com.multicampus.teamProj4.bank.account.Service;

import static org.mockito.Mockito.doThrow;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multicampus.teamProj4.bank.Exception.RepositoryException;
import com.multicampus.teamProj4.bank.Exception.RepositoryExceptionType;
import com.multicampus.teamProj4.bank.account.Repository.AccountRepository;
import com.multicampus.teamProj4.bank.account.entity.AccountEntity;
import com.multicampus.teamProj4.bank.account.entity.AccountType;
import com.multicampus.teamProj4.bank.utils.RandomStringGenerator;

@Service
public class AccountServiceImp implements AccountService {
	private AccountRepository accountRepository;

	public AccountServiceImp(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	@Transactional
	public Long getBalance(Long accountNum, String password) {
		AccountEntity account = accountRepository.getOne(accountNum);

		if (!account.getPassword().equals(password)) {
			throw new RepositoryException(RepositoryExceptionType.ACCOUNT_PASSWORD_NOT_MATCH);
		}

		return account.getBalance();
	}

	@Override
	@Transactional
	public Boolean addAccount(String password, AccountType type, String identify) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] newSalt = messageDigest.digest(RandomStringGenerator.getRandomString(100).getBytes("UTF-8"));
			String newSaltStr = Base64.getEncoder().encodeToString(newSalt);
			byte[] newPassword = messageDigest.digest((password + newSaltStr).getBytes());
			String newPasswordStr = Base64.getEncoder().encodeToString(newPassword);

			AccountEntity accountEntity = new AccountEntity();
			accountEntity.setIdentify(identify);
			accountEntity.setAccountType(type);
			accountEntity.setBalance(0L);
			accountEntity.setPassword(newPasswordStr);
			accountEntity.setSalt(newSaltStr);

			accountRepository.saveAndFlush(accountEntity);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new RepositoryException(RepositoryExceptionType.ACCOUNT_ERROR_OCCURED);
		}
		return true;
	}

	@Override
	@Transactional
	public List<AccountEntity> getUserAccounts(String identify) {
		return accountRepository.findByidentify(identify);
	}

	@Override
	@Transactional
	public Long addBalance(Long money, Long accountNum) {
		AccountEntity account = accountRepository.getOne(accountNum);
		account.setBalance(account.getBalance() + money);
		accountRepository.save(account);

		return account.getBalance();
	}

	@Override
	@Transactional
	public Long withdraw(Long accountNum, Long money, String password) {
		AccountEntity account = accountRepository.getOne(accountNum);
		String salt = account.getSalt();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] checker = messageDigest.digest((password + salt).getBytes());

			if (!account.getPassword().equals(Base64.getEncoder().encodeToString(checker))) {
				throw new RepositoryException(RepositoryExceptionType.ACCOUNT_PASSWORD_NOT_MATCH);
			}

			if (account.getAccountType() == AccountType.SAVINGS) {
				throw new RepositoryException(RepositoryExceptionType.ACCOUNT_WITHDRAW_INVALID_TYPE);
			}

			if (account.getBalance() - money < 0 && account.getAccountType() != AccountType.MINUS) {
				throw new RepositoryException(RepositoryExceptionType.ACCOUNT_NOT_ENOUGH_BALANCE);
			}

			account.setBalance(account.getBalance() - money);
			return account.getBalance();

		} catch (NoSuchAlgorithmException e) {
			throw new RepositoryException(RepositoryExceptionType.ACCOUNT_ERROR_OCCURED);
		}
	}

	@Override
	@Transactional
	public Long withdrawTo(Long accountFrom, Long accountTo, Long money, String password) {
		AccountEntity accFrom = accountRepository.getOne(accountFrom);
		AccountEntity accTo = accountRepository.getOne(accountTo);
		String salt = accFrom.getSalt();

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] checker = messageDigest.digest((password + salt).getBytes());

			if (!accFrom.getPassword().equals(Base64.getEncoder().encodeToString(checker))) {
				throw new RepositoryException(RepositoryExceptionType.ACCOUNT_PASSWORD_NOT_MATCH);
			}

			if (accFrom.getAccountType() == AccountType.SAVINGS) {
				throw new RepositoryException(RepositoryExceptionType.ACCOUNT_WITHDRAW_INVALID_TYPE);
			}

			if (accFrom.getBalance() - money < 0 && accFrom.getAccountType() != AccountType.MINUS) {
				throw new RepositoryException(RepositoryExceptionType.ACCOUNT_NOT_ENOUGH_BALANCE);
			}

			accFrom.setBalance(accFrom.getBalance() - money);
			accTo.setBalance(accTo.getBalance() + money);

			return accFrom.getBalance();
		} catch (NoSuchAlgorithmException e) {
			throw new RepositoryException(RepositoryExceptionType.ACCOUNT_ERROR_OCCURED);
		}
	}

}
