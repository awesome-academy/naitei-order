package com.tmh.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmh.entities.User;
import com.tmh.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	private static final int ADMIN = 1;
	private static final int USER = 0;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public String login(Model model) {
		logger.info("login page");
		return "views/admin/login";
	}
	
	/*
	 * @RequestMapping(value = "/loginProgess", method = RequestMethod.POST ) public
	 * String loginProgess(@RequestParam("email") String
	 * email, @RequestParam("password") String password, Model model , HttpSession
	 * session, final RedirectAttributes redirectAttributes) {
	 * logger.info("login progess");
	 * 
	 * User user = userService.findByEmailAndPassword(email, password); if(user
	 * !=null) { if (user.getRole() == ADMIN) { return "redirect:admin"; }else {
	 * return "redirect:/"; } }
	 * 
	 * return "redirect:/"; }
	 */
	
	@RequestMapping(value = "/logout")
    public String logout() {
		logger.info("logout progess");
        return "redirect:/login";
    }
}
