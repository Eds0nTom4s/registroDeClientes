package com.gestaoDeCliente.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import com.gestaoDeCliente.app.entity.Atividade;
import com.gestaoDeCliente.app.repository.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepo;
	
	@GetMapping
	public Atividade salvarAtividades(Atividade atividade){
		return atividadeRepo.save(atividade);
	}
	
	@GetMapping
	public List<Atividade> listarAtividades(){
		return atividadeRepo.findAll();
	}
	
	public void excluirAtividade(Long id) {
		if (!atividadeRepo.existsById(id)) {
	        throw new ResponseStatusException(HttpStatus.NO_CONTENT,"atividade com ID " + id + " não encontrado.");
	    }
		atividadeRepo.deleteById(id);
	}
	
	public Atividade editarAtividade(Long id, Atividade atividadeAtualizado) {
        Atividade atividade = atividadeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "atividade não encontrado"));
        atividade.setDescricao(atividadeAtualizado.getDescricao());
  
        return atividadeRepo.save(atividade);
    }

	public Atividade buscarPorId(Long id) {
		Optional<Atividade> atividade = atividadeRepo.findById(id);
		if(atividade.isPresent()) {
			return atividade.get();
		}else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT,"Ativide com ID: "+id+" Não encontrado!"); 
		}
		
	}
}
