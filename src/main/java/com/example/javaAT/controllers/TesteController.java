package com.example.javaAT.controllers;

import com.example.javaAT.models.Teste;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TesteController {
    Logger logger = LoggerFactory.getLogger(TesteController.class);
    @GetMapping("/hello")
    public List<Teste> helloWorld() {
        logger.info("Metodo Hello");
        return List.of(new Teste("Geovanni", 37), new Teste("Bernardo", 12));
    }
    @GetMapping("/hello2")
    public String hello2 (@RequestParam(required = false, defaultValue = "oi") String nome){
        nome = nome.toUpperCase();
        return nome;
    }
    @GetMapping("/hello3")
    public String hello3(@RequestParam String nome, @RequestParam double size){
        logger.info("Nome: " + nome);
        logger.info("size: " + size);
        size = Math.pow(size,2);
        return nome + " " + size;
    }

    @GetMapping("/variosParams")
    public void varios(@RequestParam Map<String,String> allParams){
        logger.info("Params: " + allParams.toString());
        boolean param1 = allParams.containsKey("param1");
    }
}
