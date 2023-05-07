/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tam
 */
public class SanPhamExcelDTO {
    private String masp, tensanpham;
    private int sl;
    private double gia, tongtien;

    public SanPhamExcelDTO(String masp, String tensanpham, int sl, double gia, double tongtien) {
        this.masp = masp;
        this.tensanpham = tensanpham;
        this.sl = sl;
        this.gia = gia;
        this.tongtien = tongtien;
    }

    public SanPhamExcelDTO() {
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }
    
}
