package com.enuri.gm.jca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class AdminController {

	@ResponseBody
	@RequestMapping("/test")
	public String testString() {
		String value = "테스트1";
		return value;
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}
}
