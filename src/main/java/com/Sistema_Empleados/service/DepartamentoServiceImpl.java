package com.Sistema_Empleados.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema_Empleados.domain.Departamento;
import com.Sistema_Empleados.domain.Puesto;
import com.Sistema_Empleados.dto.DepartamentoDTO;
import com.Sistema_Empleados.repository.DepartamentoRepository;
import com.Sistema_Empleados.repository.DepartamentoRepositoryImpl;
import com.Sistema_Empleados.repository.EmpleadoRepositoryImpl;
import com.Sistema_Empleados.repository.GenericRepository;
import com.Sistema_Empleados.repository.PuestoRepositoryImpl;
import com.Sistema_Empleados.util.DepartamentoWriter;
import com.Sistema_Empleados.util.PuestoWriter;


@Service
public class DepartamentoServiceImpl extends GenericServiceImpl<Departamento> implements DepartamentoService {

	Departamento departamento;
	
	
	private GenericRepository<Departamento> departamentoRepository;


	@Autowired
	public DepartamentoServiceImpl(GenericRepository<Departamento> departamentoRepository)
	{
		super(departamentoRepository);
		this.departamentoRepository = departamentoRepository;
	}
	
	
	private List<DepartamentoDTO> getAll() 
	{
		EmpleadoService empleadoService = new EmpleadoServiceImpl(new EmpleadoRepositoryImpl());
		return departamentoRepository.findAll().stream()
			   .map(x -> new DepartamentoDTO(x.getNombre(), x.getDescripcion(), empleadoService.exist(x.getEncargadoId()) ? empleadoService.findOne(x.getEncargadoId()) : null))
			   .collect(Collectors.toList());
	}

	@Override
	public void export(HttpServletResponse response) {

		DepartamentoWriter writer = new DepartamentoWriter();
		writer.generateWord(response, getAll());
		
	}

}
