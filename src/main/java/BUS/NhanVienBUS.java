/*
` * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVien;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienBUS {
    private  NhanVienDAO nvDAO;
    public NhanVienBUS(){
        nvDAO = new NhanVienDAO();
    }
    
    public List<NhanVien> takeAll() throws SQLException, ClassNotFoundException{
        
        return nvDAO.takeAll();
    }

    public List<NhanVien> filter(String sortCondition,String searchInput) throws ClassNotFoundException, SQLException{
        if(searchInput == null || searchInput.isEmpty())
            searchInput = "%";
        else
            searchInput = "%"+searchInput+"%";
        if(sortCondition.contains("Tất cả"))
            return nvDAO.searchOnly(searchInput);
        else
            sortCondition = "%"+sortCondition+"%";
        
        return nvDAO.filter(sortCondition, searchInput);
    }
    
    public int addEmp(NhanVien nv) throws ClassNotFoundException, SQLException{
        return nvDAO.addEmp(nv);
    }
    
    public int updateEmp(NhanVien nv) throws ClassNotFoundException, SQLException{
        return nvDAO.updateEmp(nv);
    }
    
    public int delEmp(int ID) throws ClassNotFoundException, SQLException{
        return nvDAO.delEmp(ID);
    }
    
    public void updateWhenCVIsDeleted(int MaCV) throws ClassNotFoundException, SQLException {
    	nvDAO.updateWhenCVIsDeleted(MaCV);
    }
    
    public NhanVien verifyLogin(String username, String password) throws SQLException {
    	return nvDAO.verifyLogin(username, password);
    }
    
    public int updateEmployeeInfo(NhanVien nv) throws SQLException {
    	return nvDAO.updateEmployeeInfo(nv);
    }
    
    public int changePassword (NhanVien nv) throws SQLException {
    	return nvDAO.changePassword(nv);
    }
    
    public boolean phoneNumberExisted (NhanVien nv) throws SQLException {
    	return nvDAO.phoneNumberExisted(nv);
    }
    
    public int updateEmpWithoutPassword(NhanVien nv) throws ClassNotFoundException, SQLException {
    	return nvDAO.updateEmpWithoutPassword(nv);
    }
    
}
