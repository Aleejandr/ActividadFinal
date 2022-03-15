insert into Asignaturas (id, nombre, descripcion , Curso)
 select 1, 'Matematicas', 'Lo de Sumar y Restar' , 5 from dual where not exists (select 1 from Asignaturas where id = 1);

insert into Asignaturas (id, nombre, descripcion , Curso)
	select 2, 'Historia', 'Cosas que pasaron' , 3 from dual where not exists (select 1 from Asignaturas where id = 2);
	
	 insert into Asignaturas (id, nombre, descripcion , Curso)
	select 3, 'Dibujo', 'Para dibujar cosas' , 4 from dual where not exists (select 1 from Asignaturas where id = 3);
	
	insert into rol (id, rol)
	select 1, 'ADMIN' from dual where not exists (select 1 from rol where id = 1);

insert into rol (id, rol)
	select 2, 'CONSULTA' from dual where not exists (select 1 from rol where id = 2);

/* pass = 1111 */
insert into usuario (username, nombre, password, rol_id)
	select 'user1', 'Alex Martinez', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 1 from dual where not exists (select 1 from usuario where username = 'user1');

insert into usuario (username, nombre, password, rol_id)
	select 'user2', 'Empleado de NTTData', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 2 from dual where not exists (select 1 from usuario where username = 'user2');
  