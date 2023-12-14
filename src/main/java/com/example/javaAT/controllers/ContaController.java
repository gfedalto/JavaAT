package com.example.javaAT.controllers;

import com.example.javaAT.models.ContaBancaria;
import com.example.javaAT.models.ContaBancariaApi;
import com.example.javaAT.models.payloads.*;
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
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "real") String moeda,
                                 @RequestParam(required = false, defaultValue = "") String sort,
                                 @RequestParam(required = false, defaultValue = "") List<Integer> agencia
                                 ) {

        List<ContaBancaria> contas = contaBancariaService.getAll(sort, agencia);
        if (moeda.equals("dolar")) {
            List<DolarPayload> contasDolarPayload = contas.stream().map(DolarPayload::new).toList();
            return ResponseEntity.status(HttpStatus.OK).body(contasDolarPayload);
        } else {
            List<ContaPayload> contasPayload = contas.stream().map(ContaPayload::new).toList();
            return ResponseEntity.status(HttpStatus.OK).body(contasPayload);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            ContaBancaria conta = contaBancariaService.getById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente Inexistente"));
            ContaPayload contaPayload = new ContaPayload(conta);
            return ResponseEntity.status(HttpStatus.OK).body(contaPayload);
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

    @PostMapping
    public ResponseEntity create(@RequestBody ContaBancariaApi conta) {
        //HttpHeaders httpHeaders = new HttpHeaders();
        ContaBancaria retornada = contaBancariaService.create(conta);

        //httpHeaders.set("cliente-id",String.valueOf(returned.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
        // está funcionando, fazer um nova classe para receber somente o CEP e instanciar o Endereço via API
    }

    @PutMapping("/{id}")
    public ResponseEntity changeById(@PathVariable Long id, @RequestBody ContaBancariaApi conta) {
        contaBancariaService.replace(conta, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
