package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Competicao;
import com.traderbook.domains.Estrategia;
import com.traderbook.domains.Mercado;
import com.traderbook.domains.Pais;
import com.traderbook.repositories.RepositorioCompeticao;
import com.traderbook.repositories.RepositorioEstrategia;
import com.traderbook.repositories.RepositorioMercado;
import com.traderbook.repositories.RepositorioPais;

@Controller
@RequestMapping("/methods")
public class MetodosController {
	
	@Autowired
	private RepositorioCompeticao repositorioCompeticao;
	@Autowired
	private RepositorioEstrategia repositorioEstrategia;
	@Autowired
	private RepositorioMercado repositorioMercado;
	@Autowired
	private RepositorioPais repositorioPais;
	
	@GetMapping("/index")
	public ModelAndView list() {
		List<Competicao> competicoes = repositorioCompeticao.findAll();
		List<Estrategia> estrategias = repositorioEstrategia.findAll();
		List<Mercado> mercados = repositorioMercado.findAll();
		List<Pais> paises = repositorioPais.findAll();
		//Ordenando países por ordem alfabética
		paises.sort((p1, p2) -> {
			return p1.getNomePais().compareTo(p2.getNomePais());
		});
		
		competicoes.sort((c1, c2) -> {
			return c1.getNome().compareTo(c2.getNome());
		});
		
		ModelAndView result = new ModelAndView("metodos/listar");
		result.addObject("competicoes", competicoes);
		result.addObject("estrategias", estrategias);
		result.addObject("mercados", mercados);
		result.addObject("paises", paises);
		return result;
	}

}