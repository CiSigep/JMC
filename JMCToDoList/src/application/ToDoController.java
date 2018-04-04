package application;

import java.io.IOException;
//import java.time.LocalDate;
//import java.time.Month;
import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

import application.model.ToDoData;
import application.model.ToDoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
//import javafx.scene.input.MouseEvent;


public class ToDoController {
	
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private ListView<ToDoItem> toDoListView;
	@FXML
	private TextArea detailsArea;
	@FXML
	private Label deadlineLabel;
	
	//private List<ToDoItem> items;
	
	public void initialize() {
/*		ToDoItem tdi = new ToDoItem("Check Mail", "Go to the Mailbox and check the mail.", LocalDate.of(2018, Month.MAY, 1));
		ToDoItem tdi2 = new ToDoItem("Test App", "Write your test applications.", LocalDate.of(2018, Month.APRIL, 20));
		ToDoItem tdi3 = new ToDoItem("Doctor's Appointment", "Go to Doctor.", LocalDate.of(2018, Month.FEBRUARY, 7));
		
		items = new ArrayList<>();
		items.add(tdi);
		items.add(tdi2);
		items.add(tdi3);
		
		ToDoData.getInstance().setItems(items);*/
		
		toDoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ToDoItem>() {

			@Override
			public void changed(ObservableValue<? extends ToDoItem> observable, ToDoItem oldValue, ToDoItem newValue) {
				if(newValue != null) {
					detailsArea.setText(newValue.getDetails());
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM d, yyyy");
					deadlineLabel.setText(dtf.format(newValue.getDeadline()));
				}
				
			}
		});
		
		toDoListView.getItems().setAll(ToDoData.getInstance().getItems());
		toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		toDoListView.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void showNewItemDialog() {
		Dialog<ButtonType> d = new Dialog<>();
		d.initOwner(mainBorderPane.getScene().getWindow());
		FXMLLoader fl = new FXMLLoader();
		fl.setLocation(getClass().getResource("ToDoItemDialog.fxml"));
		try {
			d.getDialogPane().setContent(fl.load());
			d.setTitle("Create New To Do item");
		} catch (IOException ie) {
			System.out.println(ie.getMessage());
			ie.printStackTrace();
			return;
		}
		
		d.getDialogPane().getButtonTypes().add(ButtonType.OK);
		d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> result = d.showAndWait();
		if(result.isPresent() && result.get().equals(ButtonType.OK)) {
			NewDialogController ndc = fl.getController();
			ToDoItem tdi = ndc.processResults();
			toDoListView.getItems().setAll(ToDoData.getInstance().getItems());
			toDoListView.getSelectionModel().select(tdi);
	    }
		else if(result.isPresent() && result.get().equals(ButtonType.CANCEL))
			System.out.println("CANCEL");
	}
	
/*	@FXML
	public void onClickListView(MouseEvent me) {
		ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
		StringBuilder sb = new StringBuilder(item.getDetails() + "\n\n\n\n");
		sb.append("Due: " + item.getDeadline().toString());
		
		detailsArea.setText(sb.toString());
		detailsArea.setText(item.getDetails());
		deadlineLabel.setText(item.getDeadline().toString());
	}
	*/
}
