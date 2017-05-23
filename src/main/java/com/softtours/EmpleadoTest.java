package com.softtours;

import org.junit.Test;

import com.softtours.domain.Empleado;
import com.softtours.service.EmpleadoService;
import com.softtours.service.EmpleadoServiceImpl;

public class EmpleadoTest 
{
	
	private EmpleadoService empleadoService;

	@Test
	public void Modificar() 
	{
		empleadoService = new EmpleadoServiceImpl();
		
		Empleado empleado = empleadoService.findOne(2);
		empleado.setNombre("Ramon");
		
		empleadoService.update(empleado);
		
	}

}
