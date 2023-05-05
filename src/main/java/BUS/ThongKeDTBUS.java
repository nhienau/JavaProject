package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.JFreeChart;

import DAO.ThongKeDTDAO;

public class ThongKeDTBUS {
    public static ArrayList<String> getListMonthYear() throws SQLException, ClassNotFoundException {
        return ThongKeDTDAO.getListMonthYear();
    }
    public static DefaultTableModel getTongTien() throws SQLException, ClassNotFoundException {
        return ThongKeDTDAO.getTongTienTableModel();
    }
    public static JFreeChart createChart() throws SQLException, ClassNotFoundException {
        return ThongKeDTDAO.getDoanhThuChart();
    }
    public static DefaultTableModel getDTByMonth(String monthYear) throws SQLException, ClassNotFoundException {
        return ThongKeDTDAO.getDTByMonth(monthYear);
    }
}
