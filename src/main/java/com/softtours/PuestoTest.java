package com.softtours;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.softtours.domain.Departamento;
import com.softtours.domain.Puesto;
import com.softtours.service.DepartamentoServiceImpl;
import com.softtours.service.PuestoService;
import com.softtours.service.PuestoServiceImpl;

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
