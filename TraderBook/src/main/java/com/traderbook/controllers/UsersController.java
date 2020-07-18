package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Users;
import com.traderbook.repositories.CountryRepository;
import com.traderbook.repositories.UserRepository;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CountryRepository countryRepository;
	
	@GetMapping("/list")
	public ModelAndView listar() {
		ModelAndView result = new ModelAndView("users/list");
		List<Users> users = userRepository.findAll();
		result.addObject("users", users);
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("users/add");
		result.addObject("user", new Users());
		result.addObject("countries", countryRepository.findAll());		
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(Users user) {
		user.calcAge(user.getBirthDate());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "redirect:/users/list";
	}

}
