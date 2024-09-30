package com.example.financeiroApp.controller;

import com.example.financeiroApp.models.Grupo;
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
    public Grupo createGrupo(@RequestBody Grupo grupo) {
        return grupoService.saveGrupo(grupo);
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
}