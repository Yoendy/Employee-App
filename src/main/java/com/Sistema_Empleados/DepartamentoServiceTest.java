package com.Sistema_Empleados;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.Sistema_Empleados.domain.Departamento;
import com.Sistema_Empleados.repository.DepartamentoRepositoryImpl;
import com.Sistema_Empleados.service.DepartamentoService;
import com.Sistema_Empleados.service.DepartamentoServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartamentoServiceTest {

	private DepartamentoService departamentoService;
	

	@org.junit.Test
	public void Guardar() 
	{
		departamentoService = new DepartamentoServiceImpl(new DepartamentoRepositoryImpl());
		
		
		Departamento departamento = new Departamento();
		
		departamento.setId(2);
		departamento.setNombre("Contabilidad");
		departamento.setDescripcion("Contabilidad");
		departamento.setEncargadoId(0);
		
		departamentoService.save(departamento);
	
	}



}
