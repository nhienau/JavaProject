/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ASUS
 */
public class SanPham {
    private int MaSP;
    private String TenSP;
    private String GiaSP;
    private String AnhSP;
    private int SL;
    private int IsDeleted;

    public SanPham(int MaSP, String TenSP, String GiaSP, String AnhSP, int SL, int IsDeleted) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.GiaSP = GiaSP;
        this.AnhSP = AnhSP;
        this.SL = SL;
        this.IsDeleted = IsDeleted;
    }
    public SanPham( String TenSP, String GiaSP, String AnhSP, int SL, int IsDeleted) {
        
        this.TenSP = TenSP;
        this.GiaSP = GiaSP;
        this.AnhSP = AnhSP;
        this.SL = SL;
        this.IsDeleted = IsDeleted;
    }
    public SanPham( String TenSP, String GiaSP, String AnhSP, int SL) {
        this.TenSP = TenSP;
        this.GiaSP = GiaSP;
        this.AnhSP = AnhSP;
        this.SL = SL;
    }
    
    
    public SanPham(){};

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getGiaSP() {
        return GiaSP;
    }

    public void setGiaSP(String GiaSP) {
        this.GiaSP = GiaSP;
    }

    public String getAnhSP() {
        return AnhSP;
    }

    public void setAnhSP(String AnhSP) {
        this.AnhSP = AnhSP;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
    
    
}
