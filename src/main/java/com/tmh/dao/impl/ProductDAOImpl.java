package com.tmh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.tmh.dao.GenericDAO;
import com.tmh.dao.ProductDAO;
import com.tmh.entities.Product;

public class ProductDAOImpl extends GenericDAO<Integer, Product> implements ProductDAO {
	
	public ProductDAOImpl() {
		super(Product.class);
	}
	
	public ProductDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		return getSession().createQuery("from Product").getResultList();
	}
	
}
