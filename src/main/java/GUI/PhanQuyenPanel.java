/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import BUS.ChucVuBUS;
import BUS.NhanVienBUS;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import CUSTOM.DraggableRoundPanel;
import CUSTOM.KiemTra;
import DTO.ChucVu;

import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import javax.swing.border.LineBorder;

/**
 *
 * @author Admin
 */
public class PhanQuyenPanel extends javax.swing.JPanel {

    private List<JCheckBox> parentCheckBoxList;
    private List<ChucVu> chucVuList;
    private HashMap<JCheckBox, List<JCheckBox>> childCheckBoxHashMap;
    private HashMap<JCheckBox, JPanel> panelHashMap;
    private NhanVienBUS nvBUS;
    private ChucVuBUS cvBUS;
    private KiemTra kTra;
    
    private boolean searchDencen;
    private boolean addDencen;
    private boolean editDencen;
    private boolean delDencen;
    
    
    /**
     * Creates new form NhanVienJPanel
     * @throws SQLException 
     * @throws ClassNotFoundException 
     */
    public PhanQuyenPanel() throws ClassNotFoundException, SQLException {
    	setForeground(new Color(31, 31, 31));
        initComponents();
        cvBUS = new ChucVuBUS();
        nvBUS = new NhanVienBUS();
        kTra = new KiemTra();
        jLabel1.setIcon(new ImageIcon("src\\main\\java\\Images\\account-img.png"));
        jLabel2.setIcon(new ImageIcon("src\\main\\java\\Images\\image-removebg-preview (1).png"));
        delIconLabel.setIcon(new ImageIcon("src\\main\\java\\Images\\delete.png"));
        addIconLabel.setIcon(new ImageIcon("src\\main\\java\\Images\\plus.png"));
        editIconLabel.setIcon(new ImageIcon("src\\main\\java\\Images\\edit.png"));
        
        
        setDencenDetails();
        setRowColorBackground(this.positionListTable);

        initData();
        initTable();
        addSelectedCheckBoxEvent();
        searchTFListener();
        
        addAllBorderFocus();
        
    }

    private void setDencenDetails() {
    	searchDencen = true;
    	addDencen = true;
    	delDencen = true;
    	editDencen = true;
    	
    	if(searchDencen) {
    		searchTextField.setEditable(true);
    	}else {
    		searchTextField.setEditable(false);
    		searchTextField.setBackground(Color.GRAY);
    	}
    	
    	EnableRoundPanelBtn(delRoundPanel, false);;
        EnableRoundPanelBtn(editRoundPanel, false);
        EnableRoundPanelBtn(addRoundPanel, true);
    }
    
    private void searchTFListener() {
    	searchTextField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String inputString = searchTextField.getText();
				try {
					search(inputString);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String inputString = searchTextField.getText();
				try {
					search(inputString);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String inputString = searchTextField.getText();
				try {
					search(inputString);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			public void search(String input) throws ClassNotFoundException, SQLException {
				chucVuList = cvBUS.search(input);
				initTable();
			}
		});
    }
    
    private void initData() throws ClassNotFoundException, SQLException {
        parentCheckBoxList = new ArrayList<>();
        this.parentCheckBoxList.add(billCheckBox);
        this.parentCheckBoxList.add(customerCheckBox);
        this.parentCheckBoxList.add(productCheckBox);
        this.parentCheckBoxList.add(employeeCheckBox);
        this.parentCheckBoxList.add(dencenCheckBox);
        this.parentCheckBoxList.add(importCheckBox);
        this.parentCheckBoxList.add(discountCheckBox);

        childCheckBoxHashMap = new HashMap<>();

        List<JCheckBox> childBillCheckBoxList = new ArrayList<>();
        childBillCheckBoxList.add(addBillCheckBox);
        childBillCheckBoxList.add(updateBillCheckBox);
        childBillCheckBoxList.add(delBillCheckBox);
        childBillCheckBoxList.add(searchBillCheckBox);

        List<JCheckBox> childCustomerCheckBoxList = new ArrayList<>();
        childCustomerCheckBoxList.add(addCustomerCheckBox);
        childCustomerCheckBoxList.add(updateCustomerCheckBox);
        childCustomerCheckBoxList.add(delCustomerCheckBox);
        childCustomerCheckBoxList.add(searchCustomerCheckBox);

        List<JCheckBox> childDiscountCheckBoxList = new ArrayList<>();
        childDiscountCheckBoxList.add(addDiscountCheckBox);
        childDiscountCheckBoxList.add(updateDiscountCheckBox);
        childDiscountCheckBoxList.add(delDiscountCheckBox);
        childDiscountCheckBoxList.add(searchDiscountCheckBox);

        List<JCheckBox> childEmployeeCheckBoxList = new ArrayList<>();
        childEmployeeCheckBoxList.add(addEmployeeCheckBox);
        childEmployeeCheckBoxList.add(updateEmployeeCheckBox);
        childEmployeeCheckBoxList.add(delEmployeeCheckBox);
        childEmployeeCheckBoxList.add(searchEmployeeCheckBox);

        List<JCheckBox> childDencenCheckBoxList = new ArrayList<>();
        childDencenCheckBoxList.add(addDencenCheckBox);
        childDencenCheckBoxList.add(updateDencenCheckBox);
        childDencenCheckBoxList.add(delDencenCheckBox);
        childDencenCheckBoxList.add(searchDencenCheckBox);

        List<JCheckBox> childImportCheckBoxList = new ArrayList<>();
        childImportCheckBoxList.add(addImportCheckBox);
        childImportCheckBoxList.add(updateImportCheckBox);
        childImportCheckBoxList.add(delImportCheckBox);
        childImportCheckBoxList.add(searchImportCheckBox);

        List<JCheckBox> childProductCheckBoxList = new ArrayList<>();
        childProductCheckBoxList.add(addProductCheckBox);
        childProductCheckBoxList.add(updateProductCheckBox);
        childProductCheckBoxList.add(delProductCheckBox);
        childProductCheckBoxList.add(searchProductCheckBox);

        childCheckBoxHashMap.put(billCheckBox, childBillCheckBoxList);
        childCheckBoxHashMap.put(importCheckBox, childImportCheckBoxList);
        childCheckBoxHashMap.put(employeeCheckBox, childEmployeeCheckBoxList);
        childCheckBoxHashMap.put(discountCheckBox, childDiscountCheckBoxList);
        childCheckBoxHashMap.put(customerCheckBox, childCustomerCheckBoxList);
        childCheckBoxHashMap.put(productCheckBox, childProductCheckBoxList);
        childCheckBoxHashMap.put(dencenCheckBox, childDencenCheckBoxList);        
        
        panelHashMap = new HashMap<>();

        panelHashMap.put(billCheckBox, billPanel);
        panelHashMap.put(customerCheckBox, customerPanel);
        panelHashMap.put(employeeCheckBox, employPanel);
        panelHashMap.put(productCheckBox, productPanel);
        panelHashMap.put(discountCheckBox, discountPanel);
        panelHashMap.put(statiscCheckBox, statisPanel);
        panelHashMap.put(importCheckBox, importPanel);
        panelHashMap.put(dencenCheckBox, decenPanel);
        
        
        chucVuList = cvBUS.takeAll();
    }

    private void initTable()  {
    	
    	DefaultTableModel tableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row,int column) {
    			return false;
    		}
    	};
    	
    	tableModel.addColumn("Mã chức vụ");
    	tableModel.addColumn("Tên chức vụ");
    	tableModel.addColumn("Hóa đơn");
    	tableModel.addColumn("Khách hàng");
    	tableModel.addColumn("Nhân viên");
    	tableModel.addColumn("Khuyến mãi");
    	tableModel.addColumn("Sản phẩm");
    	tableModel.addColumn("Phân quyền");
    	tableModel.addColumn("Thống kê");
    	tableModel.addColumn("Nhập hàng");
    	positionListTable.setModel(tableModel);
    	for(ChucVu cv : chucVuList) {
    		String maCV = "CV"+cv.getMaCV();
    		String tenCV = cv.getTenCV();
    		String hoaDon = cv.getHoaDon();
    		String khachHang = cv.getKhachHang();
    		String nhanVien = cv.getNhanVien();
    		String khuyenMai = cv.getKhuyenMai();
    		String sanPham = cv.getSanPham();
    		String phanQuyen = cv.getPhanQuyen();
    		String thongKe = cv.getThongKe();
    		String nhapHang = cv.getNhapHang();
    		tableModel.addRow(new Object[] {maCV,tenCV,hoaDon,khachHang,nhanVien,khuyenMai,
    				sanPham,phanQuyen,thongKe,nhapHang});
    	}
    	
    }
    
    public void addSelectedCheckBoxEvent() {
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JCheckBox cb = (JCheckBox) e.getItem();
                    if (parentCheckBoxList.contains(cb)) {
                        List<JCheckBox> cbChildList = childCheckBoxHashMap.get(cb);
                        for (JCheckBox cbb : cbChildList) {
                            cbb.setEnabled(true);
                            cbb.setSelected(true);
                        }
                        panelHashMap.get(cb).setBorder(BorderFactory.createLineBorder(new Color(255,101,0)));
                    }
                    if(cb == statiscCheckBox) {
                    	statisPanel.setBorder(BorderFactory.createLineBorder(new Color(255,101,0)));
                    }

                    cb.setForeground(new Color(255, 101, 0));
                } else {
                    JCheckBox cbSelected = (JCheckBox) e.getItem();
                    if (parentCheckBoxList.contains(cbSelected)) {
                        for (JCheckBox cbb : childCheckBoxHashMap.get(cbSelected)) {
                            cbb.setEnabled(false);
                            cbb.setSelected(false);
                            cbb.setForeground(Color.GRAY);
                        }
                        cbSelected.setForeground(Color.WHITE);
                        
                        panelHashMap.get(cbSelected).setBorder(BorderFactory.createLineBorder(new Color(31,31,31)));
                    }
                    else{
                    	if(cbSelected == statiscCheckBox)
                    		statisPanel.setBorder(BorderFactory.createLineBorder(new Color(31,31,31)));
                    	
                    	cbSelected.setForeground(Color.white);
                        for(JCheckBox parentCheckBox : parentCheckBoxList){
                            if(childCheckBoxHashMap.get(parentCheckBox).contains(cbSelected) && 
                                    allChildCheckBoxIsNotSelected(childCheckBoxHashMap.get(parentCheckBox))){
                                parentCheckBox.setSelected(false);
                            }
                        }
                        
                    }

                }
            }
        };
        for (JCheckBox parentCheckBox : parentCheckBoxList) {
            parentCheckBox.addItemListener(itemListener);
            for (JCheckBox childCheckBox : childCheckBoxHashMap.get(parentCheckBox)) {
                childCheckBox.addItemListener(itemListener);
            }
        }
        statiscCheckBox.addItemListener(itemListener);
    }

    public boolean allChildCheckBoxIsNotSelected(List<JCheckBox> childList){
        for(JCheckBox childCheckBox : childList){
            if(childCheckBox.isSelected())
                return false;
        }
        return true;
    }
    
    public void addAllBorderFocus() {
        FocusListener fcListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField j = (JTextField) e.getComponent();
                j.setBorder(BorderFactory.createLineBorder(new Color(255, 101, 0)));
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField j = (JTextField) e.getComponent();
                j.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
            }
        };
        
        nameTextField.addFocusListener(fcListener);
        searchTextField.addFocusListener(fcListener);
    }

    public void setRowColorBackground(JTable table) {
        // Set the selection mode to single selection
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set the cell renderer to highlight the selected row
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // If the row is selected, set the background and foreground colors
                if (isSelected) {
                    Color Orange = new Color(255, 101, 0);

                    component.setBackground(Color.WHITE);
                    component.setForeground(Orange);
                } else {

                    Color Black = new Color(32, 32, 32);
                    Color Gray = new Color(155, 153, 151);
                    component.setBackground(Black);
                    component.setForeground(Color.WHITE);
                }

                return component;
            }
        });

        // Add a list selection listener to the table to detect when a row is selected
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = table.getSelectedRow();

                // If a row is selected, repaint the table to update the cell renderer
                if (selectedRow >= 0) {
                    table.repaint();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        draggableRoundPanel1 = new CUSTOM.DraggableRoundPanel();
        draggableRoundPanel2 = new CUSTOM.DraggableRoundPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        draggableRoundPanel3 = new CUSTOM.DraggableRoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane2.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyChar() == KeyEvent.VK_DELETE) {
        			try {
						delPosition();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
        
        positionListTable = new javax.swing.JTable();
        positionListTable.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyChar() == KeyEvent.VK_DELETE) {
        			try {
						delPosition();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
      
       
        jLabel1 = new javax.swing.JLabel();
        draggableRoundPanel4 = new CUSTOM.DraggableRoundPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        iDTextField = new javax.swing.JTextField();
        iDTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jPanel7 = new javax.swing.JPanel();
        billPanel = new javax.swing.JPanel();
        billCheckBox = new javax.swing.JCheckBox();
        billCheckBox.setBackground(new Color(31, 31, 31));
        addBillCheckBox = new javax.swing.JCheckBox();
        addBillCheckBox.setBackground(new Color(31, 31, 31));
        updateBillCheckBox = new javax.swing.JCheckBox();
        updateBillCheckBox.setBackground(new Color(31, 31, 31));
        delBillCheckBox = new javax.swing.JCheckBox();
        delBillCheckBox.setBackground(new Color(31, 31, 31));
        searchBillCheckBox = new javax.swing.JCheckBox();
        searchBillCheckBox.setBackground(new Color(31, 31, 31));
        customerPanel = new javax.swing.JPanel();
        customerCheckBox = new javax.swing.JCheckBox();
        customerCheckBox.setBackground(new Color(31, 31, 31));
        addCustomerCheckBox = new javax.swing.JCheckBox();
        addCustomerCheckBox.setBackground(new Color(31, 31, 31));
        updateCustomerCheckBox = new javax.swing.JCheckBox();
        updateCustomerCheckBox.setBackground(new Color(31, 31, 31));
        delCustomerCheckBox = new javax.swing.JCheckBox();
        delCustomerCheckBox.setBackground(new Color(31, 31, 31));
        searchCustomerCheckBox = new javax.swing.JCheckBox();
        searchCustomerCheckBox.setBackground(new Color(31, 31, 31));
        employPanel = new javax.swing.JPanel();
        employeeCheckBox = new javax.swing.JCheckBox();
        employeeCheckBox.setBackground(new Color(31, 31, 31));
        addEmployeeCheckBox = new javax.swing.JCheckBox();
        addEmployeeCheckBox.setBackground(new Color(31, 31, 31));
        updateEmployeeCheckBox = new javax.swing.JCheckBox();
        updateEmployeeCheckBox.setBackground(new Color(31, 31, 31));
        delEmployeeCheckBox = new javax.swing.JCheckBox();
        delEmployeeCheckBox.setBackground(new Color(31, 31, 31));
        searchEmployeeCheckBox = new javax.swing.JCheckBox();
        searchEmployeeCheckBox.setBackground(new Color(31, 31, 31));
        discountPanel = new javax.swing.JPanel();
        discountCheckBox = new javax.swing.JCheckBox();
        discountCheckBox.setBackground(new Color(31, 31, 31));
        addDiscountCheckBox = new javax.swing.JCheckBox();
        addDiscountCheckBox.setBackground(new Color(31, 31, 31));
        updateDiscountCheckBox = new javax.swing.JCheckBox();
        updateDiscountCheckBox.setBackground(new Color(31, 31, 31));
        delDiscountCheckBox = new javax.swing.JCheckBox();
        delDiscountCheckBox.setBackground(new Color(31, 31, 31));
        searchDiscountCheckBox = new javax.swing.JCheckBox();
        searchDiscountCheckBox.setBackground(new Color(31, 31, 31));
        productPanel = new javax.swing.JPanel();
        productCheckBox = new javax.swing.JCheckBox();
        productCheckBox.setBackground(new Color(31, 31, 31));
        addProductCheckBox = new javax.swing.JCheckBox();
        addProductCheckBox.setBackground(new Color(31, 31, 31));
        updateProductCheckBox = new javax.swing.JCheckBox();
        updateProductCheckBox.setBackground(new Color(31, 31, 31));
        delProductCheckBox = new javax.swing.JCheckBox();
        delProductCheckBox.setBackground(new Color(31, 31, 31));
        searchProductCheckBox = new javax.swing.JCheckBox();
        searchProductCheckBox.setBackground(new Color(31, 31, 31));
        decenPanel = new javax.swing.JPanel();
        dencenCheckBox = new javax.swing.JCheckBox();
        dencenCheckBox.setBackground(new Color(31, 31, 31));
        addDencenCheckBox = new javax.swing.JCheckBox();
        addDencenCheckBox.setBackground(new Color(31, 31, 31));
        updateDencenCheckBox = new javax.swing.JCheckBox();
        updateDencenCheckBox.setBackground(new Color(31, 31, 31));
        delDencenCheckBox = new javax.swing.JCheckBox();
        delDencenCheckBox.setBackground(new Color(31, 31, 31));
        searchDencenCheckBox = new javax.swing.JCheckBox();
        searchDencenCheckBox.setBackground(new Color(31, 31, 31));
        statisPanel = new javax.swing.JPanel();
        statiscCheckBox = new javax.swing.JCheckBox();
        statiscCheckBox.setBackground(new Color(31, 31, 31));
        importPanel = new javax.swing.JPanel();
        importCheckBox = new javax.swing.JCheckBox();
        importCheckBox.setBackground(new Color(31, 31, 31));
        addImportCheckBox = new javax.swing.JCheckBox();
        addImportCheckBox.setBackground(new Color(31, 31, 31));
        updateImportCheckBox = new javax.swing.JCheckBox();
        updateImportCheckBox.setBackground(new Color(31, 31, 31));
        delImportCheckBox = new javax.swing.JCheckBox();
        delImportCheckBox.setBackground(new Color(31, 31, 31));
        searchImportCheckBox = new javax.swing.JCheckBox();
        searchImportCheckBox.setBackground(new Color(31, 31, 31));
        jPanel16 = new javax.swing.JPanel();

        setBackground(new Color(31, 31, 31));

        draggableRoundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        draggableRoundPanel2.setBackground(new java.awt.Color(32, 32, 32));

        jPanel1.setBackground(new java.awt.Color(31, 31, 31));

        jLabel2.setText("jLabel2");

        searchTextField.setBackground(new java.awt.Color(32, 32, 32));
        searchTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchTextField.setForeground(new java.awt.Color(255, 255, 255));
        searchTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        searchTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        searchTextField.setSelectionColor(new java.awt.Color(255, 101, 0));
        
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        			.addComponent(searchTextField, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        			.addComponent(jLabel2))
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout draggableRoundPanel2Layout = new javax.swing.GroupLayout(draggableRoundPanel2);
        draggableRoundPanel2Layout.setHorizontalGroup(
        	draggableRoundPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(draggableRoundPanel2Layout.createSequentialGroup()
        			.addGap(112)
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(164, Short.MAX_VALUE))
        );
        draggableRoundPanel2Layout.setVerticalGroup(
        	draggableRoundPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(draggableRoundPanel2Layout.createSequentialGroup()
        			.addGap(20)
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(22, Short.MAX_VALUE))
        );
        draggableRoundPanel2.setLayout(draggableRoundPanel2Layout);

        draggableRoundPanel3.setBackground(new java.awt.Color(31, 31, 31));

        jScrollPane2.setBackground(new java.awt.Color(32, 32, 32));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        positionListTable.setBackground(new Color(128, 128, 0));
        positionListTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        positionListTable.setForeground(new java.awt.Color(240, 240, 240));
        positionListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        positionListTable.setGridColor(new java.awt.Color(255, 255, 255));
        positionListTable.setSelectionBackground(new java.awt.Color(32, 32, 32));
        positionListTable.setSelectionForeground(new java.awt.Color(255, 101, 0));
        positionListTable.setShowGrid(true);
        positionListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                positionListTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(positionListTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 101, 0));
        jLabel1.setText("DANH SÁCH CHỨC VỤ");

        javax.swing.GroupLayout draggableRoundPanel3Layout = new javax.swing.GroupLayout(draggableRoundPanel3);
        draggableRoundPanel3Layout.setHorizontalGroup(
        	draggableRoundPanel3Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(draggableRoundPanel3Layout.createSequentialGroup()
        			.addGroup(draggableRoundPanel3Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(draggableRoundPanel3Layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
        				.addGroup(draggableRoundPanel3Layout.createSequentialGroup()
        					.addGap(213)
        					.addComponent(jLabel1)))
        			.addContainerGap())
        );
        draggableRoundPanel3Layout.setVerticalGroup(
        	draggableRoundPanel3Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(draggableRoundPanel3Layout.createSequentialGroup()
        			.addGap(12)
        			.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE)
        			.addGap(22))
        );
        draggableRoundPanel3.setLayout(draggableRoundPanel3Layout);

        draggableRoundPanel4.setBackground(new java.awt.Color(31, 31, 31));

        jPanel2.setBackground(new java.awt.Color(32, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 101, 0));
        jLabel3.setText("THÔNG TIN CHỨC VỤ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(131)
        			.addComponent(jLabel3)
        			.addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jLabel3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
        );
        jPanel2.setLayout(jPanel2Layout);

        jPanel4.setBackground(new java.awt.Color(32, 32, 32));

        jPanel5.setBackground(new java.awt.Color(32, 32, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(118, 118, 118));
        jLabel5.setText("Mã chức vụ");

        iDTextField.setEditable(false);
        iDTextField.setBackground(new java.awt.Color(118, 118, 118));
        iDTextField.setForeground(new Color(51, 51, 51));
        iDTextField.setBorder(null);
        iDTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5Layout.setHorizontalGroup(
        	jPanel5Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel5Layout.createSequentialGroup()
        			.addComponent(jLabel5)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(iDTextField, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
        			.addGap(26))
        );
        jPanel5Layout.setVerticalGroup(
        	jPanel5Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel5Layout.createSequentialGroup()
        			.addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(iDTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel5))
        			.addGap(1))
        );
        jPanel5.setLayout(jPanel5Layout);

        jPanel6.setBackground(new java.awt.Color(32, 32, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(210, 210, 210));
        jLabel6.setText("Tên chức vụ");

        nameTextField.setBackground(new java.awt.Color(32, 32, 32));
        nameTextField.setForeground(new java.awt.Color(255, 255, 255));
        nameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        nameTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6Layout.setHorizontalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addComponent(jLabel6)
        			.addGap(8)
        			.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
        			.addGap(25))
        );
        jPanel6Layout.setVerticalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addGroup(jPanel6Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel6))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6.setLayout(jPanel6Layout);

        jPanel7.setBackground(new java.awt.Color(31, 31, 31));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        billPanel.setBackground(new java.awt.Color(31, 31, 31));
        billPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        billCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        billCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        billCheckBox.setText("Hóa đơn");
        

        addBillCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addBillCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        addBillCheckBox.setText("Thêm");
        addBillCheckBox.setEnabled(false);

        updateBillCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateBillCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        updateBillCheckBox.setText("Sửa");
        updateBillCheckBox.setEnabled(false);

        delBillCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delBillCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        delBillCheckBox.setText("Xóa");
        delBillCheckBox.setEnabled(false);

        searchBillCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchBillCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        searchBillCheckBox.setText("Tìm kiếm");
        searchBillCheckBox.setEnabled(false);

        javax.swing.GroupLayout billPanelLayout = new javax.swing.GroupLayout(billPanel);
        billPanel.setLayout(billPanelLayout);
        billPanelLayout.setHorizontalGroup(
            billPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billCheckBox)
                .addGap(34, 34, 34)
                .addComponent(addBillCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateBillCheckBox)
                .addGap(18, 18, 18)
                .addComponent(delBillCheckBox)
                .addGap(18, 18, 18)
                .addComponent(searchBillCheckBox)
                .addGap(6, 6, 6))
        );
        billPanelLayout.setVerticalGroup(
            billPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(billPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(billCheckBox)
                    .addComponent(updateBillCheckBox)
                    .addComponent(delBillCheckBox)
                    .addComponent(searchBillCheckBox)
                    .addComponent(addBillCheckBox))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        customerPanel.setBackground(new java.awt.Color(31, 31, 31));
        customerPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        customerCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        customerCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        customerCheckBox.setText("Khách hàng");
        

        addCustomerCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addCustomerCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        addCustomerCheckBox.setText("Thêm");
        addCustomerCheckBox.setEnabled(false);

        updateCustomerCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateCustomerCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        updateCustomerCheckBox.setText("Sửa");
        updateCustomerCheckBox.setEnabled(false);

        delCustomerCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delCustomerCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        delCustomerCheckBox.setText("Xóa");
        delCustomerCheckBox.setEnabled(false);
        

        searchCustomerCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchCustomerCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        searchCustomerCheckBox.setText("Tìm kiếm");
        searchCustomerCheckBox.setEnabled(false);

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerCheckBox)
                .addGap(15, 15, 15)
                .addComponent(addCustomerCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateCustomerCheckBox)
                .addGap(18, 18, 18)
                .addComponent(delCustomerCheckBox)
                .addGap(18, 18, 18)
                .addComponent(searchCustomerCheckBox)
                .addGap(6, 6, 6))
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerCheckBox)
                    .addComponent(addCustomerCheckBox)
                    .addComponent(updateCustomerCheckBox)
                    .addComponent(delCustomerCheckBox)
                    .addComponent(searchCustomerCheckBox))
                .addGap(7, 7, 7))
        );

        employPanel.setBackground(new java.awt.Color(31, 31, 31));
        employPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        employeeCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        employeeCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        employeeCheckBox.setText("Nhân viên");
      

        addEmployeeCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addEmployeeCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        addEmployeeCheckBox.setText("Thêm");
        addEmployeeCheckBox.setEnabled(false);

        updateEmployeeCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateEmployeeCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        updateEmployeeCheckBox.setText("Sửa");
        updateEmployeeCheckBox.setEnabled(false);

        delEmployeeCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delEmployeeCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        delEmployeeCheckBox.setText("Xóa");
        delEmployeeCheckBox.setEnabled(false);

        searchEmployeeCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchEmployeeCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        searchEmployeeCheckBox.setText("Tìm kiếm");
        searchEmployeeCheckBox.setEnabled(false);

        javax.swing.GroupLayout employPanelLayout = new javax.swing.GroupLayout(employPanel);
        employPanelLayout.setHorizontalGroup(
        	employPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(employPanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(employeeCheckBox)
        			.addGap(25)
        			.addComponent(addEmployeeCheckBox)
        			.addGap(18)
        			.addComponent(updateEmployeeCheckBox)
        			.addGap(18)
        			.addComponent(delEmployeeCheckBox)
        			.addGap(18)
        			.addComponent(searchEmployeeCheckBox)
        			.addGap(6))
        );
        employPanelLayout.setVerticalGroup(
        	employPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(employPanelLayout.createSequentialGroup()
        			.addGap(7)
        			.addGroup(employPanelLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(employPanelLayout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(employeeCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(addEmployeeCheckBox)
        					.addComponent(updateEmployeeCheckBox))
        				.addGroup(employPanelLayout.createSequentialGroup()
        					.addGroup(employPanelLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(delEmployeeCheckBox)
        						.addComponent(searchEmployeeCheckBox))
        					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)))
        			.addGap(7))
        );
        employPanel.setLayout(employPanelLayout);

        discountPanel.setBackground(new java.awt.Color(31, 31, 31));
        discountPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        discountCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        discountCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        discountCheckBox.setText("Khuyến mãi");
        

        addDiscountCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addDiscountCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        addDiscountCheckBox.setText("Thêm");
        addDiscountCheckBox.setEnabled(false);

        updateDiscountCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateDiscountCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        updateDiscountCheckBox.setText("Sửa");
        updateDiscountCheckBox.setEnabled(false);

        delDiscountCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delDiscountCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        delDiscountCheckBox.setText("Xóa");
        delDiscountCheckBox.setEnabled(false);

        searchDiscountCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchDiscountCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        searchDiscountCheckBox.setText("Tìm kiếm");
        searchDiscountCheckBox.setEnabled(false);

        javax.swing.GroupLayout discountPanelLayout = new javax.swing.GroupLayout(discountPanel);
        discountPanel.setLayout(discountPanelLayout);
        discountPanelLayout.setHorizontalGroup(
            discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(discountCheckBox)
                .addGap(15, 15, 15)
                .addComponent(addDiscountCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateDiscountCheckBox)
                .addGap(18, 18, 18)
                .addComponent(delDiscountCheckBox)
                .addGap(18, 18, 18)
                .addComponent(searchDiscountCheckBox)
                .addGap(6, 6, 6))
        );
        discountPanelLayout.setVerticalGroup(
            discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(discountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discountCheckBox)
                    .addComponent(addDiscountCheckBox)
                    .addComponent(updateDiscountCheckBox)
                    .addComponent(delDiscountCheckBox)
                    .addComponent(searchDiscountCheckBox))
                .addGap(7, 7, 7))
        );

        productPanel.setBackground(new java.awt.Color(31, 31, 31));
        productPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        productCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        productCheckBox.setText("Sản phẩm");
        

        addProductCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addProductCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        addProductCheckBox.setText("Thêm");
        addProductCheckBox.setEnabled(false);

        updateProductCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateProductCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        updateProductCheckBox.setText("Sửa");
        updateProductCheckBox.setEnabled(false);

        delProductCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delProductCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        delProductCheckBox.setText("Xóa");
        delProductCheckBox.setEnabled(false);

        searchProductCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchProductCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        searchProductCheckBox.setText("Tìm kiếm");
        searchProductCheckBox.setEnabled(false);

        javax.swing.GroupLayout productPanelLayout = new javax.swing.GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productCheckBox)
                .addGap(26, 26, 26)
                .addComponent(addProductCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateProductCheckBox)
                .addGap(18, 18, 18)
                .addComponent(delProductCheckBox)
                .addGap(18, 18, 18)
                .addComponent(searchProductCheckBox)
                .addGap(6, 6, 6))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productCheckBox)
                    .addComponent(addProductCheckBox)
                    .addComponent(updateProductCheckBox)
                    .addComponent(delProductCheckBox)
                    .addComponent(searchProductCheckBox))
                .addGap(7, 7, 7))
        );

        decenPanel.setBackground(new java.awt.Color(31, 31, 31));
        decenPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        dencenCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dencenCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        dencenCheckBox.setText("Phân quyền");
        

        addDencenCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addDencenCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        addDencenCheckBox.setText("Thêm");
        addDencenCheckBox.setEnabled(false);

        updateDencenCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateDencenCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        updateDencenCheckBox.setText("Sửa");
        updateDencenCheckBox.setEnabled(false);

        delDencenCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delDencenCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        delDencenCheckBox.setText("Xóa");
        delDencenCheckBox.setEnabled(false);

        searchDencenCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchDencenCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        searchDencenCheckBox.setText("Tìm kiếm");
        searchDencenCheckBox.setEnabled(false);

        javax.swing.GroupLayout decenPanelLayout = new javax.swing.GroupLayout(decenPanel);
        decenPanelLayout.setHorizontalGroup(
        	decenPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(decenPanelLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(dencenCheckBox)
        			.addGap(15)
        			.addComponent(addDencenCheckBox)
        			.addGap(18)
        			.addComponent(updateDencenCheckBox)
        			.addGap(18)
        			.addComponent(delDencenCheckBox)
        			.addGap(18)
        			.addComponent(searchDencenCheckBox)
        			.addGap(6))
        );
        decenPanelLayout.setVerticalGroup(
        	decenPanelLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(decenPanelLayout.createSequentialGroup()
        			.addGap(10)
        			.addGroup(decenPanelLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(dencenCheckBox)
        				.addComponent(addDencenCheckBox)
        				.addComponent(updateDencenCheckBox)
        				.addComponent(delDencenCheckBox)
        				.addComponent(searchDencenCheckBox))
        			.addContainerGap(10, Short.MAX_VALUE))
        );
        decenPanel.setLayout(decenPanelLayout);

        statisPanel.setBackground(new java.awt.Color(31, 31, 31));
        statisPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        statiscCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statiscCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        statiscCheckBox.setText("Thống kê");
        

        javax.swing.GroupLayout statisPanelLayout = new javax.swing.GroupLayout(statisPanel);
        statisPanel.setLayout(statisPanelLayout);
        statisPanelLayout.setHorizontalGroup(
            statisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statiscCheckBox)
                .addGap(325, 325, 325))
        );
        statisPanelLayout.setVerticalGroup(
            statisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(statiscCheckBox)
                .addGap(7, 7, 7))
        );

        importPanel.setBackground(new java.awt.Color(31, 31, 31));
        importPanel.setBorder(new LineBorder(new Color(31, 31, 31)));

        importCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        importCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        importCheckBox.setText("Nhập hàng");
        

        addImportCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addImportCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        addImportCheckBox.setText("Thêm");
        addImportCheckBox.setEnabled(false);

        updateImportCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateImportCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        updateImportCheckBox.setText("Sửa");
        updateImportCheckBox.setEnabled(false);

        delImportCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delImportCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        delImportCheckBox.setText("Xóa");
        delImportCheckBox.setEnabled(false);

        searchImportCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchImportCheckBox.setForeground(new java.awt.Color(128, 128, 128));
        searchImportCheckBox.setText("Tìm kiếm");
        searchImportCheckBox.setEnabled(false);

        javax.swing.GroupLayout importPanelLayout = new javax.swing.GroupLayout(importPanel);
        importPanel.setLayout(importPanelLayout);
        importPanelLayout.setHorizontalGroup(
            importPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(importCheckBox)
                .addGap(19, 19, 19)
                .addComponent(addImportCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateImportCheckBox)
                .addGap(18, 18, 18)
                .addComponent(delImportCheckBox)
                .addGap(18, 18, 18)
                .addComponent(searchImportCheckBox)
                .addGap(6, 6, 6))
        );
        importPanelLayout.setVerticalGroup(
            importPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(importPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(importPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importCheckBox)
                    .addComponent(addImportCheckBox)
                    .addComponent(updateImportCheckBox)
                    .addComponent(delImportCheckBox)
                    .addComponent(searchImportCheckBox))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7Layout.setHorizontalGroup(
        	jPanel7Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel7Layout.createSequentialGroup()
        			.addGroup(jPanel7Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(billPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(customerPanel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        				.addComponent(employPanel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        				.addComponent(discountPanel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        				.addComponent(productPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(decenPanel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        				.addComponent(statisPanel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        				.addComponent(importPanel, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
        			.addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
        	jPanel7Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel7Layout.createSequentialGroup()
        			.addGap(0, 0, Short.MAX_VALUE)
        			.addComponent(billPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(customerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(employPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(discountPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(productPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(decenPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(statisPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(importPanel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addGap(17))
        );
        jPanel7.setLayout(jPanel7Layout);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4Layout.setHorizontalGroup(
        	jPanel4Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel4Layout.createSequentialGroup()
        			.addGroup(jPanel4Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        				.addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        				.addGroup(jPanel4Layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
        	jPanel4Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel4Layout.createSequentialGroup()
        			.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(10)
        			.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
        			.addGap(10)
        			.addComponent(jPanel7, GroupLayout.PREFERRED_SIZE, 439, Short.MAX_VALUE)
        			.addGap(0))
        );
        jPanel4.setLayout(jPanel4Layout);

        jPanel16.setBackground(new java.awt.Color(31, 31, 31));
        
        addRoundPanel = new DraggableRoundPanel();
        addRoundPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(addRoundPanel.isEnabled()) {        			
        			int rowSelected = positionListTable.getSelectedRow();
        			if(rowSelected >= 0 ) {
        				clearAllInput();
        				nameTextField.requestFocus();
        				positionListTable.clearSelection();
        			}
        			else {
        				if(kTra.KTHoVaTen(nameTextField.getText())) {
        					ChucVu cVu = takeInput();
        					try {
        						int rowAffect = cvBUS.addChucVu(cVu);
        						if(rowAffect >0) {
        							JOptionPane.showMessageDialog(getRootPane(), "Thêm thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        							chucVuList = cvBUS.takeAll();
        							initTable();
        						}
        						else {
        							
        							JOptionPane.showMessageDialog(getRootPane(), "Thêm thất bại","Thông báo",JOptionPane.ERROR_MESSAGE);
        						}
        					} catch (ClassNotFoundException | SQLException e1) {
        						// TODO Auto-generated catch block
        						e1.printStackTrace();
        					}
        					
        				}
        				else {
        					JOptionPane.showMessageDialog(getRootPane(), "Vui lòng điền thông tin đúng định dạng và không để trống ","Thông báo",JOptionPane.ERROR_MESSAGE);
        				}
        			}
        		}
        	}
        });
        addRoundPanel.setBackground(new Color(255, 101, 0));
        
        editRoundPanel = new DraggableRoundPanel();
        editRoundPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(editRoundPanel.isEnabled()) {
        			int option = JOptionPane.showConfirmDialog(getRootPane(), "Bạn có chắc chắc muốn sửa không");
        			if(option == JOptionPane.YES_OPTION) {
        				ChucVu newCV = takeInput();
        				if(kTra.KTHoVaTen(newCV.getTenCV())) {
        					
        					try {
        						int rowAffect = cvBUS.updateChucVu(newCV);
        						if(rowAffect > 0) {
        							JOptionPane.showMessageDialog(getRootPane(), "Cập nhật thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        							chucVuList = cvBUS.takeAll();
        							initTable();
        							clearAllInput();
        							EnableRoundPanelBtn(editRoundPanel, false);
        							EnableRoundPanelBtn(delRoundPanel, false);
        							
        						}else {
        							
        							JOptionPane.showMessageDialog(getRootPane(), "Cập nhật thất bại","Thông báo",JOptionPane.ERROR_MESSAGE);
        						}
        					} catch (ClassNotFoundException | SQLException e1) {
        						// TODO Auto-generated catch block
        						e1.printStackTrace();
        					}
        				}
        				else {
        					JOptionPane.showMessageDialog(getRootPane(), "Vui lòng điền tên theo đúng định dạng và không để trống!!!","Thông báo",JOptionPane.ERROR_MESSAGE);
    						
        				}
        			}
        		}
        	}
        });
        editRoundPanel.setForeground(new Color(205, 205, 205));
        editRoundPanel.setBackground(new Color(192, 192, 192));
        
        JLabel editLabel = new JLabel("Sửa");
 
        editLabel.setForeground(new Color(127, 127, 127));
        editLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        editIconLabel = new JLabel("");
        editIconLabel.setIcon(new ImageIcon("C:\\Users\\Admin\\JavaProject\\src\\main\\java\\Images\\edit.png"));
        GroupLayout gl_editRoundPanel = new GroupLayout(editRoundPanel);
        gl_editRoundPanel.setHorizontalGroup(
        	gl_editRoundPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_editRoundPanel.createSequentialGroup()
        			.addGap(18)
        			.addComponent(editIconLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(editLabel)
        			.addContainerGap(35, Short.MAX_VALUE))
        );
        gl_editRoundPanel.setVerticalGroup(
        	gl_editRoundPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_editRoundPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_editRoundPanel.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(editLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(editIconLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
        			.addContainerGap(8, Short.MAX_VALUE))
        );
        editRoundPanel.setLayout(gl_editRoundPanel);
        
        delRoundPanel = new DraggableRoundPanel();
        delRoundPanel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
					delPosition();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        delRoundPanel.setBackground(new Color(192, 192, 192));
        
        JLabel delLabel = new JLabel("Xóa");
        delLabel.setBackground(new Color(204, 204, 204));
        delLabel.setForeground(new Color(127, 127, 127));
        delLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        delIconLabel = new JLabel("");
        delIconLabel.setIcon(new ImageIcon("C:\\Users\\Admin\\JavaProject\\src\\main\\java\\Images\\delete.png"));
        GroupLayout gl_delRoundPanel = new GroupLayout(delRoundPanel);
        gl_delRoundPanel.setHorizontalGroup(
        	gl_delRoundPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_delRoundPanel.createSequentialGroup()
        			.addGap(18)
        			.addComponent(delIconLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
        			.addGap(7)
        			.addComponent(delLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(24, Short.MAX_VALUE))
        );
        gl_delRoundPanel.setVerticalGroup(
        	gl_delRoundPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_delRoundPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_delRoundPanel.createParallelGroup(Alignment.BASELINE)
        				.addComponent(delLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(delIconLabel))
        			.addContainerGap(25, Short.MAX_VALUE))
        );
        delRoundPanel.setLayout(gl_delRoundPanel);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16Layout.setHorizontalGroup(
        	jPanel16Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel16Layout.createSequentialGroup()
        			.addGap(25)
        			.addComponent(addRoundPanel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
        			.addGap(19)
        			.addComponent(editRoundPanel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
        			.addGap(19)
        			.addComponent(delRoundPanel, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
        	jPanel16Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel16Layout.createSequentialGroup()
        			.addGroup(jPanel16Layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(editRoundPanel, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
        				.addComponent(delRoundPanel, GroupLayout.PREFERRED_SIZE, 50, Short.MAX_VALUE)
        				.addComponent(addRoundPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        JLabel addLabel = new JLabel("Thêm");
        addLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addLabel.setForeground(new Color(255, 255, 255));
        
        addIconLabel = new JLabel("");
        addIconLabel.setIcon(new ImageIcon("C:\\Users\\Admin\\JavaProject\\src\\main\\java\\Images\\plus.png"));
        GroupLayout gl_addRoundPanel = new GroupLayout(addRoundPanel);
        gl_addRoundPanel.setHorizontalGroup(
        	gl_addRoundPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_addRoundPanel.createSequentialGroup()
        			.addGap(18)
        			.addComponent(addIconLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(addLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(19, Short.MAX_VALUE))
        );
        gl_addRoundPanel.setVerticalGroup(
        	gl_addRoundPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_addRoundPanel.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_addRoundPanel.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(addLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(addIconLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
        			.addGap(14))
        );
        addRoundPanel.setLayout(gl_addRoundPanel);
        jPanel16.setLayout(jPanel16Layout);

        javax.swing.GroupLayout draggableRoundPanel4Layout = new javax.swing.GroupLayout(draggableRoundPanel4);
        draggableRoundPanel4Layout.setHorizontalGroup(
        	draggableRoundPanel4Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(draggableRoundPanel4Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(draggableRoundPanel4Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel16, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        				.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jPanel4, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
        			.addContainerGap())
        );
        draggableRoundPanel4Layout.setVerticalGroup(
        	draggableRoundPanel4Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(draggableRoundPanel4Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(jPanel16, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        draggableRoundPanel4.setLayout(draggableRoundPanel4Layout);

        javax.swing.GroupLayout draggableRoundPanel1Layout = new javax.swing.GroupLayout(draggableRoundPanel1);
        draggableRoundPanel1Layout.setHorizontalGroup(
        	draggableRoundPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(draggableRoundPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(draggableRoundPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(draggableRoundPanel2, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
        				.addComponent(draggableRoundPanel3, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(draggableRoundPanel4, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        draggableRoundPanel1Layout.setVerticalGroup(
        	draggableRoundPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(draggableRoundPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(draggableRoundPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(draggableRoundPanel4, GroupLayout.PREFERRED_SIZE, 652, GroupLayout.PREFERRED_SIZE)
        				.addGroup(Alignment.LEADING, draggableRoundPanel1Layout.createSequentialGroup()
        					.addComponent(draggableRoundPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(draggableRoundPanel3, 0, 0, Short.MAX_VALUE)))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        draggableRoundPanel1.setLayout(draggableRoundPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(5)
        			.addComponent(draggableRoundPanel1, GroupLayout.PREFERRED_SIZE, 1126, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(5)
        			.addComponent(draggableRoundPanel1, GroupLayout.PREFERRED_SIZE, 670, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void positionListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_positionListTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = positionListTable.getSelectedRow();
        
        if (selectedRow >= 0) {
            String iDString = (String) positionListTable.getValueAt(selectedRow, 0);
            int iD = Integer.parseInt(iDString.split("CV")[1]) ;
            for(ChucVu cVu : chucVuList) {
            	if(cVu.getMaCV() == iD) {
            		clearAllInput();
            		setDataOnClickTable(cVu);
            		EnableRoundPanelBtn(delRoundPanel, true);
            		EnableRoundPanelBtn(editRoundPanel, true);
            		break;
            	}
            }
        }
    }//GEN-LAST:event_positionListTableMouseClicked

    private void setDataOnClickTable(ChucVu cv) {
    	
    	
    	
    	iDTextField.setText("CV"+cv.getMaCV());
    	nameTextField.setText(cv.getTenCV());
    	if(!cv.getHoaDon().isEmpty()) {
    		billCheckBox.setSelected(true);
    	}
    	
    	if(!cv.getHoaDon().contains("them")) 
    		addBillCheckBox.setSelected(false);
    	
    	if(!cv.getHoaDon().contains("sua")) 
    		updateBillCheckBox.setSelected(false);
    	
    	if(!cv.getHoaDon().contains("xoa")) 
    		delBillCheckBox.setSelected(false);
    	
    	if(!cv.getHoaDon().contains("timkiem")) 
    		searchBillCheckBox.setSelected(false);
    	
    	if(!cv.getNhapHang().isEmpty()) {
    		importCheckBox.setSelected(true);
    	}
    	if(!cv.getNhapHang().contains("them")) 
    		addImportCheckBox.setSelected(false);
    	
    	if(!cv.getNhapHang().contains("sua")) 
    		updateImportCheckBox.setSelected(false);
    	
    	if(!cv.getNhapHang().contains("xoa")) 
    		delImportCheckBox.setSelected(false);
    	
    	if(!cv.getNhapHang().contains("timkiem")) 
    		searchImportCheckBox.setSelected(false);
    	
    	if(!cv.getKhuyenMai().isEmpty()) {
    		discountCheckBox.setSelected(true);
    	}
    	
    	if(!cv.getKhuyenMai().contains("them")) 
    		addDiscountCheckBox.setSelected(false);
    	
    	if(!cv.getKhuyenMai().contains("sua")) 
    		updateDiscountCheckBox.setSelected(false);
    	
    	if(!cv.getKhuyenMai().contains("xoa")) 
    		delDiscountCheckBox.setSelected(false);
    	
    	if(!cv.getKhuyenMai().contains("timkiem")) 
    		searchDiscountCheckBox.setSelected(false);
    	
    	if(!cv.getNhanVien().isEmpty()) {
    		employeeCheckBox.setSelected(true);
    	}
    	
    	if(!cv.getNhanVien().contains("them")) 
    		addEmployeeCheckBox.setSelected(false);
    	
    	if(!cv.getNhanVien().contains("sua")) 
    		updateEmployeeCheckBox.setSelected(false);
    	
    	if(!cv.getNhanVien().contains("xoa")) 
    		delEmployeeCheckBox.setSelected(false);
    	
    	if(!cv.getNhanVien().contains("timkiem")) 
    		searchEmployeeCheckBox.setSelected(false);
    	
    	if(!cv.getKhachHang().isEmpty()) {
    		customerCheckBox.setSelected(true);
    	}
    	
    	if(!cv.getKhachHang().contains("them")) 
    		addCustomerCheckBox.setSelected(false);
    	
    	if(!cv.getKhachHang().contains("sua")) 
    		updateCustomerCheckBox.setSelected(false);
    	
    	if(!cv.getKhachHang().contains("xoa")) 
    		delCustomerCheckBox.setSelected(false);
    	
    	if(!cv.getKhachHang().contains("timkiem")) 
    		searchCustomerCheckBox.setSelected(false);
    	
    	if(!cv.getSanPham().isEmpty()) {
    		productCheckBox.setSelected(true);
    	}
    	
    	if(!cv.getSanPham().contains("them")) 
    		addProductCheckBox.setSelected(false);
    	
    	if(!cv.getSanPham().contains("sua")) 
    		updateProductCheckBox.setSelected(false);
    	
    	if(!cv.getSanPham().contains("xoa")) 
    		delProductCheckBox.setSelected(false);
    	
    	if(!cv.getSanPham().contains("timkiem")) 
    		searchProductCheckBox.setSelected(false);
    	
    	if(!cv.getPhanQuyen().isEmpty()) {
    		dencenCheckBox.setSelected(true);
    	}
    	
    	if(!cv.getPhanQuyen().contains("them")) 
    		addDencenCheckBox.setSelected(false);
    	
    	if(!cv.getPhanQuyen().contains("sua")) 
    		updateDencenCheckBox.setSelected(false);
    	
    	if(!cv.getPhanQuyen().contains("xoa")) 
    		delDencenCheckBox.setSelected(false);
    	
    	if(!cv.getPhanQuyen().contains("timkiem")) 
    		searchDencenCheckBox.setSelected(false);
    	
    	if(!cv.getThongKe().isEmpty())
    		statiscCheckBox.setSelected(true);
    	
    	
    	
    }
    
    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_searchTextFieldKeyTyped

    
    public ChucVu takeInput() {
    	ChucVu cv;
    	
    	String TenCV = nameTextField.getText();
    	String HoaDon = "";
    	String KhachHang = "";
    	String NhanVien = "";
    	String KhuyenMai = "";
    	String SanPham = "";
    	String PhanQuyen = "";
    	String ThongKe = "";
    	String NhapHang = "";
    	
    	
    	ChucVu newCV = new ChucVu();
    	
    	if(addBillCheckBox.isSelected()) 
    		HoaDon += "them,";
    	
    	if(updateBillCheckBox.isSelected()) 
    		HoaDon += "sua,";
    	   	
    	if(delBillCheckBox.isSelected()) 
    		HoaDon+="xoa,";
    	  	
    	if(searchBillCheckBox.isSelected()) 
    		HoaDon += "timkiem";
    	
    	if(addDencenCheckBox.isSelected()) 
    		PhanQuyen += "them,";
    	
    	if(updateDencenCheckBox.isSelected()) 
    		PhanQuyen += "sua,";
    	   	
    	if(delDencenCheckBox.isSelected()) 
    		PhanQuyen+="xoa,";
    	  	
    	if(searchDencenCheckBox.isSelected()) 
    		PhanQuyen += "timkiem";
    	
    	
    	if(addImportCheckBox.isSelected()) 
    		NhapHang += "them,";
    	
    	if(updateImportCheckBox.isSelected()) 
    		NhapHang += "sua,";
    	   	
    	if(delImportCheckBox.isSelected()) 
    		NhapHang+="xoa,";
    	  	
    	if(searchImportCheckBox.isSelected()) 
    		NhapHang += "timkiem";
    	
    	
    	
    	if(addEmployeeCheckBox.isSelected()) 
    		NhanVien += "them,";
    	
    	if(updateEmployeeCheckBox.isSelected()) 
    		NhanVien += "sua,";
    	   	
    	if(delEmployeeCheckBox.isSelected()) 
    		NhanVien+="xoa,";
    	  	
    	if(searchEmployeeCheckBox.isSelected()) 
    		NhanVien += "timkiem";
    	
    	
    	if(addCustomerCheckBox.isSelected()) 
    		KhachHang += "them,";
    	
    	if(updateCustomerCheckBox.isSelected()) 
    		KhachHang += "sua,";
    	   	
    	if(delCustomerCheckBox.isSelected()) 
    		KhachHang+="xoa,";
    	  	
    	if(searchCustomerCheckBox.isSelected()) 
    		KhachHang += "timkiem";
    	
    	if(addDiscountCheckBox.isSelected()) 
    		KhuyenMai += "them,";
    	
    	if(updateDiscountCheckBox.isSelected()) 
    		KhuyenMai += "sua,";
    	   	
    	if(delDiscountCheckBox.isSelected()) 
    		KhuyenMai+="xoa,";
    	  	
    	if(searchDiscountCheckBox.isSelected()) 
    		KhuyenMai += "timkiem";
    	
    	if(addProductCheckBox.isSelected()) 
    		SanPham += "them,";
    	
    	if(updateProductCheckBox.isSelected()) 
    		SanPham += "sua,";
    	   	
    	if(delProductCheckBox.isSelected()) 
    		SanPham+="xoa,";
    	  	
    	if(searchProductCheckBox.isSelected()) 
    		SanPham += "timkiem";
    	
    	if(statiscCheckBox.isSelected()) {
    		ThongKe +="xem";
    	}
    	
    	
    	if(iDTextField.getText().isEmpty()) {
    		cv = new ChucVu(TenCV, HoaDon, KhachHang, NhanVien, KhuyenMai, SanPham, PhanQuyen, ThongKe, NhapHang);
    	}
    	else {
    		int MaCV = Integer.parseInt(iDTextField.getText().split("CV")[1]) ;
    		cv = new ChucVu(MaCV, TenCV, HoaDon, KhachHang, NhanVien, KhuyenMai, SanPham, PhanQuyen, ThongKe, NhapHang);
    	}
    	return cv;
    }
    
    public void clearAllInput() {
    	iDTextField.setText("");
    	nameTextField.setText("");
    	for(JCheckBox parentCheckBox : parentCheckBoxList) {
    		parentCheckBox.setSelected(false);
    	}
    	statiscCheckBox.setSelected(false);
    }
    
    
    private void delPosition() throws ClassNotFoundException, SQLException {
    	if(delRoundPanel.isEnabled()) {
    		int rowSelected = positionListTable.getSelectedRow();
    		if(rowSelected >=0) {
    			int option = JOptionPane.showConfirmDialog(getRootPane(), "Bạn có chắc muốn xóa không","Xác nhận",JOptionPane.OK_CANCEL_OPTION);
    			if(option == JOptionPane.YES_OPTION) {
    				int MaCV = Integer.parseInt(iDTextField.getText().split("CV")[1]);
    				int rowAffect = cvBUS.deleteChucVu(MaCV);
    				if(rowAffect > 0) {
    					nvBUS.updateWhenCVIsDeleted(MaCV);
    					clearAllInput();
    					positionListTable.clearSelection();
    					chucVuList = cvBUS.takeAll();
    					initTable();
    					JOptionPane.showMessageDialog(getRootPane(), "Xóa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
    				}
    				else {
    					JOptionPane.showMessageDialog(getRootPane(), "Xóa thất bại","Thông báo",JOptionPane.ERROR_MESSAGE);
    					
    				}
    			}
    			
    		}
    		
    	}
    }
    
 
    private void EnableRoundPanelBtn(DraggableRoundPanel btn,boolean condition) {
    	boolean dencen = false;
    	if(btn == editRoundPanel && editDencen) {
    		dencen = true;
    	}
    	if(btn == delRoundPanel && delDencen) {
    		dencen = true;
    	}
    	if(btn == addRoundPanel && addDencen) {
    		dencen = true;
    	}
    	
    	
    	if (condition && dencen) {
    		Component[] componentList = btn.getComponents();
    		btn.setEnabled(true);
    		btn.setBackground(new Color(255,101,0));
    		for(Component c : componentList) {
    			c.setForeground(Color.white);
    		
    		}
		}else {
			Component[] componentList = btn.getComponents();
    		btn.setBackground(new Color(199,199,199));
    		btn.setEnabled(false);
    		for(Component c : componentList) {
    			c.setForeground(new Color(127,127,127) );
    		}
		}
	}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox addBillCheckBox;
    private javax.swing.JCheckBox addCustomerCheckBox;
    private javax.swing.JCheckBox addDencenCheckBox;
    private javax.swing.JCheckBox addDiscountCheckBox;
    private DraggableRoundPanel addRoundPanel;
    private DraggableRoundPanel editRoundPanel;
    private DraggableRoundPanel delRoundPanel;
    private javax.swing.JCheckBox addEmployeeCheckBox;
    private javax.swing.JCheckBox addImportCheckBox;
    private javax.swing.JCheckBox addProductCheckBox;
    private javax.swing.JCheckBox billCheckBox;
    private javax.swing.JPanel billPanel;
    private javax.swing.JCheckBox customerCheckBox;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JPanel decenPanel;
    private javax.swing.JCheckBox delBillCheckBox;
    private javax.swing.JCheckBox delCustomerCheckBox;
    private javax.swing.JCheckBox delDencenCheckBox;
    private javax.swing.JCheckBox delDiscountCheckBox;
    private javax.swing.JCheckBox delEmployeeCheckBox;
    private javax.swing.JCheckBox delImportCheckBox;
    private javax.swing.JCheckBox delProductCheckBox;
    private javax.swing.JCheckBox dencenCheckBox;
    private javax.swing.JCheckBox discountCheckBox;
    private javax.swing.JPanel discountPanel;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel1;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel2;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel3;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel4;
    private javax.swing.JPanel employPanel;
    private javax.swing.JCheckBox employeeCheckBox;
    private javax.swing.JTextField iDTextField;
    private javax.swing.JCheckBox importCheckBox;
    private javax.swing.JPanel importPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTable positionListTable;
    private javax.swing.JCheckBox productCheckBox;
    private javax.swing.JPanel productPanel;
    private javax.swing.JCheckBox searchBillCheckBox;
    private javax.swing.JCheckBox searchCustomerCheckBox;
    private javax.swing.JCheckBox searchDencenCheckBox;
    private javax.swing.JCheckBox searchDiscountCheckBox;
    private javax.swing.JCheckBox searchEmployeeCheckBox;
    private javax.swing.JCheckBox searchImportCheckBox;
    private javax.swing.JCheckBox searchProductCheckBox;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JPanel statisPanel;
    private javax.swing.JCheckBox statiscCheckBox;
    private javax.swing.JCheckBox updateBillCheckBox;
    private javax.swing.JCheckBox updateCustomerCheckBox;
    private javax.swing.JCheckBox updateDencenCheckBox;
    private javax.swing.JCheckBox updateDiscountCheckBox;
    private javax.swing.JCheckBox updateEmployeeCheckBox;
    private javax.swing.JCheckBox updateImportCheckBox;
    private javax.swing.JCheckBox updateProductCheckBox;
    private JLabel addIconLabel;
    private JLabel editIconLabel;
    private JLabel delIconLabel;
}
