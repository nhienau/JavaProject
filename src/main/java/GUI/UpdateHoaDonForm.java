package GUI;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import BUS.UpdateHDBUS;
import DAO.HoaDonDAO;

public class UpdateHoaDonForm extends JFrame
{
    HoaDonDAO hd  =new HoaDonDAO();
    UpdateHDBUS updateHDBUS = new UpdateHDBUS();

     private JTextField jTextField_maHD;
     private JComboBox<KhachHang> comboBox_khachHang;
     private JComboBox<NhanVien> comboBox_nhanVien;


     String[] str = {"0" ,"1","2" };
     private JComboBox<String> comboBox_khuyenMai;
    private JButton jButton_exit;
    private JButton jButton_capNhat;


    private NhanVien selectedNV;

    private KhachHang selectedKH;
    // private String maHD;
    // private String maKH;
    // private String maNV;
    // private String maKMHD;
    

    public UpdateHoaDonForm (String maHD, String maKH, String maNV, String maKMHD)
     {
        this.init_update(maHD);
        this.setVisible(true);
        loadKhoaNgoai(maKH,maNV,maKMHD);
     }

     public void init_update (String maHD)
     {
        this.setTitle("Update");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        Font font_tieuDe = new Font("Arial ", Font.BOLD, 20);
        Font font = new Font("Arial ", Font.BOLD, 14);
        
        
        JLabel jLabel_tieuDe = new JLabel("CHỈNH SỬA HÓA ĐƠN");
        jLabel_tieuDe.setFont(font_tieuDe);
        jLabel_tieuDe.setBounds(121, 22, 258, 24);

        JLabel jLabel_maHoaDon = new JLabel("MÃ HÓA ĐƠN");
        jLabel_maHoaDon.setFont(font);
        jLabel_maHoaDon.setBounds(84, 116, 300, 17);

        JLabel jLabel_maKhachHang = new JLabel("MÃ KHÁCH HÀNG");
        jLabel_maKhachHang.setFont(font);
        jLabel_maKhachHang.setBounds(78, 181, 300, 17);

        JLabel jLabel_maNhanVien = new JLabel("MÃ NHÂN VIÊN");
        jLabel_maNhanVien.setFont(font);
        jLabel_maNhanVien.setBounds(84, 248, 300, 17);

        JLabel jLabel_maKhuyenMai = new JLabel("MÃ KHUYẾN MÃI");
        jLabel_maKhuyenMai.setFont(font);
        jLabel_maKhuyenMai.setBounds(84, 310, 300, 17);

         jTextField_maHD = new JTextField(maHD);
        jTextField_maHD.setEditable(false);
        jTextField_maHD.setBackground(Color.LIGHT_GRAY);
        jTextField_maHD.setBounds(219, 108, 176, 32);

         comboBox_khachHang = new JComboBox<KhachHang>();
         comboBox_khachHang.setBounds(219, 178, 176, 23);

         comboBox_nhanVien = new JComboBox<NhanVien>();
         comboBox_nhanVien.setBounds(219, 245, 176, 23);

         comboBox_khuyenMai = new JComboBox<>(str);
         comboBox_khuyenMai.setBounds(219, 310, 176, 23);

          jButton_exit = new JButton("EXIT");
         jButton_exit.setFont(font);
         jButton_exit.setBounds(77,383,129,40);
         jButton_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Dispose(evt);
            }
        });
//-------------------------------------------------------------------------------------------------------------
        jButton_capNhat = new JButton("CẬP NHẬT");
        jButton_capNhat.setFont(font);
        jButton_capNhat.setBounds(285,383,129,40);
        jButton_capNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    UpdateHD(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//------------------------------------------------------------------------------------------------------------

         this.setLayout(null);
         this.add(jLabel_tieuDe);
         this.add(jLabel_maHoaDon);
         this.add(jLabel_maKhachHang);
         this.add(jLabel_maNhanVien);
         this.add(jLabel_maKhuyenMai);
         
         this.add(jTextField_maHD);
         this.add(comboBox_khachHang);
         this.add(comboBox_nhanVien);
         this.add(comboBox_khuyenMai);
         this.add(jButton_exit);
         this.add(jButton_capNhat);
         

     }
 

     public void loadKhoaNgoai ( String maKH, String maNV, String maKMHD)
     {
        comboBox_nhanVien.removeAllItems();
      
        ArrayList<NhanVien> arrNV = new ArrayList<NhanVien>();
        arrNV = updateHDBUS.takeAllNV();
  
        for (NhanVien nhanVien : arrNV) 
        {
          comboBox_nhanVien.addItem(nhanVien);
       }


       comboBox_khachHang.removeAllItems();
      
        ArrayList<KhachHang> arrKH = new ArrayList<KhachHang>();
        arrKH = updateHDBUS.takeAllKH();
  
        for (KhachHang khachHang : arrKH) 
        {
          comboBox_khachHang.addItem(khachHang);
       }
     
       for (int i = 0; i < comboBox_nhanVien.getItemCount(); i++) {
          if (((NhanVien) comboBox_nhanVien.getItemAt(i)).getMaNV()==(Integer.parseInt(maNV))) {
            comboBox_nhanVien.setSelectedIndex(i);
              break;
          }
      }

      for (int i = 0; i < comboBox_khachHang.getItemCount(); i++) {
        if (((KhachHang) comboBox_khachHang.getItemAt(i)).getMaHD() ==(Integer.parseInt(maKH))) {
          comboBox_khachHang.setSelectedIndex(i);
      
            break;
        }
    }

    comboBox_khuyenMai.setSelectedItem(maKMHD);
    System.out.println(maKMHD);
    
    }
     public void UpdateHD (java.awt.event.ActionEvent evt)throws ClassNotFoundException, SQLException
     {
        selectedNV = (NhanVien) comboBox_nhanVien.getSelectedItem();
        selectedKH = (KhachHang) comboBox_khachHang.getSelectedItem();
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHD(Integer.parseInt(jTextField_maHD.getText().trim())  );
        hoaDon.setMaKH(selectedKH.getMaHD());
        hoaDon.setMaNV( selectedNV.getMaNV());
        hoaDon.setMaKMHD(Integer.parseInt( comboBox_khuyenMai.getSelectedItem().toString()));
        updateHDBUS.updateHoaDon(hoaDon);
        JOptionPane.showMessageDialog(null, "Cập nhật thành công !");

        this.dispose();
     }
     public void Dispose (java.awt.event.ActionEvent evt)
     {
        this.dispose();

     }
}