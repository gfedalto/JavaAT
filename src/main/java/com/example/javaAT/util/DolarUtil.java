package com.example.javaAT.util;

import com.example.javaAT.exceptions.ResourceNotFoundException;
import com.example.javaAT.models.CotacaoDolar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DolarUtil {

    public CotacaoDolar getDolar() {
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String valorFormatado = hoje.format(formatador);

        String uri = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaPeriodoFechamento(codigoMoeda=@codigoMoeda,dataInicialCotacao=@dataInicialCotacao,dataFinalCotacao=@dataFinalCotacao)?@codigoMoeda='USD'&@dataInicialCotacao='11-01-2023'&@dataFinalCotacao='" + valorFormatado + "'&$format=json&$select=cotacaoVenda,dataHoraCotacao";

        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .GET()
                    .uri(new URI(uri))
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 404){
                throw new ResourceNotFoundException();
            }

            JsonNode cotacaoNode = new ObjectMapper().readTree(response.body());

            CotacaoDolar cotacaoDolar = new CotacaoDolar();
            cotacaoDolar.setValor(cotacaoNode.get("value").get(0).get("cotacaoVenda").doubleValue());
            cotacaoDolar.setData(cotacaoNode.get("value").get(0).get("dataHoraCotacao").textValue());

            return cotacaoDolar;

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
