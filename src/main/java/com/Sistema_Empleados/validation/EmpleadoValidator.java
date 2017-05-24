package com.Sistema_Empleados.validation;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.Sistema_Empleados.domain.Empleado;
import com.Sistema_Empleados.domain.Puesto;
import com.Sistema_Empleados.service.PuestoService;
import com.Sistema_Empleados.service.PuestoServiceImpl;

@Component
public class EmpleadoValidator  implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Empleado.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Nombre", "Nombre", "El nombre es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Apellido", "Apellido", "El apellido es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Email", "Email", "El email es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Direccion", "Direccion", "La direccion es obligatoria.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Celular", "Celular", "El celular es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "FechaIngreso", "FechaIngreso", "La fecha ingreso es obligatoria.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "FechaCumpleano", "FechaCumpleano", "La fecha de nacimiento es obligatoria.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PuestoId", "PuestoId", "El puesto es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "DepartamentoId", "DepartamentoId", "El departamento es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Salario", "Salario", "El salario es obligatorio.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Supervisor", "Supervisor", "El supervisor es obligatorio.");
		
		Empleado empleado = (Empleado) target;
		
		//Edad
		
		if(empleado.getFechaIngreso() != null && empleado.getFechaCumpleano() != null)
		{
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			int age = (Integer.parseInt(formatter.format(empleado.getFechaIngreso()))- Integer.parseInt(formatter.format(empleado.getFechaCumpleano())))/10000;
			
			if(age <18)
				errors.rejectValue("FechaCumpleano", "FechaCumpleano","No aceptamos menores de edad.");

		}

		if(empleado.getEmail() != null)
		{
			if(!isValidEmailAddress(empleado.getEmail()))
				errors.rejectValue("Email", "Email","No es un correo electronico valido.");
		}

		if(empleado.getPuestoId() != 0 && empleado.getSalario().doubleValue() != 0)
		{
			if(!isValidSalario(empleado.getPuestoId(), empleado.getSalario()))
				errors.rejectValue("Salario", "Salario","El salario no esta en el rango de la posicion.");
		}

		if(empleado.getPuestoId() == 0)
			errors.rejectValue("PuestoId", "PuestoId", "El puesto es obligatorio.");

		if(empleado.getDepartamentoId() == 0)
			errors.rejectValue("DepartamentoId", "DepartamentoId", "El departamento es obligatorio.");
		
		if(empleado.getSalario().doubleValue() == 0)
			errors.rejectValue("Salario", "Salario", "El salario no puede ser 0.");

		if(empleado.getSalario().doubleValue() < 0)
			errors.rejectValue("Salario", "Salario", "El salario no puede ser negativo.");
		
		if(empleado.getSupervisor() == 0)
			errors.rejectValue("Supervisor", "Supervisor", "El supervisor es obligatorio.");
		    
	}
	
    public boolean isValidEmailAddress(String email) 
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public boolean isValidSalario(int puestoId, BigDecimal salario) 
    {
        PuestoService puestoService = new PuestoServiceImpl();
        Puesto puesto = puestoService.findOne(puestoId);
        return puesto.getSalarioMinimo().doubleValue()<=salario.doubleValue() && puesto.getSalarioMaximo().doubleValue()>=salario.doubleValue();
    }

}
