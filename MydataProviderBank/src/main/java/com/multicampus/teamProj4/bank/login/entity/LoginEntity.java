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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fingerPrint == null) ? 0 : fingerPrint.hashCode());
		result = prime * result + ((pinNumber == null) ? 0 : pinNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginEntity other = (LoginEntity) obj;
		if (fingerPrint == null) {
			if (other.fingerPrint != null)
				return false;
		} else if (!fingerPrint.equals(other.fingerPrint))
			return false;
		if (pinNumber == null) {
			if (other.pinNumber != null)
				return false;
		} else if (!pinNumber.equals(other.pinNumber))
			return false;
		return true;
	}
}
