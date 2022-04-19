package br.com.zup.edu.personmanager.controller;

import java.time.LocalDate;

import br.com.zup.edu.personmanager.model.Pessoa;

public class PessoaDTO {
	
	private String nome;
    private String cpf;
    private String apelido;
    private LocalDate dataNascimento;
    
	public PessoaDTO(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.apelido = pessoa.getApelido();
		this.dataNascimento = pessoa.getDataNascimento();
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getApelido() {
		return apelido;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
}
