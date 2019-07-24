package com.tmh.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tmh.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	private static final Logger logger = Logger.getLogger(CategoryController.class);

	@RequestMapping(value = "/categories")
	public String userList(Model model) {
		logger.info("category list page");
		model.addAttribute("categories", categoryService.findAll());
		return "views/admin/categoryManager/categoryList";
	}
	
}