package com.example.financeiroApp.controller;

import com.example.financeiroApp.models.Lancamento;
import com.example.financeiroApp.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<Lancamento> getAllLancamentos() {
        return lancamentoService.getAllLancamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> getLancamentoById(@PathVariable Long id) {
        Lancamento lancamento = lancamentoService.getLancamentoById(id);
        if (lancamento != null) {
            return ResponseEntity.ok(lancamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Lancamento> createLancamento(@RequestBody LancamentoRequest lancamentoRequest) {
        Lancamento lancamento = new Lancamento();
        lancamento.setData(lancamentoRequest.getData());
        lancamento.setValor(lancamentoRequest.getValor());
        lancamento.setTipo(lancamentoRequest.getTipo());
        lancamento.setNome(lancamentoRequest.getNome());
        lancamento.setDescricao(lancamentoRequest.getDescricao());
        lancamento.setCategoria(lancamentoRequest.getCategoria());
        
        Lancamento createdLancamento = lancamentoService.saveLancamento(lancamento, lancamentoRequest.getGrupoId());
        return ResponseEntity.ok(createdLancamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> updateLancamento(@PathVariable Long id, @RequestBody Lancamento lancamento) {
        Lancamento updatedLancamento = lancamentoService.updateLancamento(id, lancamento);
        if (updatedLancamento != null) {
            return ResponseEntity.ok(updatedLancamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLancamento(@PathVariable Long id) {
        boolean deleted = lancamentoService.deleteLancamento(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

class LancamentoRequest {
    private LocalDate data;
    private BigDecimal valor;
    private String tipo;
    private String nome;
    private String descricao;
    private String categoria;
    private Long grupoId;
    
    // Getters e Setters
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

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }
}
