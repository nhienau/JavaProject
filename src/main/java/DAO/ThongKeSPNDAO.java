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
public class ThongKeSPNDAO {
    public static JFreeChart createChart() throws SQLException, ClassNotFoundException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DB.connect();
            String sql = "SELECT MaSP, SUM(SL) FROM chitietphieunhap GROUP BY MaSP";
            st = con.createStatement();
            rs = st.executeQuery(sql);
    
            while (rs.next()) {
                String MaSP = rs.getString("MaSP");
                int SoLuong = rs.getInt("SUM(SL)");
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
            "Thống kê số lượng sản phẩm nhập về cửa hàng",
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
    public static DefaultTableModel getSPN() throws SQLException, ClassNotFoundException {
        
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
    
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Số lượng");
        model.addColumn("Giá nhập/cái");
    
        try {
            String sql = "SELECT chitietphieunhap.MaSP, sanpham.TenSP, chitietphieunhap.SL, chitietphieunhap.DonGia "
            + "FROM chitietphieunhap INNER JOIN sanpham ON chitietphieunhap.MaSP = sanpham.MaSP";
            conn = DB.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
    
            while (rs.next()) {
                int maSP = rs.getInt("MaSP");
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SL");
                double donGia = rs.getDouble("DONGIA");
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
        String sql = "SELECT ctpn.MaSP, sp.TenSP, ctpn.SL, ctpn.DonGia "
        + "FROM chitietphieunhap ctpn "
        + "INNER JOIN sanpham sp ON ctpn.MaSP = sp.MaSP "
        + "INNER JOIN phieunhap pn ON ctpn.MaPN = pn.MaPN "
        + "WHERE DATE_FORMAT(pn.NgayTao, '%Y-%m') = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel(new String[] {"Mã SP", "Tên SP", "Số lượng", "Giá Nhập/cái"}, 0);
    
        try {
            conn = DB.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, monthYear);
            rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int maSP = rs.getInt("MaSP");
                String tenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SL");
                double donGia = rs.getDouble("DONGIA");
                long dg = (long) donGia;
                model.addRow(new Object[] {maSP, tenSP, soLuong, dg});
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
    
        String sql = "SELECT DISTINCT DATE_FORMAT(NgayTao, '%Y-%m') AS Thang FROM phieunhap";
    
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
