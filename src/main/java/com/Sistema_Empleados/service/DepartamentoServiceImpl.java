package com.Sistema_Empleados.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema_Empleados.domain.Departamento;
import com.Sistema_Empleados.dto.DepartamentoDTO;
import com.Sistema_Empleados.repository.DepartamentoRepository;
import com.Sistema_Empleados.repository.DepartamentoRepositoryImpl;
import com.Sistema_Empleados.util.DepartamentoWriter;
import com.Sistema_Empleados.util.PuestoWriter;


@Service
public class DepartamentoServiceImpl implements DepartamentoService {

	Departamento departamento;
	
	private DepartamentoRepository departamentoRepository;	

	public DepartamentoServiceImpl()
	{
		this.departamentoRepository = new DepartamentoRepositoryImpl();
	
	}
	
	@Override
	public List<Departamento> findAll() {

		return departamentoRepository.findAll();
	}

	@Override
	public void save(Departamento departamento) {
		
		departamentoRepository.save(departamento);
	}

	@Override
	public void update(Departamento departamento){

		departamentoRepository.update(departamento);
	}

	@Override
	public Departamento findOne(int id) {

		return departamentoRepository.findOne(id);
	}

	@Override
	public void delete(int id){

		departamentoRepository.delete(id);
	}

	@Override
	public boolean exist(int id) {
		return departamentoRepository.exist(id);
	}

	@Override
	public List<Departamento> searchAll(String filtro) {

		return departamentoRepository.searchAll(filtro);
	}
	
	private List<DepartamentoDTO> getAll() 
	{
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
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
