package application;
	
import java.io.IOException;

import application.model.ToDoData;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("ToDo.fxml"));
			Scene scene = new Scene(root,900,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("To Do");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() throws Exception {
		try {
			ToDoData.getInstance().saveToDoItems();
		}
		catch (IOException ie) {
			System.out.println(ie.getMessage());
		}
		
	}
	
	@Override
	public void init() throws Exception {
		try {
			ToDoData.getInstance().loadToDoItems();
		}catch (IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
}
