package application.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Artist {
	
	private SimpleLongProperty _id;
	private SimpleStringProperty name;
	
	public Artist() {
		this._id = new SimpleLongProperty();
		this.name = new SimpleStringProperty();
	}

	public Artist(long _id, String name) {
		
		this._id = new SimpleLongProperty(_id);
		this.name = new SimpleStringProperty(name);
	}

	public long get_id() {
		return _id.get();
	}

	public void set_id(long _id) {
		this._id.set(_id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	
	

}
