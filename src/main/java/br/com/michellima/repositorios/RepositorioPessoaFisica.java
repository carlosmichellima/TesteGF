package br.com.michellima.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.michellima.entidades.PessoaFisica;

public interface RepositorioPessoaFisica extends JpaRepository<PessoaFisica, String>{
	
	List<PessoaFisica> findByNomeContaining (String nome);

}
