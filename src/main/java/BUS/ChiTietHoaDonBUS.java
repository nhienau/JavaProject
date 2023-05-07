/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDon;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class ChiTietHoaDonBUS {
    private ChiTietHoaDonDAO cthdDAO;
    
    public ChiTietHoaDonBUS()
    {
        cthdDAO=new ChiTietHoaDonDAO();
    }
    
    public boolean addCTHD(ChiTietHoaDon cthd) throws ClassNotFoundException, SQLException
    {
        return cthdDAO.addCTHD(cthd);
    }
}
