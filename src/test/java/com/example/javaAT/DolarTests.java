package com.example.javaAT;

import com.example.javaAT.exceptions.ResourceNotFoundException;
import com.example.javaAT.models.CotacaoDolar;
import com.example.javaAT.util.DolarUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class DolarTests {

    @Test
    @DisplayName("Teste da cotação do dolar")
    public void dolarTest () {
        DolarUtil dolarUtil = new DolarUtil();
        CotacaoDolar cotacao = dolarUtil.getDolar();
        assertEquals(4.9158, cotacao.getValor());
        assertNotNull(cotacao.getData());
    }

}

