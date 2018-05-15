package application;

//import javafx.application.Platform;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {
	
	@FXML
	private ListView<String> listView;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label progressLabel;
	
	//private Task<ObservableList<String>> task;
	private Service<ObservableList<String>> eService;
	

	public void initialize() {
		/*task = new Task<ObservableList<String>>() {
			
			@Override
			protected ObservableList<String> call() throws Exception {
				
				String[] names = {"John Smith", "Jane Doe", "Test Testerson", "Ronald Dole"};
				
				final ObservableList<String> employees = FXCollections.observableArrayList();
				
				for(int i = 0; i < names.length; i++) {
					employees.add(names[i]);
					updateProgress(i + 1, names.length);
					updateMessage("Added " + names[i] + " to the list");
					Thread.sleep(200);
				}
					
				
				Platform.runLater(new Runnable() { // Not Good Practice
					
					@Override
					public void run() {
						listView.setItems(employees);
						
					}
				});
				return employees;
			}
		};*/
		
		eService = new EmployeeService();
		
		
		
		progressLabel.textProperty().bind(eService.messageProperty());
		progressBar.progressProperty().bind(eService.progressProperty());
		listView.itemsProperty().bind(eService.valueProperty()); // Better
		
/*		eService.setOnRunning(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
				progressBar.setVisible(true);
				progressLabel.setVisible(true);
			}
		});
		
		eService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
				progressBar.setVisible(false);
				progressLabel.setVisible(false);
			}
		});*/
		
		progressBar.visibleProperty().bind(eService.runningProperty());
		progressLabel.visibleProperty().bind(eService.runningProperty());
	}
	
	@FXML
	public void onButtonPressed(ActionEvent ae) {
		//new Thread(task).start();
		if(eService.getState() == Service.State.SUCCEEDED) {
			eService.reset();
			eService.start();
		} else if (eService.getState() == Service.State.READY) {
			eService.start();
		}
	}
}
