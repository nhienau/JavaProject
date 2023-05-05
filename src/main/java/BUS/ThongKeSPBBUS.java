package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.JFreeChart;
import DAO.ThongKeSPBDAO;

public class ThongKeSPBBUS {
    public static JFreeChart createChart() throws SQLException, ClassNotFoundException {
        return ThongKeSPBDAO.createChart();
    }
    public static DefaultTableModel getSPB() throws SQLException, ClassNotFoundException {
        return ThongKeSPBDAO.getSPB();
    }
    public static ArrayList<String> getListMonthYear() throws SQLException, ClassNotFoundException {
        return ThongKeSPBDAO.getListMonthYear();
    }
    public static DefaultTableModel getProductDetailsByMonth(String monthYear) throws SQLException, ClassNotFoundException {
        return ThongKeSPBDAO.getProductDetailsByMonth(monthYear);
    }
    
    
}
