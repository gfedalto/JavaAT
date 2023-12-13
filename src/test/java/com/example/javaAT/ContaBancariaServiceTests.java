package com.example.javaAT;

import com.example.javaAT.services.ContaBancariaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContaBancariaServiceTests {

    @Test
    @DisplayName("testa inicialização da conta")
    public void ContaBancariaServiceTest () {
        ContaBancariaService contaBancariaService = new ContaBancariaService();

        contaBancariaService.getContas().values().forEach(conta -> assertFalse(conta.getEndereco().isErro()));

    }




}
