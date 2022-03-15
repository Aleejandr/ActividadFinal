package com.nettdatademoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nettdatademoweb.repository.entity.Usuario;
import com.nettdatademoweb.service.AsignaturaService;
import com.nettdatademoweb.service.UsuarioService;

@Controller
public class WebController {
	
	@Autowired
	AsignaturaService asignaturaservice;
	
	@Autowired
	UsuarioService usuarioservice;
	
	 @GetMapping("/")
		public String index (Model model) {
			Usuario u = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("usuario", u);
			return "index";
		}
	 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/asignaturas")
 	public String listaAsig(Model model) {
 		
 		model.addAttribute("listaAsig" , asignaturaservice.listar());
 	
 		
 		return "asignaturas";
 	}

	
	@GetMapping("/listaUsuarios")
 	public String listaUsers(Model model) {
 		
 		model.addAttribute("listaUsers" , usuarioservice.findByRol(null));
 	
 		
 		return "listaUsuarios";
 	}
	
}
