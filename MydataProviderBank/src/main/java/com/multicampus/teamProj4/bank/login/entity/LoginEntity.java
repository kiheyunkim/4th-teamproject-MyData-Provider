package com.multicampus.teamProj4.bank.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
public class LoginEntity {
	@Id
	@Column(name = "PIN_NUMBER",unique = true, length = 64)
	private String pinNumber;
	@Column(name = "FINGERPRINT",unique = true, length = 64)
	private String fingerPrint;
	
	public LoginEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginEntity(String pinNumber, String fingerPrint) {
		this.pinNumber = pinNumber;
		this.fingerPrint = fingerPrint;
	}
	
	public String getPinNumber() {
		return pinNumber;
	}
	
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}
	
	public String getFingerPrint() {
		return fingerPrint;
	}
	
	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}
}
