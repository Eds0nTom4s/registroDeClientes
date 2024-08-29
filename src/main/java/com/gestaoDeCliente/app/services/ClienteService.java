package com.gestaoDeCliente.app.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestaoDeCliente.app.entity.Atividade;
import com.gestaoDeCliente.app.entity.Cliente;
import com.gestaoDeCliente.app.exceptions.AtividadeNotFoundException;
import com.gestaoDeCliente.app.repository.AtividadeRepository;
import com.gestaoDeCliente.app.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private AtividadeRepository atividadeRepo;
	
	
	public Cliente adicionarAtividade(Long clienteId, Long atividadeId) {
		Optional<Cliente> clienteOptional = clienteRepo.findById(clienteId);
		
		if(!clienteOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente com ID: " + clienteId + " não encontrado.");
		}
		var cliente = clienteOptional.get();
		
		Optional<Atividade> atividadeOptional = atividadeRepo.findById(atividadeId);
		
		if(!atividadeOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Atividade com ID: " + atividadeId + " não encontrado.");
		}
		
		var atividade = atividadeOptional.get();
		
		if(!cliente.getAtividades().contains(atividade)) {
			cliente.addAtividade(atividade);
		}
		return clienteRepo.save(cliente);
	}
	
	public void removerAtividadeDoCliente(Long clienteId, Long atividadeId) {
		Optional<Cliente> clienteOptional = clienteRepo.findById(clienteId);
		
		if(!clienteOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente com ID: " + clienteId + " não encontrado.");
		}
		var cliente = clienteOptional.get();
		
		Optional<Atividade> atividadeOptional = atividadeRepo.findById(atividadeId);
		
		if(!atividadeOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Atividade com ID: " + atividadeId + " não encontrado.");
		}
		
		var atividade = atividadeOptional.get();
		
		if(cliente.getAtividades().size() <= 1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O cliente deve ter pelo menos uma atividade associada");
		}
		
		if(cliente.getAtividades().contains(atividade)) {
			cliente.getAtividades().remove(atividade);
			clienteRepo.save(cliente);
			
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A atividade não está associada a este cliente");
		}
		
	}
	
	public Cliente salvarCliente(Cliente cliente, Long atividadeId){
		Optional<Atividade> atividadeOptional = atividadeRepo.findById(atividadeId);
		if(!atividadeOptional.isPresent()) {
			throw new AtividadeNotFoundException("A Atividade com o ID: " +atividadeId+ " não foi encontrada"); 
			
			//ResponseStatusException(HttpStatus.NOT_FOUND,"O Atividade com o ID: "+atividadeId+" não foi encontrado");
		}
		if(clienteRepo.existsByNomeIgnoreCase(cliente.getNome())){
			throw new ResponseStatusException(HttpStatus.CONFLICT,"Existe cliente com o mesmo Nome");
		}
		var atividade = atividadeOptional.get();
		cliente.setDataDeRegistro();
		cliente.addAtividade(atividade);
		return clienteRepo.save(cliente);
	}

	public List<Cliente> listarClientes(){
		return clienteRepo.findAll();
	}
	
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepo.findById(id);
		if(cliente.isPresent()) {
			return cliente.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"O cliente com o ID: "+id+" não foi encontrado");
		}
	}
	public void excluirCliente(Long id) {
		if (!clienteRepo.existsById(id)) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente com ID " + id + " não encontrado.");
	    }
		clienteRepo.deleteById(id);
	}
	
}
