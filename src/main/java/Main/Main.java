/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.naming.InitialContext;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import DTO.NhanVien;
import GUI.*;

public class Main {
	static LoginForm loginFrame;
	static HomeGui mainFrame;
	private NhanVien currentUser = null;

    public NhanVien getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(NhanVien currentUser) {
		this.currentUser = currentUser;
	}
	
	private void init() {
		loginFrame = new LoginForm();
        mainFrame = new HomeGui();
        
        // set Windows look and feel
        try {
        	javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(mainFrame);
        
        loginFrame.addWindowListener(new LoginFrameWindowAdapter());
        mainFrame.addWindowListener(new MainFrameWindowAdapter());
        mainFrame.addComponentListener(new MainFrameComponentAdapter());
	}

	private class LoginFrameWindowAdapter extends WindowAdapter {
		@Override
        public void windowClosing(WindowEvent e) {
        }
    	
    	@Override
        public void windowClosed(WindowEvent e) {
            if (loginFrame.isLoginSuccess()) {
            	setCurrentUser(loginFrame.getCurrentUser());
            	mainFrame.setVisible(true);
            }
        }
    }
	
	private class MainFrameWindowAdapter extends WindowAdapter {
		@Override
        public void windowClosing(WindowEvent e) {
//            System.out.println("exit");
        }
    	
    	@Override
        public void windowClosed(WindowEvent e) {
            if (mainFrame.isLoggedOut()) {
            	loginFrame.clearInputFields();
            	loginFrame.setVisible(true);
            }
        }
	}
	
	private class MainFrameComponentAdapter extends ComponentAdapter {
		@Override
        public void componentShown(ComponentEvent e) {
    		mainFrame.setLoggedOut(false);
        }
	}
	
	public static void main(String[] args) {
		new Main().init();
    }
}
