package sample.model;

public class Song {
	
	private long _id;
	private long track;
	private String name;
	private long album;
	
	public Song() {}
	
	public Song(long _id, long track, String name, long album) {
		super();
		this._id = _id;
		this.track = track;
		this.name = name;
		this.album = album;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getTrack() {
		return track;
	}

	public void setTrack(long track) {
		this.track = track;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAlbum() {
		return album;
	}

	public void setAlbum(long album) {
		this.album = album;
	}
	
	
	

}
