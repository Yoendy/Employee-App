package com.Sistema_Empleados.repository;



import com.Sistema_Empleados.domain.Empleado;


public interface EmpleadoRepository extends GenericRepository<Empleado>{

		int countAllByDepartamento(int id);
		
		int countAllByPuesto(int id);
}
