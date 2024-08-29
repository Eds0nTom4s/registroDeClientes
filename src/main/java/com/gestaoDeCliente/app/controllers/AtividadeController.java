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

import com.gestaoDeCliente.app.dto.AtividadeDTO;
import com.gestaoDeCliente.app.entity.Atividade;
import com.gestaoDeCliente.app.services.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {
	
	@Autowired
	private AtividadeService atividadeService;
	
	@PostMapping
	public ResponseEntity<Atividade> criarAtividade(@RequestBody AtividadeDTO atividadeDTO) {
		var atividade = new Atividade();
		
		BeanUtils.copyProperties(atividadeDTO, atividade);
		
				var atividadeSalvo = atividadeService.salvarAtividades(atividade);
		return ResponseEntity.status(HttpStatus.CREATED).body(atividadeSalvo);
		
	}
	
	@GetMapping
	public List<Atividade> listaratividade(){
		return atividadeService.listarAtividades();
	}
	@GetMapping("/{id}")
	public Atividade buscarPorId(@PathVariable Long id) {
		return atividadeService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	public Atividade atualizarCliente(@PathVariable Long id, @RequestBody Atividade atividade) {
		return atividadeService.editarAtividade(id, atividade);
	}
	
	@DeleteMapping("/{id}")
	public void excluirCliente(@PathVariable Long id) {
		atividadeService.excluirAtividade(id);
	}
}
