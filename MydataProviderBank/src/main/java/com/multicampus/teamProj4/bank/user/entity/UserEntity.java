package com.multicampus.teamProj4.bank.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USER")
public class UserEntity {
	@Id
	@Column
	private String identifyStr;
	@Column
	private String birthday;
	@Column
	private Character gender;
	@Column
	private String name;
	@Column
	private String phoneNumber;
	@Column
	private String email;
	@Column
	private String address;

	public String getIdentifyStr() {
		return identifyStr;
	}

	public void setIdentifyStr(String identifyStr) {
		this.identifyStr = identifyStr;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
