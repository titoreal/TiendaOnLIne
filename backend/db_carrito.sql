-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2025 a las 02:22:16
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_carrito`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `numero_serie` varchar(50) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`numero_serie`, `nombre`, `descripcion`, `precio`, `imagen`) VALUES
('ABC56789', 'Laptop UltraThin 13', 'Laptop ultradelgada de 13 pulgadas con procesador Intel i3, 4GB de RAM y 128GB SSD.', 659.99, '1.jpg'),
('ABC67890', 'Laptop ProElite 15', 'Laptop de 15 pulgadas con procesador Intel i7, 16GB de RAM y 512GB SSD, ideal para productividad y entretenimiento.', 1099.00, '2.jpg'),
('ABC90123', 'Speaker SoundBoom', 'Altavoz portátil con sonido estéreo, resistencia al agua y hasta 15 horas de batería.', 17.99, '3.jpg'),
('DEF11223', 'Auriculares SoundMax', 'Auriculares inalámbricos con cancelación de ruido activa, sonido de alta calidad y hasta 30 horas de batería.', 39.99, '4.jpg'),
('DEF67890', 'Cámara DSLR MaxShot', 'Cámara réflex digital con lente de 18-55 mm, 24 MP y grabación en 4K.', 599.00, '5.jpg'),
('DEF78901', 'Smartphone Vivo Max', 'Smartphone con pantalla de 6.5 pulgadas, cámara de 64 MP y 128GB de almacenamiento.', 649.00, '6.jpg'),
('GHI11223', 'Teclado Mecánico HyperX', 'Teclado mecánico con retroiluminación RGB y teclas personalizables.', 19.99, '7.jpg'),
('GHI34567', 'Audífonos Bluetooth SportX', 'Auriculares inalámbricos deportivos con ajuste ergonómico, resistencia al sudor y hasta 12 horas de batería.', 39.99, '8.jpg'),
('GHI44567', 'Smartwatch FitTrack', 'Reloj inteligente con monitoreo de actividad, GPS, y notificaciones de mensajes y llamadas.', 59.00, '9.jpg'),
('JKL11234', 'Estación de Carga PowerHub', 'Estación de carga con puertos USB y tecnología de carga rápida, compatible con múltiples dispositivos.', 9.99, '10.jpg'),
('JKL33456', 'Monitor UltraView 24\"', 'Monitor Full HD de 24 pulgadas, ideal para trabajar o jugar, con frecuencia de actualización de 75 Hz.', 109.00, '11.jpg'),
('JKL99876', 'Fitness Band MaxFit', 'Pulsera inteligente con monitoreo de frecuencia cardíaca, contador de pasos y duración de batería de hasta 7 días.', 39.99, '12.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`numero_serie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
