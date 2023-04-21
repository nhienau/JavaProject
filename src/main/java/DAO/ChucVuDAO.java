/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChucVu;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChucVuDAO {
    public ChucVuDAO(){
        
    }
    
    public List<ChucVu> takeAll() throws ClassNotFoundException, SQLException{
        Connection conn = DB.connect();
        String sql = "select * from chucvu where IsDeleted = ?";
        PreparedStatement pst =  conn.prepareStatement(sql);
        pst.setInt(1, 0);
        ResultSet rs = pst.executeQuery();
        List<ChucVu> cvList = new ArrayList<>();
        while(rs.next()){
            int MaCV = rs.getInt("MaCV");
            String TenCV = rs.getString("TenCV");
            String HoaDon = rs.getString("HoaDon");
            String KhachHang = rs.getString("KhachHang");
            String NhanVien = rs.getString("NhanVien");
            String KhuyenMai = rs.getString("KhuyenMai");
            String SanPham = rs.getString("SanPham");
            String PhanQuyen = rs.getString("PhanQuyen");
            String ThongKe = rs.getString("ThongKe");
            String NhapHang = rs.getString("NhapHang");
            int IsDeleted = rs.getInt("IsDeleted");
            cvList.add(new ChucVu(MaCV, TenCV, HoaDon, KhachHang, NhanVien, KhuyenMai, SanPham, PhanQuyen, ThongKe, NhapHang, IsDeleted));
        }
        
        conn.close();
        return cvList;
    }
}
