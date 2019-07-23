package com.tmh.dao;

import java.util.List;

import com.tmh.entities.User;

public interface UserDAO extends BaseDAO<Integer, User> {
	
	// List<Product> findByKeyword(String keyword);
	
	List<User> findAll();
}
