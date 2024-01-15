create type datosNutricion as (
	kcal int,
    grasa int,
    hidratos int
);

create table producto(
    codigo serial primary key,
    nombre varchar(50),
    info datosNutricion,
    -- Almacena los disitintos precios que tiene 
	-- un producto a lo largo de su existencia
	precios int[] 
);
create table venta(
    codigo serial primary key,
    fecha date,
    producto int,
	cantidad int,
    precio int,
    foreign key (producto) references producto(codigo) 
	on update cascade on delete restrict
)



