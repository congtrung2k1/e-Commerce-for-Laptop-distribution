/*
 Navicat MySQL Data Transfer

 Source Server         : WAD
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : laptopecommerce

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 16/05/2023 13:24:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role_id` int NULL DEFAULT 1,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (1, 'admin', 'admin', '0987123456', 'admin@laptop.com', 'NY', 'USA', 0);
INSERT INTO `customers` VALUES (2, '123qwe', 'vu_dung', '0343435343', 'vuvandung@gmail.com', 'HCMC', 'VN', 1);

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`  (
  `installed_rank` int NOT NULL,
  `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `checksum` int NULL DEFAULT NULL,
  `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`) USING BTREE,
  INDEX `flyway_schema_history_s_idx`(`success` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES (1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2023-05-12 14:49:44', 0, 1);

-- ----------------------------
-- Table structure for order_details
-- ----------------------------
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details`  (
  `idx` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `price` double(8, 2) NOT NULL,
  `quantity` int NULL DEFAULT 1,
  PRIMARY KEY (`idx`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_details
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `shipment_id` int NOT NULL,
  `amount` double(8, 2) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `shipping_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `order_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `order_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'NAN',
  `discount` double(8, 2) NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `role_id` int NOT NULL,
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (0, 'view_make');
INSERT INTO `permission` VALUES (1, 'view_create');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` double(8, 2) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_date` date NOT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, 'Lenovo IdeaPad Slim 5 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Thin & Light Laptop (16GB/512GB SSD/Windows 11/Office 2021/Backlit/FPR/3months Game Pass/Graphite Grey/1.66Kg), 82FG01B5IN', 59418.00, 'rank 5,057 in Electronics (See Top 100 in Electronics) 46 in Traditional Laptops | 35.7 x 23.3 x 2 Centimeters | 4.2', '', '2022-01-11', 'Lenovo');
INSERT INTO `products` VALUES (2, 'Lenovo IdeaPad Slim 3 Intel Core i3-1115G4 11th Gen 15.6\" (39.62cm) FHD Laptop (8GB/256GB SSD/Win 11/Office 2021/2 Year Warranty/3 Month Game Pass/Platinum Grey/1.7Kg), 81X800LCIN', 33990.00, 'rank 1,250 in Electronics (See Top 100 in Electronics) 2 in Traditional Laptops | 36.2 x 25.3 x 2 Centimeters | 4.0', '', '2022-08-17', 'Lenovo');
INSERT INTO `products` VALUES (3, 'HP 15s, AMD Athlon Silver 3050U 8GB RAM/512GB SSD 15.6-inches/39.6 cm HD, Micro-Edge Display/Windows 11/AMD Radeon Graphics/Dual Speakers/MSO/Fast Charge/1.69 Kg, 15s-eq1559AU', 29212.00, 'rank 407 in Computers & Accessories (See Top 100 in Computers & Accessories) 3 in Traditional Laptops | 35.8 x 24.2 x 1.8 Centimeters | 3.7', '', '2022-07-13', 'HP');
INSERT INTO `products` VALUES (4, 'ASUS Vivobook 16X (2022), 16.0-inch (40.64 cms) FHD+ 16:10, AMD Ryzen 5 5600H, Thin and Laptop (8GB/512GB SSD/Integrated Graphics/Windows 11/Office 2021/Silver/1.80 kg), M1603QA-MB501WS', 48999.00, 'rank 4,253 in Electronics (See Top 100 in Electronics) 31 in Traditional Laptops | 24.8 x 35.8 x 2 Centimeters | 4.4', '', '2022-08-07', 'ASUS');
INSERT INTO `products` VALUES (5, 'Lenovo E41-55 AMD 14-inch HD 220 Nits Antiglare Thin and Light Laptop (AMD Athlon A3150U/4GB RAM/256GB HDD/DOS/Integrated AMD Graphics/Grey/1 Year Onsite/1.59 kg), 82FJ00AGIH', 18990.00, 'rank 1,441 in Computers & Accessories (See Top 100 in Computers & Accessories) 35 in Traditional Laptops | 23 x 33 x 2 Centimeters | 3.9', '', '2022-07-09', 'Lenovo');
INSERT INTO `products` VALUES (6, 'Lenovo Yoga 7 AMD Ryzen 7 5800U 14\"(35.56cm) FHD IPS 2-in-1 Convertible Touchscreen Laptop(16GB/512GB SSD/Win10/Office/Backlit/Digital Pen/3Yr Warranty/Slate Grey/Aluminium Surface/1.45Kg), 82N7000AIN', 96990.00, 'rank 54,030 in Computers & Accessories (See Top 100 in Computers & Accessories) 53 in 2 in 1 Laptops | 32 x 16.7 x 1.8 Centimeters | 4.1', '', '2021-09-15', 'Lenovo');
INSERT INTO `products` VALUES (7, 'Honor MagicBook 15, AMD Ryzen 5 5500U 15.6-inch (39.62 cm) FHD IPS Anti-Glare Thin and Light Laptop (8GB/256GB PCIe SSD/Windows 11/ Metal Body/Fingerprint Login/1.54Kg), Gray, BohrM-WDQ9CHNE', 39490.00, 'rank 2,805 in Electronics (See Top 100 in Electronics) 15 in Traditional Laptops | 17 x 230 x 357 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (8, 'Lenovo IdeaPad D330 Intel Celeron N4020 10.1\" (25cm) HD IPS Detachable 2-in-1 Laptop (4GB/128GB eMMC/Windows 10/1 Yr Warranty/Mineral Grey/1.1Kg), 82H0001YIN', 21797.00, 'rank 3,442 in Electronics (See Top 100 in Electronics) 20 in Traditional Laptops | 24.9 x 17.8 x 1 Centimeters | 3.8', '', '2022-02-18', 'Lenovo');
INSERT INTO `products` VALUES (9, 'Lenovo IdeaPad Slim 3 Intel Core i5 11th Gen 15.6 inches (39.62cm) FHD Thin & Light Business Laptop (8GB/512GB SSD/Windows 11/MS Office/Backlit Keyboard/Arctic Grey/1.65Kg), 82H802XTIN', 52829.00, 'rank 9,396 in Electronics (See Top 100 in Electronics) 97 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.1', '', '2022-07-29', 'Lenovo');
INSERT INTO `products` VALUES (10, 'ASUS ExpertBook B9 (B9450FA) Intel i5-10210U 14 inches Notebook (8GB, 512 PCIEx4, Windows 10 Home, 0.99 kg) B9450FA-BM0691T', 30000.00, 'rank 109,403 in Computers & Accessories (See Top 100 in Computers & Accessories) 4,240 in Traditional Laptops | 20.3 x 32 x 1.5 Centimeters | 4.5', '', '2020-09-13', 'ASUS');
INSERT INTO `products` VALUES (11, 'ASUS Vivobook 16X (2022), 16.0-inch (40.64 cms) FHD+ 16:10, AMD Ryzen 5 5600H, Thin and Laptop (8GB/512GB SSD/Integrated Graphics/Windows 11/Office 2021/Silver/1.80 kg), M1603QA-MB501WS', 48999.00, 'rank 4,253 in Electronics (See Top 100 in Electronics) 31 in Traditional Laptops | 24.8 x 35.8 x 2 Centimeters | 4.4', '', '2022-08-07', 'ASUS');
INSERT INTO `products` VALUES (12, 'Lenovo E41-55 AMD 14-inch HD 220 Nits Antiglare Thin and Light Laptop (AMD Athlon A3150U/4GB RAM/256GB HDD/DOS/Integrated AMD Graphics/Grey/1 Year Onsite/1.59 kg), 82FJ00AGIH', 18990.00, 'rank 1,441 in Computers & Accessories (See Top 100 in Computers & Accessories) 35 in Traditional Laptops | 23 x 33 x 2 Centimeters | 3.9', '', '2022-07-09', 'Lenovo');
INSERT INTO `products` VALUES (13, 'Lenovo (Renewed) Thinkpad T440-i5-8 GB-500 GB 14-inch Laptop (4th Gen Core i5/8GB/500GB HDD/Windows 7/Integrated Graphics), Black', 30000.00, 'rank 1,180 in Computers & Accessories (See Top 100 in Computers & Accessories) 6 in For the Cricket Frenzy 23 in Holi Renewed 26 in Traditional Laptops | 20 x 20 x 8 Centimeters | 2.4', '', '2019-05-20', 'Lenovo');
INSERT INTO `products` VALUES (14, 'Acer Extensa 15 Lightweight Laptop Intel Core i3 11th Gen Processor (4 GB RAM/256GB SSD/Windows 11 Home/Intel UHD Graphics /1.7Kg/Black) EX215-54 with 15.6\" (39.6 cms) Full HD Display', 30490.00, 'rank 23,889 in Electronics (See Top 100 in Electronics) 236 in Traditional Laptops | 23.8 x 36.3 x 2 Centimeters | 4.0', '', '2022-02-18', 'Acer');
INSERT INTO `products` VALUES (15, 'MSI Cyborg 15, Intel 12th Gen. i7-12650H, 40CM FHD 144Hz Gaming Laptop (16GB/512GB NVMe SSD/Windows 11 Home/Nvidia GeForce RTX4060, 8GB GDDR6/Translucent Black/1.98Kg), A12VF-069IN', 124837.00, 'rank 10,935 in Computers & Accessories (See Top 100 in Computers & Accessories) 359 in Traditional Laptops | 35.9 x 25 x 2.3 Centimeters | 4.3', '', '2023-02-20', 'MSI');
INSERT INTO `products` VALUES (16, 'HP 15s-Ryzen 3 3250U 8GB SDRAM/256GB SSD 15.6inch(39.6cm) HD, Micro-Edge Laptop/AMD Radeon Graphics/Dual Speakers/Win 11 Home/MS Office/Fast Charge/Jet Black/1.69Kg, 15s-ey1508AU', 33490.00, 'rank 1,621 in Computers & Accessories (See Top 100 in Computers & Accessories) 47 in Traditional Laptops | 35.9 x 24.2 x 1.8 Centimeters | 3.4', '', '2022-10-13', 'HP');
INSERT INTO `products` VALUES (17, 'Honor MagicBook 15, AMD Ryzen 5 5500U 15.6-inch (39.62 cm) FHD IPS Anti-Glare Thin and Light Laptop (8GB/256GB PCIe SSD/Windows 11/ Metal Body/Fingerprint Login/1.54Kg), Gray, BohrM-WDQ9CHNE', 39490.00, 'rank 2,805 in Electronics (See Top 100 in Electronics) 15 in Traditional Laptops | 17 x 230 x 357 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (18, 'Lenovo IdeaPad Slim 3 Intel Core i3 12th Gen 15.6\" (39.62cm) FHD Thin & Light Laptop (8GB/512GB SSD/Windows 11/Office 2021/2Yr Warranty/3months Game Pass/Arctic Grey/1.63Kg), 82RK006DIN', 45990.00, 'rank 13,459 in Electronics (See Top 100 in Electronics) 135 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.0', '', '2022-06-20', 'Lenovo');
INSERT INTO `products` VALUES (19, 'HP Victus Gaming Laptop 11th Gen Intel Core i5-11400H16.1 inch(40.9 cm) FHD IPS Gaming Laptop(16GB RAM/512GB SSD/NVIDIA GeForce GTX 1650 graphics/144Hz/Backlit KB/Wi11/MSO/B&O/Alexa),16-d0311TX', 65990.00, 'rank 8,474 in Electronics (See Top 100 in Electronics) 88 in Traditional Laptops | 37 x 26.2 x 2.4 Centimeters | 5.0', '', '2023-01-16', 'HP');
INSERT INTO `products` VALUES (20, 'Honor MagicBook 14, AMD Ryzen 5 5500U 14-inch (35.56 cm) FHD IPS Anti-Glare Thin and Light Laptop (16GB/512GB PCIe SSD/Windows 11/Fingerprint Login/Metal Body/Backlit KB/1.38Kg), Gray, NobelM-WFQ9AHNE', 30000.00, 'rank 2,532 in Electronics (See Top 100 in Electronics) 14 in Traditional Laptops | 16 x 215 x 323 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (21, 'Honor MagicBook 14, AMD Ryzen 5 5500U 14-inch (35.56 cm) FHD IPS Anti-Glare Thin and Light Laptop (16GB/512GB PCIe SSD/Windows 11/Fingerprint Login/Metal Body/Backlit KB/1.38Kg), Gray, NobelM-WFQ9AHNE', 30000.00, 'rank 2,532 in Electronics (See Top 100 in Electronics) 14 in Traditional Laptops | 16 x 215 x 323 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (22, 'Lenovo IdeaPad D330 Intel Celeron N4020 10.1\" (25cm) HD IPS Detachable 2-in-1 Laptop (4GB/128GB eMMC/Windows 10/1 Yr Warranty/Mineral Grey/1.1Kg), 82H0001YIN', 21797.00, 'rank 3,442 in Electronics (See Top 100 in Electronics) 20 in Traditional Laptops | 24.9 x 17.8 x 1 Centimeters | 3.8', '', '2022-02-18', 'Lenovo');
INSERT INTO `products` VALUES (23, 'Lenovo V14-IGL Laptop Intel Celeron N4020/4GB DDR4 RAM/256GB SSD/DOS/14.0 HD/ Intel UHD Graphics 600/1 Year Warranty', 19999.00, 'rank 4,093 in Computers & Accessories (See Top 100 in Computers & Accessories) 136 in Traditional Laptops | 24.1 x 32.7 x 2 Centimeters | 4.3', '', '2023-02-09', 'Lenovo');
INSERT INTO `products` VALUES (24, 'Lenovo IdeaPad Slim 3 Intel Core i3-1115G4 11th Gen 15.6\" (39.62cm) FHD Laptop (8GB/256GB SSD/Win 11/Office 2021/2 Year Warranty/3 Month Game Pass/Platinum Grey/1.7Kg), 81X800LCIN', 33990.00, 'rank 1,250 in Electronics (See Top 100 in Electronics) 2 in Traditional Laptops | 36.2 x 25.3 x 2 Centimeters | 4.0', '', '2022-08-17', 'Lenovo');
INSERT INTO `products` VALUES (25, 'ASUS VivoBook 15 (2021), 15.6-inch (39.62 cm) HD, Dual Core Intel Celeron N4020, Thin and Light Laptop (4GB RAM/256GB SSD/Integrated Graphics/Windows 11 Home/Transparent Silver/1.8 Kg), X515MA-BR011W', 25990.00, 'rank 980 in Computers & Accessories (See Top 100 in Computers & Accessories) 18 in Traditional Laptops | 23.5 x 36 x 2 Centimeters | 4.0', '', '2022-02-13', 'ASUS');
INSERT INTO `products` VALUES (26, 'Lenovo ThinkBook 15 AMD Ryzen 5 5500U 15.6\" (39.62cm) FHD 220 nits Antiglare Thin and Light Laptop (8GB/512GB SSD/Windows 11/MS Office/Mineral Grey/1.7 Kg), 21A4A09UIH', 45990.00, 'rank 22,087 in Electronics (See Top 100 in Electronics) 220 in Traditional Laptops | 23.5 x 35.7 x 1.9 Centimeters | 3.9', '', '2022-05-16', 'Lenovo');
INSERT INTO `products` VALUES (27, 'Honor MagicBook 15, AMD Ryzen 5 5500U 15.6-inch (39.62 cm) FHD IPS Anti-Glare Thin and Light Laptop (8GB/256GB PCIe SSD/Windows 11/ Metal Body/Fingerprint Login/1.54Kg), Gray, BohrM-WDQ9CHNE', 39490.00, 'rank 2,805 in Electronics (See Top 100 in Electronics) 15 in Traditional Laptops | 17 x 230 x 357 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (28, 'HP 15s-Ryzen 3 3250U 8GB SDRAM/256GB SSD 15.6inch(39.6cm) HD, Micro-Edge Laptop/AMD Radeon Graphics/Dual Speakers/Win 11 Home/MS Office/Fast Charge/Jet Black/1.69Kg, 15s-ey1508AU', 33490.00, 'rank 1,621 in Computers & Accessories (See Top 100 in Computers & Accessories) 47 in Traditional Laptops | 35.9 x 24.2 x 1.8 Centimeters | 3.4', '', '2022-10-13', 'HP');
INSERT INTO `products` VALUES (29, 'HP 255 G8 Laptop with AMD Athlon Silver 3050U APU/ 8GB Ram/ 1TB HDD/DOS/AMD Radeon Vega 8 Mobile Graphics/39.6 cm HD (1366 x 768), SVA, Anti-Glare WLED/Black/1 Year Onsite Warranty', 27990.00, 'rank 3,677 in Computers & Accessories (See Top 100 in Computers & Accessories) 116 in Traditional Laptops | 24.2 x 35.8 x 2 Centimeters | 3.4', '', '2022-12-15', 'HP');
INSERT INTO `products` VALUES (30, 'Lenovo IdeaPad Gaming 3 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Gaming Laptop (8GB/512GB SSD/4GB NVIDIA GTX 1650/120Hz/Win 11/Backlit/3months Game Pass/Shadow Black/2.25Kg), 82K10198IN', 55990.00, 'rank 15,053 in Electronics (See Top 100 in Electronics) 157 in Traditional Laptops | 36 x 25.2 x 2.4 Centimeters | 4.1', '', '2022-07-05', 'Lenovo');
INSERT INTO `products` VALUES (31, 'Lenovo E41-55 AMD 14-inch HD 220 Nits Antiglare Thin and Light Laptop (AMD Athlon A3150U/4GB RAM/256GB HDD/DOS/Integrated AMD Graphics/Grey/1 Year Onsite/1.59 kg), 82FJ00AGIH', 18990.00, 'rank 1,441 in Computers & Accessories (See Top 100 in Computers & Accessories) 35 in Traditional Laptops | 23 x 33 x 2 Centimeters | 3.9', '', '2022-07-09', 'Lenovo');
INSERT INTO `products` VALUES (32, 'Lenovo E41-55 AMD 14-inch HD 220 Nits Antiglare Thin and Light Laptop (AMD Athlon A3150U/4GB RAM/256GB HDD/DOS/Integrated AMD Graphics/Grey/1 Year Onsite/1.59 kg), 82FJ00AGIH', 18990.00, 'rank 1,441 in Computers & Accessories (See Top 100 in Computers & Accessories) 35 in Traditional Laptops | 23 x 33 x 2 Centimeters | 3.9', '', '2022-07-09', 'Lenovo');
INSERT INTO `products` VALUES (33, 'Lenovo IdeaPad Slim 5 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Thin & Light Laptop (16GB/512GB SSD/Windows 11/Office 2021/Backlit/FPR/3months Game Pass/Graphite Grey/1.66Kg), 82FG01B5IN', 59418.00, 'rank 5,057 in Electronics (See Top 100 in Electronics) 46 in Traditional Laptops | 35.7 x 23.3 x 2 Centimeters | 4.2', '', '2022-01-11', 'Lenovo');
INSERT INTO `products` VALUES (34, 'Lenovo V15 Intel Celeron N4020 15.6\" (39.62 cm) HD 220 nits Antiglare Thin and Light Laptop (4GB RAM/256GB SSD/DOS/Iron Grey/1.85 kg), 82C3A008IH', 19990.00, 'rank 12,214 in Electronics (See Top 100 in Electronics) 121 in Traditional Laptops | 25.2 x 36.2 x 2 Centimeters | 3.2', '', '2022-03-26', 'Lenovo');
INSERT INTO `products` VALUES (35, 'HP Victus Gaming Laptop 11th Gen Intel Core i5-11400H16.1 inch(40.9 cm) FHD IPS Gaming Laptop(16GB RAM/512GB SSD/NVIDIA GeForce GTX 1650 graphics/144Hz/Backlit KB/Wi11/MSO/B&O/Alexa),16-d0311TX', 65990.00, 'rank 8,474 in Electronics (See Top 100 in Electronics) 88 in Traditional Laptops | 37 x 26.2 x 2.4 Centimeters | 5.0', '', '2023-01-16', 'HP');
INSERT INTO `products` VALUES (36, 'Lenovo E41-55 AMD 14-inch HD 220 Nits Antiglare Thin and Light Laptop (AMD Athlon A3150U/4GB RAM/256GB HDD/DOS/Integrated AMD Graphics/Grey/1 Year Onsite/1.59 kg), 82FJ00AGIH', 18990.00, 'rank 1,441 in Computers & Accessories (See Top 100 in Computers & Accessories) 35 in Traditional Laptops | 23 x 33 x 2 Centimeters | 3.9', '', '2022-07-09', 'Lenovo');
INSERT INTO `products` VALUES (37, 'Lenovo Ideapad 3 AMD Ryzen 5 5500U 15.6\" (39.62cm) FHD Thin & Light Laptop (8GB/512GB SSD/Windows 11/Office 2021/Backlit Keyboard/2Yr Warranty/Arctic Grey/1.65Kg), 82KU017KIN', 45126.00, 'rank 2,125 in Electronics (See Top 100 in Electronics) 9 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.2', '', '2021-11-26', 'Lenovo');
INSERT INTO `products` VALUES (38, 'MSI Cyborg 15, Intel 12th Gen. i7-12650H, 40CM FHD 144Hz Gaming Laptop (16GB/512GB NVMe SSD/Windows 11 Home/Nvidia GeForce RTX4060, 8GB GDDR6/Translucent Black/1.98Kg), A12VF-069IN', 124837.00, 'rank 10,935 in Computers & Accessories (See Top 100 in Computers & Accessories) 359 in Traditional Laptops | 35.9 x 25 x 2.3 Centimeters | 4.3', '', '2023-02-20', 'MSI');
INSERT INTO `products` VALUES (39, 'HP 15s, AMD Athlon Silver 3050U 8GB RAM/512GB SSD 15.6-inches/39.6 cm HD, Micro-Edge Display/Windows 11/AMD Radeon Graphics/Dual Speakers/MSO/Fast Charge/1.69 Kg, 15s-eq1559AU', 29212.00, 'rank 407 in Computers & Accessories (See Top 100 in Computers & Accessories) 3 in Traditional Laptops | 35.8 x 24.2 x 1.8 Centimeters | 3.7', '', '2022-07-13', 'HP');
INSERT INTO `products` VALUES (40, 'Apple 2020 MacBook Air Laptop M1 chip, 13.3-inch/33.74 cm Retina Display, 8GB RAM, 256GB SSD Storage, Backlit Keyboard, FaceTime HD Camera, Touch ID. Works with iPhone/iPad; Silver', 85990.00, 'rank unknown | 25.2 x 36.2 x 2 Centimeters | 4.3', '', '2022-07-05', 'Apple');
INSERT INTO `products` VALUES (41, 'Lenovo IdeaPad Gaming 3 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Gaming Laptop (8GB/512GB SSD/4GB NVIDIA GTX 1650/120Hz/Win 11/Backlit/3months Game Pass/Shadow Black/2.25Kg), 82K10198IN', 55990.00, 'rank 15,053 in Electronics (See Top 100 in Electronics) 157 in Traditional Laptops | 36 x 25.2 x 2.4 Centimeters | 4.1', '', '2022-07-05', 'Lenovo');
INSERT INTO `products` VALUES (42, 'HP 245 G8 Laptop with AMD Athlon Silver 3050U APU/ 8GB Ram/ 1TB HDD/ Windows 11/AMD Radeon Vega 8 Mobile Graphics/35.6 cm HD (1366 x 768), SVA , Anti-Glare WLED/Black/1 Year Onsite Warranty', 25999.00, 'rank 1,524 in Computers & Accessories (See Top 100 in Computers & Accessories) 40 in Traditional Laptops | 25.2 x 36.2 x 2 Centimeters | 3.1', '', '2023-01-07', 'HP');
INSERT INTO `products` VALUES (43, 'Lenovo IdeaPad Gaming 3 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Gaming Laptop (8GB/512GB SSD/4GB NVIDIA GTX 1650/120Hz/Win 11/Backlit/3months Game Pass/Shadow Black/2.25Kg), 82K10198IN', 55990.00, 'rank 15,053 in Electronics (See Top 100 in Electronics) 157 in Traditional Laptops | 36 x 25.2 x 2.4 Centimeters | 4.1', '', '2022-07-05', 'Lenovo');
INSERT INTO `products` VALUES (44, 'HP 245 G8 Laptop with AMD Athlon Silver 3050U APU/ 8GB Ram/ 1TB HDD/ Windows 11/AMD Radeon Vega 8 Mobile Graphics/35.6 cm HD (1366 x 768), SVA , Anti-Glare WLED/Black/1 Year Onsite Warranty', 25999.00, 'rank 1,524 in Computers & Accessories (See Top 100 in Computers & Accessories) 40 in Traditional Laptops | 25.2 x 36.2 x 2 Centimeters | 3.1', '', '2023-01-07', 'HP');
INSERT INTO `products` VALUES (45, 'Lenovo IdeaPad Slim 5 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Thin & Light Laptop (16GB/512GB SSD/Windows 11/Office 2021/Backlit/FPR/3months Game Pass/Graphite Grey/1.66Kg), 82FG01B5IN', 59418.00, 'rank 5,057 in Electronics (See Top 100 in Electronics) 46 in Traditional Laptops | 35.7 x 23.3 x 2 Centimeters | 4.2', '', '2022-01-11', 'Lenovo');
INSERT INTO `products` VALUES (46, 'Lenovo IdeaPad Slim 3 Intel Core i3 12th Gen 15.6\" (39.62cm) FHD Thin & Light Laptop (8GB/512GB SSD/Windows 11/Office 2021/2Yr Warranty/3months Game Pass/Arctic Grey/1.63Kg), 82RK006DIN', 45990.00, 'rank 13,459 in Electronics (See Top 100 in Electronics) 135 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.0', '', '2022-06-20', 'Lenovo');
INSERT INTO `products` VALUES (47, 'Acer Extensa 15 Lightweight Laptop Intel Core i3 11th Gen Processor (4 GB RAM/256GB SSD/Windows 11 Home/Intel UHD Graphics /1.7Kg/Black) EX215-54 with 15.6\" (39.6 cms) Full HD Display', 30490.00, 'rank 23,889 in Electronics (See Top 100 in Electronics) 236 in Traditional Laptops | 23.8 x 36.3 x 2 Centimeters | 4.0', '', '2022-02-18', 'Acer');
INSERT INTO `products` VALUES (48, 'Lenovo ThinkBook 15 AMD Ryzen 5 5500U 15.6\" (39.62cm) FHD 220 nits Antiglare Thin and Light Laptop (8GB/512GB SSD/Windows 11/MS Office/Mineral Grey/1.7 Kg), 21A4A09UIH', 45990.00, 'rank 22,087 in Electronics (See Top 100 in Electronics) 220 in Traditional Laptops | 23.5 x 35.7 x 1.9 Centimeters | 3.9', '', '2022-05-16', 'Lenovo');
INSERT INTO `products` VALUES (49, 'Lenovo IdeaPad Slim 3 Intel Core i5 11th Gen 15.6 inches (39.62cm) FHD Thin & Light Business Laptop (8GB/512GB SSD/Windows 11/MS Office/Backlit Keyboard/Arctic Grey/1.65Kg), 82H802XTIN', 52829.00, 'rank 9,396 in Electronics (See Top 100 in Electronics) 97 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.1', '', '2022-07-29', 'Lenovo');
INSERT INTO `products` VALUES (50, 'Lenovo IdeaPad Slim 3 Intel Core i5 11th Gen 15.6 inches (39.62cm) FHD Thin & Light Business Laptop (8GB/512GB SSD/Windows 11/MS Office/Backlit Keyboard/Arctic Grey/1.65Kg), 82H802XTIN', 52829.00, 'rank 9,396 in Electronics (See Top 100 in Electronics) 97 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.1', '', '2022-07-29', 'Lenovo');
INSERT INTO `products` VALUES (51, 'Apple 2020 MacBook Air Laptop M1 chip, 13.3-inch/33.74 cm Retina Display, 8GB RAM, 256GB SSD Storage, Backlit Keyboard, FaceTime HD Camera, Touch ID. Works with iPhone/iPad; Silver', 85990.00, 'rank unknown | 25.2 x 36.2 x 2 Centimeters | 4.3', '', '2022-07-05', 'Apple');
INSERT INTO `products` VALUES (52, 'Lenovo Yoga 7 AMD Ryzen 7 5800U 14\"(35.56cm) FHD IPS 2-in-1 Convertible Touchscreen Laptop(16GB/512GB SSD/Win10/Office/Backlit/Digital Pen/3Yr Warranty/Slate Grey/Aluminium Surface/1.45Kg), 82N7000AIN', 96990.00, 'rank 54,030 in Computers & Accessories (See Top 100 in Computers & Accessories) 53 in 2 in 1 Laptops | 32 x 16.7 x 1.8 Centimeters | 4.1', '', '2021-09-15', 'Lenovo');
INSERT INTO `products` VALUES (53, 'HP 255 G8 Laptop with AMD Athlon Silver 3050U APU/ 8GB Ram/ 1TB HDD/DOS/AMD Radeon Vega 8 Mobile Graphics/39.6 cm HD (1366 x 768), SVA, Anti-Glare WLED/Black/1 Year Onsite Warranty', 27990.00, 'rank 3,677 in Computers & Accessories (See Top 100 in Computers & Accessories) 116 in Traditional Laptops | 24.2 x 35.8 x 2 Centimeters | 3.4', '', '2022-12-15', 'HP');
INSERT INTO `products` VALUES (54, 'Honor MagicBook 15, AMD Ryzen 5 5500U 15.6-inch (39.62 cm) FHD IPS Anti-Glare Thin and Light Laptop (8GB/256GB PCIe SSD/Windows 11/ Metal Body/Fingerprint Login/1.54Kg), Gray, BohrM-WDQ9CHNE', 39490.00, 'rank 2,805 in Electronics (See Top 100 in Electronics) 15 in Traditional Laptops | 17 x 230 x 357 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (55, 'Lenovo IdeaPad Gaming 3 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Gaming Laptop (8GB/512GB SSD/4GB NVIDIA GTX 1650/120Hz/Win 11/Backlit/3months Game Pass/Shadow Black/2.25Kg), 82K10198IN', 55990.00, 'rank 15,053 in Electronics (See Top 100 in Electronics) 157 in Traditional Laptops | 36 x 25.2 x 2.4 Centimeters | 4.1', '', '2022-07-05', 'Lenovo');
INSERT INTO `products` VALUES (56, 'ASUS VivoBook 15 (2021), 15.6-inch (39.62 cm) HD, Dual Core Intel Celeron N4020, Thin and Light Laptop (4GB RAM/256GB SSD/Integrated Graphics/Windows 11 Home/Transparent Silver/1.8 Kg), X515MA-BR011W', 25990.00, 'rank 980 in Computers & Accessories (See Top 100 in Computers & Accessories) 18 in Traditional Laptops | 23.5 x 36 x 2 Centimeters | 4.0', '', '2022-02-13', 'ASUS');
INSERT INTO `products` VALUES (57, 'Lenovo IdeaPad Slim 3 Intel Core i5 11th Gen 15.6 inches (39.62cm) FHD Thin & Light Business Laptop (8GB/512GB SSD/Windows 11/MS Office/Backlit Keyboard/Arctic Grey/1.65Kg), 82H802XTIN', 52829.00, 'rank 9,396 in Electronics (See Top 100 in Electronics) 97 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.1', '', '2022-07-29', 'Lenovo');
INSERT INTO `products` VALUES (58, 'Lenovo V14-IGL Laptop Intel Celeron N4020/4GB DDR4 RAM/256GB SSD/DOS/14.0 HD/ Intel UHD Graphics 600/1 Year Warranty', 19999.00, 'rank 4,093 in Computers & Accessories (See Top 100 in Computers & Accessories) 136 in Traditional Laptops | 24.1 x 32.7 x 2 Centimeters | 4.3', '', '2023-02-09', 'Lenovo');
INSERT INTO `products` VALUES (59, 'Lenovo Ideapad 3 AMD Ryzen 5 5500U 15.6\" (39.62cm) FHD Thin & Light Laptop (8GB/512GB SSD/Windows 11/Office 2021/Backlit Keyboard/2Yr Warranty/Arctic Grey/1.65Kg), 82KU017KIN', 45126.00, 'rank 2,125 in Electronics (See Top 100 in Electronics) 9 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.2', '', '2021-11-26', 'Lenovo');
INSERT INTO `products` VALUES (60, 'Lenovo IdeaPad Flex 5 AMD Ryzen 5 5500U 14\" (35.56cm) WUXGA IPS 2-in-1 Convertible Touchscreen Laptop (16GB/512GB SDD/Win11/Office 2021/Backlit/FPR/3months Game Pass/Storm Grey/1.55Kg), 82R9008GIN', 62001.00, 'rank 3,516 in Computers & Accessories (See Top 100 in Computers & Accessories) 108 in Traditional Laptops | 31.3 x 22.5 x 1.8 Centimeters | 3.9', '', '2022-07-22', 'Lenovo');
INSERT INTO `products` VALUES (61, 'Lenovo V15 Intel Celeron N4020 15.6\" (39.62 cm) HD 220 nits Antiglare Thin and Light Laptop (4GB RAM/256GB SSD/DOS/Iron Grey/1.85 kg), 82C3A008IH', 19990.00, 'rank 12,214 in Electronics (See Top 100 in Electronics) 121 in Traditional Laptops | 25.2 x 36.2 x 2 Centimeters | 3.2', '', '2022-03-26', 'Lenovo');
INSERT INTO `products` VALUES (62, 'Honor MagicBook 15, AMD Ryzen 5 5500U 15.6-inch (39.62 cm) FHD IPS Anti-Glare Thin and Light Laptop (8GB/256GB PCIe SSD/Windows 11/ Metal Body/Fingerprint Login/1.54Kg), Gray, BohrM-WDQ9CHNE', 39490.00, 'rank 2,805 in Electronics (See Top 100 in Electronics) 15 in Traditional Laptops | 17 x 230 x 357 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (63, 'Lenovo IdeaPad Slim 5 Intel Core i5 11th Gen 15.6\" (39.62cm) FHD IPS Thin & Light Laptop (16GB/512GB SSD/Windows 11/Office 2021/Backlit/FPR/3months Game Pass/Graphite Grey/1.66Kg), 82FG01B5IN', 59418.00, 'rank 5,057 in Electronics (See Top 100 in Electronics) 46 in Traditional Laptops | 35.7 x 23.3 x 2 Centimeters | 4.2', '', '2022-01-11', 'Lenovo');
INSERT INTO `products` VALUES (64, 'Lenovo (Renewed) Thinkpad T440-i5-8 GB-500 GB 14-inch Laptop (4th Gen Core i5/8GB/500GB HDD/Windows 7/Integrated Graphics), Black', 30000.00, 'rank 1,180 in Computers & Accessories (See Top 100 in Computers & Accessories) 6 in For the Cricket Frenzy 23 in Holi Renewed 26 in Traditional Laptops | 20 x 20 x 8 Centimeters | 2.4', '', '2019-05-20', 'Lenovo');
INSERT INTO `products` VALUES (65, 'Honor MagicBook 14, AMD Ryzen 5 5500U 14-inch (35.56 cm) FHD IPS Anti-Glare Thin and Light Laptop (8GB/512GB PCIe SSD/Windows 11/Fingerprint Login/Metal Body /Backlit KB/1.38Kg), Gray, NobelM-WDQ9BHNE', 30000.00, 'rank 4,143 in Electronics (See Top 100 in Electronics) 30 in Traditional Laptops | 16 x 215 x 323 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (66, '(Renewed) DELL LATITUDE 7390 (Core i5 8th GEN /8GB RAM/ 256GB SSD/ WEBCAM/ 13.3\'\' TOUCH/ WIN-10 PRO) 1 Year Warranty', 30000.00, 'rank 1,580 in Computers & Accessories (See Top 100 in Computers & Accessories) 31 in Holi Renewed 42 in Traditional Laptops | 25.2 x 36.2 x 2 Centimeters | 3.0', '', '2023-02-14', '(Renewed)');
INSERT INTO `products` VALUES (67, '(Renewed) DELL LATITUDE 7390 (Core i5 8th GEN /8GB RAM/ 256GB SSD/ WEBCAM/ 13.3\'\' TOUCH/ WIN-10 PRO) 1 Year Warranty', 30000.00, 'rank 1,580 in Computers & Accessories (See Top 100 in Computers & Accessories) 31 in Holi Renewed 42 in Traditional Laptops | 25.2 x 36.2 x 2 Centimeters | 3.0', '', '2023-02-14', '(Renewed)');
INSERT INTO `products` VALUES (68, 'HP 14s, 11th Gen Intel Core i3-1115G4, 8GB RAM/256GB SSD 14-inch(35.6 cm) Micro-Edge, Anti-Glare, FHD Laptop/Alexa Built-in/Win 11/Intel UHD Graphics/Dual Speakers/ MSO 2021/1.41 Kg, 14s-dy2507TU', 35490.00, 'rank 688 in Electronics (See Top 100 in Electronics) 1 in Traditional Laptops | 32.5 x 21.6 x 1.7 Centimeters | 4.0', '', '2022-07-08', 'HP');
INSERT INTO `products` VALUES (69, 'Lenovo Ideapad 3 AMD Ryzen 5 5500U 15.6\" (39.62cm) FHD Thin & Light Laptop (8GB/512GB SSD/Windows 11/Office 2021/Backlit Keyboard/2Yr Warranty/Arctic Grey/1.65Kg), 82KU017KIN', 45126.00, 'rank 2,125 in Electronics (See Top 100 in Electronics) 9 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.2', '', '2021-11-26', 'Lenovo');
INSERT INTO `products` VALUES (70, 'Honor MagicBook 14, AMD Ryzen 5 5500U 14-inch (35.56 cm) FHD IPS Anti-Glare Thin and Light Laptop (8GB/512GB PCIe SSD/Windows 11/Fingerprint Login/Metal Body /Backlit KB/1.38Kg), Gray, NobelM-WDQ9BHNE', 30000.00, 'rank 4,143 in Electronics (See Top 100 in Electronics) 30 in Traditional Laptops | 16 x 215 x 323 Millimeters | 4.3', '', '2022-09-21', 'Honor');
INSERT INTO `products` VALUES (71, 'Lenovo IdeaPad Slim 1 Intel Celeron N4020 4th Gen 11.6\'\' (29.46cm) HD Thin & Light Laptop (4GB/256GB SSD/Windows 11/Office 2021/3months Game Pass/Platinum Grey/1.2Kg), 81VT009UIN', 24990.00, 'rank 12,057 in Computers & Accessories (See Top 100 in Computers & Accessories) 399 in Traditional Laptops | 28.8 x 20 x 1.8 Centimeters | 3.8', '', '2022-03-10', 'Lenovo');
INSERT INTO `products` VALUES (72, 'HP 14s, 11th Gen Intel Core i3-1115G4, 8GB RAM/256GB SSD 14-inch(35.6 cm) Micro-Edge, Anti-Glare, FHD Laptop/Alexa Built-in/Win 11/Intel UHD Graphics/Dual Speakers/ MSO 2021/1.41 Kg, 14s-dy2507TU', 35490.00, 'rank 688 in Electronics (See Top 100 in Electronics) 1 in Traditional Laptops | 32.5 x 21.6 x 1.7 Centimeters | 4.0', '', '2022-07-08', 'HP');
INSERT INTO `products` VALUES (73, 'Lenovo Ideapad 3 AMD Ryzen 5 5500U 15.6\" (39.62cm) FHD Thin & Light Laptop (8GB/512GB SSD/Windows 11/Office 2021/Backlit Keyboard/2Yr Warranty/Arctic Grey/1.65Kg), 82KU017KIN', 45126.00, 'rank 2,125 in Electronics (See Top 100 in Electronics) 9 in Traditional Laptops | 35.9 x 23.7 x 2 Centimeters | 4.2', '', '2021-11-26', 'Lenovo');
INSERT INTO `products` VALUES (74, 'Lenovo IdeaPad Slim 1 Intel Celeron N4020 4th Gen 11.6\'\' (29.46cm) HD Thin & Light Laptop (4GB/256GB SSD/Windows 11/Office 2021/3months Game Pass/Platinum Grey/1.2Kg), 81VT009UIN', 24990.00, 'rank 12,057 in Computers & Accessories (See Top 100 in Computers & Accessories) 399 in Traditional Laptops | 28.8 x 20 x 1.8 Centimeters | 3.8', '', '2022-03-10', 'Lenovo');

-- ----------------------------
-- Table structure for shipments
-- ----------------------------
DROP TABLE IF EXISTS `shipments`;
CREATE TABLE `shipments`  (
  `shipment_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `shipment_date` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `shipment_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`shipment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shipments
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
