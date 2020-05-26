package com.multicampus.teamProj4.bank.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CommonController {
	@GetMapping("/")
	public String mainIndex() {
		return "index";
	}
}