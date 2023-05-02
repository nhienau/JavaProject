/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAO.DB;

/**
 *
 * @author Tam
 */
public class SanPhamDAO {
    private DB DB;
    public SanPhamDAO(){
        
    }
    
    public List<SanPham> getAll() throws SQLException, ClassNotFoundException{
    List<SanPham> spList = new ArrayList<>();
    try(Connection conn = new DB().connect()) {
        String sql = "Select * from sanpham where IsDeleted = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, 0);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            spList.add(new SanPham(rs.getInt("MaSP"),
                    rs.getString("TenSP"), rs.getDouble("GiaSP"),
                    rs.getString("AnhSP"), rs.getInt("SL"),
                    rs.getInt("IsDeleted")));
        }
    }
    catch(Exception e){
        System.out.println("Lỗi lấy danh sách sản phẩm");
    }
    
    return spList;
    }
    
    public int themSanPham (SanPham sp) throws SQLException, ClassNotFoundException {
        int rs;
        try (Connection conn = new DB().connect()) {
            String sql = "Insert into sanpham(AnhSP, TenSP, GiaSP, SL, IsDeleted) values (?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, sp.getAnhSP());
            pst.setString(2, sp.getTenSP());
            pst.setDouble(3, sp.getGiaSP());
            pst.setInt(4, sp.getSL());
            pst.setInt(5, sp.getIsDeleted());
            rs = pst.executeUpdate();
        }
        return rs;
    }
    
    public int xoaSanPham(int id) throws ClassNotFoundException, SQLException {
        Connection conn = new DB().connect();
        String sql = "Update sanpham set IsDeleted = ? where MaSP = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,1);
        pst.setInt(2, id);
        return pst.executeUpdate();
        
    }
    
    public List<SanPham> timKiemSanPham() throws SQLException, ClassNotFoundException {
        List<SanPham> spList = new ArrayList<>();
        try(Connection conn = new DB().connect()) {
            String sql = "Select * from sanpham where (TenSP like ? or MaSP like ? ) and IsDeleted = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, 0);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                spList.add(new SanPham(rs.getInt("MaSP"),
                        rs.getString("TenSP"), rs.getDouble("GiaSP"),
                        rs.getString("AnhSP"), rs.getInt("SL"),
                        rs.getInt("IsDeleted")));
            }
        }
        catch(Exception e){
            System.out.println("Tìm kiếm fail");
        }
        return spList;
    }
}
