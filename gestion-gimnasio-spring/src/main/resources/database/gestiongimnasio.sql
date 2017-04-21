-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 19-04-2017 a las 11:16:21
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `gestiongimnasio`
--
DROP DATABASE `gestiongimnasio`;
CREATE DATABASE `gestiongimnasio` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `gestiongimnasio`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `buscarUsuarioNombre`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarUsuarioNombre`(IN pnombre VARCHAR(50))
BEGIN
	Select 
    u.nombre,
    u.apellidos,
    u.codigo,
    u.email
    FROM usuario as u
    WHERE nombre LIKE CONCAT('%',LOWER(pnombre),'%');
END$$

DROP PROCEDURE IF EXISTS `ejercicioActividad`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ejercicioActividad`(IN pactividad VARCHAR(250))
BEGIN
	SELECT
		ej.codigo,
        ej.actividad,
        ej.grupomuscular,
        ej.maquina,
        ej.descripcion
        
	FROM ejercicio as ej
    WHERE ej.actividad = pactividad and activo = true;
END$$

DROP PROCEDURE IF EXISTS `ejercicioCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ejercicioCreate`(IN pactividad VARCHAR(100),IN pgrupomuscular VARCHAR(150), IN pmaquina VARCHAR(250), IN pdescripcion VARCHAR(300), OUT pcodigo INT)
BEGIN
	INSERT INTO ejercicio
    (actividad,
    grupomuscular,
    maquina,
    descripcion)
    
    VALUES
    (LOWER(pactividad),
    LOWER(pgrupomuscular),
    LOWER(pmaquina),
    LOWER(pdescripcion));
    
    SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `ejercicioDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ejercicioDelete`(IN pcodigo INT)
BEGIN
	UPDATE ejercicio SET activo = false
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `ejerciciogetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ejerciciogetAll`()
BEGIN
	SELECT
    ej.codigo,
    ej.actividad,
    ej.grupomuscular,
    ej.maquina,
    ej.descripcion,
    ej.activo
    
    FROM ejercicio as ej
    
    WHERE ej.activo = true;
END$$

DROP PROCEDURE IF EXISTS `ejerciciogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ejerciciogetById`(IN pcodigo INT)
BEGIN
	SELECT 
    ej.codigo,
    ej.actividad,
    ej.grupomuscular,
    ej.maquina,
    ej.descripcion
    
    FROM ejercicio as ej
    WHERE ej.codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `ejercicioUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ejercicioUpdate`(IN pactividad VARCHAR(100), IN pgrupomuscular VARCHAR(150), IN pmaquina VARCHAR(250), IN pdescripcion VARCHAR(300), IN pcodigo INT)
BEGIN
	UPDATE ejercicio
    SET
    actividad = LOWER(pactividad),
    grupomuscular = LOWER(pgrupomuscular),
    maquina = LOWER(pmaquina),
    descripcion = LOWER(pdescripcion)
    
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoCreate`(IN pfecha date, IN pusuario_codigo INT, OUT pcodigo INT)
BEGIN
	INSERT INTO entrenamiento
    (fecha,
    usuario_codigo)
    
    VALUES
    (pfecha,
    pusuario_codigo);
    
    SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `entrenamientoDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoDelete`(IN `pcodigo`INT)
BEGIN
	UPDATE entrenamiento SET activo = false
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoEjercicioCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoEjercicioCreate`(IN pcod_entrenamiento INT, IN pcod_ejercicio INT, IN pseries INT, IN prepeticiones INT, IN ppeso double, in ptiempo INT, OUT pcodigo int)
BEGIN
	INSERT INTO entrenamientoejercicio(
    cod_entrenamiento,
    cod_ejercicio,
	series,
    repeticiones,
    peso,
    tiempo,
    activo)
    
    VALUES
    (pcod_entrenamiento,
    pcod_ejercicio,
    pseries,
    prepeticiones,
    ppeso,
    ptiempo,
    true);
    
    SET pcodigo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `entrenamientoEjercicioDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoEjercicioDelete`(IN pcodigo INT)
BEGIN
	UPDATE entrenamientoejercicio SET activo = false
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoEjerciciogetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoEjerciciogetAll`()
BEGIN
	SELECT
    ee.codigo,
    ee.series,
    ee.repeticiones,
    ee.peso,
    ee.tiempo,
    ee.activo,
    
    en.fecha,
    
    ej.actividad,
    ej.grupomuscular,
    ej.maquina,
    ej.descripcion
		
    FROM entrenamientoejercicio as ee
		LEFT JOIN entrenamiento as en ON ee.cod_entrenamiento = en.codigo
        LEFT JOIN ejercicio as ej ON ee.cod_ejercicio = ej.codigo
    
    WHERE ee.activo = true;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoEjerciciogetByEntrenamiento`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoEjerciciogetByEntrenamiento`(IN pcodigo INT)
BEGIN
	SELECT 
		ej.actividad,
        ej.grupomuscular,
        ej.maquina,
        ej.descripcion,
        
        ee.codigo,
		ee.series,
        ee.repeticiones,
        ee.peso,
        ee.tiempo
        
    FROM entrenamientoejercicio as ee
		LEFT JOIN ejercicio as ej ON ej.codigo = ee.cod_ejercicio
    WHERE ee.cod_entrenamiento = pcodigo and ee.activo = true;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoEjerciciogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoEjerciciogetById`(IN pcodigo INT)
BEGIN
	SELECT
    ee.codigo as cod_entrenamientoEjercicio,
    ee.series,
    ee.repeticiones,
    ee.peso,
    ee.tiempo,
    ee.activo as eeactivo,
    
    en.codigo as cod_entrenamiento,
    en.fecha,
    en.activo as enactivo,
    
    u.codigo as usuario_codigo,
    u.nombre,
    u.apellidos,
    u.user,
    u.pass,
    u.email,
    u.activo as uactivo,
    
    ej.codigo as cod_ejercicio,
    ej.actividad,
    ej.grupomuscular,
    ej.maquina,
    ej.descripcion,
    ej.activo as ejactivo
		
    FROM entrenamientoejercicio as ee
		LEFT JOIN entrenamiento as en ON ee.cod_entrenamiento = en.codigo
        LEFT JOIN ejercicio as ej ON ee.cod_ejercicio = ej.codigo
        LEFT JOIN usuario as u ON en.usuario_codigo = u.codigo
    
    WHERE pcodigo = ee.codigo and ee.activo = true;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoEjercicioUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoEjercicioUpdate`(IN pseries int, In prepeticiones int,in ppeso double, in ptiempo int,IN pactividad VARCHAR(100), IN pgrupomuscular VARCHAR(150), IN pmaquina VARCHAR(250), IN pdescripcion VARCHAR(300), IN pcodigo int)
BEGIN

	DECLARE codigoEj  INT;  
    
	UPDATE entrenamientoejercicio
    SET
    series = pseries,
    repeticiones = prepeticiones,
    peso = ppeso,
    tiempo = ptiempo
    
    WHERE codigo = pcodigo;
    
    SELECT 
		ee.cod_ejercicio
    FROM entrenamientoejercicio as ee
    WHERE codigo = pcodigo;
    
    call ejercicioUpdate(LOWER(pactividad), LOWER(pgrupomuscular), LOWER(pmaquina), LOWER(pdescripcion), cod_ejercicio);
END$$

DROP PROCEDURE IF EXISTS `entrenamientogetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientogetAll`()
BEGIN
	SELECT
    en.codigo as codigo,
    en.fecha as fecha,
    en.usuario_codigo as usuario_codigo
    
    FROM entrenamiento as en
    
    WHERE en.activo = true;
END$$

DROP PROCEDURE IF EXISTS `entrenamientogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientogetById`(IN `pcodigo`INT)
BEGIN
	SELECT 
    en.codigo as codigo,
    en.fecha as fecha,
    en.usuario_codigo as usuario_codigo
    
    FROM entrenamiento as en

    WHERE pcodigo = en.codigo;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoInforme`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoInforme`(IN pcodigo INT)
BEGIN
	SELECT
    en.codigo as codigoEntrenamiento,
    en.fecha,
    en.usuario_codigo,
    en.activo as enactivo,
    
    ej.codigo as codigoEjercicio,
    ej.actividad,
    ej.grupomuscular,
    ej.maquina,
    ej.descripcion,
    ej.activo as ejactivo,
    
    ee.codigo as codigoEntrenamientoEjercicio,
    ee.series,
    ee.repeticiones,
    ee.peso,
    ee.tiempo,
    ee.activo as eeactivo
		
    FROM entrenamiento as en
		LEFT JOIN entrenamientoejercicio as ee ON en.codigo = ee.cod_entrenamiento
        LEFT JOIN ejercicio as ej ON ej.codigo = ee.cod_ejercicio
    
    WHERE en.codigo = pcodigo and ee.activo = true;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoUpdate`(IN pfecha date, IN pcodigo INT)
BEGIN
	UPDATE entrenamiento
    SET
    fecha = pfecha
    
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `entrenamientoUsuario`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `entrenamientoUsuario`(IN pcodigo INT)
BEGIN
	SELECT
		en.codigo,
		en.fecha,
        
        u.nombre
       
        
    FROM entrenamiento as en
    INNER JOIN usuario as u ON en.usuario_codigo = u.codigo
    
    WHERE en.usuario_codigo = pcodigo and en.activo = true;
END$$

DROP PROCEDURE IF EXISTS `usuarioCreate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usuarioCreate`(IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(250), IN `puser` VARCHAR(150), IN `ppass` VARCHAR(150), IN `pemail` VARCHAR(150), OUT `pcodigo` INT)
BEGIN
	INSERT INTO usuario(
	nombre,
    apellidos,
    user,
    pass,
    email,
    activo)
    
    VALUES
    (lower(pnombre),
    lower(papellidos),
    lower(puser),
    lower(ppass),
    lower(pemail),
    true);
    
    SET pcodigo = LAST_INSERT_ID();
    
END$$

DROP PROCEDURE IF EXISTS `usuarioDelete`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usuarioDelete`(IN pcodigo int)
BEGIN
	UPDATE usuario SET activo = false
    WHERE codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `usuariogetAll`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usuariogetAll`()
BEGIN
	SELECT 
    u.codigo as codigo,
    u.nombre as nombre,
    u.apellidos as apellidos,
    u.user as user,
    u.pass as pass,
    u.email as email,
    u.activo as activo
    
    FROM usuario as u
    
    WHERE activo = true;
END$$

DROP PROCEDURE IF EXISTS `usuariogetById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usuariogetById`(IN pcodigo int)
BEGIN
	SELECT 
		u.codigo as codigo,
        u.nombre as nombre,
        u.apellidos as apellidos,
        u.user as user,
        u.pass as pass,
        u.email as email,
        u.activo as activo
        
    FROM usuario as u
    
    WHERE u.codigo = pcodigo;
END$$

DROP PROCEDURE IF EXISTS `usuariogetByUser`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usuariogetByUser`(IN puser VARCHAR(150))
BEGIN
	SELECT 
		u.codigo as codigo,
        u.nombre as nombre,
        u.apellidos as apellidos,
        u.user as user,
        u.pass as pass,
        u.email as email,
        u.activo as activo
        
    FROM usuario as u
    
    WHERE u.user = puser;
END$$

DROP PROCEDURE IF EXISTS `usuarioUpdate`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `usuarioUpdate`(IN `pnombre` VARCHAR(50), IN `papellidos` VARCHAR(250), IN `puser` VARCHAR(150), IN `ppass` VARCHAR(150), IN `pemail` VARCHAR(150), IN `pcodigo` INT)
BEGIN
	UPDATE usuario
    SET
    nombre=lower(pnombre),
    apellidos=lower(papellidos),
    user=lower(puser),
    pass=lower(ppass),
    email=lower(pemail),
    activo = true
    
    WHERE codigo = pcodigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejercicio`
--

DROP TABLE IF EXISTS `ejercicio`;
CREATE TABLE IF NOT EXISTS `ejercicio` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `actividad` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `grupomuscular` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `maquina` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `descripcion` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `maquina_UNIQUE` (`maquina`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=24 ;

--
-- Volcado de datos para la tabla `ejercicio`
--

INSERT INTO `ejercicio` (`codigo`, `actividad`, `grupomuscular`, `maquina`, `descripcion`, `activo`) VALUES
(1, 'musculacion', 'pecho', 'press banca', 'inclinado', 1),
(2, 'musculacion', 'abdominales', 'abdominales', 'pelota 10kg', 1),
(3, 'musculacion', 'brazo', 'mancuerna', 'alterno', 1),
(4, 'musculacion', 'pierna', 'sentadillas', 'blabla', 1),
(5, 'musculacion', 'pecho', 'flexiones', 'espartanas', 1),
(8, 'cardio', 'completo', 'eliptica', 'prueba', 1),
(12, 'zumba', 'completo', 'ff', 'baile intenso', 1),
(13, 'boxeo', 'completo', 'saco', NULL, 1),
(14, 'pilates', 'completo', 'fitball', NULL, 1),
(15, 'crossfit', 'completo', 'fdf', NULL, 1),
(17, 'cardio', 'completo', 'correr', '', 1),
(18, 'cardio', 'completo', 'spinning', 'sala polivalente', 1),
(19, 'cardio', 'completo', 'step', '', 1),
(20, 'mantenimiento', 'completo', 'natacion', 'braza', 1),
(21, 'cardio', 'completo', 'nadar espalda', '', 1),
(22, 'cardio', 'completo', 'danza del vientre', '', 1),
(23, 'cardio', 'completo', 'remo', '', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenamiento`
--

DROP TABLE IF EXISTS `entrenamiento`;
CREATE TABLE IF NOT EXISTS `entrenamiento` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `usuario_codigo` int(11) NOT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`),
  KEY `fk_entrenamiento_usuario_codigo_idx` (`usuario_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Volcado de datos para la tabla `entrenamiento`
--

INSERT INTO `entrenamiento` (`codigo`, `fecha`, `usuario_codigo`, `activo`) VALUES
(1, '2017-04-17', 1, 1),
(2, '2016-03-12', 2, 1),
(3, '2016-03-12', 3, 1),
(4, '2017-04-16', 1, 1),
(5, '2017-04-01', 4, 1),
(8, '2017-03-21', 2, 1),
(9, '2017-03-21', 3, 1),
(11, '2017-04-17', 10, 1),
(12, '2017-04-17', 10, 0),
(13, '2017-04-17', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenamientoejercicio`
--

DROP TABLE IF EXISTS `entrenamientoejercicio`;
CREATE TABLE IF NOT EXISTS `entrenamientoejercicio` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_entrenamiento` int(11) NOT NULL,
  `cod_ejercicio` int(11) NOT NULL,
  `series` int(11) DEFAULT '0',
  `repeticiones` int(11) DEFAULT '0',
  `peso` double DEFAULT '0',
  `tiempo` int(11) DEFAULT '0',
  `activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod_entrenamiento`,`cod_ejercicio`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `fk_listaejercicios_ejercicio_codigo_idx` (`cod_ejercicio`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `entrenamientoejercicio`
--

INSERT INTO `entrenamientoejercicio` (`codigo`, `cod_entrenamiento`, `cod_ejercicio`, `series`, `repeticiones`, `peso`, `tiempo`, `activo`) VALUES
(1, 1, 1, 5, 15, 40, 5, 1),
(2, 1, 2, 5, 15, 2, 5, 1),
(3, 1, 3, 5, 15, 10, 5, 1),
(9, 2, 2, 12, 12, 12, 12, 1),
(4, 2, 3, 5, 15, 5, 0, 1),
(5, 2, 4, 5, 15, 0, 0, 1),
(6, 3, 1, 5, 15, 30, 0, 1),
(7, 3, 2, 5, 15, 0, 0, 1),
(8, 3, 8, 0, 0, 0, 65, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `user` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `pass` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `activo` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=23 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`codigo`, `nombre`, `apellidos`, `user`, `pass`, `email`, `activo`) VALUES
(1, 'jon ander', 'ochoa ruiz', 'jony', 'thor', 'jon@ipartek.com', 1),
(2, 'jorge', 'megadeth', 'jorge', 'mega', 'jorge@ipartek.com', 1),
(3, 'nora', 'gatitos', 'nora', 'miau', 'nora@ipartek.com', 1),
(4, 'santos', 'bateria', 'santos', 'bate', 'santos@ipartek.com', 1),
(5, 'laura', 'campa', 'lau', 'miniyo', 'lau@ipartek.com', 1),
(6, 'homer j', 'simpson', 'joyu', 'birra', 'homer@piruleta.com', 1),
(9, 'marge', 'bubie', 'marge', 'magie', 'marge@amadecasa.com', 0),
(10, 'bart', 'simpson', 'elbarto', 'caca', 'barto@bartanimacion.com', 1),
(14, 'eru', 'ochoa', 'eru', 'pichu', 'eru@gmail.com', 1),
(15, 'pepa', 'pig', 'pep', 'pig', 'pepa@', 0),
(16, 'bob', 'esponja', 'bob', 'gary', 'bob@esponaj.com', 1),
(17, 'calamardo', 'calamar', 'cal', 'mar', 'cal@mar.com', 1),
(18, 'thor', 'ochoa', 'thor', 'pajarito', 'thor@menta.com', 1),
(19, 'patricio', 'estrella', 'patry', 'paty', 'patry@fondodebiquini.com', 1),
(20, 'arenita', 'nuse', 'aren', 'ardilla', 'are@ardi.com', 0),
(22, 'arenita', 'arenita', 'arenil', 'arenita', 'aren@ita.com', 0);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entrenamiento`
--
ALTER TABLE `entrenamiento`
  ADD CONSTRAINT `fk_entrenamiento_usuario_codigo` FOREIGN KEY (`usuario_codigo`) REFERENCES `usuario` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `entrenamientoejercicio`
--
ALTER TABLE `entrenamientoejercicio`
  ADD CONSTRAINT `fk_listaejercicios_ejercicio_codigo` FOREIGN KEY (`cod_ejercicio`) REFERENCES `ejercicio` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_listaejercicios_entrenamiento_codigo` FOREIGN KEY (`cod_entrenamiento`) REFERENCES `entrenamiento` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
