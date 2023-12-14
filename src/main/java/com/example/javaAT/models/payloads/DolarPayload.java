package com.example.javaAT.models.payloads;

import com.example.javaAT.models.ContaBancaria;
import com.example.javaAT.models.CotacaoDolar;
import com.example.javaAT.util.DolarUtil;
import lombok.Data;

@Data
public class DolarPayload {
    public Long contaId;
    public String nomeCliente;
    public String saldoDolar;
    public int agencia;

    public DolarPayload (ContaBancaria contaBancaria) {
        CotacaoDolar cotacaoDolar = new DolarUtil().getDolar();

        this.contaId = contaBancaria.getContaId();
        this.nomeCliente = contaBancaria.getNomeCliente();
        this.agencia = contaBancaria.getAgencia();
        this.saldoDolar = String.format("%.2f", contaBancaria.getSaldo() / cotacaoDolar.getValor());
    }
}
