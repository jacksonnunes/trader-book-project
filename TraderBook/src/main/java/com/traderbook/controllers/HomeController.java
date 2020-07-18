package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Bank;
import com.traderbook.domains.Users;
import com.traderbook.repositories.BankRepository;
import com.traderbook.repositories.UserRepository;

@Controller
public class HomeController {
	
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView result = new ModelAndView("login");
		return result;
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView result = new ModelAndView("index");
		//Getting logged in user by email
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Users user = userRepository.findByEmail(username);
		
		List<Bank> banks = bankRepository.findByUser(user);
		result.addObject("banks", banks);
		return result;
	}

}
