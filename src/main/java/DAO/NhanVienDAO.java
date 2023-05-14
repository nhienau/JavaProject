/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVien;
import GUI.LoginForm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;




/**
 *
 * @author Admin
 */
public class NhanVienDAO {
    private DB db;
    public NhanVienDAO(){
        
    }
    public List<NhanVien> takeAll() throws SQLException, ClassNotFoundException{
        
        Connection conn = db.connect();
        String sql = "select * from nhanvien where IsDeleted = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,0);
        ResultSet rs = pst.executeQuery();
        List<NhanVien> nvList = new ArrayList<>();
        while(rs.next()){
            nvList.add(new NhanVien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("SDT"),rs.getString("Email"), rs.getDate("NgaySinh"), rs.getString("TaiKhoan"),rs.getString("MatKhau"), rs.getInt("MaCV"), rs.getInt("IsDeleted")));
        }
        conn.close();
        return nvList;
    }
    
    public List<NhanVien> filter(String sortCondition,String searchInput) throws ClassNotFoundException, SQLException{
        String sql = "select * from nhanvien as nv join chucvu as cv on nv.MaCV = cv.MaCV "
                + "where cv.TenCV like ? and (nv.TenNV like ? or nv.Email like ? or nv.SDT like ?) and nv.IsDeleted = ? ";
        Connection conn = DB.connect();
        PreparedStatement pst =conn.prepareStatement(sql);
        pst.setString(1, sortCondition);
        pst.setString(2, searchInput);
        pst.setString(3, searchInput);
        pst.setString(4, searchInput);
        pst.setInt(5,0);
        ResultSet rs = pst.executeQuery();
        List<NhanVien> nvList = new ArrayList<>();
        while(rs.next()){
            nvList.add(new NhanVien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("SDT"),rs.getString("Email"), rs.getDate("NgaySinh"), rs.getString("TaiKhoan"),rs.getString("MatKhau"), rs.getInt("MaCV"), rs.getInt("IsDeleted")));
        }
        conn.close();
        return nvList;
    }
    
    public List<NhanVien> searchOnly(String searchInput) throws ClassNotFoundException, SQLException{
        Connection conn = DB.connect();
        String sql = "select * from nhanvien where (TenNV like ? or Email like ? or SDT like ?) and IsDeleted = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,searchInput);
        pst.setString(2,searchInput);
        pst.setString(3,searchInput);
        pst.setInt(4, 0);
        ResultSet rs = pst.executeQuery();
        List<NhanVien> nvList = new ArrayList<>();
        while(rs.next()){
            nvList.add(new NhanVien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("SDT"),rs.getString("Email"), rs.getDate("NgaySinh"), rs.getString("TaiKhoan"),rs.getString("MatKhau"), rs.getInt("MaCV"), rs.getInt("IsDeleted")));
        }
        conn.close();
        return nvList;
    }
    
    public int addEmp(NhanVien nv) throws ClassNotFoundException, SQLException{
        Connection con = DB.connect();
        String sql;
            sql = "insert into nhanvien(TenNV,SDT,Email,NgaySinh,TaiKhoan,MatKhau,IsDeleted,MaCV) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, nv.getTenNV());
        pst.setString(2, nv.getSDT());
        pst.setString(3, nv.getEmail());
        
        pst.setString(4,  nv.getDate());
        pst.setString(5, nv.getTaiKhoan());
        pst.setString(6, nv.getMatKhau());
        
        pst.setInt(7, nv.getIsDeleted());
        if(nv.getMaCV() != -1){
            pst.setInt(8, nv.getMaCV());
        }
        else {
        	pst.setNull(8, Types.INTEGER);
        }
        int rs = pst.executeUpdate();
        con.close();
        return rs;
    }
    
    public int updateEmp(NhanVien nv) throws ClassNotFoundException, SQLException{
        Connection con = DB.connect();
        String sql;
           sql = "update nhanvien set TenNV = ? ,SDT = ? , Email = ? , NgaySinh = ? , TaiKhoan = ? , MatKhau = ? ,MaCV = ? Where MaNV = ?";
       
        PreparedStatement pst = con.prepareStatement(sql);
         pst.setString(1, nv.getTenNV());
        pst.setString(2, nv.getSDT());
        pst.setString(3, nv.getEmail());
        
        pst.setString(4,  nv.getDate());
        pst.setString(5, nv.getTaiKhoan());
        pst.setString(6, nv.getMatKhau());
        if(nv.getMaCV() != -1){
            pst.setInt(7, nv.getMaCV());
            
        }
        else{
            pst.setNull(7, Types.INTEGER);
        }
        pst.setInt(8, nv.getMaNV());
        int rowAffect =  pst.executeUpdate();
        con.close();
        return rowAffect;
    }
    
    public int updateEmpWithoutPassword(NhanVien nv) throws ClassNotFoundException, SQLException{
        Connection con = DB.connect();
        String sql;
           sql = "update nhanvien set TenNV = ? ,SDT = ? , Email = ? , NgaySinh = ? , TaiKhoan = ? ,MaCV = ? Where MaNV = ?";
       
        PreparedStatement pst = con.prepareStatement(sql);
         pst.setString(1, nv.getTenNV());
        pst.setString(2, nv.getSDT());
        pst.setString(3, nv.getEmail());
        
        pst.setString(4,  nv.getDate());
        pst.setString(5, nv.getTaiKhoan());
        if(nv.getMaCV() != -1){
            pst.setInt(6, nv.getMaCV());
            
        }
        else{
            pst.setNull(6, Types.INTEGER);
        }
        pst.setInt(7, nv.getMaNV());
        int rowAffect =  pst.executeUpdate();
        con.close();
        return rowAffect;
    }
    
    public int delEmp(int ID) throws ClassNotFoundException, SQLException{
        Connection con = DB.connect();
        String sql = "update nhanvien set IsDeleted = ? where MaNV = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, 1);
        pst.setInt(2, ID);
        int rowAffect = pst.executeUpdate();
        con.close();
        return rowAffect;
    }
    
    
    public void updateWhenCVIsDeleted(int MaCV) throws ClassNotFoundException, SQLException {
    	Connection conn = DB.connect();
    	String sqlString = "update nhanvien set MaCV = ? Where MaCV = ? ";
    	PreparedStatement pst = conn.prepareStatement(sqlString);
    	pst.setNull(1, Types.INTEGER);
    	pst.setInt(2, MaCV);
    	pst.executeUpdate();
    	conn.close();
    	
    }
    
    public NhanVien verifyLogin(String username, String password) throws SQLException {
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
//			e.printStackTrace();
			throw e;
		}
		return nv;
    }
    
    public int updateEmployeeInfo (NhanVien nv) throws SQLException {
    	int rowAffect = 0;
    	try {
			Connection conn = DB.connect();
			String query = "UPDATE nhanvien SET TenNV = ?, SDT = ?, Email = ?, NgaySinh = ? WHERE MaNV = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, nv.getTenNV());
	        pst.setString(2, nv.getSDT());
	        pst.setString(3, nv.getEmail());
	        pst.setString(4, nv.getDate()); // chua duoc
	        pst.setInt(5, nv.getMaNV());
	        
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
    
    public int changePassword (NhanVien nv) throws SQLException {
    	int rowAffect = 0;
    	try {
			Connection conn = DB.connect();
			String query = "UPDATE nhanvien SET MatKhau = ? WHERE MaNV = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setString(1, nv.getMatKhau());
	        pst.setInt(2, nv.getMaNV());
	        
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
    
    public boolean phoneNumberExisted (NhanVien user) throws SQLException {
    	boolean check = false;
    	try {
			Connection conn = DB.connect();
			String query = "SELECT * FROM nhanvien WHERE IsDeleted = ? AND MaNV != ? AND SDT = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, 0);
	        pst.setInt(2, user.getMaNV());
	        pst.setString(3, user.getSDT());
	        ResultSet rs = pst.executeQuery();
	        check = rs.next();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}
    	return check;
    }
    
    public boolean isAccountNameExisted(String accoutName,int ID) throws ClassNotFoundException, SQLException {
    	Connection conn = DB.connect();
    	String sqlString="";
    	if(ID != -1) {
    		sqlString = "select * from nhanvien where TaiKhoan = '" +accoutName +"'and MaNV !=" +ID;
    		
    	}
    	else {
    		sqlString = "select * from nhanvien where TaiKhoan = '" +accoutName +"'";  		
    	}
    	Statement stm = conn.createStatement();
    	ResultSet resultSet = stm.executeQuery(sqlString);
	    int rowCount = 0;
	    while(resultSet.next()) {
	    	rowCount++;
	    }
    	return rowCount > 0 ;
    	
    }
    
    public boolean emailExisted (NhanVien user) throws SQLException {
    	boolean check = false;
    	try {
			Connection conn = DB.connect();
			String query = "SELECT * FROM nhanvien WHERE IsDeleted = ? AND MaNV != ? AND Email = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
	        pst.setInt(1, 0);
	        pst.setInt(2, user.getMaNV());
	        pst.setString(3, user.getEmail());
	        ResultSet rs = pst.executeQuery();
	        check = rs.next();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}
    	return check;
    }
    
}
