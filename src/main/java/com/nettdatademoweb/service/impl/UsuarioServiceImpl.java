package com.nettdatademoweb.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nettdatademoweb.repository.UsuarioRepoJPA;
import com.nettdatademoweb.repository.entity.Rol;
import com.nettdatademoweb.repository.entity.Usuario;
import com.nettdatademoweb.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService , UserDetailsService{
	
	@Autowired
	UsuarioRepoJPA usuarioDAO;
	
	
	@Override
	public List<Usuario> listar() {
		
		return usuarioDAO.findAll();
	}
	
	public List<Usuario> findByRol(Rol r){
		
		return usuarioDAO.findByRol(r = new Rol(2));
	}
	
	

	@Override
	public Usuario buscarPorUsername(String username) {
		
		Usuario u = usuarioDAO.findById(username).get();
		
		return usuarioDAO.findById(username).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 return buscarPorUsername(username);
	}



}
