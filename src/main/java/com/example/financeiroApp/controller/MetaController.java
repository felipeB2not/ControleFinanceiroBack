package com.example.financeiroApp.controller;

import com.example.financeiroApp.models.Meta;
import com.example.financeiroApp.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService metaService;

    @GetMapping
    public List<Meta> getAllMetas() {
        return metaService.getAllMetas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meta> getMetaById(@PathVariable int id) {
        Meta meta = metaService.getMetaById(id);
        if (meta != null) {
            return ResponseEntity.ok(meta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Meta createMeta(@RequestBody Meta meta) {
        return metaService.saveMeta(meta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meta> updateMeta(@PathVariable int id, @RequestBody Meta meta) {
        Meta updatedMeta = metaService.updateMeta(id, meta);
        if (updatedMeta != null) {
            return ResponseEntity.ok(updatedMeta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeta(@PathVariable int id) {
        boolean deleted = metaService.deleteMeta(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}