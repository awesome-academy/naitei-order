package com.tmh.controller.admin;

import java.util.Locale;

import javax.validation.Valid;

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

import com.tmh.entities.Category;
import com.tmh.service.CategoryService;

@Controller
@RequestMapping(value = "/admin")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	public MessageSource messageSource;

	private static final Logger logger = Logger.getLogger(CategoryController.class);

	@RequestMapping(value = "/categories")
	public String categoryList(Model model) {
		logger.info("category list page");
		model.addAttribute("categories", categoryService.findAll());
		return "views/admin/categoryManager/categoryList";
	}
	
	@RequestMapping(value = "/categories/{id}/delete")
	public String deleteCategory(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		logger.info("delete category");
		
		if (categoryService.delete(categoryService.findById(id))) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("category.delete", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("category.delete.fail", null, Locale.US));
		}
		
		return "redirect:/admin/categories";
	}
	
	@RequestMapping(value = "/categories/add")
	public String addNewcategory(Model model) {
		logger.info("add category");
		
		Category category = new Category();
		
		
		model.addAttribute("categoryForm", category);
		model.addAttribute("status", "add");
		
		return "views/admin/categoryManager/categoryForm";
	}
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public String submitAddOrUpdateCategory(@Valid @ModelAttribute("categoryForm") Category category, BindingResult bindingResult, @RequestParam("status") String status,final RedirectAttributes redirectAttributes,  Model model) {
		logger.info("submit add/update category");
		if (bindingResult.hasErrors()) {
            return "views/admin/categoryManager/categoryForm";
        }
		
		categoryService.saveOrUpdate(category);
		model.addAttribute("category", category);
		if (status.equals("add")) {
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("category.add", null, Locale.US));
		} 
		if (status.equals("edit")) {
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("category.update", null, Locale.US));
		}
		return "redirect:/admin/categories";
	}
	
	@RequestMapping(value = "/categories/{id}/edit")
	public String editCategory(@PathVariable("id") int id, Model model) {
		logger.info("edit category");
		
		Category category = categoryService.findById(id);
		
		model.addAttribute("categoryForm", category);
		model.addAttribute("status", "edit");
		
		return "views/admin/categoryManager/categoryForm";
	}
	
}