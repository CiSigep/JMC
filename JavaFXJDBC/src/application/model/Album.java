package application.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Album {
	
	private SimpleLongProperty _id;
	private SimpleStringProperty name;
	private SimpleLongProperty artist;
	
	public Album() {
		this._id = new SimpleLongProperty();
		this.name = new SimpleStringProperty();
		this.artist = new SimpleLongProperty();
	}

	public Album(long _id, String name, long artist) {
		
		this._id = new SimpleLongProperty(_id);
		this.name = new SimpleStringProperty(name);
		this.artist = new SimpleLongProperty(artist);
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

	public long getArtist() {
		return artist.get();
	}

	public void setArtist(long artist) {
		this.artist.set(artist);
	}
		
	
}
