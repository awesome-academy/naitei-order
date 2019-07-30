package com.tmh.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;

import com.tmh.dao.GenericDAO;
import com.tmh.dao.OrderDAO;
import com.tmh.entities.Order;

public class OrderDAOImpl extends GenericDAO<Integer, Order> implements OrderDAO {

	public OrderDAOImpl() {
		super(Order.class);
	}
	
	public OrderDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() {
		return getSession().createQuery("from Order").getResultList();
	}
	
	@Override
	public List<Order> findByKeyword(String keyword) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Order> query = builder.createQuery(Order.class);
		Root<Order> root = query.from(Order.class);
		query.select(root);
		
		query.where(builder.or(builder.like(root.get("email"), "%" + keyword + "%"),
				builder.like(root.get("fullName"), "%" + keyword + "%"),
				builder.like(root.get("phone"), "%" + keyword + "%"),
				builder.like(root.get("address"), "%" + keyword + "%")));
		
		return getSession().createQuery(query).getResultList();
	}
	
}
