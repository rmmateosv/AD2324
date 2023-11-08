drop database if exists tallerDAM;
create database tallerDAM;
use tallerDAM;

create table usuarios(
	id int auto_increment primary key,
    usuario varchar(50) not null,
    ps blob not null,
    perfil enum('A','M','C')
)engine innodb;
insert into usuarios values (default, 'admin',sha2('admin',512),'A');

create table vehiculos(
	matricula varchar(7) primary key,
    propietario varchar(100) not null,
    telf varchar(9)  not null
)engine InnodB;
create table piezas(
	id int auto_increment primary key,
    nombre varchar(50)  not null,
    stock int  not null,
    precio float  not null
)engine innodb;
create table reparacion(
	id int auto_increment primary key,
    fecha date not null,
    vehiculo varchar(7) not null,
    usuario int not null,
    foreign key (usuario) references usuarios(id) on update cascade on delete restrict,
    foreign key (vehiculo) references vehiculos(matricula) on update cascade on delete restrict
)engine innodb;
create table piezaReparacion(
	reparacion int not null,
    pieza int not null,
    cantidad int not null,
    precioU int not null,
    primary key (reparacion, pieza),
    foreign key (reparacion) references reparacion(id) on update cascade on delete restrict,
    foreign key (pieza) references piezas(id) on update cascade on delete restrict
)engine innodb;