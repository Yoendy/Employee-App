package com.Sistema_Empleados.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema_Empleados.domain.Puesto;
import com.Sistema_Empleados.repository.GenericRepository;
import com.Sistema_Empleados.repository.PuestoRepositoryImpl;
import com.Sistema_Empleados.util.PuestoWriter;

@Service
public class PuestoServiceImpl extends GenericServiceImpl<Puesto> implements PuestoService {

	private static GenericRepository<Puesto> repository = new PuestoRepositoryImpl();
	
	public PuestoServiceImpl()
	{
		super(repository);
	}
	@Autowired
	public PuestoServiceImpl(GenericRepository<Puesto> repository) 
	{
		super(repository);
		
	}

	@Override
	public void export(HttpServletResponse response) {

			PuestoWriter puestoWriter = new PuestoWriter();
			puestoWriter.generateWord(response, findAll());
	}
	


}
