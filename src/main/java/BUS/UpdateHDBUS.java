package BUS;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.HoaDon;
import DTO.KhachHang;
import DTO.NhanVien;
import DAO.*;
public  class UpdateHDBUS
{
    UpdateHDDAO updateHDDAO = new UpdateHDDAO();
    
    public ArrayList<NhanVien> takeAllNV ()
    {
        return updateHDDAO.takeAllNV();
    }
    
    
    public ArrayList<KhachHang> takeAllKH ()
    {
        return updateHDDAO.takeAllKH();
    }

    public boolean updateHoaDon (HoaDon hoaDon)
    {
      return  updateHDDAO.updateHoaDon(hoaDon);
    }
    public List<HoaDon> takeAll() throws ClassNotFoundException, SQLException{
        return updateHDDAO.takeAll();
    }
}
