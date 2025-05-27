-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2025 at 04:00 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `raja_konsol`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `id_akun` varchar(255) NOT NULL,
  `role` enum('Admin','Kasir') DEFAULT NULL,
  `username` varchar(12) NOT NULL,
  `password` varchar(8) NOT NULL,
  `nama_lengkap` varchar(25) NOT NULL,
  `no_telp` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `akun`
--

INSERT INTO `akun` (`id_akun`, `role`, `username`, `password`, `nama_lengkap`, `no_telp`) VALUES
('ADM001', 'Admin', 'admin', '123', 'Raymond', NULL),
('KSR001', 'Kasir', 'kasir1', '123', 'Bagus Setianto', '081234567890'),
('KSR317', 'Kasir', 'Kasir2', '123', 'Ramadhan', '08');

-- --------------------------------------------------------

--
-- Table structure for table `konsol`
--

CREATE TABLE `konsol` (
  `id_konsol` varchar(255) NOT NULL,
  `kategori` enum('PS1','PS2','PS3','PS4','PS5','Xbox 360','Xbox One','Xbox Series X','Xbox Series S') DEFAULT NULL,
  `harga_sewa` int(11) DEFAULT NULL,
  `status` enum('Tersedia','Disewa') DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `konsol`
--

INSERT INTO `konsol` (`id_konsol`, `kategori`, `harga_sewa`, `status`, `isdeleted`) VALUES
('PSDex001', 'PS1', 1500, 'Tersedia', 1),
('PSDex002', 'PS2', 3000, 'Tersedia', NULL),
('PSDex003', 'PS3', 5000, 'Tersedia', NULL),
('XbDex001', 'PS4', 8000, 'Tersedia', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sewa`
--

CREATE TABLE `sewa` (
  `id_sewa` int(11) NOT NULL,
  `id_konsol` varchar(255) DEFAULT NULL,
  `id_kasir` varchar(255) DEFAULT NULL,
  `nama_penyewa` varchar(255) DEFAULT NULL,
  `alamat_penyewa` varchar(255) DEFAULT NULL,
  `no_telp` varchar(255) DEFAULT NULL,
  `jenis_sewa` enum('Jam','Hari') DEFAULT NULL,
  `durasi` int(11) DEFAULT NULL,
  `tanggal_sewa` datetime DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sewa`
--

INSERT INTO `sewa` (`id_sewa`, `id_konsol`, `id_kasir`, `nama_penyewa`, `alamat_penyewa`, `no_telp`, `jenis_sewa`, `durasi`, `tanggal_sewa`, `total`) VALUES
(3, 'PSDex001', 'KSR001', 'Ray', 'Jl', '08', 'Jam', 5, '2025-05-27 00:00:00', 288000),
(4, 'XbDex001', 'KSR317', 'Raymond', 'Jl. Cendana 15', '089691561083', 'Hari', 5, '2025-05-27 00:00:00', 60000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indexes for table `konsol`
--
ALTER TABLE `konsol`
  ADD PRIMARY KEY (`id_konsol`);

--
-- Indexes for table `sewa`
--
ALTER TABLE `sewa`
  ADD PRIMARY KEY (`id_sewa`),
  ADD KEY `id_konsol` (`id_konsol`),
  ADD KEY `id_kasir` (`id_kasir`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sewa`
--
ALTER TABLE `sewa`
  MODIFY `id_sewa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sewa`
--
ALTER TABLE `sewa`
  ADD CONSTRAINT `sewa_ibfk_1` FOREIGN KEY (`id_konsol`) REFERENCES `konsol` (`id_konsol`),
  ADD CONSTRAINT `sewa_ibfk_2` FOREIGN KEY (`id_kasir`) REFERENCES `akun` (`id_akun`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
