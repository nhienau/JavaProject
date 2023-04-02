/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class ChiTietPhieuNhap {
    private int MaSP;
    private int MaPN;
    private int SL;
    private double DonGia;

    public ChiTietPhieuNhap(int MaSP, int MaPN, int SL, double DonGia) {
        this.MaSP = MaSP;
        this.MaPN = MaPN;
        this.SL = SL;
        this.DonGia = DonGia;
    }
    
    public ChiTietPhieuNhap(){};

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public int getMaPN() {
        return MaPN;
    }

    public void setMaPN(int MaPN) {
        this.MaPN = MaPN;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }
    
    
}
