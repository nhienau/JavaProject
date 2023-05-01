/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.NhanVien;

/**
 *
 * @author HP
 */
public class LoginDAO {
	
    public LoginDAO() {
        
    }
    
    public NhanVien verifyLogin(String username, String password) {
    	NhanVien nv = null;
    	try {
			Connection conn = DB.connect();
			String query = "SELECT * FROM nhanvien WHERE TaiKhoan = ? AND BINARY MatKhau = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				nv = new NhanVien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("SDT"),rs.getString("Email"), rs.getDate("NgaySinh"), rs.getString("TaiKhoan"),rs.getString("MatKhau"), rs.getInt("MaCV"), rs.getInt("IsDeleted"));
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nv;
    }
}
