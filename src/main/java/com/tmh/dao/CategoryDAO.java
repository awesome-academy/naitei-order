package com.tmh.dao;

import java.util.List;

import com.tmh.entities.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category> {
	
	// List<Product> findByKeyword(String keyword);
	
	List<Category> findAll();
}
