package com.remedios.icaro.projeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedios.icaro.projeto.remedio.DadosAtualizarRemedio;
import com.remedios.icaro.projeto.remedio.DadosCadastroRemedios;
import com.remedios.icaro.projeto.remedio.DadosListagemRemedio;
import com.remedios.icaro.projeto.remedio.Remedio;
import com.remedios.icaro.projeto.remedio.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {

	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroRemedios dados) {
		
		repository.save(new Remedio(dados));
	}
	
	@GetMapping
	public List<DadosListagemRemedio> listar(){
		return repository.findAll().stream().map(DadosListagemRemedio::new).toList();
		
	}
	
	@PutMapping
	@Transactional
	public void atualizarLista(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
		
	}
	
	
}
