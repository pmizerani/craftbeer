-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Tempo de geração: 06/04/2019 às 19:29
-- Versão do servidor: 10.3.12-MariaDB-1:10.3.12+maria~bionic
-- Versão do PHP: 7.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `craftbeer`
--
CREATE DATABASE IF NOT EXISTS `craftbeer` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `craftbeer`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `beer`
--

CREATE TABLE `beer` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `ingredients` text NOT NULL,
  `alcohol_content` varchar(200) NOT NULL,
  `price` decimal(15,2) NOT NULL,
  `category` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `beer`
--
ALTER TABLE `beer`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `beer`
--
ALTER TABLE `beer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
