-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 17-Nov-2016 às 17:45
-- Versão do servidor: 5.7.16-0ubuntu0.16.04.1
-- PHP Version: 7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `looproject`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `avaliacao`
--

CREATE TABLE `avaliacao` (
  `id` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `medicoresponsavel` varchar(250) DEFAULT NULL,
  `medicamento` varchar(250) DEFAULT NULL,
  `observacaodehoje` varchar(350) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `avaliacao`
--

INSERT INTO `avaliacao` (`id`, `idcliente`, `medicoresponsavel`, `medicamento`, `observacaodehoje`) VALUES
(1, 1, 'Joanna B', 'Calminex', 'Não gosta do remédio.'),
(2, 2, 'Meiriane Wit', 'Alongamentos', 'Dores na coluna'),
(3, 3, 'Jose Med', 'Gel para as pernas', 'Reclama de tudo'),
(4, 4, '', '', ''),
(5, 5, 'Pedro Fol', 'Losartana', 'Problema cardíacos e diabetes'),
(6, 4, 'Pedro Fol', 'Losartana', 'diabetes e obesidade'),
(7, 6, 'Pedro Fol', 'Pó mágico', 'Acha que é fada'),
(8, 7, 'Pedro Fol', 'Bola mágica', 'Acha que é médico'),
(9, 8, 'Joana B', 'Papinhas', 'bebe muito fofa'),
(10, 9, 'Joana B', 'Bolinhas massagem', 'Risonha'),
(11, 10, 'Meiriane Wit', 'Aspirina', 'Gosta de balas'),
(12, 11, 'Meiriane Wit', 'Analgésicos', 'Alongamentos '),
(13, 12, 'Pedro Fol', 'nenhum', 'exercícios no barrel'),
(14, 13, 'Pedro Fol', 'Nenhum', 'Barrel'),
(15, 14, 'Meiriane Wit', 'Pomadas', 'Alogamentos e reforço'),
(16, 15, 'Joana B', 'Loratadina', 'Problemas com a fala'),
(17, 16, 'Joana B', 'Losartana', 'Pressão alta'),
(18, 17, 'Joana B', 'Ervas', 'acredita em ervas');

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(250) NOT NULL,
  `idade` int(11) NOT NULL,
  `endereco` varchar(250) DEFAULT NULL,
  `datanascimento` date DEFAULT NULL,
  `sexo` varchar(250) DEFAULT NULL,
  `planosaude` varchar(250) DEFAULT NULL,
  `telefone` varchar(250) DEFAULT NULL,
  `responsavel` varchar(250) DEFAULT NULL,
  `especialidade` varchar(250) DEFAULT NULL,
  `datainiciotratamento` date DEFAULT NULL,
  `datadaavaliacao` date DEFAULT NULL,
  `medicoresponsavel` varchar(250) DEFAULT NULL,
  `qtdesessoesrealizadas` int(11) DEFAULT NULL,
  `atualizadoem` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `idade`, `endereco`, `datanascimento`, `sexo`, `planosaude`, `telefone`, `responsavel`, `especialidade`, `datainiciotratamento`, `datadaavaliacao`, `medicoresponsavel`, `qtdesessoesrealizadas`, `atualizadoem`) VALUES
(1, 'Ana Maria Bras', 6, 'Ruas das Amoras, 123', '2010-03-02', 'Feminino', 'SUS', '(42)3623-1234 ', 'José Maria Bras', 'Ortopedia', '2016-10-10', '2016-10-10', '', 10, '2016-11-15 14:29:06'),
(2, 'Jose Maria Bras', 36, 'Rua da Amoras', '1980-02-02', 'Masculino', 'SUS', '(42)3623-1234 ', 'Jose Maria Bras', 'Pilates', '2010-02-02', '2010-02-02', '', 100, '2016-11-15 14:30:46'),
(3, 'Joaquina Suarinha', 56, 'Rua das Lindas Flores, 23', '1960-10-10', 'Feminino', 'UNIMED', '(42)3623-5678 ', 'Joania Saurinha Filha', 'Ortopedia', '2015-05-01', '2015-05-01', '', 18, '2016-11-15 14:33:58'),
(4, 'Bet Boop', 76, 'Rua dos Cartoes, 57', '1940-01-01', 'Feminino', 'UNIMED', '(42)3623-5678 ', 'Betina Boop Neta', 'Neurologia', '1990-03-01', '1990-03-01', '', 100, '2016-11-15 14:35:59'),
(5, 'José Medo', 96, 'Rua dos Lindos, 98', '1920-03-01', 'Masculino', 'UNIMED', '(11)9912-2912 ', 'Josefina Medo Filha', 'Outros', '2010-02-01', '2010-03-01', '', 50, '2016-11-15 14:39:31'),
(6, 'Maria Alice das Fadas', 10, 'Rua dos contos s/n', '2006-10-10', 'Feminino', 'SUSINHA', '(42)3456-7899 ', 'Sabrina Alice das Fadas', 'Neurologia', '2015-01-01', '2015-01-02', '', 10, '2016-11-15 14:53:21'),
(7, 'Joao Sem Pé De Feijão', 22, 'Rua dos contos s/n', '1994-10-10', 'Masculino', 'Era uma vez', '(42)3623-1045 ', 'José Com Pé', 'Neurologia', '2015-10-10', '2015-10-10', '', 10, '2016-11-15 14:55:15'),
(8, 'Zuleika Zuzu', 2, 'Rua das Flores', '2014-02-02', 'Feminino', 'SUS', '(42)3623-1456 ', 'Sumira Zuzu', 'Outros', '2016-10-01', '2016-10-01', '', 10, '2016-11-15 15:13:03'),
(9, 'Belina B', 1, 'Rua das Nações', '2015-02-02', 'Feminino', 'Unimed', '(42)3623-1456 ', 'Beto B', 'Ortopedia', '2016-10-10', '2016-10-10', '', 10, '2016-11-15 15:14:18'),
(10, 'Carla Miriam Jan', 1, 'Rua das Carlas, 34', '2015-10-10', 'Feminino', 'UNIMED', '(42)3456-7890 ', 'Miriam Jan', 'Ortopedia', '2016-10-10', '2016-10-10', '', 10, '2016-11-15 15:18:33'),
(11, 'Danilo Dan Dan', 110, 'Rua das profissões, 3456', '1906-01-02', 'Masculino', 'UNIMAIS', '(43)9978-6758 ', 'Daniel Dan Neto', 'Pilates', '2010-04-03', '2010-04-03', '', 1000, '2016-11-15 15:21:03'),
(12, 'Emerson Professor', 20, 'Rua da utf', '1996-10-10', 'Masculino', 'UNIMDE', '(42)1234-5678 ', 'Emerson Pai', 'Pilates', '2015-10-10', '2015-10-10', '', 10, '2016-11-15 15:22:39'),
(13, 'Paulo Professor', 20, 'Rua da utf 45', '1996-03-02', 'Masculino', 'Unimed', '(42)3987-8999 ', 'Paulo Pai', 'Pilates', '2014-10-10', '2014-10-10', '', 100, '2016-11-15 15:24:23'),
(14, 'Fatima Dana', 10, 'Rua das mininas, 23', '2006-03-04', 'Feminino', 'Unimed', '(42)9998-1234 ', 'Pedor Dana', 'Ortopedia', '2010-05-03', '2010-05-03', '', 30, '2016-11-15 15:26:11'),
(15, 'Guilherme Lerme', 10, 'Rua das flores, 34', '2006-04-11', 'Masculino', 'SUSINHO', '(42)9998-6748 ', 'Maria Lerme', 'Neurologia', '2015-05-10', '2015-05-10', '', 50, '2016-11-15 15:27:55'),
(16, 'Hilma Hilaria', 100, 'Rua dos felizes,000', '1916-10-10', 'Feminino', 'SUSINHA', '(42)3490-9090 ', 'Joanna Hilaria', 'Pilates', '2013-10-10', '2013-10-10', '', 100, '2016-11-15 15:29:45'),
(17, 'Choise da Silwa Saura', 50, 'Rua das Rex, 34', '1966-04-02', 'Feminino', 'UNIMENDES', '(42)9999-99999', 'Man Choise Saura', 'Ortopedia', '2014-03-01', '2014-03-01', '', 100, '2016-11-15 15:31:57');

-- --------------------------------------------------------

--
-- Estrutura da tabela `funcionarios`
--

CREATE TABLE `funcionarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `idade` int(11) DEFAULT NULL,
  `endereco` varchar(60) DEFAULT NULL,
  `datanascimento` date DEFAULT NULL,
  `sexo` varchar(20) DEFAULT NULL,
  `planosaude` varchar(20) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `funcionarios`
--

INSERT INTO `funcionarios` (`id`, `nome`, `idade`, `endereco`, `datanascimento`, `sexo`, `planosaude`, `telefone`) VALUES
(1, 'Pedro Fol', 36, 'Rua dos fofos, 123', '1980-10-10', 'Masculino', 'UNIMED', '(42)9956-7890 '),
(2, 'Jose Med', 50, 'Rua dos Fisioterapeutas', '1960-11-10', 'Masculino', 'Espertesa', '(34)9987-6790 '),
(3, 'Meiriane Wit', 36, 'Rua das Bruxas,56', '1980-02-01', 'Feminino', 'Certeza', '(42)9978-7989 '),
(4, 'Joana B', 30, 'Rua das Joannas', '1986-02-03', 'Feminino', 'US', '(42)9945-3434 '),
(5, 'Joanna B', 36, 'Rua das Joannas', '1980-03-10', 'Feminino', 'USS', '(42)9945-3434 ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `avaliacao`
--
ALTER TABLE `avaliacao`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `avaliacao`
--
ALTER TABLE `avaliacao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
