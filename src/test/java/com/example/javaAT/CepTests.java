package com.example.javaAT;

import com.example.javaAT.exceptions.ResourceNotFoundException;
import com.example.javaAT.models.Endereco;
import com.example.javaAT.services.CepService;
import com.example.javaAT.util.CepUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CepTests {

    @Test
    @DisplayName("Testa as informações do CEP obtido")
    public void CepApiTest(){
        CepUtil cepUtil = new CepUtil();
        Endereco endereco = cepUtil.getEndereco("80030440");

        assertEquals("Curitiba", endereco.getLocalidade());
        assertEquals("Rua Manoel Eufrásio", endereco.getLogradouro());

        assertThrows(ResourceNotFoundException.class, () -> cepUtil.getEndereco("80030441"));
        assertThrows(ResourceNotFoundException.class, () -> cepUtil.getEndereco("800304"));

    }

    @Test
    @DisplayName("Testa criação de lista de CEPs")
    public void CepServiceTest () {
        CepService cepService = new CepService();
        assertEquals(22, cepService.getListaCep().size());
        assertEquals("84071-110", cepService.getListaCep().get(1));
    }
}
