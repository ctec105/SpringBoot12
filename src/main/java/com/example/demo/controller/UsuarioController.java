package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@GetMapping("/usersForm")
	public String usersForm(Model model, @RequestParam(name = "id", required = true) int id) {
		Usuario usuario = new Usuario();
		if (id != 0) {
			usuario = usuarioService.findUsuarioById(id);
		}
		model.addAttribute("usuario", usuario);
		return "usersForm";
	}

	@PostMapping("addUsuario")
	public String addUsuario(@ModelAttribute(name = "usuario") Usuario usuario) {
		usuarioService.addUsuario(usuario);
		return "redirect:/usuario/listado";	
	}

	@GetMapping("/listado")
	public ModelAndView listarUsuario() {  
		ModelAndView mav = new ModelAndView("users");
		mav.addObject("usuarios", usuarioService.listAllUsuario());
		return mav;
	}
	
	@GetMapping("/removeUsuario")
	public ModelAndView eliminarUsuario(@RequestParam(name = "id", required = true) int id) {
		usuarioService.removeUsuario(id);
		return listarUsuario();
	}

}
