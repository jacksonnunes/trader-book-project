package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Competicao;
import com.traderbook.domains.Pais;
import com.traderbook.repositories.RepositorioCompeticao;
import com.traderbook.repositories.RepositorioPais;

@Controller
@RequestMapping("/competition")
public class CompeticaoController {
	
	@Autowired
	private RepositorioCompeticao repositorioCompeticao;
	@Autowired
	private RepositorioPais repositorioPais;
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("competicao/adicionar");
		Competicao competicao = new Competicao();
		competicao.setPais(new Pais());
		result.addObject("competicao", competicao);
		List<Pais> paises = repositorioPais.findAll();
		paises.sort((p1, p2) -> {
			return p1.getNomePais().compareTo(p2.getNomePais());
		});
		result.addObject("paises", repositorioPais.findAll());
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(Competicao competicao) {
		repositorioCompeticao.save(competicao);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("competicao/editar");
		result.addObject("competicao", repositorioCompeticao.findById(id));
		result.addObject("paises", repositorioPais.findAll());
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Competicao competicao) {
		repositorioCompeticao.save(competicao);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioCompeticao.deleteById(id);
		return "redirect:/methods/index";
	}

}
