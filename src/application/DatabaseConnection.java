package application;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public Connection databaseLink; 
	
	public Connection getConnection() {
		String databaseName = "A1db.db";
		String url = "jdbc:sqlite:C:\\Users\\tueng\\Desktop\\A1_2\\TestFx15\\src\\application\\" + databaseName;
		try {
			databaseLink = DriverManager.getConnection(url);
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return databaseLink;
	}
}
