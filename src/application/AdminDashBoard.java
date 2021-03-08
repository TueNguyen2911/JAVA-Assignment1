package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AdminDashBoard implements Initializable {
	
	
	
	@FXML 
	private Text welcomeAdmin;
	
	public void addLib(ActionEvent event) {
		Main main = new Main();
		main.addScene("AddLib.fxml");
	}
	public void addLib() {
		Main main = new Main();
		main.addScene("AddLib.fxml");
	}
	public void adminHome(ActionEvent event) {
		Main main = new Main();
		welcomeAdmin.setText("Welcome!");
		welcomeAdmin.setFont(Font.font(40));
		main.addScene("none");
	}
	public void deleteLib(ActionEvent event) {
		System.out.println("Delete");
		Main main = new Main();

		main.addScene("DeleteLib.fxml");
	}
	public void viewLib(ActionEvent event) {
		Main main = new Main();
		main.addScene("ViewLib.fxml");
	}
	public void logOut(ActionEvent event) {
		Main main = new Main();
		main.changeScene("Login.fxml");
	}
	@Override
	public void initialize(URL url, ResourceBundle rsb) {
		// TODO Auto-generated method stub
		welcomeAdmin.setText("Welcome!");
		welcomeAdmin.setFont(Font.font(40));
	}
	
}
