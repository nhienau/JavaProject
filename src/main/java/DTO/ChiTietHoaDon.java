/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class ChiTietHoaDon {
    private int MaSP;
    private int MaHD;
    private int SoLuong;
    private double GiaChuaGiam;
    private double GiaDaGiam;

    public ChiTietHoaDon(int MaSP, int MaHD, int SoLuong, double GiaChuaGiam, double GiaDaGiam) {
        this.MaSP = MaSP;
        this.MaHD = MaHD;
        this.SoLuong = SoLuong;
        this.GiaChuaGiam = GiaChuaGiam;
        this.GiaDaGiam = GiaDaGiam;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getGiaChuaGiam() {
        return GiaChuaGiam;
    }

    public void setGiaChuaGiam(double GiaChuaGiam) {
        this.GiaChuaGiam = GiaChuaGiam;
    }

    public double getGiaDaGiam() {
        return GiaDaGiam;
    }

    public void setGiaDaGiam(double GiaDaGiam) {
        this.GiaDaGiam = GiaDaGiam;
    }
    
    
}
