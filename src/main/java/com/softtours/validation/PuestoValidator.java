package com.softtours.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.softtours.domain.Puesto;

@Component
public class PuestoValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		 return Puesto.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Nombre", "Nombre", "El nombre es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Descripcion", "Descripcion", "La descripcion es obligatoria.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "SalarioMinimo", "SalarioMinimo", "El salario minimo es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "SalarioMaximo", "SalarioMaximo", "El salario maximo es obligatorio.");
		
		Puesto puesto = (Puesto) target;
		
		if(puesto.getSalarioMinimo().doubleValue() < 15000)
			errors.rejectValue("SalarioMinimo", "SalarioMinimo","El salario minimo es 15000.");
		
		if(puesto.getSalarioMaximo().doubleValue() < 15000)
			errors.rejectValue("SalarioMaximo", "SalarioMaximo","El salario minimo es 15000.");
		
		if(puesto.getSalarioMinimo().doubleValue() > puesto.getSalarioMaximo().doubleValue())
		errors.rejectValue("SalarioMinimo","SalarioMinimo", "El salario minimo no puede ser mayor que el maximo.");
	}

}
