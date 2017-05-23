package com.softtours.repository;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;


public interface GenericRepository<T> {

	List<T> findAll();

	T findOne(int id);

	void save(T modelo);

	void update(T modelo);

	void delete(T modelo);

	Boolean exist(int id);

	int count();
	
	void getData();
	
	void update(T departamento, int index);

	void generateData();
	
	List<T> searchAll(String filtro);

	void openWorkBook();

	void insert(T departamento, int index);

	boolean isRowEmpty(Row row);
	
	void delete(int id);
	
	List<T> findAll(int id);

	void closeWorkBook();

}