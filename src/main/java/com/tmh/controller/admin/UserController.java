package com.tmh.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmh.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	private static final Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/users")
	public String userList(Model model) {
		logger.info("user list page");
		model.addAttribute("users", userService.findAll());
		return "views/admin/userManager/userList";
	}
	
}