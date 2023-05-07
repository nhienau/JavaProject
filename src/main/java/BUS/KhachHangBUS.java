/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.KhachHangDAO;
import DTO.KhachHang;

/**
 *
 * @author HP
 */
public class KhachHangBUS {
	private KhachHangDAO khachHangDAO;
    public KhachHangBUS() {
    	khachHangDAO = new KhachHangDAO();
    }
    
    public List<KhachHang> getAll() throws SQLException {
    	return khachHangDAO.getAll();
    }
    
    public int addCustomer(KhachHang kh) throws SQLException {
    	return khachHangDAO.addCustomer(kh);
    }
    
    public int updateCustomer(KhachHang kh) throws SQLException {
    	return khachHangDAO.updateCustomer(kh);
    }
    
    public int deleteCustomer(int id) throws SQLException {
    	return khachHangDAO.deleteCustomer(id);
    }
    
}
