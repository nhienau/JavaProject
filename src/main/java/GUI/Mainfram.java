package GUI;
import javax.swing.*;

public class Mainfram extends JFrame {
    public Mainfram() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LoginForm loginform = new LoginForm();
        add(loginform);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mainfram());
    }
}