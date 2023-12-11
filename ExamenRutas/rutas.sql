drop database if exists rutas;
create database rutas;
use rutas;

create table municipio(
	id int not null primary key auto_increment,
	nombre varchar(100) unique null,
    provincia varchar(100) not null
)engine Innodb;
insert into municipio (nombre, provincia) values
('Abadía','Cáceres'),
('Abertura','Cáceres'),
('Acebo','Cáceres'),
('Acedera','Badajoz'),
('Acehúche','Cáceres'),
('Aceituna','Cáceres'),
('Aceuchal','Badajoz'),
('Ahigal','Cáceres'),
('Ahillones','Badajoz'),
('Alagón del Río','Cáceres'),
('Alange','Badajoz'),
('Albalá','Cáceres'),
('Alburquerque','Badajoz'),
('Alcántara','Cáceres'),
('Alcollarín','Cáceres'),
('Alconchel','Badajoz'),
('Alconera','Badajoz'),
('Alcuéscar','Cáceres'),
('Aldea del Cano','Cáceres'),
('Aldeacentenera','Cáceres'),
('Aldeanueva de la Vera','Cáceres'),
('Aldeanueva del Camino','Cáceres'),
('Aldehuela de Jerte','Cáceres'),
('Alía','Cáceres'),
('Aliseda','Cáceres'),
('Aljucén','Badajoz'),
('Almaraz','Cáceres'),
('Almendral','Badajoz'),
('Almendralejo','Badajoz'),
('Almoharín','Cáceres'),
('Arroyo de la Luz','Cáceres'),
('Arroyo de San Serván','Badajoz'),
('Arroyomolinos de la Vera','Cáceres'),
('Arroyomolinos','Cáceres'),
('Atalaya','Badajoz'),
('Azuaga','Badajoz'),
('Badajoz','Badajoz'),
('Baños de Montemayor','Cáceres'),
('Barcarrota','Badajoz'),
('Barrado','Cáceres'),
('Baterno','Badajoz'),
('Belvís de Monroy','Cáceres'),
('Benquerencia de la Serena','Badajoz'),
('Benquerencia','Cáceres'),
('Berlanga','Badajoz'),
('Berrocalejo','Cáceres'),
('Berzocana','Cáceres'),
('Bienvenida','Badajoz'),
('Bodonal de la Sierra','Badajoz'),
('Bohonal de Ibor','Cáceres'),
('Botija','Cáceres'),
('Brozas','Cáceres'),
('Burguillos del Cerro','Badajoz'),
('Cabañas del Castillo','Cáceres'),
('Cabeza del Buey','Badajoz'),
('Cabeza la Vaca','Badajoz'),
('Cabezabellosa','Cáceres'),
('Cabezuela del Valle','Cáceres'),
('Cabrero','Cáceres'),
('Cáceres','Cáceres'),
('Cachorrilla','Cáceres'),
('Cadalso','Cáceres'),
('Calamonte','Badajoz'),
('Calera de León','Badajoz'),
('Calzadilla de los Barros','Badajoz'),
('Calzadilla','Cáceres'),
('Caminomorisco','Cáceres'),
('Campanario','Badajoz'),
('Campillo de Deleitosa','Cáceres'),
('Campillo de Llerena','Badajoz'),
('Campo Lugar','Cáceres'),
('Cañamero','Cáceres'),
('Cañaveral','Cáceres'),
('Capilla','Badajoz'),
('Carbajo','Cáceres'),
('Carcaboso','Cáceres'),
('Carmonita','Badajoz'),
('Carrascalejo','Cáceres'),
('Casar de Cáceres','Cáceres'),
('Casar de Palomero','Cáceres'),
('Casares de las Hurdes','Cáceres'),
('Casas de Don Antonio','Cáceres'),
('Casas de Don Gómez','Cáceres'),
('Casas de Don Pedro','Badajoz'),
('Casas de Millán','Cáceres'),
('Casas de Miravete','Cáceres'),
('Casas de Reina','Badajoz'),
('Casas del Castañar','Cáceres'),
('Casas del Monte','Cáceres'),
('Casatejada','Cáceres'),
('Casillas de Coria','Cáceres'),
('Castañar de Ibor','Cáceres'),
('Castilblanco','Badajoz'),
('Castuera','Badajoz'),
('Ceclavín','Cáceres'),
('Cedillo','Cáceres'),
('Cerezo','Cáceres'),
('Cheles','Badajoz'),
('Cilleros','Cáceres'),
('Collado de la Vera','Cáceres'),
('Conquista de la Sierra','Cáceres'),
('Cordobilla de Lácara','Badajoz'),
('Coria','Cáceres'),
('Corte de Peleas','Badajoz'),
('Cristina','Badajoz'),
('Cuacos de Yuste','Cáceres'),
('Deleitosa','Cáceres'),
('Descargamaría','Cáceres'),
('Don Álvaro','Badajoz'),
('Don Benito','Badajoz'),
('El Carrascalejo','Badajoz'),
('El Gordo','Cáceres'),
('El Torno','Cáceres'),
('Eljas','Cáceres'),
('Entrín Bajo','Badajoz'),
('Escurial','Cáceres'),
('Esparragalejo','Badajoz'),
('Esparragosa de la Serena','Badajoz'),
('Esparragosa de Lares','Badajoz'),
('Feria','Badajoz'),
('Fregenal de la Sierra','Badajoz'),
('Fresnedoso de Ibor','Cáceres'),
('Fuenlabrada de los Montes','Badajoz'),
('Fuente de Cantos','Badajoz'),
('Fuente del Arco','Badajoz'),
('Fuente del Maestre','Badajoz'),
('Fuentes de León','Badajoz'),
('Galisteo','Cáceres'),
('Garbayuela','Badajoz'),
('Garciaz','Cáceres'),
('Garganta la Olla','Cáceres'),
('Gargantilla','Cáceres'),
('Gargüera','Cáceres'),
('Garlitos','Badajoz'),
('Garrovillas de Alconétar','Cáceres'),
('Garvín','Cáceres'),
('Gata','Cáceres'),
('Granja de Torrehermosa','Badajoz'),
('Guadalupe','Cáceres'),
('Guadiana','Badajoz'),
('Guareña','Badajoz'),
('Guijo de Coria','Cáceres'),
('Guijo de Galisteo','Cáceres'),
('Guijo de Granadilla','Cáceres'),
('Guijo de Santa Bárbara','Cáceres'),
('Helechosa de los Montes','Badajoz'),
('Herguijuela','Cáceres'),
('Hernán-Pérez','Cáceres'),
('Herrera de Alcántara','Cáceres'),
('Herrera del Duque','Badajoz'),
('Herreruela','Cáceres'),
('Hervás','Cáceres'),
('Higuera de la Serena','Badajoz'),
('Higuera de Llerena','Badajoz'),
('Higuera de Vargas','Badajoz'),
('Higuera la Real','Badajoz'),
('Higuera','Cáceres'),
('Hinojal','Cáceres'),
('Hinojosa del Valle','Badajoz'),
('Holguera','Cáceres'),
('Hornachos','Badajoz'),
('Hoyos','Cáceres'),
('Huélaga','Cáceres'),
('Ibahernando','Cáceres'),
('Jaraicejo','Cáceres'),
('Jaraíz de la Vera','Cáceres'),
('Jarandilla de la Vera','Cáceres'),
('Jarilla','Cáceres'),
('Jerez de los Caballeros','Badajoz'),
('Jerte','Cáceres'),
('La Albuera','Badajoz'),
('La Aldea del Obispo','Cáceres'),
('La Codosera','Badajoz'),
('La Coronada','Badajoz'),
('La Cumbre','Cáceres'),
('La Garganta','Cáceres'),
('La Garrovilla','Badajoz'),
('La Granja','Cáceres'),
('La Haba','Badajoz'),
('La Lapa','Badajoz'),
('La Morera','Badajoz'),
('La Nava de Santiago','Badajoz'),
('La Parra','Badajoz'),
('La Pesga','Cáceres'),
('La Roca de la Sierra','Badajoz'),
('La Zarza','Badajoz'),
('Ladrillar','Cáceres'),
('Llera','Badajoz'),
('Llerena','Badajoz'),
('Lobón','Badajoz'),
('Logrosán','Cáceres'),
('Los Santos de Maimona','Badajoz'),
('Losar de la Vera','Cáceres'),
('Madrigal de la Vera','Cáceres'),
('Madrigalejo','Cáceres'),
('Madroñera','Cáceres'),
('Magacela','Badajoz'),
('Maguilla','Badajoz'),
('Majadas de Tiétar','Cáceres'),
('Malcocinado','Badajoz'),
('Malpartida de Cáceres','Cáceres'),
('Malpartida de la Serena','Badajoz'),
('Malpartida de Plasencia','Cáceres'),
('Manchita','Badajoz'),
('Marchagaz','Cáceres'),
('Mata de Alcántara','Cáceres'),
('Medellín','Badajoz'),
('Medina de las Torres','Badajoz'),
('Membrío','Cáceres'),
('Mengabril','Badajoz'),
('Mérida','Badajoz'),
('Mesas de Ibor','Cáceres'),
('Miajadas','Cáceres'),
('Millanes','Cáceres'),
('Mirabel','Cáceres'),
('Mirandilla','Badajoz'),
('Mohedas de Granadilla','Cáceres'),
('Monesterio','Badajoz'),
('Monroy','Cáceres'),
('Montánchez','Cáceres'),
('Montehermoso','Cáceres'),
('Montemolín','Badajoz'),
('Monterrubio de la Serena','Badajoz'),
('Montijo','Badajoz'),
('Moraleja','Cáceres'),
('Morcillo','Cáceres'),
('Navaconcejo','Cáceres'),
('Navalmoral de la Mata','Cáceres'),
('Navalvillar de Ibor','Cáceres'),
('Navalvillar de Pela','Badajoz'),
('Navas del Madroño','Cáceres'),
('Navezuelas','Cáceres'),
('Nogales','Badajoz'),
('Nuñomoral','Cáceres'),
('Oliva de la Frontera','Badajoz'),
('Oliva de Mérida','Badajoz'),
('Oliva de Plasencia','Cáceres'),
('Olivenza','Badajoz'),
('Orellana de la Sierra','Badajoz'),
('Orellana la Vieja','Badajoz'),
('Palomas','Badajoz'),
('Palomero','Cáceres'),
('Pasarón de la Vera','Cáceres'),
('Pedroso de Acim','Cáceres'),
('Peñalsordo','Badajoz'),
('Peraleda de la Mata','Cáceres'),
('Peraleda de San Román','Cáceres'),
('Peraleda del Zaucejo','Badajoz'),
('Perales del Puerto','Cáceres'),
('Pescueza','Cáceres'),
('Piedras Albas','Cáceres'),
('Pinofranqueado','Cáceres'),
('Piornal','Cáceres'),
('Plasencia','Cáceres'),
('Plasenzuela','Cáceres'),
('Portaje','Cáceres'),
('Portezuelo','Cáceres'),
('Pozuelo de Zarzón','Cáceres'),
('Puebla de Alcocer','Badajoz'),
('Puebla de la Calzada','Badajoz'),
('Puebla de la Reina','Badajoz'),
('Puebla de Obando','Badajoz'),
('Puebla de Sancho Pérez','Badajoz'),
('Puebla del Maestre','Badajoz'),
('Puebla del Prior','Badajoz'),
('Pueblonuevo de Miramontes3​','Cáceres'),
('Pueblonuevo del Guadiana','Badajoz'),
('Puerto de Santa Cruz','Cáceres'),
('Quintana de la Serena','Badajoz'),
('Rebollar','Cáceres'),
('Reina','Badajoz'),
('Rena','Badajoz'),
('Retamal de Llerena','Badajoz'),
('Ribera del Fresno','Badajoz'),
('Riolobos','Cáceres'),
('Risco','Badajoz'),
('Robledillo de Gata','Cáceres'),
('Robledillo de la Vera','Cáceres'),
('Robledillo de Trujillo','Cáceres'),
('Robledollano','Cáceres'),
('Romangordo','Cáceres'),
('Rosalejo','Cáceres'),
('Ruanes','Cáceres'),
('Salorino','Cáceres'),
('Salvaleón','Badajoz'),
('Salvatierra de los Barros','Badajoz'),
('Salvatierra de Santiago','Cáceres'),
('San Martín de Trevejo','Cáceres'),
('San Pedro de Mérida','Badajoz'),
('San Vicente de Alcántara','Badajoz'),
('Sancti-Spíritus','Badajoz'),
('Santa Amalia','Badajoz'),
('Santa Ana','Cáceres'),
('Santa Cruz de la Sierra','Cáceres'),
('Santa Cruz de Paniagua','Cáceres'),
('Santa Marta de Magasca','Cáceres'),
('Santa Marta','Badajoz'),
('Santiago de Alcántara','Cáceres'),
('Santiago del Campo','Cáceres'),
('Santibáñez el Alto','Cáceres'),
('Santibáñez el Bajo','Cáceres'),
('Saucedilla','Cáceres'),
('Segura de León','Badajoz'),
('Segura de Toro','Cáceres'),
('Serradilla','Cáceres'),
('Serrejón','Cáceres'),
('Sierra de Fuentes','Cáceres'),
('Siruela','Badajoz'),
('Solana de los Barros','Badajoz'),
('Talarrubias','Badajoz'),
('Talaván','Cáceres'),
('Talavera la Real','Badajoz'),
('Talaveruela de la Vera','Cáceres'),
('Talayuela','Cáceres'),
('Táliga','Badajoz'),
('Tamurejo','Badajoz'),
('Tejeda de Tiétar','Cáceres'),
('Tiétar','Cáceres'),
('Toril','Cáceres'),
('Tornavacas','Cáceres'),
('Torre de Don Miguel','Cáceres'),
('Torre de Miguel Sesmero','Badajoz'),
('Torre de Santa María','Cáceres'),
('Torrecilla de los Ángeles','Cáceres'),
('Torrecillas de la Tiesa','Cáceres'),
('Torrejón el Rubio','Cáceres'),
('Torrejoncillo','Cáceres'),
('Torremayor','Badajoz'),
('Torremejía','Badajoz'),
('Torremenga','Cáceres'),
('Torremocha','Cáceres'),
('Torreorgaz','Cáceres'),
('Torrequemada','Cáceres'),
('Trasierra','Badajoz'),
('Trujillanos','Badajoz'),
('Trujillo','Cáceres'),
('Usagre','Badajoz'),
('Valdastillas','Cáceres'),
('Valdecaballeros','Badajoz'),
('Valdecañas de Tajo','Cáceres'),
('Valdefuentes','Cáceres'),
('Valdehúncar','Cáceres'),
('Valdelacalzada','Badajoz'),
('Valdelacasa de Tajo','Cáceres'),
('Valdemorales','Cáceres'),
('Valdeobispo','Cáceres'),
('Valdetorres','Badajoz'),
('Valencia de Alcántara','Cáceres'),
('Valencia de las Torres','Badajoz'),
('Valencia del Mombuey','Badajoz'),
('Valencia del Ventoso','Badajoz'),
('Valle de la Serena','Badajoz'),
('Valle de Matamoros','Badajoz'),
('Valle de Santa Ana','Badajoz'),
('Valverde de Burguillos','Badajoz'),
('Valverde de la Vera','Cáceres'),
('Valverde de Leganés','Badajoz'),
('Valverde de Llerena','Badajoz'),
('Valverde de Mérida','Badajoz'),
('Valverde del Fresno','Cáceres'),
('Vegaviana','Cáceres'),
('Viandar de la Vera','Cáceres'),
('Villa del Campo','Cáceres'),
('Villa del Rey','Cáceres'),
('Villafranca de los Barros','Badajoz'),
('Villagarcía de la Torre','Badajoz'),
('Villagonzalo','Badajoz'),
('Villalba de los Barros','Badajoz'),
('Villamesías','Cáceres'),
('Villamiel','Cáceres'),
('Villanueva de la Serena','Badajoz'),
('Villanueva de la Sierra','Cáceres'),
('Villanueva de la Vera','Cáceres'),
('Villanueva del Fresno','Badajoz'),
('Villar de Plasencia','Cáceres'),
('Villar de Rena','Badajoz'),
('Villar del Pedroso','Cáceres'),
('Villar del Rey','Badajoz'),
('Villarreal de San Carlos','Cáceres'),
('Villarta de los Montes','Badajoz'),
('Villasbuenas de Gata','Cáceres'),
('Zafra','Badajoz'),
('Zahínos','Badajoz'),
('Zalamea de la Serena','Badajoz'),
('Zarza Capilla','Badajoz'),
('Zarza de Granadilla','Cáceres'),
('Zarza de Montánchez','Cáceres'),
('Zarza la Mayor','Cáceres'),
('Zorita','Cáceres');

create table paraje(
	id int primary key not null auto_increment,
	nombre varchar(100) not null unique,
    hectareas int not null
)engine InnoDB;
INSERT INTO paraje VALUES 
(null,'GeoParque Villuerca-Ibores',254400),
(null,'Parque Nacional de Monfragüe',17852);

create table lugar(
	id int primary key not null auto_increment,
    nombre varchar(100) not null,
    paraje int not null,
    municipio int not null,
    foreign key (paraje) references paraje(id) on update cascade on delete restrict,
    foreign key (municipio) references municipio(id) on update cascade on delete restrict
)engine innoDB;
insert into lugar values
(null,'Ermita de Santa Catalina',(select id from paraje where nombre='GeoParque Villuerca-Ibores'),(select id from municipio where nombre='Alía')),
(null,'Castaño del Abuelo',(select id from paraje where nombre='GeoParque Villuerca-Ibores'),(select id from municipio where nombre='Guadalupe')),
(null,'Cruz de Andrade',(select id from paraje where nombre='GeoParque Villuerca-Ibores'),(select id from municipio where nombre='Cañamero')),
(null,'Castillo',(select id from paraje where nombre='Parque Nacional de Monfragüe'),(select id from municipio where nombre='Torrejón el Rubio')),
(null,'Salto del Gitano',(select id from paraje where nombre='Parque Nacional de Monfragüe'),(select id from municipio where nombre='Torrejón el Rubio')),
(null,'Fuente la Parra',(select id from paraje where nombre='Parque Nacional de Monfragüe'),(select id from municipio where nombre='Villarreal de San Carlos')),
(null,'Fuente la Francés',(select id from paraje where nombre='Parque Nacional de Monfragüe'),(select id from municipio where nombre='Villarreal de San Carlos')),
(null,'Puente Malvecino',(select id from paraje where nombre='Parque Nacional de Monfragüe'),(select id from municipio where nombre='Villarreal de San Carlos')),
(null,'Fuente la Francés',(select id from paraje where nombre='Parque Nacional de Monfragüe'),(select id from municipio where nombre='Villarreal de San Carlos'));

create table ruta(
	id int not null primary key auto_increment,
    paraje int not null,
    color enum ('verde','amarillo','rojo') not null ,
    fecha date not null,
    duracion int not null,
    foreign key (paraje) references paraje(id) on update cascade on delete restrict
)engine InnoDB;

create table ruta_lugar(
	ruta int not null,
    lugar int not null,
    primary key (ruta,lugar),
    foreign key (ruta) references ruta(id) on update cascade on delete restrict,
    foreign key (lugar) references lugar(id) on update cascade on delete restrict
)engine Innodb;

delimiter //
-- Crea el lugar que cuyo nombre se pasa por parámetro en el paraje y el municipio que se pasan por parámetros
-- Devuelve:
-- el id del lugar creado si no se produce ningún error
-- -1 si el paraje no existe
-- -2 si el municipio no existe
-- -3 si el lugar ya existe en el paraje y municipio
create function crear_lugar(p_nombre varchar(100),p_paraje int, p_municipio int)
	returns int deterministic
    begin
    -- Chequear paraje
    declare vParaje int default null;
    declare vMunicipio int default null;
    declare vParaje2 int default null;
    
    select id into vParaje from paraje where id=p_paraje;
    if(vParaje is null) then 
		return -1;
	end if;
    -- chequear municipio
    select id into vMunicipio from municipio where id=p_municipio;
    if(vMunicipio is null) then 
		return -2;
	end if;
    -- Chequar que no existe el lugar
    select id into vParaje2 from lugar where nombre = p_nombre and paraje = p_paraje and 
		municipio = p_municipio;
	if(vParaje2 is not null) then
		return -3;
	end if;
    -- Crear el lugar
    insert into lugar values (null,p_nombre,p_paraje, p_municipio);
    return last_insert_id();
    end//


