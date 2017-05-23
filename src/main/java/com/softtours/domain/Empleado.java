package com.softtours.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Empleado 
{

	private int Id;
	private String Nombre;
	private String Apellido;
	private String Email;
	private String Direccion;
	private String Celular;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date FechaIngreso;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date FechaCumpleano;
	private int PuestoId = 0;
	private int DepartamentoId = 0;
	private BigDecimal Salario = new BigDecimal(0);
	private int Supervisor= 0;
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
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	public Date getFechaIngreso() {
		return FechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		FechaIngreso = fechaIngreso;
	}
	public Date getFechaCumpleano() {
		return FechaCumpleano;
	}
	public void setFechaCumpleano(Date fechaCumpleano) {
		FechaCumpleano = fechaCumpleano;
	}
	public int getPuestoId() {
		return PuestoId;
	}
	public void setPuestoId(int puestoId) {
		PuestoId = puestoId;
	}
	public int getDepartamentoId() {
		return DepartamentoId;
	}
	public void setDepartamentoId(int departamentoId) {
		DepartamentoId = departamentoId;
	}
	public BigDecimal getSalario() {
		return Salario;
	}
	public void setSalario(BigDecimal salario) {
		Salario = salario;
	}
	public int getSupervisor() {
		return Supervisor;
	}
	public void setSupervisor(int supervisor) {
		Supervisor = supervisor;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
}
