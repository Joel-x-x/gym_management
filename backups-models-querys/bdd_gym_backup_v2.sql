-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdd_gym
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdd_gym
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdd_gym` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bdd_gym` ;

-- -----------------------------------------------------
-- Table `bdd_gym`.`administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`administrador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `cedula` VARCHAR(15) NULL DEFAULT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `password_salt` VARCHAR(255) NOT NULL,
  `sesion_iniciada` TINYINT NOT NULL,
  `super_admin` TINYINT NOT NULL,
  `clave` VARCHAR(255) NULL DEFAULT NULL,
  `direccion` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `bdd_gym`.`entrenador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`entrenador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `sexo` ENUM('Masculino', 'Femenino') NOT NULL,
  `correo` VARCHAR(100) NULL DEFAULT NULL UNIQUE,
  `telefono` VARCHAR(15) NOT NULL,
  `cedula` VARCHAR(20) NOT NULL UNIQUE,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE,
  INDEX `fk_administrador_id2_idx` (`administrador_id` ASC) VISIBLE,
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE,
  CONSTRAINT `fk_entrenador_id_administrador`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdd_gym`.`clase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`clase` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `clase` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(300) NULL DEFAULT NULL,
  `entrenador_id` INT NOT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`, `entrenador_id`),
  INDEX `fk_clase_entrenador1_idx` (`entrenador_id` ASC) VISIBLE,
  INDEX `fk_administrador_id1_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_administrador_id1`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`),
  CONSTRAINT `fk_clase_entrenador1`
    FOREIGN KEY (`entrenador_id`)
    REFERENCES `bdd_gym`.`entrenador` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `bdd_gym`.`cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`cuenta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre_empresa` VARCHAR(50) NULL DEFAULT NULL,
  `logo_empresa` LONGBLOB NULL DEFAULT NULL,
  `imagen_perfil` LONGBLOB NULL DEFAULT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`, `administrador_id`),
  INDEX `fk_cuenta_administrador1_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_cuenta_administrador1`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `bdd_gym`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `fecha_nacimiento` DATE NULL DEFAULT NULL,
  `sexo` ENUM('Masculino', 'Femenino') NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL UNIQUE,
  `cedula` VARCHAR(15) NULL DEFAULT NULL UNIQUE,
  `direccion` VARCHAR(300) NULL DEFAULT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `huella` TINYINT DEFAULT 0,
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_administrador_id_usuario_idx` (`administrador_id` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC) VISIBLE,
  CONSTRAINT `fk_administrador_id`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `bdd_gym`.`fisico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`fisico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `altura` DOUBLE NOT NULL CHECK (`altura` > 0),
  `peso` DOUBLE NOT NULL CHECK (`peso` > 0),
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`),
  INDEX `fk_fisico_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_fisico_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdd_gym`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdd_gym`.`tipo_membresia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`tipo_membresia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL,
  `descripcion` VARCHAR(300) DEFAULT '',
  `precio` DECIMAL(6,2) NOT NULL CHECK (precio >= 0),
  `duracion` INT NOT NULL CHECK (duracion > 0),
  `tipo_duracion` ENUM('hour', 'day', 'month', 'year') NOT NULL,
  `clase_id` INT NOT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_clase_id_idx` (`clase_id` ASC) VISIBLE,
  INDEX `fk_administrador_id_tipo_membresia_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_clase_id`
    FOREIGN KEY (`clase_id`)
    REFERENCES `bdd_gym`.`clase` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_administrador_id_tipo_membresia`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Trigger para actulizar historial de precios tipo membresia con cada inserción
delimiter ..
drop trigger if exists after_insert_precio..
create trigger after_insert_precio
after insert on tipo_membresia
for each row
begin 
	insert into historial_precio_tipo_membresia(precio, tipo_membresia_id) values(new.precio, new.id);		
end..
delimiter ;

-- Trigger para actualizar el historial de precios tipo membresia con cada actulización
delimiter ..
drop trigger if exists before_update_precio..
create trigger before_update_precio
before update on tipo_membresia 
for each row
begin
	if(new.precio <> old.precio) then
		insert into historial_precio_tipo_membresia(precio, tipo_membresia_id) values(new.precio, new.id);
	end if;
end.. 
delimiter ;

CREATE TABLE IF NOT EXISTS `bdd_gym`.`historial_precio_tipo_membresia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `precio` DECIMAL(6,2) NOT NULL CHECK (precio >= 0),
  `tipo_membresia_id` INT NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_tipo_membresia_id_idx` (`tipo_membresia_id` ASC) VISIBLE,
  CONSTRAINT `fk_tipo_membresia_id_precio`
	FOREIGN KEY (`tipo_membresia_id`)
    REFERENCES `bdd_gym`.`tipo_membresia`(id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
  
-- -----------------------------------------------------
-- Table `bdd_gym`.`membresia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`membresia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_inicio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin` DATETIME NULL DEFAULT NULL,
  `activo` TINYINT NOT NULL,
  `usuario_id` INT NOT NULL,
  `administrador_id` INT NOT NULL,
  `tipo_membresia_id` INT NOT NULL,
  `precio_id` INT NOT NULL,
  `factura_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_membresia_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_administrador_id_membresia_idx` (`administrador_id` ASC) VISIBLE,
  INDEX `fk_tipo_membresia_id_idx` (`tipo_membresia_id` ASC) VISIBLE,
  INDEX `fk_factura_id_idx` (`factura_id` ASC) VISIBLE,
  INDEX `fk_precio_id_idx` (`precio_id` ASC) VISIBLE,
  CONSTRAINT `fk_administrador_id_membresia`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`),
  CONSTRAINT `fk_membresia_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdd_gym`.`usuario` (`id`),
  CONSTRAINT `fk_membresia_factura_id`
    FOREIGN KEY (`factura_id`)
    REFERENCES `bdd_gym`.`factura` (`id`),
  CONSTRAINT `fk_membresia_precio_id`
	FOREIGN KEY (`precio_id`)
    REFERENCES `bdd_gym`.`historial_precio_tipo_membresia` (id),
  CONSTRAINT `fk_tipo_membresia_id`
    FOREIGN KEY (`tipo_membresia_id`)
    REFERENCES `bdd_gym`.`tipo_membresia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Insertar Membresia
delimiter //
drop procedure if exists insertarMembresia//
create procedure insertarMembresia (
in administradorId int,
in usuarioId int,
in facturaId int,
in tipoMembresiaId int
 )
 begin 
 
	-- Asignar fecha fin
	declare fechaFin datetime;
    declare tipo_duracion enum('hour', 'day', 'month', 'year');
    declare duracion int;
    declare precioId int;
    
    set tipo_duracion = ( select obtener_tipo_duracion(tipoMembresiaId) );
    set duracion = ( select obtener_duracion(tipoMembresiaId) );
    set precioId = ( select obtenerIdPrecioHistorial(tipoMembresiaId));
	set fechaFin = (
		select calcularFechaFin(duracion, tipo_duracion)
    );
    
    
    -- Insertar membresia
    insert into membresia(
		fecha_fin, activo, usuario_id,
		administrador_id, tipo_membresia_id, precio_id, factura_id
    )
    values(
		fechaFin, 1, usuarioId,
        administradorId, tipoMembresiaId, precioId, facturaId
    );

end//
delimiter ;

-- Procedure listar el historial de precios de un tipo de membresia
delimiter ..
drop procedure if exists listarHistorialPrecios..
create procedure listarHistorialPrecios(in tipoMembresiaId int)
begin
	select * from historial_precio_tipo_membresia where tipo_membresia_id = tipoMembresiaId order by id desc;
end..
delimiter ;

-- Function que devuelve el ultimo precio del historial de precios recibe el id del tipo membresia
delimiter .. 
drop function if exists obtenerIdPrecioHistorial.. 
create function obtenerIdPrecioHistorial(tipoMembresiaId int)
returns int unsigned
reads sql data
begin 
	declare idPrecio int unsigned;
    
    set idPrecio = (
		select max(id) from historial_precio_tipo_membresia where tipo_membresia_id = tipoMembresiaId
    ); 
    
    return idPrecio;

end.. 
delimiter ;

-- Trigger cuando se actualice la duracion o el tipo de la membresia
delimiter //
drop trigger if exists cambiarMembresiaDespuesActualizarTipo //
create trigger cambiarMembresiaDespuesActualizarTipo
after update on tipo_membresia
for each row
begin
    declare fechaFin datetime;
    
    set fechaFin = calcularFechaFin(new.duracion, new.tipo_duracion);
    
    update membresia
    set fecha_fin = fechaFin,
        activo = if(fechaFin >= NOW(), 1, 0)
    where tipo_membresia_id = new.id;
end;
//
delimiter ;

-- Obtener duracion
delimiter ..
drop function if exists obtener_duracion..
create function obtener_duracion(tipoMembresiaId int)
returns int unsigned
reads sql data
begin
	declare duracionProcedure int unsigned;
    
    set duracionProcedure = (
		select duracion from tipo_membresia where id = tipoMembresiaId
    );
    
    return duracionProcedure;
end.. 
delimiter ;

-- Obtener tipo de duracion
delimiter ..
drop function if exists obtener_tipo_duracion..
create function obtener_tipo_duracion(tipoMembresiaId int)
returns enum('hour', 'day', 'month', 'year')
reads sql data
begin
	declare tipoDuracion enum('hour', 'day', 'month', 'year');
    
    set tipoDuracion = (
		select tipo_duracion from tipo_membresia where id = tipoMembresiaId
    );
    
    return tipoDuracion;
end.. 
delimiter ;

-- Procedures Membresias
delimiter ..
drop procedure if exists listarMembresias..
create procedure listarMembresias(in administradorId int)
begin
	select m.*, u.nombre, u.cedula, t.nombre, t.clase_id, c.clase, c.entrenador_id, e.nombre, f.numero_factura from membresia m
	join usuario u on u.id = m.usuario_id
	join tipo_membresia t on t.id = m.tipo_membresia_id
	join clase c on c.id = t.clase_id
	join entrenador e on e.id = c.entrenador_id
	join factura f on f.id = m.factura_id
    where m.administrador_id = administradorId
    order by activo desc, fecha_fin;

end.. 
delimiter ;

delimiter ..
drop procedure if exists consultarMembresiasNombre..
create procedure consultarMembresiasNombre(in administradorId int, in buscar varchar(30))
begin
	select m.*, u.nombre, u.cedula, t.nombre, t.clase_id, c.clase, c.entrenador_id, e.nombre, f.numero_factura from membresia m
	join usuario u on u.id = m.usuario_id
	join tipo_membresia t on t.id = m.tipo_membresia_id
	join clase c on c.id = t.clase_id
	join entrenador e on e.id = c.entrenador_id
	join factura f on f.id = m.factura_id
    where m.administrador_id = administradorId and u.nombre like buscar
    order by activo desc, fecha_fin;

end.. 
delimiter ;

delimiter ..
drop procedure if exists consultarMembresiasCedula..
create procedure consultarMembresiasCedula(in administradorId int, in buscar varchar(20))
begin
	select m.*, u.nombre, u.cedula, t.nombre, t.clase_id, c.clase, c.entrenador_id, e.nombre, f.numero_factura from membresia m
	join usuario u on u.id = m.usuario_id
	join tipo_membresia t on t.id = m.tipo_membresia_id
	join clase c on c.id = t.clase_id
	join entrenador e on e.id = c.entrenador_id
	join factura f on f.id = m.factura_id
    where m.administrador_id = administradorId and u.cedula like buscar
    order by activo desc, fecha_fin;
end.. 
delimiter ;

-- -----------------------------------------------------
-- Table `bdd_gym`.`recuperacion_cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`recuperacion_cuenta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre_amigo` VARCHAR(100) COLLATE 'utf8mb3_unicode_ci' NULL DEFAULT NULL,
  `nombre_mascota` VARCHAR(100) COLLATE 'utf8mb3_unicode_ci' NULL DEFAULT NULL,
  `color_favorito` VARCHAR(100) COLLATE 'utf8mb3_unicode_ci' NULL DEFAULT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_administrador_id_datos_recuperacion_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_administrador_id_datos_recuperacion`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `bdd_gym`.`registro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`registro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_entrada` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_salida` DATETIME NULL DEFAULT NULL,
  `usuario_id` INT NOT NULL,
  `membresia_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_registro_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_membresia_id_idx` (`membresia_id` ASC) VISIBLE,
  CONSTRAINT `fk_membresia_id`
    FOREIGN KEY (`membresia_id`)
    REFERENCES `bdd_gym`.`membresia` (`id`),
  CONSTRAINT `fk_registro_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdd_gym`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `bdd_gym`.`factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdd_gym`.`factura` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero_factura` VARCHAR(10) DEFAULT NULL,
  `descuento_porcentaje` DOUBLE DEFAULT 0.0,
  `descuento` DECIMAL(6,2) DEFAULT 0.0,
  `subtotal` DECIMAL(6,2) DEFAULT 0.0,
  `iva` DECIMAL(6,2) DEFAULT 0.0,
  `total` DECIMAL(6,2) DEFAULT 0.0,
  `forma_pago` ENUM('efectivo', 'transferencia') DEFAULT 'efectivo',
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `establecimiento` VARCHAR(3) DEFAULT '001',
  `punto_emision` VARCHAR(3) DEFAULT '001',
  `usuario_id` INT DEFAULT NULL,
  `iva_id` INT NOT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_id_establecimiento_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_administrador_id_idx` (`administrador_id` ASC) VISIBLE,
  INDEX `fk_iva_id_idx` (`iva_id` ASC) VISIBLE,
  UNIQUE INDEX `numero_factura_UNIQUE` (`numero_factura` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_id_factura`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdd_gym`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_iva_id_factura`
    FOREIGN KEY (`iva_id`)
    REFERENCES `bdd_gym`.`iva` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_administrador_id_factura`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Insertar Factura
DELIMITER //
drop procedure if exists insertarFactura//
CREATE PROCEDURE insertarFactura(
    IN administradorId INT
)
BEGIN
    DECLARE numeroFactura VARCHAR(10);
    
    INSERT INTO factura (administrador_id, iva_id) VALUES (administradorId, (select obtenerIdIva(administradorId)));
    SET numeroFactura = LPAD(LAST_INSERT_ID(), 9, '0');
    
    UPDATE factura SET numero_factura = numeroFactura WHERE id = LAST_INSERT_ID();
END;
//
DELIMITER ;

-- Actualizar factura
delimiter ..
drop procedure if exists actualizarFactura..
create procedure actualizarFactura(in idIn int, in descuentoPorcentaje double,
in descuentoIn decimal(6,2), in subtotalIn decimal(6,2), in ivaIn decimal(6,2),
in totalIn decimal(6,2), in formaPago varchar(15), in fechaIn datetime, in usuarioId int)
begin
	update factura set descuento_porcentaje = descuentoPorcentaje,
    descuento = descuentoIn, subtotal = subtotalIn, iva = ivaIn, total = totalIn,
    forma_pago = formaPago, fecha = fechaIn, usuario_id = usuarioid  where id = idIn;
end ..
delimiter ;

-- Calcular fecha fin
delimiter //
drop function if exists calcularFechaFin//
create function calcularFechaFin(
    duracion int,
    tipo_duracion enum('hour', 'day', 'month', 'year')
) returns datetime
deterministic
begin
    declare fecha_fin datetime;
    
    case tipo_duracion
        when 'hour' then
            set fecha_fin = date_add(current_timestamp(), interval duracion hour);
        when 'day' then
            set fecha_fin = date_add(current_timestamp(), interval duracion day);
        when 'month' then
            set fecha_fin = date_add(current_timestamp(), interval duracion month); 
        when 'year' then
            set fecha_fin = date_add(current_timestamp(), interval duracion year);
        else 
            set fecha_fin = current_timestamp();
    end case;
    
    return fecha_fin;
end;
//
delimiter ;

-- IVA
CREATE TABLE IF NOT EXISTS `bdd_gym`.`iva` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `iva` DECIMAL(6,2) NOT NULL CHECK (iva >= 0),
  `administrador_id` INT NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_administrador_id_idx` (`administrador_id` ASC) VISIBLE,
  CONSTRAINT `fk_administrador_id_iva`
	FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador`(id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- Function que devuelve el ultimo iva insertador recibe el id del administrador
delimiter .. 
drop function if exists obtenerIdIva.. 
create function obtenerIdIva(administradorId int)
returns int unsigned
reads sql data
begin 
	declare idIva int unsigned;
    set idIva = (
		select max(id) from iva where administrador_id = administradorId
    ); 
    return idIva;
end.. 
delimiter ;

-- Procedure listar ivas
delimiter ..
drop procedure if exists listarIvas..
create procedure listarIvas(in administradorId int)
begin
	select * from iva where administrador_id = administradorId order by id desc;
end..
delimiter ;

-- === AUDITORIAS ===
-- === Administrador ===
-- Crear tabla de auditoría para administrador
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_administrador` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(100) NOT NULL,
  `cedula` VARCHAR(15) NULL DEFAULT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en administrador
DELIMITER //
CREATE TRIGGER auditoria_administrador_insert
AFTER INSERT ON administrador
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_administrador (id, accion, nombre, apellido, email, cedula)
  VALUES (NEW.id, 'INSERT', NEW.nombre, NEW.apellido, NEW.email, NEW.cedula);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en administrador
DELIMITER //
CREATE TRIGGER auditoria_administrador_update
AFTER UPDATE ON administrador
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_administrador (id, accion, nombre, apellido, email, cedula)
  VALUES (NEW.id, 'UPDATE', NEW.nombre, NEW.apellido, NEW.email, NEW.cedula);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en administrador
DELIMITER //
CREATE TRIGGER auditoria_administrador_delete
AFTER DELETE ON administrador
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_administrador (id, accion, nombre, apellido, email, cedula)
  VALUES (OLD.id, 'DELETE', OLD.nombre, OLD.apellido, OLD.email, OLD.cedula);
END;
//
DELIMITER ;

-- === Entrenador ===
-- Crear tabla de auditoría para entrenador
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_entrenador` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `sexo` ENUM('Masculino', 'Femenino') NOT NULL,
  `correo` VARCHAR(100) NULL DEFAULT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `cedula` VARCHAR(20) NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en entrenador
DELIMITER //
CREATE TRIGGER auditoria_entrenador_insert
AFTER INSERT ON entrenador
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_entrenador (id, accion, nombre, apellido, sexo, correo, telefono, cedula)
  VALUES (NEW.id, 'INSERT', NEW.nombre, NEW.apellido, NEW.sexo, NEW.correo, NEW.telefono, NEW.cedula);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en entrenador
DELIMITER //
CREATE TRIGGER auditoria_entrenador_update
AFTER UPDATE ON entrenador
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_entrenador (id, accion, nombre, apellido, sexo, correo, telefono, cedula)
  VALUES (NEW.id, 'UPDATE', NEW.nombre, NEW.apellido, NEW.sexo, NEW.correo, NEW.telefono, NEW.cedula);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en entrenador
DELIMITER //
CREATE TRIGGER auditoria_entrenador_delete
AFTER DELETE ON entrenador
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_entrenador (id, accion, nombre, apellido, sexo, correo, telefono, cedula)
  VALUES (OLD.id, 'DELETE', OLD.nombre, OLD.apellido, OLD.sexo, OLD.correo, OLD.telefono, OLD.cedula);
END;
//
DELIMITER ;

-- === Clase ===
-- Modificar tabla de auditoría para clase
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_clase` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `clase` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(300) NULL DEFAULT NULL,
  `entrenador_id` INT NOT NULL,
  `administrador_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en clase
DELIMITER //
CREATE TRIGGER auditoria_clase_insert
AFTER INSERT ON clase
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_clase (id, accion, clase, descripcion, entrenador_id, administrador_id)
  VALUES (NEW.id, 'INSERT', NEW.clase, NEW.descripcion, NEW.entrenador_id, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en clase
DELIMITER //
CREATE TRIGGER auditoria_clase_update
AFTER UPDATE ON clase
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_clase (id, accion, clase, descripcion, entrenador_id, administrador_id)
  VALUES (NEW.id, 'UPDATE', NEW.clase, NEW.descripcion, NEW.entrenador_id, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en clase
DELIMITER //
CREATE TRIGGER auditoria_clase_delete
AFTER DELETE ON clase
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_clase (id, accion, clase, descripcion, entrenador_id, administrador_id)
  VALUES (OLD.id, 'DELETE', OLD.clase, OLD.descripcion, OLD.entrenador_id, OLD.administrador_id);
END;
//
DELIMITER ;

-- === Cuenta ===
-- Modificar tabla de auditoría para cuenta
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_cuenta` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre_empresa` VARCHAR(50) NULL DEFAULT NULL,
  `administrador_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en cuenta
DELIMITER //
CREATE TRIGGER auditoria_cuenta_insert
AFTER INSERT ON cuenta
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cuenta (id, accion, nombre_empresa, administrador_id)
  VALUES (NEW.id, 'INSERT', NEW.nombre_empresa, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en cuenta
DELIMITER //
CREATE TRIGGER auditoria_cuenta_update
AFTER UPDATE ON cuenta
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cuenta (id, accion, nombre_empresa, administrador_id)
  VALUES (NEW.id, 'UPDATE', NEW.nombre_empresa, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en cuenta
DELIMITER //
CREATE TRIGGER auditoria_cuenta_delete
AFTER DELETE ON cuenta
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_cuenta (id, accion, nombre_empresa, administrador_id)
  VALUES (OLD.id, 'DELETE', OLD.nombre_empresa, OLD.administrador_id);
END;
//
DELIMITER ;

-- === Usuario ===
-- Modificar tabla de auditoría para usuario
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_usuario` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NOT NULL,
  `fecha_nacimiento` DATE NULL DEFAULT NULL,
  `sexo` ENUM('Masculino', 'Femenino') NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `cedula` VARCHAR(15) NULL DEFAULT NULL,
  `direccion` VARCHAR(300) NULL DEFAULT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `administrador_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en usuario
DELIMITER //
CREATE TRIGGER auditoria_usuario_insert
AFTER INSERT ON usuario
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_usuario (id, accion, nombre, apellido, fecha_nacimiento, sexo, email, cedula, direccion, telefono, administrador_id)
  VALUES (NEW.id, 'INSERT', NEW.nombre, NEW.apellido, NEW.fecha_nacimiento, NEW.sexo, NEW.email, NEW.cedula, NEW.direccion, NEW.telefono, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en usuario
DELIMITER //
CREATE TRIGGER auditoria_usuario_update
AFTER UPDATE ON usuario
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_usuario (id, accion, nombre, apellido, fecha_nacimiento, sexo, email, cedula, direccion, telefono, administrador_id)
  VALUES (NEW.id, 'UPDATE', NEW.nombre, NEW.apellido, NEW.fecha_nacimiento, NEW.sexo, NEW.email, NEW.cedula, NEW.direccion, NEW.telefono, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en usuario
DELIMITER //
CREATE TRIGGER auditoria_usuario_delete
AFTER DELETE ON usuario
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_usuario (id, accion, nombre, apellido, fecha_nacimiento, sexo, email, cedula, direccion, telefono, administrador_id)
  VALUES (OLD.id, 'DELETE', OLD.nombre, OLD.apellido, OLD.fecha_nacimiento, OLD.sexo, OLD.email, OLD.cedula, OLD.direccion, OLD.telefono, OLD.administrador_id);
END;
//
DELIMITER ;

-- === Fisico ===
-- Modificar tabla de auditoría para fisico
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_fisico` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `altura` DOUBLE NOT NULL,
  `peso` DOUBLE NOT NULL,
  `usuario_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en fisico
DELIMITER //
CREATE TRIGGER auditoria_fisico_insert
AFTER INSERT ON fisico
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_fisico (id, accion, altura, peso, usuario_id)
  VALUES (NEW.id, 'INSERT', NEW.altura, NEW.peso, NEW.usuario_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en fisico
DELIMITER //
CREATE TRIGGER auditoria_fisico_update
AFTER UPDATE ON fisico
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_fisico (id, accion, altura, peso, usuario_id)
  VALUES (NEW.id, 'UPDATE', NEW.altura, NEW.peso, NEW.usuario_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en fisico
DELIMITER //
CREATE TRIGGER auditoria_fisico_delete
AFTER DELETE ON fisico
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_fisico (id, accion, altura, peso, usuario_id)
  VALUES (OLD.id, 'DELETE', OLD.altura, OLD.peso, OLD.usuario_id);
END;
//
DELIMITER ;

-- === Tipo de Membresia ===
-- Modificar tabla de auditoría para tipo_membresia
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_tipo_membresia` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(30) NOT NULL,
  `descripcion` VARCHAR(300) DEFAULT '',
  `precio` DECIMAL(6,2) NOT NULL,
  `duracion` INT NOT NULL,
  `tipo_duracion` ENUM('hour', 'day', 'month', 'year') NOT NULL,
  `clase_id` INT NOT NULL,
  `administrador_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en tipo_membresia
DELIMITER //
CREATE TRIGGER auditoria_tipo_membresia_insert
AFTER INSERT ON tipo_membresia
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_tipo_membresia (id, accion, nombre, descripcion, precio, duracion, tipo_duracion, clase_id, administrador_id)
  VALUES (NEW.id, 'INSERT', NEW.nombre, NEW.descripcion, NEW.precio, NEW.duracion, NEW.tipo_duracion, NEW.clase_id, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en tipo_membresia
DELIMITER //
CREATE TRIGGER auditoria_tipo_membresia_update
AFTER UPDATE ON tipo_membresia
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_tipo_membresia (id, accion, nombre, descripcion, precio, duracion, tipo_duracion, clase_id, administrador_id)
  VALUES (NEW.id, 'UPDATE', NEW.nombre, NEW.descripcion, NEW.precio, NEW.duracion, NEW.tipo_duracion, NEW.clase_id, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en tipo_membresia
DELIMITER //
CREATE TRIGGER auditoria_tipo_membresia_delete
AFTER DELETE ON tipo_membresia
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_tipo_membresia (id, accion, nombre, descripcion, precio, duracion, tipo_duracion, clase_id, administrador_id)
  VALUES (OLD.id, 'DELETE', OLD.nombre, OLD.descripcion, OLD.precio, OLD.duracion, OLD.tipo_duracion, OLD.clase_id, OLD.administrador_id);
END;
//
DELIMITER ;

-- === Membresia ===
-- Modificar tabla de auditoría para membresia
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_membresia` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_inicio` DATETIME NOT NULL,
  `fecha_fin` DATETIME NULL,
  `activo` TINYINT NOT NULL,
  `usuario_id` INT NOT NULL,
  `administrador_id` INT NOT NULL,
  `tipo_membresia_id` INT NOT NULL,
  `factura_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en membresia
DELIMITER //
CREATE TRIGGER auditoria_membresia_insert
AFTER INSERT ON membresia
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_membresia (id, accion, fecha_inicio, fecha_fin, activo, usuario_id, administrador_id, tipo_membresia_id, factura_id)
  VALUES (NEW.id, 'INSERT', NEW.fecha_inicio, NEW.fecha_fin, NEW.activo, NEW.usuario_id, NEW.administrador_id, NEW.tipo_membresia_id, NEW.factura_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en membresia
DELIMITER //
CREATE TRIGGER auditoria_membresia_update
AFTER UPDATE ON membresia
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_membresia (id, accion, fecha_inicio, fecha_fin, activo, usuario_id, administrador_id, tipo_membresia_id, factura_id)
  VALUES (NEW.id, 'UPDATE', NEW.fecha_inicio, NEW.fecha_fin, NEW.activo, NEW.usuario_id, NEW.administrador_id, NEW.tipo_membresia_id, NEW.factura_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en membresia
DELIMITER //
CREATE TRIGGER auditoria_membresia_delete
AFTER DELETE ON membresia
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_membresia (id, accion, fecha_inicio, fecha_fin, activo, usuario_id, administrador_id, tipo_membresia_id, factura_id)
  VALUES (OLD.id, 'DELETE', OLD.fecha_inicio, OLD.fecha_fin, OLD.activo, OLD.usuario_id, OLD.administrador_id, OLD.tipo_membresia_id, OLD.factura_id);
END;
//
DELIMITER ;

-- === Recuperacion Cuenta ===
-- Modificar tabla de auditoría para recuperacion_cuenta
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_recuperacion_cuenta` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre_amigo` VARCHAR(100) COLLATE 'utf8mb3_unicode_ci' NULL,
  `nombre_mascota` VARCHAR(100) COLLATE 'utf8mb3_unicode_ci' NULL,
  `color_favorito` VARCHAR(100) COLLATE 'utf8mb3_unicode_ci' NULL,
  `administrador_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en recuperacion_cuenta
DELIMITER //
CREATE TRIGGER auditoria_recuperacion_cuenta_insert
AFTER INSERT ON recuperacion_cuenta
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_recuperacion_cuenta (id, accion, nombre_amigo, nombre_mascota, color_favorito, administrador_id)
  VALUES (NEW.id, 'INSERT', NEW.nombre_amigo, NEW.nombre_mascota, NEW.color_favorito, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en recuperacion_cuenta
DELIMITER //
CREATE TRIGGER auditoria_recuperacion_cuenta_update
AFTER UPDATE ON recuperacion_cuenta
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_recuperacion_cuenta (id, accion, nombre_amigo, nombre_mascota, color_favorito, administrador_id)
  VALUES (NEW.id, 'UPDATE', NEW.nombre_amigo, NEW.nombre_mascota, NEW.color_favorito, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en recuperacion_cuenta
DELIMITER //
CREATE TRIGGER auditoria_recuperacion_cuenta_delete
AFTER DELETE ON recuperacion_cuenta
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_recuperacion_cuenta (id, accion, nombre_amigo, nombre_mascota, color_favorito, administrador_id)
  VALUES (OLD.id, 'DELETE', OLD.nombre_amigo, OLD.nombre_mascota, OLD.color_favorito, OLD.administrador_id);
END;
//
DELIMITER ;

-- === Factura ===
-- Modificar tabla de auditoría para factura
CREATE TABLE IF NOT EXISTS `bdd_gym`.`auditoria_factura` (
  `id` INT NOT NULL,
  `accion` ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `numero_factura` VARCHAR(10) DEFAULT NULL,
  `descuento_porcentaje` DOUBLE DEFAULT 0.0,
  `descuento` DECIMAL(6,2) DEFAULT 0.0,
  `subtotal` DECIMAL(6,2) DEFAULT NULL,
  `iva` DECIMAL(6,2) DEFAULT NULL,
  `total` DECIMAL(6,2) DEFAULT NULL,
  `forma_pago` ENUM('efectivo', 'transferencia') DEFAULT 'efectivo',
  `fecha_factura` DATETIME NOT NULL,
  `establecimiento` VARCHAR(3) DEFAULT '001',
  `punto_emision` VARCHAR(3) DEFAULT '001',
  `usuario_id` INT DEFAULT NULL,
  `administrador_id` INT NOT NULL
) ENGINE = InnoDB;

-- Trigger para auditoría INSERT en factura
DELIMITER //
CREATE TRIGGER auditoria_factura_insert
AFTER INSERT ON factura
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_factura (id, accion, numero_factura, descuento_porcentaje, descuento, subtotal, iva, total, forma_pago, fecha_factura, establecimiento, punto_emision, usuario_id, administrador_id)
  VALUES (NEW.id, 'INSERT', NEW.numero_factura, NEW.descuento_porcentaje, NEW.descuento, NEW.subtotal, NEW.iva, NEW.total, NEW.forma_pago, NEW.fecha, NEW.establecimiento, NEW.punto_emision, NEW.usuario_id, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría UPDATE en factura
DELIMITER //
CREATE TRIGGER auditoria_factura_update
AFTER UPDATE ON factura
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_factura (id, accion, numero_factura, descuento_porcentaje, descuento, subtotal, iva, total, forma_pago, fecha_factura, establecimiento, punto_emision, usuario_id, administrador_id)
  VALUES (NEW.id, 'UPDATE', NEW.numero_factura, NEW.descuento_porcentaje, NEW.descuento, NEW.subtotal, NEW.iva, NEW.total, NEW.forma_pago, NEW.fecha, NEW.establecimiento, NEW.punto_emision, NEW.usuario_id, NEW.administrador_id);
END;
//
DELIMITER ;

-- Trigger para auditoría DELETE en factura
DELIMITER //
CREATE TRIGGER auditoria_factura_delete
AFTER DELETE ON factura
FOR EACH ROW
BEGIN
  INSERT INTO auditoria_factura (id, accion, numero_factura, descuento_porcentaje, descuento, subtotal, iva, total, forma_pago, fecha_factura, establecimiento, punto_emision, usuario_id, administrador_id)
  VALUES (OLD.id, 'DELETE', OLD.numero_factura, OLD.descuento_porcentaje, OLD.descuento, OLD.subtotal, OLD.iva, OLD.total, OLD.forma_pago, OLD.fecha, OLD.establecimiento, OLD.punto_emision, OLD.usuario_id, OLD.administrador_id);
END;
//
DELIMITER ;

-- Funcion maximo de factura
DELIMITER //
DROP FUNCTION IF EXISTS maxIdFactura//
CREATE FUNCTION maxIdFactura()
RETURNS INT
DETERMINISTIC
BEGIN
  DECLARE max_id INT;
  SELECT MAX(id) INTO max_id FROM factura;
  RETURN max_id;
END //
DELIMITER ;

-- Datos de la Base de Datos
-- Insertar un administrador
INSERT INTO administrador (nombre, apellido, email, cedula, password, password_salt, sesion_iniciada, super_admin, clave, direccion)
VALUES ('NombreAdmin', 'ApellidoAdmin', 'wacho@gmail.com', '123456789', '123456789', 'random', 0, 1, 'claveadmin', 'DirecciónAdmin');

-- Insertar un entrenador
INSERT INTO entrenador (nombre, apellido, sexo, correo, telefono, cedula, administrador_id)
VALUES ('NombreEntrenador', 'ApellidoEntrenador', 'Masculino', 'entrenador@example.com', '123456789', '987654321', 1);

-- Insertar una clase
INSERT INTO clase (clase, descripcion, entrenador_id, administrador_id)
VALUES ('Clase de Ejemplo', 'Descripción de la clase', 1, 1);

-- Insertar una cuenta
INSERT INTO cuenta (nombre_empresa, administrador_id)
VALUES ('Mi Empresa', 1);

-- Insertar recuperacion cuenta
INSERT INTO recuperacion_cuenta(nombre_amigo, nombre_mascota, color_favorito, administrador_id) 
VALUES("Nombre Amigo", "Nombre mascota", "Verde", 1);

-- Insertar un usuario
INSERT INTO usuario (nombre, apellido, fecha_nacimiento, sexo, email, cedula, direccion, telefono, administrador_id)
VALUES ('NombreUsuario', 'ApellidoUsuario', '1990-01-01', 'Masculino', 'usuario@example.com', '123456789', 'DirecciónUsuario', '987654321', 1);

-- Insertar un físico
INSERT INTO fisico (altura, peso, usuario_id)
VALUES (170.5, 70.0, 1);

-- Insertar un tipo de membresía
INSERT INTO tipo_membresia (nombre, descripcion, precio, duracion, tipo_duracion, clase_id, administrador_id)
VALUES ('Membresía Mensual', 'Descripción de la membresía', 50.00, 30, 'day', 1, 1);

-- Insertar iva por defecto
INSERT INTO iva(iva, administrador_id) values(12, 1);

-- Insertar una factura
CALL insertarFactura(1);

-- Insertar una membresía
-- call insertarMembresia(1,1,1,1);

select iva from iva where id = (select obtenerIdIva(1));
 











