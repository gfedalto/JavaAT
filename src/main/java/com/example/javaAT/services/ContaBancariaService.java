package com.example.javaAT.services;

import com.example.javaAT.exceptions.ResourceNotFoundException;
import com.example.javaAT.models.ContaBancaria;
import com.example.javaAT.models.ContaBancariaApi;
import com.example.javaAT.models.Endereco;
import com.example.javaAT.util.CepUtil;
import lombok.Getter;
import org.springframework.stereotype.Service;
import com.github.javafaker.Faker;

import java.util.*;

@Service
public class ContaBancariaService {

    @Getter
    private Map<Long, ContaBancaria> contas;
    @Getter
    Long quantidadeClientes = 10L;
    private CepService cepService = new CepService();
    private CepUtil cepUtil = new CepUtil();

    public ContaBancariaService() {
        contas = initClientes();
    }

    private Map<Long, ContaBancaria> initClientes() {
        Map<Long, ContaBancaria> contasbancarias = new HashMap<Long, ContaBancaria>();

        for (int i = 1; i <= quantidadeClientes; i++) {
            Faker faker = new Faker();
            String nomeCliente = faker.name().fullName();
            int agencia = faker.number().numberBetween(100, 115);
            String cpf = faker.number().digits(11);
            double saldo = faker.number().randomDouble(2, 1, 500000);
            Endereco endereco;
            String cep;
            do {
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

    public List<ContaBancaria> getAll(String sort, List<Integer> agencias) {
        List<ContaBancaria> contasAgencias;

        if (!agencias.isEmpty()) {
            contasAgencias = contas.values().stream()
                    .filter(conta -> agencias.stream().anyMatch(agencia -> agencia == conta.getAgencia()))
                    .toList();
        } else {
            contasAgencias = contas.values().stream().toList();
        }

        if (sort.equals("agencia")) {
            return contasAgencias.stream().sorted(Comparator.comparing(ContaBancaria::getAgencia)).toList();
        } else if (sort.equals("saldo")) {
            return contasAgencias.stream().sorted(Comparator.comparing(ContaBancaria::getSaldo).reversed()).toList();
        } else {
            return contasAgencias;
        }
    }

    public Optional<ContaBancaria> getById(Long id) {
        ContaBancaria conta = contas.get(id);
        if (conta == null) return Optional.empty();
        return Optional.of(conta);
    }

    public ContaBancaria deleteById(Long id) {
        if (!contas.containsKey(id)) throw new ResourceNotFoundException("Cliente Inexistente");
        ContaBancaria removida = contas.remove(id);
        return removida;
    }

    public Long incrementId() {
        this.quantidadeClientes++;
        return quantidadeClientes;
    }
    public ContaBancaria create (ContaBancariaApi contaApi){
        Long proximoId = incrementId();
        ContaBancaria contaBancaria = ContaBancaria.builder()
                .nomeCliente(contaApi.getNomeCliente())
                .contaId(proximoId)
                .endereco(cepUtil.getEndereco(contaApi.getCep()))
                .agencia(contaApi.getAgencia())
                .saldo(contaApi.getSaldo())
                .cpf(contaApi.getCpf())
                .build();
        contas.put(proximoId, contaBancaria);
        return contaBancaria;
    }

    public ContaBancaria replace(ContaBancariaApi contaApi, long id) {
        ContaBancaria contaReplace = getById(id).get();
        if(contaApi.getNomeCliente() != null) contaReplace.setNomeCliente(contaApi.getNomeCliente());
        if(contaApi.getCpf() != null) contaReplace.setCpf(contaApi.getCpf());
        if(contaApi.getSaldo() != 0.0) contaReplace.setSaldo(contaApi.getSaldo());
        if(contaApi.getAgencia() != 0) contaReplace.setAgencia(contaApi.getAgencia());
        if(contaApi.getCep() != null) contaReplace.setEndereco(cepUtil.getEndereco(contaApi.getCep()));
        return contaReplace;
    }


}
