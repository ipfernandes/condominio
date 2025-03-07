-- MySQL Script generated by MySQL Workbench
-- Sat Sep 28 16:57:24 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema condominio
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `condominio` ;

-- -----------------------------------------------------
-- Schema condominio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `condominio` DEFAULT CHARACTER SET utf8 ;
USE `condominio` ;

-- -----------------------------------------------------
-- Table `condominio`.`condominio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `condominio`.`condominio` ;

CREATE TABLE IF NOT EXISTS `condominio`.`condominio` (
  `identificador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(120) NULL,
  `cnpj` VARCHAR(14) NULL,
  `tipo` VARCHAR(12) NULL,
  `criadoEm` TIMESTAMP NULL,
  PRIMARY KEY (`identificador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `condominio`.`Bloco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `condominio`.`Bloco` ;

CREATE TABLE IF NOT EXISTS `condominio`.`Bloco` (
  `identificador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `condominio_identificador` INT NOT NULL,
  PRIMARY KEY (`identificador`),
  INDEX `fk_Bloco_condominio_idx` (`condominio_identificador` ASC) VISIBLE,
  CONSTRAINT `fk_Bloco_condominio`
    FOREIGN KEY (`condominio_identificador`)
    REFERENCES `condominio`.`condominio` (`identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `condominio`.`Unidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `condominio`.`Unidade` ;

CREATE TABLE IF NOT EXISTS `condominio`.`Unidade` (
  `identificador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `metragem_quadrada` DOUBLE NOT NULL,
  `Bloco_identificador` INT NOT NULL,
  PRIMARY KEY (`identificador`),
  INDEX `fk_Unidade_Bloco1_idx` (`Bloco_identificador` ASC) VISIBLE,
  CONSTRAINT `fk_Unidade_Bloco1`
    FOREIGN KEY (`Bloco_identificador`)
    REFERENCES `condominio`.`Bloco` (`identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `condominio`.`Area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `condominio`.`Area` ;

CREATE TABLE IF NOT EXISTS `condominio`.`Area` (
  `identificador` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(80) NOT NULL,
  `metragem_quadrada` DOUBLE NOT NULL,
  `tipo` VARCHAR(12) NOT NULL,
  `condominio_identificador` INT NULL,
  `Bloco_identificador` INT NULL,
  PRIMARY KEY (`identificador`),
  INDEX `fk_Area_condominio1_idx` (`condominio_identificador` ASC) VISIBLE,
  INDEX `fk_Area_Bloco1_idx` (`Bloco_identificador` ASC) VISIBLE,
  CONSTRAINT `fk_Area_condominio1`
    FOREIGN KEY (`condominio_identificador`)
    REFERENCES `condominio`.`condominio` (`identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Area_Bloco1`
    FOREIGN KEY (`Bloco_identificador`)
    REFERENCES `condominio`.`Bloco` (`identificador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
