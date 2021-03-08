package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteLib implements Initializable{
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
	
	@FXML 
	private TableColumn<Librarian, Button> deleteCol;

	@Override
	public void initialize(URL url, ResourceBundle rsb) {
		//Basically what serCellValueFactory does is to get the property specified of the Librarian object in PropertyValueFactory.
		//You also need to create public getter function in Librarian class.
		idCol.setCellValueFactory(new PropertyValueFactory<Librarian, Integer>("Lib_id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("FName"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("LName"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Username"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Phone"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Email"));
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		final ObservableList<Librarian> data = FXCollections.observableArrayList();
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet resultLib = statement.executeQuery("SELECT * FROM Lib");
			while(resultLib.next()) {
				data.add( new Librarian( resultLib.getInt("Lib_id"), resultLib.getString("FName"), resultLib.getString("LName")
						, resultLib.getString("Username"), resultLib.getString("PhoneNumber"), resultLib.getString("Email")));
				
			}
			table.setItems(data);
			//table.getColumns().addAll(idCol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
		}
	}
	@FXML 
	/**
	 * Delete a record selected in the tableview. 
	 * @param event
	 */
	private void deleteALib(ActionEvent event) {
		table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
		System.out.println(table.getSelectionModel().getSelectedItem().getLib_id());
		int lib_id = table.getSelectionModel().getSelectedItem().getLib_id();
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		Statement statement;
		try {
			statement = connectDB.createStatement();
			statement.execute("DELETE FROM Lib WHERE Lib_id = " + lib_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
