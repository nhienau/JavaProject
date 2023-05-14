/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import BUS.KhachHangBUS;
import CUSTOM.KiemTra;
import DTO.ChucVu;
import DTO.NhanVien;
import DTO.KhachHang;

/**
 *
 * @author HP
 */
public class KhachHangJPanel extends javax.swing.JPanel {
    private List<KhachHang> customers = null;
    private List<KhachHang> currentList = null;
    private KhachHangBUS khBUS = new KhachHangBUS();
    private String currentAction = null;
    private KhachHang selectedCustomer = null;
    private int selectedCustomerIndex = -1;

    /**
     * Creates new form KhachHangJPanel
     */
    public KhachHangJPanel() {
        initComponents();
    }
    
    public KhachHangJPanel (NhanVien user, ChucVu permission) {
        initComponents();
        
        try {
			customers = khBUS.getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        currentList = customers;
        showData(currentList);
        currentAction = "read";
        auth(permission);
    }
    
    private void showData(List<KhachHang> list) {
    	DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCustomer.setModel(tableModel);

        tableModel.addColumn("Mã khách hàng");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Số điện thoại");
        tableModel.addColumn("Ngày sinh");
        tableModel.addColumn("Điểm hiện tại");
        tableModel.addColumn("Tổng điểm");

        for (KhachHang kh : list) {
        	int id = kh.getMaKH();
        	String name = kh.getTenKH();
        	String phone = kh.getSDT();
        	String dob = kh.getDate();
        	int curPoint = kh.getDiemHienTai();
        	int totalPoint = kh.getTongDiem();
        	tableModel.addRow(new Object[]{id, name, phone, dob, curPoint, totalPoint});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel = new CUSTOM.DraggableRoundPanel();
        pTableControl = new javax.swing.JPanel();
        pSearchBar = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        pCustomerDetail = new javax.swing.JPanel();
        pToolbar = new javax.swing.JPanel();
        btnResetAction = new javax.swing.JButton();
        btnAddCustomer = new javax.swing.JButton();
        btnUpdateCustomer = new javax.swing.JButton();
        btnDeleteCustomer = new javax.swing.JButton();
        pCustomerInfo = new javax.swing.JPanel();
        pCustomerInfo1 = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        lblPhoneNumber = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        pCustomerInfo2 = new javax.swing.JPanel();
        lblDateOfBirth = new javax.swing.JLabel();
        dcDateOfBirth = new com.toedter.calendar.JDateChooser();
        lblCurPoint = new javax.swing.JLabel();
        txtCurPoint = new javax.swing.JTextField();
        lblTotalPoint = new javax.swing.JLabel();
        txtTotalPoint = new javax.swing.JTextField();
        pCustomerInfo3 = new javax.swing.JPanel();
        btnConfirmAction = new javax.swing.JButton();
        lblErrorMessage = new javax.swing.JLabel();
        pTable = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();

        setBackground(new java.awt.Color(51, 51, 51));

        roundPanel.setBackground(new java.awt.Color(51, 51, 51));
        roundPanel.setLayout(new java.awt.BorderLayout());

        pTableControl.setBackground(new java.awt.Color(51, 51, 51));
        pTableControl.setLayout(new java.awt.BorderLayout());

        pSearchBar.setBackground(new java.awt.Color(51, 51, 51));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearch.setEnabled(false);
        txtSearch.setMinimumSize(new java.awt.Dimension(300, 28));
        txtSearch.setPreferredSize(new java.awt.Dimension(300, 28));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        pSearchBar.add(txtSearch);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        btnSearch.setEnabled(false);
        btnSearch.setPreferredSize(new java.awt.Dimension(28, 28));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pSearchBar.add(btnSearch);

        pTableControl.add(pSearchBar, java.awt.BorderLayout.NORTH);

        pCustomerDetail.setBackground(new java.awt.Color(51, 51, 51));
        pCustomerDetail.setLayout(new java.awt.BorderLayout(0, 5));

        pToolbar.setBackground(new java.awt.Color(51, 51, 51));
        pToolbar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnResetAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cursor.png"))); // NOI18N
        btnResetAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionActionPerformed(evt);
            }
        });
        pToolbar.add(btnResetAction);

        btnAddCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/plus.png"))); // NOI18N
        btnAddCustomer.setEnabled(false);
        btnAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCustomerActionPerformed(evt);
            }
        });
        pToolbar.add(btnAddCustomer);

        btnUpdateCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        btnUpdateCustomer.setEnabled(false);
        btnUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCustomerActionPerformed(evt);
            }
        });
        pToolbar.add(btnUpdateCustomer);

        btnDeleteCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        btnDeleteCustomer.setEnabled(false);
        btnDeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCustomerActionPerformed(evt);
            }
        });
        pToolbar.add(btnDeleteCustomer);

        pCustomerDetail.add(pToolbar, java.awt.BorderLayout.NORTH);

        pCustomerInfo.setBackground(new java.awt.Color(51, 51, 51));
        pCustomerInfo.setLayout(new java.awt.GridLayout(1, 3, 20, 20));

        pCustomerInfo1.setBackground(new java.awt.Color(51, 51, 51));
        pCustomerInfo1.setLayout(new java.awt.GridLayout(3, 2, 20, 8));

        lblID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblID.setForeground(new java.awt.Color(245, 245, 245));
        lblID.setText("Mã khách hàng");
        pCustomerInfo1.add(lblID);

        txtID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtID.setEnabled(false);
        txtID.setMinimumSize(new java.awt.Dimension(200, 28));
        txtID.setPreferredSize(new java.awt.Dimension(200, 28));
        pCustomerInfo1.add(txtID);

        lblFullName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFullName.setForeground(new java.awt.Color(245, 245, 245));
        lblFullName.setText("Họ và tên");
        pCustomerInfo1.add(lblFullName);

        txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtFullName.setEnabled(false);
        txtFullName.setMinimumSize(new java.awt.Dimension(200, 28));
        txtFullName.setPreferredSize(new java.awt.Dimension(200, 28));
        pCustomerInfo1.add(txtFullName);

        lblPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPhoneNumber.setForeground(new java.awt.Color(245, 245, 245));
        lblPhoneNumber.setText("Số điện thoại");
        pCustomerInfo1.add(lblPhoneNumber);

        txtPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPhoneNumber.setEnabled(false);
        txtPhoneNumber.setMinimumSize(new java.awt.Dimension(200, 28));
        txtPhoneNumber.setPreferredSize(new java.awt.Dimension(200, 28));
        pCustomerInfo1.add(txtPhoneNumber);

        pCustomerInfo.add(pCustomerInfo1);

        pCustomerInfo2.setBackground(new java.awt.Color(51, 51, 51));
        pCustomerInfo2.setLayout(new java.awt.GridLayout(3, 2, 20, 8));

        lblDateOfBirth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDateOfBirth.setForeground(new java.awt.Color(245, 245, 245));
        lblDateOfBirth.setText("Ngày sinh");
        pCustomerInfo2.add(lblDateOfBirth);

        dcDateOfBirth.setDateFormatString("dd/MM/yyyy");
        dcDateOfBirth.setEnabled(false);
        dcDateOfBirth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dcDateOfBirth.setMinimumSize(new java.awt.Dimension(200, 28));
        dcDateOfBirth.setPreferredSize(new java.awt.Dimension(200, 28));
        pCustomerInfo2.add(dcDateOfBirth);

        lblCurPoint.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCurPoint.setForeground(new java.awt.Color(245, 245, 245));
        lblCurPoint.setText("Điểm hiện tại");
        pCustomerInfo2.add(lblCurPoint);

        txtCurPoint.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCurPoint.setEnabled(false);
        txtCurPoint.setMinimumSize(new java.awt.Dimension(200, 28));
        txtCurPoint.setPreferredSize(new java.awt.Dimension(200, 28));
        pCustomerInfo2.add(txtCurPoint);

        lblTotalPoint.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTotalPoint.setForeground(new java.awt.Color(245, 245, 245));
        lblTotalPoint.setText("Tổng điểm");
        pCustomerInfo2.add(lblTotalPoint);

        txtTotalPoint.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTotalPoint.setEnabled(false);
        txtTotalPoint.setMinimumSize(new java.awt.Dimension(200, 28));
        txtTotalPoint.setPreferredSize(new java.awt.Dimension(200, 28));
        pCustomerInfo2.add(txtTotalPoint);

        pCustomerInfo.add(pCustomerInfo2);

        pCustomerInfo3.setBackground(new java.awt.Color(51, 51, 51));
        pCustomerInfo3.setLayout(new javax.swing.BoxLayout(pCustomerInfo3, javax.swing.BoxLayout.PAGE_AXIS));

        btnConfirmAction.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnConfirmAction.setText("Xác nhận");
        btnConfirmAction.setEnabled(false);
        btnConfirmAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionActionPerformed(evt);
            }
        });
        pCustomerInfo3.add(btnConfirmAction);

        lblErrorMessage.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblErrorMessage.setForeground(new java.awt.Color(255, 102, 102));
        lblErrorMessage.setText("Error message here");
        pCustomerInfo3.add(lblErrorMessage);
        lblErrorMessage.setVisible(false);

        pCustomerInfo.add(pCustomerInfo3);

        pCustomerDetail.add(pCustomerInfo, java.awt.BorderLayout.CENTER);

        pTableControl.add(pCustomerDetail, java.awt.BorderLayout.PAGE_END);

        roundPanel.add(pTableControl, java.awt.BorderLayout.NORTH);

        pTable.setBackground(new java.awt.Color(51, 51, 51));
        pTable.setLayout(new java.awt.BorderLayout());

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Họ và tên", "Số điện thoại", "Ngày sinh", "Điểm hiện tại", "Tổng điểm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCustomerMousePressed(evt);
            }
        });
        scrollPane.setViewportView(tblCustomer);

        pTable.add(scrollPane, java.awt.BorderLayout.CENTER);

        roundPanel.add(pTable, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMousePressed
        // TODO add your handling code here:
    	checkConflictAction();
    	lblErrorMessage.setVisible(false);
        selectedCustomerIndex = tblCustomer.getSelectedRow();
        selectedCustomer = currentList.get(selectedCustomerIndex);
        txtID.setText(Integer.toString(selectedCustomer.getMaKH()));
        txtFullName.setText(selectedCustomer.getTenKH());
        txtPhoneNumber.setText(selectedCustomer.getSDT());
        dcDateOfBirth.setDate(selectedCustomer.getNgaySinh());
        txtCurPoint.setText(Integer.toString(selectedCustomer.getDiemHienTai()));
        txtTotalPoint.setText(Integer.toString(selectedCustomer.getTongDiem()));
    }//GEN-LAST:event_tblCustomerMousePressed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    	search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnResetActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionActionPerformed
        // TODO add your handling code here:
    	reset();
    }//GEN-LAST:event_btnResetActionActionPerformed

    private void btnAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCustomerActionPerformed
        // TODO add your handling code here:
    	currentAction = "add";
    	selectedCustomer = null;
    	selectedCustomerIndex = -1;
    	tblCustomer.clearSelection();
    	clearAllInput();
    	enableAllInput();
    	txtFullName.requestFocus();
    }//GEN-LAST:event_btnAddCustomerActionPerformed

    private void btnUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCustomerActionPerformed
        // TODO add your handling code here:
    	checkConflictAction();
    	int selectedRow = tblCustomer.getSelectedRow();
    	if (selectedRow == -1) {
    		return;
    	}
    	currentAction = "update";
    	enableAllInput();
    }//GEN-LAST:event_btnUpdateCustomerActionPerformed

    private void btnDeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCustomerActionPerformed
        // TODO add your handling code here:
    	int selectedRow = tblCustomer.getSelectedRow();
    	if (selectedRow == -1) {
    		return;
    	}
    	
    	String customerName = selectedCustomer.getTenKH();
    	int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá khách hàng " + customerName + "?", "Cửa hàng máy tính", JOptionPane.OK_CANCEL_OPTION);
    	
        if (option != 0) {
        	return;
        }
        
        int customerID = Integer.parseInt(txtID.getText());
        int result = 0;
       	try {
			result = khBUS.deleteCustomer(customerID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;
	    }

       	if (result > 0) {
       		customers.removeIf(kh -> (kh.getMaKH() == customerID));
       		currentList = customers;
       		txtSearch.setText("");
       		reset();
       		showData(currentList);
       		JOptionPane.showMessageDialog(this, "Xoá khách hàng " + customerName + " thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
       	} else {
       		JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Error", JOptionPane.ERROR_MESSAGE);
       	}
    }//GEN-LAST:event_btnDeleteCustomerActionPerformed

    private void btnConfirmActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionActionPerformed
        // TODO add your handling code here:
    	lblErrorMessage.setVisible(false);
    	lblErrorMessage.setText("");
    	switch (currentAction) {
    		case "add":
    			addCustomer();
    			break;
    		case "update":
    			updateCustomer();
    			break;
    		default:
    	}
    }//GEN-LAST:event_btnConfirmActionActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
    	if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        	search();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCustomer;
    private javax.swing.JButton btnConfirmAction;
    private javax.swing.JButton btnDeleteCustomer;
    private javax.swing.JButton btnResetAction;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateCustomer;
    private com.toedter.calendar.JDateChooser dcDateOfBirth;
    private javax.swing.JLabel lblCurPoint;
    private javax.swing.JLabel lblDateOfBirth;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblTotalPoint;
    private javax.swing.JPanel pCustomerDetail;
    private javax.swing.JPanel pCustomerInfo;
    private javax.swing.JPanel pCustomerInfo1;
    private javax.swing.JPanel pCustomerInfo2;
    private javax.swing.JPanel pCustomerInfo3;
    private javax.swing.JPanel pSearchBar;
    private javax.swing.JPanel pTable;
    private javax.swing.JPanel pTableControl;
    private javax.swing.JPanel pToolbar;
    private CUSTOM.DraggableRoundPanel roundPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtCurPoint;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotalPoint;
    // End of variables declaration//GEN-END:variables
    
    private void reset() { 
    	currentAction = "read";
    	selectedCustomer = null;
    	selectedCustomerIndex = -1;
    	tblCustomer.clearSelection();
    	disableAllInput();
    	clearAllInput();
    	lblErrorMessage.setVisible(false);
    	lblErrorMessage.setText("");
    }
    
    private void checkConflictAction() {
    	if (currentAction.equals("add")) {
    		currentAction = "read";
        	disableAllInput();
        	clearAllInput();
    	}
    }
    
    private void disableAllInput() {
    	JTextField [] arr = {txtFullName, txtPhoneNumber, txtCurPoint, txtTotalPoint};
    	for (JTextField t : arr) {
    		t.setEnabled(false);
    	}
    	dcDateOfBirth.setEnabled(false);
    	btnConfirmAction.setEnabled(false);
    }
    
    private void enableAllInput() {
    	JTextField [] arr = {txtFullName, txtPhoneNumber, txtCurPoint, txtTotalPoint};
    	for (JTextField t : arr) {
    		t.setEnabled(true);
    	}
    	dcDateOfBirth.setEnabled(true);
    	btnConfirmAction.setEnabled(true);
    }
    
    private void clearAllInput() {
    	JTextField [] arr = {txtID, txtFullName, txtPhoneNumber, txtCurPoint, txtTotalPoint};
    	for (JTextField t : arr) {
    		t.setText("");
    	}
    	dcDateOfBirth.setDate(null);
    }
    
    private boolean infoChanged() {
    	boolean dateChanged = false;
    	try {
    		dateChanged = !dcDateOfBirth.getDate().equals(selectedCustomer.getNgaySinh());
    	} catch (NullPointerException e) {
    		dateChanged = true;
    	}

    	return !txtFullName.getText().trim().equals(selectedCustomer.getTenKH()) || !txtPhoneNumber.getText().trim().equals(selectedCustomer.getSDT()) || dateChanged || !(txtCurPoint.getText().trim().equals("" + selectedCustomer.getDiemHienTai())) || !(txtTotalPoint.getText().trim().equals("" + selectedCustomer.getTongDiem()));
    }
    
    private boolean allInputEmpty() {
    	boolean dateEmpty = true;
    	try {
    		dateEmpty = dcDateOfBirth.getDate() == null;
    	} catch (NullPointerException e) {
    		dateEmpty = true;
    	}
    	return txtFullName.getText().isEmpty() && txtPhoneNumber.getText().isEmpty() && dateEmpty && txtCurPoint.getText().isEmpty() && txtTotalPoint.getText().isEmpty();
    }
    
    private boolean anyInputEmpty() {
    	boolean dateEmpty = true;
    	try {
    		dateEmpty = dcDateOfBirth.getDate() == null;
    	} catch (NullPointerException e) {
    		dateEmpty = true;
    	}
    	return txtFullName.getText().isEmpty() || txtPhoneNumber.getText().isEmpty() || dateEmpty || txtCurPoint.getText().isEmpty() || txtTotalPoint.getText().isEmpty();
    }
    
    private boolean validateRegex() throws ParseException {
    	KiemTra validate = new KiemTra();
    	boolean valid = true;
    	
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	Date today = format.parse(format.format(new Date())); // only get date, time is set to 00:00:00

    	if (!validate.KTHoVaTen(txtFullName.getText().trim())) {
    		valid = false;
    		lblErrorMessage.setText("Họ và tên không hợp lệ");
    		txtFullName.requestFocus();
    	} else if (!validate.isValidPhone(txtPhoneNumber.getText().trim())) {
    		valid = false;
    		lblErrorMessage.setText("Số điện thoại không hợp lệ");
    		txtPhoneNumber.requestFocus();
    	} else if (dcDateOfBirth.getDate().compareTo(today) >= 0) {
    		valid = false;
    		lblErrorMessage.setText("Ngày sinh không hợp lệ");
    		dcDateOfBirth.requestFocus();
    	} else if (!validate.KTSo(txtCurPoint.getText().trim())) {
    		valid = false;
    		lblErrorMessage.setText("Điểm hiện tại phải là số");
    		txtCurPoint.requestFocus();
    	} else if (!validate.KTSo(txtTotalPoint.getText().trim())) {
    		valid = false;
    		lblErrorMessage.setText("Tổng điểm phải là số");
    		txtTotalPoint.requestFocus();
    	}
    	
    	int curPoint = -1;
    	int totalPoint = -1;
    	
    	try {
    		curPoint = Integer.parseInt(txtCurPoint.getText().trim());
    	} catch (NumberFormatException e) {

    	}
    	
    	try {
    		totalPoint = Integer.parseInt(txtTotalPoint.getText().trim());
    	} catch (NumberFormatException e) {

    	}
    	
    	if (curPoint != -1 && totalPoint != -1 && curPoint > totalPoint) {
    		valid = false;
    		lblErrorMessage.setText("Điểm hiện tại không thể lớn hơn tổng điểm");
    		txtCurPoint.requestFocus();
    	}
    	
    	if (!valid) {
    		lblErrorMessage.setVisible(true);
    	}

    	return valid;
    }
    
    private void printAllInfo(KhachHang kh) {
    	System.out.println(kh.getMaKH() + " " + kh.getTenKH() + " " + kh.getSDT() + " " + kh.getDate() + " " + kh.getDiemHienTai() + " " + kh.getTongDiem());
    }

    private void search() {
    	reset();
    	
    	String input = txtSearch.getText().trim();
    	
    	if (input.isEmpty()) {
    		// Clear table
    		DefaultTableModel dtm = (DefaultTableModel) tblCustomer.getModel();
        	dtm.setRowCount(0);
        	
        	// Load all
        	currentList = customers;
        	showData(currentList);
        	return;
    	}
    	
    	// Search in customers ArrayList
    	List<KhachHang> temp = new ArrayList<>();
    	int inputInt = 0;
    	try {
    		inputInt = Integer.parseInt(input);
    	} catch (Exception e) {
    		
    	}

    	for (KhachHang kh: customers) {  		
    		if (kh.getMaKH() == inputInt || kh.getTenKH().toLowerCase().contains(input.toLowerCase()) || kh.getSDT().contains(input)) {
    			temp.add(kh);
    		}
    	}
    	
    	// Clear table
    	DefaultTableModel dtm = (DefaultTableModel) tblCustomer.getModel();
    	dtm.setRowCount(0);
    	
    	if (temp.isEmpty()) {
    		return;
    	}
    	
    	currentList = temp;
    	showData(currentList);
    }

    private void addCustomer() {
    	if (allInputEmpty()) {
    		txtFullName.requestFocus();
    		return;
    	}
    	
    	if (anyInputEmpty()) {
    		lblErrorMessage.setText("Thông tin không được bỏ trống");
    		txtFullName.requestFocus();
    		lblErrorMessage.setVisible(true);
    		return;
    	}
    	
    	try {
    		if (!validateRegex()) {
    			return;
    		}
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	
    	for (KhachHang kh: customers) {  		
    		if (txtPhoneNumber.getText().trim().equals(kh.getSDT())) {
    			lblErrorMessage.setText("Số điện thoại đã tồn tại");
        		txtPhoneNumber.requestFocus();
        		lblErrorMessage.setVisible(true);
    			return;
    		}
    	}
    	
    	// Store new info
    	KhachHang temp = new KhachHang();
    	temp.setTenKH(txtFullName.getText().trim());
    	temp.setSDT(txtPhoneNumber.getText().trim());
    	temp.setNgaySinh(dcDateOfBirth.getDate());
    	temp.setDiemHienTai(Integer.parseInt(txtCurPoint.getText().trim()));
    	temp.setTongDiem(Integer.parseInt(txtTotalPoint.getText().trim()));
    	
    	int result = 0;
       	try {
			result = khBUS.addCustomer(temp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;
	    }
       	
       	if (result > 0) {
       		try {
    			customers = khBUS.getAll();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
            currentList = customers;
            showData(currentList);
            clearAllInput();
            reset();
            JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
       	} else {
       		JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Error", JOptionPane.ERROR_MESSAGE);
       	}
    }
    
    private void updateCustomer() {
    	if (!infoChanged()) {
    		return;
    	}
    	
    	if (anyInputEmpty()) {
    		lblErrorMessage.setText("Thông tin không được bỏ trống");
    		txtFullName.requestFocus();
    		lblErrorMessage.setVisible(true);
    		return;
    	}
    	
    	try {
    		if (!validateRegex()) {
    			return;
    		}
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	
    	for (KhachHang kh: customers) {
    		if (kh.getMaKH() == Integer.parseInt(txtID.getText().trim())) {
    			continue;
    		}
    		
    		if (txtPhoneNumber.getText().trim().equals(kh.getSDT())) {
    			lblErrorMessage.setText("Số điện thoại đã tồn tại");
        		txtPhoneNumber.requestFocus();
        		lblErrorMessage.setVisible(true);
        		return;
    		}
    	}
    	
    	// Store new info
    	KhachHang temp = new KhachHang();
    	temp.setMaKH(Integer.parseInt(txtID.getText().trim()));
    	temp.setTenKH(txtFullName.getText().trim());
    	temp.setSDT(txtPhoneNumber.getText().trim());
    	temp.setNgaySinh(dcDateOfBirth.getDate());
    	temp.setDiemHienTai(Integer.parseInt(txtCurPoint.getText().trim()));
    	temp.setTongDiem(Integer.parseInt(txtTotalPoint.getText().trim()));
    	
    	int result = 0;
       	try {
			result = khBUS.updateCustomer(temp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Lỗi không xác định", "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	        return;
	    }
       	
       	if (result > 0) {
       		currentList.get(selectedCustomerIndex).setTenKH(temp.getTenKH());
       		currentList.get(selectedCustomerIndex).setSDT(temp.getSDT());
       		currentList.get(selectedCustomerIndex).setNgaySinh(temp.getNgaySinh());
       		currentList.get(selectedCustomerIndex).setDiemHienTai(temp.getDiemHienTai());
       		currentList.get(selectedCustomerIndex).setTongDiem(temp.getTongDiem());
       		
       		for (KhachHang kh : customers) {
       			if (kh.getMaKH() == temp.getMaKH()) {
       				kh.setTenKH(temp.getTenKH());
       				kh.setSDT(temp.getSDT());
       				kh.setNgaySinh(temp.getNgaySinh());
       				kh.setDiemHienTai(temp.getDiemHienTai());
       				kh.setTongDiem(temp.getTongDiem());
       				break;
       			}
       		}
       		
            showData(currentList);
            clearAllInput();
            reset();
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
       	} else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra, vui lòng thử lại", "Error", JOptionPane.ERROR_MESSAGE);
       	}
    }
    
    private void auth(ChucVu permission) {
    	String allPermission = permission.getKhachHang();
    	boolean toggleAdd = allPermission.contains("them");
    	boolean toggleUpdate = allPermission.contains("sua");
    	boolean toggleDelete = allPermission.contains("xoa");
    	boolean toggleSearch = allPermission.contains("timkiem");

    	btnAddCustomer.setEnabled(toggleAdd);
    	btnUpdateCustomer.setEnabled(toggleUpdate);
    	btnDeleteCustomer.setEnabled(toggleDelete);
    	btnSearch.setEnabled(toggleSearch);
    	txtSearch.setEnabled(toggleSearch);
    }
}
