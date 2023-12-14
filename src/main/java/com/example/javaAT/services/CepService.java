package com.example.javaAT.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


@Service
public class CepService {

    @Getter private ArrayList<String> listaCep = new ArrayList<>() ;

    public CepService()  {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/ListaCEPs.txt"))) {
            String cep = null;
            while ((cep = reader.readLine()) != null) {
                listaCep.add(cep);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
