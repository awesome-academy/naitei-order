package com.tmh.service.impl;

import com.tmh.dao.ProductDAO;
import com.tmh.dao.UserDAO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseServiceImpl {

	protected ProductDAO productDAO;
	
	protected UserDAO userDAO;
	
}
