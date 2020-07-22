package com.traderbook.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Continent;
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
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("methods/country/list");
		List<Continent> continents = continentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		for(Continent continent : continents) {
			continent.getCountries().sort((c1, c2) -> {
				return c1.getName().compareTo(c2.getName());
			});
		}
		result.addObject("continents", continents);
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("methods/country/add");
		List<Continent> continents = continentRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		result.addObject("country", new Country());
		result.addObject("continents", continents);
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(@Valid Country country, BindingResult result) {
		if(result.hasErrors())
			return "methods/country/add";
		countryRepository.save(country);
		return "redirect:/country/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Optional<Country> country = countryRepository.findById(id);
		ModelAndView result = new ModelAndView("methods/country/edit");
		result.addObject("country", country);
		result.addObject("continents", continentRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(@Valid Country country, BindingResult result) {
		if(result.hasErrors())
			return "methods/country/edit";
		countryRepository.save(country);
		return "redirect:/country/list";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		Country country = countryRepository.getOne(id);
		country.setContinent(null);
		countryRepository.delete(country);
		return "redirect:/country/list";
	}

}
