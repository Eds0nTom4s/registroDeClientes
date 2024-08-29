package com.gestaoDeCliente.app.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_ATIVIDADE")
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	@ManyToMany(mappedBy = "atividades")
	private Set<Cliente> clientes = new HashSet<>();
	
	public Atividade() {
		// TODO Auto-generated constructor stub
	}
	
	public Atividade(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void addCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}

	@Override
	public String toString() {
		return "Atividade [id=" + id + ", descricao=" + descricao + "]";
	}
	
	
}
