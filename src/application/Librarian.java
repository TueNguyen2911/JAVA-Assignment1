package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Librarian {
	private Integer Lib_id; 
	private String FName, LName, Username, Phone, Email;
	
	public Librarian(Integer Lib_id, String FName, String LName, String Username, String Phone, String Email) {
		this.Lib_id = Lib_id;
		this.FName = FName;
		this.LName = LName;
		this.Username = Username;
		this.Phone = Phone;
		this.Email = Email;
		
	}
	
	// getters must be same as members 
	public int getLib_id() {
		return Lib_id;
	}
	public String getFName() {
		return FName;
	}
	public String getLName() {
		return LName;
	}
	public String getUsername() {
		return Username;
	}
	public String getPhone() {
		return Phone;
	}
	public String getEmail() {
		return Email;
	}
}

