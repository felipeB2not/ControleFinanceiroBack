package com.example.financeiroApp.controller;

import com.example.financeiroApp.models.Grupo;
import com.example.financeiroApp.models.Lancamento;
import com.example.financeiroApp.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public List<Grupo> getAllGrupos() {
        return grupoService.getAllGrupos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> getGrupoById(@PathVariable Long id) {
        Grupo grupo = grupoService.getGrupoById(id);
        if (grupo != null) {
            return ResponseEntity.ok(grupo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Grupo> createGrupo(@RequestBody Grupo grupo) {
        Grupo createdGrupo = grupoService.saveGrupo(grupo);
        return ResponseEntity.status(201).body(createdGrupo); // Retorna 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> updateGrupo(@PathVariable Long id, @RequestBody Grupo grupo) {
        Grupo updatedGrupo = grupoService.updateGrupo(id, grupo);
        if (updatedGrupo != null) {
            return ResponseEntity.ok(updatedGrupo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable Long id) {
        boolean deleted = grupoService.deleteGrupo(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar um lançamento ao grupo
    @PostMapping("/{grupoId}/lancamentos")
    public ResponseEntity<Lancamento> addLancamentoToGrupo(@PathVariable Long grupoId, @RequestBody Lancamento lancamento) {
        Lancamento createdLancamento = grupoService.addLancamentoToGrupo(grupoId, lancamento);
        if (createdLancamento != null) {
            return ResponseEntity.status(201).body(createdLancamento); // Retorna 201 Created
        } else {
            return ResponseEntity.badRequest().build(); // Retorna 400 Bad Request
        }
    }

    // Listar lançamentos do grupo
    @GetMapping("/{grupoId}/lancamentos")
    public ResponseEntity<List<Lancamento>> getLancamentosByGrupo(@PathVariable Long grupoId) {
        List<Lancamento> lancamentos = grupoService.getLancamentosByGrupo(grupoId);
        if (lancamentos != null && !lancamentos.isEmpty()) {
            return ResponseEntity.ok(lancamentos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
