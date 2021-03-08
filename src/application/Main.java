package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class Main extends Application {
	private static Stage stage;
	private static BorderPane rootPane;
	private static BorderPane sidePane;

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 1200, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("LogIn");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Change scence in a window
	 * @param fxmlFile which fxmlFile to change the window to
	 */
	public void changeScene(String fxmlFile) {
		try {
			rootPane = FXMLLoader.load(getClass().getResource(fxmlFile));
			stage.getScene().setRoot(rootPane);
		} catch (IOException e) {
			e.printStackTrace();
			e.getCause();
		}

	}
	/**
	 * Change scence in a Stage
	 * @param fxmlFile which fxmlFile to add to the AdminDashBoard
	 */
	public void addScene(String fxmlFile) {

		try {
			if(!fxmlFile.equals("none")) {
				System.out.println(fxmlFile);
				rootPane.getChildren().remove(sidePane);
				try {
					sidePane = FXMLLoader.load(getClass().getResource(fxmlFile));
				} catch(Exception e) {
					e.printStackTrace();
					e.getCause();
				}
				rootPane.setCenter(sidePane);
				
			}
			else {
				System.out.println("null");
				rootPane.getChildren().remove(sidePane);
				Text welcome = new Text("Welcome!");
				welcome.setFont(Font.font(40));
				rootPane.setCenter(welcome);
			}

			
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

	
}
