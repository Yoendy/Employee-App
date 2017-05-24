package com.Sistema_Empleados.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class Puesto {
	private int Id;
	private String Nombre;
	private String Descripcion;
	private BigDecimal SalarioMinimo = new BigDecimal(0);
	private BigDecimal SalarioMaximo = new BigDecimal(0);;
	private Boolean Estado = true;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public BigDecimal getSalarioMinimo() {
		return SalarioMinimo;
	}
	public void setSalarioMinimo(BigDecimal salarioMinimo) {
		SalarioMinimo = salarioMinimo;
	}
	public BigDecimal getSalarioMaximo() {
		return SalarioMaximo;
	}
	public void setSalarioMaximo(BigDecimal salarioMaximo) {
		SalarioMaximo = salarioMaximo;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	
}
