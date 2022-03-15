package com.nettdatademoweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nettdatademoweb.repository.AsignaturaRepoJPA;
import com.nettdatademoweb.repository.entity.Asignaturas;
import com.nettdatademoweb.service.AsignaturaService;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {
	
	@Autowired
	AsignaturaRepoJPA asignaturarepo;
	
	public List<Asignaturas> listar() {
		
		
		return asignaturarepo.findAll();
	}

}
