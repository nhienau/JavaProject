/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChucVuDAO;
import DTO.ChucVu;
import DTO.NhanVien;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChucVuBUS {
    private ChucVuDAO cvDAO;
    public ChucVuBUS(){
        cvDAO = new ChucVuDAO();
    }
    
    public List<ChucVu> takeAll() throws ClassNotFoundException, SQLException{
        return cvDAO.takeAll();
    }
    
    public List<ChucVu> search(String input) throws ClassNotFoundException, SQLException{
    	return cvDAO.search(input);
    }
    
    public int addChucVu(ChucVu cv) throws ClassNotFoundException, SQLException {
    	return cvDAO.addChucVu(cv);
    	
    }
    
    public int updateChucVu(ChucVu cv) throws ClassNotFoundException, SQLException {
    	return cvDAO.updateChucVu(cv);
    }
    
    public int deleteChucVu(int ID) throws ClassNotFoundException, SQLException {
    	return cvDAO.deleteChucVu(ID);
    }
    
    public ChucVu getPermission(NhanVien nv) {
    	return cvDAO.getPermission(nv);
    }
    
}
