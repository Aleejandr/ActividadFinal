package com.nettdatademoweb.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.nettdatademoweb.repository.entity.Rol;
import com.nettdatademoweb.repository.entity.Usuario;

public interface UsuarioService {
	
	
	List<Usuario> listar();
	
	Usuario buscarPorUsername(String username);
	
	List<Usuario> findByRol(Rol r);
	
}
