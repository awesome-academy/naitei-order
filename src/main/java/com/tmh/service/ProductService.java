package com.tmh.service;

import java.util.List;

import com.tmh.entities.Product;

public interface ProductService extends BaseService<Integer, Product>{

	List<Product> findAll();
	
//	boolean deleteById(int id);
	
}
