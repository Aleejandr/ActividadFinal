package com.nettdatademoweb.service;

import java.util.List;

import com.nettdatademoweb.repository.entity.Asignaturas;

public interface AsignaturaService {

	 List<Asignaturas> listar();

	 Asignaturas getById(Integer id);

	 void eliminar(Integer id);

	 Asignaturas inserta(Asignaturas asignatura);

	Asignaturas modifica(Asignaturas asignatura);

	void eliminarTodasAsignaturas();
}
