/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.ChucVuBUS;
import BUS.NhanVienBUS;
import CUSTOM.KiemTra;
import DTO.ChucVu;
import DTO.NhanVien;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.plaf.IconUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVienPanel extends javax.swing.JPanel {

    private KiemTra kt;
    private NhanVienBUS nvBUS;
    private List<ChucVu> cvList;
    private ChucVuBUS cvBUS;
    private List<NhanVien> nvList;
    private String searchInput;

    /**
     * Creates new form NhanVienJPanel
     */
    public NhanVienPanel() throws SQLException, ClassNotFoundException {
        initComponents();
        kt = new KiemTra();
        nvBUS = new NhanVienBUS();
        cvBUS = new ChucVuBUS();
        cvList = cvBUS.takeAll();
        nvList = nvBUS.takeAll();

        titleTableLabel.setIcon(new ImageIcon("src\\main\\java\\Images\\account-img.png"));
        jLabel2.setIcon(new ImageIcon("src\\main\\java\\Images\\search-icon.png"));
        refreshButton.setIcon(new ImageIcon("src\\main\\java\\Images\\refresh.png"));
        refreshButton.setVisible(false);
        setRowColorBackground(this.empListTable);
        setRenderComboBox(sortComboBox);
        setRenderComboBox(positionComboBox);
        addTFFocusListener();
//        setAllValidateLabel();

        addSearchTFListener();

        addShowLabelListener(nameValidateLabel, nameTextField, "nameTextField");
        addShowLabelListener(phoneValidateLabel, phoneTextField, "phoneTextField");
        addShowLabelListener(accountNameValidateLabel, accountNameTextField, "accountNameTextField");

        addShowLabelListener(mailValidateLabel, mailTextField, "mailTextField");
        addShortCutEnterForInput();
        initTable(nvList);
        renderSortComboxData();
        renderPositionComboxData();
    }

    public void initTable(List<NhanVien> nvSortList) {
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        empListTable.setModel(tableModel);

        tableModel.addColumn("Mã nhân viên");
        tableModel.addColumn("Tên nhân viên");
        tableModel.addColumn("SĐT");
        tableModel.addColumn("Email");
        tableModel.addColumn("Ngày Sinh");

        tableModel.addColumn("Chức vụ");

        for (NhanVien nv : nvSortList) {
            String id = "NV" + nv.getMaNV();
            String name = nv.getTenNV();
            String phone = nv.getSDT();
            String mail = nv.getEmail();
            String dateBirth = dateToString(nv.getNgaySinh());

            String position = iDToPosition(nv.getMaCV());
            tableModel.addRow(new Object[]{id, name, phone, mail, dateBirth, position});
        }
    }

    public void renderSortComboxData() {
        sortComboBox.addItem("Tất cả");
        for (ChucVu cv : cvList) {
            sortComboBox.addItem(cv.getTenCV());
        }
        sortComboBox.setSelectedIndex(0);
    }

    public void renderPositionComboxData() {
        positionComboBox.addItem("Chọn chức vụ");
        for (ChucVu cv : cvList) {
            positionComboBox.addItem(cv.getTenCV());
        }
    }

    public String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public String iDToPosition(int ID) {
        for (ChucVu cv : cvList) {
            if (cv.getMaCV() == ID) {
                return cv.getTenCV();
            }
        }
        return null;
    }

    public void addShowLabelListener(JLabel lb, JTextField tf, String nametf) {
        tf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                showLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                showLabel();
            }

            public void showLabel() {
                if (isValid()) {
                    lb.setForeground(Color.BLACK);
                } else {
                    lb.setForeground(new Color(255, 101, 0));
                }
            }

            public boolean isValid() {
                String input = tf.getText();

                switch (nametf) {
                    case "nameTextField":
                        return kt.KTHoVaTen(input);
                    case "phoneTextField":
                        return kt.isValidPhone(input);
                    case "mailTextField":
                        return kt.isValidEmail(input);
                    case "accountNameTextField":
                        return kt.KTKyTuDacBietKhongKhoangCach(input);
                    default:
                        throw new AssertionError();
                }
            }
        });
    }

    public void addSearchTFListener() {
        searchTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (searchTextField.getText().isEmpty()) {
                    EnableBtn(searchButton, false);
                } else {
                    EnableBtn(searchButton, true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (searchTextField.getText().isEmpty()) {
                    EnableBtn(searchButton, false);
                } else {
                    EnableBtn(searchButton, true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (searchTextField.getText().isEmpty()) {
                    EnableBtn(searchButton, false);
                } else {
                    EnableBtn(searchButton, true);
                }
            }
        });
    }

    public void addShortCutEnterForInput() {
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    isUpdate(e);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    isUpdate(e);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    isUpdate(e);
                } catch (ParseException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void isUpdate(KeyEvent e) throws ParseException, ClassNotFoundException, SQLException {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (updateButton.isEnabled()) {
                        updateEmp();
                    } else {
                        addEmp();
                    }
                }
            }
        };
        nameTextField.addKeyListener(keyListener);
        accountNameTextField.addKeyListener(keyListener);
        passTextField.addKeyListener(keyListener);
        mailTextField.addKeyListener(keyListener);
        phoneTextField.addKeyListener(keyListener);
        jDateChooser1.addKeyListener(keyListener);
        positionComboBox.addKeyListener(keyListener);
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

    public void setRenderComboBox(JComboBox comboBox) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(Color.WHITE);
                    c.setForeground(new Color(255, 101, 0));
                }
                return c;
            }
        });
    }

    public void addTFFocusListener() {
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField textField = (JTextField) e.getComponent();
                setBorderTFOnFocus(textField, true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                JTextField textField = (JTextField) e.getComponent();
                setBorderTFOnFocus(textField, false);
            }

        };
        searchTextField.addFocusListener(focusListener);
        nameTextField.addFocusListener(focusListener);
        mailTextField.addFocusListener(focusListener);
        passTextField.addFocusListener(focusListener);
        accountNameTextField.addFocusListener(focusListener);
        phoneTextField.addFocusListener(focusListener);
    }

    public void setBorderTFOnFocus(JTextField j, boolean isFocus) {
        if (isFocus) {
            j.setBorder(BorderFactory.createLineBorder(new Color(255, 101, 0)));
        } else {
            j.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)));
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

        draggableRoundPanel1 = new CUSTOM.DraggableRoundPanel();
        draggableRoundPanel3 = new CUSTOM.DraggableRoundPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        empListTable = new javax.swing.JTable();
        titleTableLabel = new javax.swing.JLabel();
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
        nameValidateLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        phoneTextField = new javax.swing.JTextField();
        phoneValidateLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        mailTextField = new javax.swing.JTextField();
        mailValidateLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        accountNameTextField = new javax.swing.JTextField();
        accountNameValidateLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        passTextField = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        dateTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        positionComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        delButton = new javax.swing.JButton();
        draggableRoundPanel2 = new CUSTOM.DraggableRoundPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        sortComboBox = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(31, 31, 31));

        draggableRoundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        draggableRoundPanel3.setBackground(new java.awt.Color(31, 31, 31));

        jScrollPane2.setBackground(new java.awt.Color(32, 32, 32));

        empListTable.setBackground(new java.awt.Color(32, 32, 32));
        empListTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        empListTable.setForeground(new java.awt.Color(240, 240, 240));
        empListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1"},
                {"2"},
                {"3"},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        empListTable.setGridColor(new java.awt.Color(255, 255, 255));
        empListTable.setSelectionBackground(new java.awt.Color(32, 32, 32));
        empListTable.setSelectionForeground(new java.awt.Color(255, 101, 0));
        empListTable.setShowGrid(true);
        empListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empListTableMouseClicked(evt);
            }
        });
        empListTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                empListTableKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(empListTable);

        titleTableLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleTableLabel.setForeground(new java.awt.Color(255, 101, 0));
        titleTableLabel.setText("DANH SÁCH NHÂN VIÊN");

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
                .addGap(269, 269, 269)
                .addComponent(titleTableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshButton)
                .addGap(35, 35, 35))
            .addGroup(draggableRoundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        draggableRoundPanel3Layout.setVerticalGroup(
            draggableRoundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggableRoundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(draggableRoundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleTableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        draggableRoundPanel4.setBackground(new java.awt.Color(31, 31, 31));

        jPanel2.setBackground(new java.awt.Color(32, 32, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 101, 0));
        jLabel3.setText("THÔNG TIN NHÂN VIÊN");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(80, 80, 80))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(32, 32, 32));

        jPanel5.setBackground(new java.awt.Color(32, 32, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(210, 210, 210));
        jLabel5.setText("Mã nhân viên");

        iDTextField.setEditable(false);
        iDTextField.setBackground(new java.awt.Color(118, 118, 118));
        iDTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        iDTextField.setForeground(new java.awt.Color(51, 51, 51));
        iDTextField.setBorder(null);
        iDTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iDTextField)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(32, 32, 32));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(210, 210, 210));
        jLabel6.setText("Tên nhân viên");

        nameTextField.setBackground(new java.awt.Color(32, 32, 32));
        nameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nameTextField.setForeground(new java.awt.Color(255, 255, 255));
        nameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        nameTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        nameValidateLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        nameValidateLabel.setText("Nhập tên không có số và kí tự đặc biệt");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(nameValidateLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(nameTextField)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nameValidateLabel))
        );

        jPanel7.setBackground(new java.awt.Color(32, 32, 32));

        phoneTextField.setBackground(new java.awt.Color(32, 32, 32));
        phoneTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneTextField.setForeground(new java.awt.Color(255, 255, 255));
        phoneTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        phoneTextField.setCaretColor(new java.awt.Color(255, 255, 255));
        phoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneTextFieldKeyTyped(evt);
            }
        });

        phoneValidateLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        phoneValidateLabel.setText("Nhập số điện thoại tối thiểu 11 số");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(210, 210, 210));
        jLabel7.setText("SĐT");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneValidateLabel)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(phoneValidateLabel))
        );

        jPanel8.setBackground(new java.awt.Color(32, 32, 32));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(210, 210, 210));
        jLabel8.setText("Email");

        mailTextField.setBackground(new java.awt.Color(32, 32, 32));
        mailTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mailTextField.setForeground(new java.awt.Color(255, 255, 255));
        mailTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        mailTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        mailValidateLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        mailValidateLabel.setText("Nhập đúng định dạng chứa dấu '@'");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(mailValidateLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(mailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mailValidateLabel))
        );

        jPanel9.setBackground(new java.awt.Color(32, 32, 32));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(210, 210, 210));
        jLabel9.setText("Tài khoản");

        accountNameTextField.setBackground(new java.awt.Color(32, 32, 32));
        accountNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        accountNameTextField.setForeground(new java.awt.Color(255, 255, 255));
        accountNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        accountNameTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        accountNameValidateLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        accountNameValidateLabel.setText("Không nhập kí tự đặc biệc và khoảng cách");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountNameValidateLabel)
                    .addComponent(accountNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountNameValidateLabel))
        );

        jPanel10.setBackground(new java.awt.Color(32, 32, 32));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(210, 210, 210));
        jLabel10.setText("Mật khẩu");

        passTextField.setBackground(new java.awt.Color(32, 32, 32));
        passTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passTextField.setForeground(new java.awt.Color(255, 255, 255));
        passTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        passTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(32, 32, 32));

        dateTextField.setEditable(false);
        dateTextField.setBackground(new java.awt.Color(118, 118, 118));
        dateTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateTextField.setForeground(new java.awt.Color(51, 51, 51));
        dateTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        dateTextField.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(210, 210, 210));
        jLabel12.setText("Ngày sinh");

        jDateChooser1.setBackground(new java.awt.Color(204, 102, 0));
        jDateChooser1.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setAlignmentX(0.0F);
        jDateChooser1.setAlignmentY(0.0F);
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(32, 32, 32));

        positionComboBox.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(210, 210, 210));
        jLabel4.setText("Chức vụ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(positionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(positionComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(31, 31, 31));

        addButton.setBackground(new java.awt.Color(255, 101, 0));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Thêm");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(44, 44, 46));
        updateButton.setForeground(new java.awt.Color(118, 118, 118));
        updateButton.setText("Sửa");
        updateButton.setEnabled(false);
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });

        delButton.setBackground(new java.awt.Color(44, 44, 46));
        delButton.setForeground(new java.awt.Color(118, 118, 118));
        delButton.setText("Xóa");
        delButton.setEnabled(false);
        delButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout draggableRoundPanel4Layout = new javax.swing.GroupLayout(draggableRoundPanel4);
        draggableRoundPanel4.setLayout(draggableRoundPanel4Layout);
        draggableRoundPanel4Layout.setHorizontalGroup(
            draggableRoundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(draggableRoundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(draggableRoundPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        draggableRoundPanel4Layout.setVerticalGroup(
            draggableRoundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });

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

        sortComboBox.setBackground(new java.awt.Color(32, 32, 32));
        sortComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sortComboBox.setForeground(new java.awt.Color(204, 204, 204));
        sortComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout draggableRoundPanel2Layout = new javax.swing.GroupLayout(draggableRoundPanel2);
        draggableRoundPanel2.setLayout(draggableRoundPanel2Layout);
        draggableRoundPanel2Layout.setHorizontalGroup(
            draggableRoundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggableRoundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        draggableRoundPanel2Layout.setVerticalGroup(
            draggableRoundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(draggableRoundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sortComboBox)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout draggableRoundPanel1Layout = new javax.swing.GroupLayout(draggableRoundPanel1);
        draggableRoundPanel1.setLayout(draggableRoundPanel1Layout);
        draggableRoundPanel1Layout.setHorizontalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggableRoundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(draggableRoundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(draggableRoundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(draggableRoundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        draggableRoundPanel1Layout.setVerticalGroup(
            draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggableRoundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(draggableRoundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(draggableRoundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(draggableRoundPanel1Layout.createSequentialGroup()
                        .addComponent(draggableRoundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(draggableRoundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(draggableRoundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        // TODO add your handling code here:
        int selectedRow = empListTable.getSelectedRow();
        if (selectedRow != -1) {
            empListTable.clearSelection();
            iDTextField.setText("");
            List<JTextField> tfList = takeTextFieldsList();
            resetAllTF(tfList);

            positionComboBox.setSelectedIndex(0);
            EnableBtn(delButton, false);
            EnableBtn(updateButton, false);
            nameTextField.requestFocus();
        } else {
            try {
                //Check all input is ok? adn then submit
                addEmp();
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_addButtonMouseClicked

    private boolean allInputValid() {
        HashMap<String, JTextField> tfList = new HashMap<>();
        tfList.put("nameTextField", nameTextField);
        tfList.put("passTextField", passTextField);
        tfList.put("mailTextField", mailTextField);
        tfList.put("accountNameTextField", accountNameTextField);
        tfList.put("dateTextField", dateTextField);
        tfList.put("phoneTextField", phoneTextField);
        boolean isValid = true;
        for (String key : tfList.keySet()) {
            String input = tfList.get(key).getText();
            if (input.isEmpty()) {
                isValid = false;
                break;
            }
            switch (key) {
                case "nameTextField":
                    if (!kt.KTHoVaTen(input)) {
                        System.out.println("input => " + input + key);
                        isValid = false;
                    }
                    break;
                case "phoneTextField":
                    if (!kt.isValidPhone(input)) {
                        isValid = false;
                    }
                    break;
                case "mailTextField":
                    if (!kt.isValidEmail(input)) {
                        isValid = false;
                    }
                    break;
                case "accountNameTextField":
                    if (!kt.KTKyTuDacBietKhongKhoangCach(input)) {
                        isValid = false;
                    }
                    break;
                case "dateTextField":
                case "passTextField":
                    if (input.isEmpty()) {
                        isValid = false;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }

        return isValid;
    }

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        if (evt.getPropertyName().equals("date")) {
            Date selectedDate = jDateChooser1.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormated = dateFormat.format(selectedDate);

            dateTextField.setText(dateFormated);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void empListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empListTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = empListTable.getSelectedRow();

        if (selectedRow != -1) {
            EnableBtn(delButton, true);
            EnableBtn(updateButton, true);
            empListTable.setSelectionBackground(Color.BLUE);
            empListTable.setSelectionForeground(Color.WHITE);
            for (int i = 0; i < nvList.size(); i++) {
                if (i == selectedRow) {
                    showDetailEmp(nvList.get(i));
                    break;
                }
            }
        }
    }//GEN-LAST:event_empListTableMouseClicked
    private void showDetailEmp(NhanVien nv) {
        iDTextField.setText("NV" + String.valueOf(nv.getMaNV()));
        nameTextField.setText(nv.getTenNV());
        accountNameTextField.setText(nv.getTaiKhoan());
        passTextField.setText(nv.getMatKhau());
        dateTextField.setText(String.valueOf(nv.getNgaySinh()));
        mailTextField.setText(nv.getEmail());
        phoneTextField.setText(nv.getSDT());
        if (nv.getMaCV() > 0) {
            positionComboBox.setSelectedItem(iDToPosition(nv.getMaCV()));

        } else {
            positionComboBox.setSelectedIndex(0);

        }
    }

    private void delEmp() throws ClassNotFoundException, SQLException {
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên " + nameTextField.getText() + "không");

        if (option == JOptionPane.YES_OPTION) {

            int MaNV = Integer.parseInt(iDTextField.getText().split("NV")[1]);
            
            int rowAffect = nvBUS.delEmp(MaNV);
            if(rowAffect > 0){
                JOptionPane.showMessageDialog(this, "Deleted Successfully", "Success Annoucement", JOptionPane.INFORMATION_MESSAGE);
                resetAllTF(takeTextFieldsList());
                positionComboBox.setSelectedIndex(0);
                empListTable.clearSelection();
                filter();
            }
        };
    }

    private void updateEmp() throws ParseException, ClassNotFoundException, SQLException {
        int option = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa không ?");
        if (option == JOptionPane.YES_OPTION) {
            if (allInputValid()) {
                int MaNV = Integer.parseInt(iDTextField.getText().split("NV")[1]);
                String TenNV = nameTextField.getText();
                String SDT = phoneTextField.getText();
                String Email = mailTextField.getText();
                String TaiKhoan = accountNameTextField.getText();
                String MatKhau = passTextField.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

                Date NgaySinh = dateFormat.parse(dateTextField.getText());
                int maCV = positionToID((String) positionComboBox.getSelectedItem());
                NhanVien newNV = new NhanVien(MaNV, TenNV, SDT, Email, NgaySinh, TaiKhoan, MatKhau, maCV, 0);
                int rowAffect = nvBUS.updateEmp(newNV);
                if (rowAffect > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Update", JOptionPane.INFORMATION_MESSAGE);
                    resetAllTF(takeTextFieldsList());
                    positionComboBox.setSelectedIndex(0);
                    empListTable.clearSelection();
                    iDTextField.setText("");
                    filter();
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ và đúng định dạng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        // TODO add your handling code here:
//
//        checkInputToEnable(evt, searchButton, searchTextField);
        if (evt.getKeyChar() == KeyEvent.VK_ENTER && searchButton.isEnabled()) {
            String input = searchTextField.getText();
            searchInput = input;
            try {
                filter();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            refreshButton.setVisible(true);
        }
    }//GEN-LAST:event_searchTextFieldKeyTyped

    public void filter() throws ClassNotFoundException, SQLException {
        String sortCondition = (String) sortComboBox.getSelectedItem();
        nvList = nvBUS.filter(sortCondition, searchInput);
        initTable(nvList);
    }


    private void searchTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusLost
        // TODO add your handling code here:
        setBorderTFOnFocus(searchTextField, false);
    }//GEN-LAST:event_searchTextFieldFocusLost

    private void searchTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusGained
        setBorderTFOnFocus(searchTextField, true);
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextFieldFocusGained

    private void phoneTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneTextFieldKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_phoneTextFieldKeyTyped

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
        // TODO add your handling code here:
        if (updateButton.isEnabled()) {
            try {
                updateEmp();
            } catch (ParseException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_updateButtonMouseClicked

    private void empListTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empListTableKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_DELETE) {
            int selectedRow = empListTable.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    delEmp();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            };
        };
    }//GEN-LAST:event_empListTableKeyPressed

    private void delButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delButtonMouseClicked
        // TODO add your handling code here:
        int selectedRow = empListTable.getSelectedRow();
        if (selectedRow != -1) {
            try {
                delEmp();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
    }//GEN-LAST:event_delButtonMouseClicked

    private void sortComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortComboBoxItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            try {
                filter();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_sortComboBoxItemStateChanged

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        // TODO add your handling code here:
        if (searchButton.isEnabled()) {
            String input = searchTextField.getText();
            searchInput = input;
            try {
                filter();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            refreshButton.setVisible(true);
        }
    }//GEN-LAST:event_searchButtonMouseClicked

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonActionPerformed

    private void refreshButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshButtonMouseClicked
        // TODO add your handling code here:
        searchInput = null;
        sortComboBox.setSelectedIndex(0);
        searchTextField.setText("");
        try {
            filter();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshButton.setVisible(false);
    }//GEN-LAST:event_refreshButtonMouseClicked

    private void addEmp() throws ParseException, ClassNotFoundException, SQLException {
        if (allInputValid()) {
            String name = nameTextField.getText();
            String phone = phoneTextField.getText();
            String mail = mailTextField.getText();
            String accountName = accountNameTextField.getText();
            String pass = passTextField.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date dateBirth = dateFormat.parse(dateTextField.getText());
            int positionID = positionToID((String) positionComboBox.getSelectedItem());
            NhanVien newNV = new NhanVien(name, phone, mail, dateBirth, accountName, pass, positionID, 0);
            int rowAffect = nvBUS.addEmp(newNV);
            if (rowAffect > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                filter();
                resetAllTF(takeTextFieldsList());
                sortComboBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ và đúng định dạng", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int positionToID(String position) {
        if (position.equals("Chọn chức vụ")) {
            return -1;
        } else {
            for (ChucVu cv : cvList) {
                if (cv.getTenCV().equals(position)) {
                    return cv.getMaCV();
                }
            }
        }
        return -1;
    }

    private List<JTextField> takeTextFieldsList() {
        List<JTextField> tfList = new ArrayList<>();
        tfList.add(nameTextField);
        tfList.add(passTextField);
        tfList.add(mailTextField);
        tfList.add(accountNameTextField);
        tfList.add(dateTextField);
        tfList.add(phoneTextField);
        return tfList;
    }

    private void resetAllTF(List<JTextField> tfList) {
        for (JTextField tf : tfList) {
            tf.setText("");
        }
    }

    private void searchEmp() {

    }

    private void EnableBtn(JButton btn, boolean condition) {
        if (condition) {
            btn.setEnabled(true);
            btn.setBackground(new Color(255, 101, 0));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setEnabled(false);
            btn.setBackground(new Color(44, 44, 46));
            btn.setForeground(new Color(118, 118, 118));
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNameTextField;
    private javax.swing.JLabel accountNameValidateLabel;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton delButton;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel1;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel2;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel3;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel4;
    private javax.swing.JTable empListTable;
    private javax.swing.JTextField iDTextField;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField mailTextField;
    private javax.swing.JLabel mailValidateLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel nameValidateLabel;
    private javax.swing.JTextField passTextField;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JLabel phoneValidateLabel;
    private javax.swing.JComboBox<String> positionComboBox;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JLabel titleTableLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
//    public static void main(String[] args) {
//        // Create a JFrame
//        JFrame frame = new JFrame("My Application");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Create a JPanel with your interface components
//        JPanel panel = new NhanVienJPanel();
//        
////         Add the panel to the frame
//        frame.getContentPane().add(panel);
//
//        // Pack and show the frame
//        frame.pack();
//        frame.setVisible(true);
//    }
}
