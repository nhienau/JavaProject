package GUI;
 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import BUS.HoaDonBUS;
import DAO.HoaDonDAO;
import DTO.HoaDon;

public class HoaDonJPanel extends javax.swing.JPanel {
    private HoaDonBUS hdBUS;
    private String maHD;
    private String maKH;
    private String maNV;
    private String maKMHD;
    static int flag = 0;
    private NonEditableTableModel tableModel_jTable1;
    public HoaDonJPanel() {
        initComponents();
        hdBUS = new HoaDonBUS();
        load_HoaDon();
    }
                       
    private void initComponents() {

        draggableRoundPanel1 = new CUSTOM.DraggableRoundPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton_search = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton_export = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_update = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(31, 31, 31));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("QUẢN LÝ HÓA ĐƠN");

        jButton_search.setBackground(new java.awt.Color(255, 102, 0));
        jButton_search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_search.setForeground(new java.awt.Color(31, 31, 31));
        jButton_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jButton_search.setText("Tìm Kiếm");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jButton_export.setBackground(new java.awt.Color(255, 102, 0));
        jButton_export.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/export.png"))); // NOI18N
        jButton_export.setText("Xuất");
        jButton_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_exportActionPerformed(evt);
            }
        });

        jButton_delete.setBackground(new java.awt.Color(255, 102, 0));
        jButton_delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/trash.png"))); // NOI18N
        jButton_delete.setText("Xóa");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

//-----------------------------------------------------------------------------------------------------------------------------------------

        jButton_update.setBackground(new java.awt.Color(255, 102, 0));
        jButton_update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/circular-arrow.png"))); // NOI18N
        jButton_update.setText("Sửa");
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton_updateActionPerformed(evt);
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        });
//-----------------------------------------------------------------------------------------------------------------------------------------
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("Nhập thông tin cần tìm kiếm");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MaHD", "MaNV", "MaKH" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("Tìm kiếm theo");


        jTable1 = new JTable();
        tableModel_jTable1 = new NonEditableTableModel(new Object[][] {
        }, new String[]{"Mã Hóa Đơn", "Ngày Tạo", "Tổng Tiền", "Mã Khách Hàng", "Mã Nhân Viên", "Mã KMHD", "IsDeleted"} );
        jTable1 = new JTable(tableModel_jTable1);
        jScrollPane1 = new JScrollPane(jTable1);
        // jTable1.setModel(new javax.swing.table.DefaultTableModel(
        //     new Object [][] {},
        //     new String [] {
        //         "Mã Hóa Đơn", "Ngày Tạo", "Tổng Tiền", "Mã Khách Hàng", "Mã Nhân Viên", "Mã KMHD", "IsDeleted"
        //     }
        // ));

        // dtm = new DefaultTableModel();
        // dtm.addColumn("Mã Hóa Đơn");
        // dtm.addColumn("Ngày Tạo");
        // dtm.addColumn("Tổng Tiền");
        // dtm.addColumn("Mã Khách Hàng");
        // dtm.addColumn("Mã Nhân Viên");
        // dtm.addColumn("Mã KMHD");
        // dtm.addColumn("IsDeleted");
        // jTable1 = new JTable(dtm);
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable tableSelect = jTable1;
                int selectedRow = tableSelect.getSelectedRow();
                // jTextField_maXe.setText(jTable_XeMay.getValueAt(selectedRow, 1)+"");
                 maHD = jTable1.getValueAt(selectedRow, 0)+"";
                 maKH = jTable1.getValueAt(selectedRow, 3)+"";
                 maNV = jTable1.getValueAt(selectedRow, 4)+"";
                 maKMHD = jTable1.getValueAt(selectedRow, 5)+"";
                
              }  
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Hóa đơn bán hàng");

        jLabel5.setText("Tên khách hàng");

        jLabel6.setText("Số điện thoại");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "STT", "Tên SP", "ĐV tính", "SL", "Thành Tiền"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jTextField3)))))
                .addContainerGap(139, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(422, 422, 422)
                        .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_export, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton_search, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_export, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout draggableRoundPanel1Layout = new javax.swing.GroupLayout(draggableRoundPanel1);
        draggableRoundPanel1.setLayout(draggableRoundPanel1Layout);
        draggableRoundPanel1Layout.setHorizontalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
            .addGroup(draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        draggableRoundPanel1Layout.setVerticalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 725, Short.MAX_VALUE)
            .addGroup(draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        

    public void LoadHoaDon() throws ClassNotFoundException, SQLException
    {  
        //  dtm  = (DefaultTableModel) jTable1.getModel();
        // dtm.setRowCount(0);
        // List<HoaDon> hdList = new ArrayList<>();
        // hdList = hdBUS.takeAll();
        // for(int i=0 ; i<hdList.size(); i++)
        // {
        //     HoaDon hd = hdList.get(i);
        //     int MaHD =  hd.getMaHD();
        //     Date ngayTao = (Date) hd.getNgayTao();
        //     double TongTien = hd.getTongTien();
        //     long result = (long) TongTien; 
        //     int maKH = hd.getMaKH();
        //     int maNV = hd.getMaNV();
        //     int maKMHD = hd.getMaKMHD();
        //     int IsDeleted = hd.getIsDeleted();
        //     dtm.addRow(new Object[]{MaHD,ngayTao,result,maKH, maNV, maKMHD,IsDeleted});
        //     System.out.println(i);
    
        // }
        tableModel_jTable1.setRowCount(0);
        List<HoaDon> hdList = new ArrayList<>();
        hdList = hdBUS.takeAll();
        for (int i = 0; i<hdList.size(); i++)
      {
        HoaDon hoaDon = hdList.get(i);
        double TongTien = hoaDon.getTongTien();
        long result = (long) TongTien;
        Object[] newrow = {hoaDon.getMaHD(), hoaDon.getNgayTao(),result,hoaDon.getMaKH(), hoaDon.getMaNV(), hoaDon.getMaKMHD(), hoaDon.getIsDeleted()};
        tableModel_jTable1.addRow(newrow);

      }
        
    } 
    public void load_HoaDon() {
        try {
            HoaDonBUS hdonBus = new HoaDonBUS();
            List<HoaDon> listHoaDon = hdonBus.takeAll();
            // Cập nhật dữ liệu trên bảng hiển thị
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Xóa dữ liệu hiện tại trên bảng
    
            for (HoaDon hdon : listHoaDon) {
                // Thêm dữ liệu mới vào bảng
                double TongTien = hdon.getTongTien();
              long result = (long) TongTien; 
                Object[] row = new Object[] { hdon.getMaHD(), hdon.getNgayTao(), result, hdon.getMaKH(), hdon.getMaNV(), hdon.getMaKMHD(), hdon.getIsDeleted()  };
                model.addRow(row);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
                       
    public void reloadTableData(List<HoaDon> hdList) {
        // DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // model.setRowCount(0); // clear the table
       
        // for (HoaDon hd : hdList) {
        //     double TongTien = hd.getTongTien();
        //     long result = (long) TongTien; 
        //     Object[] row = { hd.getMaHD(), hd.getNgayTao(), result, hd.getMaKH(), hd.getMaNV(), hd.getMaKMHD(), hd.getIsDeleted() };
        //     model.addRow(row);
        //     System.out.println("5");
        // }

        // DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        // model.setRowCount(0); // clear the table
       tableModel_jTable1.setRowCount(0);
        for (HoaDon hd : hdList) {
            double TongTien = hd.getTongTien();
            long result = (long) TongTien; 
            Object[] row = { hd.getMaHD(), hd.getNgayTao(), result, hd.getMaKH(), hd.getMaNV(), hd.getMaKMHD(), hd.getIsDeleted() };
            tableModel_jTable1.addRow(row);
            System.out.println("5");
        }
    }
    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        try {
            if(jTextField1.getText().trim().equals(""))
            {
                LoadHoaDon();
                // tableModel_jTable1.setRowCount(0);
                JOptionPane.showMessageDialog(this,"Vui lòng nhập đủ thông tin");
            }
            else {
                String searchValue = jTextField1.getText();
                int search_Value = Integer.parseInt(searchValue);
                String comboBoxValue = jComboBox1.getSelectedItem().toString();
                List<HoaDon> hd = hdBUS.searchHoaDon(comboBoxValue, search_Value);
                reloadTableData(hd);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn");
        }
    }   
     
    
    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {                                                                         
        int selectedRowIndex = jTable1.getSelectedRow();
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn để xóa.");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int maHD = (int) model.getValueAt(selectedRowIndex, 0);
        int confirmed = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hóa đơn này?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            try {
                HoaDonDAO hoaDonDAO = new HoaDonDAO();
                hoaDonDAO.deleteHoaDon(maHD);
                JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công.");
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi xóa hóa đơn: " + ex.getMessage());
            }
        }  
    }
    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt)  
    {         

    }      


    

    private void jButton_exportActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }      
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          


    // Variables declaration - do not modify                           
    private CUSTOM.DraggableRoundPanel draggableRoundPanel1;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_export;
    private javax.swing.JButton jButton_search;
    private javax.swing.JButton jButton_update;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration 
    
    


    public class NonEditableTableModel extends DefaultTableModel {
  
        public NonEditableTableModel(Object[][] data, Object[] columnNames) {
          super(data, columnNames);
        }
        @Override
        public boolean isCellEditable(int row, int column) {
          return false; // không cho phép sửa đổi các ô trong bảng
        }
      }
}
