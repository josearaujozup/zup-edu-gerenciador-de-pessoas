package br.com.zup.edu.personmanager.controller;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.edu.personmanager.model.Pessoa;
import br.com.zup.edu.personmanager.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	private final PessoaRepository repository;

	public PessoaController(PessoaRepository repository) {
		this.repository = repository;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable("id") Long idPessoa){
		
		Pessoa pessoa = repository.findById(idPessoa).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe cadastro de grupo para o id informado"));
		
		//colocar o if aqui da idade
		LocalDate hoje = LocalDate.now();
		Period periodo = Period.between(pessoa.getDataNascimento(), hoje);
		
		if(periodo.getYears() >= 18) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"não é possível remover pessoas maiores de 18");
		}
		
		repository.delete(pessoa);
		
		return ResponseEntity.noContent().build();
	}
	
}
