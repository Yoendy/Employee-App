package com.Sistema_Empleados;

import org.junit.Test;

import com.Sistema_Empleados.domain.Empleado;
import com.Sistema_Empleados.service.EmpleadoService;
import com.Sistema_Empleados.service.EmpleadoServiceImpl;

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
