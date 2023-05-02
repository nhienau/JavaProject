/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CouponDAO;
import DTO.Coupon;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author ASUS
 */
public class Coupon_BUS {
    CouponDAO cpDAO=new CouponDAO();
    public ArrayList<Coupon> getAllCouponNotDeleted() throws SQLException, ClassNotFoundException
    {
        ArrayList<Coupon> arr=cpDAO.getAllCoupon();
        ArrayList<Coupon> temp=new ArrayList<Coupon>();
        for(int i=0;i<arr.size();i++)
        {
            if(arr.get(i).getIsDeleted()==0)
            {
                temp.add(arr.get(i));
                System.out.println(arr.get(i).getIsDeleted());
            }
        }
        return temp;
    }
    
    public boolean deleteCoupon (int id) throws ClassNotFoundException, SQLException
    {
        return cpDAO.deleteCoupon(id);
    }
    
    public boolean addCoupon(Coupon cp) throws SQLException, ClassNotFoundException
    {
        return cpDAO.addCoupon(cp);
    }
    
    public String[] getDistincMaKMHD() throws SQLException, ClassNotFoundException{
        ArrayList<Coupon> arr=cpDAO.getAllCoupon();
        ArrayList<String> temp=new ArrayList<String>();
        for(int i = 0; i < arr.size(); i++){  
            int flag=0;
            if(temp.isEmpty()==false)
            {
                for(int j=0;j<temp.size();j++)
                {
                    if(temp.get(j).equals(Integer.toString(arr.get(i).getMaKMHD())))
                    {
                        flag++;
                    }
                }
            }
            if(flag==0)
            {
                temp.add(Integer.toString(arr.get(i).getMaKMHD()));
            }
        }  
        String[] stringArray = temp.toArray(new String[0]);
        return stringArray;
    }
    
    public boolean hasCode(String code) throws SQLException, ClassNotFoundException
    {
        return cpDAO.hasCode(code);
    }
    
    public boolean updateCoupon(Coupon cp) throws ClassNotFoundException, SQLException
    {
        return cpDAO.updateCoupon(cp);
    }
    
    public ArrayList<Coupon> searchCp(String dk, String input) throws ClassNotFoundException, SQLException
    {
        ArrayList<String> temp=new ArrayList<String>();
        temp.add("Mã Coupon");
        temp.add("Code");
        temp.add("Tổng lượt áp dụng");
        temp.add("Tổng lượt đã dùng");
        temp.add("Mã KMHD");
        ArrayList<String> temp1=new ArrayList<String>();
        temp1.add("MaCP");
        temp1.add("Code");
        temp1.add("TongLuotApDung");
        temp1.add("TongLuotDaDung");
        temp1.add("MaKMHD");
        for(int i=0;i<temp.size();i++)
        {
            if(temp.get(i)==dk)
            {
                return cpDAO.searchCp(temp1.get(i), input);
            }
        }
        return cpDAO.searchCp(temp1.get(0), input);
    }
}
