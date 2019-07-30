package com.tmh.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/")
	public String index(Model model) {
		logger.info("home page");
		return "views/client/home";
	}
	
	@RequestMapping(value = "/admin")
	public String admin(Model model) {
		logger.info("admin home page");
		return "views/admin/home";
	}
	
}
