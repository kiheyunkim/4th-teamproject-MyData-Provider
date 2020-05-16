package com.multicampus.teamProj4.bank.login.dao;

import java.util.Optional;

import org.hibernate.HibernateException;

public interface LoginDao{
	public void addFingerPrint(String pin, String fingerprint) throws HibernateException;
	public String getFingerPrint(String pinNumber) throws HibernateException;
	public void deleteFingerPrint(String pin, String fingerprint) throws HibernateException;
}