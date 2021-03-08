package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddLib {
	@FXML 
	private TextField usernameLib, fnameLib, lnameLib, phoneLib, emailLib;
	
	@FXML 
	private PasswordField passwordLib1, passwordLib2;
	
	@FXML 
	private Label libRegisterError;
	
	private static Statement statement;
	private static ResultSet resultDB;
	
	public void setResultDB(String query) throws SQLException {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		statement = connectDB.createStatement();
		resultDB = statement.executeQuery(query);
	}
	/**
	 * Register an Librarian, add one to the database
	 * @throws SQLException
	 */
	public void registerLib() throws SQLException {
		try {
			setResultDB("Select MAX(Lib_id) FROM Lib");
			int max_id = resultDB.next() == true ? resultDB.getInt(1) + 1 : 0;
			
			String insertLib = ("insert into Lib (Lib_id, FName, LName, Username, Password, Email, PhoneNumber) "
					+ "values (" + max_id + ", '" + fnameLib.getText() + "', '" + lnameLib.getText() + "', '" + usernameLib.getText() + "', '" + passwordLib1.getText()  
					+ "', '" + emailLib.getText() + "', '" + phoneLib.getText() +  "')");
			
			System.out.println(insertLib);
			statement.execute(insertLib);
			AdminDashBoard dashboard = new AdminDashBoard(); 
			dashboard.addLib();
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	/**
	 * Check the information, call registerLib() if correct
	 * @param event
	 */
	public void checkRegister(ActionEvent event) {
		
		if(usernameLib.getText().length() == 0 || fnameLib.getText().length() == 0 || lnameLib.getText().length() == 0 || phoneLib.getText().length() == 0 
				|| phoneLib.getText().length() == 0 || emailLib.getText().length() == 0 || passwordLib1.getText().length() == 0 || passwordLib2.getText().length() == 0)
			libRegisterError.setText("Information can't be blank");
		
		else if(!passwordLib1.getText().equals(passwordLib2.getText())) 
			libRegisterError.setText("Password do not match!");

		else {
			Alert a1 = new Alert(Alert.AlertType.INFORMATION);
			a1.setHeaderText("Librarian " + usernameLib.getText() + " added!");
			a1.showAndWait();
			try {
				registerLib();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.getCause();
			}
		}
	}

}
