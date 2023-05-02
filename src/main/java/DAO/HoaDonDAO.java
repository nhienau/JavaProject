package DAO;

import DTO.HoaDon;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
public class HoaDonDAO {
    public HoaDonDAO()
    {
        
    }
    public List<HoaDon> takeAll() throws ClassNotFoundException, SQLException
    {
        Connection conn = DB.connect();
        String sql = "SELECT * FROM hoadon where IsDeleted = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,0);
        ResultSet rs = pst.executeQuery();
        List<HoaDon> hdList = new ArrayList<>();
        while (rs.next()) {
            int maHD = rs.getInt("MaHD");
            Date ngayTao = rs.getDate("NgayTao");
            double tongTien = rs.getDouble("TongTien");
            int maKH = rs.getInt("MaKH");
            int maNV = rs.getInt("MaNV");
            int maKMHD = rs.getInt("MaKMHD");
            int IsDeleted = rs.getInt("IsDeleted");

            hdList.add(new HoaDon(maHD, ngayTao, tongTien, maKH, maNV, maKMHD, IsDeleted));
        }
        conn.close();
        return hdList;
    }
    public List<HoaDon> loadHoaDon() throws ClassNotFoundException, SQLException {
        Connection conn = DB.connect();
        String sql = "SELECT * FROM hoadon WHERE IsDeleted = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, 0);
        ResultSet rs = pst.executeQuery();
        List<HoaDon> hdList = new ArrayList<>();
        while (rs.next()) {
            int maHD = rs.getInt("MaHD");
            Date ngayTao = rs.getDate("NgayTao");
            double tongTien = rs.getDouble("TongTien");
            int maKH = rs.getInt("MaKH");
            int maNV = rs.getInt("MaNV");
            int maKMHD = rs.getInt("MaKMHD");
            int IsDeleted = rs.getInt("IsDeleted");
            hdList.add(new HoaDon(maHD, ngayTao, tongTien, maKH, maNV, maKMHD, IsDeleted));
        }
        conn.close();
        return hdList;
    }
    
    public HoaDon getHoaDonById(int MaHD) throws SQLException, ClassNotFoundException {
        Connection conn = DB.connect();
        String query = "SELECT * FROM hoadon WHERE MaHD = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, MaHD);
        ResultSet rs = ps.executeQuery();

        HoaDon hoaDon = null;
        if (rs.next()) {
            int maHD = rs.getInt("MaHD");
            Date ngayTao = rs.getDate("NgayTao");
            double tongTien = rs.getDouble("TongTien");
            int maKH = rs.getInt("MaKH");
            int maNV = rs.getInt("MaNV");
            int maKMHD = rs.getInt("MaKMHD");
            int deleted = rs.getInt("IsDeleted");
            hoaDon = new HoaDon(maHD, ngayTao, tongTien, maKH, maNV, maKMHD, deleted);
        }

        conn.close();
        return hoaDon;
    }

    public void updateHoaDon(HoaDon hoaDon) throws ClassNotFoundException, SQLException {
        Connection conn = DB.connect();
        String sql = "UPDATE hoadon SET MaKH = ?, MaNV = ?, MaKMHD = ? WHERE MaHD = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, hoaDon.getMaKH());
        pst.setInt(2, hoaDon.getMaNV());
        pst.setInt(3, hoaDon.getMaKMHD());
        pst.setInt(4, hoaDon.getMaHD());
        pst.executeUpdate();
        conn.close();
    }
    
    public List<HoaDon> searchHoaDon(String comboBoxValue, int searchValue) {
        List<HoaDon> hdList = new ArrayList<>();
        try {
            Connection con = DB.connect();
            String sql = "SELECT * FROM hoadon WHERE " + comboBoxValue + "=? AND IsDeleted=0";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, searchValue);
            ResultSet rs = pst.executeQuery();
            if(!rs.isBeforeFirst()){ 
                JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn!");
            }
            while (rs.next()) {
                hdList.add(new HoaDon(rs.getInt("MaHD"), rs.getDate("NgayTao"), rs.getDouble("TongTien"), rs.getInt("MaKH"), rs.getInt("MaNV"), rs.getInt("MaKMHD"), 0));
            }
            con.close();
            loadHoaDon();
            //System.out.println("abc");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return hdList;
    }
    
    public void deleteHoaDon(int maHD) throws ClassNotFoundException, SQLException {
        Connection con = DB.connect();
        String sql = "UPDATE hoadon SET IsDeleted = 1 WHERE MaHD=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maHD);
        pst.executeUpdate();
        loadHoaDon();
        con.close();
    }
    
    public List<HoaDon> getActiveHoaDonList(int MaHD) throws ClassNotFoundException, SQLException {
        Connection con = DB.connect();
        String sql = "SELECT * FROM hoadon WHERE IsDeleted=0";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<HoaDon> hdList = new ArrayList<>();
        while (rs.next()) {
            HoaDon hd = new HoaDon(rs.getInt("MaHD"), rs.getDate("NgayTao"), rs.getDouble("TongTien"),
                    rs.getInt("MaKH"), rs.getInt("MaNV"), rs.getInt("MaKMHD"), 0);
            hdList.add(hd);
        }
        con.close();
        return hdList;
    }
}
