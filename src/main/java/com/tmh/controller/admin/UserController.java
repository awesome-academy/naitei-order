package com.tmh.controller.admin;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmh.entities.User;
import com.tmh.service.UserService;

@Controller
@RequestMapping(value = "/admin") 
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	public MessageSource messageSource;

	private static final Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "/users")
	public String showUserList(Model model) {
		logger.info("show users list");
		
		model.addAttribute("users", userService.findAll());
		
		return "views/admin/userManager/userList";
	}
	
	@RequestMapping(value = "/users/{id}")
	public String showUserDetail(@PathVariable("id") int id, Model model) {
		logger.info("show user detail");
		
		User user = userService.findById(id);
		
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", messageSource.getMessage("user.notfound", null, Locale.US));
		}
		
		model.addAttribute("user", user);
		
		return "views/admin/userManager/userDetail";
	}
	
	@RequestMapping(value = "/users/{id}/delete")
	public String deleteUser(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		logger.info("delete user");
		
		if (userService.delete(userService.findById(id))) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.delete", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.delete.fail", null, Locale.US));
		}
		
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/users/add")
	public String addNewUser(Model model) {
		logger.info("add user");
		
		User user = new User();
		user.setRole(0);
		
		model.addAttribute("userForm", user);
		model.addAttribute("status", "add");
		
		return "views/admin/userManager/userForm";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String submitAddOrUpdateUser(@ModelAttribute("userForm") User user, @RequestParam("status") String status, BindingResult bindingResult, Model model) {
		logger.info("submit add/update user");
		
		userService.saveOrUpdate(user);
		model.addAttribute("user", user);
		
		if (status.equals("add")) {
			model.addAttribute("msg", messageSource.getMessage("user.add", null, Locale.US));
		} else {
			model.addAttribute("msg", messageSource.getMessage("user.update", null, Locale.US));
		}
		
		return "views/admin/userManager/userDetail";
	}
	
	@RequestMapping(value = "/users/{id}/edit")
	public String editUser(@PathVariable("id") int id, Model model) {
		logger.info("edit user");
		
		User user = userService.findById(id);
		
		model.addAttribute("userForm", user);
		model.addAttribute("status", "edit");
		
		return "views/admin/userManager/userForm";
	}
	
}
