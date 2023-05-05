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

public class ThongKeDTDAO {
    public static ArrayList<String> getListMonthYear() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<String>();
    
        String sql = "SELECT DISTINCT DATE_FORMAT(NgayTao, '%Y-%m') AS Thang FROM hoadon UNION SELECT DISTINCT DATE_FORMAT(NgayTao, '%Y-%m') AS Thang FROM phieunhap ORDER BY Thang ASC" ;

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

        while (rs.next()) {
            String ngayTao = rs.getString("Thang");
            list.add(ngayTao);
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
    public static DefaultTableModel getTongTienTableModel() throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT SUM(TongTien) as TongTien1 FROM hoadon";
        String sql2 = "SELECT SUM(TongTien) as TongTien2 FROM phieunhap";
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Tổng tiền doanh thu", "Tổng tiền chi tiêu", "Lợi nhuận"}, 0);
    
        try {
            conn = DB.connect();
            pstmt1 = conn.prepareStatement(sql1);
            rs1 = pstmt1.executeQuery();
    
            double doanhThu = 0;
           
            if (rs1.next()) {
                doanhThu = rs1.getDouble("TongTien1");
            }
    
            pstmt2 = conn.prepareStatement(sql2);
            rs2 = pstmt2.executeQuery();
    
            double chiTieu = 0;
            
            if (rs2.next()) {
                chiTieu = rs2.getDouble("TongTien2");
                
            }
            long ct = (long) chiTieu;
            long dt = (long) doanhThu;
            double loiNhuan = doanhThu - chiTieu;
            long ln = (long) loiNhuan;
            model.addRow(new Object[]{dt, ct, ln});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs1 != null) {
                rs1.close();
            }
            if (pstmt1 != null) {
                pstmt1.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return model;
    }
    
    public static JFreeChart getDoanhThuChart() throws SQLException, ClassNotFoundException {
        String sql = "SELECT MONTH(NgayTao) AS Thang, YEAR(NgayTao) AS Nam, SUM(TongTien) AS DoanhThu "
                + "FROM (SELECT NgayTao, TongTien FROM hoadon UNION SELECT NgayTao, TongTien FROM phieunhap) as TongTien "
                + "GROUP BY MONTH(NgayTao), YEAR(NgayTao) "
                + "ORDER BY Nam, Thang";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {
            conn = DB.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
    
            while (rs.next()) {
                String thang = String.format("%02d", rs.getInt("Thang"));
                String nam = Integer.toString(rs.getInt("Nam"));
                double doanhThu = rs.getDouble("DoanhThu");
                long dt = (long) doanhThu;
                String key = thang + "/" + nam;
                dataset.setValue(dt, "Doanh thu", key);
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
        JFreeChart chart = ChartFactory.createBarChart("Thống Kê Doanh Thu", "Tháng/Năm", "Tổng tiền", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLACK);
            return chart;
    }

    public static DefaultTableModel getDTByMonth(String month) throws SQLException, ClassNotFoundException {
        String sql1 = "SELECT TongTien as TongTien1 FROM hoadon WHERE DATE_FORMAT(NgayTao, '%Y-%m') = ?";
        String sql2 = "SELECT TongTien as TongTien2 FROM phieunhap WHERE DATE_FORMAT(NgayTao, '%Y-%m') = ?";
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Tổng tiền doanh thu", "Tổng tiền chi tiêu", "Lợi nhuận"}, 0);
    
        try {
            conn = DB.connect();
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, month);
            rs1 = pstmt1.executeQuery();
    
            double doanhThu = 0;
           
            if (rs1.next()) {
                doanhThu = rs1.getDouble("TongTien1");
            }
    
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, month);
            rs2 = pstmt2.executeQuery();
    
            double chiTieu = 0;
            
            if (rs2.next()) {
                chiTieu = rs2.getDouble("TongTien2");
            }
            long ct = (long) chiTieu;
            long dt = (long) doanhThu;
            double loiNhuan = doanhThu - chiTieu;
            long ln = (long) loiNhuan;
            model.addRow(new Object[]{dt, ct, ln});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs1 != null) {
                rs1.close();
            }
            if (pstmt1 != null) {
                pstmt1.close();
            }
            if (rs2 != null) {
                rs2.close();
            }
            if (pstmt2 != null) {
                pstmt2.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return model;
    }
    
    
}
