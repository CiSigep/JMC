package application;

import java.util.Optional;

import application.model.Contact;
import application.model.ContactData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class Controller {
	
	@FXML
	private BorderPane rootPane;
	
	@FXML
	private TableView<Contact> contactsTableView;
	
	TableColumn<Contact, String> firstNameColumn;
	TableColumn<Contact, String> lastNameColumn;
	TableColumn<Contact, String> phoneNumberColumn;
	TableColumn<Contact, String> notesColumn;
	
	private ContactData data = new ContactData();
	
	public void initialize() {
		data.loadContacts();
		
	    firstNameColumn = new TableColumn<>();
		firstNameColumn.setText("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		lastNameColumn = new TableColumn<>();
		lastNameColumn.setText("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		phoneNumberColumn = new TableColumn<>();
		phoneNumberColumn.setText("Phone Number");
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		notesColumn = new TableColumn<>();
		notesColumn.setText("Notes");
		notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
		
		contactsTableView.getColumns().addAll(firstNameColumn, lastNameColumn, phoneNumberColumn, notesColumn);
		contactsTableView.setItems(data.getItems());
		contactsTableView.getSelectionModel().selectFirst();
	}
	
	@FXML
	public void addNewContact(ActionEvent ae) {
		Dialog<ButtonType> d = new Dialog<>();
		d.initOwner(rootPane.getScene().getWindow());
		FXMLLoader fl = new FXMLLoader();
		fl.setLocation(getClass().getResource("ContactDialog.fxml"));
		try {
			d.getDialogPane().setContent(fl.load());
			d.setTitle("New Contact");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		d.getDialogPane().getButtonTypes().add(ButtonType.OK);
		d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		

		Optional<ButtonType> result = d.showAndWait();
		if(result.isPresent() && result.get().equals(ButtonType.OK)) {
			ContactDialogController cdc = fl.getController();
			Contact c = cdc.processNewResults();
			data.addContact(c);
			contactsTableView.getSelectionModel().select(c);
			data.saveContacts();
		}
	}
	
	@FXML
	public void editExistingContact(ActionEvent ae) {
		Contact c = contactsTableView.getSelectionModel().getSelectedItem();
		Dialog<ButtonType> d = new Dialog<>();
		d.initOwner(rootPane.getScene().getWindow());
		FXMLLoader fl = new FXMLLoader();
		fl.setLocation(getClass().getResource("ContactDialog.fxml"));
		try {
			d.getDialogPane().setContent(fl.load());
			d.setTitle("Edit Contact");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		ContactDialogController cdc = fl.getController();
		cdc.setFields(c);
		
		d.getDialogPane().getButtonTypes().add(ButtonType.OK);
		d.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		Optional<ButtonType> result = d.showAndWait();
		if(result.isPresent() && result.get().equals(ButtonType.OK)) {
			cdc.processEditResults(c);
			contactsTableView.getSelectionModel().select(c);
			data.saveContacts();
		}
	}
	
	@FXML
	public void deleteContact(ActionEvent ae) {
		Contact c = contactsTableView.getSelectionModel().getSelectedItem();
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setHeaderText("Contact: " + c.getFirstName() + " " + c.getLastName());
		a.setContentText("Are you sure you want to delete this Contact? OK to confirm, Cancel to return.");
		a.setTitle("Delete Contact");
		Optional<ButtonType> result = a.showAndWait();
		
		if(result.isPresent() && result.get().equals(ButtonType.OK)) {
			data.removeContact(c);
		
			contactsTableView.getSelectionModel().selectFirst();
			data.saveContacts();
		}
	}
	
	@FXML
	public void onExitAction(ActionEvent ae) {
		Platform.exit();
	}
}
