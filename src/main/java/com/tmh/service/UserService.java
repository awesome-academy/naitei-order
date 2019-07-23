package com.tmh.service;

import java.util.List;

import com.tmh.entities.User;

public interface UserService extends BaseService<Integer, User> {
	
	List<User> findAll();

}
