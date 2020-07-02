package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Banca;
import com.traderbook.repositories.RepositorioBanca;

@Controller
public class HomeController {
	
	@Autowired
	private RepositorioBanca repositorioBanca;
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView resultado = new ModelAndView("index");
		List<Banca> bancas = repositorioBanca.findAll();
		resultado.addObject("bancas", bancas);
		return resultado;
	}

}
