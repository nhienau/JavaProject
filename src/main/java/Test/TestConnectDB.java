package Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import DAO.DB;

public class TestConnectDB {
	public static void main(String[] args) {
		DB db = new DB();
		db.getConnect();
		Connection connection = db.getConn();
		Statement statement = null;
		String query = "SELECT * FROM nhanvien";
		
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while(result.next())
				System.out.println(result.getInt(1) + " " +
				result.getString(2) + " " +
				result.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeConnect();
	}
}
