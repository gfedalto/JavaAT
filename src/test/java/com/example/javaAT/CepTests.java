package com.example.javaAT;

import com.example.javaAT.exceptions.ResourceNotFoundException;
import com.example.javaAT.models.Endereco;
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
        Endereco endereco = cepUtil.getCep(80030440);

        assertEquals("Curitiba", endereco.getLocalidade());
        assertEquals("Rua Manoel Eufrásio", endereco.getLogradouro());

        assertThrows(ResourceNotFoundException.class, () -> cepUtil.getCep(80030441));
        assertThrows(ResourceNotFoundException.class, () -> cepUtil.getCep(800304));

    }
}
