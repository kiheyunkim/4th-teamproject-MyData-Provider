package com.multicampus.teamProj4.bank.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.multicampus.teamProj4.bank.login.dao.LoginDao;

@Controller
public class TestController {

	private LoginDao loginDao;
	
	public TestController(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	@GetMapping(path = "/")
	public String getHome() {
		
		
		return "index";
	}
}
