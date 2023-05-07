/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.NhapHangBUS;
import DTO.ChiTietNhapHang;
import DTO.ChiTietPhieuNhap;
import DTO.ChucVu;
import DTO.NhanVien;
import DTO.PhieuNhap;
import DTO.SanPhamExcelDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.IOException;
import java.security.KeyStore;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
//import static java.sql.Types.NUMERIC;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Receiver;
import javax.swing.DefaultCellEditor;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class NhapHangPanel extends javax.swing.JPanel {

    private String path;
    private NhapHangBUS pnBUS;
    List<SanPhamExcelDTO> listSP = new ArrayList();
    private XSSFSheet sheet;
    private List<PhieuNhap> pnList;
    private ArrayList<ArrayList> listExcel = new ArrayList<>();
    

    /**
     * Creates new form NhapHangPanel
     */
    public NhapHangPanel() throws SQLException, ClassNotFoundException {

        initComponents();
        pnBUS = new NhapHangBUS();
        pnList = pnBUS.getAll();
        initTablePhieuNhap(pnList);
        clickTable(tableCTPNExcel);
//        changeSL(tableCart);

        updateTotal(tableCart);
        addQuantityListener(tableCart);

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // Create a JFrame
        NhanVien nv = new NhanVien();
        ChucVu cv = new ChucVu();
        JFrame frame = new JFrame("My Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel with your interface components
        JPanel panel = new NhapHangPanel();

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Pack and show the frame
        frame.pack();
        frame.setVisible(true);
    }

    public void initTablePhieuNhap(List<PhieuNhap> pnAdd) {

        DefaultTableModel tableModel = (DefaultTableModel) tableCTPN.getModel();
        tableModel.setRowCount(0);
//        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        for (PhieuNhap pn : pnAdd) {
            String maPhieuNhap = "PN" + pn.getMaPN();
            Date ngayTao = (Date) pn.getNgayTao();
            String input = pn.getTongTien().replaceAll("[^\\d]", "").trim();
            String tongTien = input;
            String maNhanVien = "NV" + pn.getMaNV();

            tableModel.addRow(new Object[]{maPhieuNhap, ngayTao, tongTien, maNhanVien});
        }

    }
    
//    public List<ChiTietPhieuNhap> getChiTietPhieuNhapList() {
//    // Khởi tạo danh sách chi tiết phiếu nhập
//        List<ChiTietPhieuNhap> chiTietPhieuNhapList = new ArrayList<>();
//    
//        // Thực hiện logic để lấy dữ liệu từ JTable hoặc từ nguồn dữ liệu khác
//        // Ví dụ:
//        // Lặp qua các dòng trong JTable và tạo các đối tượng ChiTietPhieuNhap từ dữ liệu của mỗi dòng
//        for (int i = 0; i < tableCart.getRowCount(); i++) {
//            // Lấy dữ liệu từ JTable
//            
//            int maSanPham = (int) tableCart.getValueAt(i, 0);
//            int soLuong = (int) tableCart.getValueAt(i, 1);
//            double donGia = (double) tableCart.getValueAt(i, 2);
//
//            // Tạo đối tượng ChiTietPhieuNhap và thêm vào danh sách
//            ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(maSanPham, soLuong, donGia);
//            chiTietPhieuNhapList.add(chiTietPhieuNhap);
//    }
//    
//    // Trả về danh sách chi tiết phiếu nhập
//    return chiTietPhieuNhapList;
//}


    public void clickTable(JTable tableCTPNExcel) {
        

        tableCTPNExcel.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                
                // Kiểm tra xem người dùng đã chọn hàng chưa
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = tableCTPNExcel.getSelectedRow();
                    if (selectedRow != -1) {
                        // Trích xuất dữ liệu từ hàng đã chọn
                        Object[] rowData = new Object[tableCTPNExcel.getColumnCount()];
                        for (int i = 0; i < rowData.length; i++) {
                            rowData[i] = tableCTPNExcel.getValueAt(selectedRow, i);
                        }
                        // Đưa dữ liệu vào JTable khác
                        DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
                       
                        model.addRow(new Object[]{rowData[0], rowData[1], rowData[2], rowData[3], rowData[4]});
                       
                        updateTotal(tableCart);
                      

                    }
                }
            }
        });
    }

    public void addQuantityListener(JTable table) {
        // Create a cell editor for the quantity column
        JTextField quantityEditor = new JTextField();
        quantityEditor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateMoneyColumn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateMoneyColumn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateMoneyColumn();
            }

            private void updateMoneyColumn() {
                int rowIndex = tableCart.getSelectedRow();
                int columnIndex = tableCart.getSelectedColumn();
                int BeforeSL = (int) tableCart.getValueAt(rowIndex, 2);
                if (columnIndex == 2) { // Quantity column
                    int quantity = Integer.parseInt(quantityEditor.getText());
                    if (quantity > BeforeSL) {
                        JOptionPane.showMessageDialog(getParent(), "Giá trị SL không được quá NSX đưa ra!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        removeRowFromTable(rowIndex);

                    } else {
                        double unitPrice = (double) tableCart.getValueAt(rowIndex, 3); // Unit price column
                        double money = quantity * unitPrice;
                        tableCart.setValueAt(money, rowIndex, 4); // Money column
                        updateTotal(tableCart);
                    }

                }
            }
        });
        tableCart.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(quantityEditor));
    }

    public void removeRowFromTable(int row) {
        int rowIndex = tableCart.getSelectedRow();
        int getRow = row;
        DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
        model.removeRow(getRow);
        updateTotal(tableCart);

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        roundPanel = new CUSTOM.DraggableRoundPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCTPNExcel = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableCTPN = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCart = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        sumTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setText("Add");

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 51, 0));
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Tam\\Documents\\NetBeansProjects\\JavaProject\\src\\main\\java\\Images\\import.png")); // NOI18N
        jButton3.setText("Chọn Danh Sách SP");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 51, 0), new java.awt.Color(255, 51, 0), null, null));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Tam\\Documents\\NetBeansProjects\\JavaProject\\src\\main\\java\\GUI\\search.png")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 51), new java.awt.Color(0, 255, 51), new java.awt.Color(0, 255, 0), new java.awt.Color(51, 255, 51)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tableCTPNExcel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá/Cái", "Thành Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCTPNExcel.setToolTipText("");
        tableCTPNExcel.setName(""); // NOI18N
        jScrollPane1.setViewportView(tableCTPNExcel);

        tableCTPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Phiếu Nhập", "Ngày Tạo", "Tổng Tiền", "Mã Nhân Viên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableCTPN);
        if (tableCTPN.getColumnModel().getColumnCount() > 0) {
            tableCTPN.getColumnModel().getColumn(1).setResizable(false);
            tableCTPN.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 0));
        jLabel8.setText("DANH SÁCH PHIẾU NHẬP");
        jLabel8.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                        .addComponent(jScrollPane3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("DANH SÁCH SẢN PHẨM CẦN THÊM");
        jLabel1.setToolTipText("");

        tableCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCart.setRowHeight(40);
        jScrollPane2.setViewportView(tableCart);
        if (tableCart.getColumnModel().getColumnCount() > 0) {
            tableCart.getColumnModel().getColumn(0).setResizable(false);
            tableCart.getColumnModel().getColumn(1).setResizable(false);
            tableCart.getColumnModel().getColumn(3).setResizable(false);
            tableCart.getColumnModel().getColumn(4).setResizable(false);
        }

        sumTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sumTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sumTotal.setText("0");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("TỔNG");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sumTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(460, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sumTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jButton4.setBackground(new java.awt.Color(102, 255, 204));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Tam\\Documents\\NetBeansProjects\\JavaProject\\src\\main\\java\\Images\\export.png")); // NOI18N
        jButton4.setText("NHẬP HÀNG");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Tam\\Documents\\NetBeansProjects\\JavaProject\\src\\main\\java\\Images\\trash.png")); // NOI18N
        jButton5.setText("XÓA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanelLayout = new javax.swing.GroupLayout(roundPanel);
        roundPanel.setLayout(roundPanelLayout);
        roundPanelLayout.setHorizontalGroup(
            roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanelLayout.setVerticalGroup(
            roundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.removeChoosableFileFilter(fc.getFileFilter());
        FileFilter filter = new FileNameExtensionFilter("Excel files (.xlsx)", "xlsx");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File imported = fc.getSelectedFile();
            List<SanPhamExcelDTO> listSP = new ArrayList<>();
            try {
                FileInputStream in = new FileInputStream(imported);
                XSSFWorkbook xFile = new XSSFWorkbook(in);
                XSSFSheet sheet = xFile.getSheetAt(0);
                String masp = "", tensanpham = "";
                int sl = 0;
                double gia = 0, tongtien = 0;
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (row.getRowNum() != 0) {
                            switch (cell.getColumnIndex()) {
                                case 0:
                                    masp = cell.getStringCellValue();
                                    break;
                                case 1:
                                    tensanpham = cell.getStringCellValue();
                                    break;
                                case 2:
                                    sl = (int) cell.getNumericCellValue();
                                    break;
                                case 3:
                                    gia = cell.getNumericCellValue();
                                    break;
                                case 4:
                                    tongtien = cell.getNumericCellValue();
                                    break;
                            }
                        }
                    }
                    if (!"".equals(masp)) {
                        SanPhamExcelDTO spex = new SanPhamExcelDTO(masp, tensanpham, sl, gia, tongtien);
                        listSP.add(spex);

                    }

                }
                JOptionPane.showMessageDialog(this, "Load dữ liệu thành công. ", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                loadReceivedProducts((ArrayList<SanPhamExcelDTO>) listSP);

            } catch (Exception e) {
                System.out.println("An error has occurred at btnImportActionPerformed in Recieved_GUI class");
                System.out.println(e);
            }
        }
    }                                        


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        int selecrow = tableCart.getSelectedRow();
        if (selecrow != -1) {
            int n = JOptionPane.showConfirmDialog(this, "Bạn xác nhận sẽ xóa chứ? ", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
                model.removeRow(selecrow);

                JOptionPane.showMessageDialog(this, "Xóa hàng thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                updateTotal(tableCart);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn hàng để xóa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (sumTotal.getText().length() == 3) {
            JOptionPane.showMessageDialog(this, "Chưa có dữ liệu để lưu đơn nhập hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            int n = JOptionPane.showConfirmDialog(this, "Xác nhận lưu đơn nhập hàng? ", "Lưu ý", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                try {

                    String tongTien = sumTotal.getText().split("\\₫")[0].trim().replace(".", "");
                    int maNV = 3;
                    PhieuNhap pn = new PhieuNhap(tongTien, maNV, 0);
                    int themBus = 0;
                    
                    themBus = pnBUS.themPhieuNhap(pn, tongTien);
                    if(themBus >0){
                        
                        JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thành công. ", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                        loadTablePhieuNhap(pnList);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(NhapHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NhapHangPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }                                        

    public void updateTotal(JTable tableCart) {
        NumberFormat vnFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        double total = 0;
        for (int i = 0; i < tableCart.getRowCount(); i++) {
            double subtotal = (double) tableCart.getValueAt(i, 4);
            total += subtotal;
        }
        sumTotal.setText(vnFormat.format(total));
        System.out.println(sumTotal.getText().split("\\₫")[0].replace(".", "").trim());

    }

    public List<ChiTietNhapHang> getAllValueTableCart() {
        List<ChiTietNhapHang> CTNH = new ArrayList<>();
        for (int i = 0; i < tableCart.getRowCount(); i++) {
            String[] maSPArray = tableCart.getValueAt(i, 0).toString().split("SP");
            int maSP = Integer.parseInt(maSPArray[1]);
            int maPN = 1;
            int sl = (int) tableCart.getValueAt(i, 2);
            double donGia = (double) tableCart.getValueAt(i, 3);

            ChiTietNhapHang ctnh = new ChiTietNhapHang(maSP, maPN, sl, donGia);
            CTNH.add(ctnh);
        }
        return CTNH;
    }

    public void loadReceivedProducts(List<SanPhamExcelDTO> listSP) {

        DefaultTableModel model = (DefaultTableModel) tableCTPNExcel.getModel();
        model.setRowCount(0);
        for (SanPhamExcelDTO sp : listSP) {
            Object[] row = new Object[5];
            row[0] = sp.getMasp();
            row[1] = sp.getTensanpham();
            row[2] = sp.getSl();
            row[3] = sp.getGia();
            row[4] = sp.getTongtien();
            model.addRow(row);
        }
        tableCTPNExcel.setModel(model);
    }
    public void loadTablePhieuNhap (List<PhieuNhap> pnList) {
        DefaultTableModel model = (DefaultTableModel) tableCTPN.getModel();
        model.setRowCount(0);
        for (PhieuNhap pn : pnList) {
            Object[] row = new Object[5];
            row[0] = pn.getMaPN();
            row[1] = pn.getNgayTao();
            row[2] = pn.getTongTien();
            row[3] = pn.getMaNV();
            
            model.addRow(row);
        }
        tableCTPN.setModel(model);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private CUSTOM.DraggableRoundPanel roundPanel;
    private javax.swing.JLabel sumTotal;
    private javax.swing.JTable tableCTPN;
    private javax.swing.JTable tableCTPNExcel;
    private javax.swing.JTable tableCart;
    // End of variables declaration                   

}
