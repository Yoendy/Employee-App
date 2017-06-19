package com.Sistema_Empleados.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.Sistema_Empleados.domain.Departamento;

@Component
public class DepartamentoValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) 
	{
		return Departamento.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Nombre", "Nombre", "El nombre es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Descripcion", "Descripcion", "La descripcion es obligatoria.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "EncargadoId", "EncargadoId", "El Encargado es obligatorio.");
		
		Departamento departamento = (Departamento) target;
		
		if(departamento.getEncargadoId() == 0)
			errors.rejectValue("EncargadoId", "EncargadoId","El encargado es obligatorio.");
	}

}
