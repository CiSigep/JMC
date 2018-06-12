package sample;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import sample.model.Artist;
import sample.model.DataSource;
import sample.model.SongArtist;

public class Music {

	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("Properties.properties"));

		DataSource ds = new DataSource(prop);
		
		if(!ds.open()) {
			System.err.println("Failed to connect.");
			return;
		}
		
		List<Artist> artists = ds.getAllArtists(DataSource.ORDER_BY_DESC);
		
		if(artists == null) {
			System.err.println("No Artist Data.");
			return;
		}
		
		for(Artist a : artists)
			System.out.println("ID: " + a.get_id() + " NAME: " + a.getName());
		
		List<String> albums = ds.getAlbumNamesForArtist("Pink Floyd", DataSource.ORDER_BY_DESC);
		
		if(albums == null) {
			System.err.println("No Album Data.");
			return;
		}
			
		
		for(String ab: albums)
			System.out.println(ab);
		
		
		List<SongArtist> sa = ds.getArtistAndAlbumForSong("Wish You Were Here", DataSource.ORDER_BY_DESC);
		
		if(sa == null) {
			System.err.println("No Song Artist Data.");
			return;
		}
		
		for(SongArtist s : sa)
			System.out.println("Artist: " + s.getArtistName() + " Album: " + s.getAlbumName() + " Track: " + s.getTrack());
		
		ds.queryMetadata("songs");
		
		System.out.println("Record Count: " + ds.getRecordCount("songs"));
		
		List<SongArtist> sas = ds.queryArtistListView("Go Your Own Way");
		
		if(sas == null) {
			System.err.println("No Song Artist Data.");
			return;
		}
		
		for(SongArtist s : sas)
			System.out.println("Artist: " + s.getArtistName() + " Album: " + s.getAlbumName() + " Track: " + s.getTrack());
		
		
		ds.insertSong("Touch of Grey", "Grateful Dead", "In The Dark", 1);
		ds.close();
	}

}
