/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class ChucVu {
    private int MaCV;
    private String TenCV;
    private String HoaDon;
    private String KhachHang;
    private String NhanVien;
    private String KhuyenMai;
    private String SanPham;
    private String PhanQuyen;
    private String ThongKe;
    private String NhapHang;
    private int IsDeleted;

    public ChucVu(int MaCV, String TenCV, String HoaDon, String KhachHang, String NhanVien, String KhuyenMai, String SanPham, String PhanQuyen, String ThongKe, String NhapHang, int IsDeleted) {
        this.MaCV = MaCV;
        this.TenCV = TenCV;
        this.HoaDon = HoaDon;
        this.KhachHang = KhachHang;
        this.NhanVien = NhanVien;
        this.KhuyenMai = KhuyenMai;
        this.SanPham = SanPham;
        this.PhanQuyen = PhanQuyen;
        this.ThongKe = ThongKe;
        this.NhapHang = NhapHang;
        this.IsDeleted = IsDeleted;
    }
    
    public ChucVu(){};

    public int getMaCV() {
        return MaCV;
    }

    public void setMaCV(int MaCV) {
        this.MaCV = MaCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String TenCV) {
        this.TenCV = TenCV;
    }

    public String getHoaDon() {
        return HoaDon;
    }

    public void setHoaDon(String HoaDon) {
        this.HoaDon = HoaDon;
    }

    public String getKhachHang() {
        return KhachHang;
    }

    public void setKhachHang(String KhachHang) {
        this.KhachHang = KhachHang;
    }

    public String getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(String NhanVien) {
        this.NhanVien = NhanVien;
    }

    public String getKhuyenMai() {
        return KhuyenMai;
    }

    public void setKhuyenMai(String KhuyenMai) {
        this.KhuyenMai = KhuyenMai;
    }

    public String getSanPham() {
        return SanPham;
    }

    public void setSanPham(String SanPham) {
        this.SanPham = SanPham;
    }

    public String getPhanQuyen() {
        return PhanQuyen;
    }

    public void setPhanQuyen(String PhanQuyen) {
        this.PhanQuyen = PhanQuyen;
    }

    public String getThongKe() {
        return ThongKe;
    }

    public void setThongKe(String ThongKe) {
        this.ThongKe = ThongKe;
    }

    public String getNhapHang() {
        return NhapHang;
    }

    public void setNhapHang(String NhapHang) {
        this.NhapHang = NhapHang;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
    
}
