package com.Sistema_Empleados;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.Sistema_Empleados.domain.Departamento;
import com.Sistema_Empleados.domain.Puesto;
import com.Sistema_Empleados.service.DepartamentoServiceImpl;
import com.Sistema_Empleados.service.PuestoService;
import com.Sistema_Empleados.service.PuestoServiceImpl;

public class PuestoTest {

	@Test
	public void Guardar() 
	{
		PuestoService puestoService = new PuestoServiceImpl();
		
		
		Puesto puesto= new Puesto();
		
		puesto.setId(2);
		puesto.setNombre("Analista de Requerimiento");
		puesto.setDescripcion("Analista de Requerimiento");
		puesto.setSalarioMinimo(new BigDecimal(45000));
		puesto.setSalarioMaximo(new BigDecimal(95000));
		puesto.setEstado(true);
				
		puestoService.save(puesto);
	
	}
}
