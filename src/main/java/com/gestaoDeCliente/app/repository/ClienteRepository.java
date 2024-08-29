package com.gestaoDeCliente.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaoDeCliente.app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	boolean existsByNomeIgnoreCase(String nome);
}
		