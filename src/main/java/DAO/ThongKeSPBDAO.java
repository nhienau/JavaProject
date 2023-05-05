package DAO;

import java.awt.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;




public class ThongKeSPBDAO {
    public static JFreeChart createChart() throws SQLException, ClassNotFoundException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DB.connect();
            String sql = "SELECT MaSP, SUM(SoLuong) FROM chitiethoadon GROUP BY MaSP";
            st = con.createStatement();
            rs = st.executeQuery(sql);
    
            while (rs.next()) {
                String MaSP = rs.getString("MaSP");
                int SoLuong = rs.getInt("SUM(SoLuong)");
                dataset.addValue(SoLuong, "Số lượng", MaSP);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
    
        JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê số lượng sản phẩm đã bán được",
            "Mã sản phẩm",
            "Số lượng",
            dataset,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );
    
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeGridlinePaint(Color.BLACK);
    
        return chart;
    }
    public static DefaultTableModel getSPB() throws SQLException, ClassNotFoundException {
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
    
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Số lượng");
        model.addColumn("Giá bán/cái");
    
        try {
            String sql = "SELECT chitiethoadon.MaSP, sanpham.TenSP, chitiethoadon.SoLuong, "
            + "CASE WHEN chitiethoadon.GiaDaGiam > 0 THEN chitiethoadon.GiaDaGiam ELSE chitiethoadon.GiaChuaGiam END AS GiaBan "
            + "FROM chitiethoadon INNER JOIN sanpham ON chitiethoadon.MaSP = sanpham.MaSP";
            conn = DB.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
    
            while (rs.next()) {
                int maSP = rs.getInt("MaSP");
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                double donGia = rs.getDouble("GiaBan");
                long dg = (long) donGia;
                model.addRow(new Object[] {maSP, tenSP, soLuong, dg});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return model;
    }
    public static DefaultTableModel getProductDetailsByMonth(String monthYear) throws SQLException, ClassNotFoundException {
        String sql = "SELECT cthd.MaSP, sp.TenSP, cthd.SoLuong, " 
                   + "CASE WHEN cthd.GiaDaGiam > 0 THEN cthd.GiaDaGiam ELSE cthd.GiaChuaGiam END AS GiaBan " 
                   + "FROM chitiethoadon cthd " 
                   + "INNER JOIN sanpham sp ON cthd.MaSP = sp.MaSP " 
                   + "INNER JOIN hoadon hd ON cthd.MaHD = hd.MaHD " 
                   + "WHERE DATE_FORMAT(hd.NgayTao, '%Y-%m') = ?";
    
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel(new String[] {"Mã SP", "Tên SP", "Số lượng", "Giá bán/cái"}, 0);
    
        try {
            conn = DB.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, monthYear);
            rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int productId = rs.getInt("MaSP");
                String productName = rs.getString("TenSP");
                int quantity = rs.getInt("SoLuong");
                int price = rs.getInt("GiaBan");
                model.addRow(new Object[] {productId, productName, quantity, price});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    
        return model;
    }
    
    
    public static ArrayList<String> getListMonthYear() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<String>();
    
        String sql = "SELECT DISTINCT DATE_FORMAT(NgayTao, '%Y-%m') AS Thang FROM hoadon";
    
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
    
        try {
            conn = DB.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
    
            while (rs.next()) {
                String monthYear = rs.getString("Thang");
                list.add(monthYear);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    
        return list;
    }
    
    
}
