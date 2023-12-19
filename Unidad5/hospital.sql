-- Definir un tipo de datos complejo
create type contacto as(
	telefono varchar(9),
	email varchar(100)
);

-- Definir tabla persona
create table persona(
	id serial primary key,
	nombre varchar(100) not null,
	datos contacto not null
);

-- Definir tabla con herencia
create table paciente(
	nss int,
	
) inherit persona;