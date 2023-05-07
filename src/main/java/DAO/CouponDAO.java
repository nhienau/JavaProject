/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Coupon;
import DTO.HoaDon;
import java.sql.*;
import static java.sql.Types.NULL;
import java.util.ArrayList;
/**
 *
 * @author ASUS
 */
public class CouponDAO {
    private DB db;

    public CouponDAO() {
    }
    
    
    public ArrayList<Coupon> getAllCoupon() throws SQLException, ClassNotFoundException
    {
        Connection conn=DB.connect();
        ArrayList<Coupon> arr=new ArrayList<>();
        if(conn!=null)
        {
            try{
                String sql = "Select * from Coupon";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Coupon cpon=new Coupon();
                    cpon.setMaCP(rs.getInt("MaCP"));
                    cpon.setCode(rs.getString("Code"));
                    cpon.setMaKMHD(rs.getInt("MaKMHD"));
                    cpon.setTongLuotApDung(rs.getInt("TongLuotApDung"));
                    cpon.setTongLuotDaDung(rs.getInt("TongLuotDaDung"));
                    cpon.setIsDeleted(rs.getInt("IsDeleted"));
                    arr.add(cpon);
                }
            }catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                conn.close();
            }
        }
        //System.out.print(arr.size());
        return arr;
    }
    
    public boolean addCoupon(Coupon cp) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn=db.connect();
        if(conn!=null) {
            try {
                    String sql = "Insert into Coupon(Code,TongLuotApDung,TongLuotDaDung,MaKMHD,IsDeleted) values(?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, cp.getCode());
                    stmt.setInt(2, cp.getTongLuotApDung());
                    stmt.setInt(3, cp.getTongLuotDaDung());
                    stmt.setInt(4, cp.getMaKMHD());
                    stmt.setInt(5, 0);
                    if (stmt.executeUpdate()>=1)
                        result = true;
                } catch (SQLException ex) {
            System.out.println(ex);
            } finally{
            conn.close(); } }
            return result;
    }
    
    public boolean deleteCoupon(int id) throws ClassNotFoundException, SQLException
    {
        Connection conn=db.connect();
        boolean result = false;
        if(conn!=null) {
            try {
                    String sql = "update Coupon set IsDeleted = ? where MaCP = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, 1);
                    stmt.setInt(2, id);
                    if (stmt.executeUpdate()>=1)
                        result = true;
                } catch (SQLException ex) {
            System.out.println(ex);
            } finally{
            conn.close(); } }
            return result;
    }
    
    public boolean hasCode(String cp) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection conn=db.connect();
        if(conn!=null) {
            try {
                    String sql = "Select * from Coupon where Code = '"+cp+"'";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while(rs.next())
                    {
                        result=true;
                    }
                } catch (SQLException ex) {
            System.out.println(ex);
            } finally{
            conn.close(); } }
            return result;
    }
    
    public boolean updateCoupon(Coupon cp) throws ClassNotFoundException, SQLException
    {
        Connection conn=db.connect();
        boolean result = false;
        if(conn!=null) {
            try {
                    String sql = "update Coupon set  Code = ? ,TongLuotApDung = ? ,TongLuotDaDung = ? ,MaKMHD = ? ,IsDeleted = ? where MaCP = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, cp.getCode());
                    stmt.setInt(2, cp.getTongLuotApDung());
                    stmt.setInt(3, cp.getTongLuotDaDung());
                    stmt.setInt(4, cp.getMaKMHD());
                    stmt.setInt(5, 0);
                    stmt.setInt(6, cp.getMaCP());
                    if (stmt.executeUpdate()>=1)
                        result = true;
                } catch (SQLException ex) {
            System.out.println(ex);
            } finally{
            conn.close(); } }
            return result;
    }
    
    public ArrayList<Coupon> searchCp(String dk, String input) throws ClassNotFoundException, SQLException
    {
        ArrayList<Coupon> arr=new ArrayList<Coupon>();
        Connection conn=DB.connect();
        if(conn!=null)
        {
            Statement stm=conn.createStatement();
            ResultSet rs;
            String sql="select * from coupon where "+dk+" like '%"+input+"%' and IsDeleted='0'";
            //PreparedStatement stm1=conn.prepareStatement(sql);
            //stm1.setString(1, input);
            rs=stm.executeQuery(sql);
            while(rs.next())
            {
                Coupon cp=new Coupon();
                cp.setMaCP(rs.getInt("MaCP"));
                cp.setTongLuotApDung(rs.getInt("TongLuotApDung"));
                cp.setTongLuotDaDung(rs.getInt("TongLuotDaDung"));
                cp.setMaKMHD(rs.getInt("MaKMHD"));
                cp.setCode(rs.getString("Code"));
                cp.setIsDeleted(rs.getInt("IsDeleted"));
                arr.add(cp);
            }
        }
        return arr;
    }
    
    public boolean addHD(HoaDon hd) throws ClassNotFoundException, SQLException
    {
        java.sql.Date sqlDate = new java.sql.Date(hd.getNgayTao().getTime());
        boolean result = false;
        Connection conn=db.connect();
        if(conn!=null) {
            try {
                    String sql = "Insert into hoadon(MaKH,MaNV,MaKMHD,NgayTao,TongTien,IsDeleted) values(?,?,?,?,?,?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    if(hd.getMaKMHD()!=NULL)
                    {
                        stmt.setInt(1, hd.getMaKH());
                        stmt.setInt(2, hd.getMaNV());
                        stmt.setInt(3, hd.getMaKMHD());
                        stmt.setDate(4, sqlDate);
                        stmt.setDouble(5, hd.getTongTien());
                        stmt.setInt(6, 0);
                    }
                    else
                    {
                        stmt.setInt(1, hd.getMaKH());
                        stmt.setInt(2, hd.getMaNV());
                        stmt.setNull(3, NULL);
                        stmt.setDate(4, sqlDate);
                        stmt.setDouble(5, hd.getTongTien());
                        stmt.setInt(6, 0);
                    }
                    if (stmt.executeUpdate()>=1)
                        result = true;
                } catch (SQLException ex) {
            System.out.println(ex);
            } finally{
            conn.close(); } }
            return result;
    }
    
    public HoaDon searchNewestHD() throws SQLException, ClassNotFoundException
    {
        HoaDon hd=new HoaDon();
        Connection conn=DB.connect();
        if(conn!=null)
        {
            Statement stm=conn.createStatement();
            ResultSet rs;
            String sql="SELECT * from hoadon ORDER BY MaHD DESC LIMIT 1";
            rs=stm.executeQuery(sql);
            while(rs.next())
            {
                hd.setMaHD(rs.getInt("MaHD"));
            }
        }
        return hd;
    }
}
