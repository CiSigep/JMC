package application;

import java.io.IOException;
import java.time.LocalDate;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


public class ToDoController {
	
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private ListView<ToDoItem> toDoListView;
	@FXML
	private TextArea detailsArea;
	@FXML
	private Label deadlineLabel;
	@FXML
	private ContextMenu listContextMenu;
	
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
		
		listContextMenu = new ContextMenu();
		MenuItem deleteMenuItem = new MenuItem();
		deleteMenuItem.setText("Delete");
		deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToDoItem tdi = toDoListView.getSelectionModel().getSelectedItem();
				deleteItem(tdi);
				
			}
		});
		
		listContextMenu.getItems().addAll(deleteMenuItem);
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
		
		toDoListView.setItems(ToDoData.getInstance().getItems());
		toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		toDoListView.getSelectionModel().selectFirst();
		
		toDoListView.setCellFactory(new Callback<ListView<ToDoItem>, ListCell<ToDoItem>>() {
			
			@Override
			public ListCell<ToDoItem> call(ListView<ToDoItem> param) {
				ListCell<ToDoItem> cell = new ListCell<ToDoItem>() {
					@Override
					protected void updateItem(ToDoItem item, boolean empty) {
						super.updateItem(item, empty);
						if(empty)
							setText(null);
						else {
							setText(item.getDescription());
							if(item.getDeadline().compareTo(LocalDate.now()) <= 0) {
								setTextFill(Color.RED);
							}
							else if(item.getDeadline().equals(LocalDate.now().plusDays(1)))
							{
								setTextFill(Color.ORANGERED);
							}
						}
					}
				};
				
				cell.emptyProperty().addListener(
						(obs, wasEmpty, isNowEmpty) -> {
							if(isNowEmpty)
								cell.setContextMenu(null);
							else
								cell.setContextMenu(listContextMenu);
						});
				
				return cell;
			}
		});
	}
	
	public void deleteItem(ToDoItem tdi) {
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText("Item: " + tdi.getDescription());
		a.setContentText("Are you sure you want to delete this item? OK to confirm, Cancel to return.");
		a.setTitle("Delete item");
		Optional<ButtonType> result = a.showAndWait();
		
		if(result.isPresent() && result.get().equals(ButtonType.OK)) {
			ToDoData.getInstance().deleteToDoItem(tdi);
		}
			
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
/*			toDoListView.getItems().setAll(ToDoData.getInstance().getItems());*/
			toDoListView.getSelectionModel().select(tdi);
	    }
/*		else if(result.isPresent() && result.get().equals(ButtonType.CANCEL))
			System.out.println("CANCEL");*/
	}
	
	@FXML
	public void handleKeyPressed(KeyEvent ke) {
		ToDoItem tdi = toDoListView.getSelectionModel().getSelectedItem();
		if(tdi != null)
			if(KeyCode.DELETE.equals(ke.getCode()))
				deleteItem(tdi);
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
