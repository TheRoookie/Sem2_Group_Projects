-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2023 at 09:30 PM
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
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `db_bill_id` int(11) NOT NULL,
  `db_bill_date` date NOT NULL,
  `db_bill_amount` double(10,2) NOT NULL,
  `db_bill_no_of_orders` int(11) NOT NULL,
  `db_bill_cust_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `db_cart_cust_id` int(11) NOT NULL,
  `db_cart_product_id` int(11) NOT NULL,
  `db_cart_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`db_cart_cust_id`, `db_cart_product_id`, `db_cart_quantity`) VALUES
(7, 1, 2),
(7, 2, 2),
(7, 3, 2),
(7, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `db_cust_id` int(11) NOT NULL,
  `db_cust_user_name` varchar(20) NOT NULL,
  `db_cust_name` varchar(30) NOT NULL,
  `db_cust_pass` varchar(20) NOT NULL,
  `db_cust_address` varchar(50) NOT NULL,
  `db_cust_email` varchar(20) NOT NULL,
  `db_cust_phone` bigint(10) NOT NULL,
  `db_cust_gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`db_cust_id`, `db_cust_user_name`, `db_cust_name`, `db_cust_pass`, `db_cust_address`, `db_cust_email`, `db_cust_phone`, `db_cust_gender`) VALUES
(1, 'user1', 'user', 'user', 'user', 'user', 1111111111, NULL),
(7, 'test', 'test', 'test@test', '', 'test', 1234, 'male');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `db_order_id` int(11) NOT NULL,
  `db_order_cust_id` int(11) NOT NULL,
  `db_order_prod_id` int(11) NOT NULL,
  `db_order_quality` int(11) NOT NULL,
  `db_order_bill_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `db_prod_id` int(11) NOT NULL,
  `db_prod_section` varchar(50) NOT NULL,
  `db_prod_name` varchar(50) NOT NULL,
  `db_prod_color` varchar(20) NOT NULL,
  `db_prod_size` varchar(10) NOT NULL,
  `db_prod_price` double DEFAULT NULL,
  `db_prod_gender` varchar(20) NOT NULL,
  `db_prod_vendor_id` int(11) NOT NULL,
  `db_prod_stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`db_prod_id`, `db_prod_section`, `db_prod_name`, `db_prod_color`, `db_prod_size`, `db_prod_price`, `db_prod_gender`, `db_prod_vendor_id`, `db_prod_stock`) VALUES
(1, 't-shirt', 'T-Shirt 1', 'red', 'L', 400, 'male', 0, 0),
(2, 't-shirt', 'T-Shirt 1', 'red', 'M', 500, 'male', 0, 0),
(3, 't-shirt', 'T-Shirt 1', 'blue', 'S', 300, 'male', 0, 0),
(4, 't-shirt', 'T-Shirt 2', 'red', 'L', 500, 'male', 0, 0),
(5, 't-shirt', 'T-Shirt 2', 'green', 'M', 400, 'male', 0, 0),
(6, 't-shirt', 'T-shirt 2', 'blue', 'S', 300, 'male', 0, 0),
(7, 't-shirt', 'T-shirt 3', 'yellow', 'L', 700, 'male', 0, 0),
(8, 't-shirt', 'T-shirt 3', 'blue', 'M', 400, 'male', 0, 0),
(9, 't-shirt', 'T-shirt 3', 'blue', 'S', 300, 'male', 0, 0),
(10, 'shirt', 'Shirt 1', 'blue', 'L', 600, 'male', 0, 0),
(11, 'shirt', 'Shirt 1', 'red', 'M', 500, 'male', 0, 0),
(12, 'shirt', 'Shirt 1', 'blue', 'S', 300, 'male', 0, 0),
(13, 'shirt', 'Shirt 2', 'red', 'L', 500, 'male', 0, 0),
(14, 'shirt', 'Shirt 2', 'green', 'M', 500, 'male', 0, 0),
(15, 'shirt', 'Shirt 2', 'blue', 'S', 300, 'male', 0, 0),
(16, 'shirt', 'Shirt 3', 'blue', 'L', 700, 'male', 0, 0),
(17, 'shirt', 'Shirt 3', 'blue', 'M', 400, 'male', 0, 0),
(18, 'shirt', 'Shirt 3', 'blue', 'S', 300, 'male', 0, 0),
(19, 'kurti', 'Kurti 1', 'red', 'L', 800, 'female', 0, 0),
(20, 'kurti', 'Kurti 1', 'red', 'M', 600, 'female', 0, 0),
(21, 'kurti', 'Kurti 1', 'blue', 'S', 500, 'female', 0, 0),
(22, 'kurti', 'Kurti 2', 'red', 'L', 900, 'female', 0, 0),
(23, 'kurti', 'Kurti 2', 'green', 'M', 600, 'female', 0, 0),
(24, 'kurti', 'Kurti 2', 'blue', 'S', 400, 'female', 0, 0),
(25, 'kurti', 'Kurti 3', 'yellow', 'L', 700, 'female', 0, 0),
(26, 'kurti', 'Kurti 3', 'blue', 'M', 500, 'female', 0, 0),
(27, 'kurti', 'Kurti 3', 'blue', 'S', 500, 'female', 0, 0),
(28, 'dress', 'Dress 1', 'blue', 'L', 1000, 'female', 0, 0),
(29, 'dress', 'Dress 1', 'red', 'M', 800, 'female', 0, 0),
(30, 'dress', 'Dress 1', 'blue', 'S', 600, 'female', 0, 0),
(31, 'dress', 'Dress 2', 'red', 'L', 1200, 'female', 0, 0),
(32, 'dress', 'Dress 2', 'green', 'M', 700, 'female', 0, 0),
(33, 'dress', 'Dress 2', 'blue', 'S', 600, 'female', 0, 0),
(34, 'dress', 'Dress 3', 'blue', 'L', 1400, 'female', 0, 0),
(35, 'dress', 'Dress 3', 'blue', 'M', 700, 'female', 0, 0),
(36, 'dress', 'Dress 3', 'blue', 'S', 600, 'female', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE `vendor` (
  `db_ven_id` int(11) NOT NULL,
  `db_ven_name` varchar(50) NOT NULL,
  `db_ven_pass` varchar(50) NOT NULL,
  `db_ven_city` varchar(20) NOT NULL,
  `db_ven_phone` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `db_wish_cust_id` int(11) NOT NULL,
  `db_wish_product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`db_wish_cust_id`, `db_wish_product_id`) VALUES
(7, 1),
(7, 2),
(7, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`db_bill_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`db_cust_id`) USING BTREE,
  ADD UNIQUE KEY `unique_user` (`db_cust_user_name`),
  ADD UNIQUE KEY `unique_phone` (`db_cust_phone`),
  ADD UNIQUE KEY `unique_email` (`db_cust_email`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`db_order_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`db_prod_id`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`db_ven_id`),
  ADD UNIQUE KEY `unique_vendor_phone` (`db_ven_phone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `db_bill_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `db_cust_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `db_order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `db_prod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `db_ven_id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
