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
public class PhieuNhap {
    private int MaPN;
    private Date NgayTao;
    private double TongTien;
    private int MaNV;
    private int IsDeleted;

    public PhieuNhap(int MaPN, Date NgayTao, double TongTien, int MaNV, int IsDeleted) {
        this.MaPN = MaPN;
        this.NgayTao = NgayTao;
        this.TongTien = TongTien;
        this.MaNV = MaNV;
        this.IsDeleted = IsDeleted;
    }
    
    public PhieuNhap(){};

    public int getMaPN() {
        return MaPN;
    }

    public void setMaPN(int MaPN) {
        this.MaPN = MaPN;
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

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
    
}
