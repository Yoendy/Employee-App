package com.Sistema_Empleados.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.Sistema_Empleados.repository.GenericRepository;

public class GenericServiceImpl<T> implements GenericService<T> {

	@Autowired
	private GenericRepository<T> repository;
	
	public GenericServiceImpl(GenericRepository<T> repository)
	{
		this.repository = repository;
	}
	
	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(T modelo) {
		repository.save(modelo);
		
	}

	@Override
	public void update(T modelo) {
		repository.update(modelo);
		
	}

	@Override
	public T findOne(int id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(int id) {
		repository.delete(id);
		
	}

	@Override
	public boolean exist(int id) {
		return repository.exist(id);
	}

	@Override
	public List<T> searchAll(String filtro) {
		return repository.searchAll(filtro);
	}

	@Override
	public void export(HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

}
