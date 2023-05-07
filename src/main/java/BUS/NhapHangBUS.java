/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhapHangDAO;
import DTO.ChiTietNhapHang;
import DTO.ChiTietNhapHang;
import DTO.PhieuNhap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tam
 */
public class NhapHangBUS {
    
    private NhapHangDAO nhDAO;
    public NhapHangBUS(){
        nhDAO = new NhapHangDAO();

    }
    public List<PhieuNhap> getAll () throws SQLException, ClassNotFoundException 
    {
        return nhDAO.getAll();
    }
    
    public int themPhieuNhap(PhieuNhap pn, Double TongTien,List<ChiTietNhapHang> ctList) throws SQLException, ClassNotFoundException{
        return nhDAO.themNhapHang(pn, TongTien,ctList);
    }
    
//    public int themChiTietPhieuNhap(ArrayList<ArrayList> ct){
//        return nhDAO.themChiTietPhieuNhap(ct);
//    }
}
