package com.tmh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.tmh.dao.GenericDAO;
import com.tmh.dao.CategoryDAO;
import com.tmh.entities.Category;

public class CategoryDAOImpl extends GenericDAO<Integer, Category> implements CategoryDAO {
	
	public CategoryDAOImpl() {
		super(Category.class);
	}
	
	public CategoryDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		return getSession().createQuery("from Category").getResultList();
	}
	
}