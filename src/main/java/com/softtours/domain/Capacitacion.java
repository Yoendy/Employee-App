package com.softtours.domain;

import java.util.Date;

public class Capacitacion {
	private int Id;
	private String Nombre;
	private String Descripcion;
	private Date FechaInicio;
	private Date FechaFinal;
	
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
	public Date getFechaInicio() {
		return FechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		FechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return FechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		FechaFinal = fechaFinal;
	}
	
	
}
