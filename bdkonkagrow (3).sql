-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-04-2017 a las 05:02:44
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
('Jenrru', '17614376-2', 955144664, 'Andres.wolf@gmail.com', 648);

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

--
-- Volcado de datos para la tabla `tb_entrada`
--

INSERT INTO `tb_entrada` (`codentrada`, `fecha`, `usuario`, `motivo`, `nulo`) VALUES
(10000001, '2016-12-04', 'Juanoso', 'Compra', 0),
(10000002, '2016-12-04', 'Juanoso', 'Compra', 0),
(10000003, '2017-04-11', 'Admin', 'Compra', 0),
(10000004, '2017-04-11', 'Admin', 'Compra', 0),
(10000005, '2017-04-11', 'Admin', 'Compra', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_marcas`
--

CREATE TABLE `tb_marcas` (
  `codmarca` int(6) NOT NULL,
  `nom_marca` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_marcas`
--

INSERT INTO `tb_marcas` (`codmarca`, `nom_marca`) VALUES
(29, '4:20 Genetics'),
(52, 'aledinha'),
(51, 'amico'),
(18, 'Atami'),
(24, 'B.A.C.'),
(36, 'Barney´s Farm'),
(21, 'Biobizz'),
(55, 'Blunt Wrap'),
(47, 'Buddha Seeds'),
(53, 'c-thru'),
(20, 'Canna'),
(32, 'Cannabiogen'),
(14, 'Cultibox Light'),
(44, 'Delicious'),
(26, 'Dinafem'),
(33, 'Dutch Passion'),
(37, 'Elements'),
(31, 'EVA'),
(15, 'Garden High'),
(57, 'Green House'),
(23, 'Grotek'),
(17, 'Grow Genetics'),
(58, 'Growth Tecnology'),
(59, 'Growth Tecnology'),
(19, 'Hesi'),
(54, 'Kenex'),
(45, 'Maceta'),
(41, 'Medical Seeds'),
(30, 'Nirvana'),
(42, 'OCB'),
(49, 'Odorsok'),
(48, 'OSRAM'),
(40, 'Paradise Seeds'),
(25, 'Powder Feeding'),
(16, 'Probox'),
(27, 'Pyramid'),
(43, 'Randy´s'),
(38, 'Raw'),
(34, 'Ripper Seeds'),
(39, 'Samsara'),
(50, 'Sensi Seeds'),
(28, 'Sweet Seeds'),
(22, 'Top Crop'),
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

--
-- Volcado de datos para la tabla `tb_motientrada`
--

INSERT INTO `tb_motientrada` (`codmotiv`, `motivo`) VALUES
(1, 'Compra'),
(2, 'Traslado'),
(1, 'Compra'),
(2, 'Traslado');

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
(2, 'Perdida'),
(1, 'Robo'),
(3, 'Traslado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_pack`
--

CREATE TABLE `tb_pack` (
  `codpack` varchar(10) NOT NULL,
  `nompack` varchar(30) NOT NULL,
  `preciopack` int(10) NOT NULL,
  `ahorro` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_packprod`
--

CREATE TABLE `tb_packprod` (
  `codprod` varchar(10) NOT NULL,
  `codpack` varchar(10) NOT NULL,
  `producto` varchar(40) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio_unitario` int(6) NOT NULL,
  `precio_total` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `detalleprod` varchar(40) NOT NULL,
  `unidad` varchar(10) NOT NULL,
  `rubro` varchar(20) NOT NULL,
  `marca` varchar(15) NOT NULL,
  `proveedor` varchar(30) DEFAULT NULL,
  `precioprod` int(10) NOT NULL,
  `stock` int(4) NOT NULL,
  `stockmin` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_productos`
--

INSERT INTO `tb_productos` (`codprod`, `detalleprod`, `unidad`, `rubro`, `marca`, `proveedor`, `precioprod`, `stock`, `stockmin`) VALUES
('30052765', 'papelillo ocb negro 1 1/4 ', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 100, 10),
('30068926', 'papelillo ocb  x-pert 1 1/4 griz', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 100, 10),
('30087859', 'papelillo ocb organico 1 1/4', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 100, 10),
('30104907', 'papelillo ocb no blanqueado 1 1/4', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 100, 10),
('30119550', 'papelillo ocb ultimate 1 1/4', 'unidad', 'Tabaquería', 'OCB', 'KONKA GROW', 450, 100, 10),
('4008321387585', 'Ampolleta Sodio 150', 'unidad', 'Iluminación', 'OSRAM', 'dosifencio dosada', 5000, 19, 6),
('4823016212399', 'Extractor lineal  de 150', 'unidad', 'Ventilación', 'Vents', 'KONKA GROW', 19000, 100, 10),
('4823016238160', 'Extractor TT 100  dual', 'unidad', 'Ventilación', 'Vents', 'KONKA GROW', 35000, 100, 10),
('5060240030002', 'Gramera  Eternity Clasic', 'unidad', 'Parafernalia', 'Kenex', 'KONKA GROW', 10000, 100, 10),
('644536227618', 'Blunt  wet cherry', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 1000, 100, 10),
('644536384410', 'papelillo blunt 1 1/4 regular', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 850, 100, 10),
('644536384618', 'papelillo Blun 1 1/4 umbliched', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 850, 100, 10),
('644536384717', 'Papelillo Blunt 1 1/14 Premium', 'unidad', 'Tabaquería', 'Blunt Wrap', 'KONKA GROW', 850, 100, 10),
('644536840022', 'papel de celulosa', 'unidad', 'Tabaquería', 'c-thru', 'KONKA GROW', 1500, 100, 10),
('648658033294', 'Randy´s Gris', 'unidad', 'Tabaquería', 'Randy´s', 'dosifencio dosada', 1500, 100, 10),
('648658200016', 'papelillo cafe', 'unidad', 'Tabaquería', 'Randy´s', 'KONKA GROW', 1500, 1000, 10),
('6943205401001', 'ODORSOK 100', 'unidad', 'Ventilación', 'Odorsok', 'dosifencio dosada', 23500, 100, 1),
('6943205401002', 'Odorsok 125', 'unidad', 'Ventilación', 'Odorsok', 'dosifencio dosada', 28000, 100, 1),
('6943205401003', 'Odorsok 150', 'unidad', 'Ventilación', 'Odorsok', 'dosifencio dosada', 46000, 100, 1),
('716165176107', 'Raw Connosieur', 'unidad', 'Tabaquería', 'Raw', 'dosifencio dosada', 1250, 100, 10),
('716165177296', 'papelillo elements', 'unidad', 'Tabaquería', 'Elements', 'KONKA GROW', 600, 100, 10),
('716165177357', 'Raw Sabana', 'unidad', 'Tabaquería', 'Raw', 'dosifencio dosada', 1000, 100, 10),
('716165178873', 'tips (boquillas de papel prepicado )', 'unidad', 'Tabaquería', 'Elements', 'KONKA GROW', 600, 100, 10),
('716165178897', 'Elements Artesano', 'unidad', 'Tabaquería', 'Elements', 'dosifencio dosada', 1250, 100, 10),
('716165178910', 'Elements Artesano Sabana', 'unidad', 'Tabaquería', 'Elements', 'dosifencio dosada', 2500, 100, 10),
('716165200246', 'Raw Gummed Tips', 'unidad', 'Tabaquería', 'Raw', 'dosifencio dosada', 800, 100, 10),
('716165201038', 'Raw Artesano', 'unidad', 'Tabaquería', 'Raw', 'dosifencio dosada', 1500, 100, 10),
('7898915411041', 'papel de celulosa brasileño', 'unidad', 'Tabaquería', 'aledinha', 'KONKA GROW', 1500, 100, 100),
('8436554710904', 'Cooltube 125S', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 32000, 100, 10),
('8436554711000', 'Cooltube 125M', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 36000, 100, 10),
('8436554760015', 'Malla Sgrog', 'unidad', 'Cultivo', 'Garden High', 'dosifencio dosada', 6000, 100, 10),
('8436554760930', 'Lentes protectores sodio', 'unidad', 'Iluminación', 'Garden High', 'KONKA GROW', 9000, 99, 10),
('8436554761166', 'extractor lineal 100', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 13500, 100, 10),
('8436554761173', 'Extractor lineal 125', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 15500, 100, 10),
('8436554761234', ' Extractor TT dual125', 'unidad', 'Ventilación', 'Garden High', 'KONKA GROW', 37500, 100, 10),
('8438479305814', 'Organic Bloom 500', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 8200, 100, 10),
('8438479308419', 'Organic Grow 500', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 8500, 100, 10),
('8438479308617', 'Organic Grow 250', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 6200, 100, 10),
('8438479308815', 'Organic Bloom 250', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 6200, 100, 10),
('8438479309010', 'Organic PK Booster', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 10200, 100, 10),
('8438479310818', 'Bloom Stimulator 30ml', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 10100, 100, 10),
('8438479311211', 'Final Solution 30ml', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 13500, 1, 10),
('8438479311617', 'Auto Stimulator', 'Botella', 'Fertilizantes', 'B.A.C.', 'dosifencio dosada', 20500, 100, 10),
('8506710000146', 'Tabaqueria Papel amico', 'unidad', 'Tabaquería', 'amico', 'KONKA GROW', 500, 100, 5),
('8717524950682', 'Boost Accelerator', 'Botella', 'Fertilizantes', 'Canna', 'dosifencio dosada', 30500, 1, 10),
('8717524958541', 'PK 13/14', 'Botella', 'Fertilizantes', 'Canna', 'dosifencio dosada', 10500, 100, 10),
('8717524958589', 'Rhizotonic', 'Botella', 'Fertilizantes', 'Canna', 'dosifencio dosada', 20000, 100, 10),
('8717524958626', 'Cannazym', 'Botella', 'Fertilizantes', 'Canna', 'dosifencio dosada', 11500, 100, 10),
('8717662944307', 'Terra Flores 500', 'Botella', 'Fertilizantes', 'Canna', 'dosifencio dosada', 11500, 100, 10),
('8717662944314', 'Terra Vega 500', 'Botella', 'Fertilizantes', 'Canna', 'dosifencio dosada', 11500, 100, 10),
('8717729827383', 'Power Feeding hibrida sachet', 'unidad', 'Fertilizantes', 'Green House', 'KONKA GROW', 500, 100, 10),
('8717729827406', 'Powder Feeding  long flowering sachet', 'unidad', 'Fertilizantes', 'Green House', 'KONKA GROW', 500, 100, 10),
('8717729827437', 'Fertilizante Power Feeding Sachet', 'unidad', 'Fertilizantes', 'Green House', 'KONKA GROW', 500, 100, 10),
('8718403232127', 'Try pack Biobizz', 'Botella', 'Fertilizantes', 'Biobizz', 'dosifencio dosada', 15000, 100, 10),
('8718403232134', 'Try Pack Outdoor', 'unidad', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 15000, 100, 10),
('8718403232141', 'Try pack  Stimulant', 'unidad', 'Fertilizantes', 'Biobizz', 'KONKA GROW', 27000, 100, 10),
('8719244303878', 'Bloombastic  pk14-15', 'unidad', 'Fertilizantes', 'Atami', 'KONKA GROW', 19000, 100, 10),
('DELTA NUEVE 150ml', 'Delta 9 150ml', 'Botella', 'Fertilizantes', 'Cannabiogen', 'dosifencio dosada', 9000, 100, 10),
('TCSC50L', 'Sustrato Top Crop Complete Mix 50Lt', 'saco', 'Sustrato', 'Top Crop', 'dosifencio dosada', 12000, 100, 10);

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
('Johanns yucrans', '18144924-1', 654123, 'calle123'),
('dosifencio dosada', '22222222-2', 22222222, 'calle dos '),
('Johanncion Mon', '55555555-5', 57575858, 'España 297'),
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
(31, 'Armarios'),
(34, 'Cultivo'),
(26, 'Fertilizantes'),
(25, 'Iluminación'),
(30, 'Medición y Control'),
(32, 'Parafernalia'),
(28, 'Semillas'),
(27, 'Sustrato'),
(33, 'Tabaquería'),
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_semilla`
--

CREATE TABLE `tb_semilla` (
  `codprod` varchar(10) NOT NULL,
  `indica` int(2) NOT NULL,
  `sativa` int(2) NOT NULL,
  `rudelaris` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(10, 'Botella'),
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
('18144924-1', 'Admin', 'admin123', 50574261, 'jojan@homail.com', 'Administrador', 0, 20000, 2000, 5, 5, 2000, 2500),
('33333333-3', 'Chavez', 'mamerto33', 654321, 'jojo@jeje.cl', 'Administrador', 0, 0, 0, 0, 0, 2000, 2500),
('55555555-5', 'Juanoso', '', 654321, 'konka@weed.com', 'Bodega', 0, 0, 0, 0, 0, 1500, 2500),
('77777777-7', 'Oscar', '2323', 789456, 'oscar@cheles.mex', 'Otro', 0, 0, 0, 0, 0, 0, 0);

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
  `subtotal` int(6) NOT NULL,
  `descuento` int(6) NOT NULL,
  `totalventa` int(6) NOT NULL,
  `nulo` tinyint(1) NOT NULL,
  `puntos_venta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tb_venta`
--

INSERT INTO `tb_venta` (`n_boleta`, `fechaventa`, `cliente`, `usuario`, `medio_pago`, `subtotal`, `descuento`, `totalventa`, `nulo`, `puntos_venta`) VALUES
(1, '2017-04-13', 'Jenrru', 'Chavez', 'Débito', 9000, 900, 8100, 0, 648);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_ventapack`
--

CREATE TABLE `tb_ventapack` (
  `n_bol` int(10) NOT NULL,
  `codpack` varchar(10) NOT NULL,
  `nombrepack` varchar(40) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio_unitario` int(6) NOT NULL,
  `precio_total` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_ventaprod`
--

CREATE TABLE `tb_ventaprod` (
  `n_bol` int(10) NOT NULL,
  `codprod` varchar(20) NOT NULL,
  `producto` varchar(40) NOT NULL,
  `cantidad` int(3) NOT NULL,
  `precio_unitario` int(6) NOT NULL,
  `precio_total` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

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
-- AUTO_INCREMENT de la tabla `tb_marcas`
--
ALTER TABLE `tb_marcas`
  MODIFY `codmarca` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
--
-- AUTO_INCREMENT de la tabla `tb_motivos`
--
ALTER TABLE `tb_motivos`
  MODIFY `codmotiv` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `tb_rubros`
--
ALTER TABLE `tb_rubros`
  MODIFY `codrubro` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT de la tabla `tb_salidas`
--
ALTER TABLE `tb_salidas`
  MODIFY `codsalida` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tb_unidades`
--
ALTER TABLE `tb_unidades`
  MODIFY `codunidad` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
