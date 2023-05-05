package DAO;


import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;


public class ThongKeKHDAO {
    public static JFreeChart createChart() throws SQLException, ClassNotFoundException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DB.connect();
            String sql = "SELECT MaKH, COUNT(*) FROM hoadon GROUP BY MaKH";
            st = con.createStatement();
            rs = st.executeQuery(sql);
    
            while (rs.next()) {
                String MaKH = rs.getString("MaKH");
                int SoLuongHD = rs.getInt("COUNT(*)");
                dataset.addValue(SoLuongHD, "Số lượng", MaKH);
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
            "Thống kê số lượng khách hàng đã mua",
            "Mã khách hàng",
            "Số lượng hóa đơn",
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
    public static DefaultTableModel getCustomerStatistics() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Mã khách hàng");
        model.addColumn("Tên khách hàng");
        model.addColumn("Số lần mua");
        
        try {
            conn = DB.connect();
            stmt = conn.createStatement();
            String sql = "SELECT h.MaKH, k.TenKH, COUNT(*) AS SoLanMua " +
                         "FROM hoadon h JOIN khachhang k ON h.MaKH = k.MaKH " +
                         "GROUP BY h.MaKH, k.TenKH " +
                         "ORDER BY SoLanMua DESC";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                String MaKH = rs.getString("MaKH");
                String TenKH = rs.getString("TenKH");
                int SoLanMua = rs.getInt("SoLanMua");
                model.addRow(new Object[]{MaKH, TenKH, SoLanMua});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
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
    public static DefaultTableModel getCustomerDetailsByMonth(String monthYear) throws SQLException, ClassNotFoundException {
        String sql = "SELECT kh.MaKH, kh.TenKH, COUNT(*) as 'SoLanMua'"
                   + "FROM hoadon hd "
                   + "INNER JOIN khachhang kh ON hd.MaKH = kh.MaKH "
                   + "WHERE DATE_FORMAT(hd.NgayTao, '%Y-%m') = ?"
                   + "GROUP BY kh.MaKH";
    
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel(new String[] {"Mã KH", "Tên KH", "Số lần mua"}, 0);
    
        try {
            conn = DB.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, monthYear);
            rs = pstmt.executeQuery();
    
            while (rs.next()) {
                int customerId = rs.getInt("MaKH");
                String customerName = rs.getString("TenKH");
                int purchaseCount = rs.getInt("SoLanMua");
                model.addRow(new Object[] {customerId, customerName, purchaseCount});
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
    
    
    
}
