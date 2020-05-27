package com.multicampus.teamProj4.bank.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	@ResponseBody
	public Map<String, Object> loginRequest(ModelAndView model, HttpSession session, LoginEntity params){
		String id = (String) params.getId();
		String password = (String) params.getPassword();
		String identifyStr = null;
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			identifyStr = loginService.checkLoginInfo(id, password);
		} catch (RepositoryException e) {
			switch (e.getErrorType()) {
			case LOGIN_PASSWORD_NOT_MATCH:
				result.put("result", "login_notMatch");
			default:
				result.put("result", "login_error");
				break;
			}
			return result;
			
		} catch (EntityNotFoundException e) {
			result.put("result", "login_notMatch");
			return result;
		}
		if(identifyStr != null) {
			session.setAttribute("Identity", identifyStr);
		}
		return result;
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "join";
	}
	
	@GetMapping("/individual_register")
	public String getRegisterIndividual() {
		return "join_individual";
	}
}

























