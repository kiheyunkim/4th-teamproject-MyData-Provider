package com.multicampus.teamProj4.bank.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.multicampus.teamProj4.bank.Exception.RepositoryException;
import com.multicampus.teamProj4.bank.login.entity.LoginEntity;
import com.multicampus.teamProj4.bank.login.service.LoginService;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/")
	public ModelAndView loginPage(ModelAndView model) {	
		model.setViewName("login");		
		return model;
	}
	
	@PostMapping("/loginRequest")
	public ModelAndView loginRequest(ModelAndView model, HttpSession session, LoginEntity params){
		System.out.println(params.getPassword());
		String id = (String) params.getId();
		String password = (String) params.getPassword();
		String identifyStr = null;
		String result = "";
		
		try {
			identifyStr = loginService.checkLoginInfo(id, password);
		} catch (RepositoryException e) {
			switch (e.getErrorType()) {
			case LOGIN_PASSWORD_NOT_MATCH:
				result = "login_notMatch";
			default:
				result = "login_error";
				break;
			}
		} catch (EntityNotFoundException e) {
			result = "login_notFound";
		}
		
		if(identifyStr != null) {
			session.setAttribute("Identity", identifyStr);
			model.setViewName("index");
			return model;
		}
		
		model.setViewName("login");
		model.addObject("status", result);
		return model;
	}
}

























