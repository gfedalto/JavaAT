package com.example.javaAT.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotacaoDolar {
        private Double valor;
        private String data;

    @JsonProperty("value")
    private void setValue(Map<String,Object> value) {
        this.valor = (Double)value.get("cotacaoVenda");
        this.data = (String)value.get("dataHoraCotacao");
    }


}