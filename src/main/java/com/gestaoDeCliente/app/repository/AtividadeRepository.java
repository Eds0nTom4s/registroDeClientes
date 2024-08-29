package com.gestaoDeCliente.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestaoDeCliente.app.entity.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
	List<Atividade> findByDescricao(String descricao);
	boolean existsByDescricaoIgnoreCase(String descricao);
}
		