/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiSanPham {
    private int MaKMSP;
    private Timestamp NgayBatDau;
    private Timestamp NgayKetThuc;
    private float PhanTramGiam;
    private double SoTienGiam;
    private int TongLuotApDung;
    private int TongLuotDaDung;
    private int MaSP;
    private int IsDeleted;

    public KhuyenMaiSanPham(int MaKMSP, Timestamp NgayBatDau, Timestamp NgayKetThuc, float PhanTramGiam, double SoTienGiam, int TongLuotApDung, int TongLuotDaDung, int MaSP, int IsDeleted) {
        this.MaKMSP = MaKMSP;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.PhanTramGiam = PhanTramGiam;
        this.SoTienGiam = SoTienGiam;
        this.TongLuotApDung = TongLuotApDung;
        this.TongLuotDaDung = TongLuotDaDung;
        this.MaSP = MaSP;
        this.IsDeleted = IsDeleted;
    }
    
    public KhuyenMaiSanPham(){};

    public int getMaKMSP() {
        return MaKMSP;
    }

    public void setMaKMSP(int MaKMSP) {
        this.MaKMSP = MaKMSP;
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

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
    
}
