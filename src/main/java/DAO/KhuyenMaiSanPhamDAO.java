/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuyenMaiSanPham;
import DTO.SanPham;
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
public class KhuyenMaiSanPhamDAO {
    private DB db;

    public KhuyenMaiSanPhamDAO() {
    }
    
    public ArrayList<KhuyenMaiSanPham> getAllKMSP() throws ClassNotFoundException, SQLException
    {
        String sql="select * from khuyenmaisanpham";
        ArrayList<KhuyenMaiSanPham> temp=new ArrayList<KhuyenMaiSanPham>();
        Connection conn=DB.connect();
        if(conn!=null)
        {
            Statement smt=conn.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next())
            {
                KhuyenMaiSanPham kmsp=new KhuyenMaiSanPham();
                kmsp.setMaKMSP(rs.getInt("MAKMSP"));
                kmsp.setMaSP(rs.getInt("MaSP"));
                kmsp.setNgayBatDau(rs.getTimestamp("NGAYBATDAU"));
                kmsp.setNgayKetThuc(rs.getTimestamp("NGAYKETTHUC"));
                kmsp.setPhanTramGiam(rs.getFloat("PHANTRAMGIAM"));
                kmsp.setSoTienGiam(rs.getDouble("SOTIENGIAM"));
                kmsp.setTongLuotApDung(rs.getInt("TongLuotApDung"));
                kmsp.setTongLuotDaDung(rs.getInt("TongLuotDaDung"));
                kmsp.setIsDeleted(rs.getInt("IsDeleted"));
                temp.add(kmsp);
            }
        }
        conn.close();
        return temp;
    }
    
    public boolean addKhuyenMaiSanPham(KhuyenMaiSanPham kmsp) throws ClassNotFoundException, SQLException
    {
        String sql="insert into khuyenmaisanpham(MaSP,NGAYBATDAU,NGAYKETTHUC,PHANTRAMGIAM,SOTIENGIAM,TongLuotApDung,TongLuotDaDung,IsDeleted) value(?,?,?,?,?,?,?,?)";
        Connection conn=DB.connect();
        boolean result=false;
        if(conn!=null)
        {
            PreparedStatement stm=conn.prepareStatement(sql);
            stm.setInt(1, kmsp.getMaSP());
            stm.setTimestamp(2, kmsp.getNgayBatDau());
            stm.setTimestamp(3, kmsp.getNgayKetThuc());
            stm.setFloat(4, kmsp.getPhanTramGiam());
            stm.setDouble(5,kmsp.getSoTienGiam());
            stm.setInt(6, kmsp.getTongLuotApDung());
            stm.setInt(7, kmsp.getTongLuotDaDung());
            stm.setInt(8, kmsp.getIsDeleted());
            if (stm.executeUpdate()>=1)
                result = true;
        }
        conn.close();
        return result;
    }
    
    public ArrayList<String> getMaSP() throws ClassNotFoundException, SQLException
    {
        String sql="select MaSP from sanpham where IsDeleted=0";
        ArrayList<String> temp=new ArrayList<String>();
        Connection conn=DB.connect();
        if(conn!=null)
        {
            Statement smt=conn.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next())
            {
                temp.add(rs.getString("MaSP"));
            }
        }
        conn.close();
        return temp;
    }
    
    public ArrayList<String> getMaSPFromKMSP() throws ClassNotFoundException, SQLException
    {
        String sql="select MaSP from khuyenmaisanpham where IsDeleted=0";
        ArrayList<String> temp=new ArrayList<String>();
        Connection conn=DB.connect();
        if(conn!=null)
        {
            Statement smt=conn.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next())
            {
                temp.add(rs.getString("MaSP"));
            }
        }
        conn.close();
        return temp;
    }
    
    public boolean deleteKmsp(int id) throws SQLException, ClassNotFoundException
    {
        String sql="update khuyenmaisanpham set IsDeleted = ? Where MAKMSP = ?";
        Connection conn=DB.connect();
        boolean result=false;
        if(conn!=null)
        {
            PreparedStatement smt=conn.prepareStatement(sql);
            smt.setInt(1, 1);
            smt.setInt(2, id);
            result=smt.executeUpdate()>0;
        }
        return result;
    }
    
    public boolean updateKmsp(KhuyenMaiSanPham kmsp) throws SQLException, ClassNotFoundException
    {
        String sql="update khuyenmaisanpham set MaSP=?,NGAYBATDAU=?,NGAYKETTHUC=?,PHANTRAMGIAM=?,SOTIENGIAM=?,TongLuotApDung=?,TongLuotDaDung=?,IsDeleted=? where MAKMSP=?";
        Connection conn=DB.connect();
        boolean result=false;
        if(conn!=null)
        {
            PreparedStatement stm=conn.prepareStatement(sql);
            stm.setInt(1, kmsp.getMaSP());
            stm.setTimestamp(2, kmsp.getNgayBatDau());
            stm.setTimestamp(3, kmsp.getNgayKetThuc());
            stm.setFloat(4, kmsp.getPhanTramGiam());
            stm.setDouble(5,kmsp.getSoTienGiam());
            stm.setInt(6, kmsp.getTongLuotApDung());
            stm.setInt(7, kmsp.getTongLuotDaDung());
            stm.setInt(8, kmsp.getIsDeleted());
            stm.setInt(9, kmsp.getMaKMSP());
            result=stm.executeUpdate()>0;
            //System.out.print(kmsp.getNgayKetThuc());
        }
        return result;
    }
    
    public ArrayList<KhuyenMaiSanPham> searchKmsp(String dk, String input,int dk1) throws ClassNotFoundException, SQLException
    {
        ArrayList<KhuyenMaiSanPham> arr=new ArrayList<KhuyenMaiSanPham>();
        Connection conn=DB.connect();
        if(conn!=null)
        {
            Statement stm=conn.createStatement();
            ResultSet rs;
            if(dk=="NgayBatDau"||dk=="NgayKetThuc"){
                if(dk1==0)
                {
                    String sql="select * from khuyenmaisanpham where "+dk+" >= '"+input+"' and IsDeleted='0'";
                    rs=stm.executeQuery(sql);
                }
                else
                {
                    String sql="select * from khuyenmaisanpham where "+dk+" <= '"+input+"' and IsDeleted='0'";
                    rs=stm.executeQuery(sql);
                }
            }
            else
            {
                String sql="select * from khuyenmaisanpham where "+dk+" like '%"+input+"%' and IsDeleted='0'";
                rs=stm.executeQuery(sql);
            }
            while(rs.next())
            {
                KhuyenMaiSanPham kmsp=new KhuyenMaiSanPham();
                kmsp.setMaKMSP(rs.getInt("MaKMSP"));
                kmsp.setNgayBatDau(rs.getTimestamp("NgayBatDau"));
                kmsp.setNgayKetThuc(rs.getTimestamp("NgayKetThuc"));
                kmsp.setPhanTramGiam(rs.getFloat("PhanTramGiam"));
                kmsp.setSoTienGiam(rs.getDouble("SoTienGiam"));
                kmsp.setTongLuotApDung(rs.getInt("TongLuotApDung"));
                kmsp.setTongLuotDaDung(rs.getInt("TongLuotDaDung"));
                kmsp.setMaSP(rs.getInt("MaSP"));
                kmsp.setIsDeleted(rs.getInt("IsDeleted"));
                arr.add(kmsp);
            }
        }
        return arr;
    }
}
