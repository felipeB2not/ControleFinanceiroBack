package com.example.financeiroApp.controller;

import com.example.financeiroApp.models.Lancamento;
import com.example.financeiroApp.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Lancamento createLancamento(@RequestBody Lancamento lancamento) {
        return lancamentoService.saveLancamento(lancamento);
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
