package com.multicampus.teamProj4.bank.login.service;

public interface LoginService {
	public Boolean registerLoginInfo(String id, String password, String identifyStr);

	public String checkLoginInfo(String id, String password);

	public String loginWithIdentifyStr(String identify);
	
	public Boolean checkIdExist(String id);
}