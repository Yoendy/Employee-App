package com.softtours;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.softtours.domain.Departamento;
import com.softtours.service.DepartamentoService;
import com.softtours.service.DepartamentoServiceImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartamentoServiceTest {

	private DepartamentoService departamentoService;
	

	@org.junit.Test
	public void Guardar() 
	{
		departamentoService = new DepartamentoServiceImpl();
		
		
		Departamento departamento = new Departamento();
		
		departamento.setId(2);
		departamento.setNombre("Contabilidad");
		departamento.setDescripcion("Contabilidad");
		departamento.setEncargadoId(0);
		
		departamentoService.save(departamento);
	
	}



}
