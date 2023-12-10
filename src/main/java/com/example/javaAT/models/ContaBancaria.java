package com.example.javaAT.models;

import lombok.Data;

@Data
public class ContaBancaria {
    private String nomeCliente;
    private Long contaId;
    private int agencia;
    private double saldo;
    private Endereco endereco;

}
