/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChucVu;
import DTO.NhanVien;

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
    
    public List<ChucVu> search(String input) throws ClassNotFoundException, SQLException {
    	Connection connection = DB.connect();
    	String sqlString = "select * from chucvu where TenCV like ? and IsDeleted = ?";
    	PreparedStatement pStatement = connection.prepareStatement(sqlString);
    	pStatement.setString(1, "%"+input+"%");
    	pStatement.setInt(2, 0);
    	ResultSet rSet = pStatement.executeQuery();
    	List<ChucVu> cvList = new ArrayList<>();
    	while(rSet.next()) {
    		int MaCV = rSet.getInt("MaCV");
    		String TenCV = rSet.getString("TenCV");
    		String HoaDon = rSet.getString("HoaDon");
    		String KhachHang = rSet.getString("KhachHang");
    		String NhanVien = rSet.getString("NhanVien");
    		String KhuyenMai = rSet.getString("KhuyenMai");
    		String SanPham = rSet.getString("SanPham");
    		String PhanQuyen = rSet.getString("PhanQuyen");
    		String ThongKe = rSet.getString("ThongKe");
    		String NhapHang = rSet.getString("NhapHang");
    		int IsDeleted = rSet.getInt("IsDeleted");
    		cvList.add(new ChucVu(MaCV, TenCV, HoaDon, KhachHang, NhanVien, KhuyenMai, SanPham, PhanQuyen, ThongKe, NhapHang, IsDeleted));
    	}
    	connection.close();
    	return cvList;
    	
    }
    
    public int addChucVu(ChucVu cv) throws ClassNotFoundException, SQLException {
    	Connection conn = DB.connect();
    	String sql = "insert into "
    			+ "chucvu(TenCV,HoaDon,KhachHang,NhanVien,KhuyenMai,SanPham,PhanQuyen,ThongKe,NhapHang,IsDeleted)"
    			+ " values(?,?,?,?,?,?,?,?,?,?)";
    	PreparedStatement pst = conn.prepareStatement(sql);
    	pst.setString(1, cv.getTenCV());
    	pst.setString(2, cv.getHoaDon());
    	pst.setString(3, cv.getKhachHang());
    	pst.setString(4, cv.getNhanVien());
    	pst.setString(5, cv.getKhuyenMai());
    	pst.setString(6, cv.getSanPham());
    	pst.setString(7, cv.getPhanQuyen());
    	pst.setString(8, cv.getThongKe());
    	pst.setString(9, cv.getNhapHang());
    	pst.setInt(10, 0);
    	int rowAffect = pst.executeUpdate();
    	conn.close();
    	return rowAffect;
    	
    }
    
    public int updateChucVu(ChucVu cv) throws ClassNotFoundException, SQLException {
    	Connection conn = DB.connect();
    	
    	String sql = "update chucvu set TenCV = ?, HoaDon = ?, "
    			+ "KhachHang = ?, NhanVien = ?, KhuyenMai= ?, SanPham =?,"
    			+ "PhanQuyen = ?, ThongKe = ?,NhapHang = ? where MaCV = ?";
    	PreparedStatement pst = conn.prepareStatement(sql);
    	pst.setString(1, cv.getTenCV());
    	pst.setString(2, cv.getHoaDon());
    	pst.setString(3, cv.getKhachHang());
    	pst.setString(4, cv.getNhanVien());
    	pst.setString(5, cv.getKhuyenMai());
    	pst.setString(6, cv.getSanPham());
    	pst.setString(7, cv.getPhanQuyen());
    	pst.setString(8, cv.getThongKe());
    	pst.setString(9, cv.getNhapHang());
    	pst.setInt(10, cv.getMaCV());
    	
    	int rowAffect = pst.executeUpdate();
    	conn.close();
    	return rowAffect;
    }
    
    public int deleteChucVu(int ID) throws ClassNotFoundException, SQLException {
    	Connection conn = DB.connect();
    	String sql = "update chucvu set IsDeleted = ? where MaCV = ?";
    	PreparedStatement pStatement = conn.prepareStatement(sql);
    	pStatement.setInt(1, 1);
    	pStatement.setInt(2, ID);
    	int rowAffect = pStatement.executeUpdate();
    	conn.close();
    	return rowAffect;
    }
    
    public ChucVu getPermission(NhanVien nv) {
    	ChucVu cv = null;
    	try {
			Connection conn = DB.connect();
			String query = "SELECT CV.* FROM nhanvien NV, chucvu CV WHERE NV.MaCV = CV.MaCV AND NV.MaNV = ?";
	        PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, nv.getMaNV());
			ResultSet rs = pst.executeQuery();
			
			if (rs.next() && rs.getInt(11) == 0) {
				cv = new ChucVu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cv;
    }
    
}
