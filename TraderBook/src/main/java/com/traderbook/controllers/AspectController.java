package com.traderbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Aspect;
import com.traderbook.repositories.AspectRepository;

@Controller
@RequestMapping("/aspect")
public class AspectController {
	
	@Autowired
	private AspectRepository aspectRepository;
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("methods/aspect/list");
		result.addObject("aspects", aspectRepository.findAll());
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("methods/aspect/add");
		Aspect aspect = new Aspect();
		result.addObject("aspect", aspect);
		return result;
	}
	
	@PostMapping("/add")
	public String add(Aspect aspect) {
		aspectRepository.save(aspect);
		return "redirect:/aspect/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("methods/aspect/edit");
		result.addObject("aspect", aspectRepository.getOne(id));
		return result;
	}
	
	@PostMapping("/edit")
	public String edit(Aspect aspect) {
		aspectRepository.save(aspect);
		return "redirect:/aspect/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		aspectRepository.deleteById(id);
		return "redirect:/aspect/list";
	}

}
