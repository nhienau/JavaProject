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
public class HoaDon {
    private int MaHD;
    private Date NgayTao;
    private double TongTien;
    private int MaKH;
    private int MaNV;
    private int MaKMHD;
    private int IsDeleted;

    public HoaDon(int MaHD, Date NgayTao, double TongTien, int MaKH, int MaNV, int MaKMHD, int IsDeleted) {
        this.MaHD = MaHD;
        this.NgayTao = NgayTao;
        this.TongTien = TongTien;
        this.MaKH = MaKH;
        this.MaNV = MaNV;
        this.MaKMHD = MaKMHD;
        this.IsDeleted = IsDeleted;
    }
    
    public HoaDon(){};

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public int getMaKMHD() {
        return MaKMHD;
    }

    public void setMaKMHD(int MaKMHD) {
        this.MaKMHD = MaKMHD;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
    
}
