package com.gerenciador.pessoal.services;

import org.springframework.stereotype.Service;

import com.gerenciador.pessoal.Exceptions.ResourceNotFoundException;
import com.gerenciador.pessoal.models.Contato;
import com.gerenciador.pessoal.repository.ContatoRepository;

import java.util.List;

@Service
public class ContatoService {

    private final com.gerenciador.pessoal.repository.ContatoRepository repository;

    public ContatoService(ContatoRepository repository) {
        this.repository = repository;
    }

    public List<Contato> listarTodos() {
        return repository.findAll();
    }

    public Contato buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com ID: " + id));
    }

    public Contato criar(Contato contato) {
        repository.findByEmail(contato.getEmail())
                .ifPresent(c -> {
                    throw new RuntimeException("Email já cadastrado!");
                });

        return repository.save(contato);
    }

    public Contato atualizar(Long id, Contato contatoAtualizado) {
        Contato contato = buscarPorId(id);

        contato.setNome(contatoAtualizado.getNome());
        contato.setEmail(contatoAtualizado.getEmail());
        contato.setTelefone(contatoAtualizado.getTelefone());

        return repository.save(contato);
    }

    public void deletar(Long id) {
        Contato contato = buscarPorId(id);
        repository.delete(contato);
    }

    public List<Contato> pesquisarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}