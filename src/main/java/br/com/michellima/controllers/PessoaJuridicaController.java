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

import br.com.michellima.entidades.PessoaJuridica;
import br.com.michellima.repositorios.RepositorioPessoaJuridica;



@Controller
@RequestMapping("/pessoasjuridicas")
public class PessoaJuridicaController {
	
	@Autowired
	private RepositorioPessoaJuridica repositorioPessoaJuridica;
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView resultado = new ModelAndView("pessoajuridica/index");
		List<PessoaJuridica> pessoasjuridicas = repositorioPessoaJuridica.findAll();
		resultado.addObject("pessoasjuridicas", pessoasjuridicas);
		return resultado;
	}
	
	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView resultado = new ModelAndView("pessoajuridica/inserir");	
		resultado.addObject("pessoajuridica", new PessoaJuridica());
		return resultado;	
	}
	
	@PostMapping("/inserir")
	public String inserir(PessoaJuridica usuario) {
		repositorioPessoaJuridica.save(usuario);
		return "redirect:/pessoasfisicas/index";
	}
	
	@GetMapping("/editar/{cnpj}")
	public ModelAndView editar(@PathVariable String cnpj) {
		
		PessoaJuridica pessoaJuridica = repositorioPessoaJuridica.getOne(cnpj);
		ModelAndView resultado = new ModelAndView("pessoajuridica/editar");
		resultado.addObject("pessoaJuridica", pessoaJuridica);
		return resultado;
		
	}
	
	@PostMapping("/editar")
	public String editar(PessoaJuridica pessoaJuridica) {
		
		repositorioPessoaJuridica.save(pessoaJuridica);
		return "redirect:/pessoasjuridicas/index";
	}
	
	@GetMapping("/excluir/{cnpj}")
	public String excluir (@PathVariable String cnpj) {
		repositorioPessoaJuridica.delete(cnpj);
		return "redirect:/pessoasjuridicas/index";		
	}
	
	/*@GetMapping({"/pesquisarPorNome/{nomeFantasia}", "/pesquisarPorNome"})
	public @ResponseBody List<PessoaJuridica> pesquisarPorNome (@PathVariable Optional<String> nomeFantasia ){
						
		if(nomeFantasia.isPresent()) {
			return repositorioPessoaJuridica.findByNomeContaining(nomeFantasia.get());	
		} else {
			return repositorioPessoaJuridica.findAll();
		}
	}	*/

}
