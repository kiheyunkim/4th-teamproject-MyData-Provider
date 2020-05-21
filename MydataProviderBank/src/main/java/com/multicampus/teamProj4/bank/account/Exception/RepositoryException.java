package com.multicampus.teamProj4.bank.account.Exception;

import org.springframework.dao.DataAccessException;

public class RepositoryException extends DataAccessException {

	private static final long serialVersionUID = 1L;
	
	private Long errorNum;

	public RepositoryException(String msg, Long errorType) {
		super(msg);
		this.errorNum = errorType;
	}
	
	public Long getErrorType() {
		return errorNum;
	}
}
