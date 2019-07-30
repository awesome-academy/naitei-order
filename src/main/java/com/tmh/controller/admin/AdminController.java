package com.tmh.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmh.service.CategoryService;
import com.tmh.service.OrderService;
import com.tmh.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected OrderService orderService;

	@Autowired
	protected MessageSource messageSource;
	
	protected void loadModelAttribute(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("orders", orderService.findAll());
	}
	
}
