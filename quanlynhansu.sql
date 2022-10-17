-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2022 at 05:27 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlynhansu`
--

-- --------------------------------------------------------

--
-- Table structure for table `bangcap`
--

CREATE TABLE `bangcap` (
  `Mã bằng cấp` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên bằng cấp` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ngày bắt đầu hiệu lực` date NOT NULL,
  `Ngày hết hạn hiệu lực` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bangcap`
--

INSERT INTO `bangcap` (`Mã bằng cấp`, `Tên bằng cấp`, `Ngày bắt đầu hiệu lực`, `Ngày hết hạn hiệu lực`) VALUES
('BC001', 'Đại học', '2014-12-11', '2025-01-01'),
('BC002', 'IELTS', '2019-02-03', '2025-02-03'),
('BC003', 'Cao đẳng', '2022-05-10', '2022-05-20');

-- --------------------------------------------------------

--
-- Table structure for table `bangchamcong`
--

CREATE TABLE `bangchamcong` (
  `Mã bảng chấm công` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã nhân viên` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Thời gian` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Số giờ trễ` float NOT NULL,
  `Số giờ làm thêm` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bangchamcong`
--

INSERT INTO `bangchamcong` (`Mã bảng chấm công`, `Mã nhân viên`, `Thời gian`, `Số giờ trễ`, `Số giờ làm thêm`) VALUES
('BCC001', 'NV001', '05/2022', 210, 21),
('BCC002', 'NV002', '05/2022', 220, 0),
('BCC003', 'NV003', '05/2022', 220, 0),
('BCC004', 'NV004', '05/2022', 220, 0),
('BCC005', 'NV001', '06/2022', 210, 11),
('BCC006', 'NV002', '06/2022', 213, 0),
('BCC007', 'NV003', '06/2022', 211, 2),
('BCC008', 'NV004', '06/2022', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `baohiem`
--

CREATE TABLE `baohiem` (
  `Mã bảo hiểm` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên bảo hiểm` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `baohiem`
--

INSERT INTO `baohiem` (`Mã bảo hiểm`, `Tên bảo hiểm`) VALUES
('BH001', 'Bảo hiểm xã hội'),
('BH002', 'Bảo hiểm nhân thọ'),
('BH003', 'Bảo hiểm y tế'),
('BH004', 'Bảo hiểm tai nạn');

-- --------------------------------------------------------

--
-- Table structure for table `chitietbangchamcong`
--

CREATE TABLE `chitietbangchamcong` (
  `Mã bảng chấm công` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ngày` date NOT NULL,
  `Giờ vào` time NOT NULL,
  `Giờ ra` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitietbangchamcong`
--

INSERT INTO `chitietbangchamcong` (`Mã bảng chấm công`, `Ngày`, `Giờ vào`, `Giờ ra`) VALUES
('BCC001', '2022-05-01', '07:00:00', '17:00:00'),
('BCC001', '2022-05-02', '07:00:00', '18:00:00'),
('BCC001', '2022-05-03', '00:00:00', '00:00:00'),
('BCC001', '2022-05-04', '00:00:00', '00:00:00'),
('BCC001', '2022-05-05', '00:00:00', '00:00:00'),
('BCC001', '2022-05-06', '00:00:00', '00:00:00'),
('BCC001', '2022-05-07', '00:00:00', '00:00:00'),
('BCC001', '2022-05-08', '00:00:00', '00:00:00'),
('BCC001', '2022-05-09', '00:00:00', '00:00:00'),
('BCC001', '2022-05-10', '00:00:00', '00:00:00'),
('BCC001', '2022-05-11', '00:00:00', '00:00:00'),
('BCC001', '2022-05-12', '00:00:00', '00:00:00'),
('BCC001', '2022-05-13', '00:00:00', '00:00:00'),
('BCC001', '2022-05-14', '00:00:00', '00:00:00'),
('BCC001', '2022-05-15', '00:00:00', '00:00:00'),
('BCC001', '2022-05-16', '00:00:00', '00:00:00'),
('BCC001', '2022-05-17', '00:00:00', '00:00:00'),
('BCC001', '2022-05-18', '00:00:00', '00:00:00'),
('BCC001', '2022-05-19', '00:00:00', '00:00:00'),
('BCC001', '2022-05-20', '00:00:00', '00:00:00'),
('BCC001', '2022-05-21', '07:00:00', '17:00:00'),
('BCC001', '2022-05-22', '00:00:00', '00:00:00'),
('BCC001', '2022-05-23', '00:00:00', '00:00:00'),
('BCC001', '2022-05-24', '00:00:00', '00:00:00'),
('BCC001', '2022-05-25', '00:00:00', '00:00:00'),
('BCC001', '2022-05-26', '00:00:00', '00:00:00'),
('BCC001', '2022-05-27', '00:00:00', '00:00:00'),
('BCC001', '2022-05-28', '00:00:00', '00:00:00'),
('BCC001', '2022-05-29', '00:00:00', '00:00:00'),
('BCC001', '2022-05-30', '00:00:00', '00:00:00'),
('BCC001', '2022-05-31', '00:00:00', '00:00:00'),
('BCC002', '2022-05-01', '00:00:00', '00:00:00'),
('BCC002', '2022-05-02', '00:00:00', '00:00:00'),
('BCC002', '2022-05-03', '00:00:00', '00:00:00'),
('BCC002', '2022-05-04', '00:00:00', '00:00:00'),
('BCC002', '2022-05-05', '00:00:00', '00:00:00'),
('BCC002', '2022-05-06', '00:00:00', '00:00:00'),
('BCC002', '2022-05-07', '00:00:00', '00:00:00'),
('BCC002', '2022-05-08', '00:00:00', '00:00:00'),
('BCC002', '2022-05-09', '00:00:00', '00:00:00'),
('BCC002', '2022-05-10', '00:00:00', '00:00:00'),
('BCC002', '2022-05-11', '00:00:00', '00:00:00'),
('BCC002', '2022-05-12', '00:00:00', '00:00:00'),
('BCC002', '2022-05-13', '00:00:00', '00:00:00'),
('BCC002', '2022-05-14', '00:00:00', '00:00:00'),
('BCC002', '2022-05-15', '00:00:00', '00:00:00'),
('BCC002', '2022-05-16', '00:00:00', '00:00:00'),
('BCC002', '2022-05-17', '00:00:00', '00:00:00'),
('BCC002', '2022-05-18', '00:00:00', '00:00:00'),
('BCC002', '2022-05-19', '00:00:00', '00:00:00'),
('BCC002', '2022-05-20', '00:00:00', '00:00:00'),
('BCC002', '2022-05-21', '00:00:00', '00:00:00'),
('BCC002', '2022-05-22', '00:00:00', '00:00:00'),
('BCC002', '2022-05-23', '00:00:00', '00:00:00'),
('BCC002', '2022-05-24', '00:00:00', '00:00:00'),
('BCC002', '2022-05-25', '00:00:00', '00:00:00'),
('BCC002', '2022-05-26', '00:00:00', '00:00:00'),
('BCC002', '2022-05-27', '00:00:00', '00:00:00'),
('BCC002', '2022-05-28', '00:00:00', '00:00:00'),
('BCC002', '2022-05-29', '00:00:00', '00:00:00'),
('BCC002', '2022-05-30', '00:00:00', '00:00:00'),
('BCC002', '2022-05-31', '00:00:00', '00:00:00'),
('BCC003', '2022-05-01', '00:00:00', '00:00:00'),
('BCC003', '2022-05-02', '00:00:00', '00:00:00'),
('BCC003', '2022-05-03', '00:00:00', '00:00:00'),
('BCC003', '2022-05-04', '00:00:00', '00:00:00'),
('BCC003', '2022-05-05', '00:00:00', '00:00:00'),
('BCC003', '2022-05-06', '00:00:00', '00:00:00'),
('BCC003', '2022-05-07', '00:00:00', '00:00:00'),
('BCC003', '2022-05-08', '00:00:00', '00:00:00'),
('BCC003', '2022-05-09', '00:00:00', '00:00:00'),
('BCC003', '2022-05-10', '00:00:00', '00:00:00'),
('BCC003', '2022-05-11', '00:00:00', '00:00:00'),
('BCC003', '2022-05-12', '00:00:00', '00:00:00'),
('BCC003', '2022-05-13', '00:00:00', '00:00:00'),
('BCC003', '2022-05-14', '00:00:00', '00:00:00'),
('BCC003', '2022-05-15', '00:00:00', '00:00:00'),
('BCC003', '2022-05-16', '00:00:00', '00:00:00'),
('BCC003', '2022-05-17', '00:00:00', '00:00:00'),
('BCC003', '2022-05-18', '00:00:00', '00:00:00'),
('BCC003', '2022-05-19', '00:00:00', '00:00:00'),
('BCC003', '2022-05-20', '00:00:00', '00:00:00'),
('BCC003', '2022-05-21', '00:00:00', '00:00:00'),
('BCC003', '2022-05-22', '00:00:00', '00:00:00'),
('BCC003', '2022-05-23', '00:00:00', '00:00:00'),
('BCC003', '2022-05-24', '00:00:00', '00:00:00'),
('BCC003', '2022-05-25', '00:00:00', '00:00:00'),
('BCC003', '2022-05-26', '00:00:00', '00:00:00'),
('BCC003', '2022-05-27', '00:00:00', '00:00:00'),
('BCC003', '2022-05-28', '00:00:00', '00:00:00'),
('BCC003', '2022-05-29', '00:00:00', '00:00:00'),
('BCC003', '2022-05-30', '00:00:00', '00:00:00'),
('BCC003', '2022-05-31', '00:00:00', '00:00:00'),
('BCC004', '2022-05-01', '00:00:00', '00:00:00'),
('BCC004', '2022-05-02', '00:00:00', '00:00:00'),
('BCC004', '2022-05-03', '00:00:00', '00:00:00'),
('BCC004', '2022-05-04', '00:00:00', '00:00:00'),
('BCC004', '2022-05-05', '00:00:00', '00:00:00'),
('BCC004', '2022-05-06', '00:00:00', '00:00:00'),
('BCC004', '2022-05-07', '00:00:00', '00:00:00'),
('BCC004', '2022-05-08', '00:00:00', '00:00:00'),
('BCC004', '2022-05-09', '00:00:00', '00:00:00'),
('BCC004', '2022-05-10', '00:00:00', '00:00:00'),
('BCC004', '2022-05-11', '00:00:00', '00:00:00'),
('BCC004', '2022-05-12', '00:00:00', '00:00:00'),
('BCC004', '2022-05-13', '00:00:00', '00:00:00'),
('BCC004', '2022-05-14', '00:00:00', '00:00:00'),
('BCC004', '2022-05-15', '00:00:00', '00:00:00'),
('BCC004', '2022-05-16', '00:00:00', '00:00:00'),
('BCC004', '2022-05-17', '00:00:00', '00:00:00'),
('BCC004', '2022-05-18', '00:00:00', '00:00:00'),
('BCC004', '2022-05-19', '00:00:00', '00:00:00'),
('BCC004', '2022-05-20', '00:00:00', '00:00:00'),
('BCC004', '2022-05-21', '00:00:00', '00:00:00'),
('BCC004', '2022-05-22', '00:00:00', '00:00:00'),
('BCC004', '2022-05-23', '00:00:00', '00:00:00'),
('BCC004', '2022-05-24', '00:00:00', '00:00:00'),
('BCC004', '2022-05-25', '00:00:00', '00:00:00'),
('BCC004', '2022-05-26', '00:00:00', '00:00:00'),
('BCC004', '2022-05-27', '00:00:00', '00:00:00'),
('BCC004', '2022-05-28', '00:00:00', '00:00:00'),
('BCC004', '2022-05-29', '00:00:00', '00:00:00'),
('BCC004', '2022-05-30', '00:00:00', '00:00:00'),
('BCC004', '2022-05-31', '00:00:00', '00:00:00'),
('BCC005', '2022-06-01', '07:00:00', '17:00:00'),
('BCC005', '2022-06-02', '00:00:00', '00:00:00'),
('BCC005', '2022-06-03', '00:00:00', '00:00:00'),
('BCC005', '2022-06-04', '00:00:00', '00:00:00'),
('BCC005', '2022-06-05', '07:00:00', '18:00:00'),
('BCC005', '2022-06-06', '00:00:00', '00:00:00'),
('BCC005', '2022-06-07', '00:00:00', '00:00:00'),
('BCC005', '2022-06-08', '00:00:00', '00:00:00'),
('BCC005', '2022-06-09', '00:00:00', '00:00:00'),
('BCC005', '2022-06-10', '00:00:00', '00:00:00'),
('BCC005', '2022-06-11', '00:00:00', '00:00:00'),
('BCC005', '2022-06-12', '00:00:00', '00:00:00'),
('BCC005', '2022-06-13', '00:00:00', '00:00:00'),
('BCC005', '2022-06-14', '00:00:00', '00:00:00'),
('BCC005', '2022-06-15', '00:00:00', '00:00:00'),
('BCC005', '2022-06-16', '00:00:00', '00:00:00'),
('BCC005', '2022-06-17', '00:00:00', '00:00:00'),
('BCC005', '2022-06-18', '00:00:00', '00:00:00'),
('BCC005', '2022-06-19', '00:00:00', '00:00:00'),
('BCC005', '2022-06-20', '00:00:00', '00:00:00'),
('BCC005', '2022-06-21', '00:00:00', '00:00:00'),
('BCC005', '2022-06-22', '00:00:00', '00:00:00'),
('BCC005', '2022-06-23', '00:00:00', '00:00:00'),
('BCC005', '2022-06-24', '00:00:00', '00:00:00'),
('BCC005', '2022-06-25', '00:00:00', '00:00:00'),
('BCC005', '2022-06-26', '00:00:00', '00:00:00'),
('BCC005', '2022-06-27', '00:00:00', '00:00:00'),
('BCC005', '2022-06-28', '00:00:00', '00:00:00'),
('BCC005', '2022-06-29', '00:00:00', '00:00:00'),
('BCC005', '2022-06-30', '00:00:00', '00:00:00'),
('BCC006', '2022-06-01', '09:00:00', '16:00:00'),
('BCC006', '2022-06-02', '00:00:00', '00:00:00'),
('BCC006', '2022-06-03', '00:00:00', '00:00:00'),
('BCC006', '2022-06-04', '00:00:00', '00:00:00'),
('BCC006', '2022-06-05', '00:00:00', '00:00:00'),
('BCC006', '2022-06-06', '00:00:00', '00:00:00'),
('BCC006', '2022-06-07', '00:00:00', '00:00:00'),
('BCC006', '2022-06-08', '00:00:00', '00:00:00'),
('BCC006', '2022-06-09', '00:00:00', '00:00:00'),
('BCC006', '2022-06-10', '00:00:00', '00:00:00'),
('BCC006', '2022-06-11', '00:00:00', '00:00:00'),
('BCC006', '2022-06-12', '00:00:00', '00:00:00'),
('BCC006', '2022-06-13', '00:00:00', '00:00:00'),
('BCC006', '2022-06-14', '00:00:00', '00:00:00'),
('BCC006', '2022-06-15', '00:00:00', '00:00:00'),
('BCC006', '2022-06-16', '00:00:00', '00:00:00'),
('BCC006', '2022-06-17', '00:00:00', '00:00:00'),
('BCC006', '2022-06-18', '00:00:00', '00:00:00'),
('BCC006', '2022-06-19', '00:00:00', '00:00:00'),
('BCC006', '2022-06-20', '00:00:00', '00:00:00'),
('BCC006', '2022-06-21', '00:00:00', '00:00:00'),
('BCC006', '2022-06-22', '00:00:00', '00:00:00'),
('BCC006', '2022-06-23', '00:00:00', '00:00:00'),
('BCC006', '2022-06-24', '00:00:00', '00:00:00'),
('BCC006', '2022-06-25', '00:00:00', '00:00:00'),
('BCC006', '2022-06-26', '00:00:00', '00:00:00'),
('BCC006', '2022-06-27', '00:00:00', '00:00:00'),
('BCC006', '2022-06-28', '00:00:00', '00:00:00'),
('BCC006', '2022-06-29', '00:00:00', '00:00:00'),
('BCC006', '2022-06-30', '00:00:00', '00:00:00'),
('BCC007', '2022-06-01', '08:00:00', '19:00:00'),
('BCC007', '2022-06-02', '00:00:00', '00:00:00'),
('BCC007', '2022-06-03', '00:00:00', '00:00:00'),
('BCC007', '2022-06-04', '00:00:00', '00:00:00'),
('BCC007', '2022-06-05', '00:00:00', '00:00:00'),
('BCC007', '2022-06-06', '00:00:00', '00:00:00'),
('BCC007', '2022-06-07', '00:00:00', '00:00:00'),
('BCC007', '2022-06-08', '00:00:00', '00:00:00'),
('BCC007', '2022-06-09', '00:00:00', '00:00:00'),
('BCC007', '2022-06-10', '00:00:00', '00:00:00'),
('BCC007', '2022-06-11', '00:00:00', '00:00:00'),
('BCC007', '2022-06-12', '00:00:00', '00:00:00'),
('BCC007', '2022-06-13', '00:00:00', '00:00:00'),
('BCC007', '2022-06-14', '00:00:00', '00:00:00'),
('BCC007', '2022-06-15', '00:00:00', '00:00:00'),
('BCC007', '2022-06-16', '00:00:00', '00:00:00'),
('BCC007', '2022-06-17', '00:00:00', '00:00:00'),
('BCC007', '2022-06-18', '00:00:00', '00:00:00'),
('BCC007', '2022-06-19', '00:00:00', '00:00:00'),
('BCC007', '2022-06-20', '00:00:00', '00:00:00'),
('BCC007', '2022-06-21', '00:00:00', '00:00:00'),
('BCC007', '2022-06-22', '00:00:00', '00:00:00'),
('BCC007', '2022-06-23', '00:00:00', '00:00:00'),
('BCC007', '2022-06-24', '00:00:00', '00:00:00'),
('BCC007', '2022-06-25', '00:00:00', '00:00:00'),
('BCC007', '2022-06-26', '00:00:00', '00:00:00'),
('BCC007', '2022-06-27', '00:00:00', '00:00:00'),
('BCC007', '2022-06-28', '00:00:00', '00:00:00'),
('BCC007', '2022-06-29', '00:00:00', '00:00:00'),
('BCC007', '2022-06-30', '00:00:00', '00:00:00'),
('BCC008', '2022-06-01', '00:00:00', '00:00:00'),
('BCC008', '2022-06-02', '00:00:00', '00:00:00'),
('BCC008', '2022-06-03', '00:00:00', '00:00:00'),
('BCC008', '2022-06-04', '00:00:00', '00:00:00'),
('BCC008', '2022-06-05', '00:00:00', '00:00:00'),
('BCC008', '2022-06-06', '00:00:00', '00:00:00'),
('BCC008', '2022-06-07', '00:00:00', '00:00:00'),
('BCC008', '2022-06-08', '00:00:00', '00:00:00'),
('BCC008', '2022-06-09', '00:00:00', '00:00:00'),
('BCC008', '2022-06-10', '00:00:00', '00:00:00'),
('BCC008', '2022-06-11', '00:00:00', '00:00:00'),
('BCC008', '2022-06-12', '00:00:00', '00:00:00'),
('BCC008', '2022-06-13', '00:00:00', '00:00:00'),
('BCC008', '2022-06-14', '00:00:00', '00:00:00'),
('BCC008', '2022-06-15', '00:00:00', '00:00:00'),
('BCC008', '2022-06-16', '00:00:00', '00:00:00'),
('BCC008', '2022-06-17', '00:00:00', '00:00:00'),
('BCC008', '2022-06-18', '00:00:00', '00:00:00'),
('BCC008', '2022-06-19', '00:00:00', '00:00:00'),
('BCC008', '2022-06-20', '00:00:00', '00:00:00'),
('BCC008', '2022-06-21', '00:00:00', '00:00:00'),
('BCC008', '2022-06-22', '00:00:00', '00:00:00'),
('BCC008', '2022-06-23', '00:00:00', '00:00:00'),
('BCC008', '2022-06-24', '00:00:00', '00:00:00'),
('BCC008', '2022-06-25', '00:00:00', '00:00:00'),
('BCC008', '2022-06-26', '00:00:00', '00:00:00'),
('BCC008', '2022-06-27', '00:00:00', '00:00:00'),
('BCC008', '2022-06-28', '00:00:00', '00:00:00'),
('BCC008', '2022-06-29', '00:00:00', '00:00:00'),
('BCC008', '2022-06-30', '00:00:00', '00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `chitietchucnang`
--

CREATE TABLE `chitietchucnang` (
  `Mã chi tiết chức năng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên chi tiết chức năng` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã chức năng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitietchucnang`
--

INSERT INTO `chitietchucnang` (`Mã chi tiết chức năng`, `Tên chi tiết chức năng`, `Mã chức năng`) VALUES
('CTCN001', 'Quản lý phòng ban', 'CN001'),
('CTCN002', 'Quản lý bằng cấp', 'CN001'),
('CTCN003', 'Quản lý bảo hiểm', 'CN001'),
('CTCN004', 'Quản lý chức vụ', 'CN001'),
('CTCN005', 'Quản lý hợp đồng lao động', 'CN001'),
('CTCN006', 'Quản lý thưởng', 'CN002'),
('CTCN007', 'Quản lý hệ số lương', 'CN002'),
('CTCN008', 'Quản lý phụ cấp', 'CN002'),
('CTCN009', 'Quản lý lương căn bản', 'CN002');

-- --------------------------------------------------------

--
-- Table structure for table `chitietchucnang-chucnang`
--

CREATE TABLE `chitietchucnang-chucnang` (
  `Mã chức năng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã chi tiết chức năng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chitietchucnang-chucnang`
--

INSERT INTO `chitietchucnang-chucnang` (`Mã chức năng`, `Mã chi tiết chức năng`) VALUES
('CN001', 'CTCN001'),
('CN001', 'CTCN002'),
('CN001', 'CTCN003'),
('CN001', 'CTCN004'),
('CN001', 'CTCN005');

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `Mã chức năng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên chức năng` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`Mã chức năng`, `Tên chức năng`) VALUES
('CN001', 'Quản lý nhân viên'),
('CN002', 'Quản lý lương'),
('CN003', 'Quản lý chức năng'),
('CN004', 'Quản lý quyền'),
('CN005', 'Quản lý tài khoản'),
('CN006', 'Bảng chấm công'),
('CN007', 'Thống kê');

-- --------------------------------------------------------

--
-- Table structure for table `chucvu`
--

CREATE TABLE `chucvu` (
  `Mã chức vụ` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên chức vụ` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chucvu`
--

INSERT INTO `chucvu` (`Mã chức vụ`, `Tên chức vụ`) VALUES
('CV001', 'Trưởng phòng'),
('CV002', 'Phó trưởng phòng'),
('CV003', 'Nhân viên');

-- --------------------------------------------------------

--
-- Table structure for table `hesoluong`
--

CREATE TABLE `hesoluong` (
  `Mã hệ số lương` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Hệ số lương` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hesoluong`
--

INSERT INTO `hesoluong` (`Mã hệ số lương`, `Hệ số lương`) VALUES
('HSL001', 2);

-- --------------------------------------------------------

--
-- Table structure for table `hopdonglaodong`
--

CREATE TABLE `hopdonglaodong` (
  `Mã HĐLĐ` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Loại HĐLĐ` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ngày bắt đầu` date NOT NULL,
  `Ngày kết thúc` date NOT NULL,
  `Địa điểm làm việc` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ngày kí` date NOT NULL,
  `Thời hạn hợp đồng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Làm việc trong ngày từ` time NOT NULL,
  `Làm việc trong ngày đến` time NOT NULL,
  `Làm việc trong tuần từ` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Làm việc trong tuần đến` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã lương căn bản` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã thưởng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã hệ số lương` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã phụ cấp` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ghi chú` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hopdonglaodong`
--

INSERT INTO `hopdonglaodong` (`Mã HĐLĐ`, `Loại HĐLĐ`, `Ngày bắt đầu`, `Ngày kết thúc`, `Địa điểm làm việc`, `Ngày kí`, `Thời hạn hợp đồng`, `Làm việc trong ngày từ`, `Làm việc trong ngày đến`, `Làm việc trong tuần từ`, `Làm việc trong tuần đến`, `Mã lương căn bản`, `Mã thưởng`, `Mã hệ số lương`, `Mã phụ cấp`, `Ghi chú`) VALUES
('HD001', 'Hợp đồng FULLTIME', '2015-01-01', '2035-01-01', '2 An Dương Vương, phường 7, Q5, TPHCM', '2015-01-01', '20 năm', '07:00:00', '17:00:00', 'Thứ hai', 'Thứ sáu', 'LCB001', 'T001', 'HSL001', 'PC001', ''),
('HDLD002', 'a', '2022-05-11', '2022-05-19', 'a', '2022-05-19', 'b', '07:00:00', '17:00:00', 'a', 'a', 'LCB001', 'T001', 'HSL001', 'PC001', '');

-- --------------------------------------------------------

--
-- Table structure for table `luong`
--

CREATE TABLE `luong` (
  `Mã lương` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã bảng chấm công` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Lương chính thức` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `luong`
--

INSERT INTO `luong` (`Mã lương`, `Mã bảng chấm công`, `Lương chính thức`) VALUES
('L001', 'BCC001', 30998530),
('L002', 'BCC002', 30997800),
('L003', 'BCC003', 30997800),
('L004', 'BCC004', 30997800),
('L005', 'BCC005', 30998230),
('L006', 'BCC006', 30997870),
('L007', 'BCC007', 30997950),
('L008', 'BCC008', 31000000);

-- --------------------------------------------------------

--
-- Table structure for table `luongcanban`
--

CREATE TABLE `luongcanban` (
  `Mã lương căn bản` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Lương căn bản` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `luongcanban`
--

INSERT INTO `luongcanban` (`Mã lương căn bản`, `Lương căn bản`) VALUES
('LCB001', 15000000),
('LCB002', 1.2);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `Mã nhân viên` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Họ tên` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Số điện thoại` int(11) NOT NULL,
  `Ngày sinh` date NOT NULL,
  `Địa chỉ` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Giới tính` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `CMND` int(11) NOT NULL,
  `Tên tài khoản` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã bằng cấp` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã HĐLĐ` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã phòng ban` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`Mã nhân viên`, `Họ tên`, `Email`, `Số điện thoại`, `Ngày sinh`, `Địa chỉ`, `Giới tính`, `CMND`, `Tên tài khoản`, `Mã bằng cấp`, `Mã HĐLĐ`, `Mã phòng ban`) VALUES
('NV001', 'Nguyễn Văn AD', 'a@gmail.com', 900900903, '1988-02-11', '123 Nguyễn Trãi, phường 7, Q1, TPHCM', 'Nam', 28161537, 'admin', 'BC001', 'HD001', 'PB001'),
('NV002', 'Nguyễn Văn B', 'b@gmail.com', 900900902, '1989-01-01', '111 Lê Đức Thọ, phường 1, Q.Gò Vấp, TPHCM', 'Nam', 209185381, 'TK001', 'BC001', 'HD001', 'PB002'),
('NV003', 'Nguyễn Văn C', 'c@gmail.com', 900900906, '1988-02-03', '123 TCV', 'Nữ', 209183746, '1', 'BC001', 'HD001', 'PB003'),
('NV004', 'a', 'a@gmail.com', 909090900, '2022-05-13', 's', 's', 90090090, '1', 'BC001', 'HD001', 'PB001');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien-baohiem`
--

CREATE TABLE `nhanvien-baohiem` (
  `Mã bảo hiểm` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã nhân viên` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ngày bắt đầu` date NOT NULL,
  `Ngày hết hạn` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhanvien-baohiem`
--

INSERT INTO `nhanvien-baohiem` (`Mã bảo hiểm`, `Mã nhân viên`, `Ngày bắt đầu`, `Ngày hết hạn`) VALUES
('BH001', 'NV001', '2022-05-11', '2022-05-20');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien-chucvu`
--

CREATE TABLE `nhanvien-chucvu` (
  `Mã chức vụ` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã nhân viên` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ngày bắt đầu` date NOT NULL,
  `Ngày kết thúc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `phongban`
--

CREATE TABLE `phongban` (
  `Mã phòng ban` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên phòng ban` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `phongban`
--

INSERT INTO `phongban` (`Mã phòng ban`, `Tên phòng ban`) VALUES
('PB001', 'Phòng hành chính'),
('PB002', 'Phòng nhân sự'),
('PB003', 'Phòng kế toán');

-- --------------------------------------------------------

--
-- Table structure for table `phucap`
--

CREATE TABLE `phucap` (
  `Mã phụ cấp` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên phụ cấp` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tiền phụ cấp` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `phucap`
--

INSERT INTO `phucap` (`Mã phụ cấp`, `Tên phụ cấp`, `Tiền phụ cấp`) VALUES
('PC001', 'Tiền ăn', 500000),
('PC002', 'a', 3);

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `Mã quyền` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tên quyền` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`Mã quyền`, `Tên quyền`) VALUES
('ADMIN', 'ADMIN'),
('NV', 'Nhân viên');

-- --------------------------------------------------------

--
-- Table structure for table `quyen-chucnang`
--

CREATE TABLE `quyen-chucnang` (
  `Mã quyền` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mã chức năng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `quyen-chucnang`
--

INSERT INTO `quyen-chucnang` (`Mã quyền`, `Mã chức năng`) VALUES
('ADMIN', 'CN001'),
('ADMIN', 'CN002'),
('ADMIN', 'CN003'),
('ADMIN', 'CN004'),
('ADMIN', 'CN005'),
('ADMIN', 'CN006'),
('ADMIN', 'CN007'),
('NV', 'CN001'),
('NV', 'CN002');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `Tên tài khoản` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Mật khẩu` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Trạng thái` int(11) NOT NULL,
  `Mã quyền` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`Tên tài khoản`, `Mật khẩu`, `Trạng thái`, `Mã quyền`) VALUES
('1', '1', 0, 'NV'),
('2', '1', 1, 'NV'),
('admin', '12345678', 0, 'ADMIN'),
('TK001', '12345678', 0, 'NV');

-- --------------------------------------------------------

--
-- Table structure for table `thuong`
--

CREATE TABLE `thuong` (
  `Mã thưởng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Loại thưởng` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Tiền thưởng` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `thuong`
--

INSERT INTO `thuong` (`Mã thưởng`, `Loại thưởng`, `Tiền thưởng`) VALUES
('T001', 'Thưởng', 500000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bangcap`
--
ALTER TABLE `bangcap`
  ADD PRIMARY KEY (`Mã bằng cấp`);

--
-- Indexes for table `bangchamcong`
--
ALTER TABLE `bangchamcong`
  ADD PRIMARY KEY (`Mã bảng chấm công`,`Mã nhân viên`,`Thời gian`);

--
-- Indexes for table `baohiem`
--
ALTER TABLE `baohiem`
  ADD PRIMARY KEY (`Mã bảo hiểm`);

--
-- Indexes for table `chitietbangchamcong`
--
ALTER TABLE `chitietbangchamcong`
  ADD PRIMARY KEY (`Mã bảng chấm công`,`Ngày`);

--
-- Indexes for table `chitietchucnang`
--
ALTER TABLE `chitietchucnang`
  ADD PRIMARY KEY (`Mã chi tiết chức năng`),
  ADD KEY `cttcn_cn_FK` (`Mã chức năng`);

--
-- Indexes for table `chitietchucnang-chucnang`
--
ALTER TABLE `chitietchucnang-chucnang`
  ADD PRIMARY KEY (`Mã chức năng`,`Mã chi tiết chức năng`),
  ADD KEY `ctcncn_ctcn_FK` (`Mã chi tiết chức năng`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`Mã chức năng`);

--
-- Indexes for table `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`Mã chức vụ`);

--
-- Indexes for table `hesoluong`
--
ALTER TABLE `hesoluong`
  ADD PRIMARY KEY (`Mã hệ số lương`);

--
-- Indexes for table `hopdonglaodong`
--
ALTER TABLE `hopdonglaodong`
  ADD PRIMARY KEY (`Mã HĐLĐ`),
  ADD KEY `hdld_lcb_FK` (`Mã lương căn bản`),
  ADD KEY `hdld_t_FK` (`Mã thưởng`),
  ADD KEY `hdld_hsl_FK` (`Mã hệ số lương`),
  ADD KEY `hdld_pc_FK` (`Mã phụ cấp`);

--
-- Indexes for table `luong`
--
ALTER TABLE `luong`
  ADD PRIMARY KEY (`Mã lương`),
  ADD KEY `l_bcc_FK` (`Mã bảng chấm công`);

--
-- Indexes for table `luongcanban`
--
ALTER TABLE `luongcanban`
  ADD PRIMARY KEY (`Mã lương căn bản`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`Mã nhân viên`),
  ADD KEY `nv_bc_FK` (`Mã bằng cấp`),
  ADD KEY `nv_tk_FK` (`Tên tài khoản`),
  ADD KEY `nv_hdld_FK` (`Mã HĐLĐ`),
  ADD KEY `nv_pb_FK` (`Mã phòng ban`);

--
-- Indexes for table `nhanvien-baohiem`
--
ALTER TABLE `nhanvien-baohiem`
  ADD PRIMARY KEY (`Mã bảo hiểm`,`Mã nhân viên`,`Ngày bắt đầu`),
  ADD KEY `nv_bh_FK` (`Mã nhân viên`);

--
-- Indexes for table `nhanvien-chucvu`
--
ALTER TABLE `nhanvien-chucvu`
  ADD PRIMARY KEY (`Mã chức vụ`,`Mã nhân viên`,`Ngày bắt đầu`),
  ADD KEY `nv_cv_FK` (`Mã nhân viên`);

--
-- Indexes for table `phongban`
--
ALTER TABLE `phongban`
  ADD PRIMARY KEY (`Mã phòng ban`);

--
-- Indexes for table `phucap`
--
ALTER TABLE `phucap`
  ADD PRIMARY KEY (`Mã phụ cấp`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`Mã quyền`);

--
-- Indexes for table `quyen-chucnang`
--
ALTER TABLE `quyen-chucnang`
  ADD PRIMARY KEY (`Mã quyền`,`Mã chức năng`),
  ADD KEY `qcn_cn_FK` (`Mã chức năng`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`Tên tài khoản`),
  ADD KEY `tk_q_FK` (`Mã quyền`);

--
-- Indexes for table `thuong`
--
ALTER TABLE `thuong`
  ADD PRIMARY KEY (`Mã thưởng`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietbangchamcong`
--
ALTER TABLE `chitietbangchamcong`
  ADD CONSTRAINT `ctbcc_bcc_FK` FOREIGN KEY (`Mã bảng chấm công`) REFERENCES `bangchamcong` (`Mã bảng chấm công`);

--
-- Constraints for table `chitietchucnang-chucnang`
--
ALTER TABLE `chitietchucnang-chucnang`
  ADD CONSTRAINT `ctcncn_cn_FK` FOREIGN KEY (`Mã chức năng`) REFERENCES `chucnang` (`Mã chức năng`),
  ADD CONSTRAINT `ctcncn_ctcn_FK` FOREIGN KEY (`Mã chi tiết chức năng`) REFERENCES `chitietchucnang` (`Mã chi tiết chức năng`);

--
-- Constraints for table `hopdonglaodong`
--
ALTER TABLE `hopdonglaodong`
  ADD CONSTRAINT `hdld_hsl_FK` FOREIGN KEY (`Mã hệ số lương`) REFERENCES `hesoluong` (`Mã hệ số lương`),
  ADD CONSTRAINT `hdld_lcb_FK` FOREIGN KEY (`Mã lương căn bản`) REFERENCES `luongcanban` (`Mã lương căn bản`),
  ADD CONSTRAINT `hdld_pc_FK` FOREIGN KEY (`Mã phụ cấp`) REFERENCES `phucap` (`Mã phụ cấp`),
  ADD CONSTRAINT `hdld_t_FK` FOREIGN KEY (`Mã thưởng`) REFERENCES `thuong` (`Mã thưởng`);

--
-- Constraints for table `luong`
--
ALTER TABLE `luong`
  ADD CONSTRAINT `l_bcc_FK` FOREIGN KEY (`Mã bảng chấm công`) REFERENCES `bangchamcong` (`Mã bảng chấm công`);

--
-- Constraints for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nv_bc_FK` FOREIGN KEY (`Mã bằng cấp`) REFERENCES `bangcap` (`Mã bằng cấp`),
  ADD CONSTRAINT `nv_hdld_FK` FOREIGN KEY (`Mã HĐLĐ`) REFERENCES `hopdonglaodong` (`Mã HĐLĐ`),
  ADD CONSTRAINT `nv_pb_FK` FOREIGN KEY (`Mã phòng ban`) REFERENCES `phongban` (`Mã phòng ban`),
  ADD CONSTRAINT `nv_tk_FK` FOREIGN KEY (`Tên tài khoản`) REFERENCES `taikhoan` (`Tên tài khoản`);

--
-- Constraints for table `nhanvien-baohiem`
--
ALTER TABLE `nhanvien-baohiem`
  ADD CONSTRAINT `bh_bh_FK` FOREIGN KEY (`Mã bảo hiểm`) REFERENCES `baohiem` (`Mã bảo hiểm`),
  ADD CONSTRAINT `nv_bh_FK` FOREIGN KEY (`Mã nhân viên`) REFERENCES `nhanvien` (`Mã nhân viên`),
  ADD CONSTRAINT `nvbh_bh_FK` FOREIGN KEY (`Mã bảo hiểm`) REFERENCES `baohiem` (`Mã bảo hiểm`);

--
-- Constraints for table `nhanvien-chucvu`
--
ALTER TABLE `nhanvien-chucvu`
  ADD CONSTRAINT `cv_cv_FK` FOREIGN KEY (`Mã chức vụ`) REFERENCES `chucvu` (`Mã chức vụ`),
  ADD CONSTRAINT `nv_cv_FK` FOREIGN KEY (`Mã nhân viên`) REFERENCES `nhanvien` (`Mã nhân viên`);

--
-- Constraints for table `quyen-chucnang`
--
ALTER TABLE `quyen-chucnang`
  ADD CONSTRAINT `qcn_cn_FK` FOREIGN KEY (`Mã chức năng`) REFERENCES `chucnang` (`Mã chức năng`),
  ADD CONSTRAINT `qcn_q_FK` FOREIGN KEY (`Mã quyền`) REFERENCES `quyen` (`Mã quyền`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `tk_q_FK` FOREIGN KEY (`Mã quyền`) REFERENCES `quyen` (`Mã quyền`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
