package com.traderbook.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Banca;
import com.traderbook.domains.Deposito;
import com.traderbook.repositories.RepositorioBanca;
import com.traderbook.repositories.RepositorioDeposito;

@Controller
@RequestMapping("/bank")
public class BancaController {
	
	@Autowired
	private RepositorioBanca repositorioBanca;
	@Autowired
	private RepositorioDeposito repositorioDeposito;
	
	@GetMapping("/add")
	public ModelAndView inserir() {
		ModelAndView result = new ModelAndView("banca/adicionar");
		Banca banca = new Banca();
		result.addObject("banca", banca);
		return result;
	}
	
	@PostMapping("/add")
	public String inserir(Banca banca) {
		repositorioBanca.save(banca);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Banca banca = repositorioBanca.getOne(id);
		ModelAndView result = new ModelAndView("banca/editar");
		result.addObject("banca", banca);
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Banca banca) {
		repositorioBanca.save(banca);
		return "redirect:/";
	}
	
	@GetMapping("/deposit/{id}")
	public ModelAndView depositar(@PathVariable Long id) {
		Banca banca = repositorioBanca.getOne(id);
		Deposito deposito = new Deposito(new Date());
		ModelAndView result = new ModelAndView("banca/depositar");
		result.addObject("bancas", banca);
		result.addObject("deposito", deposito);
		return result;
	}
	
	@PostMapping("/deposit")
	public String depositar(Banca banca, Deposito deposito) {
		double valorAtualizado = banca.getSaldo() + deposito.getValor();
		banca.setSaldo(valorAtualizado);
		deposito.setBanca(banca);
		repositorioDeposito.save(deposito);
		repositorioBanca.save(banca);
		return "redirect:/";
	}
	
	@GetMapping("/withdraw/{id}")
	public ModelAndView sacar(@PathVariable Long id) {
		Banca banca = repositorioBanca.getOne(id);
		Deposito deposito = new Deposito(new Date());
		ModelAndView result = new ModelAndView("banca/sacar");
		result.addObject("bancas", banca);
		result.addObject("deposito", deposito);
		return result;
	}
	
	@PostMapping("/withdraw")
	public String sacar(Banca banca, Deposito deposito) {
		double valorAtualizado = banca.getSaldo() - deposito.getValor();
		banca.setSaldo(valorAtualizado);
		deposito.setBanca(banca);
		repositorioDeposito.save(deposito);
		repositorioBanca.save(banca);
		return "redirect:/";
	}

}