package com.tmh.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.tmh.entities.Category;
import com.tmh.service.CategoryService;


public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {
	
private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Override
	public Category saveOrUpdate(Category entity) {
		try {
			return getCategoryDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	@Override
	public Category findById(Serializable key) {
		try {
			return getCategoryDAO().findById(key);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(Category entity) {
		try {
			getCategoryDAO().delete(entity);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	@Override
	public List<Category> findAll() {
		try {
			return getCategoryDAO().findAll();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@Override
	public List<Category> findByKeyword(String keyword) {
		try {
			return getCategoryDAO().findByKeyword(keyword);
		} catch (Exception e) {
			return null;
		}
	}
	

}
