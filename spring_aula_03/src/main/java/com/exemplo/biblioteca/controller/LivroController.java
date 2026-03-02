package com.exemplo.biblioteca.controller;

import com.exemplo.biblioteca.model.Livro;
import com.exemplo.biblioteca.dto.AutorResumoDTO;
import com.exemplo.biblioteca.repository.LivroRepository;
import com.exemplo.biblioteca.service.LivroService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository repository;
    private final LivroService service;

    public LivroController(LivroRepository repository, LivroService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public Page<Livro> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        return service.atualizar(id, livro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public Page<Livro> buscar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) Integer ano,
            Pageable pageable) {

        if (titulo != null)
            return repository.findByTituloContainingIgnoreCase(titulo, pageable);

        if (autor != null)
            return repository.findByAutorNomeContainingIgnoreCase(autor, pageable);

        if (ano != null)
            return repository.findByAnoPublicacao(ano, pageable);

        return repository.findAll(pageable);
    }

    @GetMapping("/estatisticas/livros-por-autor")
    public List<AutorResumoDTO> livrosPorAutor() {
        return repository.contarLivrosPorAutor();
    }
}