package com.gestaoDeCliente.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestaoDeCliente.app.entity.Atividade;
import com.gestaoDeCliente.app.entity.Cliente;
import com.gestaoDeCliente.app.repository.AtividadeRepository;
import com.gestaoDeCliente.app.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private AtividadeRepository atividadeRepo;
	
	public Cliente salvarCliente(Cliente cliente, Atividade atividade){
		
		atividadeRepo.save(atividade);
		cliente.setDataDeRegistro(new Date());
		//Verifica se atividade ja esta salva no banco
		if(atividade.getId() == null) {
			cliente.setAtividade(atividade);
		}
		//Associamos o cliente e atividade
		cliente.setAtividade(atividade);
		
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
	
	public Cliente editarCliente(Long id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
        
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setCapitalSocial(clienteAtualizado.getCapitalSocial());
        cliente.setAtividade(clienteAtualizado.getAtividade());
        return clienteRepo.save(cliente);
    }
}
