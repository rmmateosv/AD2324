c-- Definir un tipo de datos complejo
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
	nss int not null unique,
	historia text[][]	
) inherits (persona);

insert into paciente values 
(default,'Pedro',('611111111','pedro@gmail.com'),123,
         array[array['01/12/2023','Diabetes tipo I'],
			   array['10/12/2023','Hipertensión']]),
(default,'María',('622222222','maria@gmail.com'),654,null);

create table medico(
	colegiado int not null unique,
	especialidad varchar(50)
) inherits (persona);

insert into medico values 
(default,'Martín',('633333333','martin@gmail.com'),1,'Dermatología'),
(default,'Mónica',('644444444','monica@gmail.com'),3,'Medicina Interna'),
(default,'Laura',('655555555','laura@gmail.com'),34,'Endocrionología');

create table consulta(
	paciente int not null,
	medico int not null,
	fecha date not null,
	diagnostico varchar(255) null,
	foreign key(paciente) references paciente(nss) on update cascade on delete restrict,
	foreign key(medico) references medico(colegiado) on update cascade on delete restrict
);


