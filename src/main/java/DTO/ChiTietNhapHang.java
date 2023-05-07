/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tam
 */
public class ChiTietNhapHang {
    private int MaSP;
    private int MaPN;
    private int SL;
    private double DONGIA;

    public ChiTietNhapHang(int MaSP, int MaPN, int SL, double DONGIA) {
        this.MaSP = MaSP;
        this.MaPN = MaPN;
        this.SL = SL;
        this.DONGIA = DONGIA;
    }

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

    public double getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(double DONGIA) {
        this.DONGIA = DONGIA;
    }
}
