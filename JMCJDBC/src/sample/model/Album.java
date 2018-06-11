package sample.model;

public class Album {
	
	private long _id;
	private String name;
	private long artist;
	
	public Album() {}

	public Album(long _id, String name, long artist) {
		super();
		this._id = _id;
		this.name = name;
		this.artist = artist;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getArtist() {
		return artist;
	}

	public void setArtist(long artist) {
		this.artist = artist;
	}
		
	
}
