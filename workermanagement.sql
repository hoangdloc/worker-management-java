-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 31, 2021 lúc 01:34 PM
-- Phiên bản máy phục vụ: 10.4.14-MariaDB
-- Phiên bản PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `workermanagement`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblcity`
--

CREATE TABLE `tblcity` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tblcity`
--

INSERT INTO `tblcity` (`id`, `name`) VALUES
(1, 'Ha Noi'),
(2, 'Ho Chi Minh'),
(3, 'Hai Phong'),
(4, 'Da Nang'),
(5, 'Hue'),
(6, 'Thai Binh'),
(7, 'Lao Cai'),
(8, 'Dien Bien'),
(9, 'Nghe An'),
(10, 'Thanh Hoa');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblworker`
--

CREATE TABLE `tblworker` (
  `id` int(11) NOT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dob` int(11) NOT NULL,
  `tblCityid` int(11) NOT NULL,
  `gender` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `coefficientSalary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tblworker`
--

INSERT INTO `tblworker` (`id`, `fullname`, `dob`, `tblCityid`, `gender`, `coefficientSalary`) VALUES
(1, 'Hoang Dinh Loc', 2000, 1, 'Nam', 5),
(2, 'Vu Duc Duy', 2000, 1, 'Nam', 1),
(3, 'Nguyen Xuan Loc', 1999, 6, 'Nam', 2),
(4, 'Nguyen Thanh Binh', 2000, 1, 'Nam', 4),
(5, 'Le Yen Nhi', 2001, 9, 'Nu', 2.5),
(6, 'Nguyen Thu Huyen', 2001, 3, 'Nu', 1.5),
(7, 'Luyen Thu Trang', 2000, 2, 'Nu', 4.5),
(8, 'Dinh Trang Nhung', 1999, 3, 'Nu', 1),
(9, 'Luu Gia Bao', 2000, 6, 'Nam', 3.5),
(10, 'Hach Lien Tu Nguyet', 2000, 2, 'Nu', 3),
(11, 'Hoang Dinh Phuc', 2003, 1, 'Nam', 4.5),
(12, 'Dang Thi Thu Hien', 1999, 8, 'Nu', 1),
(13, 'Dinh Trong Hieu', 2000, 3, 'Nam', 4),
(14, 'Nguyen Phuong Ly', 1992, 2, 'Nu', 5),
(15, 'Ngo Ba Kha', 2003, 4, 'Nam', 1),
(16, 'Vo Ho Nguyen', 2000, 7, 'Nam', 1.5),
(17, 'Robert Lewandowski', 1985, 1, 'Nam', 5),
(18, 'Nguyen Phuong Thao', 2000, 1, 'Nu', 1.5),
(19, 'Quan Truong Huy', 2000, 10, 'Nam', 2),
(20, 'Hoang Duc Duy', 2000, 1, 'Nam', 0.5),
(21, 'Nguyen Thu Trang', 2003, 8, 'Nu', 1),
(22, 'Arjen Robben', 1980, 4, 'Nam', 5);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tblcity`
--
ALTER TABLE `tblcity`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tblworker`
--
ALTER TABLE `tblworker`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tblCityid` (`tblCityid`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tblcity`
--
ALTER TABLE `tblcity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `tblworker`
--
ALTER TABLE `tblworker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tblworker`
--
ALTER TABLE `tblworker`
  ADD CONSTRAINT `tblworker_ibfk_1` FOREIGN KEY (`tblCityid`) REFERENCES `tblcity` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
