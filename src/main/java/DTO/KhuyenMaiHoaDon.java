/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiHoaDon {
    private int MaKMHD;
    private String TenKM;
    private Timestamp NgayBatDau;
    private Timestamp NgayKetThuc;
    private double DonHangToiThieu;
    private float PhanTramGiam;
    private double SoTienGiam;
    private int TongLuotApDung;
    private int TongLuotDaDung;
    private int IsDeleted;

    public KhuyenMaiHoaDon(int MaKHMD, String TenKM, Timestamp NgayBatDau, Timestamp NgayKetThuc, double DonHangToiThieu, float PhanTramGiam, double SoTienGiam, int TongLuotApDung, int TongLuotDaDung, int IsDeleted) {
        this.MaKMHD = MaKHMD;
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

    public int getMaKMHD() {
        return MaKMHD;
    }

    public void setMaKMHD(int MaKHMD) {
        this.MaKMHD = MaKHMD;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public Timestamp getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Timestamp NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Timestamp getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Timestamp NgayKetThuc) {
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
