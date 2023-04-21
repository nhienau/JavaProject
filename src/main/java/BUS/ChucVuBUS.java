/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChucVuDAO;
import DTO.ChucVu;
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
    
}
