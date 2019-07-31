package com.tmh.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	@Override
	public List<Product> findByKeyword(String keyword) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root);

		query.where(builder.or(builder.like(root.get("name"), "%" + keyword + "%"),
				builder.like(root.get("description"), "%" + keyword + "%"),
				builder.like(root.get("categoryName"), "%" + keyword + "%")));

		return getSession().createQuery(query).getResultList();
	}

}
