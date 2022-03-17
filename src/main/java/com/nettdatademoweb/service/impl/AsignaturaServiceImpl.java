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

	@Override
	public Asignaturas getById(Integer id) throws Exception {
		return asignaturarepo.findById(id).orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		asignaturarepo.deleteById(id);
	}

	@Override
	public Asignaturas inserta(Asignaturas asignatura) throws Exception {
		return asignaturarepo.save(asignatura);
	}

	@Override
	public Asignaturas modifica(Asignaturas asignatura) {
		return asignaturarepo.save(asignatura);
	}

	@Override
	public void eliminarTodasAsignaturas() {
		asignaturarepo.deleteAll();
		
	}
	
	
}
