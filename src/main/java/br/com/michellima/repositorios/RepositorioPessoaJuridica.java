package br.com.michellima.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.michellima.entidades.PessoaJuridica;

public interface RepositorioPessoaJuridica extends JpaRepository<PessoaJuridica, String> {

	//List<PessoaJuridica> findByNomeContaining (String nomeFantasia);
}
