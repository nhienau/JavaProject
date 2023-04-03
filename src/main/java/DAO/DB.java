import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DB {
    String user="root";
    String pass="";
    String url="jdbc:mysql://localhost:3306/javaproject?useUnicode=yes&characterEncoding=UTF-8";
    Connection conn=null;
    Statement st=null;
    ResultSet rs=null;
    public void getConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=(Connection) DriverManager.getConnection(url, user, pass);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Lỗi kết nối database");
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Lỗi kết nối database");
        }
    }
    public void closeConnect(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MyConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
