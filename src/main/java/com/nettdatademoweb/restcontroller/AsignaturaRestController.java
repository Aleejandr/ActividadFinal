package com.nettdatademoweb.restcontroller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nettdatademoweb.repository.entity.Asignaturas;

import com.nettdatademoweb.service.AsignaturaService;

@RestController
@RequestMapping ("/api/asignaturas")
public class AsignaturaRestController {
	
	
	@Autowired
	AsignaturaService asignaturaservice;
	
	@GetMapping
	@Cacheable(value="Asignaturas")
	public ResponseEntity<List<Asignaturas>> listarAsignaturas(){
		
		try {
			return new ResponseEntity<List<Asignaturas>>(asignaturaservice.listar() ,HttpStatus.OK);
			
		} 		catch (Exception ex) {
			
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	@GetMapping (value="/{id}")
	public ResponseEntity<Asignaturas> devuelveAsignatura(@PathVariable("id") Integer id){
		
		Asignaturas asig = asignaturaservice.getById(id);
		
		try {
		
			if(asig==null)
				return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(asig, HttpStatus.OK);
		
		} catch (Exception ex) {
		
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		
	}
	
	
	@DeleteMapping (value="/{id}")
	@CacheEvict(value="Asignaturas" , allEntries= true)
	public ResponseEntity<List<Asignaturas>> eliminarAsignatura(@PathVariable("id") Integer id){
		
		Asignaturas asig = asignaturaservice.getById(id);
		
		try {
			
			if(asig==null)
				
				return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
				
			
			else
		
			asignaturaservice.eliminar(id);
			
		
		} catch (Exception ex) {
		
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		return listarAsignaturas();

		
	}
	

	
	@PostMapping
	@CacheEvict(value="Asignaturas" , allEntries= true)
	public  ResponseEntity<Asignaturas> insertaAsignatura(@RequestBody Asignaturas asignatura) {
		
		try {
			HttpHeaders headers = new HttpHeaders();
			if (asignatura.getId()!=null) {
				headers.set("Message", "Para dar de alta una nueva Asignatura, el ID debe llegar vacío");
				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);
			}
			else if (asignatura.getNombre()==null || asignatura.getNombre().equals("")) {
				headers.set("Message", "El NOMBRE no puede ser nulos");
				return new ResponseEntity<>(headers, HttpStatus.NOT_ACCEPTABLE);	
			}
			
			Asignaturas asig = asignaturaservice.inserta(asignatura);
			URI newPath = new URI("/api/asignaturas/"+asig.getId());
			headers.setLocation(newPath);
			headers.set("Message", "Asignatura insertada correctamente con id: "+asig.getId());
			
			return new ResponseEntity<Asignaturas> (asig, headers,  HttpStatus.CREATED);
		}
		catch (Exception ex) {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping
	@CacheEvict(value="Asignaturas" , allEntries= true)
	public ResponseEntity<Asignaturas> modificarAsignaturas(@RequestBody Asignaturas asignatura) {
		
		try {
		
		Asignaturas asig = asignaturaservice.modifica(asignatura);
		
		return new ResponseEntity<Asignaturas> (asig , HttpStatus.OK);
		
		} catch (Exception ex) {
			
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@DeleteMapping
	@CacheEvict(value="Asignaturas" , allEntries= true)
	public  ResponseEntity<List<Asignaturas>>  eliminarTodasAsignatura(){
		

		try {

			asignaturaservice.eliminarTodasAsignaturas();
			
		
		} catch (Exception ex) {
		
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
		
		return new ResponseEntity<List<Asignaturas>>(asignaturaservice.listar() ,HttpStatus.OK);
			

		
	}


}
