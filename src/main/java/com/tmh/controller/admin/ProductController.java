package com.tmh.controller.admin;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tmh.entities.Product;

@Controller
public class ProductController extends AdminController {
	
	private static final Logger logger = Logger.getLogger(ProductController.class);
	
	@RequestMapping(value = "/products")
	public String showproductList(Model model) {
		logger.info("show products list");
		model.addAttribute("products", productService.findAll());
		return "views/admin/productManager/productList";
	}

	@RequestMapping(value = "/products/{id}")
	public String showproductDetail(@PathVariable("id") int id, Model model) {
		logger.info("show product detail");

		Product product = productService.findById(id);

		if (product == null) {
			model.addAttribute("css", "error");
			model.addAttribute("msg", messageSource.getMessage("product.notfound", null, Locale.US));
		}

		model.addAttribute("product", product);

		return "views/admin/productManager/productDetail";
	}

	@RequestMapping(value = "/products/{id}/delete")
	public String deleteProduct(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		logger.info("delete product");

		if (productService.delete(productService.findById(id))) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("product.delete", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg",
					messageSource.getMessage("product.delete.fail", null, Locale.US));
		}

		return "redirect:/admin/products";
	}

	@RequestMapping(value = "/products/add")
	public String addNewProduct(Model model) {
		logger.info("add product");

		model.addAttribute("productForm", new Product());
		model.addAttribute("status", "add");

		return "views/admin/productManager/productForm";
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String submitAddOrUpdateProduct(@Valid @ModelAttribute("productForm") Product product,
			BindingResult bindingResult, @RequestParam("status") String status, Model model) {
		logger.info("submit add/update product");

		if (bindingResult.hasErrors()) {

			model.addAttribute("status", status);

			return "views/admin/productManager/productForm";
		}

		try {
			// product.setCategory(categoryService.findById(categoryMenu.getId()));

			productService.saveOrUpdate(product);
		} catch (Exception e) {
			model.addAttribute("status", status);
			model.addAttribute("css", "error");
			if (status.equals("add")) {
				model.addAttribute("msg", messageSource.getMessage("product.add.fail", null, Locale.US));
			} else {
				model.addAttribute("msg", messageSource.getMessage("product.update.fail", null, Locale.US));
			}

			return "views/admin/productManager/productForm";
		}

		model.addAttribute("product", product);
		model.addAttribute("categoryName", categoryService.findById(product.getCategory().getId()).getName());

		if (status.equals("add")) {
			model.addAttribute("msg", messageSource.getMessage("product.add", null, Locale.US));
		} else {
			model.addAttribute("msg", messageSource.getMessage("product.update", null, Locale.US));
		}

		return "views/admin/productManager/productDetail";
	}

	@RequestMapping(value = "/products/{id}/edit")
	public String editProduct(@PathVariable("id") int id, Model model) {
		logger.info("edit product");

		Product product = productService.findById(id);

		model.addAttribute("productForm", product);
		model.addAttribute("status", "edit");

		return "views/admin/productManager/productForm";
	}

	@RequestMapping(value = "/products/search")
	public String searchProduct(@RequestParam("keyword") String keyword, @ModelAttribute("productList") Product product,Model model) {
		logger.info("search product");

		model.addAttribute("categoryName", categoryService.findById(product.getCategory().getId()).getName());
		model.addAttribute("products", productService.findByKeyword(keyword));
		model.addAttribute("keyword", keyword);

		return "views/admin/productManager/productList";
	}


}
