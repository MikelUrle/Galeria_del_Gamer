package com.mikel.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicioController {
	
	// Solicitud inicial
	@RequestMapping("/")
	public String paginaPrincipal(Model model) {
		
		return "index";
	}
	
}

