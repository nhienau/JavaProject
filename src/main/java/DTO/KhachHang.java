/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class KhachHang {
    private int MaKH;
    private String TenKH;
    private String SDT;
    private Date NgaySinh;
    private int DiemHienTai;
    private int TongDiem;
    private int IsDeleted;

    public KhachHang(int MaKH, String TenKH, String SDT, Date NgaySinh, int DiemHienTai, int TongDiem, int IsDeleted) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.SDT = SDT;
        this.NgaySinh = NgaySinh;
        this.DiemHienTai = DiemHienTai;
        this.TongDiem = TongDiem;
        this.IsDeleted = IsDeleted;
    }
    
    public KhachHang(){};

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public int getDiemHienTai() {
        return DiemHienTai;
    }

    public void setDiemHienTai(int DiemHienTai) {
        this.DiemHienTai = DiemHienTai;
    }

    public int getTongDiem() {
        return TongDiem;
    }

    public void setTongDiem(int TongDiem) {
        this.TongDiem = TongDiem;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.NgaySinh);
    }
    @Override
    public String toString() {
        return MaKH + "-"+ TenKH;
    }
}
