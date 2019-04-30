package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lugar")
public class Lugar{
	
	
	private int id_lugar;
	private String capital;
	private String pais;
	private int num_habitantes;

	public Lugar() {
		
	}

	public Lugar(String capital, String pais){
		this.capital = capital;
		this.pais = pais;
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

	@Override
	public boolean equals(Object obj){

		// checking if both the object references are
		// referring to the same object.
		if(this == obj)
			return true;

		// it checks if the argument is of the
		// type Geek by comparing the classes
		// of the passed argument and this object.
		// if(!(obj instanceof Geek)) return false; ---> avoid.
		if(obj == null || obj.getClass()!= this.getClass())
			return false;

		// type casting of the argument.
		Lugar l = (Lugar) obj;

		// comparing the state of argument with
		// the state of 'this' Object.
		return (l.getCapital().equalsIgnoreCase(this.capital));
	}

	@Override
	public int hashCode() {
		return Objects.hash(capital);
	}
}