package com.gestaoDeCliente.app.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gestaoDeCliente.app.dto.ClienteAtividadeDTO;
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
		BeanUtils.copyProperties(clienteAtividadeDTO, cliente);
	
		 Long atividadeId = clienteAtividadeDTO.getAtividadeId();
		    
		    if (atividadeId == null) {
		        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID da atividade não pode ser nulo.");
		    }
		
		Cliente clienteSalvo = clienteService.salvarCliente(cliente, clienteAtividadeDTO.getAtividadeId());
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, 
                                               @RequestBody ClienteAtividadeDTO clienteAtividadeDTO) {
    // Busca o cliente existente pelo ID
    Cliente clienteExistente = clienteService.buscarPorId(id);
    if (clienteExistente == null) {
        return ResponseEntity.notFound().build(); // Retorna 404 se o cliente não existir
    }

    // Copia as propriedades do DTO para o cliente existente, exceto as atividades
    BeanUtils.copyProperties(clienteAtividadeDTO, clienteExistente, "id", "atividade");

    // Aqui, mantemos as atividades existentes intactas
    // Se precisar atualizar algo específico nas atividades, deve ser feito em outra lógica separada

    // Atualiza o cliente sem alterar suas atividades
    Cliente clienteAtualizado = clienteService.editarCliente(id, clienteExistente);

    return ResponseEntity.ok(clienteAtualizado);
}
	
	@PatchMapping("/{clienteId}/atividades/add/{atividadeId}")
	public ResponseEntity<Cliente> adicionarAtividadeAoCliente(
	        @PathVariable Long clienteId, 
	        @PathVariable Long atividadeId) {
	    Cliente clienteAtualizado = clienteService.adicionarAtividade(clienteId, atividadeId);
	    return ResponseEntity.ok(clienteAtualizado);
	}
 
	@DeleteMapping("/{clienteId}/atividades/rem/{atividadeId}")
    public ResponseEntity<String> removerAtividade(
            @PathVariable Long clienteId, 
            @PathVariable Long atividadeId) {
        try {
            // Chama o serviço para remover a atividade do cliente
            clienteService.removerAtividadeDoCliente(clienteId, atividadeId);
            return ResponseEntity.ok("Atividade removida com sucesso do cliente.");
        } catch (ResponseStatusException e) {
            // Retorna a mensagem de erro personalizada se alguma exceção for lançada
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            // Caso alguma exceção inesperada ocorra
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor.");
        }
    }
	
	
	@DeleteMapping("/{id}")
	public void excluirCliente(@PathVariable Long id) {
		clienteService.excluirCliente(id);
	}
	
}
