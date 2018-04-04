package application;

import application.model.ToDoData;
import application.model.ToDoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewDialogController {
	
	@FXML
	private TextField description;
	@FXML
	private TextArea details;
	@FXML
	private DatePicker deadline;
	
	public ToDoItem processResults() {
		ToDoItem tdi = new ToDoItem(description.getText(), details.getText(), deadline.getValue());
		ToDoData.getInstance().addToDoItem(tdi);
		return tdi;
	}

}
