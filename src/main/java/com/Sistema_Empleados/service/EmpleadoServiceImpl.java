package com.Sistema_Empleados.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sistema_Empleados.domain.Departamento;
import com.Sistema_Empleados.domain.Empleado;
import com.Sistema_Empleados.domain.Puesto;
import com.Sistema_Empleados.dto.DepartamentoDTO;
import com.Sistema_Empleados.dto.EmpleadoDTO;
import com.Sistema_Empleados.dto.PuestoDTO;
import com.Sistema_Empleados.repository.DepartamentoRepositoryImpl;
import com.Sistema_Empleados.repository.EmpleadoRepository;
import com.Sistema_Empleados.repository.PuestoRepositoryImpl;
import com.Sistema_Empleados.util.EmpleadoWriter;

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado> implements EmpleadoService{
	Empleado empleado;
	

	private EmpleadoRepository empleadoRepository;
	private PuestoService puestoService;
	private DepartamentoService departamentoService;
	
	@Autowired
	public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository){
		super(empleadoRepository);
		this.empleadoRepository = empleadoRepository;
		this.departamentoService = new DepartamentoServiceImpl(new DepartamentoRepositoryImpl());
		this.puestoService = new PuestoServiceImpl(new PuestoRepositoryImpl());
		
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
