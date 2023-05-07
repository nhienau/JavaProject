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
    private String TongTien;
    private int MaNV;
    private int IsDeleted;

    public PhieuNhap(int MaPN, Date NgayTao, String TongTien, int MaNV, int IsDeleted) {
        this.MaPN = MaPN;
        this.NgayTao = NgayTao;
        this.TongTien = TongTien;
        this.MaNV = MaNV;
        this.IsDeleted = IsDeleted;
    }
    
    public PhieuNhap( String TongTien, int MaNV, int IsDeleted) {
       
        this.TongTien = TongTien;
        this.MaNV = MaNV;
        this.IsDeleted = IsDeleted;
    }
    public PhieuNhap( Date NgayTao, String TongTien, int MaNV, int IsDeleted) {
        
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
    if (NgayTao == null) {
        // Handle the case when NgayTao is null, e.g., assign a default value or throw an exception
        // For example, you can return the current date and time as the default value:
        return new Date();
    }
    return NgayTao;
}


   public void setNgayTao(Date NgayTao) {
    if (NgayTao != null) {
        this.NgayTao = NgayTao;
    } else {
        // Handle the case when NgayTao is null, e.g., assign a default value or throw an exception
        // For example, you can assign the current date and time as the default value:
        this.NgayTao = new Date();
    }
}


    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String TongTien) {
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
