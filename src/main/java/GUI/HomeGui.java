package GUI;

import CUSTOM.DraggableRoundPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;

import BEAN.DanhMucBean;
import BUS.Controller;
import java.awt.SystemColor;
import java.awt.Cursor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class HomeGui extends javax.swing.JFrame {

    /**
     * Creates new form HomeGui
     */
    public HomeGui() {
        initComponents();
        initController();
         DraggableRoundPanel roundPanel = new DraggableRoundPanel();
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        draggableRoundPanel4 = new CUSTOM.DraggableRoundPanel();
        draggableRoundPanel5 = new CUSTOM.DraggableRoundPanel();
        draggableRoundPanel6 = new CUSTOM.DraggableRoundPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1361, 725));
        setSize(new java.awt.Dimension(1361, 725));
        
        pUserInfo = new JPanel();

        javax.swing.GroupLayout draggableRoundPanel4Layout = new javax.swing.GroupLayout(draggableRoundPanel4);
        draggableRoundPanel4Layout.setHorizontalGroup(
        	draggableRoundPanel4Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, draggableRoundPanel4Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(pUserInfo, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        			.addContainerGap())
        );
        draggableRoundPanel4Layout.setVerticalGroup(
        	draggableRoundPanel4Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, draggableRoundPanel4Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(pUserInfo, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        lblTenNV = new JLabel("nhanvien.TenNV");
        lblTenNV.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        lblTenNV.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        lblChucVu = new JLabel("chucvu.TenCV");
        lblChucVu.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GroupLayout gl_pUserInfo = new GroupLayout(pUserInfo);
        gl_pUserInfo.setHorizontalGroup(
        	gl_pUserInfo.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_pUserInfo.createSequentialGroup()
        			.addGroup(gl_pUserInfo.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblTenNV)
        				.addComponent(lblChucVu, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(51, Short.MAX_VALUE))
        );
        gl_pUserInfo.setVerticalGroup(
        	gl_pUserInfo.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_pUserInfo.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lblTenNV)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblChucVu, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(21, Short.MAX_VALUE))
        );
        pUserInfo.setLayout(gl_pUserInfo);
        draggableRoundPanel4.setLayout(draggableRoundPanel4Layout);

        javax.swing.GroupLayout draggableRoundPanel6Layout = new javax.swing.GroupLayout(draggableRoundPanel6);
        draggableRoundPanel6.setLayout(draggableRoundPanel6Layout);
        draggableRoundPanel6Layout.setHorizontalGroup(
            draggableRoundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1155, Short.MAX_VALUE)
        );
        draggableRoundPanel6Layout.setVerticalGroup(
            draggableRoundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(draggableRoundPanel5, 0, 0, Short.MAX_VALUE)
        				.addComponent(draggableRoundPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(draggableRoundPanel6, GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(draggableRoundPanel6, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(draggableRoundPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(draggableRoundPanel5, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);
        
        pBanHang = new JPanel();
        pBanHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        pSanPham = new JPanel();
        pSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        lblSanPham = new JLabel("Sản phẩm");
        lblSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblSanPham.setHorizontalAlignment(SwingConstants.CENTER);
        lblSanPham.setFont(new Font("Tahoma", Font.BOLD, 14));
        GroupLayout gl_pSanPham = new GroupLayout(pSanPham);
        gl_pSanPham.setHorizontalGroup(
        	gl_pSanPham.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 168, Short.MAX_VALUE)
        		.addComponent(lblSanPham, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        gl_pSanPham.setVerticalGroup(
        	gl_pSanPham.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 38, Short.MAX_VALUE)
        		.addComponent(lblSanPham, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pSanPham.setLayout(gl_pSanPham);
        draggableRoundPanel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        lblBanHang = new JLabel("Bán hàng");
        lblBanHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblBanHang.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanHang.setFont(new Font("Tahoma", Font.BOLD, 14));
        GroupLayout gl_pBanHang = new GroupLayout(pBanHang);
        gl_pBanHang.setHorizontalGroup(
        	gl_pBanHang.createParallelGroup(Alignment.LEADING)
        		.addComponent(lblBanHang, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        gl_pBanHang.setVerticalGroup(
        	gl_pBanHang.createParallelGroup(Alignment.LEADING)
        		.addComponent(lblBanHang, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pBanHang.setLayout(gl_pBanHang);
        draggableRoundPanel5.add(pBanHang);
        draggableRoundPanel5.add(pSanPham);
        
        pLoaiSanPham = new JPanel();
        pLoaiSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        draggableRoundPanel5.add(pLoaiSanPham);
        
        lblLoaiSanPham = new JLabel("Loại sản phẩm");
        lblLoaiSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblLoaiSanPham.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoaiSanPham.setFont(new Font("Tahoma", Font.BOLD, 14));
        GroupLayout gl_pLoaiSanPham = new GroupLayout(pLoaiSanPham);
        gl_pLoaiSanPham.setHorizontalGroup(
        	gl_pLoaiSanPham.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 168, Short.MAX_VALUE)
        		.addComponent(lblLoaiSanPham, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        gl_pLoaiSanPham.setVerticalGroup(
        	gl_pLoaiSanPham.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 38, Short.MAX_VALUE)
        		.addComponent(lblLoaiSanPham, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pLoaiSanPham.setLayout(gl_pLoaiSanPham);
        
        pTaiKhoan = new JPanel();
        draggableRoundPanel5.add(pTaiKhoan);
        
        lblTaiKhoan = new JLabel("Tài khoản");
        lblTaiKhoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
        lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 14));
        GroupLayout gl_pTaiKhoan = new GroupLayout(pTaiKhoan);
        gl_pTaiKhoan.setHorizontalGroup(
        	gl_pTaiKhoan.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 168, Short.MAX_VALUE)
        		.addComponent(lblTaiKhoan, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );
        gl_pTaiKhoan.setVerticalGroup(
        	gl_pTaiKhoan.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 38, Short.MAX_VALUE)
        		.addComponent(lblTaiKhoan, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pTaiKhoan.setLayout(gl_pTaiKhoan);

        pack();
//        initController();
        
    }// </editor-fold>//GEN-END:initComponents

    private void initController() {
    	Controller controller = new Controller(draggableRoundPanel6);
        controller.setView(pBanHang, lblBanHang);
        List <DanhMucBean> listItem = new ArrayList();
        listItem.add(new DanhMucBean("BanHang", pBanHang, lblBanHang));
        listItem.add(new DanhMucBean("SanPham", pSanPham, lblSanPham));
        listItem.add(new DanhMucBean("LoaiSanPham", pLoaiSanPham, lblLoaiSanPham));
        listItem.add(new DanhMucBean("TaiKhoan", pTaiKhoan, lblTaiKhoan));
        controller.setEvent(listItem);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CUSTOM.DraggableRoundPanel draggableRoundPanel4;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel5;
    private CUSTOM.DraggableRoundPanel draggableRoundPanel6;
    private JPanel pBanHang;
    private JLabel lblBanHang;
    private JPanel pSanPham;
    private JLabel lblSanPham;
    private JPanel pLoaiSanPham;
    private JLabel lblLoaiSanPham;
    private JPanel pTaiKhoan;
    private JLabel lblTaiKhoan;
    private JPanel pUserInfo;
    private JLabel lblTenNV;
    private JLabel lblChucVu;
}
