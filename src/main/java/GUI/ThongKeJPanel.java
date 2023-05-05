package GUI;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import BUS.ThongKeDTBUS;
import BUS.ThongKeKHBUS;
import BUS.ThongKeSPBBUS;
import BUS.ThongKeSPNBUS;
import DAO.ThongKeDTDAO;




public class ThongKeJPanel extends javax.swing.JPanel {

 
    public ThongKeJPanel() {
        
        initComponents();
        showCustomerStatistics();
        showSPN();
        showSPB();
        showDT();
        loadCombobox1();
        loadCombobox2();
        loadCombobox3();
        loadCombobox4();
    }              
    private void initComponents() {

        draggableRoundPanel1 = new CUSTOM.DraggableRoundPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_KH = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        BieuDo_KH = new javax.swing.JPanel();
        KhachhangCombobox = new javax.swing.JComboBox<>();
        DoanhthuCombobox = new javax.swing.JComboBox<>();
        SpbCombobox = new javax.swing.JComboBox<>();
        SpnCombobox = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DT = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        BieuDo_DT = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_SPB = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        BieuDo_SPB = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_SPN = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        BieuDo_SPN = new javax.swing.JPanel();
        draggableRoundPanel1.setBackground(new java.awt.Color(31, 31, 31));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setText("THỐNG KÊ CỬA HÀNG");

        jPanel6.setBackground(new java.awt.Color(31, 31, 31));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 0));
        jLabel4.setText("Bảng khách hàng");

        jTable_KH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số lần mua"
            }
        ));

        jScrollPane2.setViewportView(jTable_KH);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 0));
        jLabel5.setText("Biểu đồ");

        javax.swing.GroupLayout BieuDo_KHLayout = new javax.swing.GroupLayout(BieuDo_KH);
        BieuDo_KH.setLayout(BieuDo_KHLayout);
        BieuDo_KHLayout.setHorizontalGroup(
            BieuDo_KHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BieuDo_KHLayout.setVerticalGroup(
            BieuDo_KHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BieuDo_KH.setLayout(new BorderLayout());
        BieuDo_KH.setPreferredSize(new Dimension(600, 400));
        Container chart_Panel;
        try {
                JFreeChart chart2 = ThongKeKHBUS.createChart();
                ChartPanel chartPanel3 = new ChartPanel(chart2);
                BieuDo_KH.add(chartPanel3, BorderLayout.CENTER);
            } catch (SQLException | ClassNotFoundException ex) {
            }
        KhachhangCombobox.setBackground(new java.awt.Color(255, 102, 0));
        KhachhangCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        
        KhachhangCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        KhachhangCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KhachhangComboboxActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 492, Short.MAX_VALUE))
                    .addComponent(BieuDo_KH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(KhachhangCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(KhachhangCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BieuDo_KH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1133, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khách Hàng", jPanel4);

        jPanel1.setBackground(new java.awt.Color(31, 31, 31));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Bảng Doanh Thu");

        jTable_DT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Chi Tiêu", "Doanh Thu", "Lợi Nhuận"
            }
        ));
        
        jScrollPane1.setViewportView(jTable_DT);

        DoanhthuCombobox.setBackground(new java.awt.Color(255, 102, 0));
        KhachhangCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        DoanhthuCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoanhthuComboboxActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("Biểu đồ");

        javax.swing.GroupLayout BieuDo_DTLayout = new javax.swing.GroupLayout(BieuDo_DT);
        BieuDo_DT.setLayout(BieuDo_DTLayout);
        BieuDo_DTLayout.setHorizontalGroup(
            BieuDo_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BieuDo_DTLayout.setVerticalGroup(
            BieuDo_DTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(498, Short.MAX_VALUE))
                    .addComponent(BieuDo_DT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DoanhthuCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DoanhthuCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BieuDo_DT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        BieuDo_DT.setLayout(new BorderLayout());
        BieuDo_DT.setPreferredSize(new Dimension(400, 300));
        Container chartPanel_4;
        try {
                JFreeChart chart4 = ThongKeDTDAO.getDoanhThuChart();
                ChartPanel chartPanel2 = new ChartPanel(chart4);
                BieuDo_DT.add(chartPanel2, BorderLayout.CENTER);
            } catch (SQLException | ClassNotFoundException ex) {
            }
        jTabbedPane1.addTab("Doanh Thu", jPanel1);

        jPanel10.setBackground(new java.awt.Color(31, 31, 31));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 0));
        jLabel8.setText("Bảng sản phẩm bán");

        jTable_SPB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá bán"
            }
        ));
        jScrollPane4.setViewportView(jTable_SPB);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 0));
        jLabel9.setText("Biểu đồ");

        javax.swing.GroupLayout BieuDo_SPBLayout = new javax.swing.GroupLayout(BieuDo_SPB);
        BieuDo_SPB.setLayout(BieuDo_SPBLayout);
        BieuDo_SPBLayout.setHorizontalGroup(
            BieuDo_SPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BieuDo_SPBLayout.setVerticalGroup(
            BieuDo_SPBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BieuDo_SPB.setLayout(new BorderLayout());
        BieuDo_SPB.setPreferredSize(new Dimension(600, 400));
        Container chartPanel;
        try {
                JFreeChart chart = ThongKeSPBBUS.createChart();
                ChartPanel chartPanel2 = new ChartPanel(chart);
                BieuDo_SPB.add(chartPanel2, BorderLayout.CENTER);
            } catch (SQLException | ClassNotFoundException ex) {
            }
        SpbCombobox.setBackground(new java.awt.Color(255, 102, 0));
        SpbCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        SpbCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpbComboboxActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(BieuDo_SPB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SpbCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SpbCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BieuDo_SPB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1133, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm Bán", jPanel2);

        jPanel8.setBackground(new java.awt.Color(31, 31, 31));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 102, 0));
        jLabel6.setText("Bảng sản phẩm nhập");

        jTable_SPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá nhập"
            }
        ));   
        jScrollPane3.setViewportView(jTable_SPN);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Biểu đồ");

        javax.swing.GroupLayout BieuDo_SPNLayout = new javax.swing.GroupLayout(BieuDo_SPN);
        BieuDo_SPN.setLayout(BieuDo_SPNLayout);
        BieuDo_SPNLayout.setHorizontalGroup(
            BieuDo_SPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BieuDo_SPNLayout.setVerticalGroup(
            BieuDo_SPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        BieuDo_SPN.setLayout(new BorderLayout());
        BieuDo_SPN.setPreferredSize(new Dimension(600, 400));
        Container char_tPanel;
        try {
                JFreeChart chart = ThongKeSPNBUS.createChart();
                ChartPanel chartPanel2 = new ChartPanel(chart);
                BieuDo_SPN.add(chartPanel2, BorderLayout.CENTER);
            } catch (SQLException | ClassNotFoundException ex) {
            }
        SpnCombobox.setBackground(new java.awt.Color(255, 102, 0));
        SpnCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        SpnCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpnComboboxActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(BieuDo_SPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SpnCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SpnCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BieuDo_SPN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Sản Phẩm Nhập", jPanel3);

        javax.swing.GroupLayout draggableRoundPanel1Layout = new javax.swing.GroupLayout(draggableRoundPanel1);
        draggableRoundPanel1.setLayout(draggableRoundPanel1Layout);
        draggableRoundPanel1Layout.setHorizontalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(draggableRoundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        draggableRoundPanel1Layout.setVerticalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold> 
    public void loadCombobox1() {
        try {
            ArrayList<String> listMonthYear = ThongKeSPBBUS.getListMonthYear();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listMonthYear.toArray(new String[0]));
            SpbCombobox.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
    }
    public void loadCombobox2() {
        try {
            ArrayList<String> listMonthYear = ThongKeSPNBUS.getListMonthYear();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listMonthYear.toArray(new String[0]));
            SpnCombobox.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
    }
    public void loadCombobox3() {
        try {
            ArrayList<String> listMonthYear = ThongKeKHBUS.getListMonthYear();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listMonthYear.toArray(new String[0]));
            KhachhangCombobox.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
    }
    public void loadCombobox4() {
        try {
            ArrayList<String> listMonthYear = ThongKeDTBUS.getListMonthYear();
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(listMonthYear.toArray(new String[0]));
            DoanhthuCombobox.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            // Xử lý ngoại lệ nếu có
            e.printStackTrace();
        }
    }
    
                             
    public void showCustomerStatistics() {
    try {
        DefaultTableModel model = ThongKeKHBUS.getCustomerStatistics();
        jTable_KH.setModel(model);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    }

     public void showSPB() {
        DefaultTableModel model;
        try {
            model = ThongKeSPBBUS.getSPB();
            jTable_SPB.setModel(model);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void showSPN() {
        DefaultTableModel model;
        try {
            model = ThongKeSPNBUS.getSPN();
            jTable_SPN.setModel(model);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void showDT() {
        TableModel model;
        try {
            model = ThongKeDTBUS.getTongTien();
            jTable_DT.setModel(model);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void DoanhthuComboboxActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        DefaultTableModel model = new DefaultTableModel();                            
        String monthYear = DoanhthuCombobox.getSelectedItem().toString();
        try {
            model = ThongKeDTBUS.getDTByMonth(monthYear);
            // Hiển thị dữ liệu lên bảng
            jTable_DT.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }                                                

    private void KhachhangComboboxActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        DefaultTableModel model = new DefaultTableModel();                            
        String monthYear = KhachhangCombobox.getSelectedItem().toString();
        try {
            model = ThongKeKHBUS.getCustomerDetailsByMonth(monthYear);
            // Hiển thị dữ liệu lên bảng
            jTable_KH.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }                                                 

    private void SpnComboboxActionPerformed(java.awt.event.ActionEvent evt) {                                            
        DefaultTableModel model = new DefaultTableModel();                            
        String monthYear = SpnCombobox.getSelectedItem().toString();
        try {
            model = ThongKeSPNBUS.getProductDetailsByMonth(monthYear);
            // Hiển thị dữ liệu lên bảng
            jTable_SPN.setModel(model);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }                                           

    private void SpbComboboxActionPerformed(java.awt.event.ActionEvent evt) {                                            
            DefaultTableModel model = new DefaultTableModel();                            
            String monthYear = SpbCombobox.getSelectedItem().toString();
            try {
                model = ThongKeSPBBUS.getProductDetailsByMonth(monthYear);
                // Hiển thị dữ liệu lên bảng
                jTable_SPB.setModel(model);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }      
                                     


    // Variables declaration - do not modify                     
    private javax.swing.JPanel BieuDo_DT;
    private javax.swing.JPanel BieuDo_KH;
    private javax.swing.JPanel BieuDo_SPB;
    private javax.swing.JPanel BieuDo_SPN;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel1;
    private javax.swing.JComboBox<String> DoanhthuCombobox;
    private javax.swing.JComboBox<String> KhachhangCombobox;
    private static javax.swing.JComboBox<String> SpbCombobox;
    private javax.swing.JComboBox<String> SpnCombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_DT;
    private javax.swing.JTable jTable_KH;
    private javax.swing.JTable jTable_SPB;
    private javax.swing.JTable jTable_SPN;
    // End of variables declaration                   
}
