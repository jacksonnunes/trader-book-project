package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Competition;
import com.traderbook.domains.Continent;
import com.traderbook.domains.Strategy;
import com.traderbook.domains.Market;
import com.traderbook.domains.Country;
import com.traderbook.repositories.CompetitionRepository;
import com.traderbook.repositories.ContinentRepository;
import com.traderbook.repositories.StrategyRepository;
import com.traderbook.repositories.MarketRepository;
import com.traderbook.repositories.CountryRepository;

@Controller
@RequestMapping("/methods")
public class MethodsController {
	
	@Autowired
	private ContinentRepository continentRepository;
	@Autowired
	private CompetitionRepository repositorioCompeticao;
	@Autowired
	private StrategyRepository repositorioEstrategia;
	@Autowired
	private MarketRepository repositorioMercado;
	@Autowired
	private CountryRepository repositorioPais;
	
	@GetMapping("/index")
	public ModelAndView list() {
		List<Continent> continents = continentRepository.findAll();
		List<Competition> competicoes = repositorioCompeticao.findAll();
		List<Strategy> estrategias = repositorioEstrategia.findAll();
		List<Market> mercados = repositorioMercado.findAll();
		List<Country> paises = repositorioPais.findAll();
		//Ordenando países por ordem alfabética
		continents.sort((c1, c2) -> {
			return c1.getName().compareToIgnoreCase(c2.getName());
		});
		paises.sort((p1, p2) -> {
			return p1.getName().compareTo(p2.getName());
		});
		//Ordenando mercados por ordem alfabética
		mercados.sort((m1, m2) -> {
			return m1.getName().compareTo(m2.getName());
		});
		
		ModelAndView result = new ModelAndView("methods/list");
		result.addObject("continents", continents);
		result.addObject("competicoes", competicoes);
		result.addObject("estrategias", estrategias);
		result.addObject("mercados", mercados);
		result.addObject("paises", paises);
		return result;
	}

}
