package com.gerenciador.pessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gerenciador.pessoal.models.Contato;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByNomeContainingIgnoreCase(String nome);

    Optional<Contato> findByEmail(String email);
}