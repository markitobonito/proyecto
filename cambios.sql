ALTER TABLE `mydb`.`Usuarios`
    ADD COLUMN `telefono` VARCHAR(20) NULL,
    ADD COLUMN `fechaCreacion` DATETIME DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE IF NOT EXISTS `mydb`.`Actividad` (
                                                  `idActividad` INT NOT NULL AUTO_INCREMENT,
                                                  `idUsuario` INT NOT NULL,
                                                  `descripcion` VARCHAR(100) NOT NULL,
                                                  `detalle` TEXT NULL,
                                                  `fecha` DATETIME DEFAULT CURRENT_TIMESTAMP,
                                                  PRIMARY KEY (`idActividad`),
                                                  INDEX `fk_Actividad_Usuarios_idx` (`idUsuario` ASC),
                                                  CONSTRAINT `fk_Actividad_Usuarios`
                                                      FOREIGN KEY (`idUsuario`)
                                                          REFERENCES `mydb`.`Usuarios` (`idUsuarios`)
                                                          ON DELETE CASCADE
                                                          ON UPDATE CASCADE
)
    ENGINE = InnoDB;

ALTER TABLE `mydb`.`Espacio`
    ADD COLUMN `observaciones` VARCHAR(300) NULL AFTER `foto3`;
