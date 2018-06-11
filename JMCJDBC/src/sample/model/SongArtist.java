package sample.model;

public class SongArtist {
	
	private String albumName;
	private String artistName;
	private long track;
	
	public SongArtist() {}
	
	public SongArtist(String artistName, String albumName, long track) {
		super();
		this.albumName = albumName;
		this.artistName = artistName;
		this.track = track;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public long getTrack() {
		return track;
	}

	public void setTrack(long track) {
		this.track = track;
	}
	
	

}
