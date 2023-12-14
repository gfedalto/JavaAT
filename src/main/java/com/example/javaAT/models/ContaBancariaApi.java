package com.example.javaAT.models;

import lombok.Builder;
import lombok.Data;
@Data
public class ContaBancariaApi {
    private String nomeCliente;
    private String cpf;
    private int agencia;
    private double saldo;
    private String cep;
}
