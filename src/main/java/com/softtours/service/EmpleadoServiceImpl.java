package com.softtours.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtours.domain.Departamento;
import com.softtours.domain.Empleado;
import com.softtours.domain.Puesto;
import com.softtours.dto.DepartamentoDTO;
import com.softtours.dto.EmpleadoDTO;
import com.softtours.dto.PuestoDTO;
import com.softtours.repository.EmpleadoRepository;
import com.softtours.repository.EmpleadoRepositoryImpl;
import com.softtours.util.DepartamentoWriter;
import com.softtours.util.EmpleadoWriter;

import scala.Console;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	Empleado empleado;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	private PuestoService puestoService;
	private DepartamentoService departamentoService;
	
	public EmpleadoServiceImpl(){
		this.empleadoRepository = new EmpleadoRepositoryImpl();
		this.departamentoService = new DepartamentoServiceImpl();
		this.puestoService = new PuestoServiceImpl();
		
	}

	@Override
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}

	@Override
	public void save(Empleado puesto) {
		empleadoRepository.save(puesto);
	}

	@Override
	public void update(Empleado puesto) {
		empleadoRepository.update(puesto);
		
	}

	@Override
	public Empleado findOne(int id) {
		return empleadoRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		empleadoRepository.delete(id);
		
	}

	@Override
	public boolean exist(int id) {
		return empleadoRepository.exist(id);
	}

	@Override
	public List<Empleado> searchAll(String filtro) {
		return empleadoRepository.searchAll(filtro);
	}

	@Override
	public List<DepartamentoDTO> findAllEmpleadosByDepartamento() {

		List<Departamento> departamentos = new ArrayList<>();
		List<DepartamentoDTO> departamentosDTO = new ArrayList<>();
		try
		{
			departamentos =	departamentoService.findAll().stream()
							.filter(x -> x.getEstado())
							.collect(Collectors.toList());
			departamentosDTO = departamentos.stream()
							   .map(x -> new DepartamentoDTO(x.getNombre(), empleadoRepository.countAllByDepartamento(x.getId())))
							   .collect(Collectors.toList());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return departamentosDTO;
	}

	@Override
	public List<PuestoDTO> findAllEmpleadosByPuesto() {

		List<Puesto> puestos= new ArrayList<>();
		List<PuestoDTO> puestosDTO = new ArrayList<>();
		try
		{
			puestos =	puestoService.findAll().stream()
						.filter(x -> x.getEstado())
						.collect(Collectors.toList());
			
			puestosDTO = puestos.stream()
							   .map(x -> new PuestoDTO(x.getNombre(), empleadoRepository.countAllByPuesto(x.getId())))
							   .collect(Collectors.toList());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return puestosDTO;
	}
	
	private List<EmpleadoDTO> getAll()
	{
		List<EmpleadoDTO> empleados = findAll().stream()
									  .map(x -> new EmpleadoDTO(x, puestoService.findOne(x.getPuestoId()), departamentoService.findOne(x.getDepartamentoId()), findOne(x.getId())))
									  .collect(Collectors.toList());
		return empleados;
	}

	@Override
	public void export(HttpServletResponse response) 
	{
		EmpleadoWriter writer = new EmpleadoWriter();
		writer.generateWord(response, getAll());
		
	}

}
