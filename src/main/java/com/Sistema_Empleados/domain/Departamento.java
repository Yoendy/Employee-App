package com.Sistema_Empleados.domain;

public class Departamento {
	private int Id;
	private String Nombre;
	private String Descripcion;
	private int EncargadoId = 0;
	private Boolean Estado = true;
	
	public Departamento()
	{
		
	}
	
	public Departamento(int id, String nombre, String descripcion){
		this.Id = id;
		this.Nombre = nombre;
		this.Descripcion = descripcion;
		this.EncargadoId = 0;
	}
	
	public Departamento(int id, String nombre, String descripcion, int idEncargado){
		this.Id = id;
		this.Nombre = nombre;
		this.Descripcion = descripcion;
		this.EncargadoId = idEncargado;
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

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}
}
