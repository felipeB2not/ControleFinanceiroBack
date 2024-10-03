package com.example.financeiroApp.service;

import com.example.financeiroApp.models.Pessoa;
import com.example.financeiroApp.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa getPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElse(null);
    }

    public Pessoa savePessoa(Pessoa pessoa) {
        if (pessoaRepository.findByNome(pessoa.getNome()).isPresent()) {
            throw new IllegalArgumentException("Já existe uma pessoa com o nome: " + pessoa.getNome());
        }
        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        Optional<Pessoa> existingPessoa = pessoaRepository.findById(id);
        if (existingPessoa.isPresent()) {
            Pessoa pessoaToUpdate = existingPessoa.get();
            if (!pessoaToUpdate.getNome().equals(pessoa.getNome()) &&
                pessoaRepository.findByNome(pessoa.getNome()).isPresent()) {
                throw new IllegalArgumentException("Já existe uma pessoa com o nome: " + pessoa.getNome());
            }
            pessoaToUpdate.setNome(pessoa.getNome());
            pessoaToUpdate.setEmail(pessoa.getEmail());
            pessoaToUpdate.setCpf(pessoa.getCpf());
            pessoaToUpdate.setTelefone(pessoa.getTelefone());
            return pessoaRepository.save(pessoaToUpdate);
        } else {
            return null;
        }
    }

    public boolean deletePessoa(Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
