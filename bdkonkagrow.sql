-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-10-2017 a las 11:22:18
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdkonkagrow`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_caja`
--

CREATE TABLE `tb_caja` (
  `dv` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `cajaini` int(8) NOT NULL,
  `ventas` int(8) NOT NULL,
  `ingresos` int(8) NOT NULL,
  `egresos` int(8) NOT NULL,
  `cajafin` int(8) NOT NULL,
  `diferencia` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_caja`
--

INSERT INTO `tb_caja` (`dv`, `fecha`, `usuario`, `cajaini`, `ventas`, `ingresos`, `egresos`, `cajafin`, `diferencia`) VALUES
(10, '2017-08-05', 'admin', 1, 129000, 0, 0, 129001, 0),
(11, '2017-08-09', 'admin', 0, 74700, 20000, 25000, 70000, 300),
(12, '2017-08-12', 'admin', 1, 0, 0, 0, 12, 11),
(13, '2017-08-13', 'admin', 1, 0, 0, 0, 1, 0),
(14, '2017-08-14', 'admin', 1, 0, 0, 0, 1, 0),
(15, '2017-08-14', 'admin', 1, 0, 0, 0, 1, 0),
(16, '2017-08-14', 'admin', 1, 0, 0, 0, 1, 0),
(17, '2017-08-14', 'admin', 1, 231385, 0, 0, 231386, 0),
(18, '2017-08-14', 'admin', 1, 565585, 0, 0, 600000, 34414),
(19, '2017-08-15', 'admin', 1, 0, 0, 0, 1, 0),
(20, '2017-09-05', 'admin', 1, 43380, 0, 0, 43381, 0),
(21, '2017-09-08', 'Admin', 0, 0, 0, 0, 0, 0),
(22, '2017-09-07', 'admin', 1, 0, 0, 0, 1, 0),
(23, '2017-09-08', 'Admin', 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_cajaegreso`
--

CREATE TABLE `tb_cajaegreso` (
  `dv` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `monto` int(8) NOT NULL,
  `usuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_cajaegreso`
--

INSERT INTO `tb_cajaegreso` (`dv`, `fecha`, `motivo`, `monto`, `usuario`) VALUES
(1, '2017-08-09', 'venta de pasta', 25000, 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_cajaingreso`
--

CREATE TABLE `tb_cajaingreso` (
  `dv` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `monto` int(8) NOT NULL,
  `usuario` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_cajaingreso`
--

INSERT INTO `tb_cajaingreso` (`dv`, `fecha`, `motivo`, `monto`, `usuario`) VALUES
(1, '2017-08-09', 'venta de droga', 20000, 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_cliente`
--

CREATE TABLE `tb_cliente` (
  `nomcliente` varchar(20) NOT NULL,
  `rutcliente` varchar(10) NOT NULL,
  `fonocliente` int(15) NOT NULL,
  `emailcliente` varchar(40) NOT NULL,
  `puntoscliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_cliente`
--

INSERT INTO `tb_cliente` (`nomcliente`, `rutcliente`, `fonocliente`, `emailcliente`, `puntoscliente`) VALUES
('SIN CLIENTE', '17614376-2', 412252408, 'ventasincliente@gmail.com', 1518);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_detalleentrada`
--

CREATE TABLE `tb_detalleentrada` (
  `codentrada` int(10) NOT NULL,
  `codprod` varchar(10) NOT NULL,
  `detalleprod` varchar(30) NOT NULL,
  `cantidad` int(6) NOT NULL,
  `precio_unitario` int(7) NOT NULL,
  `precio_total` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_detallesalida`
--

CREATE TABLE `tb_detallesalida` (
  `codsalida` int(10) NOT NULL,
  `codprod` varchar(20) NOT NULL,
  `detalleprod` varchar(40) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio_unitario` int(6) NOT NULL,
  `precio_total` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_detallesalida`
--

INSERT INTO `tb_detallesalida` (`codsalida`, `codprod`, `detalleprod`, `cantidad`, `precio_unitario`, `precio_total`) VALUES
(10000001, '000000017', 'Pot Silicona 5.5', 11, 3000, 33000),
(10000002, '000000015', 'BONG ZONG CHICO', 1, 15000, 15000),
(10000002, '000000017', 'POT SILICONA 5.5', 1, 3000, 3000),
(10000002, '000000021', 'BONG RECYCLER GRANDE', 1, 45000, 45000),
(10000003, '000000007', 'BOWL REPUESTO COLORES', 1, 4500, 4500),
(10000003, '000000100', 'BONG DISCO TURBINA HONEY COMB', 1, 35, 35),
(10000003, '000000101', 'MOLEDOR L CONCAVO 4 PIEZAS', 1, 12, 12),
(10000003, '000000104', 'BONG UFO TRANSPARENTE', 1, 20000, 20000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_entrada`
--

CREATE TABLE `tb_entrada` (
  `codentrada` int(30) NOT NULL,
  `fecha` date NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `motivo` varchar(30) NOT NULL,
  `nulo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_marcas`
--

CREATE TABLE `tb_marcas` (
  `codmarca` int(30) NOT NULL,
  `nom_marca` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_marcas`
--

INSERT INTO `tb_marcas` (`codmarca`, `nom_marca`) VALUES
(29, '4:20 Genetics'),
(77, 'Agricola Mercosur'),
(52, 'aledinha'),
(51, 'amico'),
(18, 'Atami'),
(61, 'Atmos'),
(24, 'B.A.C.'),
(36, 'Barney´s Farm'),
(21, 'Biobizz'),
(55, 'Blunt Wrap'),
(47, 'Buddha Seeds'),
(53, 'c-thru'),
(73, 'Can-Filter'),
(20, 'Canna'),
(32, 'Cannabiogen'),
(83, 'Clipper'),
(63, 'Co2Boost'),
(81, 'Cultibox'),
(14, 'Cultibox Light'),
(44, 'Delicious'),
(26, 'Dinafem'),
(33, 'Dutch Passion'),
(37, 'Elements'),
(31, 'EVA'),
(15, 'Garden High'),
(68, 'Geo BOOST'),
(70, 'GOLD Label'),
(57, 'Green House'),
(23, 'Grotek'),
(17, 'Grow Genetics'),
(58, 'Growth Tecnology'),
(19, 'Hesi'),
(79, 'Hortitec'),
(65, 'Integra Boost'),
(54, 'Kenex'),
(76, 'Koa'),
(82, 'Lion Rolling Circus'),
(45, 'Maceta'),
(41, 'Medical Seeds'),
(84, 'Neon'),
(30, 'Nirvana'),
(42, 'OCB'),
(49, 'Odorsok'),
(60, 'Ona'),
(69, 'ORGANIK'),
(48, 'OSRAM'),
(40, 'Paradise Seeds'),
(71, 'Plagron'),
(25, 'Powder Feeding'),
(16, 'Probox'),
(27, 'Pyramid'),
(43, 'Randy´s'),
(38, 'Raw'),
(34, 'Ripper Seeds'),
(39, 'Samsara'),
(80, 'Scrog'),
(50, 'Sensi Seeds'),
(75, 'Siembra Chile'),
(67, 'Sin Marca'),
(28, 'Sweet Seeds'),
(72, 'THC'),
(85, 'The Bulldog'),
(22, 'Top Crop'),
(86, 'VDL'),
(66, 'Ventiladores Varios'),
(56, 'Vents'),
(46, 'World of Seeds');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_motientrada`
--

CREATE TABLE `tb_motientrada` (
  `codmotiv` int(7) NOT NULL,
  `motivo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_motivos`
--

CREATE TABLE `tb_motivos` (
  `codmotiv` int(6) NOT NULL,
  `motivo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_motivos`
--

INSERT INTO `tb_motivos` (`codmotiv`, `motivo`) VALUES
(1, 'robo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pack`
--

CREATE TABLE `tb_pack` (
  `codpack` varchar(25) NOT NULL,
  `nompack` varchar(30) NOT NULL,
  `preciopack` int(10) NOT NULL,
  `ahorro` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_pack`
--

INSERT INTO `tb_pack` (`codpack`, `nompack`, `preciopack`, `ahorro`) VALUES
('PACK10000001', 'PACK DE MIERDA', 45000, -22943),
('PACK10000002', 'cagada de pack', 30000, 4500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_packprod`
--

CREATE TABLE `tb_packprod` (
  `codprod` varchar(25) NOT NULL,
  `codpack` varchar(25) NOT NULL,
  `producto` varchar(40) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio_unitario` int(6) NOT NULL,
  `precio_total` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_packprod`
--

INSERT INTO `tb_packprod` (`codprod`, `codpack`, `producto`, `cantidad`, `precio_unitario`, `precio_total`) VALUES
('000000002', 'PACK10000002', 'POT SILICONA 3 CM', 1, 2000, 2000),
('000000004', 'PACK10000002', 'POT PLASTICO', 2, 2000, 4000),
('000000007', 'PACK10000002', 'BOWL REPUESTO COLORES', 3, 4500, 13500),
('000000045', 'PACK10000002', 'MOLEDOR XL CONCAVO 4 PIEZAS', 1, 15000, 15000),
('000000049', 'PACK10000001', 'MOLEDOR M CONCAVO 4 PIEZAS', 1, 10, 10),
('000000100', 'PACK10000001', 'BONG DISCO TURBINA HONEY COMB', 1, 35, 35),
('000000101', 'PACK10000001', 'MOLEDOR L CONCAVO 4 PIEZAS', 1, 12, 12),
('000000108', 'PACK10000001', 'BONG 30CM BOQUILLA BASE Y COLOR', 1, 22000, 22000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pago`
--

CREATE TABLE `tb_pago` (
  `valor_hora` int(11) NOT NULL,
  `valor_hora_extra` int(11) NOT NULL,
  `rut_emp` int(11) NOT NULL,
  `hora_trab` int(11) NOT NULL,
  `horae_trab` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `fecha_pago` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_productos`
--

CREATE TABLE `tb_productos` (
  `codprod` varchar(20) NOT NULL,
  `detalleprod` varchar(100) NOT NULL,
  `unidad` varchar(10) NOT NULL,
  `rubro` varchar(20) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `proveedor` varchar(30) DEFAULT NULL,
  `precioprod` int(10) NOT NULL,
  `stock` int(4) NOT NULL,
  `stockmin` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_productos`
--

INSERT INTO `tb_productos` (`codprod`, `detalleprod`, `unidad`, `rubro`, `marca`, `proveedor`, `precioprod`, `stock`, `stockmin`) VALUES
('000000001', 'TUBO Extraccion BHO', 'unidad', 'Ventilación', 'Sin Marca', 'KONKA GROW', 6000, 0, 3),
('000000002', 'Pot silicona 3 cm', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 2000, 0, 3),
('000000004', 'Pot Plastico', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 2000, 0, 3),
('000000005', 'Boquilla Silicona', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 4500, 0, 3),
('000000006', 'Bolw Repuesto Transparente', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 4000, 0, 3),
('000000007', 'Bowl Repuesto Colores', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 4500, 0, 1),
('000000015', 'BONG ZONG CHICO', 'Botella', 'Accesorios', 'Atami', 'KONKA GROW', 15000, -1, 3),
('000000017', 'POT SILICONA 800', 'Caca', 'Semillas', 'amico', 'KONKA GROW', 3000, 2953, 3),
('000000020', 'Bong Cohete', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 40000, 1, 3),
('000000021', 'Bong Recycler grande', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 45000, -1, 3),
('000000024', 'Bong Doble Piso Colores Lunares', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 40000, 1, 2),
('000000025', 'Bong Masiso Jaula Invertida  Transparente con verde', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 40000, 0, 3),
('000000030', 'Bong Recycler Donut (Bola que gira y luminoso)', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 50000, 2, 3),
('000000031', 'Bong Fabegg Color', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 50000, 1, 1),
('000000033', 'Bong Recycler Doble Brazo', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 35000, 1, 2),
('000000035', 'Bong Bubbler Huevo', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 45000, 1, 2),
('000000037', 'Bong 8 Quemadores', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 1, 2),
('000000038', 'Moledor S 3 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 6000, 5, 3),
('000000040', 'Moledor M 3 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 7000, 0, 2),
('000000041', 'Moledor L 4 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 0, 3),
('000000043', 'Moledor XL 4 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 15000, 9, 3),
('000000045', 'Moledor XL Concavo 4 piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 15000, 3, 3),
('000000047', 'Moledor XL Concavo 4 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 15000, 0, 3),
('000000049', 'Moledor M Concavo 4 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 10, 12, 3),
('000000100', 'Bong disco turbina Honey Comb', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 35, -1, 1),
('000000101', 'Moledor L Concavo 4 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12, 9, 3),
('000000103', 'Bong silicona', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 32000, 1, 1),
('000000104', 'Bong UFO Transparente', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 20000, -1, 1),
('000000105', 'Bong 30cm Hojitas Atrapahielo cilindrico', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 20000, 2, 3),
('000000108', 'Bong 30CM Boquilla base y color', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 22000, 2, 1),
('000000109', 'Bong Domo ducha color', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 40000, 1, 1),
('000000121', 'Bolsos Chile Skunk', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 3, 1),
('000000122', 'Maquina Moledoa', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 2, 2),
('000000136', 'White Widow XXL x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 25000, 4, 5),
('000000152', 'Big Kush Feminizada pack 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 25000, 1, 1),
('000000154', 'Cbd Moby Dick feminizada Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 9000, 1, 1),
('000000156', 'CBD Moby Dick Feminizada Pack 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 26000, 2, 2),
('000000157', 'Critacal + Feminizada unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8600, 1, 1),
('000000158', 'Moby Dick Feminizada Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 9600, 1, 1),
('000000159', 'Moby Dick Feminizada Pack 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 26000, 17, 10),
('000000160', 'Original Amnesia Feminizada Pack 3 ', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24900, 1, 2),
('000000161', 'OG Kush Feminizada Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 9200, 1, 2),
('000000162', 'Critical + Feminizada Pack 3 ', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 25400, 4, 2),
('000000163', 'Critical + 2.0 Dinafem Feminizada Pack 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 25200, 1, 4),
('000000165', 'Purple Afgan Kush Feminizada Pack 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 26000, 3, 2),
('000000166', 'Original Amnesia Feminizada Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8900, 1, 2),
('000000167', 'Acapulco Gold x5', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 31000, 5, 5),
('000000172', 'Original Amnesia Auto x1', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8400, 0, 5),
('000000173', 'Original Amnesia auto x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24900, 0, 5),
('000000175', 'Critical +2.0 Auto x1', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 9000, 1, 5),
('000000176', 'Critical +2.0 Auto x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24800, 4, 5),
('000000177', 'White Widow Auto x1', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 9000, 5, 5),
('000000178', 'White Widow Auto', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 26000, 3, 5),
('000000179', 'Moby Dick XXL x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24900, 4, 6),
('000000180', 'Moby Dick XXL x1', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8600, 9, 6),
('000000181', 'Haze 2.0 Auto unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8300, 1, 4),
('000000182', 'Haze 2.0 Auto x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24500, 3, 3),
('000000183', 'Critical Jack Auto unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8300, 2, 5),
('000000184', 'Critical Jack Auto x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24500, 1, 5),
('000000186', 'White Widow XXl x1', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8700, 1, 5),
('000000187', 'White Widow Dinafem Feminizada Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 9000, 0, 1),
('000000188', 'White Widow Dinafem Feminizada Pack 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 25000, 4, 4),
('000000192', 'Critical Jack Dinafem Feminizada Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8900, 4, 4),
('000000202', 'Critical Jack Dinafem Feminizada PAck 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24900, 4, 4),
('000000215', 'Moledor Hoja Marihuana', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 8000, 2, 3),
('000000217', 'Moledor Palitroque', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 4000, 4, 3),
('000000218', 'Moledor Molinillo', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 13000, 0, 1),
('000000219', 'Moledor Rolling Circus Plastico', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 7, 3),
('000000220', 'Bong Zong Mediano', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 25000, 1, 3),
('000000221', 'Caja rejillas Chicas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 300, 0, 5),
('000000225', 'Poleras B estido Dama S', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 7, 3),
('000000226', 'Polerones Skunk', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 18000, 2, 1),
('000000227', 'Poleras Sure Varon', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 24, 3),
('000000228', 'Moledor Bala 3 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 5, 2),
('000000229', 'Moledor pequeño 3 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 1, 1),
('000000230', 'Moledor Diseño 3 Piezas', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 1, 2),
('000000233', 'Grinder Lata Chela', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 6000, 7, 3),
('000000236', 'Moledor Bull Dog', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 3, 1),
('000000237', 'Moledor Extintor', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 6000, 2, 2),
('000000247', 'Vapor Vicod 5 G', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 120000, 0, 1),
('000000249', 'Vapo VICOD', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 100000, 0, 1),
('000000250', 'JUMP', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 60000, 2, 1),
('000000252', '3 en 1', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 38000, 3, 1),
('000000254', 'Kilin', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 70000, 1, 1),
('000000256', 'PIQ', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 15000, 2, 1),
('000000257', 'ORBIT', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 50000, 2, 1),
('000000258', 'NANO', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 10, 2),
('000000259', 'DOMO Clavo', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 6000, 6, 3),
('000000261', 'Z Cuarzo', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 14000, 4, 3),
('000000262', 'Banger de cuarzo', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 22500, 3, 1),
('000000263', 'Quemadores de titanio', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 20000, 8, 2),
('000000264', 'Quemador Titanio Tornasol', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 25000, 2, 1),
('000000265', 'Quemadores Titanio 1CC', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 25000, 3, 1),
('000000267', 'Quemador Titanio con tapa como gorro', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 30000, 3, 1),
('000000269', 'Quemador Ceramica', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 15000, 2, 1),
('000000273', 'Stack silicona doble', 'unidad', 'Extracciones', 'Sin Marca', 'KONKA GROW', 6000, 3, 1),
('000000274', 'Papel Reynolds', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 4000, 0, 1),
('000000276', 'Daver Gold', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 10000, 3, 1),
('000000277', 'Daber Hexagonal', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 1, 1),
('000000278', 'Daber Espalda', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 15000, 47, 1),
('000000279', 'Daber titanio chico', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 6000, 3, 1),
('000000280', 'Daver Vidrio', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 9, 1),
('000000281', 'Daber Titanio Grande', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 3, 1),
('000000282', 'Platos siliconas 12', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 7500, 4, 1),
('000000284', 'Manta de silicona 12', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 2, 1),
('000000285', 'Soplete', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 10000, 4, 1),
('000000286', 'DNAIL Electronicos', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 150000, 3, 1),
('000000287', 'Mallas HASH', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 38000, 1, 1),
('000000288', 'Bong Extraccion color', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 20000, 5, 1),
('000000289', 'Dabbuchino', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 20000, 1, 1),
('000000301', 'Maquinas para enrrolar', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 35000, 1, 1),
('000000304', 'Caja Corta papelillo ELITE', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 1500, 10, 3),
('000000305', 'Caja metalica Raw King Grande', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 4000, 5, 3),
('000000306', 'Caja metalica Raw King Chica', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 3000, 8, 3),
('000000307', 'BONG portatil de silicona', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 11500, 20, 3),
('000000308', 'Caja de madera Raw', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 15000, 2, 1),
('000000311', 'Pipa Silicona', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 6000, 1, 1),
('000000312', 'Limpia Pipa', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 100, 98, 5),
('000000328', 'Gipsy Haze', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 21000, 3, 5),
('000000329', 'Excalibur', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 21000, 4, 5),
('000000330', 'Missing in Barcelona', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 23000, 4, 5),
('000000331', 'Veneno', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 23000, 5, 5),
('000000332', 'Papa´s Candy', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 22000, 5, 5),
('000000333', 'Furious Candy', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 19000, 5, 5),
('000000334', 'Monster', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 18000, 5, 5),
('000000335', 'Black Dream', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 18000, 5, 5),
('000000410', 'TNT Kush', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 21000, 5, 5),
('000000414', 'Chanell + ', 'unidad', 'Semillas', 'Medical Seeds', 'KONKA GROW', 22000, 5, 5),
('000000415', 'White Widow', 'unidad', 'Semillas', 'Medical Seeds', 'KONKA GROW', 24000, 5, 5),
('000000416', '2046', 'unidad', 'Semillas', 'Medical Seeds', 'KONKA GROW', 24000, 5, 5),
('000000417', 'Y Griega', 'unidad', 'Semillas', 'Medical Seeds', 'KONKA GROW', 21000, 5, 5),
('000000418', 'Jack la Mota', 'unidad', 'Semillas', 'Medical Seeds', 'KONKA GROW', 80000, 5, 5),
('000000419', 'Sour Diesel', 'unidad', 'Semillas', 'Medical Seeds', 'KONKA GROW', 22000, 5, 5),
('000000420', 'Pineapple Express Auto x1', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 7500, 5, 5),
('000000421', 'Pineapple Express Auto x3', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 21000, 5, 5),
('000000422', 'NYC Diesel Auto', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 7500, 5, 5),
('000000423', 'Cookies Kush', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 22000, 5, 5),
('000000424', 'Cookies Kush x5', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 31000, 5, 5),
('000000425', 'Ayahuasca Purple x3', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 22000, 5, 5),
('000000426', 'Liberty Haze', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 22000, 5, 5),
('000000427', 'LSD', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 23000, 5, 5),
('000000428', 'G13 Haze', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 23000, 5, 5),
('000000429', 'Acapulco Gold', 'unidad', 'Semillas', 'Barney´s Farm', 'KONKA GROW', 23000, 5, 5),
('000000430', 'Pink Plant', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 21000, 4, 5),
('000000431', 'Jamaican Dream', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 21000, 3, 5),
('000000432', 'High Level', 'unidad', 'Semillas', 'EVA', 'KONKA GROW', 18000, 4, 5),
('000000433', 'Sour Ripper', 'unidad', 'Semillas', 'Ripper Seeds', 'KONKA GROW', 18000, 4, 5),
('000000434', 'Toxic', 'unidad', 'Semillas', 'Ripper Seeds', 'KONKA GROW', 18000, 2, 5),
('000000435', 'Washing Machine', 'unidad', 'Semillas', 'Ripper Seeds', 'KONKA GROW', 18000, 2, 5),
('000000436', 'Criminal +', 'unidad', 'Semillas', 'Ripper Seeds', 'KONKA GROW', 18000, 2, 5),
('000000437', 'Grapegum', 'unidad', 'Semillas', 'Ripper Seeds', 'KONKA GROW', 18000, 2, 5),
('000000438', 'Double Glock', 'unidad', 'Semillas', 'Ripper Seeds', 'KONKA GROW', 18000, 5, 5),
('000000445', 'Ripper Haze', 'unidad', 'Semillas', 'Ripper Seeds', 'KONKA GROW', 18000, 5, 5),
('00000052', 'MiniBong', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 7500, 10, 3),
('00000053', 'Bong 20Cm Cilindrico atrapa esferico', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 25000, 1, 1),
('00000054', 'Pipas varias PEANUT 4500', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 4500, 63, 3),
('00000055', 'PIPA economica', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 3000, 8, 3),
('00000056', 'PIPA mediana Amanita Joya Roots', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 6500, 37, 3),
('00000057', 'PIPA Lotus martillo Masisa', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 10000, 9, 3),
('00000058', 'PIPA Animal', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 8000, 3, 3),
('00000059', 'Bubbler Inside', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 9000, 6, 3),
('00000060', 'PIPA Percolador Interno', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 14000, 0, 1),
('00000061', 'Bong de color recto atrapahielo', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 22000, 3, 1),
('00000062', 'Bong atrapahielo Chico', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 18000, 0, 1),
('00000063', 'Bong Atrapahielo domo diseño chico marley color', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 15000, 1, 1),
('00000064', 'Bong transparente costado masiso turbinas traseras', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 38000, 3, 1),
('00000065', 'Bong Extraccion Lunar', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 35000, 2, 1),
('00000066', 'Bong gorditos con atrapahielo', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 25000, 1, 1),
('00000067', 'Tubo Respuesto 1914 y difusor', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 4500, 7, 3),
('00000070', 'Atrapa ceniza siemple', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 8000, 4, 2),
('00000071', 'Atrapa cenizas 8 Brazos', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 12000, 2, 2),
('00000077', 'Bong 710', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 40000, 0, 1),
('00000078', 'Bong Zong Grandre', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 64000, 1, 3),
('00000079', 'Bong redonde verde 4 brazos percolador invertido', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 40000, 1, 1),
('00000080', 'Caja rejillas grandes', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 300, 0, 5),
('00000081', 'Hitter Insideout', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 4500, 9, 3),
('00000082', 'Hitter Color plano', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 300, 12, 3),
('00000083', 'Grinder metalico Grande 2pc', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 5000, 3, 1),
('00000084', 'Grinder Metalico Chico 2pc', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 3000, 1, 1),
('00000085', 'Grinder metal 4pc 56mm Diseño color', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 9000, 2, 2),
('00000086', 'Boquillas Pyrex', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 1000, 23, 5),
('00000088', 'PIPA Chupete', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 3000, 9, 3),
('00000089', 'Pipa Cigarro metal', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 2000, 27, 5),
('00000090', 'Pipa Perno', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 3000, 4, 2),
('00000091', 'Pipa Hongito', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 2000, 3, 2),
('00000093', 'PIPA Madera Vidrio Modelo M03', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 10000, 2, 1),
('00000094', 'Papel AMICO, libre de tabaco', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 1000, 16, 3),
('00000095', 'Revista Cañamo', 'unidad', 'Parafernalia', 'Sin Marca', 'KONKA GROW', 1800, 0, 1),
('0000111cult', 'MALLASCROG PARA CULTIVO', 'unidad', 'Armarios', 'Scrog', 'KONKA GROW', 6500, 1, 3),
('000013 Bio', 'Alga Mix 1 lts', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 23500, 1, 5),
('000014Bio', 'Roots Juice 250 ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 17000, 2, 5),
('000015', 'Bio Heaven', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 21900, 1, 5),
('000016Bio', 'Try Pack hidro', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 21000, 1, 5),
('000017Bio', 'Active Vera 250 ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 9000, 2, 5),
('00005ata', 'ATAZYME 500 ML', 'Botella', 'Fertilizantes', 'Atami', 'KONKA GROW', 9000, 1, 5),
('00009Hesi', 'Powerzyme 100 ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 10000, 1, 5),
('000101acce', 'Poleas 5 KG', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 4500, 46, 3),
('000101bob', 'Bobedas Curado', 'unidad', 'Ventilación', 'Sin Marca', 'KONKA GROW', 4000, 6, 1),
('000102vent', 'Pulverizador Metalico', 'unidad', 'Ventilación', 'Sin Marca', 'KONKA GROW', 10000, 5, 1),
('000103acce', 'Termohigrometro con sonda', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 9000, 25, 1),
('000103vent', 'Humidificador Neptuno', 'unidad', 'Ventilación', 'Sin Marca', 'KONKA GROW', 20000, 2, 1),
('000106acce', 'Cable IEC', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 3500, 6, 1),
('000107acce', 'Timer', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 4500, 33, 1),
('00010Bio', 'Top Max 500 ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 18000, 1, 5),
('00010Hesi', 'Complejo Radicular 100 ml', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 5500, 18, 5),
('000111cult', 'Bandeja Lana Roca', 'Bandeja', 'Cultivo', 'Garden High', 'KONKA GROW', 9000, 4, 1),
('000111vent', 'Manga 100', 'Manga', 'Ventilación', 'Agricola Mercosur', 'KONKA GROW', 8650, 35, 1),
('000112', 'Assorted Auto MIX', 'unidad', 'Semillas', 'Buddha Seeds', 'KONKA GROW', 24900, 999, 3),
('000113cult', 'Bandeja Jiffy', 'Bandeja', 'Cultivo', 'Garden High', 'KONKA GROW', 5000, 5, 1),
('000114cult', 'Propagadora Plastica', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 8500, 8500, 1),
('000115cult', 'Manta Calefactora', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 38000, 1, 1),
('000116vent', 'Manga 125 3 MT', 'Manga', 'Ventilación', 'Agricola Mercosur', 'KONKA GROW', 9400, 21, 1),
('000117cult', 'Kasvi 6', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 2000, 16, 1),
('000118cult', 'Kasvi 10', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 3000, 10, 1),
('000118vent', 'Manga 150 3MT', 'Manga', 'Ventilación', 'Agricola Mercosur', 'KONKA GROW', 10000, 13, 1),
('000119acce', 'Humidificador 5.2', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 32000, 5, 1),
('000119cult', 'TUTOR', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 800, 51, 1),
('000119vent', 'Inzonorizado 125', 'unidad', 'Ventilación', 'Siembra Chile', 'KONKA GROW', 23500, 1, 1),
('00011Bio', 'Alga Mix 250 Ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 8900, 1, 5),
('00011Hesi', 'Super Vit 10 ml', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 14000, 2, 5),
('000120acce', 'PH Gota', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 4500, 1, 1),
('000120cult', 'Geotextil 25', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 4000, 1, 1),
('000121cult', 'Buffer 7', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 3500, 2, 1),
('000121vent', 'Inzonorizado 1505  MT', 'unidad', 'Ventilación', 'Siembra Chile', 'KONKA GROW', 28500, 1, 1),
('000122acce', 'CO2 Tags', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 12500, 1, 1),
('000122cult', 'Buffer 4', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 3500, 11, 1),
('000123cult', 'Buffer EC ', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 3500, 5, 1),
('000123seed', 'White Draft Budda', 'unidad', 'Semillas', 'Buddha Seeds', 'KONKA GROW', 15000, 999, 1),
('000124seed', 'Syrup Buddha', 'unidad', 'Semillas', 'Buddha Seeds', 'KONKA GROW', 15000, 900, 1),
('000125cult', 'Clon Air', 'unidad', 'Cultivo', 'Sin Marca', 'KONKA GROW', 42000, 1, 1),
('000127cult', 'Filtro Osmos is Neptune', 'unidad', 'Cultivo', 'Odorsok', 'KONKA GROW', 50000, 4, 1),
('00012Bio', 'Alga Mix 500 Ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 17000, 6, 5),
('00012Hesi', 'Hesi Pack', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 14000, 14, 5),
('0001ata', 'Try pack Gbf Atami', 'unidad', 'Fertilizantes', 'Atami', 'KONKA GROW', 15000, 15, 5),
('0001CANNA', 'Canna BOOST', 'Botella', 'Fertilizantes', 'Canna', 'KONKA GROW', 30500, 1, 5),
('0001Ge0boost', 'Guano Rojo', 'Botella', 'Fertilizantes', 'Geo BOOST', 'KONKA GROW', 5000, 11, 5),
('0001GOLD', 'SOIL A', 'Botella', 'Fertilizantes', 'GOLD Label', 'KONKA GROW', 70, 4, 5),
('0001HESI', 'FOSFORO PLUS 500ML', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 90, 2, 5),
('0001ORGANIK', 'ENHACER', 'Botella', 'Fertilizantes', 'ORGANIK', 'KONKA GROW', 80, 11, 5),
('0002Ata', 'Mikro Kit aLL IN ONE TERRA', 'unidad', 'Fertilizantes', 'Atami', 'KONKA GROW', 39000, 2, 2),
('0002Bio', 'Fish Mix 250 ml ', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 4500, 6, 5),
('0002GEOBOOST', 'IMPULSO 750 ML', 'Botella', 'Fertilizantes', 'Geo BOOST', 'KONKA GROW', 7000, 8, 5),
('0002GOLD', 'SOILB + SOIL A', 'Botella', 'Fertilizantes', 'GOLD Label', 'KONKA GROW', 31000, 4, 5),
('0002HESI', 'FOSFORO PLUS 1 LT', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 11000, 3, 5),
('0002ORGANIK', 'FEED', 'Botella', 'Fertilizantes', 'ORGANIK', 'KONKA GROW', 80, 7, 5),
('0003', 'Bio Bloombastic 100 ml', 'Botella', 'Fertilizantes', 'Atami', 'KONKA GROW', 19000, 8, 2),
('00032123', 'Tripack Cannabiogen', 'unidad', 'Fertilizantes', 'Cannabiogen', 'KONKA GROW', 48000, 2, 5),
('0003Bio', 'Fish Mix 500 Ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 8300, 2, 5),
('0003GEOBOOST', 'CAOS VITRAL', 'Botella', 'Fertilizantes', 'Geo BOOST', 'KONKA GROW', 5000, 4, 5),
('0003GOLD', 'PK ULTRA', 'Botella', 'Fertilizantes', 'GOLD Label', 'KONKA GROW', 32000, 4, 5),
('0003hesi', 'PK 13 14 500 ML', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 90, 16, 5),
('0003ORGANIK', 'PREVENTION', 'Botella', 'Fertilizantes', 'ORGANIK', 'KONKA GROW', 80, 2, 5),
('0004Bio', 'Fish Mix 1 lt', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 14500, 7, 5),
('0004GEOBOOST', 'KRATOS', 'Botella', 'Fertilizantes', 'Geo BOOST', 'KONKA GROW', 4000, 43, 5),
('0004HESI', 'PK 13 14 1 LT', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 90, 1, 5),
('0005Bio', 'Bio Grow 250 Ml Bio bizz', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 1, 1, 5),
('0005HESI', 'COMPLEJO DE FLORACION 500 ML', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 11000, 4, 5),
('0006ATA', 'Root fast 250 ml', 'Botella', 'Fertilizantes', 'Atami', 'KONKA GROW', 12500, 1, 6),
('0006Bio', 'Bio Grow 500 Ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 8300, 9, 5),
('0006Hesi', 'Complejo Floración 1 lt', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 90, 5, 5),
('00074603', 'Jabon Potasico', 'Botella', 'Fertilizantes', 'ORGANIK', 'KONKA GROW', 11000, 5, 4),
('0007ATA', 'Root Fast 100 ml', 'Botella', 'Fertilizantes', 'Atami', 'KONKA GROW', 5000, 4, 5),
('0007Bio', 'Bio Bloom 250 Ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 1, 1, 5),
('0007Hesi', 'TNT CRECIEMIENTO 500 ML', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 90, 7, 5),
('0008Bio', 'Bio Bloom 500 Ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 8300, 1, 5),
('0008Hesi', 'TNT CRECIMIENTO 1 LT', 'Botella', 'Fertilizantes', 'Hesi', 'KONKA GROW', 12500, 4, 5),
('0009 Bio', 'Top Max 250 ml', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 9500, 14, 5),
('099123000', 'Bloombastic 100cc', 'Botella', 'Fertilizantes', 'Atami', 'KONKA GROW', 19000, 16, 10),
('099890100', 'Bloombastic 50cc', 'Botella', 'Fertilizantes', 'Atami', 'KONKA GROW', 13500, 1, 5),
('12', 'Top Bloom 1Lt', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 10500, 11, 5),
('13', 'Top Bud 100Ml', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 11000, 4, 3),
('14', 'Top Veg 1Lt.', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 8500, 34, 10),
('15', 'Top Candy 1Lt', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 8500, 16, 8),
('16', 'Top Barrier 1Lt.', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 7500, 4, 4),
('17', 'Big One 250ml', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 10500, 4, 4),
('18', 'Deeper Underground 250ml.', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 8500, 20, 12),
('19', 'Green Explosion 250ml', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 10500, 5, 4),
('20', 'Cyclone 50ml', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 7500, 6, 4),
('21', 'Top Vulcan 700gr.', 'saco', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 11500, 1, 1),
('21314', 'Tierra de Diatomeas', 'unidad', 'Fertilizantes', 'THC', 'KONKA GROW', 7000, 4, 4),
('22', 'Top Vulcan 4k', 'saco', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 28000, 4, 2),
('23|', 'Super Guano', 'saco', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 11000, 16, 4),
('24', 'Try Pack Top Crop', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 12500, 15, 8),
('26', 'Fast Food Organic 750ml', 'Botella', 'Fertilizantes', 'Top Crop', 'KONKA GROW', 16000, 7, 6),
('27', 'Fast Food Mineral 750ml', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 14000, 4, 4),
('28', 'Bio Clone 100ml.', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 12000, 7, 6),
('30', 'Starter kits', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 52000, 2, 1),
('30052765', 'papelillo ocb negro 1 1/4 ', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 22, 10),
('30062948', 'OCB Premiun 100U', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 650, 999, 4),
('30068926', 'papelillo ocb  x-pert 1 1/4 griz', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 122, 10),
('30071704', 'OCB X-Pert Blue', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 350, 999, 5),
('30087859', 'papelillo ocb organico 1 1/4', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 64, 10),
('30104907', 'papelillo ocb no blanqueado 1 1/4', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 29, 10),
('30104914', 'OCB UMBLEACHED', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 350, 999, 3),
('30119550', 'papelillo ocb ultimate 1 1/4', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 94, 10),
('30135932', 'OCB Ultimate', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 350, 999, 5),
('4008321387585', 'Ampolleta Sodio 150', 'unidad', 'Iluminación', 'OSRAM', 'KONKA GROW', 5000, 13, 4),
('420auto1', '4:20 Genetics Autofloreciente Unidad', 'unidad', 'Semillas', '4:20 Genetics', 'KONKA GROW', 5000, 244, 200),
('420auto10', '4:20 Genetics Autofloreciente Pack 10', 'unidad', 'Semillas', '4:20 Genetics', 'KONKA GROW', 32000, 23, 20),
('420auto3', '4:20 Geneticspack 3 ', 'unidad', 'Semillas', '4:20 Genetics', 'KONKA GROW', 12000, 102, 80),
('420fem1', '4:20 Genetics Feminizada pack 1', 'unidad', 'Semillas', '4:20 Genetics', 'KONKA GROW', 5000, 222, 200),
('420fem10', '4:20 Genetics Feminizada pack 10', 'unidad', 'Semillas', '4:20 Genetics', 'KONKA GROW', 32000, 60, 40),
('420fem3', '4:20 Genetics Feminizada pack 3', 'unidad', 'Semillas', '4:20 Genetics', 'KONKA GROW', 12000, 102, 80),
('4718343301956', 'Linterna Green Eye VDL', 'unidad', 'Accesorios', 'Hortitec', 'KONKA GROW', 9000, 5, 5),
('4823016212399', 'Extractor lineal  de 150', 'unidad', 'Ventilación', 'Vents', 'KONKA GROW', 19000, 96, 10),
('4823016238160', 'Extractor TT 100  dual', 'unidad', 'Ventilación', 'Vents', 'KONKA GROW', 32500, 5, 4),
('4891305180179', 'Encendedor Neon', 'unidad', 'Tabaquería', 'Neon', 'KONKA GROW', 300, 999, 5),
('5025644433507', 'CLONEX 50ML', 'Botella', 'Fertilizantes', 'Growth Tecnology', 'KONKA GROW', 9000, 13, 6),
('5060240030002', 'Gramera  Eternity Clasic', 'unidad', 'Parafernalia', 'Kenex', 'KONKA GROW', 10000, 99, 10),
('5060240030194', 'Gramera CD', 'unidad', 'Parafernalia', 'Kenex', 'KONKA GROW', 14000, 100, 10),
('624493111472', 'Spray Apple', 'Botella', 'Ventilación', 'Ona', 'KONKA GROW', 12500, 1, 2),
('624493122072', 'Gel 1K', 'unidad', 'Ventilación', 'Ona', 'KONKA GROW', 18000, 3, 3),
('624493411428', 'Ona Spray 250ml.', 'unidad', 'Parafernalia', 'Ona', 'KONKA GROW', 12500, 100, 10),
('624493911454', 'Ona Spray', 'Botella', 'Ventilación', 'Ona', 'KONKA GROW', 12500, 5, 4),
('638170007958', 'Calcium 500Gr.', 'unidad', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 14500, 7, 6),
('644536226314', 'Blunt Peach', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 17, 3),
('644536226512', 'Blunt Piña Colado', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 7, 3),
('644536226611', 'Blunt Cosmopolitan', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 20, 3),
('644536226710', 'Blunt Berries', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 3, 3),
('644536227311', 'Blunt Mango', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 6, 3),
('644536227410', 'Blunt Wild Honey', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 23, 3),
('644536227618', 'Blunt  wet cherry', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 13, 10),
('644536227816', 'Blunt Natural Flavor', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 8, 3),
('644536247210', 'Blunt Champagne', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 17, 3),
('644536248316', 'Blunt White Grape', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 8, 3),
('644536384410', 'papelillo blunt 1 1/4 regular', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 850, 17, 9),
('644536384618', 'papelillo Blun 1 1/4 umbliched', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 850, 3, 10),
('644536384717', 'Papelillo Blunt 1 1/14 Premium', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 850, 37, 10),
('644536840022', 'papel de celulosa', 'unidad', 'Tabaquería', 'c-thru', 'KONKA GROW', 1500, 22, 10),
('648658033294', 'Randy´s Gris', 'unidad', 'Tabaquería', 'Randy´s', 'KONKA GROW', 1500, 37, 10),
('648658200016', 'papelillo cafe', 'unidad', 'Tabaquería', 'Randy´s', 'KONKA GROW', 1500, 17, 10),
('670829207593', 'Bud fuel pro 500ml', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 13990, 1, 1),
('670829207807', 'MonsterGrow Pro de 130gr', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 13000, 6, 6),
('670829207913', 'Vitamax Plus 500ml', 'Botella', 'Fertilizantes', 'Grotek', 'KONKA GROW', 23500, 2, 2),
('670829208200', 'Carbo Max  de 100gr. Suplemento de potasio', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 7000, 1, 1),
('670829208392', 'Monster Blom  130gr.', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 10500, 6, 6),
('670829208514', 'Pro-Silicate de 1lt.( Proporciona silicio y potasa a los vegetales)', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 11000, 5, 4),
('670829208729', 'Solo-Tek Bloom 1L', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 17500, 3, 1),
('670829208736', 'Solo - tek Grow 500ml', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 12500, 3, 3),
('670829806116', 'The Final Flush 1lt.  Lavado de raices', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 12000, 4, 4),
('670829808042', 'Insta Green 1Lt.', 'Botella', 'Fertilizantes', 'Grotek', 'KONKA GROW', 14500, 5, 4),
('670829815132', 'Cal- Max de 1lt. ( corrector de  deficiencias)', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 12000, 5, 4),
('6943205401001', 'ODORSOK 100', 'unidad', 'Ventilación', 'Odorsok', 'KONKA GROW', 23500, 6, 4),
('6943205401002', 'Odorsok 125', 'unidad', 'Ventilación', 'Odorsok', 'KONKA GROW', 27500, 11, 4),
('6943205401003', 'Odorsok 150', 'unidad', 'Ventilación', 'Odorsok', 'KONKA GROW', 46000, 100, 1),
('716165176107', 'Raw Connosieur', 'unidad', 'Tabaquería', 'Raw', 'KONKA GROW', 1250, 100, 10),
('716165177296', 'papelillo elements', 'unidad', 'Tabaquería', 'Elements', 'KONKA GROW', 600, 10, 10),
('716165177357', 'Raw Sabana', 'unidad', 'Tabaquería', 'Raw', 'KONKA GROW', 1000, 20, 10),
('716165178873', 'tips (boquillas de papel prepicado )', 'unidad', 'Tabaquería', 'Elements', 'KONKA GROW', 600, 76, 10),
('716165178897', 'Elements Artesano', 'unidad', 'Tabaquería', 'Elements', 'KONKA GROW', 1250, 15, 10),
('716165178910', 'Elements Artesano Sabana', 'unidad', 'Tabaquería', 'Elements', 'KONKA GROW', 2500, 100, 10),
('716165200246', 'Raw Gummed Tips', 'unidad', 'Tabaquería', 'Raw', 'KONKA GROW', 800, 100, 10),
('716165200543', 'RAW Artesano Natura Organico King Zize', 'unidad', 'Tabaquería', 'Raw', 'KONKA GROW', 2500, 15, 3),
('716165201038', 'Raw Artesano', 'unidad', 'Tabaquería', 'Raw', 'KONKA GROW', 1500, 100, 10),
('77912602', 'Lion Rolling Circus Transparente Celulosa', 'unidad', 'Tabaquería', 'Lion Rolling Circus', 'KONKA GROW', 1750, 48, 3),
('7898915411041', 'papel de celulosa brasileño', 'unidad', 'Tabaquería', 'aledinha', 'KONKA GROW', 1500, 24, 100),
('8015358044509', 'Medidor PH y LUZ ', 'unidad', 'Medición y Control', 'VDL', 'KONKA GROW', 18000, 2, 1),
('802359000430', 'Bobeda de Curado 67 gr.', 'saco', 'Cultivo', 'Integra Boost', 'KONKA GROW', 3500, 100, 10),
('840470001486', 'Can Lite 100/125', 'unidad', 'Ventilación', 'Can-Filter', 'KONKA GROW', 32000, 2, 2),
('8412765505447', 'Clipper Clasico', 'unidad', 'Tabaquería', 'Clipper', 'KONKA GROW', 500, 40, 3),
('8412765508905', 'Clipper Super Lighter', 'unidad', 'Tabaquería', 'Clipper', 'KONKA GROW', 800, 179, 3),
('8435405304545', 'Manta de Co2', 'unidad', 'Cultivo', 'Co2Boost', 'KONKA GROW', 7500, 100, 10),
('8436554710904', 'Cooltube 125S', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 28000, 1, 4),
('8436554711000', 'Cooltube 125M', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 32000, 9, 6),
('8436554760015', 'Malla Sgrog', 'unidad', 'Cultivo', 'Garden High', 'KONKA GROW', 6000, 100, 10),
('8436554760930', 'Lentes protectores sodio', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 10500, 4, 4),
('8436554761081', 'Reflector IndorMatrix', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 50000, 3, 5),
('8436554761166', 'extractor lineal 100', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 13500, 13, 8),
('8436554761173', 'Extractor lineal 125', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 15500, 9, 6),
('8436554761180', 'Extractor Lineal 150', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 19000, 9, 6),
('8436554761234', ' Extractor TT dual125', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 34500, 5, 2),
('8436554761241', 'Extractor Dual 150', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 42000, 2, 2),
('8436554761760', 'Termohigrometro Premium', 'unidad', 'Medición y Control', 'Garden High', 'KONKA GROW', 12500, 1, 4),
('8436554761845', 'Ventilador Pinza 15w', 'unidad', 'Vaporizadores', 'Garden High', 'KONKA GROW', 11000, 33, 24),
('8438479305814', 'Organic Bloom 500ML', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 8200, 8, 6),
('8438479308419', 'Organic Grow 500ML', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 8500, 4, 6),
('8438479308617', 'Organic Grow 250ML', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 6200, 1, 4),
('8438479308815', 'Organic Bloom 250ML', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 6200, 7, 6),
('8438479309010', 'Organic PK Booster 250ML', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 10200, 7, 6),
('8438479310818', 'Bloom Stimulator 30ml', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 10100, 5, 4),
('8438479311211', 'Final Solution 30ml', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 13500, 1, 4),
('8438479311617', 'Auto Stimulator', 'Botella', 'Fertilizantes', 'B.A.C.', 'KONKA GROW', 20500, 1, 1),
('8506710000146', 'Tabaqueria Papel amico', 'unidad', 'Tabaquería', 'amico', 'KONKA GROW', 500, 100, 5),
('8716722020128', 'Tips The Bulldog', 'unidad', 'Tabaquería', 'The Bulldog', 'KONKA GROW', 500, 999, 3),
('8717202906062', 'Super Skunk', 'unidad', 'Semillas', 'Sensi Seeds', 'KONKA GROW', 22000, 100, 10),
('8717202906079', 'Skunk #1', 'unidad', 'Semillas', 'Sensi Seeds', 'KONKA GROW', 22000, 100, 10),
('8717202906178', 'Northern Lights', 'unidad', 'Semillas', 'Sensi Seeds', 'KONKA GROW', 27000, 100, 10),
('8717202907007', 'Bib Bud', 'unidad', 'Semillas', 'Sensi Seeds', 'KONKA GROW', 35000, 100, 10),
('8717202907021', 'Silver Haze #9', 'unidad', 'Semillas', 'Sensi Seeds', 'KONKA GROW', 35000, 100, 10),
('8717202907038', 'Jack Flash #5', 'unidad', 'Semillas', 'Sensi Seeds', 'KONKA GROW', 39000, 100, 10),
('8717202907380', 'Sensi Skunk', 'unidad', 'Semillas', 'Sensi Seeds', 'KONKA GROW', 20000, 100, 10),
('8717524950682', 'Boost Accelerator', 'Botella', 'Fertilizantes', 'Canna', 'KONKA GROW', 30500, 1, 2),
('8717524958541', 'PK 13/14', 'Botella', 'Fertilizantes', 'Canna', 'KONKA GROW', 10500, 1, 5),
('8717524958589', 'Rhizotonic', 'Botella', 'Fertilizantes', 'Canna', 'KONKA GROW', 20000, 2, 5),
('8717524958626', 'Cannazym', 'Botella', 'Fertilizantes', 'Canna', 'KONKA GROW', 11500, 10, 5),
('8717662944307', 'Terra Flores 500 ml', 'Botella', 'Fertilizantes', 'Canna', 'KONKA GROW', 11500, 1, 5),
('8717662944314', 'Terra Vega 500 ml', 'Botella', 'Fertilizantes', 'Canna', 'KONKA GROW', 11500, 5, 5),
('8717677610518', 'Dutch Dragon', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 22000, 100, 10),
('8717677610532', 'Nebula', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 22000, 100, 10),
('8717677610624', 'Delahaze', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 22000, 100, 10),
('8717677610747', 'Pandora', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 18000, 2, 10),
('8717677610884', 'Vertigo', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 18500, 4, 10),
('8717677610914', 'Automaria II', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 18000, 2, 5),
('8717677610990', 'Belladonna', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 24000, 100, 10),
('8717677611140', 'Auto Wappa', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 18000, 1, 5),
('8717677611157', 'Auto White Berry', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 18500, 2, 5),
('8717677611300', 'Durga Mata II CBD', 'unidad', 'Semillas', 'Paradise Seeds', 'KONKA GROW', 22000, 100, 10),
('8717729825853', 'Himalaya Gold', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 18000, 100, 8),
('8717729825891', 'The Church', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 15000, 100, 10),
('8717729825921', 'Lemon Skunk', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 17500, 100, 10),
('8717729825976', 'The Doctor', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 17000, 100, 10),
('8717729827031', 'Fertilizante Power Feeding Sachet Long Flowering 1K', 'unidad', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 23000, 1, 1),
('8717729827208', 'Pure Kush', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 17000, 100, 10),
('8717729827383', 'Power Feeding hibrida sachet 10Gr.', 'unidad', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 500, 162, 50),
('8717729827390', 'Sachet Grow 10 Gr.', 'saco', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 500, 100, 50),
('8717729827406', 'Powder Feeding  long flowering sachet 10Gr', 'unidad', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 500, 156, 50),
('8717729827437', 'Fertilizante Power Feeding Sachet Mostly Indica 10Gr', 'unidad', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 500, 41, 50),
('8717729827536', 'Norther Lights NL5 Haze Mist', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 20000, 100, 10),
('8717729827598', 'Arjan´s Ultra Haze#2', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 22000, 5, 5),
('8717729827611', 'Nevilles Haze', 'unidad', 'Semillas', 'Green House', 'KONKA GROW', 24000, 100, 10),
('8717729828908', 'Fertilizante Power Feeding Sachet Grow 125 Gr.', 'unidad', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 12500, 6, 6),
('8717729829547', 'Booster PK', 'saco', 'Fertilizantes', 'Powder Feeding', 'KONKA GROW', 12500, 6, 6),
('8718104124103', 'Worms ', 'unidad', 'Fertilizantes', 'Plagron', 'KONKA GROW', 7500, 3, 6),
('8718403232127', 'Try pack Biobizz Indoor', 'Botella', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 15000, 11, 10),
('8718403232134', 'Try Pack Outdoor', 'unidad', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 15000, 6, 10),
('8718403232141', 'Try pack  Stimulant', 'unidad', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 27000, 2, 11),
('8718405837627', 'Leaf. Coat Spray Foliar  Preventivo 750 ml', 'unidad', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 16000, 5, 10),
('8719244303878', 'Bloombastic  pk14-15', 'unidad', 'Fertilizantes', 'Atami', 'KONKA GROW', 19000, 18, 5),
('888400010641', 'Complete 3/1', 'unidad', 'Vaporizadores', 'Atmos', 'KONKA GROW', 38000, 100, 8),
('888400012560', 'the Klin  Vaporizador ', 'unidad', 'Vaporizadores', 'Atmos', 'KONKA GROW', 50000, 100, 10),
('888400014083', 'Jump Vaporizador', 'unidad', 'Vaporizadores', 'Atmos', 'KONKA GROW', 65000, 100, 10),
('Amnesiaxxl1', 'Amnesia XXl Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8500, 2, 2),
('Amnesiaxxl3', 'Amnesia XXl pack3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24400, 3, 2),
('Ballast 150', 'Ballast Solar Ray 150w.', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 32500, 9, 4),
('Ballast 250', 'Ballast Solar Ray 250w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 37000, 10, 4),
('Ballast 400', 'Ballast Solar Ray 400w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 42500, 12, 4),
('Ballast 600', 'Ballast Solar Ray 600w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 49500, 9, 4),
('Ballast Elec 1000', 'Ballast Electronico Grow Genetics 1000w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 180000, 2, 1),
('Ballast Elec 400', 'Ballast Electrónico Grow Genetics 400w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 80000, 2, 1),
('ballast Elec 600', 'Ballast Electrónico Grow Genetics 600w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 105000, 1, 1),
('BlueAmnAuto3', 'Blue Amnesia Autofloreciente Pack de 3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 21000, 1, 1),
('Carpa Garden 60', 'Carpa Garden High Pro 60', 'unidad', 'Accesorios', 'Garden High', 'KONKA GROW', 75000, 4, 2),
('Carpa Garden100', 'Carpa Garden High 100', 'unidad', 'Armarios', 'Garden High', 'KONKA GROW', 95000, 2, 2),
('Carpa Garden240L', 'Carpa Garden High 120x240', 'unidad', 'Armarios', 'Garden High', 'KONKA GROW', 200000, 2, 2),
('Carpa Grow60', 'Carpa Grow Genetics 60', 'unidad', 'Armarios', 'Grow Genetics', 'KONKA GROW', 55000, 2, 4),
('Carpa Probox100', 'Carpa Probax Basic 100', 'unidad', 'Armarios', 'Garden High', 'KONKA GROW', 75000, 4, 4),
('Carpa Probox120', 'Carpa Probox Basic 120', 'unidad', 'Armarios', 'Garden High', 'KONKA GROW', 85000, 4, 1),
('Carpa Probox60', 'Carpa ProBox Basic 60', 'unidad', 'Accesorios', 'Garden High', 'KONKA GROW', 55000, 4, 4),
('Carpa Probox80', 'Carpa Probox Basic 80', 'unidad', 'Armarios', 'Garden High', 'KONKA GROW', 65000, 1, 4),
('Carpa Propagadora', 'Carpa Propagadora ', 'unidad', 'Armarios', 'Garden High', 'KONKA GROW', 45000, 1, 1),
('CBDhazeauto', 'Haze CBD Autofloreciente pack3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24500, 1, 1),
('CFL 150', 'Ampolleta Fluorecente Compacto 150w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 29500, 2, 2),
('Cheessexxl1', 'Cheesse XXL unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8200, 2, 4),
('Cheessexxl3', 'Cheesse XXl pack3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24000, 3, 2),
('Cooltube 150', 'Cooltube 150', 'unidad', 'Iluminación', 'Siembra Chile', 'KONKA GROW', 30000, 4, 1),
('Cultibox 80', 'Carpa Cultibox 80', 'unidad', 'Armarios', 'Cultibox', 'KONKA GROW', 65000, 1, 2),
('Cultibox Light 120', 'Carpa Cultibox Light 120', 'unidad', 'Armarios', 'Cultibox Light', 'KONKA GROW', 69000, 2, 2),
('DELTA NUEVE 150ml', 'Delta 9 150ml', 'Botella', 'Fertilizantes', 'Cannabiogen', 'KONKA GROW', 9000, 24, 5),
('DELTA NUEVE 500CC', 'DELTA NUEVE 500CC', 'unidad', 'Fertilizantes', 'Cannabiogen', 'KONKA GROW', 19000, 8, 5),
('delta10', 'Delta 10 500cc', 'Botella', 'Fertilizantes', 'Cannabiogen', 'KONKA GROW', 15000, 13, 5),
('delta8', 'Delta 8 500cc', 'Botella', 'Fertilizantes', 'Cannabiogen', 'KONKA GROW', 15000, 11, 5),
('Dinamexauto1', 'Dinamex Autofloreciente x1', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8100, 1, 1),
('Dinamexauto3', 'Dinamex Autofloreciente x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24000, 1, 1),
('Dual 1000', 'HPS 1000w AUVL Alemana', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 58000, 2, 1),
('Dual 150', 'Dual 150w HPS', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 10500, 13, 6),
('Dual 250', 'Dual 250w HPS', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 11500, 11, 8),
('Dual 400', 'Dual 400w HPS', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 14500, 11, 8),
('Dual 600', 'Dual 600w HPS', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 20000, 1, 6),
('Filtro Proactive', 'Filtro Carbon 100', 'unidad', 'Vaporizadores', 'Garden High', 'KONKA GROW', 30000, 7, 4),
('Filtro ProActive125', 'Filtro Carbon 125', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 30500, 4, 6),
('Geotextil 11', 'Maceta Geotextil 11Lt', 'unidad', 'Cultivo', 'Garden High', 'KONKA GROW', 2200, 37, 12),
('Geotextil 7', 'Maceta Geotextil 7Lt', 'unidad', 'Cultivo', 'Garden High', 'KONKA GROW', 1800, 13, 12),
('GEOTEXTIL15', 'Maceta Geotextil de 15 Lts', 'unidad', 'Accesorios', 'Agricola Mercosur', 'KONKA GROW', 3000, 2, 5),
('Hazexxl1', 'Haze XXL Unidad', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8400, 20, 2),
('Hazexxl3', 'Haze XXl pack3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 8200, 3, 2),
('HM 150', 'Haluro Metal 150w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 8400, 4, 8),
('HM 250', 'Haluro Metal 250w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 9000, 19, 8),
('HM 400', 'Haluro Metal 400w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 10000, 17, 8),
('HPS 400', 'Sodio 400w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 10000, 10, 8),
('HPS 600', 'Sodio 600w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 20000, 12, 6),
('HPS150', 'Sodio 150w.', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 8400, 16, 8),
('HPS250', 'Sodio 250w', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 9000, 11, 8),
('Koa 125', 'Filtro Carbon Koalair 125', 'unidad', 'Ventilación', 'Koa', 'KONKA GROW', 50000, 4, 2),
('Koa 150', 'Filtro Carbon Koalair 150', 'unidad', 'Ventilación', 'Koa', 'KONKA GROW', 65000, 2, 2),
('Koa100', 'Filtro Koalair', 'unidad', 'Ventilación', 'Koa', 'KONKA GROW', 40000, 1, 2),
('LED 4', 'Led Thunder 4', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 155000, 4, 2),
('LED 8', 'Led Thunder 8', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 295000, 1, 1),
('Maceta 0,75', 'Macetero 0,75Lt.', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 1000, 104, 60),
('Maceta 11', 'Macetero 11Lt', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 1500, 369, 200),
('Maceta 18', 'Macetero 18Lt', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 3600, 62, 40),
('Maceta 3,5', 'Macetero 3,5Lt.', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 1200, 22, 40),
('Maceta 5,8', 'Macetero 5,8Lt', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 1350, 200, 100);
INSERT INTO `tb_productos` (`codprod`, `detalleprod`, `unidad`, `rubro`, `marca`, `proveedor`, `precioprod`, `stock`, `stockmin`) VALUES
('Maceta 7', 'Macetero 7Lt', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 1400, 254, 200),
('Matrix', 'Reflector Matrix', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 50000, 1, 1),
('Mega', 'Mega Pack Grotek', 'unidad', 'Fertilizantes', 'Grotek', 'KONKA GROW', 115000, 2, 1),
('Nirvana Auto', 'Nirvana Autofloreciente', 'unidad', 'Semillas', 'Nirvana', 'KONKA GROW', 15000, 6, 6),
('Nirvana Fem', 'Nirvana Feminizada', 'unidad', 'Semillas', 'Nirvana', 'KONKA GROW', 15000, 9, 8),
('Nirvana Reg', 'Nirvana Regular', 'unidad', 'Semillas', 'Nirvana', 'KONKA GROW', 17000, 3, 6),
('Ogkushautox3', 'OG Kush Autofloreciente x3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 24900, 6, 4),
('ooo102acce', 'Polea XL', 'unidad', 'Accesorios', 'Sin Marca', 'KONKA GROW', 6500, 6500, 1),
('Osram 150', 'Ampolleta Osram 150w', 'unidad', 'Iluminación', 'OSRAM', 'KONKA GROW', 5500, 19, 6),
('Plato 11-18', 'Plato para maceta de 11-18 Lts', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 1100, 73, 5),
('PLATO7', 'Plato para Maceta de 7 ', 'unidad', 'Cultivo', 'Agricola Mercosur', 'KONKA GROW', 900, 12, 5),
('PLATO7-11', 'Plato para maceta de 7 o 11 Lts', 'unidad', 'Accesorios', 'Agricola Mercosur', 'KONKA GROW', 1000, 48, 5),
('potgrow', 'Jabon Potasico', 'Botella', 'Fertilizantes', 'Grow Genetics', 'KONKA GROW', 5500, 1, 4),
('Pyramid Auto', 'Piramyd Autofloreciente unidad', 'unidad', 'Semillas', 'Pyramid', 'KONKA GROW', 5000, 171, 100),
('Pyramid Auto31', 'Pyramid Autofloreciente 3+1', 'unidad', 'Semillas', 'Pyramid', 'KONKA GROW', 14000, 53, 40),
('Pyramid Fem', 'Pyramid Feminizada Unidad', 'unidad', 'Semillas', 'Pyramid', 'KONKA GROW', 6500, 106, 100),
('Pyramid Fem31', 'Pyramid Feminizada 3+1', 'unidad', 'Semillas', 'Pyramid', 'KONKA GROW', 16000, 5, 25),
('ReFlector', 'Reflector Estuco', 'unidad', 'Iluminación', 'Grow Genetics', 'KONKA GROW', 10000, 23, 14),
('Roadauto3', 'Roadruner Autofloreciente pack3', 'unidad', 'Semillas', 'Dinafem', 'KONKA GROW', 18500, 1, 1),
('SamsaraAuto1', 'Samsara Autofloreciente Unidad', 'unidad', 'Semillas', 'Samsara', 'KONKA GROW', 4000, 90, 1),
('SamsaraAuto3', 'Samsara Autofloreciente Pack3', 'unidad', 'Semillas', 'Samsara', 'KONKA GROW', 10000, 19, 1),
('SamsaraFem1', 'Samsara Feminizada Pack 1', 'unidad', 'Semillas', 'Samsara', 'KONKA GROW', 5000, 8, 1),
('SamsaraFem3', 'Samsara Feminizada Pack3', 'unidad', 'Semillas', 'Samsara', 'KONKA GROW', 10500, 74, 1),
('TCSC50L', 'Sustrato Top Crop Complete Mix 50Lt', 'saco', 'Sustrato', 'Top Crop', 'KONKA GROW', 12000, 101, 10),
('Tijera 3', 'Tijera Recta', 'unidad', 'Accesorios', 'Garden High', 'KONKA GROW', 6500, 1, 1),
('Tijera Eco', 'Tijera Poda Económica', 'unidad', 'Accesorios', 'Siembra Chile', 'KONKA GROW', 1000, 12, 12),
('Tijera1', 'Tijera 1 Ojo', 'unidad', 'Accesorios', 'Garden High', 'KONKA GROW', 4500, 5, 2),
('Tijera2', 'Tijera Curva', 'unidad', 'Accesorios', 'Garden High', 'KONKA GROW', 6500, 4, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_proveedor`
--

CREATE TABLE `tb_proveedor` (
  `nomproveedor` varchar(20) NOT NULL,
  `rutempresa` varchar(10) NOT NULL,
  `fonoproveedor` int(15) NOT NULL,
  `direccion` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_proveedor`
--

INSERT INTO `tb_proveedor` (`nomproveedor`, `rutempresa`, `fonoproveedor`, `direccion`) VALUES
('KONKA GROW', '66666666-6', 976696957, 'Barros Arana 54');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_rubros`
--

CREATE TABLE `tb_rubros` (
  `codrubro` int(6) NOT NULL,
  `nom_rubro` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_rubros`
--

INSERT INTO `tb_rubros` (`codrubro`, `nom_rubro`) VALUES
(36, 'Accesorios'),
(31, 'Armarios'),
(34, 'Cultivo'),
(37, 'Extracciones'),
(26, 'Fertilizantes'),
(25, 'Iluminación'),
(30, 'Medición y Control'),
(32, 'Parafernalia'),
(28, 'Semillas'),
(27, 'Sustrato'),
(33, 'Tabaquería'),
(35, 'Vaporizadores'),
(29, 'Ventilación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_salidas`
--

CREATE TABLE `tb_salidas` (
  `codsalida` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `usuario` varchar(20) NOT NULL,
  `motivo` varchar(20) NOT NULL,
  `nulo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_salidas`
--

INSERT INTO `tb_salidas` (`codsalida`, `fecha`, `usuario`, `motivo`, `nulo`) VALUES
(10000001, '2017-08-11', 'Admin', 'robo', 0),
(10000002, '2017-08-13', 'Admin', 'robo', 0),
(10000003, '2017-08-13', 'Admin', 'robo', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_semilla`
--

CREATE TABLE `tb_semilla` (
  `codprod` varchar(20) NOT NULL,
  `indica` int(2) NOT NULL,
  `sativa` int(2) NOT NULL,
  `rudelaris` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_semilla`
--

INSERT INTO `tb_semilla` (`codprod`, `indica`, `sativa`, `rudelaris`) VALUES
('8717202906079', 60, 40, 0),
('8717729825976', 70, 30, 0),
('8717729825921', 50, 50, 0),
('8717729827208', 70, 30, 0),
('8717729827536', 30, 70, 0),
('8717729827611', 10, 90, 0),
('8717729825891', 60, 40, 0),
('8717729825853', 70, 30, 0),
('8717677610624', 70, 30, 0),
('8717677611157', 70, 30, 0),
('8717677610518', 75, 25, 0),
('8717677610747', 90, 10, 0),
('8717677610914', 50, 50, 0),
('8717677610990', 40, 60, 0),
('8717677610532', 40, 60, 0),
('8717677610884', 50, 50, 0),
('8717202906062', 60, 40, 0),
('8717202907380', 80, 20, 0),
('8717202906178', 90, 10, 0),
('8717202907021', 20, 80, 0),
('8717202907038', 35, 65, 0),
('8717202907007', 85, 15, 0),
('8717677611300', 90, 10, 0),
('8717677611140', 50, 50, 0),
('000000445', 20, 80, 0),
('000000438', 100, 0, 0),
('000000437', 40, 60, 0),
('000000436', 80, 20, 0),
('000000434', 40, 60, 0),
('000000435', 80, 20, 0),
('000000433', 40, 60, 0),
('000000432', 0, 100, 0),
('000000431', 10, 90, 0),
('000000430', 85, 15, 0),
('000000328', 20, 80, 0),
('000000334', 20, 80, 0),
('000000329', 20, 80, 0),
('000000330', 50, 50, 0),
('000000335', 50, 50, 0),
('000000333', 70, 30, 0),
('000000331', 85, 15, 0),
('000000332', 90, 10, 0),
('000000410', 100, 0, 0),
('8717729827598', 30, 70, 0),
('000000414', 50, 50, 0),
('000000415', 60, 40, 0),
('000000416', 0, 100, 0),
('000000417', 20, 80, 0),
('000000418', 25, 75, 0),
('000000419', 30, 70, 0),
('000000420', 70, 30, 0),
('000000421', 70, 30, 0),
('000000422', 40, 60, 0),
('000000423', 70, 30, 0),
('000000424', 70, 30, 0),
('000000425', 100, 0, 0),
('000000426', 40, 60, 0),
('000000427', 70, 30, 0),
('000000428', 20, 80, 0),
('000000429', 30, 70, 0),
('000000167', 30, 70, 0),
('000000180', 20, 80, 0),
('000000179', 20, 80, 0),
('000000177', 50, 50, 0),
('000000178', 50, 50, 0),
('000000175', 100, 0, 0),
('000000176', 100, 0, 0),
('000000172', 10, 80, 10),
('000000173', 0, 100, 0),
('000000181', 0, 90, 10),
('000000182', 0, 90, 10),
('000000183', 30, 60, 10),
('000000184', 40, 60, 0),
('000000186', 40, 40, 20),
('000000136', 40, 40, 20),
('000112', 10, 70, 20),
('000123seed', 80, 20, 0),
('000124seed', 60, 40, 0),
('Pyramid Auto', 40, 40, 20),
('Pyramid Auto31', 40, 40, 20),
('Pyramid Fem', 50, 50, 0),
('Pyramid Fem31', 50, 50, 0),
('420auto1', 40, 40, 20),
('420auto3', 40, 40, 20),
('420auto10', 40, 40, 20),
('420fem1', 50, 50, 0),
('420fem3', 50, 50, 0),
('420fem10', 50, 50, 0),
('Nirvana Auto', 40, 40, 20),
('Nirvana Fem', 50, 50, 0),
('Nirvana Reg', 50, 50, 0),
('Cheessexxl1', 80, 10, 10),
('Cheessexxl3', 80, 10, 10),
('Hazexxl1', 10, 80, 10),
('Hazexxl3', 10, 80, 10),
('Amnesiaxxl1', 10, 80, 10),
('Amnesiaxxl3', 10, 80, 10),
('Ogkushautox3', 10, 80, 10),
('Dinamexauto1', 20, 70, 10),
('Dinamexauto3', 20, 70, 10),
('Roadauto3', 65, 15, 20),
('BlueAmnAuto3', 40, 40, 20),
('CBDhazeauto', 10, 80, 10),
('SamsaraAuto1', 40, 40, 20),
('SamsaraAuto3', 40, 40, 20),
('SamsaraFem1', 50, 50, 0),
('SamsaraFem3', 50, 50, 0),
('000000152', 85, 15, 0),
('000000154', 20, 80, 0),
('000000156', 20, 80, 0),
('000000165', 95, 5, 0),
('000000157', 100, 0, 0),
('000000162', 100, 0, 0),
('000000158', 20, 80, 0),
('000000159', 20, 80, 0),
('000000166', 90, 10, 0),
('000000160', 85, 15, 0),
('000000161', 90, 10, 0),
('000000163', 90, 10, 0),
('000000192', 20, 80, 0),
('000000202', 15, 85, 0),
('000000187', 50, 50, 0),
('000000188', 50, 50, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_unidades`
--

CREATE TABLE `tb_unidades` (
  `codunidad` int(6) NOT NULL,
  `nom_unidad` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_unidades`
--

INSERT INTO `tb_unidades` (`codunidad`, `nom_unidad`) VALUES
(11, 'Bandeja'),
(10, 'Botella'),
(13, 'Caca'),
(12, 'Manga'),
(9, 'saco'),
(8, 'unidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `rutusuario` varchar(10) NOT NULL,
  `nomusuario` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `fonousuario` int(15) NOT NULL,
  `emailusuario` varchar(40) NOT NULL,
  `cargo` varchar(15) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `adelantos` int(7) NOT NULL,
  `descuentos` int(7) NOT NULL,
  `horas` int(3) NOT NULL,
  `horas_extra` int(3) NOT NULL,
  `valor_hora` int(11) NOT NULL,
  `valor_hora_extra` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_usuario`
--

INSERT INTO `tb_usuario` (`rutusuario`, `nomusuario`, `password`, `fonousuario`, `emailusuario`, `cargo`, `activo`, `adelantos`, `descuentos`, `horas`, `horas_extra`, `valor_hora`, `valor_hora_extra`) VALUES
('18144924-1', 'Admin', 'admin123', 50574261, 'jojan@homail.com', 'Administrador', 0, 20000, 2000, 5, 5, 2000, 2500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_venta`
--

CREATE TABLE `tb_venta` (
  `n_boleta` int(10) NOT NULL,
  `fechaventa` date NOT NULL,
  `cliente` varchar(20) DEFAULT NULL,
  `usuario` varchar(20) NOT NULL,
  `medio_pago` varchar(15) NOT NULL,
  `subtotal` int(10) NOT NULL,
  `descuento` int(10) NOT NULL,
  `totalventa` int(10) NOT NULL,
  `nulo` tinyint(1) NOT NULL,
  `puntos_venta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_venta`
--

INSERT INTO `tb_venta` (`n_boleta`, `fechaventa`, `cliente`, `usuario`, `medio_pago`, `subtotal`, `descuento`, `totalventa`, `nulo`, `puntos_venta`) VALUES
(1, '2017-08-05', 'SIN CLIENTE', 'admin', 'Mixto', 9000, 900, 8100, 0, 28),
(2, '2017-08-05', 'SIN CLIENTE', 'admin', 'Efectivo', 3000, 0, 3000, 0, 31),
(3, '2017-08-05', 'SIN CLIENTE', 'admin', 'Mixto', 131000, 13100, 117900, 0, 148),
(4, '2017-08-06', 'SIN CLIENTE', 'admin', 'Tarjeta', 3000, 0, 3000, 0, 151),
(5, '2017-08-06', 'SIN CLIENTE', 'admin', 'Efectivo', 9000, 900, 8100, 0, 159),
(6, '2017-08-06', 'SIN CLIENTE', 'admin', 'Mixto', 9000, 900, 8100, 0, 167),
(7, '2017-08-06', 'SIN CLIENTE', 'admin', 'Mixto', 52500, 0, 52500, 0, 219),
(8, '2017-08-06', 'SIN CLIENTE', 'admin', 'Mixto', 6000, 300, 5700, 0, 224),
(9, '2017-08-06', 'SIN CLIENTE', 'admin', 'Mixto', 6000, 0, 6000, 0, 230),
(10, '2017-08-06', 'SIN CLIENTE', 'admin', 'Efectivo', 12000, 1200, 10800, 1, 240),
(11, '2017-08-06', 'SIN CLIENTE', 'admin', 'Tarjeta', 15000, 0, 15000, 1, 255),
(12, '2017-08-09', 'SIN CLIENTE', 'admin', 'Efectivo', 3000, 0, 3000, 0, 258),
(13, '2017-08-09', 'SIN CLIENTE', 'admin', 'Tarjeta', 3000, 0, 3000, 0, 261),
(14, '2017-08-09', 'SIN CLIENTE', 'admin', 'Mixto', 68700, 0, 68700, 1, 329),
(15, '2017-08-09', 'SIN CLIENTE', 'admin', 'Tarjeta', 47100, 0, 47100, 0, 376),
(16, '2017-08-10', 'SIN CLIENTE', 'admin', 'Mixto', 6000, 0, 6000, 0, 382),
(17, '2017-08-11', 'SIN CLIENTE', 'admin', 'Mixto', 48000, 0, 48000, 0, 430),
(18, '2017-08-11', 'SIN CLIENTE', 'admin', 'Efectivo', 3000, 0, 3000, 0, 433),
(19, '2017-08-14', 'SIN CLIENTE', 'admin', 'Efectivo', 8500, 0, 8500, 0, 865),
(20, '2017-08-14', 'SIN CLIENTE', 'admin', 'Mixto', 58100, 8715, 49385, 0, 914),
(21, '2017-08-14', 'SIN CLIENTE', 'admin', 'Efectivo', 72000, 0, 72000, 0, 986),
(22, '2017-08-14', 'SIN CLIENTE', 'admin', 'Tarjeta', 93000, 0, 93000, 0, 1079),
(23, '2017-08-14', 'SIN CLIENTE', 'admin', 'Tarjeta', 8500, 0, 8500, 0, 1100),
(24, '2017-08-14', 'SIN CLIENTE', 'admin', 'Efectivo', 54000, 0, 54000, 0, 1196),
(25, '2017-08-14', 'SIN CLIENTE', 'admin', 'Mixto', 54000, 0, 54000, 0, 1250),
(26, '2017-08-14', 'SIN CLIENTE', 'admin', 'Efectivo', 21000, 0, 21000, 0, 1271),
(27, '2017-08-14', 'SIN CLIENTE', 'admin', 'Mixto', 76700, 0, 76700, 0, 1347),
(28, '2017-08-14', 'SIN CLIENTE', 'admin', 'Mixto', 25000, 0, 25000, 0, 1372),
(29, '2017-08-14', 'SIN CLIENTE', 'admin', 'Efectivo', 65000, 0, 65000, 0, 1437),
(30, '2017-08-14', 'SIN CLIENTE', 'admin', 'Mixto', 38500, 0, 38500, 0, 1475),
(31, '2017-09-05', 'SIN CLIENTE', 'admin', 'Efectivo', 48200, 4820, 43380, 0, 1518);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_ventapack`
--

CREATE TABLE `tb_ventapack` (
  `n_bol` int(10) NOT NULL,
  `codpack` varchar(20) NOT NULL,
  `nombrepack` varchar(100) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio_unitario` int(6) NOT NULL,
  `precio_total` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_ventapack`
--

INSERT INTO `tb_ventapack` (`n_bol`, `codpack`, `nombrepack`, `cantidad`, `precio_unitario`, `precio_total`) VALUES
(17, 'PACK10000001', 'Pack de mierda', 1, 45000, 45000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_ventaprod`
--

CREATE TABLE `tb_ventaprod` (
  `n_bol` int(20) NOT NULL,
  `codprod` varchar(20) NOT NULL,
  `producto` varchar(150) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio_unitario` int(6) NOT NULL,
  `precio_total` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_ventaprod`
--

INSERT INTO `tb_ventaprod` (`n_bol`, `codprod`, `producto`, `cantidad`, `precio_unitario`, `precio_total`) VALUES
(1, '000000005', 'Boquilla Silicona', 2, 4500, 9000),
(2, '000000017', 'Pot Silicona 5.5', 1, 3000, 3000),
(3, '000000017', 'Pot Silicona 5.5', 2, 3000, 6000),
(3, '000000021', 'Bong Recycler grande', 1, 45000, 45000),
(3, '000000024', 'Bong Doble Piso Colores Lunares', 1, 40000, 40000),
(3, '000000025', 'Bong Masiso Jaula Invertida  Transparente con verde', 1, 40000, 40000),
(4, '000000017', 'Pot Silicona 5.5', 1, 3000, 3000),
(5, '000000017', 'Pot Silicona 5.5', 3, 3000, 9000),
(6, '000000017', 'Pot Silicona 5.5', 3, 3000, 9000),
(7, '000000007', 'Bowl Repuesto Colores', 1, 4500, 4500),
(7, '000000017', 'Pot Silicona 5.5', 1, 3000, 3000),
(7, '000000035', 'Bong Bubbler Huevo', 1, 45000, 45000),
(8, '000000017', 'Pot Silicona 5.5', 2, 3000, 6000),
(9, '000000017', 'Pot Silicona 5.5', 2, 3000, 6000),
(10, '000000038', 'Moledor S 3 Piezas', 2, 6000, 12000),
(11, '000000017', 'Pot Silicona 5.5', 5, 3000, 15000),
(12, '000000017', 'Pot Silicona 5.5', 1, 3000, 3000),
(13, '000000017', 'Pot Silicona 5.5', 1, 3000, 3000),
(14, '000000159', 'Moby Dick Feminizada Pack 3', 1, 26000, 26000),
(14, '000000161', 'OG Kush Feminizada Unidad', 1, 9200, 9200),
(14, '000000179', 'Moby Dick XXL x3', 1, 24900, 24900),
(14, '000000180', 'Moby Dick XXL x1', 1, 8600, 8600),
(15, '000000178', 'White Widow Auto', 1, 26000, 26000),
(15, '000000312', 'Limpia Pipa', 1, 100, 100),
(15, '000000328', 'Gipsy Haze', 1, 21000, 21000),
(16, '000000017', 'Pot Silicona 5.5', 2, 3000, 6000),
(17, '000000017', 'Pot Silicona 5.5', 1, 3000, 3000),
(18, '000000017', 'POT SILICONA 5.5', 1, 3000, 3000),
(19, '000000006', 'BOLW REPUESTO TRANSPARENTE', 1, 4000, 4000),
(19, '000000007', 'BOWL REPUESTO COLORES', 1, 4500, 4500),
(20, '000000172', 'ORIGINAL AMNESIA AUTO X1', 1, 8400, 8400),
(20, '000000173', 'ORIGINAL AMNESIA AUTO X3', 1, 24900, 24900),
(20, '000000176', 'CRITICAL +2.0 AUTO X3', 1, 24800, 24800),
(21, '000000434', 'TOXIC', 1, 18000, 18000),
(21, '000000435', 'WASHING MACHINE', 1, 18000, 18000),
(21, '000000436', 'CRIMINAL +', 1, 18000, 18000),
(21, '000000437', 'GRAPEGUM', 1, 18000, 18000),
(22, '000000431', 'JAMAICAN DREAM', 1, 21000, 21000),
(22, '000000432', 'HIGH LEVEL', 1, 18000, 18000),
(22, '000000433', 'SOUR RIPPER', 1, 18000, 18000),
(22, '000000434', 'TOXIC', 1, 18000, 18000),
(22, '000000436', 'CRIMINAL +', 1, 18000, 18000),
(23, '000000006', 'BOLW REPUESTO TRANSPARENTE', 1, 4000, 4000),
(23, '000000007', 'BOWL REPUESTO COLORES', 1, 4500, 4500),
(23, '000000430', 'PINK PLANT', 1, 21000, 21000),
(23, '000000431', 'JAMAICAN DREAM', 1, 21000, 21000),
(24, '000000435', 'WASHING MACHINE', 1, 18000, 18000),
(24, '000000436', 'CRIMINAL +', 1, 18000, 18000),
(24, '000000437', 'GRAPEGUM', 1, 18000, 18000),
(25, '000000434', 'TOXIC', 1, 18000, 18000),
(25, '000000435', 'WASHING MACHINE', 1, 18000, 18000),
(25, '000000437', 'GRAPEGUM', 1, 18000, 18000),
(26, '000000017', 'POT SILICONA 5.5', 3, 3000, 9000),
(26, '000000037', 'BONG 8 QUEMADORES', 1, 12000, 12000),
(27, '000000166', 'ORIGINAL AMNESIA FEMINIZADA UNIDAD', 1, 8900, 8900),
(27, '000000187', 'WHITE WIDOW DINAFEM FEMINIZADA UNIDAD', 1, 9000, 9000),
(27, '000000188', 'WHITE WIDOW DINAFEM FEMINIZADA PACK 3', 1, 25000, 25000),
(27, '000000192', 'CRITICAL JACK DINAFEM FEMINIZADA UNIDAD', 1, 8900, 8900),
(27, '000000202', 'CRITICAL JACK DINAFEM FEMINIZADA PACK 3', 1, 24900, 24900),
(28, '000000215', 'MOLEDOR HOJA MARIHUANA', 1, 8000, 8000),
(28, '000000217', 'MOLEDOR PALITROQUE', 1, 4000, 4000),
(28, '000000218', 'MOLEDOR MOLINILLO', 1, 13000, 13000),
(29, '000000328', 'GIPSY HAZE', 1, 21000, 21000),
(29, '000000329', 'EXCALIBUR', 1, 21000, 21000),
(29, '000000330', 'MISSING IN BARCELONA', 1, 23000, 23000),
(30, '000000179', 'MOBY DICK XXL X3', 1, 24900, 24900),
(30, '000000180', 'MOBY DICK XXL X1', 1, 8600, 8600),
(30, '000000229', 'MOLEDOR PEQUEÑO 3 PIEZAS', 1, 5000, 5000),
(31, '000000040', 'MOLEDOR M 3 PIEZAS', 1, 7000, 7000),
(31, '000000041', 'MOLEDOR L 4 PIEZAS', 1, 12000, 12000),
(31, '000000105', 'BONG 30CM HOJITAS ATRAPAHIELO CILINDRICO', 1, 20000, 20000),
(31, '000000161', 'OG KUSH FEMINIZADA UNIDAD', 1, 9200, 9200);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_caja`
--
ALTER TABLE `tb_caja`
  ADD PRIMARY KEY (`dv`);

--
-- Indices de la tabla `tb_cajaegreso`
--
ALTER TABLE `tb_cajaegreso`
  ADD PRIMARY KEY (`dv`);

--
-- Indices de la tabla `tb_cajaingreso`
--
ALTER TABLE `tb_cajaingreso`
  ADD PRIMARY KEY (`dv`);

--
-- Indices de la tabla `tb_cliente`
--
ALTER TABLE `tb_cliente`
  ADD PRIMARY KEY (`rutcliente`),
  ADD KEY `nomcliente` (`nomcliente`);

--
-- Indices de la tabla `tb_detalleentrada`
--
ALTER TABLE `tb_detalleentrada`
  ADD PRIMARY KEY (`codentrada`,`codprod`),
  ADD KEY `tb_detalleentrada_ibfk_2` (`codprod`);

--
-- Indices de la tabla `tb_detallesalida`
--
ALTER TABLE `tb_detallesalida`
  ADD PRIMARY KEY (`codsalida`,`codprod`),
  ADD KEY `codsalida` (`codsalida`),
  ADD KEY `codsalida_2` (`codsalida`),
  ADD KEY `codprod` (`codprod`);

--
-- Indices de la tabla `tb_entrada`
--
ALTER TABLE `tb_entrada`
  ADD PRIMARY KEY (`codentrada`),
  ADD KEY `motivo` (`motivo`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `tb_marcas`
--
ALTER TABLE `tb_marcas`
  ADD PRIMARY KEY (`codmarca`),
  ADD KEY `nom_marca` (`nom_marca`);

--
-- Indices de la tabla `tb_motivos`
--
ALTER TABLE `tb_motivos`
  ADD PRIMARY KEY (`codmotiv`),
  ADD KEY `motivo` (`motivo`);

--
-- Indices de la tabla `tb_pack`
--
ALTER TABLE `tb_pack`
  ADD PRIMARY KEY (`codpack`);

--
-- Indices de la tabla `tb_packprod`
--
ALTER TABLE `tb_packprod`
  ADD PRIMARY KEY (`codprod`,`codpack`),
  ADD KEY `codpack` (`codpack`),
  ADD KEY `codprod` (`codprod`),
  ADD KEY `producto` (`producto`);

--
-- Indices de la tabla `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD PRIMARY KEY (`codprod`),
  ADD KEY `detalleprod` (`detalleprod`),
  ADD KEY `proveedor` (`proveedor`),
  ADD KEY `rubro` (`rubro`),
  ADD KEY `unidad` (`unidad`),
  ADD KEY `marca` (`marca`);

--
-- Indices de la tabla `tb_proveedor`
--
ALTER TABLE `tb_proveedor`
  ADD PRIMARY KEY (`rutempresa`),
  ADD KEY `nomproveedor` (`nomproveedor`);

--
-- Indices de la tabla `tb_rubros`
--
ALTER TABLE `tb_rubros`
  ADD PRIMARY KEY (`codrubro`),
  ADD KEY `nom_rubro` (`nom_rubro`);

--
-- Indices de la tabla `tb_salidas`
--
ALTER TABLE `tb_salidas`
  ADD PRIMARY KEY (`codsalida`),
  ADD KEY `motivo` (`motivo`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `tb_semilla`
--
ALTER TABLE `tb_semilla`
  ADD KEY `codprod` (`codprod`);

--
-- Indices de la tabla `tb_unidades`
--
ALTER TABLE `tb_unidades`
  ADD PRIMARY KEY (`codunidad`),
  ADD KEY `nom_unidad` (`nom_unidad`);

--
-- Indices de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`rutusuario`),
  ADD KEY `nomusuario` (`nomusuario`);

--
-- Indices de la tabla `tb_venta`
--
ALTER TABLE `tb_venta`
  ADD PRIMARY KEY (`n_boleta`),
  ADD KEY `cliente` (`cliente`),
  ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `tb_ventapack`
--
ALTER TABLE `tb_ventapack`
  ADD PRIMARY KEY (`n_bol`,`codpack`),
  ADD KEY `codpack` (`codpack`),
  ADD KEY `n_bol` (`n_bol`);

--
-- Indices de la tabla `tb_ventaprod`
--
ALTER TABLE `tb_ventaprod`
  ADD PRIMARY KEY (`n_bol`,`codprod`),
  ADD KEY `n_bol` (`n_bol`),
  ADD KEY `codprod` (`codprod`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_caja`
--
ALTER TABLE `tb_caja`
  MODIFY `dv` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT de la tabla `tb_cajaegreso`
--
ALTER TABLE `tb_cajaegreso`
  MODIFY `dv` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tb_cajaingreso`
--
ALTER TABLE `tb_cajaingreso`
  MODIFY `dv` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tb_marcas`
--
ALTER TABLE `tb_marcas`
  MODIFY `codmarca` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;
--
-- AUTO_INCREMENT de la tabla `tb_motivos`
--
ALTER TABLE `tb_motivos`
  MODIFY `codmotiv` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `tb_rubros`
--
ALTER TABLE `tb_rubros`
  MODIFY `codrubro` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT de la tabla `tb_salidas`
--
ALTER TABLE `tb_salidas`
  MODIFY `codsalida` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10000004;
--
-- AUTO_INCREMENT de la tabla `tb_unidades`
--
ALTER TABLE `tb_unidades`
  MODIFY `codunidad` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tb_detalleentrada`
--
ALTER TABLE `tb_detalleentrada`
  ADD CONSTRAINT `tb_detalleentrada_ibfk_1` FOREIGN KEY (`codentrada`) REFERENCES `tb_entrada` (`codentrada`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_detalleentrada_ibfk_2` FOREIGN KEY (`codprod`) REFERENCES `tb_productos` (`codprod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_detallesalida`
--
ALTER TABLE `tb_detallesalida`
  ADD CONSTRAINT `tb_detallesalida_ibfk_1` FOREIGN KEY (`codsalida`) REFERENCES `tb_salidas` (`codsalida`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_detallesalida_ibfk_2` FOREIGN KEY (`codprod`) REFERENCES `tb_productos` (`codprod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_packprod`
--
ALTER TABLE `tb_packprod`
  ADD CONSTRAINT `tb_packprod_ibfk_3` FOREIGN KEY (`producto`) REFERENCES `tb_productos` (`detalleprod`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_packprod_ibfk_4` FOREIGN KEY (`codpack`) REFERENCES `tb_pack` (`codpack`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_packprod_ibfk_5` FOREIGN KEY (`codprod`) REFERENCES `tb_productos` (`codprod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_productos`
--
ALTER TABLE `tb_productos`
  ADD CONSTRAINT `tb_productos_ibfk_3` FOREIGN KEY (`marca`) REFERENCES `tb_marcas` (`nom_marca`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_productos_ibfk_4` FOREIGN KEY (`unidad`) REFERENCES `tb_unidades` (`nom_unidad`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_productos_ibfk_5` FOREIGN KEY (`proveedor`) REFERENCES `tb_proveedor` (`nomproveedor`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_salidas`
--
ALTER TABLE `tb_salidas`
  ADD CONSTRAINT `tb_salidas_ibfk_1` FOREIGN KEY (`motivo`) REFERENCES `tb_motivos` (`motivo`);

--
-- Filtros para la tabla `tb_semilla`
--
ALTER TABLE `tb_semilla`
  ADD CONSTRAINT `tb_semilla_ibfk_1` FOREIGN KEY (`codprod`) REFERENCES `tb_productos` (`codprod`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_venta`
--
ALTER TABLE `tb_venta`
  ADD CONSTRAINT `tb_venta_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `tb_cliente` (`nomcliente`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_venta_ibfk_2` FOREIGN KEY (`usuario`) REFERENCES `tb_usuario` (`nomusuario`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `tb_ventapack`
--
ALTER TABLE `tb_ventapack`
  ADD CONSTRAINT `tb_ventapack_ibfk_2` FOREIGN KEY (`n_bol`) REFERENCES `tb_venta` (`n_boleta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_ventapack_ibfk_3` FOREIGN KEY (`codpack`) REFERENCES `tb_pack` (`codpack`);

--
-- Filtros para la tabla `tb_ventaprod`
--
ALTER TABLE `tb_ventaprod`
  ADD CONSTRAINT `tb_ventaprod_ibfk_1` FOREIGN KEY (`n_bol`) REFERENCES `tb_venta` (`n_boleta`) ON DELETE CASCADE,
  ADD CONSTRAINT `tb_ventaprod_ibfk_2` FOREIGN KEY (`codprod`) REFERENCES `tb_productos` (`codprod`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
