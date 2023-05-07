/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class ChiTietHoaDonDAO {
    private DB db;
    
    public ChiTietHoaDonDAO(){
    }
    
    public boolean addCTHD(ChiTietHoaDon cthd) throws ClassNotFoundException, SQLException
    {
        boolean result = false;
        Connection conn=db.connect();
        if(conn!=null) {
            try {
                    String sql = "Insert into chitiethoadon(MaSP,MaHD,SoLuong,GiaChuaGiam,GiaDaGiam) values(?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, cthd.getMaSP());
                    stmt.setInt(2, cthd.getMaHD());
                    stmt.setInt(3, cthd.getSoLuong());
                    stmt.setDouble(4, cthd.getGiaChuaGiam());
                    stmt.setDouble(5, cthd.getGiaDaGiam());
                    if (stmt.executeUpdate()>=1)
                        result = true;
                } catch (SQLException ex) {
            System.out.println(ex);
            } finally{
            conn.close(); } }
            return result;
    }
}
