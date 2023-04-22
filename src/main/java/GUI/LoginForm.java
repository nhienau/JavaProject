package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame implements ActionListener {
    JLabel userLabel, passwordLabel;
    JTextField userTextField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;
    private boolean loginSuccess = false;

    public boolean isLoginSuccess() {
		return loginSuccess;
	}
	public void setLoginSuccess(boolean loginSuccess) {
		this.loginSuccess = loginSuccess;
	}
	
	public LoginForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        userLabel = new JLabel("      Username");
        Font myFont = new Font("Arial", Font.BOLD,16);
        userLabel.setFont(myFont);
        userLabel.setForeground(Color.WHITE);
        passwordLabel = new JLabel("      Password");
        passwordLabel.setFont(myFont);
        passwordLabel.setForeground(Color.WHITE);
        userTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        
        ImageIcon icon = new ImageIcon("main/java/Images/avatar.png");
        int newWidth = 35; // Kích thước mới cho chiều rộng
        int newHeight = 35; // Kích thước mới cho chiều cao
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        loginButton = new JButton("Login", newIcon);
        Color myColor = new Color(255,101,0);
        loginButton.setBackground(myColor);

        ImageIcon icon2 = new ImageIcon("main/java/Images/cancel.png");
        int newWidth2 = 35; // Kích thước mới cho chiều rộng
        int newHeight2 = 35; // Kích thước mới cho chiều cao
        Image img2 = icon2.getImage();
        Image newImg2 = img2.getScaledInstance(newWidth2, newHeight2, Image.SCALE_SMOOTH);
        ImageIcon newIcon2 = new ImageIcon(newImg2);
        cancelButton = new JButton("Cancel",newIcon2);
        cancelButton.setBackground(myColor);

        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        Color myColor2 = new Color(31,31,31);
        panel.setBackground(myColor2);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);
        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);
        setVisible(true);
        
        // Xử lý sự kiện khi textfiel_username bị để trống
        userTextField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(userTextField.getText().isEmpty())
                    {
                        JOptionPane.showInputDialog(LoginForm.this, "Tên đăng nhập không được để trống");
                    }    
                    passwordField.requestFocus();
                }
            }
        });
        
        // Xử lý sự kiện khi textfiel_password bị để trống 
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    char[] password_Chars = passwordField.getPassword();
                    String password = new String(password_Chars);
                    if(password.isEmpty())
                    {
                        JOptionPane.showInputDialog(LoginForm.this, "Mật khẩu không được để trống");
                    } 
                    loginButton.doClick();
                }
                
            }
        });

        // Xử lý sự kiện khi nhấn nút đăng nhập
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	loginButtonActionPerformed(e);
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cancelButtonActionPerformed(e);
            }
        });
    }
	
    // Kiểm tra thông tin đăng nhập hợp lệ
    private boolean validateLogin(String username, char[] password) {
        // Kiểm tra xem username và password có đúng hay không
        return "admin".equals(username) && "123456".equals(new String(password));
    }
    
    // Mở một cửa sổ mới khi đăng nhập thành công
    private void openMainWindow() {
        JFrame mainWindow = new JFrame("Main Window");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 500);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
    
    // Đóng cửa sổ đăng nhập
    private void closeLoginWindow() {
//        SwingUtilities.getWindowAncestor(this).dispose();
        dispose();
    }
    
    public void loginButtonActionPerformed(ActionEvent e) {
    	String username = userTextField.getText();
        char[] password = passwordField.getPassword();
        boolean isValid = validateLogin(username, password);
        if (isValid) {
            JOptionPane.showMessageDialog(LoginForm.this, "Đăng nhập thành công");
//            openMainWindow();
            setLoginSuccess(true);
            closeLoginWindow();
        } else  {
            JOptionPane.showMessageDialog(LoginForm.this, "Tên đăng nhập hoặc mật khẩu không hợp lệ!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cancelButtonActionPerformed(ActionEvent e) {
    	System.exit(0);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
    
    public void clearInputFields() {
    	passwordField.setText("");
    	userTextField.requestFocus();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if (loginButton.isSelected()) {
    		loginButtonActionPerformed(e);
    	} else if (cancelButton.isSelected()) {
    		cancelButtonActionPerformed(e);
    	}
    }
}
