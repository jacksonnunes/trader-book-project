package com.traderbook.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Country;
import com.traderbook.repositories.ContinentRepository;
import com.traderbook.repositories.CountryRepository;

@Controller
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private ContinentRepository continentRepository;
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("methods/country/add");
		result.addObject("country", new Country());
		result.addObject("continents", continentRepository.findAll());
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(Country country) {
		countryRepository.save(country);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Optional<Country> country = countryRepository.findById(id);
		ModelAndView result = new ModelAndView("methods/country/edit");
		result.addObject("country", country);
		result.addObject("continents", continentRepository.findAll());
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Country pais) {
		countryRepository.save(pais);
		return "redirect:/methods/index";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		Country country = countryRepository.getOne(id);
		country.setContinent(null);
		countryRepository.delete(country);
		return "redirect:/methods/index";
	}

}
