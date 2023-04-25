/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Coupon;
import java.sql.*;
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
        System.out.print(arr.size());
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
}
