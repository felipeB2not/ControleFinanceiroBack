package com.example.financeiroApp.repository;

import com.example.financeiroApp.models.Pessoa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	Optional<Pessoa> findByNome(String nome);
}
