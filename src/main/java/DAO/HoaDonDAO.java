package DAO;

import DTO.HoaDon;
import DTO.KhachHang;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
    public List<HoaDon> takeAllDeleted() throws ClassNotFoundException, SQLException {
        Connection conn = DB.connect();
        String sql = "SELECT * FROM hoadon WHERE IsDeleted = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, 1);
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
    public List<HoaDon> searchHoaDon_Isdeleted(String comboBoxValue, int searchValue) {
        List<HoaDon> hdList = new ArrayList<>();
        try {
            Connection con = DB.connect();
            String sql = "SELECT * FROM hoadon WHERE " + comboBoxValue + "=? AND IsDeleted=1";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, searchValue);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                hdList.add(new HoaDon(rs.getInt("MaHD"), rs.getDate("NgayTao"), rs.getDouble("TongTien"), rs.getInt("MaKH"), rs.getInt("MaNV"), rs.getInt("MaKMHD"), 1));
            }
            con.close();
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
    public void khoiPhucHoaDon(int maHD) {
        try {
            Connection con = DB.connect();
            String sql = "UPDATE hoadon SET IsDeleted=0 WHERE MaHD=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maHD);
            pst.executeUpdate();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<HoaDon> getActiveHoaDonList(int MaHD) throws ClassNotFoundException, SQLException {
        Connection con = DB.connect();
        String sql = "SELECT * FROM hoadon WHERE IsDeleted=0";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<HoaDon> hdList = new ArrayList<>();
        while (rs.next()) {
            HoaDon hd = new HoaDon(MaHD, null, MaHD, MaHD, MaHD, MaHD, MaHD);
            hdList.add(hd);
        }
        con.close();
        return hdList;
    }
    public KhachHang getCustomerByMaKH(int maKH) throws SQLException, ClassNotFoundException {
        String sql = "SELECT TenKH, SDT FROM khachhang WHERE MaKH = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        KhachHang khachHang = null;
        try {
            conn = DB.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maKH);
            rs = pstmt.executeQuery();
    
            if (rs.next()) {
                String tenKH = rs.getString("TenKH");
                String sdt = rs.getString("SDT");
                khachHang = new KhachHang(maKH, tenKH, sdt, null,0,0,0);
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
        return khachHang;
    }

    public DefaultTableModel getSPByMaHD(int maHD) throws SQLException, ClassNotFoundException {
        String sql = "SELECT sanpham.TenSP, chitiethoadon.SoLuong, " +
                    "CASE WHEN chitiethoadon.GiaDaGiam > 0 THEN chitiethoadon.GiaDaGiam ELSE chitiethoadon.GiaChuaGiam END AS GiaDaGiam " +
                    "FROM chitiethoadon " +
                    "INNER JOIN sanpham ON chitiethoadon.MaSP = sanpham.MaSP " +
                    "WHERE chitiethoadon.MaHD = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultTableModel model = new DefaultTableModel(new String[] {"STT", "Tên SP", "SL", "Giá bán/cái", "Thành Tièn"}, 0);
        try {
            conn = DB.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, maHD);
            rs = pstmt.executeQuery();
            int stt =1;
            while (rs.next()) {
                String productId = rs.getString("TenSP");
                int productName = rs.getInt("SoLuong");
                double quantity = rs.getInt("GiaDaGiam");
                long giaban = (long) quantity;
                //int price = rs.getInt("GiaBan");
                double sl = (double) productName;
                double price = (sl*quantity);
                long tongtien = (long) price;
                model.addRow(new Object[] { stt++,productId, productName, giaban, tongtien});
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
    public void exportToPDF(JPanel panel, String filePath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Font font = FontFactory.getFont("C:/Users/Admin/OneDrive/Máy tính/Java/MoHinh3lop/arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            // Get data from the table in the panel
            Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG",font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            String tenKH = ((JTextField) panel.getComponent(3)).getText();
            String sdtKH = ((JTextField) panel.getComponent(5)).getText();
            document.add(new Paragraph(" "));
            Paragraph customerInfo = new Paragraph("Tên khách hàng: " + tenKH + "    SĐT: " + sdtKH,font);
            document.add(customerInfo);
            document.add(new Paragraph(" "));
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = formatter.format(date);
            Paragraph dateParagraph = new Paragraph("Ngày tạo hóa đơn: " + strDate, font);
            document.add(dateParagraph);
            document.add(new Paragraph(" "));

            JScrollPane scrollPane = (JScrollPane) panel.getComponent(0); // assuming table is the third component in the panel
            JTable  table = (JTable) scrollPane.getViewport().getView();
            
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int numColumns = model.getColumnCount();
            int numRows = model.getRowCount();
            // Create new table
            PdfPTable pdfTable = new PdfPTable(numColumns);
            // Add header row
            for (int i = 0; i < numColumns; i++) {
                pdfTable.addCell(model.getColumnName(i));
            }
            // Add data rows
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                    pdfTable.addCell(model.getValueAt(i, j).toString());
                }
            }
            document.add(pdfTable);
            document.add(new Paragraph(" "));
            String tongtien = ((JTextField) panel.getComponent(7)).getText();
            Paragraph tien = new Paragraph("Tổng Tiền: " + tongtien,font);
            Paragraph paraChuKy = new Paragraph();
            paraChuKy.setSpacingBefore(25);
            paraChuKy.setFont(font);
            paraChuKy.add("                    Người mua hàng");
            paraChuKy.add("                                                                   " );
            paraChuKy.add("Người bán hàng");
           
            paraChuKy.add("                                      (Ký,ghi rõ họ tên)");
            paraChuKy.add("                                                                  " );
            paraChuKy.add("(Ký,ghi rõ họ tên)");    
            document.add(tien);
            document.add(paraChuKy);
            document.close();
            System.out.println("PDF exported successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}