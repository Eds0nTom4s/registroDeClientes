package com.gestaoDeCliente.app.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestaoDeCliente.app.dto.ClienteAtividadeDTO;
import com.gestaoDeCliente.app.entity.Atividade;
import com.gestaoDeCliente.app.entity.Cliente;
import com.gestaoDeCliente.app.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listarClientes(){
		return clienteService.listarClientes();
	}
	
	@GetMapping("/{id}")
	public Cliente buscarPorId(@PathVariable Long id) {
		return clienteService.buscarPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> criarCliente(@RequestBody ClienteAtividadeDTO clienteAtividadeDTO) {
		Cliente cliente = new Cliente();
		var atividade = new Atividade(null,clienteAtividadeDTO.getAtividade());
		BeanUtils.copyProperties(clienteAtividadeDTO, cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarCliente(cliente,atividade));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, 
			@RequestBody ClienteAtividadeDTO clienteAtividadeDTO) {
		
		Cliente cliente = new Cliente();
		var atividade = new Atividade(null,clienteAtividadeDTO.getAtividade());
		BeanUtils.copyProperties(clienteAtividadeDTO, cliente);
		cliente.setAtividade(atividade);
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.editarCliente(id, cliente));
	}
	
	@DeleteMapping("/{id}")
	public void excluirCliente(@PathVariable Long id) {
		clienteService.excluirCliente(id);
	}
	
}
