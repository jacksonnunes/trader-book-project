package com.traderbook.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Competition;
import com.traderbook.domains.Country;
import com.traderbook.domains.Sport;
import com.traderbook.repositories.CompetitionRepository;
import com.traderbook.repositories.CountryRepository;
import com.traderbook.repositories.SportRepository;

@Controller
@RequestMapping("/competition")
public class CompetitionController {
	
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private SportRepository SportRepository;
	
	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView result = new ModelAndView("methods/competition/list");
		List<Country> countries = countryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<Sport> sports = SportRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<Country> countriesWithCompetitions = new LinkedList<Country>();
		List<Sport> sportsWithCompetitions = new LinkedList<Sport>();
		for(Country country : countries) {
			if(!country.getCompetitions().isEmpty()) {
				countriesWithCompetitions.add(country);
			}
		}
		for(Sport sport : sports) {
			if(!sport.getCompetitions().isEmpty()) {
				sportsWithCompetitions.add(sport);
			}
		}
		for(Country country : countriesWithCompetitions) {
			country.getCompetitions().sort((c1, c2) -> {
				return c1.getName().compareTo(c2.getName());
			});
		}
		result.addObject("countries", countriesWithCompetitions);
		result.addObject("sports", sportsWithCompetitions);
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView result = new ModelAndView("methods/competition/add");
		List<Country> countries = countryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<Sport> sports = SportRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		Competition competition = new Competition();
		result.addObject("competition", competition);
		result.addObject("countries", countries);
		result.addObject("sports", sports);
		return result;
	}
	
	@PostMapping("/add")
	public String add(Competition competition, Country country, Sport sport) {
		competitionRepository.save(competition);
		return "redirect:/competition/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("methods/competition/edit");
		List<Country> countries = countryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		List<Sport> sports = SportRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		result.addObject("competition", competitionRepository.findById(id));
		result.addObject("countries", countries);
		result.addObject("sports", sports);
		return result;
	}
	
	@PostMapping("/edit")
	public String edit(Competition competicao) {
		competitionRepository.save(competicao);
		return "redirect:/competition/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		competitionRepository.deleteById(id);
		return "redirect:/competition/list";
	}

}
