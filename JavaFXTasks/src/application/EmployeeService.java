package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmployeeService extends Service<ObservableList<String>> {

	@Override
	protected Task<ObservableList<String>> createTask() {
		// TODO Auto-generated method stub
		return new Task<ObservableList<String>>() {

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
					
				return employees;
			}};
	}

}
