package com.tmh.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/login")
	public String login(Model model) {
		logger.info("login page");
		return "views/admin/login";
	}
}
