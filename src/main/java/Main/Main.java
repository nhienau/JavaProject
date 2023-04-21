/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import GUI.*;

public class Main {
	static LoginForm loginFrame;
	static HomeGui mainFrame;
    public static void main(String[] args) {
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

        loginFrame.addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent e) {
//                System.out.println("Exit");
//                System.out.println(loginFrame.isLoginSuccess());
            }
        	
        	@Override
            public void windowClosed(WindowEvent e) {
//                System.out.println("Login success");
//                System.out.println(loginFrame.isLoginSuccess());
                
                if (loginFrame.isLoginSuccess()) {
                	mainFrame.setVisible(true);
                }
            }
        });
        
        mainFrame.addWindowListener(new WindowAdapter() {
        	@Override
            public void windowClosing(WindowEvent e) {
//                System.out.println("exit");
            }
        	
        	@Override
            public void windowClosed(WindowEvent e) {
                if (mainFrame.isLoggedOut()) {
                	loginFrame.clearInputFields();
                	loginFrame.setVisible(true);
                }
            }
        });
        
        mainFrame.addComponentListener(new ComponentAdapter() {
        	@Override
            public void componentShown(ComponentEvent e) {
        		mainFrame.setLoggedOut(false);
//                System.out.println("main frame is visible");
            }
		});
    }
}
