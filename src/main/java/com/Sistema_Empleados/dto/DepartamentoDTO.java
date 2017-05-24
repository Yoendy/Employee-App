package com.Sistema_Empleados.dto;

import com.Sistema_Empleados.domain.Empleado;

public class DepartamentoDTO {
	private int Id;
	private String Nombre;
	private String Descripcion;
	private int EncargadoId;
	private int CantidadEmpleado;
	private String Encargado;
	
	public DepartamentoDTO(){}
	
	public DepartamentoDTO( String Nombre, int CantidadEmpleado)
	{
		this.Nombre = Nombre;
		this.CantidadEmpleado = CantidadEmpleado;
	}
	
	public DepartamentoDTO( String Nombre, String Descripcion, Empleado Encargado)
	{
		String encargado = Encargado.equals(null) ? "N/A" : Encargado.getNombre() +""+ Encargado.getApellido();
		this.Nombre = Nombre;
		this.Descripcion = Descripcion;
		this.setEncargado(encargado);
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
	public int getEncargadoId() {
		return EncargadoId;
	}
	public void setEncargadoId(int encargadoId) {
		EncargadoId = encargadoId;
	}
	public int getCantidadEmpleado() {
		return CantidadEmpleado;
	}
	public void setCantidadEmpleado(int cantidadEmpleado) {
		CantidadEmpleado = cantidadEmpleado;
	}

	public String getEncargado() {
		return Encargado;
	}

	public void setEncargado(String encargado) {
		Encargado = encargado;
	}
	
	
}
