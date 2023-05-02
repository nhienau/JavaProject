/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuyenMaiSanPhamDAO;
import DTO.KhuyenMaiSanPham;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiSanPhamBUS {
    private KhuyenMaiSanPhamDAO kmspDAO=new KhuyenMaiSanPhamDAO();

    public KhuyenMaiSanPhamBUS() {
    }
    
    public ArrayList<KhuyenMaiSanPham> getAllKMSPNotDeleted() throws ClassNotFoundException, SQLException
    {
        ArrayList<KhuyenMaiSanPham> arr=kmspDAO.getAllKMSP();
        ArrayList<KhuyenMaiSanPham> temp=new ArrayList<KhuyenMaiSanPham>();
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
    
    public boolean addKhuyenMaiSanPham(KhuyenMaiSanPham kmsp) throws ClassNotFoundException, SQLException
    {
        return kmspDAO.addKhuyenMaiSanPham(kmsp);
    }
    
    public String[] getMaSP() throws ClassNotFoundException, SQLException
    {
        ArrayList<String> arr=kmspDAO.getMaSP();
        return arr.toArray(new String[0]);
    }
    
    public boolean updateKhuyenMaiSanPham(KhuyenMaiSanPham kmsp) throws SQLException, ClassNotFoundException
    {
        return kmspDAO.updateKmsp(kmsp);
    }
    
    public boolean deleteKhuyenMaiSanPham(int id) throws SQLException, ClassNotFoundException
    {
        return kmspDAO.deleteKmsp(id);
    }
    
    public ArrayList<KhuyenMaiSanPham> searchKmsp(String dk, String input,int dk1) throws SQLException, ClassNotFoundException
    {
        ArrayList<String> temp=new ArrayList<String>();
        temp.add("Mã KMHD");
        temp.add("Ngày bắt đầu");
        temp.add("Ngày kết thúc");
        temp.add("Phần trăm giảm");
        temp.add("Số tiền giảm");
        temp.add("Tổng lượt áp dụng");
        temp.add("Tổng lượt đã dùng");
        temp.add("Mã SP");
        ArrayList<String> temp1=new ArrayList<String>();
        temp1.add("MaKMHD");
        temp1.add("NgayBatDau");
        temp1.add("NgayKetThuc");
        temp1.add("PhanTramGiam");
        temp1.add("SoTienGiam");
        temp1.add("TongLuotApDung");
        temp1.add("TongLuotDaDung");
        temp1.add("MaSP");
        for(int i=0;i<temp.size();i++)
        {
            if(temp.get(i)==dk)
            {
                return kmspDAO.searchKmsp(temp1.get(i), input, dk1);
            }
        }
        return kmspDAO.searchKmsp(temp1.get(0), input, dk1);
    }
}
