package com.multicampus.teamProj4.bank.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.multicampus.teamProj4.bank.Exception.RepositoryException;
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
	public ModelAndView loginPage(ModelAndView model, @Param(value = "errorType") String resultParam) {
		model.setViewName("login");
		model.addObject("status", resultParam);
		
		return model;
	}

	@PostMapping("/loginRequest")
	@ResponseBody
	public Map<String, Object> loginRequest(HttpSession session,@RequestBody HashMap<String, Object> params){
		String id = (String) params.get("id");
		String password = (String) params.get("password");
		
		String identifyStr = null;
		Map<String, Object> retval = new HashMap<String, Object>();
		try {
			identifyStr = loginService.checkLoginInfo(id, password);
		} catch (RepositoryException e) {
			switch (e.getErrorType()) {
			case LOGIN_PASSWORD_NOT_MATCH:
				retval.put("result", "login_notMatch");
				break;
			default:
				retval.put("result", "login_error");
				break;
			}
		}catch (EntityNotFoundException e) {
			retval.put("result", "login_notMatch");
		}
		
		if(identifyStr != null) {
			session.setAttribute("Identity", identifyStr);
			retval.put("result", "login_ok");
		}
		return retval;
	}
	
	@GetMapping("/register")
	public String getRegister() {
		return "join";
	}
	@GetMapping("/register_individual")
	public String getIndividualRegister() {
		return "join_individual";
	}
}

























