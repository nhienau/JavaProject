/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoginDAO;
import DTO.NhanVien;

/**
 *
 * @author HP
 */
public class LoginBUS {
    private LoginDAO loginDAO;
    
    public LoginBUS() {
    	loginDAO = new LoginDAO();
    }

	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
    
    public NhanVien verifyLogin(String username, String password) {
    	return loginDAO.verifyLogin(username, password);
    }
}
