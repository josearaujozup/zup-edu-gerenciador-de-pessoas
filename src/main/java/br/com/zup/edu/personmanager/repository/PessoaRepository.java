package br.com.zup.edu.personmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.edu.personmanager.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
