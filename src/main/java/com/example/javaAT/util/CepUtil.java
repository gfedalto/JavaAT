package com.example.javaAT.util;

import com.example.javaAT.exceptions.ResourceNotFoundException;
import com.example.javaAT.models.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;

public class CepUtil {
    public Endereco getCep(int cep) {
        String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .GET()
                    .uri(new URI(uri))
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 400){
                throw new ResourceNotFoundException();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Endereco endereco = objectMapper.readValue(response.body(), Endereco.class);
            if(endereco.isErro()){
                throw new ResourceNotFoundException();
            }
            return endereco;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
