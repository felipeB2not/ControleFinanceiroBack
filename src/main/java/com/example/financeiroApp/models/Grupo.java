package com.example.financeiroApp.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lancamento> lancamentos = new ArrayList<>(); // Inicializa a lista

    public Grupo() {
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos; // Retorna a lista de lançamentos
    }

    // Métodos para manipular os lançamentos
    public void addLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
        lancamento.setGrupo(this); // Define o grupo no lançamento
    }

    public void removeLancamento(Lancamento lancamento) {
        lancamentos.remove(lancamento);
        lancamento.setGrupo(null); // Remove a referência ao grupo no lançamento
    }
}
