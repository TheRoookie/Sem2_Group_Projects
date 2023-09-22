-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2023 at 07:45 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e-clothing`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cust_id` int(11) NOT NULL,
  `ID` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `color` varchar(20) NOT NULL,
  `size` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  `gender` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cust_id`, `ID`, `p_name`, `color`, `size`, `price`, `gender`) VALUES
(1, 22, 'Kurti 2', 'red', 'Large', 900, 'female');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `c_id` int(11) NOT NULL,
  `c_name` varchar(30) NOT NULL,
  `c_pw` varchar(20) NOT NULL,
  `c_address` varchar(50) NOT NULL,
  `c_email` varchar(20) NOT NULL,
  `c_pno` bigint(10) NOT NULL,
  `extra` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`c_id`, `c_name`, `c_pw`, `c_address`, `c_email`, `c_pno`, `extra`) VALUES
(1, 'ronil', 'ronil@2003', '24,mitra milan soc', 'ronilkansoda73@gmail', 123456789, 0),
(2, 'omil', 'omil@2007', '24,mitra milan soc', 'omilkansoda03@gamil.', 123456789, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ID` int(11) NOT NULL,
  `p_id_m` int(11) NOT NULL,
  `p_id_wm` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `color` varchar(20) NOT NULL,
  `size` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `extra` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID`, `p_id_m`, `p_id_wm`, `p_name`, `color`, `size`, `price`, `gender`, `extra`) VALUES
(1, 1, 0, 'T-Shirt 1', 'red', 'Large', 400, 'male', 0),
(2, 1, 0, 'T-Shirt 1', 'red', 'Medium', 500, 'male', 0),
(3, 1, 0, 'T-Shirt 1', 'blue', 'Small', 300, 'male', 0),
(4, 1, 0, 'T-Shirt 2', 'red', 'Large', 500, 'male', 0),
(5, 1, 0, 'T-Shirt 2', 'green', 'Medium', 400, 'male', 0),
(6, 1, 0, 'T-shirt 2', 'blue', 'Small', 300, 'male', 0),
(7, 1, 0, 'T-shirt 3', 'yellow', 'Large', 700, 'male', 0),
(8, 1, 0, 'T-shirt 3', 'blue', 'Medium', 400, 'male', 0),
(9, 1, 0, 'T-shirt 3', 'blue', 'Small', 300, 'male', 0),
(10, 2, 0, 'Shirt 1', 'blue', 'Large', 600, 'male', 0),
(11, 2, 0, 'Shirt 1', 'red', 'Medium', 500, 'male', 0),
(12, 2, 0, 'Shirt 1', 'blue', 'Small', 300, 'male', 0),
(13, 2, 0, 'Shirt 2', 'red', 'Large', 500, 'male', 0),
(14, 2, 0, 'Shirt 2', 'green', 'Medium', 500, 'male', 0),
(15, 2, 0, 'Shirt 2', 'blue', 'Small', 300, 'male', 0),
(16, 2, 0, 'Shirt 3', 'blue', 'Large', 700, 'male', 0),
(17, 2, 0, 'Shirt 3', 'blue', 'Medium', 400, 'male', 0),
(18, 2, 0, 'Shirt 3', 'blue', 'Small', 300, 'male', 0),
(19, 0, 1, 'Kurti 1', 'red', 'Large', 800, 'female', 0),
(20, 0, 1, 'Kurti 1', 'red', 'Medium', 600, 'female', 0),
(21, 0, 1, 'Kurti 1', 'blue', 'Small', 500, 'female', 0),
(22, 0, 1, 'Kurti 2', 'red', 'Large', 900, 'female', 0),
(23, 0, 1, 'Kurti 2', 'green', 'Medium', 600, 'female', 0),
(24, 0, 1, 'Kurti 2', 'blue', 'Small', 400, 'female', 0),
(25, 0, 1, 'Kurti 3', 'yellow', 'Large', 700, 'female', 0),
(26, 0, 1, 'Kurti 3', 'blue', 'Medium', 500, 'female', 0),
(27, 0, 1, 'Kurti 3', 'blue', 'Small', 500, 'female', 0),
(28, 0, 2, 'Dress 1', 'blue', 'Large', 1000, 'female', 0),
(29, 0, 2, 'Dress 1', 'red', 'Medium', 800, 'female', 0),
(30, 0, 2, 'Dress 1', 'blue', 'Small', 600, 'female', 0),
(31, 0, 2, 'Dress 2', 'red', 'Large', 1200, 'female', 0),
(32, 0, 2, 'Dress 2', 'green', 'Medium', 700, 'female', 0),
(33, 0, 2, 'Dress 2', 'blue', 'Small', 600, 'female', 0),
(34, 0, 2, 'Dress 3', 'blue', 'Large', 1400, 'female', 0),
(35, 0, 2, 'Dress 3', 'blue', 'Medium', 700, 'female', 0),
(36, 0, 2, 'Dress 3', 'blue', 'Small', 600, 'female', 0);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `cust_id` int(11) NOT NULL,
  `ID` int(11) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `color` varchar(20) NOT NULL,
  `size` varchar(20) NOT NULL,
  `price` int(11) NOT NULL,
  `gender` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`cust_id`, `ID`, `p_name`, `color`, `size`, `price`, `gender`) VALUES
(1, 6, 'T-shirt 2', 'blue', 'Small', 300, 'male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`c_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `c_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
