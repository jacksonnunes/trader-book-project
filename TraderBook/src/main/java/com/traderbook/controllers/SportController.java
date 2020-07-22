package com.traderbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Sport;
import com.traderbook.repositories.SportRepository;

@Controller
@RequestMapping("/sport")
public class SportController {
	
	@Autowired
	private SportRepository sportRepository;
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("methods/sport/add");
		result.addObject("sport", new Sport());
		return result;
	}
	
	@PostMapping("/add")
	public String add(Sport sport) {
		sportRepository.save(sport);
		return "redirect:/competition/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("methods/sport/edit");
		result.addObject("sport", sportRepository.getOne(id));
		return result;
	}
	
	@PostMapping("/edit")
	public String edit(Sport sport) {
		sportRepository.save(sport);
		return "redirect:/competition/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		sportRepository.deleteById(id);
		return "redirect:/competition/list";
	}

}
