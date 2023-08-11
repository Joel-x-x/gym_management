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
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`, `administrador_id`),
  UNIQUE INDEX `correo_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_administrador_id_idx` (`administrador_id` ASC) VISIBLE,
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
  `anticipacion` INT NULL DEFAULT 0,
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
  `factura_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_membresia_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_administrador_id_membresia_idx` (`administrador_id` ASC) VISIBLE,
  INDEX `fk_tipo_membresia_id_idx` (`tipo_membresia_id` ASC) VISIBLE,
  INDEX `fk_factura_id_idx` (`factura_id` ASC) VISIBLE,
  CONSTRAINT `fk_administrador_id_membresia`
    FOREIGN KEY (`administrador_id`)
    REFERENCES `bdd_gym`.`administrador` (`id`),
  CONSTRAINT `fk_membresia_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdd_gym`.`usuario` (`id`),
  CONSTRAINT `fk_membresia_factura_id`
    FOREIGN KEY (`factura_id`)
    REFERENCES `bdd_gym`.`factura` (`id`),
  CONSTRAINT `fk_tipo_membresia_id`
    FOREIGN KEY (`tipo_membresia_id`)
    REFERENCES `bdd_gym`.`tipo_membresia` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  PRIMARY KEY (`id`, `usuario_id`),
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
  `subtotal` DECIMAL(6,2) DEFAULT NULL,
  `iva` DECIMAL(6,2) DEFAULT NULL,
  `total` DECIMAL(6,2) DEFAULT NULL,
  `forma_pago` ENUM('efectivo', 'transferencia') DEFAULT 'efectivo',
  `fecha` DATETIME NOT NULL DEFAULT current_timestamp,
  `establecimiento` VARCHAR(3) DEFAULT '001',
  `punto_emision` VARCHAR(3) DEFAULT '001',
  `usuario_id` INT DEFAULT NULL,
  `administrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_id_establecimiento_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_administrador_id_idx` (`administrador_id` ASC) VISIBLE,
  UNIQUE INDEX `numero_factura_UNIQUE` (`numero_factura` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_id_factura`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `bdd_gym`.`usuario` (`id`)
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
    IN usuarioId INT,
    IN administradorId INT
)
BEGIN
    DECLARE numeroFactura VARCHAR(10);
    
    INSERT INTO factura (usuario_id, administrador_id) VALUES (usuarioId, administradorId);
    SET numeroFactura = LPAD(LAST_INSERT_ID(), 9, '0');
    
    UPDATE factura SET numero_factura = numeroFactura WHERE id = LAST_INSERT_ID();
END;
//
DELIMITER ;

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
    
    set tipo_duracion = ( select obtener_tipo_duracion(tipoMembresiaId) );
    set duracion = ( select obtener_duracion(tipoMembresiaId) );
    
	set fechaFin = (
		select calcularFechaFin(duracion, tipo_duracion)
    );
    
    -- Insertar membresia
    insert into membresia(
		fecha_fin, activo, usuario_id,
		administrador_id, tipo_membresia_id, factura_id
    )
    values(
		fechaFin, 1, usuarioId,
        administradorId, tipoMembresiaId, facturaId
    );

end//
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


-- Datos de la Base de Datos
-- Insertar un administrador
INSERT INTO administrador (nombre, apellido, email, cedula, password, sesion_iniciada, super_admin, clave, direccion)
VALUES ('NombreAdmin', 'ApellidoAdmin', 'admin@example.com', '123456789', 'contraseñasegura', 0, 1, 'claveadmin', 'DirecciónAdmin');

-- Insertar un entrenador
INSERT INTO entrenador (nombre, apellido, sexo, correo, telefono, cedula, administrador_id)
VALUES ('NombreEntrenador', 'ApellidoEntrenador', 'Masculino', 'entrenador@example.com', '123456789', '987654321', 1);

-- Insertar una clase
INSERT INTO clase (clase, descripcion, entrenador_id, administrador_id)
VALUES ('Clase de Ejemplo', 'Descripción de la clase', 1, 1);

-- Insertar una cuenta
INSERT INTO cuenta (nombre_empresa, administrador_id)
VALUES ('Mi Empresa', 1);

-- Insertar un usuario
INSERT INTO usuario (nombre, apellido, fecha_nacimiento, sexo, email, cedula, direccion, telefono, administrador_id)
VALUES ('NombreUsuario', 'ApellidoUsuario', '1990-01-01', 'Masculino', 'usuario@example.com', '123456789', 'DirecciónUsuario', '987654321', 1);

-- Insertar un físico
INSERT INTO fisico (altura, peso, usuario_id)
VALUES (170.5, 70.0, 1);

-- Insertar un tipo de membresía
INSERT INTO tipo_membresia (nombre, descripcion, anticipacion, precio, duracion, tipo_duracion, clase_id, administrador_id)
VALUES ('Membresía Mensual', 'Descripción de la membresía', 5, 50.00, 30, 'day', 1, 1);

update tipo_membresia set duracion = 0 where id = 1;

-- Insertar una factura
CALL insertarFactura(1, 1);

-- Insertar una membresía
 call insertarMembresia(1,1,1,1);

 select * from membresia;

-- select * from factura;
-- delete from factura where id between 1 and 6;

-- select * from membresia;

-- drop schema bdd_gym;




