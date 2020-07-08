package com.traderbook.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Usuario;
import com.traderbook.repositories.RepositorioUsuario;

@Controller
@RequestMapping("/users")
public class UsuarioController {
	
	private RepositorioUsuario repositorioUsuario;
	
	@GetMapping("/list")
	public ModelAndView listar() {
		ModelAndView result = new ModelAndView("usuarios/listar");
		List<Usuario> usuarios = repositorioUsuario.findAll();
		result.addObject("usuarios", usuarios);
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("usuarios/adicionar");
		result.addObject("usuario", new Usuario());
		return result;
	}
	
//	@PostMapping("/add")
//	public String adicionar(Usuario usuario) {
//		if(result.hasErrors())
//			return "/users/add";
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		usuario.setPassword(encoder.encode(usuario.getPassword()));
//		repositorioUsuario.save(usuario);
//		return "redirect:/users/list";
//	}

}
