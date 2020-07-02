package com.traderbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.traderbook.repositories.RepositorioOperacao;

@Controller
public class OperacoesController {
	
	private RepositorioOperacao repositorioOperacao;
	
	@RequestMapping(value = "/listar-operacoes")
	public String listar() {
		return "operations";
	}

}
