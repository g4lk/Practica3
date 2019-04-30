package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tiempo")
public class Tiempo{



	private int id_tiempo;
	private String dia_semana;
	private int dia_mes;
	private String nombre_mes;
	private int numero_mes;
	private int ano;
	private boolean esFinSemana;

	public Tiempo() {
		
	}
	
	public Tiempo(String dia_semana, int dia_mes, String nombre_mes, int numero_mes, int ano, boolean esFinSemana){
		this.dia_semana = dia_semana;
		this.dia_mes = dia_mes;
		this.nombre_mes = nombre_mes;
		this.numero_mes = numero_mes;
		this.ano = ano;
		this.esFinSemana = esFinSemana;
	}
	
	//Id unico de cada tiempo
	@Id	 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_tiempo() {
		return id_tiempo;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public void setId_tiempo(int id_tiempo) {
		this.id_tiempo = id_tiempo;
	}
	
	//Dia semana
	private void setNombre(String dia_semana){
		this.dia_semana = dia_semana;
	}
	
	private String getDia_semana(){
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tiempo tiempo = (Tiempo) o;
		return dia_mes == tiempo.dia_mes &&
				numero_mes == tiempo.numero_mes &&
				ano == tiempo.ano &&
				esFinSemana == tiempo.esFinSemana &&
				dia_semana.equals(tiempo.dia_semana) &&
				nombre_mes.equals(tiempo.nombre_mes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dia_semana, dia_mes, nombre_mes, numero_mes, ano);
	}
}