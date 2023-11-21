drop database if exists tallerDAM;
create database tallerDAM;
use tallerDAM;

create table usuarios(
	id int auto_increment primary key,
    usuario varchar(50) not null unique,
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
insert into piezas values 
	(default,'Rueda Michelín',100,87.5),
    (default,'Filtro Polen',76,45.5),
    (default,'Filtro Aceite',50,34.5),
    (default,'Correa de distribución',30,200.5);
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

alter table reparacion add (fechaPago date null, total float not null default 0 );
alter table reparacion add (horas float default 0, precioH float not null default 0 );
delimiter //
drop function if exists pagarReparacion//
create function pagarReparacion(pId int, pHoras float, pPrecioH float)
returns float deterministic
begin
	declare vResultado boolean default false;
    declare vTotal float default 0;
    
	-- Calcular el importe de las piezas
    select sum(cantidad*precioU)
		into vTotal
        from piezareparacion
        where reparacion = pId;
	-- Añadir a total el importe de mano de obra
    set vTotal = vTotal + (pHoras*pPrecioH);
    
    -- Modificar tabla reparación
    update reparacion set fechaPago = curdate(), 
						total = vTotal, 
						horas = pHoras, 
						precioH = pPrecioH 
			where id = pId;
	
    return vTotal;
    
end//
drop procedure if exists detalleReparacion//
create procedure detalleReparacion(pId int)
begin
end//
delimiter ;