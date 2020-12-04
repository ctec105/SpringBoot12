package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	@Qualifier("usuarioRepository")
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> listAllUsuario() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario addUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario findUsuarioById(int id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public void removeUsuario(int id) {
		Usuario usuario = findUsuarioById(id);
		if (usuario != null) {
			usuarioRepository.delete(usuario);
		}
	}

}
