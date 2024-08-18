package com.gestaoDeCliente.app.dto;

public class ClienteAtividadeDTO {

	private String nome;
	private Double capitalSocial;
	private String Atividade;
	
	
	public ClienteAtividadeDTO() {
		// TODO Auto-generated constructor stub
	}

	public ClienteAtividadeDTO(String nome, Double capitalSocial, String atividade) {
		super();
		this.nome = nome;
		this.capitalSocial = capitalSocial;
		Atividade = atividade;
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

	public String getAtividade() {
		return Atividade;
	}

	public void setAtividade(String atividade) {
		Atividade = atividade;
	}
}
