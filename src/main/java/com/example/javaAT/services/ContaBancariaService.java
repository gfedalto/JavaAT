package com.example.javaAT.services;

import com.example.javaAT.exceptions.ResourceNotFoundException;
import com.example.javaAT.models.ContaBancaria;
import com.example.javaAT.models.Endereco;
import com.example.javaAT.util.CepUtil;
import lombok.Getter;
import org.springframework.stereotype.Service;
import com.github.javafaker.Faker;

import java.util.*;

@Service
public class ContaBancariaService {

    @Getter private Map<Long, ContaBancaria> contas;
    @Getter int quantidadeClientes = 50;
    private CepService cepService = new CepService();
    private CepUtil cepUtil = new CepUtil();

    public ContaBancariaService () {
        contas = initClientes();
    }
    private Map<Long, ContaBancaria> initClientes() {
        Map<Long, ContaBancaria> contasbancarias = new HashMap<Long, ContaBancaria>();

        for(int i = 1; i <= quantidadeClientes; i++){
            Faker faker = new Faker();
            String nomeCliente = faker.name().fullName();
            int agencia = faker.number().numberBetween(100,115);
            String cpf = faker.number().digits(11);
            double saldo = faker.number().randomDouble(2,1,500000);
            Endereco endereco;
            String cep;
            do{
                int index = new Random().nextInt(cepService.getListaCep().size());
                cep = cepService.getListaCep().get(index).replace("-", "");
                endereco = cepUtil.getEndereco(cep);
            } while (endereco.isErro());

            ContaBancaria contaBancaria = ContaBancaria.builder()
                    .nomeCliente(nomeCliente)
                    .contaId((long) i)
                    .endereco(endereco)
                    .agencia(agencia)
                    .saldo(saldo)
                    .cpf(cpf)
                    .build();
            contasbancarias.put((long) i, contaBancaria);
        }
        return contasbancarias;
    }

    public List<ContaBancaria> getAll() {
        return contas.values().stream().toList();
    }

    public Optional<ContaBancaria> getById(Long id) {
        ContaBancaria conta = contas.get(id);
        if(conta == null) return Optional.empty();
        return Optional.of(conta);

    }

    public ContaBancaria deleteById(Long id) {
        if(!contas.containsKey(id)) throw new ResourceNotFoundException("Cliente Inexistente");
        ContaBancaria removida = contas.remove(id);
        return removida;
    }
}
