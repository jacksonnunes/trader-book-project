package com.traderbook.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Bank;
import com.traderbook.domains.Deposit;
import com.traderbook.domains.Users;
import com.traderbook.domains.Withdraw;
import com.traderbook.repositories.BankRepository;
import com.traderbook.repositories.DepositRepository;
import com.traderbook.repositories.UserRepository;
import com.traderbook.repositories.WithdrawRepository;

@Controller
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private DepositRepository depositRepository;
	@Autowired
	private WithdrawRepository withdrawRepository;
	@Autowired
	private UserRepository UserRepository;
	
	@GetMapping("/add")
	public ModelAndView inserir() {
		ModelAndView result = new ModelAndView("banca/adicionar");
		Bank bank = new Bank();
		result.addObject("bank", bank);
		return result;
	}
	
	@PostMapping("/add")
	public String inserir(@Valid Bank bank, BindingResult result) {
		if(result.hasErrors()) {
			return "banca/adicionar";
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = UserRepository.findByEmail(username);
		
		bank.setUser(user);
		bankRepository.save(bank);
		return "redirect:/home";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Bank bank = bankRepository.getOne(id);
		ModelAndView result = new ModelAndView("banca/editar");
		result.addObject("bank", bank);
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Bank bank) {
		bankRepository.save(bank);
		return "redirect:/home";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		bankRepository.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/deposit/{id}")
	public ModelAndView depositar(@PathVariable Long id) {
		Bank bank = bankRepository.getOne(id);
		Deposit deposit = new Deposit();
		ModelAndView result = new ModelAndView("banca/depositar");
		result.addObject("bank", bank);
		result.addObject("deposit", deposit);
		return result;
	}
	
	@PostMapping("/deposit")
	public String depositar(Bank bank, Deposit deposit) {
		bank.deposit(deposit.getValue());
		deposit.setBank(bank);
		depositRepository.save(deposit);
		bankRepository.save(bank);
		return "redirect:/home";
	}
	
	@GetMapping("/withdraw/{id}")
	public ModelAndView sacar(@PathVariable Long id) {
		Bank bank = bankRepository.getOne(id);
		Withdraw withdraw = new Withdraw();
		ModelAndView result = new ModelAndView("banca/sacar");
		result.addObject("bank", bank);
		result.addObject("withdraw", withdraw);
		return result;
	}
	
	@PostMapping("/withdraw")
	public String sacar(@Valid Bank bank, BindingResult resultBank, @Valid Withdraw withdraw, BindingResult resultWithdraw) {
		if(resultBank.hasErrors() || resultWithdraw.hasErrors()) {
			return "banca/sacar";
		}
		bank.withdraw(withdraw.getValue());
		//Mudar o sinal para negativo
		double valorSacado = withdraw.getValue() * -1.0;
		//Atualizando valor do saque para negativo
		withdraw.setValue(valorSacado);
		
		withdraw.setBank(bank);
		withdrawRepository.save(withdraw);
		bankRepository.save(bank);
		return "redirect:/home";
	}

}
