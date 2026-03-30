package com.gerenciador.pessoal.controllers;

import org.springframework.web.bind.annotation.*;

import com.gerenciador.pessoal.models.Contato;
import com.gerenciador.pessoal.services.ContatoService;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Contato> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Contato buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/pesquisa")
    public List<Contato> pesquisarPorNome(@RequestParam String nome) {
        return service.pesquisarPorNome(nome);
    }

    @PostMapping
    public Contato criar(@RequestBody Contato contato) {
        return service.criar(contato);
    }

    @PutMapping("/{id}")
    public Contato atualizar(@PathVariable Long id, @RequestBody Contato contato) {
        return service.atualizar(id, contato);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}