package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Bank;
import com.traderbook.domains.Operation;
import com.traderbook.domains.Users;
import com.traderbook.repositories.AspectRepository;
import com.traderbook.repositories.BankRepository;
import com.traderbook.repositories.CompetitionRepository;
import com.traderbook.repositories.StrategyRepository;
import com.traderbook.repositories.UserRepository;
import com.traderbook.repositories.MarketRepository;
import com.traderbook.repositories.OperationRepository;
import com.traderbook.repositories.SportRepository;
import com.traderbook.repositories.CountryRepository;

@Controller
@RequestMapping("/operations")
public class OperationController {
	
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private AspectRepository aspectRepository;
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private StrategyRepository strategyRepository;
	@Autowired
	private MarketRepository marketRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private SportRepository sportRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("operation/list");
		
		//Recuperando o usuário logado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userRepository.findByEmail(username);
		
		//Listando as operações do usuário
		List<Operation> operations = operationRepository.findByUser(user);
		//Ordenando por data
		operations.sort((d1, d2) -> {
			if(d1.getDate().after(d2.getDate()))
				return 1;
			if(d1.getDate().before(d2.getDate()))
				return -1;
			return 0;
		});
		result.addObject("operations", operations);
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("operation/add");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userRepository.findByEmail(username);
		
		Operation operation = new Operation();
		result.addObject("operation", operation);
		result.addObject("banks", bankRepository.findByUser(user));
		result.addObject("aspects", aspectRepository.findAll());
		result.addObject("sports", sportRepository.findAll());
		result.addObject("markets", marketRepository.findAll());
		result.addObject("strategies", strategyRepository.findAll());
		result.addObject("countries", countryRepository.findAll());
		result.addObject("competitions", competitionRepository.findAll());
		return result;
	}
	
	@PostMapping("/add")
	public String add(Operation operation) {
		
		//Recuperando a banca escolhida
		Bank bank = bankRepository.getOne(operation.getBank().getId());
		
		//Salvando o saldo da banca no momento da operação e calculando a porcentagem investida
		operation.setBankValue(bank.getBalance());
		operation.calcBankPercInvested();
		
		//Se o valor retornado for cadastrado
		if(operation.getReturnedValue() != 0.0) {
			//Atualizando saldo da banca
			double updateBalance = bank.getBalance() + operation.getReturnedValue();
			bank.setBalance(updateBalance);
			//Calcular ROI e Retorno sobre o saldo da Banca
			operation.calcROI();
			operation.calcBankROI();
		}
		
		//Recuperando o usuário logado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userRepository.findByEmail(username);
		
		//Salvando operação para usuário logado
		operation.setUser(user);
		
		//Salvando alterações
		operationRepository.save(operation);
		bankRepository.save(bank);
		return "redirect:/operations/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("operation/edit");
		//Repassando o valor anterior, para posterior alteração no saldo da banca
		Operation operation = operationRepository.getOne(id);
		operation.setPreviousValue(operation.getReturnedValue());
		
		result.addObject("operation", operation);
		result.addObject("banks", bankRepository.findAll());
		result.addObject("aspects", aspectRepository.findAll());
		result.addObject("sports", sportRepository.findAll());
		result.addObject("markets", marketRepository.findAll());
		result.addObject("strategies", strategyRepository.findAll());
		result.addObject("countries", countryRepository.findAll());
		result.addObject("competitions", competitionRepository.findAll());
		return result;
	}
	
	@PostMapping("/edit")
	public String edit(Operation operation) {
		if(operation.getReturnedValue() != 0.0) {
			//Recuperando banca da operação
			Bank bank = bankRepository.getOne(operation.getBank().getId());
			//Calcular ROI e Retorno sobre o saldo da Banca
			operation.calcROI();
			//Alterar valor na banca
			double valorAAtualizar = operation.getReturnedValue() - operation.getPreviousValue();
			valorAAtualizar += bank.getBalance();
			bank.setBalance(valorAAtualizar);
			//Salvando altareções
			bankRepository.save(bank);
			//Calcular ROI e Retorno sobre o saldo da Banca
			operation.calcROI();
			operation.calcBankROI();
		}
		operationRepository.save(operation);
		return "redirect:/operations/list";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		Operation operation = operationRepository.getOne(id);
		Bank bank = bankRepository.getOne(operation.getBank().getId());
		//Devolvendo valor para a banca
		double valorADevolver = operation.getReturnedValue() * -1.0;
		valorADevolver += bank.getBalance();
		bank.setBalance(valorADevolver);
		//Realizando o ajuste na banca e excluindo a operação
		bankRepository.save(bank);
		operationRepository.deleteById(id);
		return "redirect:/operations/list";
	}

}
