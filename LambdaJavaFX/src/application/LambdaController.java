package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

// Using Lambdas to define the action response to a button. 
public class LambdaController {
	
	@FXML
	private Button clickMeButton;
	
	public void initialize() {
		clickMeButton.setOnAction((ActionEvent ae) -> {System.out.println("I've been clicked!");});
	}

}
