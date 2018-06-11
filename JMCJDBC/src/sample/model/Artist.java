package sample.model;

public class Artist {
	
	private long _id;
	private String name;
	
	public Artist() {}

	public Artist(long _id, String name) {
		this._id = _id;
		this.name = name;
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
	
	
	

}
