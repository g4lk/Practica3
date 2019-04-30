package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	private int id_cliente;
	private String nombre;
	private String apellido;
	private String correo;
	private String dominio;
	private int dia_alta;
	private int mes_alta;
	private int ano_alta;
	private List<Compra> compra;
	
	public Cliente() {
		
	}
	
	public Cliente(int id_cliente,String nombre, String apellido, String correo, String dominio, int dia_alta, int mes_alta, int ano_alta){
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.dominio = dominio;
		this.dia_alta = dia_alta;
		this.mes_alta = mes_alta;
		this.ano_alta = ano_alta;
		this.compra = new ArrayList<Compra>();
	}
	public Cliente(String nombre, String apellido, String correo, String dominio, int dia_alta, int mes_alta, int ano_alta){
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.dominio = dominio;
		this.dia_alta = dia_alta;
		this.mes_alta = mes_alta;
		this.ano_alta = ano_alta;
		this.compra = new ArrayList<Compra>();
	}
	
	//Nombre
	
	public String getNombre(){
		return this.nombre;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}	
	
	
	//Apellidos
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getApellido(){
		return this.apellido;
	}
	
	//Correo
	public void setCorreo(String correo){
		this.correo = correo;
	}	
	
	public String getCorreo(){
		return this.correo;
	}
	
	//Dominio
	public void setDominio(String dominio){
		this.dominio = dominio;
	}	
	
	public String getDominio(){
		return this.dominio;
	}
	
	//Dia de alta
	public void setDia_alta(int dia_alta){
		this.dia_alta = dia_alta;
	}	
	
	public int getDia_alta(){
		return this.dia_alta;
	}
	
	//Mes de alta
	public void setMes_alta(int mes_alta){
		this.mes_alta = mes_alta;
	}	
	
	public int getMes_alta(){
		return this.mes_alta;
	}
	
	//Año de alta
	public void setAno_alta(int ano_alta){
		this.ano_alta = ano_alta;
	}	
	
	public int getAno_alta(){
		return this.ano_alta;
	}
	
	public String cogerClave(){
	    return this.nombre+this.apellido+this.dia_alta+this.mes_alta+this.ano_alta;
    }

	//Lista de todas las compras de un usuario
	@OneToMany (mappedBy = "cliente")
	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return  dia_alta == cliente.dia_alta &&
				mes_alta == cliente.mes_alta &&
				ano_alta == cliente.ano_alta &&
				nombre.equals(cliente.nombre) &&
				apellido.equals(cliente.apellido);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, apellido, dia_alta, mes_alta, ano_alta);}

}