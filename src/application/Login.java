package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login implements Initializable {
	@FXML
	private Button loginButton;

	@FXML 
	private Label loginError;
	
	@FXML 
	private TextField username;
	
	@FXML 
	private PasswordField password;
	
	@FXML 
	private ChoiceBox<String> loginChoice;
	
	public Login() {
		
	}
	
	/**
	 * Check for empty loginChoice, if not then call checkAdminLogin();
	 * @param event
	 * @throws IOException
	 */
	public void userLogin(ActionEvent event) throws IOException {
		if(loginChoice.getValue() == null)
			loginError.setText("Choose who are you logging in as!");
		else if(loginChoice.getValue().equals("Admin"))
			checkAdminLogin();
	}
	
	/**
	 * Check if username and password are in the database, display error if incorrect, else change window to AdminDashBoard
	 * @throws IOException
	 */
	private void checkAdminLogin() throws IOException {
		Main m = new Main();
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		String verifyLoginQuery = "SELECT count(1) FROM Admin WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";
		
		try {
			Statement statement =  connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLoginQuery);
			while(queryResult.next()) {
				if(queryResult.getInt(1) == 1) {
					loginError.setText("Success");
					m.changeScene("AdminDashBoard.fxml");
				}
				else {
					loginError.setText("Log-in Error! Try again");
				}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}


	@Override
	public void initialize(URL url, ResourceBundle rsb) {
		// TODO Auto-generated method stub
		loginChoice.getItems().add("Admin");
		loginChoice.getItems().add("Librarian");
		
	}
	
}
