package com.gestaoDeCliente.app.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="TB_CLIENTE")
public class Cliente{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "###0.00")
	private Double capitalSocial;
	
	@Temporal(TemporalType.DATE)
	private Date dataDeRegistro;
	
	@ManyToMany
    @JoinTable(
        name = "TB_CLIENTE_ATIVIDADE",
        joinColumns = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "atividade_id")
    )
	private Set<Atividade> atividades = new HashSet<>(); 
	
	public Cliente() {
	}

	public Cliente(String nome, Double capitalSocial,Atividade atividade) {
		this.nome = nome;
		this.capitalSocial = capitalSocial;
		this.atividades.add(atividade);
	}

	public Long getId() {
		return id;
	}

	public void addAtividade(Atividade atividade) {
		this.atividades.add(atividade);
		
		atividade.getClientes().add(this);
	}
	public Set<Atividade> getAtividades(){
		return atividades;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getCapitalSocial() {
		return capitalSocial;
	}

	public void setCapitalSocial(Double capitalSocial) {
		this.capitalSocial = capitalSocial;
	}

	public Date getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro() {
		 	this.dataDeRegistro = new Date();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", capitalSocial=" + capitalSocial + ", dataDeRegistro="
				+ dataDeRegistro + "]";
	}
	
	
}
