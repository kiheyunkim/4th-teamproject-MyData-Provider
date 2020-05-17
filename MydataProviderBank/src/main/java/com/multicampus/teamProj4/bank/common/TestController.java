package com.multicampus.teamProj4.bank.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class TestController {
	
	@GetMapping(path = "/")
	public String getHome() {
		
		
		return "index";
	}
}
