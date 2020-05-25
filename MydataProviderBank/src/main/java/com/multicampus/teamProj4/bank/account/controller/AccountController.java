package com.multicampus.teamProj4.bank.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.multicampus.teamProj4.bank.account.entity.AccountEntity;
import com.multicampus.teamProj4.bank.account.service.AccountService;


@Controller
@RequestMapping("/account")
public class AccountController {

	private AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@GetMapping("/getBalance")
	@ResponseBody
	public Map<String, Object> getBalance(HttpSession session, @RequestBody HashMap<String,Object> request){
		
		String requestAccountNumber = (String) request.get("accountNum");
		String identifyStr = (String) session.getAttribute("identifyStr"); 
		
		Map<String, Object> retval = new HashMap<String, Object>();
		Long balance = null;
		try {
			balance = accountService.getBalanceWithIdentifyStr(identifyStr, requestAccountNumber);
		} 
		catch (EntityNotFoundException e) {
			retval.put("result","Not Found");
			return retval;
		}
		
		retval.put("balance", balance);
		return retval;
	}
	
	@GetMapping("/accounts")
	public ModelAndView getAllAccount(HttpSession session, ModelAndView model) {
		String identifyStr = "12344";//(String) session.getAttribute("identifyStr");
		List<AccountEntity> accounts = accountService.getUserAccounts(identifyStr);
		
		model.addObject("accounts", accounts);
		model.setViewName("index");
		return model;
	}
	
	
}
