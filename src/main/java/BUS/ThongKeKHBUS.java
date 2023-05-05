package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.JFreeChart;

import DAO.ThongKeKHDAO;

public class ThongKeKHBUS {
    public static JFreeChart createChart() throws SQLException, ClassNotFoundException {
        return ThongKeKHDAO.createChart();
    }
    public static DefaultTableModel getCustomerStatistics() throws SQLException, ClassNotFoundException {
        return ThongKeKHDAO.getCustomerStatistics();
    }
    public static ArrayList<String> getListMonthYear() throws SQLException, ClassNotFoundException {
        return ThongKeKHDAO.getListMonthYear();
    }
    public static DefaultTableModel getCustomerDetailsByMonth(String monthYear) throws SQLException, ClassNotFoundException {
        return ThongKeKHDAO.getCustomerDetailsByMonth(monthYear);
    }
    
}
