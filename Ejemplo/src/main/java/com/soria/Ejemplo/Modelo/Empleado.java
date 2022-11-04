package com.soria.Ejemplo.Modelo;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="empleado")
public class Empleado {
	@Id
	//GeneratedValue(strategy=GenerationType.IDENTITY) //Autoincremental
	private int id;
	@Column(name="nombre",nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="empleado", targetEntity=Venta.class)
	private Set<Venta> ventas;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="emp", targetEntity= EmpleadoDetalles.class)
	private EmpleadoDetalles empleadoDetalles;
	
	public EmpleadoDetalles getEmpleadoDetalles() {
		return empleadoDetalles;
	}
	public void setEmpleadoDetalles(EmpleadoDetalles empleadoDetalles) {
		this.empleadoDetalles = empleadoDetalles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Set<Venta> getVentas() {
		return ventas;
	}
	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}
	
}
