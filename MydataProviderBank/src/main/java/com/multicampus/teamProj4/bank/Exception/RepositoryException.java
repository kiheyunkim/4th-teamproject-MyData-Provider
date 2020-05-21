package com.multicampus.teamProj4.bank.Exception;

import org.springframework.dao.DataAccessException;

public class RepositoryException extends DataAccessException {

	private static final long serialVersionUID = 1L;
	
	private RepositoryExceptionType errorType;

	public RepositoryException(RepositoryExceptionType errorType) {
		super("Repository Error - Type:" + errorType.name());
		this.errorType = errorType;
	}
	
	public RepositoryExceptionType getErrorType() {
		return errorType;
	}
}
