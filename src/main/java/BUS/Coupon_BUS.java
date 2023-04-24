/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CouponDAO;
import DTO.Coupon;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Coupon_BUS {
    CouponDAO cpDAO=new CouponDAO();
    public ArrayList<Coupon> getAllCoupon() throws SQLException
    {
        return cpDAO.getAllCoupon();
    }
}
