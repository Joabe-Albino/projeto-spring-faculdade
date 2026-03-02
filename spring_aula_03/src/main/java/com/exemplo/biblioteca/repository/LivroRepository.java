package com.exemplo.biblioteca.repository;

import com.exemplo.biblioteca.model.Livro;
import com.exemplo.biblioteca.dto.AutorResumoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    Page<Livro> findByAutorNomeContainingIgnoreCase(String autor, Pageable pageable);

    Page<Livro> findByAnoPublicacao(Integer ano, Pageable pageable);

    @Query("SELECT new com.exemplo.biblioteca.dto.AutorResumoDTO(a.nome, COUNT(l)) " +
           "FROM Livro l JOIN l.autor a GROUP BY a.nome")
    List<AutorResumoDTO> contarLivrosPorAutor();
}