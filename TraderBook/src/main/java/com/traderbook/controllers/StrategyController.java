package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Strategy;
import com.traderbook.domains.Market;
import com.traderbook.repositories.StrategyRepository;
import com.traderbook.repositories.MarketRepository;
import com.traderbook.repositories.SportRepository;

@Controller
@RequestMapping("/strategy")
public class StrategyController {
	
	@Autowired
	private StrategyRepository strategyRepository;
	@Autowired
	private MarketRepository marketRepository;
	@Autowired
	private SportRepository sportRepository;
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("methods/strategy/list");
		result.addObject("sports", sportRepository.findAll());
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("methods/strategy/add");
		result.addObject("strategy", new Strategy());
		List<Market> markets = marketRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		result.addObject("markets", markets);
		return result;
	}
	
	@PostMapping("/add")
	public String add(Strategy strategy) {
		strategyRepository.save(strategy);
		return "redirect:/strategy/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("methods/strategy/edit");
		result.addObject("strategy", strategyRepository.getOne(id));
		result.addObject("markets", marketRepository.findAll());
		return result;
	}
	
	@PostMapping("/edit")
	public String edit(Strategy strategy) {
		strategyRepository.save(strategy);
		return "redirect:/strategy/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		strategyRepository.deleteById(id);
		return "redirect:/strategy/list";
	}

}
