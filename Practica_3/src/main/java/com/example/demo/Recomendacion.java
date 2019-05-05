package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="recomendacion")
public class Recomendacion {
    private int id;
    private int id_usuario;
    private float valoracion;
    private long id_producto;

    public Recomendacion(int id_usuario, float valoracion, long id_producto){
        this.id_usuario = id_usuario;
        this.valoracion = valoracion;
        this.id_producto = id_producto;
    }
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }


    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
