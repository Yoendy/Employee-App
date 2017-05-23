package com.softtours.service;

import java.util.List;

import com.softtours.domain.Empleado;
import com.softtours.dto.DepartamentoDTO;
import com.softtours.dto.PuestoDTO;

public interface EmpleadoService extends GenericService<Empleado>{
	List<DepartamentoDTO> findAllEmpleadosByDepartamento();
	List<PuestoDTO> findAllEmpleadosByPuesto();
}
