package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JFrame implements ActionListener {
    JLabel userLabel, passwordLabel;
    JTextField userTextField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;

    public LoginPanel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        userLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        userTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        JPanel panel = new JPanel(new GridLayout(3, 2));
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
                        JOptionPane.showInputDialog(LoginPanel.this, "Tên đăng nhập không được để trống", "Hãy Nhập lại");
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
                        JOptionPane.showInputDialog(LoginPanel.this, "Mật Khẩu Không Được Để Trống","Hãy nhập lại!");
                    } 
                    loginButton.doClick();
                }
                
            }
        });
         // Xử lý sự kiện khi nhấn nút đăng nhập
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                char[] password = passwordField.getPassword();
                boolean isValid = validateLogin(username, password);
                if (isValid) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Login successful!");
                    openMainWindow();
                    closeLoginWindow();
                } else  {
                    JOptionPane.showMessageDialog(LoginPanel.this, "Tên Đăng Nhập Hoặc Mật Khẩu Không Hợp Lệ", "Error", JOptionPane.ERROR_MESSAGE);
                } 
            }
        });
        cancelButton.addActionListener(e -> System.exit(0));
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
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    public static void main(String[] args) {
        new LoginPanel();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
