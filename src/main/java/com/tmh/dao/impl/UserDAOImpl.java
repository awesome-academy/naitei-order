package com.tmh.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.tmh.dao.GenericDAO;
import com.tmh.dao.UserDAO;
import com.tmh.entities.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
	
	public UserDAOImpl() {
		super(User.class);
	}
	
	public UserDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return getSession().createQuery("from User").getResultList();
	}
	
}
