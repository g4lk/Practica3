package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto{
	
	
	private int id_producto;
	private String nombre;
	private String descripcion;
	public Producto() {
		
	}
	
	public Producto(String nombre, String descripcion){
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	//Id unico de cada lugar
	@Id	 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	
	//Nombre
	private void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	private String getNombre(){
		return this.nombre;
	}
	
	//Nombre
	private void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}
	
	private String getDescripcion(){
		return this.descripcion;
	}
}