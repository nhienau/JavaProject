/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiHoaDon {
    private int MaKHMD;
    private String TenKM;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private double DonHangToiThieu;
    private float PhanTramGiam;
    private double SoTienGiam;
    private int TongLuotApDung;
    private int TongLuotDaDung;
    private int IsDeleted;

    public KhuyenMaiHoaDon(int MaKHMD, String TenKM, Date NgayBatDau, Date NgayKetThuc, double DonHangToiThieu, float PhanTramGiam, double SoTienGiam, int TongLuotApDung, int TongLuotDaDung, int IsDeleted) {
        this.MaKHMD = MaKHMD;
        this.TenKM = TenKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.DonHangToiThieu = DonHangToiThieu;
        this.PhanTramGiam = PhanTramGiam;
        this.SoTienGiam = SoTienGiam;
        this.TongLuotApDung = TongLuotApDung;
        this.TongLuotDaDung = TongLuotDaDung;
        this.IsDeleted = IsDeleted;
    }
    
    public KhuyenMaiHoaDon(){};

    public int getMaKHMD() {
        return MaKHMD;
    }

    public void setMaKHMD(int MaKHMD) {
        this.MaKHMD = MaKHMD;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public double getDonHangToiThieu() {
        return DonHangToiThieu;
    }

    public void setDonHangToiThieu(double DonHangToiThieu) {
        this.DonHangToiThieu = DonHangToiThieu;
    }

    public float getPhanTramGiam() {
        return PhanTramGiam;
    }

    public void setPhanTramGiam(float PhanTramGiam) {
        this.PhanTramGiam = PhanTramGiam;
    }

    public double getSoTienGiam() {
        return SoTienGiam;
    }

    public void setSoTienGiam(double SoTienGiam) {
        this.SoTienGiam = SoTienGiam;
    }

    public int getTongLuotApDung() {
        return TongLuotApDung;
    }

    public void setTongLuotApDung(int TongLuotApDung) {
        this.TongLuotApDung = TongLuotApDung;
    }

    public int getTongLuotDaDung() {
        return TongLuotDaDung;
    }

    public void setTongLuotDaDung(int TongLuotDaDung) {
        this.TongLuotDaDung = TongLuotDaDung;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    
}
