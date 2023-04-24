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
    private testDB db=new testDB();

    public CouponDAO(testDB db) {
        this.db = db;
    }
    
    public CouponDAO() {
    }
    
    public ArrayList<Coupon> getAllCoupon() throws SQLException
    {
        ArrayList<Coupon> arr=new ArrayList<>();
        if(db.getConnect()!=null)
        {
            try{
                String sql = "Select * from Coupon";
                Statement stmt = db.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    Coupon cpon=new Coupon();
                    cpon.setMaCP(rs.getInt("MaCP"));
                    cpon.setCode(rs.getString("Code"));
                    cpon.setMaKMHD(rs.getInt("MaKMHD"));
                    cpon.setTongLuotApDung(rs.getInt("TongLuotApDung"));
                    cpon.setTongLuotDaDung(rs.getInt("TongLuotDaDung"));
                    arr.add(cpon);
                }
            }catch (SQLException ex) {
                System.out.println(ex);
            } finally {
                db.conn.close();
            }
        }
        System.out.print(arr.size());
        return arr;
    }
    
    public boolean addCoupon(Coupon cp) throws SQLException {
        boolean result = false;
        if(db.getConnect()!=null) {
            try {
                    String sql = "Insert into Coupon values(?,?,?,?,?,?)";
                    PreparedStatement stmt = db.conn.prepareStatement(sql);
                    stmt.setInt(1, cp.getMaCP());
                    stmt.setString(2, cp.getCode());
                    stmt.setInt(3, cp.getTongLuotApDung());
                    stmt.setInt(4, cp.getTongLuotDaDung());
                    stmt.setInt(5, cp.getMaKMHD());
                    stmt.setInt(6, cp.getIsDeleted());
                    if (stmt.executeUpdate()>=1)
                        result = true;
                } catch (SQLException ex) {
            System.out.println(ex);
            } finally{
            db.conn.close(); } }
            return result;
    }
}
