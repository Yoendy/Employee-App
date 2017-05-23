package com.softtours.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


public interface GenericService<T> {
	List<T> findAll();

	void save(T modelo);
	
	void update(T modelo);
	
	T findOne(int id);
	
	void delete(int id);
	
	boolean exist(int id);
	
	List<T> searchAll(String filtro);
	
	void export(HttpServletResponse response);
}
