package com.example.javaAT.models.payloads;

import com.example.javaAT.models.Endereco;
import lombok.Data;

@Data
public class EnderecoPayload {
    private String localidade;
    private String uf;
    private String cep;
    private String logradouro;

    public EnderecoPayload (Endereco endereco) {
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
    }
}
