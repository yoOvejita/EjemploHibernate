package com.soria.Ejemplo.Modelo;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private Set ventas;
	
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
	public Set getVentas() {
		return ventas;
	}
	public void setVentas(Set ventas) {
		this.ventas = ventas;
	}
	
}
