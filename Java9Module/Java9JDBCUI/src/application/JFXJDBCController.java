package application;


import application.db.DataSource;
import application.model.Artist;
import application.model.Album;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;

public class JFXJDBCController {
	
	@FXML
	private TableView artistTable;
	
	@FXML
	private ProgressBar progressBar;
	
	@FXML
	public void listArtists() {
		Task<ObservableList<Artist>> task = new GetAllArtistsTask();
		artistTable.itemsProperty().bind(task.valueProperty());
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> progressBar.setVisible(false));
		task.setOnFailed(e -> progressBar.setVisible(false));
		
		
		new Thread(task).start();
	}
	
	@FXML
	public void listAlbumsForArtist() {
		final Artist art = (Artist) artistTable.getSelectionModel().getSelectedItem();
		
		if(art == null) {
			System.err.println("No Artist selected");
			return;
		}
		Task<ObservableList<Album>> task = new Task<ObservableList<Album>>() {
			
			@Override
			protected ObservableList<Album> call() throws Exception {
				return FXCollections.observableArrayList(DataSource.getInstance().getAlbumsForArtist(art.get_id()));
			}
		};
		
		progressBar.progressProperty().bind(task.progressProperty());
		progressBar.setVisible(true);
		task.setOnSucceeded(e -> progressBar.setVisible(false));
		task.setOnFailed(e -> progressBar.setVisible(false));
		
		artistTable.itemsProperty().bind(task.valueProperty());
		
		new Thread(task).start();
	}
	
	@FXML
	public void updateArtist() { // simulate instead of creating new dialog
		final Artist art = (Artist) artistTable.getItems().get(2);
		
		Task<Boolean> task = new Task<Boolean>() {
			
			@Override
			protected Boolean call() throws Exception {
				return DataSource.getInstance().updateArtistName("AC/DC", art.get_id());
			}
		};
		task.setOnSucceeded(e -> {
			if(task.valueProperty().get());
			art.setName("AC/DC");
			artistTable.refresh();
		});
		
		new Thread(task).start();
	}
	
}

class GetAllArtistsTask extends Task<ObservableList<Artist>> {

	@Override
	public ObservableList<Artist> call() throws Exception {
		// TODO Auto-generated method stub
		return FXCollections.observableArrayList(DataSource.getInstance().getAllArtists(DataSource.ORDER_BY_ASC));
	}
}