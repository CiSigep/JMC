package application;

import application.model.Contact;
import application.model.ContactData;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.control.TextFormatter;

public class ContactDialogController {
	
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextArea notesArea;
	
	//private TextFormatter<String> phoneFormatter;
	
	
	public void initialize() {
	}
	
	public Contact processNewResults() {
		return new Contact(firstNameField.getText(), lastNameField.getText(), phoneField.getText(), notesArea.getText());
	}
	
	public void setFields(Contact c) {
		firstNameField.setText(c.getFirstName());
		lastNameField.setText(c.getLastName());
		phoneField.setText(c.getPhoneNumber());
		notesArea.setText(c.getNotes());
	}
	
	public void processEditResults(Contact c) {
		c.setFirstName(firstNameField.getText());
		c.setLastName(lastNameField.getText());
		c.setPhoneNumber(phoneField.getText());
		c.setNotes(notesArea.getText());
	}
}
