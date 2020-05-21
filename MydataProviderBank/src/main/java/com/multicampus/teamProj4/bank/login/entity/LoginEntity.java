package com.multicampus.teamProj4.bank.login.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.multicampus.teamProj4.bank.utils.RandomStringGenerator;

@Entity
@Table(name = "Login")
public class LoginEntity {
	@Id
	@Column
	private String id;
	@Column
	private String password;
	@Column
	private String salt;
	@Column(unique = true)
	private String identifyStr;
	@Column(unique = true) // for 공인인증
	private String uniqueStr;

	public LoginEntity() {
	}

	public LoginEntity(String id, String password, String salt, String identifyStr) 
			throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] uniqueStr = messageDigest.digest((id + password +
				salt + identifyStr + (new Date(System.currentTimeMillis()).toString() +
						RandomStringGenerator.getRandomString(50))).getBytes());
		
		this.id = id;
		this.password = password;
		this.salt = salt;
		this.identifyStr = identifyStr;
		this.uniqueStr = Base64.getEncoder().encodeToString(uniqueStr);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIndentifyStr() {
		return identifyStr;
	}

	public void setIndentifyStr(String indentifyStr) {
		this.identifyStr = indentifyStr;
	}

	public String getUniqueStr() {
		return uniqueStr;
	}

	public void setUniqueStr(String uniqueStr) {
		this.uniqueStr = uniqueStr;
	}
}
