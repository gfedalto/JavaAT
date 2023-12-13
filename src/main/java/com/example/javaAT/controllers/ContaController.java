package com.example.javaAT.controllers;

import com.example.javaAT.models.ContaBancaria;
import com.example.javaAT.models.payloads.DeletePayload;
import com.example.javaAT.models.payloads.NotFoundPayload;
import com.example.javaAT.services.ContaBancariaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import com.example.javaAT.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/")
public class ContaController {
    Logger looger = LoggerFactory.getLogger(ContaController.class);

    @Autowired
    ContaBancariaService contaBancariaService;

    @GetMapping
    public ResponseEntity getAll(){

        List<ContaBancaria> contas = contaBancariaService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(contas);

        //return clienteService.getAll(size,page,sort,order);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            ContaBancaria conta = contaBancariaService.getById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente Inexistente"));
            return ResponseEntity.status(HttpStatus.OK).body(conta);
        } catch (ResourceNotFoundException ex) {
            NotFoundPayload notFoundPayload = new NotFoundPayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundPayload);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            ContaBancaria conta = contaBancariaService.deleteById(id);
            DeletePayload deletePayload = new DeletePayload(conta);
            return ResponseEntity.status(HttpStatus.OK).body(deletePayload);
        } catch (ResourceNotFoundException ex) {
            NotFoundPayload notFoundPayload = new NotFoundPayload(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundPayload);
        }
    }








}
