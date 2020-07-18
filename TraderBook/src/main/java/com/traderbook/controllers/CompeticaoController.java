package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Competition;
import com.traderbook.domains.Country;
import com.traderbook.repositories.CompetitionRepository;
import com.traderbook.repositories.CountryRepository;

@Controller
@RequestMapping("/competition")
public class CompeticaoController {
	
	@Autowired
	private CompetitionRepository repositorioCompeticao;
	@Autowired
	private CountryRepository repositorioPais;
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("competicao/adicionar");
		Competition competicao = new Competition();
		competicao.setCountry(new Country());
		result.addObject("competicao", competicao);
		List<Country> paises = repositorioPais.findAll();
		paises.sort((p1, p2) -> {
			return p1.getName().compareTo(p2.getName());
		});
		result.addObject("paises", repositorioPais.findAll());
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(Competition competicao) {
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
	public String editar(Competition competicao) {
		repositorioCompeticao.save(competicao);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioCompeticao.deleteById(id);
		return "redirect:/methods/index";
	}

}
