package com.example.javaAT.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {
    private String localidade;
    private String uf;
    private String cep;
    private String logradouro;
    private boolean erro;

}
