package com.traderbook.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Pais;
import com.traderbook.repositories.RepositorioPais;

@Controller
@RequestMapping("/country")
public class PaisController {
	
	@Autowired
	private RepositorioPais repositorioPais;
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("pais/adicionar");
		result.addObject("pais", new Pais());
		return result;
	}
	
	@PostMapping("add")
	public String adicionar(Pais pais) {
		repositorioPais.save(pais);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Optional<Pais> pais = repositorioPais.findById(id);
		ModelAndView result = new ModelAndView("pais/editar");
		result.addObject("pais", pais);
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Pais pais) {
		repositorioPais.save(pais);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		repositorioPais.deleteById(id);
		return "redirect:/methods/index";
	}

}
