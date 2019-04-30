package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "compra")
public class Compra{
	
	private int id_compra;
	private int importe;
	private int valoracion;
	private Cliente cliente;
	
	public Compra() {
		
	}
	
	public Compra(int importe, int valoracion, Cliente cliente){
		this.importe = importe;
		this.valoracion = valoracion;
		this.cliente = cliente;
	}
	
	//Id unico de cada compra
	@Id	 
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId_compra() {
		return id_compra;
	}

	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	
	//Importe
	private void setImporte(int importe){
		this.importe = importe;
	}
	
	private int getImporte(){
		return this.importe;
	}
	
	//Valoracion
	private void setValoracion(int valoracion){
		this.valoracion = valoracion;
	}
	
	private int getValoracion(){
		return this.valoracion;
	}
	

	//Cliente
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_cliente")
	public Cliente getCliente(){
		return this.cliente;
	}
	
	public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}
}