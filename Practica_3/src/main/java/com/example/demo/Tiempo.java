package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tiempo")
public class Tiempo{
	
	
	private int id_tiempo;
	private int dia_semana;
	private int dia_mes;
	private String nombre_mes;
	private int numero_mes;
	private int ano;
	private boolean esFinSemana;
	//private List<Compra> compra;
	
	public Tiempo() {
		
	}
	
	public Tiempo(int dia_semana, int dia_mes, String nombre_mes, int numero_mes, int ano, boolean esFinSemana){
		this.dia_semana = dia_semana;
		this.dia_mes = dia_mes;
		this.nombre_mes = nombre_mes;
		this.numero_mes = numero_mes;
		this.ano = ano;
		this.esFinSemana = esFinSemana;
		//this.compra = new ArrayList<Compra>();
	}
	
	//Id unico de cada tiempo
	@Id	 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_tiempo() {
		return id_tiempo;
	}

	public void setDia_semana(int dia_semana) {
		this.dia_semana = dia_semana;
	}

	public void setId_tiempo(int id_tiempo) {
		this.id_tiempo = id_tiempo;
	}
	
	//Dia semana
	private void setNombre(int dia_semana){
		this.dia_semana = dia_semana;
	}
	
	private int getDia_semana(){
		return this.dia_semana;
	}
	
	//Dia mes
	private void setDia_mes(int dia_mes){
		this.dia_mes = dia_mes;
	}
	
	private int getDia_mes(){
		return this.dia_mes;
	}
	
	//Nombre mes
	private void setNombre_mes(String nombre_mes){
		this.nombre_mes = nombre_mes;
	}
	
	private String getNombre_mes(){
		return this.nombre_mes;
	}
	
	//Numero mes
	private void setNumero_mes(int numero_mes){
		this.numero_mes = numero_mes;
	}
	
	private int getNumero_mes(){
		return this.numero_mes;
	}
	
	//Año
	private void setAno(int ano){
		this.ano = ano;
	}
	
	private int getAno(){
		return this.ano;
	}
	
	//Es fin de semana
	private void setEsFinSemana(boolean esFinSemana){
		this.esFinSemana = esFinSemana;
	}
	
	private boolean getEsFinSemana(){
		return this.esFinSemana;
	}
	/*
	//Lista de todas las compras de un producto
	@OneToMany (mappedBy = "producto")
	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}*/
}