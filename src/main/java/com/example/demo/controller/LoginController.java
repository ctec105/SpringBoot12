package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Credenciales;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public String redirectLogin() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		model.addAttribute("credenciales", new Credenciales());
		return "login";
	}
	
	@PostMapping("/check")
	public String check(@ModelAttribute("credenciales") Credenciales credenciales) {
		if (credenciales.getNombre().equals("admin") && credenciales.getContraseña().equals("admin")) {
			return "redirect:/usuario/listado";
		} else {
			return "redirect:/login?error";
		}
	}

}
