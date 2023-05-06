/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
            	mainFrame.setCurrentUser(loginFrame.getCurrentUser());
            	loginFrame.clearInputFields();
            	try {
					mainFrame.getPermission(mainFrame.getCurrentUser());
				} catch (NoSuchFieldException | IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	mainFrame.setVisible(true);
            }
        }
    }
	
	private class MainFrameWindowAdapter extends WindowAdapter {
		@Override
        public void windowClosing(WindowEvent e) {
			
        }
    	
    	@Override
        public void windowClosed(WindowEvent e) {
            if (mainFrame.isLoggedOut()) {
            	loginFrame.setCurrentUser(null);
            	loginFrame.setLoginSuccess(false);
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
