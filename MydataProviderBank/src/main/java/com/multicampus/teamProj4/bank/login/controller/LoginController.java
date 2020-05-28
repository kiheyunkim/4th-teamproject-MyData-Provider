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
	public ModelAndView loginPage(ModelAndView model, @Param(value = "errorType") String resultParam) {
		model.setViewName("login");
		model.addObject("status", resultParam);
		
		return model;
	}

	@PostMapping("/loginRequest")
	@ResponseBody
	public Map<String, Object> loginRequest(HttpSession session, LoginEntity params){
		String id = params.getId();
		String password = params.getPassword();
		
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
	public String getIndividualRegister(HttpSession session) {
		if(!session.getAttribute("agreementList").equals(null)) {
			session.removeAttribute("agreementList");
		}
		Map<String, Object> agreementMap = new HashMap<String, Object>();
		agreementMap.put("currentPage", 1);
		session.setAttribute("agreementList", agreementMap);
		
		
		return "join_individual";
	}
	
	@PostMapping("/register_individual")
	@ResponseBody
	public Map<String, Object> postIndividualRegister(HttpSession session, @RequestBody HashMap<String, Object> params){
		Map<String, Object> retval = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String, Object> agreementMap = (Map<String, Object>) session.getAttribute("agreementList");
		if(agreementMap == null) {
			retval.put("result", "error_Occured");
		}
		
		int sessionCurrentPage = (int) agreementMap.get("currentPage");
		int currentPage = (int)params.get("currentPage");
		
		//세션의 위치와 정적페이지의 위치가 다르면 에러
		if(sessionCurrentPage != currentPage) {
			retval.put("result", "error_Occured");
			return retval;
		}
		
		if(sessionCurrentPage == 1) {
			boolean serviceAgreement = (boolean) params.get("serviceAgreement");
			boolean privacyAgreement = (boolean) params.get("privacyAgreement");
			
			if(!serviceAgreement) {
				retval.put("result", "service_not_agreed");
				return retval;
			}
			else if(!privacyAgreement) {
				retval.put("result", "privacy_no_agreed");
				return retval;
			}
			else {
				agreementMap.put("auth1", "ok");
				retval.put("result", "ok");
			}
		}		
		
		return retval;
	}
}

























