package com.tmh.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/login")
	public String login(@RequestParam(value = "error", required = false) final String error, final Model model) {
		logger.info("login page");
		if (error != null) {
			model.addAttribute("css", "error");
			model.addAttribute("msg", messageSource.getMessage("login.fail", null, Locale.US));
		}
		return "views/admin/login";
	}
	
	
	@RequestMapping(value = "/logout")
    public String logout() {
		logger.info("logout progess");
        return "redirect:/login";
    }
}
