package com.gestaoDeCliente.app.dto;

public class ClienteAtividadeDTO {

	private String nome;
	private Double capitalSocial;
	private Long atividadeId;
	
	public ClienteAtividadeDTO() {
		// TODO Auto-generated constructor stub
	}


	public Long getAtividadeId() {
		return atividadeId;
	}


	public void setAtividadeId(Long atividadeId) {
		this.atividadeId = atividadeId;
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

}
