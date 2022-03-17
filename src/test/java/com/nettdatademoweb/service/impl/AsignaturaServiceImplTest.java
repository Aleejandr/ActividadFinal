package com.nettdatademoweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.nettdatademoweb.repository.AsignaturaRepoJPA;
import com.nettdatademoweb.repository.entity.Asignaturas;
import com.nettdatademoweb.service.AsignaturaService;


@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
class AsignaturaServiceImplTest {
	private Asignaturas a1 , a2;
	
	@Autowired
	AsignaturaRepoJPA asignaturarepo;

	@Autowired
	AsignaturaService service;
	
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
	void testListar() {
		//GIVEN:
		//Existen dos Asignaturas
		assertEquals(2, service.listar().size(), "2 asignaturas en BBDD");
		
		//WHEN:
		List<Asignaturas> listaAsig = service.listar();
		
		//THEN:
		assertEquals(2, listaAsig.size(), "Hay 2 asignaturas en BBDD");
	}
	
	@Test
	void testGetById() throws Exception {
		//GIVEN:
		//Hay dos asignaturas en bbdd ('Historia' y 'Fisica')
		assertEquals(2, service.listar().size(), "Hay dos asignaturas en BBDD");
		
		//WHEN:
			Asignaturas a3 = service.getById(a1.getId());
		
			//THEN:
			assertEquals (a1.getId(), a3.getId(), "Misma asignatura");
			assertNotNull (a3, "Asignatura válida");
	
		
	}
	
	@Test
	void testEliminar() {
		//GIVEN:
		//Hay dos asignaturas en bbdd ('Historia' y 'Fisica')
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		
	//WHEN:
		service.eliminar( a1.getId() );
		
	//THEN:
		assertEquals(1, service.listar().size(), "Solo queda 1 asignatura");
	}
	
	@Test
	void testInserta() throws Exception {
		//GIVEN:
		//Hay dos asignaturas en bbdd ('Historia' y 'Fisica')
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		
		//WHEN:
			Asignaturas a3 = new Asignaturas();
			a3.setNombre("Dibujo");
			a3.setDescripcion("Pa hacer Circulos");
			a3.setCurso(3);
			a3 = service.inserta(a3);
			
			//THEN:
			assertEquals(3, service.listar().size(), "Hay 3 Asignaturas");
	}

	
	@Test
	void testModifica() throws Exception {
		//GIVEN:
		//Hay dos asignaturas en bbdd ('Historia' y 'Fisica')
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		
		//WHEN:
		String nuevoNombre="Matematicas";
		a2.setNombre(nuevoNombre);
		service.modifica(a2);
		
		//THEN:
		assertEquals(2, service.listar().size(), "Sigue habiendo dos Asignaturas en BBDD");
		assertEquals (nuevoNombre, service.getById(a2.getId()).getNombre(), "Nombre Asignatura Modificado" );
	}
	
	@Test
	void testEliminarTodasAsignaturas() {
		
		//GIVEN:
		//Hay dos asignaturas en bbdd ('Historia' y 'Fisica')
		assertEquals(2, service.listar().size(), "Hay dos Asignaturas en BBDD");
		
		//WHEN:
			service.eliminarTodasAsignaturas();
			
		//THEN:
			assertEquals(0, service.listar().size(), "No hay Asignaturas en BBDD");
	}
	
}
