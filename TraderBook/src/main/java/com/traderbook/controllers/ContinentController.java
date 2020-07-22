package com.traderbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Continent;
import com.traderbook.repositories.ContinentRepository;

@Controller
@RequestMapping("/continent")
public class ContinentController {
	
	@Autowired
	private ContinentRepository continentRepository;
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("methods/continent/add");
		result.addObject("continent", new Continent());
		return result;
	}
	
	@PostMapping("/add")
	public String add(Continent continent) {
		continentRepository.save(continent);
		return "redirect:/country/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("methods/continent/edit");
		result.addObject("continent", continentRepository.getOne(id));
		return result;
	}
	
	@PostMapping("/edit")
	public String edit(Continent continent) {
		continentRepository.save(continent);
		return "redirect:/country/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		continentRepository.deleteById(id);
		return "redirect:/country/list";
	}

}
