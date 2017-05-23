package com.softtours.repository;



import com.softtours.domain.Empleado;


public interface EmpleadoRepository extends GenericRepository<Empleado>{

		int countAllByDepartamento(int id);
		
		int countAllByPuesto(int id);
}
