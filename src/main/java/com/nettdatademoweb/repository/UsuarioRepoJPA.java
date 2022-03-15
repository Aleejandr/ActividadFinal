package com.nettdatademoweb.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nettdatademoweb.repository.entity.Rol;
import com.nettdatademoweb.repository.entity.Usuario;

public interface UsuarioRepoJPA extends JpaRepository<Usuario, String> {
	
	List<Usuario> findByRol(Rol r);
	
}
