package com.example.javaAT.models.payloads;

import com.example.javaAT.models.ContaBancaria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.core.annotation.AliasFor;

@Data
public class ContaPayload {

    private String nomeCliente;
    private String cpf;
    private Long contaId;
    private int agencia;
    private String saldo;
    @JsonProperty("endereco")
    private EnderecoPayload enderecoPayload;

    public ContaPayload (ContaBancaria contaBancaria) {
        this.nomeCliente = contaBancaria.getNomeCliente();
        this.cpf = contaBancaria.getCpf();
        this.contaId = contaBancaria.getContaId();
        this.agencia = contaBancaria.getAgencia();
        this.saldo = String.format("%.2f", contaBancaria.getSaldo());
        this.enderecoPayload = new EnderecoPayload(contaBancaria.getEndereco());
    }
}
