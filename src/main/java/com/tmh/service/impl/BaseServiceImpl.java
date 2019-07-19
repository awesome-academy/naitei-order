package com.tmh.service.impl;

import com.tmh.dao.ProductDAO;

public class BaseServiceImpl {

	protected ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
}
