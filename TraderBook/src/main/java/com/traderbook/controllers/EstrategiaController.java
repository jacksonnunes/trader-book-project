package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Estrategia;
import com.traderbook.domains.Mercado;
import com.traderbook.repositories.RepositorioEstrategia;
import com.traderbook.repositories.RepositorioMercado;

@Controller
@RequestMapping("/strategy")
public class EstrategiaController {
	
	@Autowired
	private RepositorioEstrategia repositorioEstrategia;
	@Autowired
	private RepositorioMercado repositorioMercado;
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("estrategia/adicionar");
		Estrategia estrategia = new Estrategia();
		estrategia.setEstrategiaMercado(new Mercado());
		result.addObject("estrategia", estrategia);
		List<Mercado> mercados = repositorioMercado.findAll();
		result.addObject("mercados", mercados);
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(Estrategia estrategia) {
		repositorioEstrategia.save(estrategia);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("estrategia/editar");
		result.addObject("estrategia", repositorioEstrategia.getOne(id));
		result.addObject("mercados", repositorioMercado.findAll());
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Estrategia estrategia) {
		repositorioEstrategia.save(estrategia);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioEstrategia.deleteById(id);
		return "redirect:/methods/index";
	}

}
