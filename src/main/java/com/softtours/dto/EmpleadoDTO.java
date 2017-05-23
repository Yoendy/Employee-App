package com.softtours.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.softtours.domain.*;

import com.softtours.domain.Empleado;

public class EmpleadoDTO {

	private int Id;
	private String Nombre;
	private String Apellido;
	private String NombreCompleto;
	private String Email;
	private String Direccion;
	private String Celular;
	private Date FechaIngreso;
	private Date FechaCumpleano;
	private int PuestoId;
	private String Puesto;
	private int DepartamentoId;
	private String Departamento;
	private BigDecimal Salario;
	private String Supervisor;
	private int SupervisorId;
	private Boolean Estado;
	private int Cantidad;
	
	public EmpleadoDTO(){}
	
	public EmpleadoDTO(String NombreCompleto, String Email, String Puesto, String Departamento, String Supervisor, double Salario)
	{
		this.NombreCompleto = NombreCompleto;
		this.Email = Email;
		this.Puesto = Puesto;
		this.Departamento = Departamento;
		this.Supervisor = Supervisor;
		this.Salario = new BigDecimal(Salario);
	}
	
	public EmpleadoDTO(Empleado empleado, Puesto puesto, Departamento departamento, Empleado supervisor){
		this.NombreCompleto = empleado.getNombre()+" "+ empleado.getApellido();
		this.Email = empleado.getEmail();
		this.Salario = empleado.getSalario();
		this.Puesto = puesto != null ? puesto.getNombre() : "N/A";
		this.Departamento = departamento != null ? departamento.getNombre() : "N/A";
		this.Supervisor = supervisor != null ? supervisor.getNombre()+" "+supervisor.getApellido(): "N/A";

	}
	
	public String getNombreCompleto() {
		return NombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}

	public String getPuesto() {
		return Puesto;
	}

	public void setPuesto(String puesto) {
		Puesto = puesto;
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public String getSupervisor() {
		return Supervisor;
	}

	public void setSupervisor(String supervisor) {
		Supervisor = supervisor;
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
	public int getSupervisorId() {
		return SupervisorId;
	}
	public void setSupervisorId(int supervisor) {
		SupervisorId = supervisor;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
}
