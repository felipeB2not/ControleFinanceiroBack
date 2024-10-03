package com.example.financeiroApp.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    @OneToMany(mappedBy = "pessoa") // mapeando pela propriedade "pessoa" em Grupo
    private List<Grupo> grupos; // Adicionando a lista de grupos

    // Construtor

    public Pessoa() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Grupo> getGrupos() {
        return grupos; // Getter para a lista de grupos
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos; // Setter para a lista de grupos
    }
}
