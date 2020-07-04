package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Operacao;
import com.traderbook.repositories.RepositorioOperacao;

@Controller
@RequestMapping("/operations")
public class OperacoesController {
	
	@Autowired
	private RepositorioOperacao repositorioOperacao;
	
	@GetMapping("/list")
	public ModelAndView listar() {
		ModelAndView result = new ModelAndView("operacao/listar");
		List<Operacao> operacoes = repositorioOperacao.findAll();
		result.addObject("operacoes", operacoes);
		return result;
	}

}
