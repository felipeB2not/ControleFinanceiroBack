package com.example.financeiroApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private double valor;
    
    public int getId() {
    	return id;
    }

    public String getTipo() {
    	return tipo;
    }
    
    public double getValor() {
    	return valor;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }
    
    public void setValor(double valor) {
    	this.valor = valor;
    }
}
