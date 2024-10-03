package com.example.financeiroApp.service;

import com.example.financeiroApp.models.Meta;
import com.example.financeiroApp.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {

    @Autowired
    private MetaRepository metaRepository;

    public List<Meta> getAllMetas() {
        return metaRepository.findAll();
    }

    public Meta getMetaById(int id) {
        Optional<Meta> meta = metaRepository.findById(id);
        return meta.orElse(null);
    }

    public Meta saveMeta(Meta meta) {
        validateMeta(meta); 
        return metaRepository.save(meta);
    }

    public Meta updateMeta(int id, Meta meta) {
        Optional<Meta> existingMeta = metaRepository.findById(id);
        if (existingMeta.isPresent()) {
            Meta metaToUpdate = existingMeta.get();
            validateMeta(meta);
            metaToUpdate.setDescricao(meta.getDescricao());
            metaToUpdate.setValor(meta.getValor());
            return metaRepository.save(metaToUpdate);
        } else {
            return null;
        }
    }

    public boolean deleteMeta(int id) {
        if (metaRepository.existsById(id)) {
            metaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void validateMeta(Meta meta) {
        if (meta.getDescricao() == null || meta.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
        }
        if (meta.getValor() < 0) {
            throw new IllegalArgumentException("O valor deve ser um número positivo.");
        }
    }
}
