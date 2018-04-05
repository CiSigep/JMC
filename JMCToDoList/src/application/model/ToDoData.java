package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ToDoData {
	
	private static ToDoData instance = new ToDoData();
	
	private ObservableList<ToDoItem> items;
	private DateTimeFormatter dtf;
	private String filename = "todo.txt";
	
	private ToDoData() {
		dtf = DateTimeFormatter.ofPattern("MMMM d, yyyy");
	}

	public static ToDoData getInstance() { return instance; }

	public ObservableList<ToDoItem> getItems() {
		return items;
	}

/*	public void setItems(List<ToDoItem> items) {
		this.items = items;
	}*/
	
	public void loadToDoItems() throws IOException {
		items = FXCollections.observableArrayList();
		Path p = Paths.get(filename);
		
		BufferedReader br = Files.newBufferedReader(p);
		
		String input;
		
		try {
			while((input = br.readLine()) != null) {
				String[] itemPieces = input.split("\t");
				
				items.add(new ToDoItem(itemPieces[0], itemPieces[1], LocalDate.parse(itemPieces[2], dtf)));
			}
		} finally {
			if(br != null)
				br.close();
		}
	}
	
	public void saveToDoItems() throws IOException {
		Path p = Paths.get(filename);
		BufferedWriter bw = Files.newBufferedWriter(p);
		
		try {
			for(ToDoItem tdi : items) {
				bw.write(tdi.getDescription() + "\t" + tdi.getDetails() + "\t" + dtf.format(tdi.getDeadline()));
				bw.newLine();
			}
		} finally {
			if(bw != null)
				bw.close();
		}
	}
	
	public void addToDoItem(ToDoItem tdi) {
		items.add(tdi);
	}

	public void deleteToDoItem(ToDoItem tdi) {
		items.remove(tdi);
	}
	
}
