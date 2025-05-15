-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `rol` VARCHAR(20) NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EstadoUsu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EstadoUsu` (
  `idEstado` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(20) NULL,
  PRIMARY KEY (`idEstado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuarios` (
  `idUsuarios` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `dni` INT NULL,
  `correo` VARCHAR(100) NULL,
  `contrasena` VARCHAR(45) NULL,
  `telefono` VARCHAR(20) NULL,
  `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `rol` INT NOT NULL,
  `estado` INT NOT NULL,
  PRIMARY KEY (`idUsuarios`),
  INDEX `fk_Usuarios_Rol_idx` (`rol` ASC) VISIBLE,
  INDEX `fk_Usuarios_Estado1_idx` (`estado` ASC) VISIBLE,
  CONSTRAINT `fk_Usuarios_Rol`
    FOREIGN KEY (`rol`)
    REFERENCES `mydb`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuarios_Estado1`
    FOREIGN KEY (`estado`)
    REFERENCES `mydb`.`EstadoUsu` (`idEstado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Lugar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Lugar` (
  `idLugar` INT NOT NULL AUTO_INCREMENT,
  `lugar` VARCHAR(45) NULL,
  PRIMARY KEY (`idLugar`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EstadoEspacio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EstadoEspacio` (
  `idEstadoEspacio` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(20) NULL,
  PRIMARY KEY (`idEstadoEspacio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Espacio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Espacio` (
  `idEspacio` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `idLugar` INT NOT NULL,
  `idEstadoEspacio` INT NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `costo` DOUBLE NULL,
  `foto1` LONGBLOB NULL,
  `foto2` LONGBLOB NULL,
  `foto3` LONGBLOB NULL,
  PRIMARY KEY (`idEspacio`),
  INDEX `fk_Cancha_Lugar1_idx` (`idLugar` ASC) VISIBLE,
  INDEX `fk_Cancha_EstadoCancha1_idx` (`idEstadoEspacio` ASC) VISIBLE,
  CONSTRAINT `fk_Cancha_Lugar1`
    FOREIGN KEY (`idLugar`)
    REFERENCES `mydb`.`Lugar` (`idLugar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cancha_EstadoCancha1`
    FOREIGN KEY (`idEstadoEspacio`)
    REFERENCES `mydb`.`EstadoEspacio` (`idEstadoEspacio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EstadoReserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EstadoReserva` (
  `idEstadoReserva` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(20) NULL,
  PRIMARY KEY (`idEstadoReserva`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reserva` (
  `idReserva` INT NOT NULL AUTO_INCREMENT,
  `horaInicio` TIME NULL,
  `horaFin` TIME NULL,
  `fecha` DATE NULL,
  `coordinador` INT NOT NULL,
  `costo` DOUBLE NULL,
  `vecino` INT NOT NULL,
  `estado` INT NOT NULL,
  `espacio` INT NOT NULL,
  `captura` BLOB NULL,
  `momentoReserva` DATETIME NULL,
  PRIMARY KEY (`idReserva`),
  INDEX `fk_Reserva_Usuarios1_idx` (`vecino` ASC) VISIBLE,
  INDEX `fk_Reserva_Usuarios2_idx` (`coordinador` ASC) VISIBLE,
  INDEX `fk_Reserva_EstadoReserva1_idx` (`estado` ASC) VISIBLE,
  INDEX `fk_Reserva_Cancha1_idx` (`espacio` ASC) VISIBLE,
  CONSTRAINT `fk_Reserva_Usuarios1`
    FOREIGN KEY (`vecino`)
    REFERENCES `mydb`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Usuarios2`
    FOREIGN KEY (`coordinador`)
    REFERENCES `mydb`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_EstadoReserva1`
    FOREIGN KEY (`estado`)
    REFERENCES `mydb`.`EstadoReserva` (`idEstadoReserva`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Cancha1`
    FOREIGN KEY (`espacio`)
    REFERENCES `mydb`.`Espacio` (`idEspacio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EstadoGeo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EstadoGeo` (
  `idEstadoGeo` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(20) NULL,
  PRIMARY KEY (`idEstadoGeo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Geolocalizacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Geolocalizacion` (
  `idGeolocalizacion` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `horaInicio` TIME NULL,
  `horaFin` TIME NULL,
  `coordinador` INT NOT NULL,
  `espacio` INT NOT NULL,
  `lugarExacto` VARCHAR(100) NULL,
  `observacion` VARCHAR(300) NULL,
  `estado` INT NOT NULL,
  PRIMARY KEY (`idGeolocalizacion`),
  INDEX `fk_Geolocalizacion_Usuarios1_idx` (`coordinador` ASC) VISIBLE,
  INDEX `fk_Geolocalizacion_Cancha1_idx` (`espacio` ASC) VISIBLE,
  INDEX `fk_Geolocalizacion_Estado1_idx` (`estado` ASC) VISIBLE,
  CONSTRAINT `fk_Geolocalizacion_Usuarios1`
    FOREIGN KEY (`coordinador`)
    REFERENCES `mydb`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Geolocalizacion_Cancha1`
    FOREIGN KEY (`espacio`)
    REFERENCES `mydb`.`Espacio` (`idEspacio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Geolocalizacion_Estado1`
    FOREIGN KEY (`estado`)
    REFERENCES `mydb`.`EstadoGeo` (`idEstadoGeo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EstadoMensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EstadoMensaje` (
  `idEstadoMensaje` INT NOT NULL AUTO_INCREMENT,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`idEstadoMensaje`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Mensaje` (
  `idMensaje` INT NOT NULL,
  `horaEnvio` TIMESTAMP NULL,
  `transmisor` INT NOT NULL,
  `receptor` INT NOT NULL,
  `mensaje` VARCHAR(500) NULL,
  `estado` INT NOT NULL,
  PRIMARY KEY (`idMensaje`),
  INDEX `fk_Mensaje_Usuarios1_idx` (`transmisor` ASC) VISIBLE,
  INDEX `fk_Mensaje_Usuarios2_idx` (`receptor` ASC) VISIBLE,
  INDEX `fk_Mensaje_EstadoMensaje1_idx` (`estado` ASC) VISIBLE,
  CONSTRAINT `fk_Mensaje_Usuarios1`
    FOREIGN KEY (`transmisor`)
    REFERENCES `mydb`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mensaje_Usuarios2`
    FOREIGN KEY (`receptor`)
    REFERENCES `mydb`.`Usuarios` (`idUsuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mensaje_EstadoMensaje1`
    FOREIGN KEY (`estado`)
    REFERENCES `mydb`.`EstadoMensaje` (`idEstadoMensaje`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
