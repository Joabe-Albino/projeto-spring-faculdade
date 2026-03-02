package com.exemplo.biblioteca.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Integer anoPublicacao;

    @ManyToOne
    private Autor autor;

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public Autor getAutor() { return autor; }

    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public void setAutor(Autor autor) { this.autor = autor; }
}