package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDon;
import DTO.KhachHang;
import GUI.HoaDonJPanel;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class HoaDonBUS {
    private static HoaDonDAO hdDAO;
    public HoaDonBUS(){
        hdDAO = new HoaDonDAO();
    }
    public List<HoaDon> takeAll() throws ClassNotFoundException, SQLException{
        return hdDAO.takeAll();
    }
    public List<HoaDon> takeAllDeleted() throws ClassNotFoundException, SQLException{
        return hdDAO.takeAllDeleted();
    }
    public List<HoaDon> searchHoaDon(String comboBoxValue, int searchValue) throws SQLException, ClassNotFoundException{
        return hdDAO.searchHoaDon(comboBoxValue, searchValue);
    }
    public List<HoaDon> searchHoaDon_Isdeleted(String comboBoxValue, int searchValue) throws SQLException, ClassNotFoundException{
        return hdDAO.searchHoaDon_Isdeleted(comboBoxValue, searchValue);
    }
    public void deleteHoaDon(int MaHD) throws ClassNotFoundException, SQLException{
        hdDAO.deleteHoaDon(MaHD);
        HoaDonJPanel hoaDonPanel = new HoaDonJPanel();
        hoaDonPanel.load_HoaDon();
    }
    public void KhoiPhucHoaDon(int MaHD) throws ClassNotFoundException, SQLException{
        hdDAO.khoiPhucHoaDon(MaHD);
        HoaDonJPanel hoaDonPanel = new HoaDonJPanel();
        hoaDonPanel.load_HoaDon();
    }
    public void loadHoaDon() throws ClassNotFoundException, SQLException {
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        List<HoaDon> hoaDonList = hoaDonDAO.takeAll();
        HoaDonJPanel hoaDonPanel = new HoaDonJPanel();
        hoaDonPanel.reloadTableData(hoaDonList);
    }
    public KhachHang getCustomerByMaKH(int maKH) throws SQLException, ClassNotFoundException {
        return hdDAO.getCustomerByMaKH(maKH);
    }
    public static DefaultTableModel getSPByMaHD(int maHD) throws SQLException, ClassNotFoundException {
        return hdDAO.getSPByMaHD(maHD);
    }
    public void exportToPDF(JPanel panel, String filePath) {
        hdDAO.exportToPDF(panel, filePath);
    }
    
    
}
