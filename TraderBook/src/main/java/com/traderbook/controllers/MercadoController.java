package com.traderbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Mercado;
import com.traderbook.repositories.RepositorioMercado;

@Controller
@RequestMapping("/market")
public class MercadoController {

	@Autowired
	private RepositorioMercado repositorioMercado;
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("mercado/adicionar");
		result.addObject("mercado", new Mercado());
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(Mercado mercado) {
		repositorioMercado.save(mercado);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Mercado mercado = repositorioMercado.getOne(id);
		ModelAndView result = new ModelAndView("mercado/editar");
		result.addObject("mercado", mercado);
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Mercado mercado) {
		repositorioMercado.save(mercado);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioMercado.deleteById(id);
		return "redirect:/methods/index";
	}
	
}
