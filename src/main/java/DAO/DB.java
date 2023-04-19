package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DB {
    
    public DB(){
        
    }
    
    public static Connection connect() throws ClassNotFoundException, SQLException{
         final String url= "jdbc:mysql://localhost:3306/javaproject";
         final String user ="root";
         final String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
    
    
    
}
