package application;

import application.model.DataSource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("JFXJDBC.fxml"));
			BorderPane root = (BorderPane) loader.load();
			JFXJDBCController controller = loader.getController();
			controller.listArtists();
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Music Database");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		super.init();
		if(!DataSource.getInstance().open()) {
			System.err.println("ERROR: Failed to connect to database");
			Platform.exit();
		}
		
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		DataSource.getInstance().close();
	}
	
	
}
