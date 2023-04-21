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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Admin
 */
public class PhanQuyenPanel extends javax.swing.JPanel {

    private List<JCheckBox> parentCheckBoxList;
    private HashMap<JCheckBox, List<JCheckBox>> childCheckBoxHashMap;
    private HashMap<JCheckBox, JPanel> panelHashMap;

    /**
     * Creates new form NhanVienJPanel
     */
    public PhanQuyenPanel() {
        initComponents();
        jLabel1.setIcon(new ImageIcon("src\\main\\java\\Images\\account-img.png"));
        jLabel2.setIcon(new ImageIcon("src\\main\\java\\Images\\search-icon.png"));
        refreshButton.setIcon(new ImageIcon("src\\main\\java\\Images\\refresh.png"));

        setRowColorBackground(this.positionListTable);

        initData();
        addSelectedCheckBoxEvent();

    }

    private void initData() {
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
                    component.setForeground(Gray);
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
        searchButton = new javax.swing.JButton();
        draggableRoundPanel3 = new CUSTOM.DraggableRoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        positionListTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        draggableRoundPanel4 = new CUSTOM.DraggableRoundPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        iDTextField = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        billPanel = new javax.swing.JPanel();
        billCheckBox = new javax.swing.JCheckBox();
        addBillCheckBox = new javax.swing.JCheckBox();
        updateBillCheckBox = new javax.swing.JCheckBox();
        delBillCheckBox = new javax.swing.JCheckBox();
        searchBillCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        customerPanel = new javax.swing.JPanel();
        customerCheckBox = new javax.swing.JCheckBox();
        addCustomerCheckBox = new javax.swing.JCheckBox();
        updateCustomerCheckBox = new javax.swing.JCheckBox();
        delCustomerCheckBox = new javax.swing.JCheckBox();
        searchCustomerCheckBox = new javax.swing.JCheckBox();
        employPanel = new javax.swing.JPanel();
        employeeCheckBox = new javax.swing.JCheckBox();
        addEmployeeCheckBox = new javax.swing.JCheckBox();
        updateEmployeeCheckBox = new javax.swing.JCheckBox();
        delEmployeeCheckBox = new javax.swing.JCheckBox();
        searchEmployeeCheckBox = new javax.swing.JCheckBox();
        discountPanel = new javax.swing.JPanel();
        discountCheckBox = new javax.swing.JCheckBox();
        addDiscountCheckBox = new javax.swing.JCheckBox();
        updateDiscountCheckBox = new javax.swing.JCheckBox();
        delDiscountCheckBox = new javax.swing.JCheckBox();
        searchDiscountCheckBox = new javax.swing.JCheckBox();
        productPanel = new javax.swing.JPanel();
        productCheckBox = new javax.swing.JCheckBox();
        addProductCheckBox = new javax.swing.JCheckBox();
        updateProductCheckBox = new javax.swing.JCheckBox();
        delProductCheckBox = new javax.swing.JCheckBox();
        searchProductCheckBox = new javax.swing.JCheckBox();
        decenPanel = new javax.swing.JPanel();
        dencenCheckBox = new javax.swing.JCheckBox();
        addDencenCheckBox = new javax.swing.JCheckBox();
        updateDencenCheckBox = new javax.swing.JCheckBox();
        delDencenCheckBox = new javax.swing.JCheckBox();
        searchDencenCheckBox = new javax.swing.JCheckBox();
        statisPanel = new javax.swing.JPanel();
        statiscCheckBox = new javax.swing.JCheckBox();
        importPanel = new javax.swing.JPanel();
        importCheckBox = new javax.swing.JCheckBox();
        addImportCheckBox = new javax.swing.JCheckBox();
        updateImportCheckBox = new javax.swing.JCheckBox();
        delImportCheckBox = new javax.swing.JCheckBox();
        searchImportCheckBox = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 204));

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
        searchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusLost(evt);
            }
        });
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(44, 44, 46));
        searchButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(118, 118, 118));
        searchButton.setText("Tìm kiếm");
        searchButton.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout draggableRoundPanel2Layout = new javax.swing.GroupLayout(draggableRoundPanel2);
        draggableRoundPanel2.setLayout(draggableRoundPanel2Layout);
        draggableRoundPanel2Layout.setHorizontalGroup(
            draggableRoundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel2Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        draggableRoundPanel2Layout.setVerticalGroup(
            draggableRoundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        draggableRoundPanel3.setBackground(new java.awt.Color(31, 31, 31));

        jScrollPane2.setBackground(new java.awt.Color(32, 32, 32));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));

        positionListTable.setBackground(new java.awt.Color(32, 32, 32));
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

        refreshButton.setBackground(new java.awt.Color(255, 101, 0));
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshButtonMouseClicked(evt);
            }
        });
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout draggableRoundPanel3Layout = new javax.swing.GroupLayout(draggableRoundPanel3);
        draggableRoundPanel3.setLayout(draggableRoundPanel3Layout);
        draggableRoundPanel3Layout.setHorizontalGroup(
            draggableRoundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(draggableRoundPanel3Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        draggableRoundPanel3Layout.setVerticalGroup(
            draggableRoundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggableRoundPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(draggableRoundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(draggableRoundPanel3Layout.createSequentialGroup()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        draggableRoundPanel4.setBackground(new java.awt.Color(31, 31, 31));

        jPanel2.setBackground(new java.awt.Color(32, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 101, 0));
        jLabel3.setText("THÔNG TIN CHỨC VỤ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(32, 32, 32));

        jPanel5.setBackground(new java.awt.Color(32, 32, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(118, 118, 118));
        jLabel5.setText("Mã chức vụ");

        iDTextField.setEditable(false);
        iDTextField.setBackground(new java.awt.Color(118, 118, 118));
        iDTextField.setForeground(new java.awt.Color(255, 255, 255));
        iDTextField.setBorder(null);
        iDTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(32, 32, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(210, 210, 210));
        jLabel6.setText("Tên chức vụ");

        nameTextField.setBackground(new java.awt.Color(32, 32, 32));
        nameTextField.setForeground(new java.awt.Color(255, 255, 255));
        nameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        nameTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nameTextField))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(31, 31, 31));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        billPanel.setBackground(new java.awt.Color(31, 31, 31));
        billPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        billCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        billCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        billCheckBox.setText("Hóa đơn");
        billCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billCheckBoxActionPerformed(evt);
            }
        });

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(210, 101, 0));
        jLabel4.setText("CHI TIẾT PHÂN QUYỀN");

        customerPanel.setBackground(new java.awt.Color(31, 31, 31));
        customerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        customerCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        customerCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        customerCheckBox.setText("Khách hàng");
        customerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerCheckBoxActionPerformed(evt);
            }
        });

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
        delCustomerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delCustomerCheckBoxActionPerformed(evt);
            }
        });

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
        employPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        employeeCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        employeeCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        employeeCheckBox.setText("Nhân viên");
        employeeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeCheckBoxActionPerformed(evt);
            }
        });

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
        employPanel.setLayout(employPanelLayout);
        employPanelLayout.setHorizontalGroup(
            employPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(employeeCheckBox)
                .addGap(25, 25, 25)
                .addComponent(addEmployeeCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateEmployeeCheckBox)
                .addGap(18, 18, 18)
                .addComponent(delEmployeeCheckBox)
                .addGap(18, 18, 18)
                .addComponent(searchEmployeeCheckBox)
                .addGap(6, 6, 6))
        );
        employPanelLayout.setVerticalGroup(
            employPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(employPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(employPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(employeeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addEmployeeCheckBox)
                        .addComponent(updateEmployeeCheckBox))
                    .addGroup(employPanelLayout.createSequentialGroup()
                        .addGroup(employPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(delEmployeeCheckBox)
                            .addComponent(searchEmployeeCheckBox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(7, 7, 7))
        );

        discountPanel.setBackground(new java.awt.Color(31, 31, 31));
        discountPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        discountCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        discountCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        discountCheckBox.setText("Khuyến mãi");
        discountCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountCheckBoxActionPerformed(evt);
            }
        });

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
        productPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        productCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        productCheckBox.setText("Sản phẩm");
        productCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productCheckBoxActionPerformed(evt);
            }
        });

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
        decenPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        dencenCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dencenCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        dencenCheckBox.setText("Phân quyền");
        dencenCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dencenCheckBoxActionPerformed(evt);
            }
        });

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
        decenPanel.setLayout(decenPanelLayout);
        decenPanelLayout.setHorizontalGroup(
            decenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decenPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dencenCheckBox)
                .addGap(15, 15, 15)
                .addComponent(addDencenCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateDencenCheckBox)
                .addGap(18, 18, 18)
                .addComponent(delDencenCheckBox)
                .addGap(18, 18, 18)
                .addComponent(searchDencenCheckBox)
                .addGap(6, 6, 6))
        );
        decenPanelLayout.setVerticalGroup(
            decenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decenPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(decenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dencenCheckBox)
                    .addComponent(addDencenCheckBox)
                    .addComponent(updateDencenCheckBox)
                    .addComponent(delDencenCheckBox)
                    .addComponent(searchDencenCheckBox))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        statisPanel.setBackground(new java.awt.Color(31, 31, 31));
        statisPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        statiscCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statiscCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        statiscCheckBox.setText("Thống kê");
        statiscCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statiscCheckBoxActionPerformed(evt);
            }
        });

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
        importPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(31, 31, 31)));

        importCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        importCheckBox.setForeground(new java.awt.Color(255, 255, 255));
        importCheckBox.setText("Nhập hàng");
        importCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importCheckBoxActionPerformed(evt);
            }
        });

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
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(billPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(customerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(employPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(discountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(productPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(decenPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(statisPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(importPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(10, 10, 10)
                .addComponent(billPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(customerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(employPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(discountPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(decenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(statisPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(importPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel16.setBackground(new java.awt.Color(31, 31, 31));

        addButton.setBackground(new java.awt.Color(255, 101, 0));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Thêm");

        updateButton.setForeground(new java.awt.Color(102, 102, 102));
        updateButton.setText("Sửa");
        updateButton.setEnabled(false);

        delButton.setForeground(new java.awt.Color(102, 102, 102));
        delButton.setText("Xóa");
        delButton.setEnabled(false);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(delButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout draggableRoundPanel4Layout = new javax.swing.GroupLayout(draggableRoundPanel4);
        draggableRoundPanel4.setLayout(draggableRoundPanel4Layout);
        draggableRoundPanel4Layout.setHorizontalGroup(
            draggableRoundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(draggableRoundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        draggableRoundPanel4Layout.setVerticalGroup(
            draggableRoundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout draggableRoundPanel1Layout = new javax.swing.GroupLayout(draggableRoundPanel1);
        draggableRoundPanel1.setLayout(draggableRoundPanel1Layout);
        draggableRoundPanel1Layout.setHorizontalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(draggableRoundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(draggableRoundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(draggableRoundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        draggableRoundPanel1Layout.setVerticalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(draggableRoundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(draggableRoundPanel1Layout.createSequentialGroup()
                        .addComponent(draggableRoundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(draggableRoundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void positionListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_positionListTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = positionListTable.getSelectedRow();
        System.out.println(selectedRow);
        if (selectedRow >= 0) {
            positionListTable.setSelectionBackground(Color.BLUE);
            positionListTable.setSelectionForeground(Color.WHITE);
        }
    }//GEN-LAST:event_positionListTableMouseClicked

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        // TODO add your handling code here:

        checkInputToEnable(evt, searchButton, searchTextField);

    }//GEN-LAST:event_searchTextFieldKeyTyped

    private void searchTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusGained
        searchTextField.setBorder(BorderFactory.createLineBorder(new Color(255, 101, 0)));
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldFocusGained

    private void searchTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusLost
        // TODO add your handling code here:
        searchTextField.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
    }//GEN-LAST:event_searchTextFieldFocusLost

    private void billCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billCheckBoxActionPerformed

    private void customerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerCheckBoxActionPerformed

    private void employeeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeCheckBoxActionPerformed

    private void discountCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountCheckBoxActionPerformed

    private void productCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productCheckBoxActionPerformed

    private void dencenCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dencenCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dencenCheckBoxActionPerformed

    private void statiscCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statiscCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statiscCheckBoxActionPerformed

    private void importCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importCheckBoxActionPerformed

    private void delCustomerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCustomerCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delCustomerCheckBoxActionPerformed

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        // TODO add your handling code here:

        refreshButton.setVisible(false);
    }//GEN-LAST:event_refreshButtonMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void checkInputToEnable(java.awt.event.KeyEvent evt, JButton btnEnable, JTextField j) {
        String sTextField = j.getText();
        System.out.println(sTextField);
        if (evt.getKeyChar() != '\b') {
            sTextField += evt.getKeyChar();
        }
        System.out.println(sTextField);
//            sTextField.substring(0, sTextField.length()-1);
        if (!sTextField.isBlank()) {
            btnEnable.setEnabled(true);
            btnEnable.setBackground(new Color(255, 101, 0));
            btnEnable.setForeground(Color.WHITE);
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                Tim kiem o day
            }
        } else {
            btnEnable.setEnabled(false);
            btnEnable.setBackground(new Color(44, 44, 46));
            btnEnable.setForeground(new Color(118, 118, 118));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox addBillCheckBox;
    private javax.swing.JButton addButton;
    private javax.swing.JCheckBox addCustomerCheckBox;
    private javax.swing.JCheckBox addDencenCheckBox;
    private javax.swing.JCheckBox addDiscountCheckBox;
    private javax.swing.JCheckBox addEmployeeCheckBox;
    private javax.swing.JCheckBox addImportCheckBox;
    private javax.swing.JCheckBox addProductCheckBox;
    private javax.swing.JCheckBox billCheckBox;
    private javax.swing.JPanel billPanel;
    private javax.swing.JCheckBox customerCheckBox;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JPanel decenPanel;
    private javax.swing.JCheckBox delBillCheckBox;
    private javax.swing.JButton delButton;
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
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JButton refreshButton;
    private javax.swing.JCheckBox searchBillCheckBox;
    private javax.swing.JButton searchButton;
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
    private javax.swing.JButton updateButton;
    private javax.swing.JCheckBox updateCustomerCheckBox;
    private javax.swing.JCheckBox updateDencenCheckBox;
    private javax.swing.JCheckBox updateDiscountCheckBox;
    private javax.swing.JCheckBox updateEmployeeCheckBox;
    private javax.swing.JCheckBox updateImportCheckBox;
    private javax.swing.JCheckBox updateProductCheckBox;
    // End of variables declaration//GEN-END:variables
//    public static void main(String[] args) {
//        // Create a JFrame
//        JFrame frame = new JFrame("My Application");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Create a JPanel with your interface components
//        JPanel panel = new PhanQuyenJPanel();
//
//        // Add the panel to the frame
//        frame.getContentPane().add(panel);
//
//        // Pack and show the frame
//        frame.pack();
//        frame.setVisible(true);
//    }
}
