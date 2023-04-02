/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class Coupon {
    private int MaCP;
    private String Code;
    private int TongLuotApDung;
    private int TongLuotDaDung;
    private int MaKMHD;
    private int IsDeleted;

    public Coupon(int MaCP, String Code, int TongLuotApDung, int TongLuotDaDung, int MaKMHD, int IsDeleted) {
        this.MaCP = MaCP;
        this.Code = Code;
        this.TongLuotApDung = TongLuotApDung;
        this.TongLuotDaDung = TongLuotDaDung;
        this.MaKMHD = MaKMHD;
        this.IsDeleted = IsDeleted;
    }
    
    public Coupon(){};

    public int getMaCP() {
        return MaCP;
    }

    public void setMaCP(int MaCP) {
        this.MaCP = MaCP;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
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
