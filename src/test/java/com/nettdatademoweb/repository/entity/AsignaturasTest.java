package com.nettdatademoweb.repository.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AsignaturasTest {

	@Test
	void test() {
		
		
		Asignaturas a1 = new Asignaturas();
		
		a1.setId(1);
		assertEquals(1, a1.getId(), "Mismo id");
		
		String nombre="Nombre Prueba";
		a1.setNombre(nombre);
		assertEquals(nombre, a1.getNombre(), "Mismo error");
		
		String descripcion="descripcion Prueba";
		a1.setDescripcion(descripcion);
		assertEquals(descripcion, a1.getDescripcion(), "Mismos apellidos");
		
		a1.setCurso(5);
		assertEquals(5, a1.getCurso(), "Mismo curso");
		
		Asignaturas a2 = new Asignaturas();
		
		a2.setId(1);
		a2.setNombre("NombrePrueba25");
		a2.setDescripcion("DescripcionPrueba 25");
		a2.setCurso(5);
		
		assertEquals(a1, a2, "Misma Asignatura");
		
		assertEquals(a1.hashCode(), a2.hashCode(), "Mismo hash code");
		
		
		assertEquals(a1, a1, "Mismo objeto");
		
		assertNotEquals(a1, nombre, "Distinto objeto");
		
		a1.setId(null);
		assertNull(a1.getId(), "id de e1 is null");
		assertNotEquals(a1, a2, "e1 a null y e2 no");
		
		
	}

}
