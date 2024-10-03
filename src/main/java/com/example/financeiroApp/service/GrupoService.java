package com.example.financeiroApp.service;

import com.example.financeiroApp.models.Grupo;
import com.example.financeiroApp.models.Lancamento;
import com.example.financeiroApp.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Grupo> getAllGrupos() {
        return grupoRepository.findAll();
    }

    public Grupo getGrupoById(Long id) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        return grupo.orElse(null);
    }

    public Grupo saveGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo updateGrupo(Long id, Grupo grupo) {
        Optional<Grupo> existingGrupo = grupoRepository.findById(id);
        if (existingGrupo.isPresent()) {
            Grupo grupoToUpdate = existingGrupo.get();
            grupoToUpdate.setNome(grupo.getNome());
            grupoToUpdate.setDescricao(grupo.getDescricao());
            return grupoRepository.save(grupoToUpdate);
        } else {
            return null;
        }
    }

    public boolean deleteGrupo(Long id) {
        if (grupoRepository.existsById(id)) {
            grupoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Lancamento addLancamentoToGrupo(Long grupoId, Lancamento lancamento) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(grupoId);
        if (grupoOptional.isPresent()) {
            Grupo grupo = grupoOptional.get();
            
            grupo.addLancamento(lancamento); 
            grupoRepository.save(grupo);
            return lancamento;
        }
        return null;
    }

    public List<Lancamento> getLancamentosByGrupo(Long grupoId) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(grupoId);
        if (grupoOptional.isPresent()) {
            Grupo grupo = grupoOptional.get();
            return grupo.getLancamentos(); 
            }
        return null;
    }

    public void removeLancamentoFromGrupo(Long grupoId, Lancamento lancamento) {
        Optional<Grupo> grupoOptional = grupoRepository.findById(grupoId);
        if (grupoOptional.isPresent()) {
            Grupo grupo = grupoOptional.get();
            grupo.removeLancamento(lancamento); 
            grupoRepository.save(grupo);
        }
    }
}
