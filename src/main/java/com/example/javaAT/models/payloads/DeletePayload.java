package com.example.javaAT.models.payloads;

import com.example.javaAT.models.ContaBancaria;
import lombok.Data;

@Data
public class DeletePayload {
    private String mensagem;
    private ContaBancaria contaBancaria;

    public DeletePayload (ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
        this.mensagem = "Conta deletada com sucesso";
    }
}
