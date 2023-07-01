use bdd_gym;

SELECT m.*, p.nombre, c.clase
FROM membresia m
JOIN plan p ON p.id = m.plan_id
JOIN clase c ON c.id = m.clase_id
WHERE m.id = (
    SELECT MAX(id) FROM membresia WHERE usuario_id = 11
)
AND m.usuario_id = 11
GROUP BY m.id, p.nombre, c.clase;


SELECT MAX(id) FROM membresia WHERE usuario_id = 11;

select r.id, u.nombre, r.fecha_entrada, r.fecha_salida, p.nombre as plan, c.clase, m.* from registro r
join usuario u on u.id = r.usuario_id
join membresia m on m.id = r.membresia_id
join plan p on p.id = m.plan_id
join clase c on c.id = m.clase_id where r.usuario_id = 11
order by r.id desc;

select r.id, u.nombre, r.fecha_entrada, r.fecha_salida, p.nombre as plan, c.clase, m.* from registro 
join usuario u on u.id = r.usuario_id 
join membresia m on m.id = r.membresia_id 
join plan p on p.id = m.plan_id 
join clase c on c.id = m.clase_id 
order by r.id desc;

select * from membresia;

update membresia set fecha_fin = '2023-06-25 23:34:54' where id = 26;
select * from registro order by id desc;
delete from registro where membresia_id = 24;
select * from administrador;

select * from usuario;
select * from registro;

delete from registro where usuario_id = 11;

SELECT r.id, u.id, u.nombre, r.fecha_entrada, r.fecha_salida, p.nombre, c.clase, m.* 
from membresia m join usuario u on u.id = m.usuario_id 
join plan p on p.id = m.plan_id join clase c on c.id = m.clase_id 
join registro r on r.usuario_id = u.id where r.usuario_id = 11 and u.id = m.usuario_id
order by r.id desc;

delete from registro where usuario_id in (2,7,8);

insert into administrador(nombre, apellido, email, password, sesion_iniciada, super) values("wacho","wachin","wacho@gmai.com","1234",0,0);
insert into cuenta(nombre_empresa, administrador_id) values("Sin nombre", 1);

alter table administrador 
change column super super_admin tinyInt not null;

alter table membresia
change column fecha_inicio fecha_inicio datetime default current_timestamp;

alter table registro_fisico
change column fecha fecha datetime default current_timestamp;

alter table registro
change column fecha_salida fecha_salida datetime null;

alter table registro
change column fecha_entrada fecha_entrada datetime not null default current_timestamp;

alter table plan_has_clase
add column precio_total decimal(10,2) not null;

alter table clase
drop column precio;

ALTER TABLE `bdd_gym`.`clase` 
CHANGE COLUMN `descripcion` `descripcion` VARCHAR(300) NULL ;

alter table plan_has_clase
add column valor_extra decimal(10,2) not null;

alter table membresia
change column fecha_inicio fecha_inicio datetime not null default current_timestamp, change column fecha_fin fecha_fin datetime not null default current_timestamp;

alter table membresia
change column fecha_fin fecha_fin datetime not null;

create table duracion(
id int not null auto_increment,
tipo varchar(20) not null,
cantidad int not null,
primary key(id));

alter table plan
drop column duracion;

alter table plan
add column duracion_id int not null;

alter table fisico 
add column fecha datetime not null default current_timestamp;

insert into usuario(nombre, apellido, fecha_nacimiento, sexo, correo, cedula, direccion, telefono, cuenta_id) values("Joel","Acosta","2003-09-13","masculino","joel@gmail.com","1850034342","Totoras en la loma","093438492834",1);
insert into usuario(nombre, apellido, fecha_nacimiento, sexo, correo, cedula, direccion, telefono, cuenta_id) values("Lucho","Miran","2003-12-28","masculino","sapo@gmail.com","1854534342","Donde las papas queman","093438492834",1);

select * from usuario;

alter table usuario
change column fecha_creacion fecha_creacion datetime not null default current_timestamp;

insert into fisico(altura, peso, usuario_id, usuario_cuenta_id) values(1.81, 71, 1, 1);
insert into fisico(altura, peso, usuario_id, usuario_cuenta_id) values(1.81, 72, 1, 1);

insert into registro_fisico(fisico_id, fisico_usuario_id, fisico_usuario_cuenta_id) values(1,1,1);
insert into registro_fisico(fisico_id, fisico_usuario_id, fisico_usuario_cuenta_id) values(2,1,1);

-- El precio en el plan ejemplo 25 es la base, dependiendo de la clase que tome aumentara o no ejemplo clase calistenia precio + 5 total 30, ejemplo 2 pesas clase + 0 total 25
insert into plan(nombre, precio, descripcion, duracion_id) values("Mes",25,"Plan de 30 días",2);
insert into plan(nombre, precio, descripcion, duracion_id) values("Diario",2,"Plan por día",1);
insert into plan(nombre, precio, descripcion, duracion_id) values("Anual",200,"Plan de 1 año",3);

insert into entrenador(nombre, apellido, sexo, correo, telefono) values("Alex","Torval","Masculino","alex@gmail.com","0983748394");

insert into clase(clase, descripcion, entrenador_id) values("Calistenia","Entrenamiento sin pesas usando unicamente el peso del cuerpo",1);

insert into duracion(tipo, cantidad) values("day",1);
insert into duracion(tipo, cantidad) values("month",1);
insert into duracion(tipo, cantidad) values("year",1);

-- El usuario tendra la opcion de agregar un valor extra tomando en cuenta que clase la cual la agregara el usuario
insert into plan_has_clase(plan_id, clase_id, precio_total, valor_extra) values(1,1,(select precio from plan where id  = 1) + 5, 5);

-- agregar tabla tiempo la cual relaciona a plan que nos va a servir para agregar la fecha_fin
insert into membresia(fecha_fin,plan_has_clase_id,usuario_id,usuario_cuenta_id) values(
current_timestamp + INTERVAL (select d.cantidad from plan p join duracion d on d.id = p.duracion_id where p.id = 1) month
,1,1,1);

select d.tipo, d.cantidad from plan p join duracion d on d.id = p.duracion_id where p.id = 1;

-- Primero insertamos sin fecha_salida y guardamos ya en java el id del registro una vez salga se hace un update
insert into registro(usuario_id) values(1);
-- este es el update
update registro set fecha_salida = current_timestamp where id = 2;

delete from duracion where cantidad = 1;

delete from plan where id = 1; 

select * from membresia;
select * from plan;
select * from clase;
select * from usuario;
select * from administrador;

insert into membresia(fecha_fin, usuario_id, plan_id, clase_id, valor_extra, valor_total, administrador_id)
values(date_add(current_timestamp, interval 1 year), 2, 8, 3, 0, 150.0,1); 

alter table plan
add column duracion enum('diario','mensual','anual'); 



















