-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 03, 2023 lúc 02:49 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `javaproject`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaSP` int(5) NOT NULL,
  `MaHD` int(5) NOT NULL,
  `SoLuong` int(5) DEFAULT NULL,
  `GiaChuaGiam` double DEFAULT NULL,
  `GiaDaGiam` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaSP`, `MaHD`, `SoLuong`, `GiaChuaGiam`, `GiaDaGiam`) VALUES
(1, 1, 1, 16290000, 15475000),
(1, 2, 3, 16290000, 0),
(2, 2, 1, 16390000, 0),
(2, 5, 3, 16390000, 0),
(3, 3, 4, 16990000, 0),
(4, 1, 2, 15590000, 0),
(5, 4, 1, 15590000, 0),
(6, 8, 2, 16290000, 0),
(6, 9, 1, 16290000, 0),
(7, 1, 2, 16290000, 0),
(8, 8, 1, 16990000, 0),
(8, 9, 2, 16990000, 0),
(9, 10, 4, 19490000, 0),
(10, 5, 2, 26290000, 0),
(12, 9, 2, 11690000, 0),
(13, 4, 1, 17490000, 17140200),
(13, 6, 2, 17490000, 17140200),
(14, 9, 3, 17490000, 0),
(15, 8, 3, 18790000, 0),
(17, 6, 1, 24790000, 0),
(19, 7, 1, 26290000, 26190000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaSP` int(5) NOT NULL,
  `MaPN` int(5) NOT NULL,
  `SL` int(5) DEFAULT NULL,
  `DONGIA` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaSP`, `MaPN`, `SL`, `DONGIA`) VALUES
(1, 1, 2, 15990000),
(2, 9, 4, 15990000),
(3, 1, 3, 16090000),
(4, 3, 2, 14590000),
(4, 5, 12, 14590000),
(5, 1, 1, 24590000),
(5, 2, 1, 24590000),
(5, 10, 23, 24090000),
(6, 6, 3, 16190000),
(7, 4, 3, 13290000),
(8, 6, 4, 15990000),
(10, 5, 4, 26000000),
(11, 5, 10, 32390000),
(12, 2, 2, 11390000),
(13, 7, 5, 16490000),
(14, 5, 13, 15890000),
(14, 9, 30, 16220000),
(15, 8, 1, 18200000),
(16, 9, 12, 29490000),
(17, 8, 2, 21120000),
(18, 9, 20, 24250000),
(19, 8, 15, 23590000),
(20, 9, 13, 21790000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucvu`
--

CREATE TABLE `chucvu` (
  `MaCV` int(5) NOT NULL,
  `TenCV` varchar(100) DEFAULT NULL,
  `HoaDon` varchar(100) DEFAULT NULL,
  `KhachHang` varchar(100) DEFAULT NULL,
  `NhanVien` varchar(100) DEFAULT NULL,
  `KhuyenMai` varchar(100) DEFAULT NULL,
  `SanPham` varchar(100) DEFAULT NULL,
  `PhanQuyen` varchar(100) DEFAULT NULL,
  `ThongKe` varchar(100) DEFAULT NULL,
  `NhapHang` varchar(100) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `chucvu`
--

INSERT INTO `chucvu` (`MaCV`, `TenCV`, `HoaDon`, `KhachHang`, `NhanVien`, `KhuyenMai`, `SanPham`, `PhanQuyen`, `ThongKe`, `NhapHang`, `IsDeleted`) VALUES
(1, 'Nhân viên bán hàng', 'them,sua', 'them,sua,xoa,timkiem', 'null', 'null', 'null', 'null', 'null', 'null', 0),
(2, 'Thủ kho', 'null', 'null', 'null', 'null', 'them,sua,xoa,timkiem', 'null', 'null', 'them,sua,xoa,timkiem', 0),
(3, 'Bộ phận nhân sự', 'null', 'null', 'them,sua,xoa,timkiem', 'null', 'null', 'them,sua,xoa,timkiem', 'null', 'null', 0),
(4, 'Kế toán', 'them,sua,xoa,tim kiem', 'null', 'null', 'them,sua,xoa,timkiem', 'null', 'null', 'xem', 'null', 0),
(5, 'Quản lý', 'them,sua,xoa,tim kiem', 'them,sua,xoa,tim kiem', 'them,sua,xoa,tim kiem', 'them,sua,xoa,tim kiem', 'them,sua,xoa,tim kiem', 'them,sua,xoa,tim kiem', 'xem', 'them,sua,xoa,tim kiem', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `coupon`
--

CREATE TABLE `coupon` (
  `MaCP` int(5) NOT NULL,
  `Code` varchar(100) DEFAULT NULL,
  `TongLuotApDung` int(5) DEFAULT NULL,
  `TongLuotDaDung` int(5) DEFAULT NULL,
  `MaKMHD` int(5) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `coupon`
--

INSERT INTO `coupon` (`MaCP`, `Code`, `TongLuotApDung`, `TongLuotDaDung`, `MaKMHD`, `IsDeleted`) VALUES
(1, 'ABec32s', 10, 1, 1, 0),
(2, 'Oec2_32', 5, 1, 2, 0),
(3, 'Ceas12', 5, 1, 2, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(5) NOT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `TongTien` double DEFAULT NULL,
  `MaKH` int(5) DEFAULT NULL,
  `MaNV` int(5) DEFAULT NULL,
  `MaKMHD` int(5) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `NgayTao`, `TongTien`, `MaKH`, `MaNV`, `MaKMHD`, `IsDeleted`) VALUES
(1, '2023-03-27 11:59:00', 79235000, 2, 1, NULL, 0),
(2, '2023-02-26 09:54:00', 65260000, 1, 1, NULL, 0),
(3, '2023-01-24 17:01:23', 67960000, 4, 1, NULL, 0),
(4, '2023-01-14 16:14:04', 32730200, 5, 1, NULL, 0),
(5, '2022-12-22 12:22:42', 85360000, 7, 1, 1, 0),
(6, '2023-01-15 13:14:14', 59070400, 2, 8, NULL, 0),
(7, '2022-09-19 14:34:01', 26290000, 2, 8, 2, 0),
(8, '2023-02-14 19:00:00', 49570000, 4, 8, NULL, 0),
(9, '2023-03-04 14:20:22', 126120000, 7, 8, NULL, 0),
(10, '2022-08-18 14:05:21', 77960000, 9, 2, 2, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(5) NOT NULL,
  `TenKH` varchar(100) DEFAULT NULL,
  `SDT` varchar(20) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiemHienTai` int(10) DEFAULT NULL,
  `TongDiem` int(10) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `SDT`, `NgaySinh`, `DiemHienTai`, `TongDiem`, `IsDeleted`) VALUES
(1, 'Thôi Hương Mai', '0770653194', '1970-05-20', 0, 10, 0),
(2, 'Bì Ngọc Thuận', '0982875301', '1966-04-29', 20, 100, 0),
(3, 'Đổng Quốc Thịnh', '0965360248', '1972-06-29', 41, 53, 0),
(4, 'Quang Anh Tài', '0949068457', '1962-11-25', 100, 544, 0),
(5, 'Tôn Bích Ngân', '0863457296', '1977-10-20', 34, 51, 0),
(6, 'Trác Trúc Chi', '0783567102', '1992-12-07', 58, 77, 0),
(7, 'Thân Anh Chi', '0822493615', '1995-10-19', 1, 10, 0),
(8, 'Ngọ Hào Nghiệp', '0799521370', '1990-10-13', 54, 70, 0),
(9, 'Hầu Quang Thắng', '0888493120', '1986-07-04', 0, 0, 0),
(10, 'Ngọc Ðức Thọ', '086480613', '1985-05-09', 1, 5, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmaihoadon`
--

CREATE TABLE `khuyenmaihoadon` (
  `MaKMHD` int(5) NOT NULL,
  `TenKM` text DEFAULT NULL,
  `NgayBatDau` datetime DEFAULT NULL,
  `NgayKetThuc` datetime DEFAULT NULL,
  `DonHangToiThieu` double DEFAULT NULL,
  `PhanTramGiam` float DEFAULT NULL,
  `SoTienGiam` double DEFAULT NULL,
  `TongLuotApDung` int(10) DEFAULT NULL,
  `TongLuotDaDung` int(10) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khuyenmaihoadon`
--

INSERT INTO `khuyenmaihoadon` (`MaKMHD`, `TenKM`, `NgayBatDau`, `NgayKetThuc`, `DonHangToiThieu`, `PhanTramGiam`, `SoTienGiam`, `TongLuotApDung`, `TongLuotDaDung`, `IsDeleted`) VALUES
(1, 'Giảm 500K cho hóa đơn lớn hơn 50 triệu', '2022-12-20 12:00:00', '2022-12-22 19:00:00', 50000000, 0, 500000, 10, 1, 0),
(2, 'Giảm 2% cho đơn hàng lớn hơn 20 triệu', '2022-12-17 12:00:00', '2022-12-17 12:00:00', 20000000, 2, 0, 10, 2, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuyenmaisanpham`
--

CREATE TABLE `khuyenmaisanpham` (
  `MAKMSP` int(5) NOT NULL,
  `NGAYBATDAU` datetime DEFAULT NULL,
  `NGAYKETTHUC` datetime DEFAULT NULL,
  `PHANTRAMGIAM` float DEFAULT NULL,
  `SOTIENGIAM` double DEFAULT NULL,
  `TongLuotApDung` int(10) DEFAULT NULL,
  `TongLuotDaDung` int(10) DEFAULT NULL,
  `MaSP` int(5) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khuyenmaisanpham`
--

INSERT INTO `khuyenmaisanpham` (`MAKMSP`, `NGAYBATDAU`, `NGAYKETTHUC`, `PHANTRAMGIAM`, `SOTIENGIAM`, `TongLuotApDung`, `TongLuotDaDung`, `MaSP`, `IsDeleted`) VALUES
(1, '2023-03-25 12:00:00', '2023-03-27 19:00:00', 5, 0, 5, 1, 1, 0),
(2, '2022-09-18 12:00:00', '2022-09-20 20:00:00', 0, 99999, 10, 1, 19, 0),
(3, '2023-01-13 12:00:00', '2023-01-19 19:00:00', 2, 0, 15, 3, 6, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` int(5) NOT NULL,
  `TenNV` varchar(100) DEFAULT NULL,
  `SDT` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `TaiKhoan` varchar(100) DEFAULT NULL,
  `MatKhau` varchar(100) DEFAULT NULL,
  `MaCV` int(5) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `SDT`, `Email`, `NgaySinh`, `TaiKhoan`, `MatKhau`, `MaCV`, `IsDeleted`) VALUES
(1, 'Khu Nhật Quân', '0833824196', 'khunhatquan178@naver.com', '1953-09-21', 'Quan', '2191953', 1, 0),
(2, 'Trương Thùy Oanh', '0355910684', 'truongthuyoanh895@facebook.com', '2000-03-12', '1', '1', 5, 0),
(3, 'Khà Diệu Nga', '0364870569', 'khadieunga304@facebook.com', '1977-12-25', 'Nga', '25121977', 2, 0),
(4, 'Yên Kim Phượng', '0997461058', 'yenkimphuong807@icloud.com', '2010-08-01', 'Phuong', '182010', 3, 0),
(5, 'Đài Lệ Thanh', '0776150843', 'dailethanh847@microsoft.com', '2010-03-13', 'admin', 'admin', 5, 0),
(6, 'La Thùy Dương', '0769785601', 'lathuyduong808@facebook.com', '2004-04-14', 'root', 'root', 4, 0),
(7, 'Trần Cát Ly', '0982935648', 'trancatly855@icloud.com', '1965-01-31', '2', '2', 2, 0),
(8, 'Bùi Minh Kiệt', '0983859264', 'buiminhkiet391@yahoo.com', '2003-02-15', '3', '3', 1, 0),
(9, 'Cấn Minh Hiền', '0867528409', 'canminhhien894@hotmail.com', '1982-10-14', '4', '4', 3, 0),
(10, 'Châu Long Quân', '0853487952', 'chaulongquan691@facebook.com', '1970-09-23', '5', '5', 4, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPN` int(5) NOT NULL,
  `NgayTao` datetime DEFAULT NULL,
  `TongTien` double DEFAULT NULL,
  `MaNV` int(5) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`MaPN`, `NgayTao`, `TongTien`, `MaNV`, `IsDeleted`) VALUES
(1, '2022-05-14 13:00:01', 104840000, 3, 0),
(2, '2022-08-19 14:04:07', 47370000, 3, 0),
(3, '2022-09-19 08:00:00', 29180000, 3, 0),
(4, '2022-10-20 17:01:05', 39870000, 3, 0),
(5, '2022-10-24 15:01:04', 809550000, 3, 0),
(6, '2022-12-03 14:16:36', 112530000, 3, 0),
(7, '2023-01-05 11:14:47', 82450000, 7, 0),
(8, '2023-01-05 21:17:04', 414290000, 7, 0),
(9, '2023-02-15 21:17:18', 1672710000, 7, 0),
(10, '2023-03-11 17:17:45', 554070000, 7, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` int(5) NOT NULL,
  `TenSP` varchar(200) DEFAULT NULL,
  `GiaSP` double DEFAULT NULL,
  `AnhSP` varchar(255) DEFAULT NULL,
  `SL` int(5) DEFAULT NULL,
  `IsDeleted` tinyint(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `TenSP`, `GiaSP`, `AnhSP`, `SL`, `IsDeleted`) VALUES
(1, 'Asus TUF Gaming F15 FX506LHB i5 10300H', 16290000, 'src/main/java/Images/image1.jpg', 10, 0),
(2, 'Lenovo Ideapad Gaming 3 15IHU6 i5 11320H', 16390000, 'src/main/java/Images/image2.jpg', 4, 0),
(3, 'Acer Aspire 7 Gaming A715 42G R05G R5 5500U', 16990000, 'src/main/java/Images/image3.jpg', 7, 0),
(4, 'MSI Gaming GF63 Thin 11SC i5 11400H (664VN)', 15590000, 'src/main/java/Images/image4.jpg', 5, 0),
(5, 'HP Gaming VICTUS 15 fa0111TX i5 12500H', 25590000, 'src/main/java/Images/image5.jpg', 1, 0),
(6, 'Acer Aspire 7 Gaming A715 42G R05G R5 5500U', 16290000, 'src/main/java/Images/image6.jpg', 4, 0),
(7, 'Asus TUF Gaming F15 FX506LHB i5 10300H', 16390000, 'src/main/java/Images/image7.jpg', 9, 0),
(8, 'Lenovo Ideapad Gaming 3 15IHU6 i5 11320H', 16990000, 'src/main/java/Images/image8.jpg', 24, 0),
(9, 'MacBook Air M1 2020 7-core GPU', 19490000, 'src/main/java/Images/image9.jpg', 13, 0),
(10, 'MacBook Air M2 2022 8-core GPU', 26290000, 'src/main/java/Images/image10.jpg', 2, 0),
(11, 'MacBook Pro M2 2022 10-core GPU', 35490000, 'src/main/java/Images/image11.jpg', 0, 0),
(12, 'HP 15s fq2716TU i3 1115G4 (7C0X3PA)', 11690000, 'src/main/java/Images/image12.jpg', 5, 0),
(13, 'Asus Vivobook 14X OLED A1403ZA i5 12500H', 17490000, 'src/main/java/Images/image13.jpg', 9, 0),
(14, 'Dell Vostro 3510 i5 1135G7 (P112F002BBL)', 18790000, 'src/main/java/Images/image1.jpg', 5, 0),
(15, 'Dell Vostro 5620 i5 1240P (70296963)', 18790000, 'src/main/java/Images/image2.jpg', 12, 0),
(16, 'Lenovo Yoga Duet 7 13ITL6 i7 1165G7 (82MA009PVN)\r\n', 32490000, 'src/main/java/Images/image3.jpg', 0, 0),
(17, 'HP EliteBook 630 G9 i7 1255U (6M146PA)\r\n', 24790000, 'src/main/java/Images/image4.jpg', 14, 0),
(18, 'Lenovo Yoga 7 14IAL7 i7 1260P (82QE000QVN)\r\n', 25150000, 'src/main/java/Images/image5.jpg', 78, 0),
(19, 'MSI Summit E14 Evo A12M i7 1280P (211VN)\r\n', 26290000, 'src/main/java/Images/image6.jpg', 64, 0),
(20, 'Asus ExpertBook B5402CB i5 1240P (KI0353W)\r\n', 22990000, 'src/main/java/Images/image7.jpg', 14, 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaSP`,`MaHD`),
  ADD KEY `MaSP` (`MaSP`,`MaHD`),
  ADD KEY `MaHD` (`MaHD`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MaSP`,`MaPN`),
  ADD KEY `MaSP` (`MaSP`,`MaPN`),
  ADD KEY `MaPN` (`MaPN`);

--
-- Chỉ mục cho bảng `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`MaCV`);

--
-- Chỉ mục cho bảng `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`MaCP`),
  ADD UNIQUE KEY `Code` (`Code`),
  ADD KEY `MaKMHD` (`MaKMHD`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `MaKH` (`MaKH`),
  ADD KEY `MaNV` (`MaNV`),
  ADD KEY `MaKMHD` (`MaKMHD`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `khuyenmaihoadon`
--
ALTER TABLE `khuyenmaihoadon`
  ADD PRIMARY KEY (`MaKMHD`);

--
-- Chỉ mục cho bảng `khuyenmaisanpham`
--
ALTER TABLE `khuyenmaisanpham`
  ADD PRIMARY KEY (`MAKMSP`),
  ADD KEY `MaSP` (`MaSP`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`),
  ADD KEY `MaCV` (`MaCV`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPN`),
  ADD KEY `MaNV` (`MaNV`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chucvu`
--
ALTER TABLE `chucvu`
  MODIFY `MaCV` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `coupon`
--
ALTER TABLE `coupon`
  MODIFY `MaCP` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `khuyenmaihoadon`
--
ALTER TABLE `khuyenmaihoadon`
  MODIFY `MaKMHD` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `khuyenmaisanpham`
--
ALTER TABLE `khuyenmaisanpham`
  MODIFY `MAKMSP` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNV` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MaPN` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSP` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`);

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`),
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`);

--
-- Các ràng buộc cho bảng `coupon`
--
ALTER TABLE `coupon`
  ADD CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`MaKMHD`) REFERENCES `khuyenmaihoadon` (`MaKMHD`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  ADD CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`MaKMHD`) REFERENCES `khuyenmaihoadon` (`MaKMHD`);

--
-- Các ràng buộc cho bảng `khuyenmaisanpham`
--
ALTER TABLE `khuyenmaisanpham`
  ADD CONSTRAINT `khuyenmaisanpham_ibfk_1` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`MaCV`) REFERENCES `chucvu` (`MaCV`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
