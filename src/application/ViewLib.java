package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewLib implements Initializable{
	@FXML 
	private TableView<Librarian> table;
	 
	@FXML 
	private TableColumn<Librarian, Integer> idCol;
	
	@FXML 
	private TableColumn<Librarian, String> fnameCol;
	
	@FXML 
	private TableColumn<Librarian, String> lnameCol;	
	
	@FXML 
	private TableColumn<Librarian, String> usernameCol;	
	
	@FXML 
	private TableColumn<Librarian, String> phoneCol; 
	
	@FXML
	private TableColumn<Librarian, String> emailCol;
	
	private static Statement statement;
	private static ResultSet resultDB;
	
	public void setResultDB(String query) throws SQLException {
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		statement = connectDB.createStatement();
		resultDB = statement.executeQuery(query);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rsb) {
		
		idCol.setCellValueFactory(new PropertyValueFactory<Librarian, Integer>("Lib_id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("FName"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("LName"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Username"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Phone"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Email"));
		

		final ObservableList<Librarian> data = FXCollections.observableArrayList();
		
		try {
			setResultDB("SELECT * FROM Lib");
			while(resultDB.next()) {
				data.add( new Librarian( resultDB.getInt("Lib_id"), resultDB.getString("FName"), resultDB.getString("LName")
						, resultDB.getString("Username"), resultDB.getString("PhoneNumber"), resultDB.getString("Email")));
				
			}
			table.setItems(data);
			//table.getColumns().addAll(idCol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
		}
	}
	public void createCSVFile(ActionEvent event) {
		String output = null;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("all_librarians.txt", false));
			try {
				setResultDB("SELECT * FROM Lib");
				
				while(resultDB.next()) {
					output = (resultDB.getInt("Lib_id") + "," + resultDB.getString("FName") + "," + resultDB.getString("LName") + ","
							+ resultDB.getString("Username") + "," + resultDB.getString("PhoneNumber") + "," + resultDB.getString("Email"));
					System.out.println(output);
					writer.write(output + "\n");
				}
				writer.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
