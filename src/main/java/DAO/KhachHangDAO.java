/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.KhachHang;

/**
 *
 * @author HP
 */
public class KhachHangDAO {
    public KhachHangDAO() {
        
    }
    
    public List<KhachHang> getAll() throws SQLException {
    	List<KhachHang> list = new ArrayList<>();
    	
    	try {
			Connection conn = DB.connect();
			String query = "SELECT * FROM khachhang WHERE IsDeleted = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, 0);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				list.add(new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getString("SDT"), rs.getDate("NgaySinh"), rs.getInt("DiemHienTai"), rs.getInt("TongDiem"), 0));
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}
    	return list;
    }
    
    public int addCustomer(KhachHang kh) throws SQLException {
    	int rowAffect = 0;
    	try {
			Connection conn = DB.connect();
			String query = "INSERT INTO khachhang(MaKH, TenKH, SDT, NgaySinh, DiemHienTai, TongDiem, IsDeleted) VALUES (NULL,?,?,?,?,?,?)";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, kh.getTenKH());
	        pst.setString(2, kh.getSDT());
	        pst.setString(3, kh.getDate());
	        pst.setInt(4, kh.getDiemHienTai());
	        pst.setInt(5, kh.getTongDiem());
	        pst.setInt(6, 0);
	        rowAffect = pst.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}
    	return rowAffect;
    }
    
    public int updateCustomer(KhachHang kh) throws SQLException {
    	int rowAffect = 0;
    	try {
			Connection conn = DB.connect();
			String query = "UPDATE khachhang SET TenKH = ?, SDT = ?, NgaySinh = ?, DiemHienTai = ?, TongDiem = ? WHERE MaKH = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, kh.getTenKH());
	        pst.setString(2, kh.getSDT());
	        pst.setString(3, kh.getDate());
	        pst.setInt(4, kh.getDiemHienTai());
	        pst.setInt(5, kh.getTongDiem());
	        pst.setInt(6, kh.getMaKH());
	        rowAffect = pst.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}
    	return rowAffect;
    }
    
    public int deleteCustomer(int id) throws SQLException {
    	int rowAffect = 0;
    	try {
			Connection conn = DB.connect();
			String query = "UPDATE khachhang SET IsDeleted = ? WHERE MaKH = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, 1);
	        pst.setInt(2, id);
	        rowAffect = pst.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}
    	return rowAffect;
    }
    
}
