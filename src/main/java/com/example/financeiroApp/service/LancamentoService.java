package com.example.financeiroApp.service;

import com.example.financeiroApp.models.Lancamento;
import com.example.financeiroApp.models.Grupo; 
import com.example.financeiroApp.repository.LancamentoRepository;
import com.example.financeiroApp.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    public List<Lancamento> getAllLancamentos() {
        return lancamentoRepository.findAll();
    }

    public Lancamento getLancamentoById(Long id) {
        Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
        return lancamento.orElse(null);
    }

    public Lancamento saveLancamento(Lancamento lancamento, Long grupoId) {
        Optional<Grupo> grupo = grupoRepository.findById(grupoId);
        if (grupo.isPresent()) {
            lancamento.setGrupo(grupo.get());
            return lancamentoRepository.save(lancamento);
        } else {
            throw new IllegalArgumentException("Grupo não encontrado com ID: " + grupoId);
        }
    }

    public Lancamento updateLancamento(Long id, Lancamento lancamento) {
        Optional<Lancamento> existingLancamento = lancamentoRepository.findById(id);
        if (existingLancamento.isPresent()) {
            Lancamento lancamentoToUpdate = existingLancamento.get();
            lancamentoToUpdate.setData(lancamento.getData());
            lancamentoToUpdate.setValor(lancamento.getValor());
            lancamentoToUpdate.setTipo(lancamento.getTipo());
            lancamentoToUpdate.setNome(lancamento.getNome());
            lancamentoToUpdate.setDescricao(lancamento.getDescricao());
            lancamentoToUpdate.setCategoria(lancamento.getCategoria());
            return lancamentoRepository.save(lancamentoToUpdate);
        } else {
            return null;
        }
    }

    public boolean deleteLancamento(Long id) {
        if (lancamentoRepository.existsById(id)) {
            lancamentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
