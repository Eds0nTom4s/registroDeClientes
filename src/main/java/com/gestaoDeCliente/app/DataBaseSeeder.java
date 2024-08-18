package com.gestaoDeCliente.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.gestaoDeCliente.app.entity.Atividade;
import com.gestaoDeCliente.app.entity.Cliente;
import com.gestaoDeCliente.app.repository.AtividadeRepository;
import com.gestaoDeCliente.app.repository.ClienteRepository;

public class DataBaseSeeder implements CommandLineRunner{
	@Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Override
    public void run(String... args) throws Exception {
        //atividades
        Atividade consultoria = new Atividade(null,"Consultoria");
        Atividade desenvolvimento = new Atividade(null,"Desenvolvimento de Software");
        Atividade treinamento = new Atividade(null,"Treinamento");

        atividadeRepository.saveAll(Arrays.asList(consultoria, desenvolvimento, treinamento));

        //clientes
        Cliente empresaA = new Cliente("Empresa A", 100000.00, consultoria);
        Cliente empresaB = new Cliente("Empresa B", 50000.00, desenvolvimento);
        Cliente empresaC = new Cliente("Empresa C", 75000.00,treinamento);

        clienteRepository.saveAll(Arrays.asList(empresaA, empresaB, empresaC));
    }
}
