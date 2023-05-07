/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuNhap;
import DTO.PhieuNhap;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tam
 */
public class NhapHangDAO {

    private DB DB;

    public NhapHangDAO() {

    }

    public List<PhieuNhap> getAll() throws SQLException, ClassNotFoundException {
        List<PhieuNhap> pnList = new ArrayList<>();
        Connection conn = DB.connect();
        String sql = "Select * from phieunhap where IsDeleted = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, 0);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            pnList.add(new PhieuNhap(rs.getInt("MaPN"), rs.getDate("NgayTao"), rs.getString("TongTien"), rs.getInt("MaNV"), rs.getInt("IsDeleted")));
        }
        return pnList;
    }

    public int themNhapHang(PhieuNhap pn, String tongTien) throws SQLException, ClassNotFoundException {
        Connection conn = DB.connect();
        int rs;

        // Câu lệnh INSERT cho bảng PhieuNhap
        String sqlPhieuNhap = "INSERT INTO phieunhap (NgayTao, TongTien, MaNV, IsDeleted) VALUES (?, ?, ?, ?)";
        PreparedStatement pstPhieuNhap = conn.prepareStatement(sqlPhieuNhap, Statement.RETURN_GENERATED_KEYS);
        pstPhieuNhap.setDate(1, new java.sql.Date(pn.getNgayTao().getTime()));
        pstPhieuNhap.setString(2, tongTien);
        pstPhieuNhap.setInt(3, pn.getMaNV());
        pstPhieuNhap.setInt(4, 0);

        rs = pstPhieuNhap.executeUpdate();
        return rs;
    }
    
  
//    public int themNhapHang(PhieuNhap pn, int tongTien) throws SQLException, ClassNotFoundException {
//        Connection conn = DB.connect();
//        int rs;
//
//        try {
//            conn.setAutoCommit(false); // Tắt chế độ tự động commit
//
//            // Câu lệnh INSERT cho bảng PhieuNhap
//            String sqlPhieuNhap = "INSERT INTO phieunhap (NgayTao, TongTien, MaNV, IsDeleted) VALUES (?, ?, ?, ?)";
//            PreparedStatement pstPhieuNhap = conn.prepareStatement(sqlPhieuNhap, Statement.RETURN_GENERATED_KEYS);
//            pstPhieuNhap.setDate(1, new java.sql.Date(pn.getNgayTao().getTime()));
//            pstPhieuNhap.setInt(2, tongTien);
//            pstPhieuNhap.setInt(3, 3);
//            pstPhieuNhap.setInt(4, 0);
//
//            rs = pstPhieuNhap.executeUpdate();
//
//            if (rs > 0) {
//                // Lấy mã phiếu nhập mới được tạo
//                ResultSet generatedKeys = pstPhieuNhap.getGeneratedKeys();
//                if (generatedKeys.next()) {
//                    int maPhieuNhap = generatedKeys.getInt(1);
//
//                    // Câu lệnh INSERT cho bảng ChiTietPhieuNhap
//                    String sqlChiTietPhieuNhap = "INSERT INTO chitietphieunhap (MaSanPham, MaPhieuNhap, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
//                    PreparedStatement pstChiTietPhieuNhap = conn.prepareStatement(sqlChiTietPhieuNhap);
//
//                    // Lặp qua danh sách chi tiết phiếu nhập và thêm vào câu lệnh INSERT
//                    for (ChiTietPhieuNhap ctpn : pn.getChiTietPhieuNhapList()) {
//                        pstChiTietPhieuNhap.setInt(1, ctpn.getMaSanPham());
//                        pstChiTietPhieuNhap.setInt(2, maPhieuNhap);
//                        pstChiTietPhieuNhap.setInt(3, ctpn.getSoLuong());
//                        pstChiTietPhieuNhap.setDouble(4, ctpn.getDonGia());
//                        pstChiTietPhieuNhap.addBatch(); // Thêm vào batch để thực thi nhiều lệnh INSERT cùng lúc
//                    }
//
//                    // Thực thi tất cả câu lệnh INSERT trong batch
//                    int[] batchResult = pstChiTietPhieuNhap.executeBatch();
//
//                    // Kiểm tra kết quả thực thi của từng câu lệnh INSERT trong batch
//                    for (int result : batchResult) {
//                        if (result <= 0) {
//                            rs = 0; // Có lỗi xảy ra trong quá trình thêm ChiTietPhieuNhap
//                            break;
//                        }
//                    }
//                } else {
//                    rs = 0; // Không lấy được mã phiếu nhập mới
//                }
//            }
//
//            if (rs > 0) {
//                conn.commit(); // Lưu thay đổi vào cơ sở dữ liệu
//            } else {
//                conn.rollback(); // Hoàn tác thay đổi do có lỗi xảy ra
//            }
//        } finally {
//            conn.setAutoCommit(true); // Bật lại chế độ tự động commit
//
//            if (pstChiTietPhieuNhap != null) {
//                pstChiTietPhieuNhap.close();
//            }
//            if (pstPhieuNhap != null) {
//                pstPhieuNhap.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//        return rs;
//    }

    public List<PhieuNhap> timKiemSanPham(String searchInput) throws SQLException, ClassNotFoundException {
        List<PhieuNhap> pnList = new ArrayList<>();
        try ( Connection conn = new DB().connect()) {
            String sql = "Select * from phieunhap where (MaNV like ? ) and IsDeleted = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, searchInput);
            pst.setInt(2, 0);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pnList.add(new PhieuNhap(rs.getInt("MaPN"),
                          rs.getDate("NgayTao"), rs.getString("TongTien"),
                          rs.getInt("MaNV"),
                          rs.getInt("IsDeleted")));
            }
        } catch (Exception e) {
            System.out.println("Tìm kiếm fail");
        }
        return pnList;
    }

}
