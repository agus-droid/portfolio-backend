-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.6.5-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando datos para la tabla portfolio.acerca: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `acerca` DISABLE KEYS */;
INSERT INTO `acerca` (`id`, `banner`, `foto`, `nombre`, `resumen`, `titulo`) VALUES
	(70, 'OxDUozhR', 'Zp0KKNCO', 'Agus', 'Esto es un resumen', 'Esto es un titulo');
/*!40000 ALTER TABLE `acerca` ENABLE KEYS */;

-- Volcando datos para la tabla portfolio.hibernate_sequence: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(71);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Volcando datos para la tabla portfolio.rol: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`id`, `rol_nombre`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;

-- Volcando datos para la tabla portfolio.usuario: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `email`, `nombre`, `password`, `username`) VALUES
	(1, 'email', 'agus', '$2a$10$6ROJGk7YL4d53laCV6/.de0SJT9KYmN/ReJc3RZCrhKSZVrk26CVC', 'agus');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando datos para la tabla portfolio.usuario_rol: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` (`usuario_id`, `rol_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
