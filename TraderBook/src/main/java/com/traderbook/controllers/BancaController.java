package com.traderbook.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Banca;
import com.traderbook.domains.Deposito;
import com.traderbook.domains.Saque;
import com.traderbook.repositories.RepositorioBanca;
import com.traderbook.repositories.RepositorioDeposito;
import com.traderbook.repositories.RepositorioSaque;

@Controller
@RequestMapping("/bank")
public class BancaController {
	
	@Autowired
	private RepositorioBanca repositorioBanca;
	@Autowired
	private RepositorioDeposito repositorioDeposito;
	@Autowired
	private RepositorioSaque repositorioSaque;
	
	@GetMapping("/add")
	public ModelAndView inserir() {
		ModelAndView result = new ModelAndView("banca/adicionar");
		Banca banca = new Banca();
		result.addObject("banca", banca);
		return result;
	}
	
	@PostMapping("/add")
	public String inserir(@Valid Banca banca, BindingResult result) {
		if(result.hasErrors()) {
			return "banca/adicionar";
		}
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
		deposito.setBanca(new Banca());
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
		Saque saque = new Saque(new Date());
		saque.setBanca(new Banca());
		ModelAndView result = new ModelAndView("banca/sacar");
		result.addObject("banca", banca);
		result.addObject("saque", saque);
		return result;
	}
	
	@PostMapping("/withdraw")
	public String sacar(@Valid Banca banca, BindingResult resultBanca, @Valid Saque saque, BindingResult resultSaque) {
		if(resultBanca.hasErrors() || resultSaque.hasErrors()) {
			return "bank/withdraw/" + banca.getId();
		}
		//Mudar o sinal para negativo
		double valorSacado = saque.getValor() * -1.0;
		//Atualizando valor da banca
		double valorAtualizado = banca.getSaldo() + valorSacado;
		banca.setSaldo(valorAtualizado);
		//Atualizando valor do saque para negativo
		saque.setValor(valorSacado);
		
		saque.setBanca(banca);
		repositorioSaque.save(saque);
		repositorioBanca.save(banca);
		return "redirect:/";
	}

}
