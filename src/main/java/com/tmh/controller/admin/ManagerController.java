package com.tmh.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController extends AdminController {
	
	private static final Logger logger = Logger.getLogger(ManagerController.class);

	@RequestMapping(value = "")
	public String admin(Model model, Authentication authentication) {
		logger.info("admin home page");
		model.addAttribute("userName", userService.findByEmail(authentication.getName()).getFullName());
		return "views/admin/home";
	}
	
}
