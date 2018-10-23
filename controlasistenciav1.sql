-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-10-2018 a las 18:48:14
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `controlasistenciav1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE `asignatura` (
  `codigo` int(15) UNSIGNED NOT NULL,
  `nombre` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `docente` bigint(20) UNSIGNED NOT NULL,
  `nrohoras` int(5) UNSIGNED NOT NULL,
  `edificio` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `aula` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`codigo`, `nombre`, `docente`, `nrohoras`, `edificio`, `aula`) VALUES
(123456, 'Algebra L?neal', 951753654, 3, 'Alfa', '301');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura_estudiante`
--

CREATE TABLE `asignatura_estudiante` (
  `codigoAsignatura` int(15) UNSIGNED NOT NULL,
  `estudianteID` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

CREATE TABLE `asistencia` (
  `asistenciaID` int(20) UNSIGNED NOT NULL,
  `asignaturaID` int(15) UNSIGNED NOT NULL,
  `estudianteID` bigint(20) UNSIGNED NOT NULL,
  `fecha` date NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `tema` int(11) UNSIGNED NOT NULL,
  `dinamica` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `isAusente` tinyint(1) UNSIGNED NOT NULL DEFAULT '0',
  `estado` tinyint(1) UNSIGNED NOT NULL DEFAULT '0',
  `motivoInasistencia` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `observacion` varchar(535) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `cursoID` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`cursoID`, `nombre`) VALUES
(101, 'CDPROM');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docente`
--

CREATE TABLE `docente` (
  `docenteID` bigint(20) UNSIGNED NOT NULL,
  `tipoDocente` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `facultad` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `docente`
--

INSERT INTO `docente` (`docenteID`, `tipoDocente`, `facultad`) VALUES
(87874575, 'Catedra', 'Ciencias Navales'),
(951753654, 'catedra', 'Ciencias B?sicas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `estudianteID` bigint(20) UNSIGNED NOT NULL,
  `curso` int(11) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`estudianteID`, `curso`) VALUES
(10101010, 101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tema`
--

CREATE TABLE `tema` (
  `temaID` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `visto` tinyint(4) UNSIGNED NOT NULL DEFAULT '0',
  `asignatura` int(15) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `tema`
--

INSERT INTO `tema` (`temaID`, `nombre`, `visto`, `asignatura`) VALUES
(1, 'Matrices', 1, 123456);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `usuarioID` int(20) UNSIGNED NOT NULL,
  `identificacion` bigint(20) UNSIGNED NOT NULL,
  `contrasena` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellido` varchar(75) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usuarioID`, `identificacion`, `contrasena`, `tipo`, `nombre`, `apellido`, `email`) VALUES
(1, 1143379027, 'admin', 'admin', 'Marco', 'Montalvo Ariza', 'marco.montalvo@unisinu.edu.co'),
(2, 10101010, 'estudiante', 'estudiante', 'Pepito', 'Perez Perez', 'peperez@enap.edu.co'),
(3, 951753654, 'docente', 'docente', 'Profesor', 'Jirafales', 'jirafales@enap.edu.co'),
(6, 94102918503, 'comandantedecurso', 'cdecurso', 'Ash', 'Ketchump', 'ashk@enap.edu.co'),
(7, 87874575, 'prueba', 'docente', 'Prueba', 'Docente', 'pb@enap.edu.co');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `asignatura_docente` (`docente`);

--
-- Indices de la tabla `asignatura_estudiante`
--
ALTER TABLE `asignatura_estudiante`
  ADD KEY `asignatura_asignatura` (`codigoAsignatura`),
  ADD KEY `asignatura_estudiante` (`estudianteID`);

--
-- Indices de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD PRIMARY KEY (`asistenciaID`),
  ADD KEY `asistencia_asignatura` (`asignaturaID`),
  ADD KEY `asistencia_tema` (`tema`),
  ADD KEY `asistencia_estudiante` (`estudianteID`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`cursoID`);

--
-- Indices de la tabla `docente`
--
ALTER TABLE `docente`
  ADD PRIMARY KEY (`docenteID`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`estudianteID`),
  ADD KEY `estudiante_curso` (`curso`);

--
-- Indices de la tabla `tema`
--
ALTER TABLE `tema`
  ADD PRIMARY KEY (`temaID`),
  ADD KEY `tema_asignatura` (`asignatura`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuarioID`),
  ADD UNIQUE KEY `identificacion` (`identificacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `asistenciaID` int(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tema`
--
ALTER TABLE `tema`
  MODIFY `temaID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usuarioID` int(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD CONSTRAINT `asignatura_docente` FOREIGN KEY (`docente`) REFERENCES `docente` (`docenteID`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `asignatura_estudiante`
--
ALTER TABLE `asignatura_estudiante`
  ADD CONSTRAINT `asignatura_asignatura` FOREIGN KEY (`codigoAsignatura`) REFERENCES `asignatura` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `asignatura_estudiante` FOREIGN KEY (`estudianteID`) REFERENCES `estudiante` (`estudianteID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD CONSTRAINT `asistencia_asignatura` FOREIGN KEY (`asignaturaID`) REFERENCES `asignatura` (`codigo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `asistencia_estudiante` FOREIGN KEY (`estudianteID`) REFERENCES `estudiante` (`estudianteID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `asistencia_tema` FOREIGN KEY (`tema`) REFERENCES `tema` (`temaID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `docente`
--
ALTER TABLE `docente`
  ADD CONSTRAINT `docente_estudiante` FOREIGN KEY (`docenteID`) REFERENCES `usuario` (`identificacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD CONSTRAINT `estudiante_curso` FOREIGN KEY (`curso`) REFERENCES `curso` (`cursoID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `estudiante_usuario` FOREIGN KEY (`estudianteID`) REFERENCES `usuario` (`identificacion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tema`
--
ALTER TABLE `tema`
  ADD CONSTRAINT `tema_asignatura` FOREIGN KEY (`asignatura`) REFERENCES `asignatura` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
