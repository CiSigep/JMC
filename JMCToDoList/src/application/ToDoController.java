package application;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import application.model.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;;


public class ToDoController {
	
	@FXML
	private ListView<ToDoItem> toDoListView;
	@FXML
	private TextArea detailsArea;
	
	private List<ToDoItem> items;
	
	public void initialize() {
		ToDoItem tdi = new ToDoItem("Check Mail", "Go to the Mailbox and check the mail.", LocalDate.of(2018, Month.MAY, 1));
		ToDoItem tdi2 = new ToDoItem("Test App", "Write your test applications.", LocalDate.of(2018, Month.APRIL, 20));
		ToDoItem tdi3 = new ToDoItem("Doctor's Appointment", "Go to Doctor.", LocalDate.of(2018, Month.FEBRUARY, 7));
		
		items = new ArrayList<>();
		items.add(tdi);
		items.add(tdi2);
		items.add(tdi3);
		
		toDoListView.getItems().setAll(items);
		toDoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
	
	@FXML
	public void onClickListView(MouseEvent me) {
		ToDoItem item = toDoListView.getSelectionModel().getSelectedItem();
		StringBuilder sb = new StringBuilder(item.getDetails() + "\n\n\n\n");
		sb.append("Due: " + item.getDeadline().toString());
		
		detailsArea.setText(sb.toString());
	}
	
}
