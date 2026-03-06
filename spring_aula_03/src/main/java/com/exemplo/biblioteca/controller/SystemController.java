package com.exemplo.biblioteca.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class SystemController {

    // ----------------------------
    // Parte 1 - Saudação
    // ----------------------------

    @GetMapping("/saudacao/{nome}")
    public String saudacao(@PathVariable String nome) {
        return "Olá, " + nome + "! Bem-vindo(a) ao Spring Boot!";
    }

    // ----------------------------
    // Parte 1 - Soma
    // ----------------------------

    @GetMapping("/soma/{numero1}/{numero2}")
    public int soma(@PathVariable int numero1, @PathVariable int numero2) {
        return numero1 + numero2;
    }

    // ----------------------------
    // Parte 2 - Informações do sistema
    // ----------------------------

    @GetMapping("/info")
    public Map<String, String> info() {

        Map<String, String> resposta = new HashMap<>();

        resposta.put("aplicacao", "API Spring Boot Aula 2");
        resposta.put("java", System.getProperty("java.version"));
        resposta.put("dataHora", LocalDateTime.now().toString());
        resposta.put("status", "Aplicação funcionando corretamente!");

        return resposta;
    }

    // ----------------------------
    // Parte 3 - Endpoint criativo
    // Gerador de frases motivacionais
    // ----------------------------

    @GetMapping("/motivacao")
    public String motivacao() {

        String[] frases = {
                "Continue aprendendo, você está no caminho certo!",
                "Pequenos progressos todos os dias geram grandes resultados.",
                "Programar é resolver problemas!",
                "Persistência é a chave do sucesso.",
                "Todo erro é uma oportunidade de aprender."
        };

        Random random = new Random();
        int indice = random.nextInt(frases.length);

        return frases[indice];
    }
}