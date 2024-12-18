-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 14-12-2024 a las 23:12:40
-- Versión del servidor: 8.0.30
-- Versión de PHP: 8.1.10
CREATE DATABASE db_spring;
USE db_spring;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_spring`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bebida`
--

CREATE TABLE `bebida` (
  `graduacion_alcoholica` float DEFAULT NULL,
  `id` int NOT NULL,
  `tamaño` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `bebida`
--

INSERT INTO `bebida` (`graduacion_alcoholica`, `id`, `tamaño`) VALUES
(5.5, 16, 330),
(0, 17, 250),
(40, 18, 50),
(12.5, 19, 750),
(0, 20, 500),
(0, 21, 400),
(0, 22, 200),
(0, 23, 300),
(0, 24, 450),
(10, 25, 300);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipo_item` enum('BEBIDA','PLATO') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `descripcion`, `tipo_item`) VALUES
(1, 'Platos Principales', 'PLATO'),
(2, 'Bebidas Alcoholicas', 'BEBIDA'),
(3, 'Postres', 'PLATO'),
(4, 'Bebidas Sin Alcohol', 'BEBIDA'),
(5, 'Gaseosas', 'BEBIDA'),
(6, 'Hamburguesas', 'PLATO'),
(7, 'Helados', 'PLATO'),
(8, 'Vinos', 'BEBIDA'),
(9, 'Pastas', 'PLATO'),
(10, 'Carnes', 'PLATO'),
(11, 'Verduras', 'PLATO'),
(12, 'Harinas', 'PLATO'),
(13, 'Cervezas', 'BEBIDA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `coordenadas_id` int DEFAULT NULL,
  `id` int NOT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`coordenadas_id`, `id`, `cuit`, `direccion`, `email`, `nombre`) VALUES
(11, 1, '20-12345678-9', 'Calle 7 N°1234', 'roberto@email.com', 'Roberto Sánchez'),
(12, 2, '27-87654321-0', 'Avenida 9 N°5678', 'laura@email.com', 'Laura Díaz'),
(13, 3, '20-45678912-3', 'Boulevard 11 N°910', 'miguel@email.com', 'Miguel Ruiz'),
(14, 4, '27-23456789-1', 'Pasaje 13 N°1112', 'sofia@email.com', 'Sofía López'),
(15, 5, '20-56789123-4', 'Ruta 15 N°1314', 'diego@email.com', 'Diego Morales'),
(16, 6, '20-67891234-5', 'Calle 17 N°1516', 'elena@email.com', 'Elena Pérez'),
(17, 7, '27-78912345-6', 'Avenida 19 N°1718', 'fernando@email.com', 'Fernando García'),
(18, 8, '20-89123456-7', 'Boulevard 21 N°1920', 'patricia@email.com', 'Patricia Torres'),
(19, 9, '27-91234567-8', 'Pasaje 23 N°2122', 'andres@email.com', 'Andrés Gómez'),
(20, 10, '20-12345678-9', 'Ruta 25 N°2324', 'claudia@email.com', 'Claudia Fernández'),
(21, 11, '20-98765432-1', 'Calle 27 N°1234', 'maria@email.com', 'María González'),
(22, 12, '27-87654321-2', 'Avenida 28 N°5678', 'juan@email.com', 'Juan Pérez'),
(23, 13, '20-76543210-3', 'Boulevard 29 N°910', 'ana@email.com', 'Ana Rodríguez'),
(24, 14, '27-65432109-4', 'Pasaje 30 N°1112', 'carlos@email.com', 'Carlos Fernández'),
(25, 15, '20-54321098-5', 'Ruta 31 N°1314', 'lucian@email.com', 'Lucía Martínez'),
(26, 16, '20-43210987-6', 'Calle 32 N°1516', 'pedros@email.com', 'Pedro Sánchez'),
(27, 17, '27-32109876-7', 'Avenida 33 N°1718', 'laurag@email.com', 'Laura Gómez'),
(28, 18, '20-21098765-8', 'Boulevard 34 N°1920', 'jorged@email.com', 'Jorge Díaz'),
(29, 19, '27-10987654-9', 'Pasaje 35 N°2122', 'sofiasan@email.com', 'Sofía Sanchez'),
(30, 20, '20-09876543-0', 'Ruta 36 N°2324', 'diegorios@email.com', 'Diego Rios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coordenada`
--

CREATE TABLE `coordenada` (
  `id` int NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `coordenada`
--

INSERT INTO `coordenada` (`id`, `lat`, `lng`) VALUES
(1, -34.6037, -58.3816),
(2, -34.6132, -58.3794),
(3, -34.6083, -58.3709),
(4, -34.5955, -58.3975),
(5, -34.6044, -58.3812),
(6, -34.61, -58.38),
(7, -34.605, -58.385),
(8, -34.609, -58.382),
(9, -34.607, -58.39),
(10, -34.611, -58.375),
(11, -34.612, -58.376),
(12, -34.613, -58.377),
(13, -34.614, -58.378),
(14, -34.615, -58.379),
(15, -34.616, -58.38),
(16, -34.617, -58.381),
(17, -34.618, -58.382),
(18, -34.619, -58.383),
(19, -34.62, -58.384),
(20, -34.621, -58.385),
(21, -34.622, -58.386),
(22, -34.623, -58.387),
(23, -34.624, -58.388),
(24, -34.625, -58.389),
(25, -34.626, -58.39),
(26, -34.627, -58.391),
(27, -34.628, -58.392),
(28, -34.629, -58.393),
(29, -34.63, -58.394),
(30, -34.631, -58.395);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estrategia_de_pago`
--

CREATE TABLE `estrategia_de_pago` (
  `id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `estrategia_de_pago`
--

INSERT INTO `estrategia_de_pago` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estrategia_mercado_pago`
--

CREATE TABLE `estrategia_mercado_pago` (
  `id` int NOT NULL,
  `alias` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `estrategia_mercado_pago`
--

INSERT INTO `estrategia_mercado_pago` (`id`, `alias`) VALUES
(1, 'mercadopago_roberto'),
(2, 'mercadopago_laura'),
(3, 'mercadopago_miguel'),
(4, 'mercadopago_sofia'),
(5, 'mercadopago_diego'),
(6, 'mercadopago_elena'),
(7, 'mercadopago_fernando'),
(8, 'mercadopago_patricia'),
(9, 'mercadopago_andres'),
(10, 'mercadopago_claudia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estrategia_transferencia`
--

CREATE TABLE `estrategia_transferencia` (
  `id` int NOT NULL,
  `cbu` varchar(255) NOT NULL,
  `cuit` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `estrategia_transferencia`
--

INSERT INTO `estrategia_transferencia` (`id`, `cbu`, `cuit`) VALUES
(11, '8901234567890123456789', '20-98765432-1'),
(12, '9012345678901234567890', '27-87654321-2'),
(13, '0123456789012345678901', '20-76543210-3'),
(14, '1234567890123456789013', '27-65432109-4'),
(15, '2345678901234567890124', '20-54321098-5'),
(16, '3456789012345678901235', '20-43210987-6'),
(17, '4567890123456789012346', '27-32109876-7'),
(18, '5678901234567890123457', '20-21098765-8'),
(19, '6789012345678901234568', '27-10987654-9'),
(20, '7890123456789012345679', '20-09876543-0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item_menu`
--

CREATE TABLE `item_menu` (
  `apto_vegano` bit(1) DEFAULT NULL,
  `categoria_id` int DEFAULT NULL,
  `id` int NOT NULL,
  `precio` float DEFAULT NULL,
  `vendedor_id` int DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `item_menu`
--

INSERT INTO `item_menu` (`apto_vegano`, `categoria_id`, `id`, `precio`, `vendedor_id`, `descripcion`, `nombre`) VALUES
(b'0', 6, 1, 1200, 1, 'Hamburguesa de carne con queso', 'Hamburguesa Clásica'),
(b'1', 1, 2, 1500, 2, 'Pizza tradicional con albahaca', 'Pizza Margherita'),
(b'1', 11, 3, 800, 3, 'Ensalada mixta de verduras frescas', 'Ensalada Verde'),
(b'0', 10, 4, 1300, 4, 'Pollo asado con hierbas', 'Pollo al Horno'),
(b'1', 11, 5, 950, 5, 'Tarta vegetariana multicolor', 'Tarta de Verduras'),
(b'0', 1, 6, 2000, 6, 'Selección de rolls', 'Sushi Variado'),
(b'1', 3, 7, 600, 7, 'Postre de chocolate intenso', 'Brownie de Chocolate'),
(b'0', 1, 8, 1100, 8, 'Selección de 6 empanadas', 'Empanadas Mix'),
(b'1', 11, 9, 850, 9, 'Ensalada cesar sin ingredientes de origen animal', 'Cesar Vegano'),
(b'0', 1, 10, 1400, 10, 'Lasaña tradicional de carne', 'Lasaña'),
(b'0', 1, 11, 1200, 1, 'Tacos con pollo y vegetales', 'Tacos de Pollo'),
(b'1', 7, 12, 700, 2, 'Sopa cremosa de tomate', 'Sopa de Tomate'),
(b'0', 1, 13, 1800, 3, 'Ceviche de pescado fresco', 'Ceviche'),
(b'0', 10, 14, 2200, 4, 'Bife de chorizo a la parrilla', 'Bife de Chorizo'),
(b'1', 7, 15, 500, 5, 'Helado artesanal de vainilla', 'Helado de Vainilla'),
(b'0', 13, 16, 200, 1, 'Cerveza artesanal rubia', 'Cerveza Artesanal'),
(b'1', 5, 17, 150, 2, 'Jugo de naranja natural', 'Jugo de Naranja'),
(b'0', 2, 18, 500, 3, 'Whisky escocés', 'Whisky'),
(b'0', 8, 19, 300, 4, 'Vino tinto Malbec', 'Vino Tinto'),
(b'1', 4, 20, 100, 5, 'Agua mineral sin gas', 'Agua Mineral'),
(b'1', 4, 21, 120, 6, 'Limonada casera', 'Limonada'),
(b'1', 4, 22, 80, 7, 'Café expreso italiano', 'Café Expreso'),
(b'1', 4, 23, 90, 8, 'Té verde orgánico', 'Té Verde'),
(b'1', 4, 24, 180, 9, 'Batido de frutas frescas', 'Batido de Frutas'),
(b'0', 2, 25, 250, 10, 'Cóctel de ron con menta', 'Mojito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item_pedido`
--

CREATE TABLE `item_pedido` (
  `cantidad` int DEFAULT NULL,
  `id` int NOT NULL,
  `item_id` int DEFAULT NULL,
  `pedido_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `item_pedido`
--

INSERT INTO `item_pedido` (`cantidad`, `id`, `item_id`, `pedido_id`) VALUES
(2, 1, 1, 1),
(1, 2, 11, 1),
(1, 3, 2, 2),
(2, 4, 12, 2),
(1, 5, 3, 3),
(1, 6, 13, 3),
(2, 7, 4, 4),
(1, 8, 14, 4),
(1, 9, 5, 5),
(1, 10, 15, 5),
(2, 11, 6, 6),
(1, 12, 21, 6),
(1, 13, 7, 7),
(2, 14, 22, 7),
(1, 15, 8, 8),
(1, 16, 23, 8),
(2, 17, 9, 9),
(1, 18, 24, 9),
(1, 19, 10, 10),
(2, 20, 25, 10),
(1, 21, 1, 11),
(1, 22, 11, 11),
(2, 23, 2, 12),
(1, 24, 12, 12),
(1, 25, 3, 13),
(2, 26, 13, 13),
(1, 27, 4, 14),
(1, 28, 14, 14),
(2, 29, 5, 15),
(1, 30, 15, 15),
(1, 31, 6, 16),
(1, 32, 21, 16),
(2, 33, 7, 17),
(1, 34, 22, 17),
(1, 35, 8, 18),
(1, 36, 23, 18),
(2, 37, 9, 19),
(1, 38, 24, 19),
(3, 39, 10, 20),
(2, 40, 25, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `estrategia_de_pago_id` int DEFAULT NULL,
  `id` int NOT NULL,
  `monto_final` double DEFAULT NULL,
  `pedido_id` int DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`estrategia_de_pago_id`, `id`, `monto_final`, `pedido_id`, `fecha`) VALUES
(1, 1, 3200, 1, '2024-02-15 19:30:00.000000'),
(2, 2, 2700, 2, '2024-02-16 20:15:00.000000'),
(3, 3, 2050, 3, '2024-02-17 18:45:00.000000'),
(4, 4, 3450, 4, '2024-02-18 21:00:00.000000'),
(5, 5, 3000, 5, '2024-02-19 19:45:00.000000'),
(6, 6, 2500, 6, '2024-02-20 18:30:00.000000'),
(7, 7, 2800, 7, '2024-02-21 20:00:00.000000'),
(8, 8, 3100, 8, '2024-02-22 19:15:00.000000'),
(9, 9, 2900, 9, '2024-02-23 18:45:00.000000'),
(10, 10, 3300, 10, '2024-02-24 20:30:00.000000'),
(11, 11, 1500, 11, '2024-02-25 19:30:00.000000'),
(12, 12, 1800, 12, '2024-02-26 20:15:00.000000'),
(13, 13, 2200, 13, '2024-02-27 18:45:00.000000'),
(14, 14, 2600, 14, '2024-02-28 21:00:00.000000'),
(15, 15, 2400, 15, '2024-03-01 19:45:00.000000'),
(16, 16, 2000, 16, '2024-03-02 18:30:00.000000'),
(17, 17, 2700, 17, '2024-03-03 20:00:00.000000'),
(18, 18, 3100, 18, '2024-03-04 19:15:00.000000'),
(19, 19, 2900, 19, '2024-03-05 18:45:00.000000'),
(20, 20, 3300, 20, '2024-03-06 20:30:00.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `cliente_id` int DEFAULT NULL,
  `id` int NOT NULL,
  `pago_id` int DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `vendedor_id` int DEFAULT NULL,
  `estado` enum('ENTREGADO','EN_ENVIO','EN_PREPARACION','RECIBIDO') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`cliente_id`, `id`, `pago_id`, `precio`, `vendedor_id`, `estado`) VALUES
(1, 1, 1, 3200, 1, 'EN_PREPARACION'),
(2, 2, 2, 2700, 2, 'ENTREGADO'),
(3, 3, 3, 2050, 3, 'RECIBIDO'),
(4, 4, 4, 3450, 4, 'EN_PREPARACION'),
(5, 5, 5, 3000, 5, 'ENTREGADO'),
(6, 6, 6, 2500, 6, 'RECIBIDO'),
(7, 7, 7, 2800, 7, 'EN_PREPARACION'),
(8, 8, 8, 3100, 8, 'ENTREGADO'),
(9, 9, 9, 2900, 9, 'RECIBIDO'),
(10, 10, 10, 3300, 10, 'EN_PREPARACION'),
(11, 11, 11, 1500, 1, 'RECIBIDO'),
(12, 12, 12, 1800, 2, 'EN_PREPARACION'),
(13, 13, 13, 2200, 3, 'ENTREGADO'),
(14, 14, 14, 2600, 4, 'RECIBIDO'),
(15, 15, 15, 2400, 5, 'EN_PREPARACION'),
(16, 16, 16, 2000, 6, 'ENTREGADO'),
(17, 17, 17, 2700, 7, 'RECIBIDO'),
(18, 18, 18, 3100, 8, 'EN_PREPARACION'),
(19, 19, 19, 2900, 9, 'ENTREGADO'),
(20, 20, 20, 4700, 10, 'RECIBIDO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plato`
--

CREATE TABLE `plato` (
  `apto_celiaco` bit(1) DEFAULT NULL,
  `calorias` int DEFAULT NULL,
  `id` int NOT NULL,
  `peso` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `plato`
--

INSERT INTO `plato` (`apto_celiaco`, `calorias`, `id`, `peso`) VALUES
(b'0', 450, 1, 250.5),
(b'1', 320, 2, 180),
(b'1', 280, 3, 200.5),
(b'0', 550, 4, 300),
(b'1', 380, 5, 220.5),
(b'0', 600, 6, 350),
(b'1', 400, 7, 250),
(b'0', 500, 8, 300),
(b'1', 350, 9, 200),
(b'0', 450, 10, 250),
(b'0', 300, 11, 200),
(b'1', 150, 12, 100),
(b'0', 200, 13, 150),
(b'0', 700, 14, 400),
(b'1', 200, 15, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `coordenadas_id` int DEFAULT NULL,
  `id` int NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`coordenadas_id`, `id`, `direccion`, `nombre`) VALUES
(1, 1, 'Calle 1 N°123', 'El Buen Sabor'),
(2, 2, 'Avenida 2 N°456', 'Sabor a Hogar'),
(3, 3, 'Boulevard 3 N°789', 'Delicias del Chef'),
(4, 4, 'Pasaje 4 N°101', 'La Cocina de Abuela'),
(5, 5, 'Ruta 5 N°202', 'Sabores del Mundo'),
(6, 6, 'Calle 6 N°1234', 'El Rincón Gourmet'),
(7, 7, 'Avenida 7 N°5678', 'Fusión de Sabores'),
(8, 8, 'Boulevard 8 N°910', 'La Esquina del Sabor'),
(9, 9, 'Pasaje 9 N°1112', 'El Sabor de Casa'),
(10, 10, 'Ruta 10 N°1314', 'Gusto y Tradición');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bebida`
--
ALTER TABLE `bebida`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKs7flyba3q35u2d8o7upiusi71` (`coordenadas_id`);

--
-- Indices de la tabla `coordenada`
--
ALTER TABLE `coordenada`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estrategia_de_pago`
--
ALTER TABLE `estrategia_de_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estrategia_mercado_pago`
--
ALTER TABLE `estrategia_mercado_pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estrategia_transferencia`
--
ALTER TABLE `estrategia_transferencia`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKcrvtrpl1g59qpc7tqmciu68mx` (`cbu`),
  ADD UNIQUE KEY `UKxm45rkqsf4q1tpuj7ksyaj89` (`cuit`);

--
-- Indices de la tabla `item_menu`
--
ALTER TABLE `item_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpbnpjo9l39h63eqw6xw1ce44h` (`categoria_id`);

--
-- Indices de la tabla `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm4tfi0613ph7jnelurx3svgn6` (`item_id`),
  ADD KEY `FK60ym08cfoysa17wrn1swyiuda` (`pedido_id`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKjs1u45p5haof5s2es63sp96yd` (`estrategia_de_pago_id`),
  ADD UNIQUE KEY `UKcjukh0gqou26iq8ro20j829ug` (`pedido_id`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKfibo078ch1xjrp3bcq4piov4e` (`pago_id`),
  ADD KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`);

--
-- Indices de la tabla `plato`
--
ALTER TABLE `plato`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK1wdejfi9beapwmnffahee3oba` (`coordenadas_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `coordenada`
--
ALTER TABLE `coordenada`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `estrategia_de_pago`
--
ALTER TABLE `estrategia_de_pago`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `item_menu`
--
ALTER TABLE `item_menu`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `item_pedido`
--
ALTER TABLE `item_pedido`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bebida`
--
ALTER TABLE `bebida`
  ADD CONSTRAINT `FKf6a5syliy588ajkg19x3im9b1` FOREIGN KEY (`id`) REFERENCES `item_menu` (`id`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FKhyw3ha2srp9oo42relj3eidwe` FOREIGN KEY (`coordenadas_id`) REFERENCES `coordenada` (`id`);

--
-- Filtros para la tabla `estrategia_mercado_pago`
--
ALTER TABLE `estrategia_mercado_pago`
  ADD CONSTRAINT `FKirit2mhl4xjipmorgcfwy5tv` FOREIGN KEY (`id`) REFERENCES `estrategia_de_pago` (`id`);

--
-- Filtros para la tabla `estrategia_transferencia`
--
ALTER TABLE `estrategia_transferencia`
  ADD CONSTRAINT `FKhysfro613iu7u69igakb0ka9i` FOREIGN KEY (`id`) REFERENCES `estrategia_de_pago` (`id`);

--
-- Filtros para la tabla `item_menu`
--
ALTER TABLE `item_menu`
  ADD CONSTRAINT `FKpbnpjo9l39h63eqw6xw1ce44h` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `item_pedido`
--
ALTER TABLE `item_pedido`
  ADD CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FKm4tfi0613ph7jnelurx3svgn6` FOREIGN KEY (`item_id`) REFERENCES `item_menu` (`id`);

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `FK8fojprqy7kv7k3d192m91e027` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  ADD CONSTRAINT `FKju92wteav4e6f3akbl4kwjqfe` FOREIGN KEY (`estrategia_de_pago_id`) REFERENCES `estrategia_de_pago` (`id`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `plato`
--
ALTER TABLE `plato`
  ADD CONSTRAINT `FK1ptfgl94ejfdtjsdokef2ijja` FOREIGN KEY (`id`) REFERENCES `item_menu` (`id`);

--
-- Filtros para la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD CONSTRAINT `FKb38jjduf0t9kqk5ye22n4iama` FOREIGN KEY (`coordenadas_id`) REFERENCES `coordenada` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
