package com.softtours.dto;

import java.math.BigDecimal;

public class PuestoDTO {
	private int Id;
	private String Nombre;
	private String Descripcion;
	private BigDecimal SalarioMinimo;
	private BigDecimal SalarioMaximo;
	private int CantidadEmpleado;
	
	public PuestoDTO(){}
	
	public PuestoDTO(String Nombre, int CantidadEmpleado)
	{
		this.Nombre = Nombre;
		this.CantidadEmpleado = CantidadEmpleado;
	}
	
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
	public int getCantidadEmpleado() {
		return CantidadEmpleado;
	}
	public void setCantidadEmpleado(int cantidadEmpleado) {
		CantidadEmpleado = cantidadEmpleado;
	}
	
	
}
