package com.tmh.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);

	@RequestMapping(value = "/admin")
	public String index(Model model) {
		logger.info("admin home page");
		return "views/admin/home";
	}
	
}