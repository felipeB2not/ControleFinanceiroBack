package com.example.financeiroApp.models;

import jakarta.persistence.*;



import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;  // Data do lançamento
    private BigDecimal valor;  // Valor do lançamento
    private String tipo;  // Tipo do lançamento: RECEITA ou DESPESA
    private String nome;  // Nome do lançamento
    private String descricao;  // Descrição detalhada do lançamento
    private String categoria;  // Categoria, como alimentação, transporte, etc.

    public Lancamento() {
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}