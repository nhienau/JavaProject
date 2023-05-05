package DAO;
import DTO.KhachHang;
import DTO.NhanVien;
import DTO.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateHDDAO {
    private Connection conn;
    public UpdateHDDAO(){
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
    public ArrayList<NhanVien> takeAllNV(){
        try {
             conn = DB.connect();
            String sql = "select * from nhanvien where IsDeleted = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,0);
            ResultSet rs = pst.executeQuery();
            ArrayList<NhanVien> nvList = new ArrayList<>();
            while(rs.next()){
                nvList.add(new NhanVien(rs.getInt("MaNV"), rs.getString("TenNV"), rs.getString("SDT"),rs.getString("Email"), rs.getDate("NgaySinh"), rs.getString("TaiKhoan"),rs.getString("MatKhau"), rs.getInt("MaCV"), rs.getInt("IsDeleted")));
            }
            return nvList;
        } catch (Exception e) {
            return null;
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }  
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
    

    public ArrayList<KhachHang> takeAllKH() {
        try {
             conn = DB.connect();
            String sql = "select * from khachhang where IsDeleted = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,0);
            ResultSet rs = pst.executeQuery();
            ArrayList<KhachHang> khList = new ArrayList<>();
            while(rs.next()){            
                khList.add(new KhachHang(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getString("SDT"),rs.getDate("NgaySinh"), rs.getInt("DiemHienTai"), rs.getInt("TongDiem"), rs.getInt("IsDeleted")));
                // KhachHang khachHang = new KhachHang();
                // khachHang.setMaHD(rs.getInt("MaHD"));
                // khachHang.setTenKH(rs.getString("TenKH"));
                // khList.add(khachHang);
                System.out.println(rs.getInt("MaKH"));                
            
            }
           
            return khList;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        finally{
            try {
                conn.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
        }
    }


    public boolean updateHoaDon(HoaDon hoaDon)  {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = DB.connect();
            String sql = "UPDATE hoadon SET MaKH=?, MaNV=?, MaKMHD =? WHERE MaHD=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, hoaDon.getMaKH());
            pst.setInt(2, hoaDon.getMaNV());
            if (hoaDon.getMaKMHD()==0)
            {pst.setNull(3, java.sql.Types.INTEGER);}
            else 
            {
                pst.setInt(3, hoaDon.getMaKMHD());
            }
            pst.setInt(4, hoaDon.getMaHD());
            System.out.println( hoaDon.getMaKH()+"," +hoaDon.getMaNV()+","+ hoaDon.getMaKMHD()+","+hoaDon.getMaHD() );
            int rowsUpdated = pst.executeUpdate();
            takeAll();
            return rowsUpdated > 0; // Trả về true nếu số dòng được cập nhật > 0
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
            finally {
               try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
               } catch (Exception e) {
                // TODO: handle exception
               }
            }
        }

       
    

 


}
