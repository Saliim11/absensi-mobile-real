-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 03, 2019 at 06:11 AM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `master_absen`
--

-- --------------------------------------------------------

--
-- Table structure for table `absen_user`
--

CREATE TABLE `absen_user` (
  `id_absen` char(36) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `status_absen` varchar(5) NOT NULL,
  `id_user` char(36) NOT NULL,
  `gambar` text NOT NULL,
  `jam_masuk` datetime NOT NULL,
  `jam_pulang` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `absen_user`
--

INSERT INTO `absen_user` (`id_absen`, `nama`, `lokasi`, `status_absen`, `id_user`, `gambar`, `jam_masuk`, `jam_pulang`) VALUES
('06ac794c-9a57-11e9-bc11-3c075420daf5', 'Qalbun Saliim', 'idn', 'HADIR', '7f770958-7add-11e9-a56f-3c075420daf5', 'gambar/5d173a99357c6.png', '2019-06-29 17:16:57', '2019-06-29 17:18:33'),
('4b9d072c-9bad-11e9-bd5a-3c075420daf5', 'Maulana Hasbi', 'Wahana', 'HADIR', '447078a4-93f5-11e9-b1f4-3c075420daf5', '-', '2019-07-01 10:07:01', '2019-07-01 10:07:32'),
('5a516e94-9a8a-11e9-bc11-3c075420daf5', 'Maulana Hasbi', 'idn', 'HADIR', '447078a4-93f5-11e9-b1f4-3c075420daf5', '-', '2019-06-29 23:24:22', '2019-06-29 23:24:24'),
('63d2f756-9c71-11e9-8f73-3c075420daf5', 'Bot 1', 'Wahana', 'HADIR', 'c3b67d26-9bb0-11e9-bd5a-3c075420daf5', '-', '2019-07-02 09:30:43', '2019-07-02 09:30:58'),
('7772f9b4-9c71-11e9-8f73-3c075420daf5', 'Bot 2', 'Wahana', 'HADIR', 'a86ee52c-9bb4-11e9-bd5a-3c075420daf5', '-', '2019-07-02 09:31:16', '0000-00-00 00:00:00'),
('82107ba8-9c71-11e9-8f73-3c075420daf5', 'Qalbun Saliim', 'Wahana', 'HADIR', '7f770958-7add-11e9-a56f-3c075420daf5', '-', '2019-07-02 09:31:33', '2019-07-02 09:32:00'),
('97f9e726-9d41-11e9-b960-3c075420daf5', 'Qalbun Saliim', 'Wahana', 'HADIR', '7f770958-7add-11e9-a56f-3c075420daf5', '-', '2019-07-03 10:21:05', '0000-00-00 00:00:00'),
('9ae67ef2-9c71-11e9-8f73-3c075420daf5', 'Maulana Hasbi', 'Wahana', 'HADIR', '447078a4-93f5-11e9-b1f4-3c075420daf5', '-', '2019-07-02 09:32:15', '2019-07-02 09:33:00'),
('9b7fbd38-9ba9-11e9-bd5a-3c075420daf5', 'Qalbun Saliim', 'Wahana', 'HADIR', '7f770958-7add-11e9-a56f-3c075420daf5', '-', '2019-07-01 09:40:37', '2019-07-01 10:14:39'),
('bd6b0e72-9d41-11e9-b960-3c075420daf5', 'Maulana Hasbi', 'Wahana', 'HADIR', '447078a4-93f5-11e9-b1f4-3c075420daf5', '-', '2019-07-03 10:22:08', '0000-00-00 00:00:00'),
('d8d43fe6-9bb4-11e9-bd5a-3c075420daf5', 'Bot 1', 'Wahana', 'HADIR', 'c3b67d26-9bb0-11e9-bd5a-3c075420daf5', '-', '2019-07-01 11:01:04', '0000-00-00 00:00:00'),
('e8ac1d8a-9bb4-11e9-bd5a-3c075420daf5', 'Bot 2', 'Wahana', 'HADIR', 'a86ee52c-9bb4-11e9-bd5a-3c075420daf5', '-', '2019-07-01 11:01:31', '2019-07-01 11:01:35');

-- --------------------------------------------------------

--
-- Table structure for table `history_absen`
--

CREATE TABLE `history_absen` (
  `id` char(36) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `status_absen` varchar(5) NOT NULL,
  `created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lokasi_event`
--

CREATE TABLE `lokasi_event` (
  `id` char(36) NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `latitude` varchar(15) NOT NULL,
  `longitude` varchar(15) NOT NULL,
  `radius` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lokasi_event`
--

INSERT INTO `lokasi_event` (`id`, `lokasi`, `latitude`, `longitude`, `radius`) VALUES
('38be46ea-79ff-11e9-b5cc-3c075420daf5', 'idn', '-6.197295', '106.795950', '5000'),
('85d65978-9ba5-11e9-bd5a-3c075420daf5', 'Wahana', '-6.155118', '106.836564', '1000'),
('cf55b1c8-7569-11e9-b89a-3c075420daf5', 'PRJ', '-6.146356', '106.846786', '1000'),
('f0c118d6-77b0-11e9-bb00-3c075420daf5', 'home', '-6.125969', '106.890120', '500');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` char(36) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `vsusername` varchar(25) NOT NULL,
  `vspassword` text NOT NULL,
  `vslevel` varchar(15) NOT NULL,
  `id_admin` char(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nama`, `vsusername`, `vspassword`, `vslevel`, `id_admin`) VALUES
('135419f2-93f4-11e9-b1f4-3c075420daf5', 'inne', 'inne', 'c37d11c9c67d1f19c977bbe76be10aee', 'subAdmin', '-'),
('1d01b274-940e-11e9-b1f4-3c075420daf5', 'Bpk. Kelvin', 'kelvin', 'b2c6de510d584484d74c9aa9f8fa9f04', 'admin', '-'),
('447078a4-93f5-11e9-b1f4-3c075420daf5', 'Maulana Hasbi', 'pace', '5ba1c4ecf208b1757df653813416a886', 'normal user', '135419f2-93f4-11e9-b1f4-3c075420daf5'),
('7f770958-7add-11e9-a56f-3c075420daf5', 'Qalbun Saliim', 'saliim', '7d40ac19382aa460347d016c3ce8255f', 'normal user', '135419f2-93f4-11e9-b1f4-3c075420daf5'),
('8dcbab32-9bb0-11e9-bd5a-3c075420daf5', 'Bot admin', 'bot', 'fabcaa97871555b68aa095335975e613', 'subAdmin', '-'),
('a86ee52c-9bb4-11e9-bd5a-3c075420daf5', 'Bot 2', 'bot2', 'fabcaa97871555b68aa095335975e613', 'normal user', '8dcbab32-9bb0-11e9-bd5a-3c075420daf5'),
('c3b67d26-9bb0-11e9-bd5a-3c075420daf5', 'Bot 1', 'bot1', 'fabcaa97871555b68aa095335975e613', 'normal user', '8dcbab32-9bb0-11e9-bd5a-3c075420daf5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absen_user`
--
ALTER TABLE `absen_user`
  ADD PRIMARY KEY (`id_absen`);

--
-- Indexes for table `history_absen`
--
ALTER TABLE `history_absen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lokasi_event`
--
ALTER TABLE `lokasi_event`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
