package com.nettdatademoweb.restcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.TestPropertySource;

import com.nettdatademoweb.repository.AsignaturaRepoJPA;
import com.nettdatademoweb.repository.entity.Asignaturas;
import com.nettdatademoweb.service.AsignaturaService;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class AsignaturaRestControllerTest {
	private Asignaturas a1 , a2;
	
	@Autowired
	AsignaturaRepoJPA asignaturarepo;

	@Autowired
	AsignaturaService service;
	
	@Autowired
	AsignaturaRestController restcontroller;
	
	@Mock
	AsignaturaService serviceMock;
	
	@InjectMocks
	AsignaturaRestController restcontrollerMock;

	@BeforeEach
	void setUp() throws Exception {
		asignaturarepo.deleteAll();
		
		a1= new Asignaturas();
		a1.setNombre("Historia");
		a1.setDescripcion("Pa aprender Historia");
		a1.setCurso(34);
		a1=asignaturarepo.save(a1);
		
		a2= new Asignaturas();
		a2.setNombre("Fisica");
		a2.setDescripcion("Protones y Fotones");
		a2.setCurso(24);
		a2=asignaturarepo.save(a2);
	}

	@AfterEach
	void tearDown() throws Exception {
		asignaturarepo.deleteAll();
	}
	
	@Test
	void testListarAsignaturas() {
		//GIVEN:
		//Existen dos Asignaturas
		assertEquals(2, service.listar().size(), "2 asignaturas en BBDD");
		
		
		//WHEN:
		ResponseEntity<List<Asignaturas>> listaAsig = restcontroller.listarAsignaturas();
		
		//THEN:
		assertEquals(2 ,listaAsig.getBody().size());
		assertEquals(HttpStatus.OK, listaAsig.getStatusCode(), "Se espera un ok");
	}
	
	@Test
	void testDevuelveAsignatura() throws Exception{
		//GIVEN:
		//Existen dos Asignaturas
		assertEquals(2, service.listar().size(), "2 asignaturas en BBDD");
		
		
		//WHEN:
		ResponseEntity<Asignaturas> TestAsig = restcontroller.devuelveAsignatura(a1.getId());
		
		//THEN:
		assertEquals(a1, TestAsig.getBody(), "Asignatura a1" );
		assertThat (TestAsig.getStatusCodeValue() ).isEqualTo(200);
		assertThat (TestAsig.getStatusCode()).isEqualTo( HttpStatus.OK);
		
		
	}
	

	
	
	@Test
	void testEliminarAsignatura() throws Exception{
		//GIVEN:
		//Existen dos Asignaturas
		assertEquals(2, service.listar().size(), "2 asignaturas en BBDD");
		
		//WHEN:
		ResponseEntity<List<Asignaturas>> TestAsig = restcontroller.eliminarAsignatura(a1.getId());
		
		
		//THEN:
		
		assertEquals(1 ,TestAsig.getBody().size());
		
		
		
		
	}
	
	@Test
	void testInsertaAsignatura() throws Exception {
		//GIVEN:
		//Existen dos Asignaturas
		assertEquals(2, service.listar().size(), "2 asignaturas en BBDD");
		
		//WHEN:
		Asignaturas	a3 = new Asignaturas();
		a3.setNombre("Ingles");
		a3.setDescripcion("Otro idioma");
		a3.setCurso(24);
		ResponseEntity<Asignaturas> TestAsig = restcontroller.insertaAsignatura(a3);
		
		//THEN:
		assertAll (
				() -> assertEquals (HttpStatus.CREATED, TestAsig.getStatusCode(), "Código 201 creado OK"),
				() -> assertEquals (3, service.listar().size(), "Ya hay tres asignaturas en BBDD")
				);
		
	}
	
	@Test
	void testInsertaAsignatura_IdIsNotNull() throws Exception {
		//GIVEN:

		//WHEN:
		Asignaturas	a3 = new Asignaturas();
		a3.setId(4);
		a3.setNombre("Ingles");
		a3.setDescripcion("Otro idioma");
		a3.setCurso(24);
		ResponseEntity<Asignaturas> TestAsig = restcontroller.insertaAsignatura(a3);
		
		
		//THEN:
		assertEquals (HttpStatus.NOT_ACCEPTABLE, TestAsig.getStatusCode(), "Error 406 id is not null");
		
		
	}
	
	@Test
	void testInsertaAsignatura_NombreIsNull() throws Exception{
		//GIVEN:

		//WHEN:
		Asignaturas	a3 = new Asignaturas();
		a3.setDescripcion("Otro idioma");
		a3.setCurso(24);
		ResponseEntity<Asignaturas> TestAsig = restcontroller.insertaAsignatura(a3);
		
		//THEN:
		assertEquals (HttpStatus.NOT_ACCEPTABLE, TestAsig.getStatusCode(), "Error 406 nombre is null");
		
	}
	
	
	@Test
	void testInsertaAsignatura_Exception() throws Exception {
		//GIVEN:
		when ( serviceMock.inserta(a1) ).thenThrow (new Exception());
		//WHEN:
		ResponseEntity<Asignaturas> TestAsig = restcontrollerMock.insertaAsignatura(a1);
		
		//THEN:
		assertEquals(HttpStatus.NOT_ACCEPTABLE /*INTERNAL_SERVER_ERROR*/, TestAsig.getStatusCode(), "Excepción");	
		
	}
	
	
	
	@Test
	void testModificarAsignaturas() throws Exception {
				//GIVEN:
		//Existen dos Asignaturas
		assertEquals(2, service.listar().size(), "2 asignaturas en BBDD");
		
		//WHEN:
		String NombreNuevo="Arte";
		a2.setNombre(NombreNuevo);
		restcontroller.modificarAsignaturas(a2);
		
		
		//THEN:
		assertEquals(2, service.listar().size(), "Sigue habiando 2 asignaturas");
		assertEquals(NombreNuevo, service.getById(a2.getId()).getNombre(), "Nombre modificado" );
	}
	
	@Test
	void testEliminarTodasAsignatura() {
		//GIVEN:
		//Existen dos Asignaturas
			assertEquals(2, service.listar().size(), "2 asignaturas en BBDD");
			
			//WHEN:
			ResponseEntity<List<Asignaturas>> TestAsig = restcontroller.eliminarTodasAsignatura();
			
			//THEN:
			assertEquals(0, TestAsig.getBody().size(), "Lista vacia");
			assertEquals(0, service.listar().size(), "Lista vacia");
			
			assertEquals(HttpStatus.OK, TestAsig.getStatusCode(), "Se espera un ok");
	}
	
	
	
	
}
