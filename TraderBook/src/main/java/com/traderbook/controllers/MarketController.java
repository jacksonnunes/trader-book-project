package com.traderbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Market;
import com.traderbook.repositories.MarketRepository;
import com.traderbook.repositories.SportRepository;

@Controller
@RequestMapping("/market")
public class MarketController {

	@Autowired
	private MarketRepository marketRepository;
	@Autowired
	private SportRepository sportRepository;
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("methods/market/add");
		result.addObject("market", new Market());
		result.addObject("sports", sportRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		return result;
	}
	
	@PostMapping("/add")
	public String add(Market market) {
		marketRepository.save(market);
		return "redirect:/strategy/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		Market market = marketRepository.getOne(id);
		ModelAndView result = new ModelAndView("methods/market/edit");
		result.addObject("market", market);
		result.addObject("sports", sportRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		return result;
	}
	
	@PostMapping("/edit")
	public String edit(Market market) {
		marketRepository.save(market);
		return "redirect:/strategy/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		marketRepository.deleteById(id);
		return "redirect:/strategy/list";
	}
	
}
