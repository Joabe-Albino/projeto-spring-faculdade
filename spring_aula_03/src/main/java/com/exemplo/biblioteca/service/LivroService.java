package com.exemplo.biblioteca.service;

import com.exemplo.biblioteca.model.Livro;
import com.exemplo.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Optional<Livro> atualizar(Long id, Livro novoLivro) {
        return repository.findById(id).map(livro -> {
            livro.setTitulo(novoLivro.getTitulo());
            livro.setAnoPublicacao(novoLivro.getAnoPublicacao());
            livro.setAutor(novoLivro.getAutor());
            return repository.save(livro);
        });
    }
}