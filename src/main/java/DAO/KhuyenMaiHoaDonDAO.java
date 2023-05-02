/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuyenMaiHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiHoaDonDAO {
    private DB db;
    public ArrayList<KhuyenMaiHoaDon> getAllKmhd() throws SQLException, ClassNotFoundException
    {
        String sql="select * from khuyenmaihoadon";
        Connection conn=DB.connect();
        ArrayList<KhuyenMaiHoaDon> arr=new ArrayList<KhuyenMaiHoaDon>();
        if(conn!=null)
        {
            Statement stm=conn.createStatement();
            ResultSet rs=stm.executeQuery(sql);
            while(rs.next())
            {
                KhuyenMaiHoaDon kmhd=new KhuyenMaiHoaDon();
                kmhd.setMaKMHD(rs.getInt("MaKMHD"));
                kmhd.setDonHangToiThieu(rs.getDouble("DonHangToiThieu"));
                kmhd.setNgayBatDau(rs.getTimestamp("NgayBatDau"));
                kmhd.setNgayKetThuc(rs.getTimestamp("NgayKetThuc"));
                kmhd.setPhanTramGiam(rs.getFloat("PhanTramGiam"));
                kmhd.setTenKM(rs.getString("TenKM"));
                kmhd.setSoTienGiam(rs.getDouble("SoTienGiam"));
                kmhd.setTongLuotApDung(rs.getInt("TongLuotApDung"));
                kmhd.setTongLuotDaDung(rs.getInt("TongLuotDaDung"));
                kmhd.setIsDeleted(rs.getInt("IsDeleted"));
                arr.add(kmhd);
            }
        }
        return arr;
    }
    
    public boolean addKmhd(KhuyenMaiHoaDon kmhd) throws ClassNotFoundException, SQLException
    {
        String sql="insert into khuyenmaihoadon(TenKM,NgayBatDau,NgayKetThuc,PhanTramGiam,SoTienGiam,DonHangToiThieu,TongLuotApDung,TongLuotDaDung,IsDeleted) values(?,?,?,?,?,?,?,?,?)";
        Connection conn=DB.connect();
        boolean result=false;
        if(conn!=null)
        {
            PreparedStatement stm=conn.prepareStatement(sql);
            stm.setString(1, kmhd.getTenKM());
            stm.setTimestamp(2, kmhd.getNgayBatDau());
            stm.setTimestamp(3, kmhd.getNgayKetThuc());
            stm.setFloat(4, kmhd.getPhanTramGiam());
            stm.setDouble(5, kmhd.getSoTienGiam());
            stm.setDouble(6, kmhd.getDonHangToiThieu());
            stm.setInt(7, kmhd.getTongLuotApDung());
            stm.setInt(8, kmhd.getTongLuotDaDung());
            stm.setInt(9, 0);
            result=stm.executeUpdate()>0;
        }
        return result;
    }
    
    public boolean deleteKmhd(int id) throws ClassNotFoundException, SQLException
    {
        String sql="update khuyenmaihoadon set IsDeleted=? where MaKMHD=?";
        boolean result=false;
        Connection conn=DB.connect();
        if(conn!=null)
        {
            PreparedStatement stm=conn.prepareStatement(sql);
            stm.setInt(1, 1);
            stm.setInt(2, id);
            result=stm.executeUpdate()>0;
        }
        return result;
    }
    
    public boolean updateKmhd(KhuyenMaiHoaDon kmhd) throws ClassNotFoundException, SQLException
    {
        String sql="update khuyenmaihoadon set TenKM=?,NgayBatDau=?,NgayKetThuc=?,PhanTramGiam=?,SoTienGiam=?,DonHangToiThieu=?,TongLuotApDung=?,TongLuotDaDung=? where MaKMHD=?";
        boolean result=false;
        Connection conn=DB.connect();
        if(conn!=null)
        {
            PreparedStatement stm=conn.prepareStatement(sql);
            stm.setString(1, kmhd.getTenKM());
            stm.setTimestamp(2, kmhd.getNgayBatDau());
            stm.setTimestamp(3, kmhd.getNgayKetThuc());
            stm.setFloat(4, kmhd.getPhanTramGiam());
            stm.setDouble(5, kmhd.getSoTienGiam());
            stm.setDouble(6, kmhd.getDonHangToiThieu());
            stm.setInt(7, kmhd.getTongLuotApDung());
            stm.setInt(8, kmhd.getTongLuotDaDung());
            stm.setInt(9, kmhd.getMaKMHD());
            result=stm.executeUpdate()>0;
        }
        return result;
    }
    
    public ArrayList<KhuyenMaiHoaDon> searchKmhd(String dk, String input,int dk1) throws ClassNotFoundException, SQLException
    {
        ArrayList<KhuyenMaiHoaDon> arr=new ArrayList<KhuyenMaiHoaDon>();
        Connection conn=DB.connect();
        if(conn!=null)
        {
            Statement stm=conn.createStatement();
            ResultSet rs;
            if(dk=="NgayBatDau"||dk=="NgayKetThuc"){
                if(dk1==0)
                {
                    String sql="select * from khuyenmaihoadon where "+dk+" >= '"+input+"' and IsDeleted='0'";
                    rs=stm.executeQuery(sql);
                }
                else
                {
                    String sql="select * from khuyenmaihoadon where "+dk+" <= '"+input+"' and IsDeleted='0'";
                    rs=stm.executeQuery(sql);
                }
            }
            else
            {
                String sql="select * from khuyenmaihoadon where "+dk+" like '%"+input+"%' and IsDeleted='0'";
                rs=stm.executeQuery(sql);
            }
            while(rs.next())
            {
                KhuyenMaiHoaDon kmhd=new KhuyenMaiHoaDon();
                kmhd.setMaKMHD(rs.getInt("MaKMHD"));
                kmhd.setDonHangToiThieu(rs.getDouble("DonHangToiThieu"));
                kmhd.setNgayBatDau(rs.getTimestamp("NgayBatDau"));
                kmhd.setNgayKetThuc(rs.getTimestamp("NgayKetThuc"));
                kmhd.setPhanTramGiam(rs.getFloat("PhanTramGiam"));
                kmhd.setTenKM(rs.getString("TenKM"));
                kmhd.setSoTienGiam(rs.getDouble("SoTienGiam"));
                kmhd.setTongLuotApDung(rs.getInt("TongLuotApDung"));
                kmhd.setTongLuotDaDung(rs.getInt("TongLuotDaDung"));
                kmhd.setIsDeleted(rs.getInt("IsDeleted"));
                arr.add(kmhd);
            }
        }
        return arr;
    }
}
