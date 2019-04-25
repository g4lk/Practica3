package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "lugar")
public class Lugar{
	
	
	private int id_lugar;
	private String capital;
	private String pais;
	private int num_habitantes;
	//private List<Compra> compra;
	public Lugar() {
		
	}
	
	public Lugar(String capital, String pais, int num_habitantes){
		this.capital = capital;
		this.pais = pais;
		this.num_habitantes = num_habitantes;
		//this.compra = new ArrayList<Compra>();
	}
	
	//Id unico de cada lugar
	@Id	 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_lugar() {
		return id_lugar;
	}

	public void setId_lugar(int id_lugar) {
		this.id_lugar = id_lugar;
	}
	
	//Capital
	public void setCapital(String capital){
		this.capital = capital;
	}
	
	private String getCapital(){
		return this.capital;
	}
	
	//Pais
	private void setPais(String pais){
		this.pais = pais;
	}
	
	private String getPais(){
		return this.pais;
	}
	
	//Capital
	private void setNum_habitantes(int num_habitantes){
		this.num_habitantes = num_habitantes;
	}
	
	private int getNum_habitantes(){
		return this.num_habitantes;
	}
	/*
	//Lista de todas las compras de un lugar
	@OneToMany (mappedBy = "lugar")
	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}*/
}