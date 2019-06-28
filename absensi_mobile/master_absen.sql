-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 28, 2019 at 11:52 AM
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
  `id_user` char(36) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `status_absen` varchar(5) NOT NULL,
  `id_absen` char(36) NOT NULL,
  `gambar` text NOT NULL,
  `jam_masuk` datetime NOT NULL,
  `jam_pulang` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `absen_user`
--

INSERT INTO `absen_user` (`id_user`, `nama`, `lokasi`, `status_absen`, `id_absen`, `gambar`, `jam_masuk`, `jam_pulang`) VALUES
('0362b5f4-9984-11e9-8d0f-3c075420daf5', 'Qalbun Saliim', 'Wahana', 'HADIR', '7f770958-7add-11e9-a56f-3c075420daf5', '-', '2019-06-28 16:06:28', '2019-06-28 16:06:36'),
('76302d1a-9983-11e9-8d0f-3c075420daf5', 'Qalbun Saliim', 'Wahana', 'HADIR', '7f770958-7add-11e9-a56f-3c075420daf5', '-', '2019-06-27 16:02:31', '0000-00-00 00:00:00'),
('8ae7bc8c-9983-11e9-8d0f-3c075420daf5', 'Maulana Hasbi', 'Wahana', 'HADIR', '447078a4-93f5-11e9-b1f4-3c075420daf5', '-', '2019-06-27 16:03:06', '0000-00-00 00:00:00'),
('d6f84ede-9988-11e9-8d0f-3c075420daf5', 'Maulana Hasbi', 'Wahana', 'HADIR', '447078a4-93f5-11e9-b1f4-3c075420daf5', '-', '2019-06-28 16:41:01', '2019-06-28 16:41:33');

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
('bd72c5ee-7c45-11e9-9b2e-3c075420daf5', 'Wahana', '-6.1550988', '106.8369706', '8000'),
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
('0ecd7524-98a2-11e9-bbcb-3c075420daf5', 'tesss', 'tes', '28b662d883b6d76fd96e4ddc5e9ba780', 'normal user', '135419f2-93f4-11e9-b1f4-3c075420daf5'),
('1306fb56-97c1-11e9-8481-3c075420daf5', 'alim', 'alim', '3ea6277babd0570c650fca3d17ec4bc5', 'normal user', '1d01b274-940e-11e9-b1f4-3c075420daf5'),
('135419f2-93f4-11e9-b1f4-3c075420daf5', 'inne', 'inne', 'c37d11c9c67d1f19c977bbe76be10aee', 'subAdmin', '-'),
('1d01b274-940e-11e9-b1f4-3c075420daf5', 'Bpk. Kelvin', 'kelvin', 'b2c6de510d584484d74c9aa9f8fa9f04', 'admin', '-'),
('447078a4-93f5-11e9-b1f4-3c075420daf5', 'Maulana Hasbi', 'pace', '5ba1c4ecf208b1757df653813416a886', 'normal user', '135419f2-93f4-11e9-b1f4-3c075420daf5'),
('7f770958-7add-11e9-a56f-3c075420daf5', 'Qalbun Saliim', 'saliim', '7d40ac19382aa460347d016c3ce8255f', 'normal user', '135419f2-93f4-11e9-b1f4-3c075420daf5');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absen_user`
--
ALTER TABLE `absen_user`
  ADD PRIMARY KEY (`id_user`);

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
