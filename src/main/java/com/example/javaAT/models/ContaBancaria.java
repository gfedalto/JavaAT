package com.example.javaAT.models;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ContaBancaria {
    private String nomeCliente;
    private String cpf;
    private Long contaId;
    private int agencia;
    private double saldo;
    private Endereco endereco;

}
