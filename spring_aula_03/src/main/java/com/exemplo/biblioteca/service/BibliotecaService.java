package com.exemplo.biblioteca.service;

import com.exemplo.biblioteca.model.Livro;
import com.exemplo.biblioteca.repository.BibliotecaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BibliotecaService {

    private final BibliotecaRepository bibliotecaRepository;

    public BibliotecaService(BibliotecaRepository bibliotecaRepository) {
        this.bibliotecaRepository = bibliotecaRepository;
    }

    public Livro cadastrarLivro(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new ResponseStatusException(BAD_REQUEST, "Título do livro não pode ser vazio");
        }
        if (livro.getAnoPublicacao() == null || livro.getAnoPublicacao() <= 1500) {
            throw new ResponseStatusException(BAD_REQUEST, "Ano de publicação deve ser maior que 1500");
        }
        if (livro.getDisponivel() == null) {
            livro.setDisponivel(true);
        }
        return bibliotecaRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return bibliotecaRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return bibliotecaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Livro não encontrado"));
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new ResponseStatusException(BAD_REQUEST, "Parâmetro título é obrigatório");
        }
        return bibliotecaRepository.findByTitulo(titulo);
    }

    public void removerLivro(Long id) {
        buscarPorId(id);
        bibliotecaRepository.deleteById(id);
    }
}
