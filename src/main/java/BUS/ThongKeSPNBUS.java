package BUS;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.JFreeChart;


import DAO.ThongKeSPNDAO;

public class ThongKeSPNBUS {
    public static JFreeChart createChart() throws SQLException, ClassNotFoundException {
        return ThongKeSPNDAO.createChart();
    }
    public static DefaultTableModel getSPN() throws SQLException, ClassNotFoundException {
        return ThongKeSPNDAO.getSPN();
    }
    public static ArrayList<String> getListMonthYear() throws SQLException, ClassNotFoundException {
        return ThongKeSPNDAO.getListMonthYear();
    }
    public static DefaultTableModel getProductDetailsByMonth(String monthYear) throws SQLException, ClassNotFoundException {
        return ThongKeSPNDAO.getProductDetailsByMonth(monthYear);
    }
}
