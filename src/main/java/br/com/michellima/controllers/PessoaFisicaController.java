package br.com.michellima.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.michellima.entidades.PessoaFisica;
import br.com.michellima.repositorios.RepositorioPessoaFisica;

@Controller
@RequestMapping("/pessoasfisicas")
public class PessoaFisicaController {
	
	@Autowired
	private RepositorioPessoaFisica repositorioPessoaFisica;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("pessoafisica/index");
		List<PessoaFisica> pessoasfisicas = repositorioPessoaFisica.findAll();
		resultado.addObject("pessoasfisicas", pessoasfisicas);
		return resultado;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("pessoafisica/inserir");	
		resultado.addObject("pessoafisica", new PessoaFisica());
		return resultado;	
	}
	
	@PostMapping("/inserir")
	public String inserir(PessoaFisica usuario) {
		repositorioPessoaFisica.save(usuario);
		return "redirect:/pessoasfisicas/index";
	}
	
	@GetMapping("/editar/{cpf}")
	public ModelAndView editar(@PathVariable String cpf) {
		
		PessoaFisica pessoaFisica = repositorioPessoaFisica.getOne(cpf);
		ModelAndView resultado = new ModelAndView("pessoafisica/editar");
		resultado.addObject("pessoaFisica", pessoaFisica);
		return resultado;
		
	}
	
	@PostMapping("/editar")
	public String editar(PessoaFisica pessoaFisica) {
		
		repositorioPessoaFisica.save(pessoaFisica);
		return "redirect:/pessoasfisicas/index";
	}
	
	@GetMapping("/excluir/{cpf}")
	public String excluir (@PathVariable String cpf) {
		repositorioPessoaFisica.delete(cpf);
		return "redirect:/pessoasfisicas/index";		
	}
	
	@GetMapping({"/pesquisarPorNome/{nome}", "/pesquisarPorNome"})
	public @ResponseBody List<PessoaFisica> pesquisarPorNome (@PathVariable Optional<String> nome ){
		
				
		if(nome.isPresent()) {
			return repositorioPessoaFisica.findByNomeContaining(nome.get());	
		} else {
			return repositorioPessoaFisica.findAll();
		}
	}
	
}
