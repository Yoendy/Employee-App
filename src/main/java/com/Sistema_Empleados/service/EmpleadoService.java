package com.Sistema_Empleados.service;

import java.util.List;

import com.Sistema_Empleados.domain.Empleado;
import com.Sistema_Empleados.dto.DepartamentoDTO;
import com.Sistema_Empleados.dto.PuestoDTO;

public interface EmpleadoService extends GenericService<Empleado>{
	List<DepartamentoDTO> findAllEmpleadosByDepartamento();
	List<PuestoDTO> findAllEmpleadosByPuesto();
}
