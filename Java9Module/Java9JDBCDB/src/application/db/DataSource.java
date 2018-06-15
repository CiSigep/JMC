package application.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import application.model.Album;
import application.model.Artist;

public class DataSource {
	
	private final String connectionString;
	private final String user;
	private final String pass;
	
	private Connection conn; 
	
	public static final String TABLE_ALBUMS = "albums";
	public static final String COLUMN_ALBUM_ID = "id";
	public static final String COLUMN_ALBUM_NAME = "name";
	public static final String COLUMN_ALBUM_ARTIST = "artist";
	public static final int INDEX_ALBUM_ID = 1;
	public static final int INDEX_ALBUM_NAME = 3;
	public static final int INDEX_ALBUM_ARTIST = 2;
	
	public static final String TABLE_ARTISTS = "artists";
	public static final String COLUMN_ARTIST_ID = "id";
	public static final String COLUMN_ARTIST_NAME = "name";
	public static final int INDEX_ARTIST_ID = 1;
	public static final int INDEX_ARTIST_NAME = 2;
	
	public static final String TABLE_SONGS = "songs";
	public static final String COLUMN_SONG_ID = "id";
	public static final String COLUMN_SONG_TRACK = "track";
	public static final String COLUMN_SONG_TITLE = "title";
	public static final String COLUMN_SONG_ALBUM = "album";
	public static final int INDEX_SONG_ID = 1;
	public static final int INDEX_SONG_TRACK = 2;
	public static final int INDEX_SONG_TITLE = 3;
	public static final int INDEX_SONG_ARTIST = 4;
	
	public static final int ORDER_BY_NONE = 0;
	public static final int ORDER_BY_ASC = 1;
	public static final int ORDER_BY_DESC = 2;
	
	public static final String VIEW_SONG_ARTIST = "artist_list";
	
	private static final String ALBUM_QUERY_START = "SELECT " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME +" FROM " + TABLE_ALBUMS
												  + " INNER JOIN " + TABLE_ARTISTS + " ON "
												  + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID
												  + " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " = '";
	
	private static final String SONG_QUERY_START = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME
			                                     + ", " + TABLE_SONGS + "." + COLUMN_SONG_TRACK + " FROM " + TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS 
			                                     + " ON " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID
			                                     + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = "
			                                     + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID + " WHERE " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = '";
	
	// SQL injection can happen with most of the statements made
	/*private static final String QUERY_VIEW = "SELECT " + COLUMN_ARTIST_NAME + ", " + COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK
			                               + " FROM " + VIEW_SONG_ARTIST + " WHERE " + COLUMN_SONG_TITLE + " = '";*/
	
	private static final String QUERY_VIEW_PREP = "SELECT " + COLUMN_ARTIST_NAME + ", " + COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK
            + " FROM " + VIEW_SONG_ARTIST + " WHERE " + COLUMN_SONG_TITLE + " = ?";
	
	private static final String INSERT_ARTIST = "INSERT INTO "+ TABLE_ARTISTS + "(" + COLUMN_ARTIST_NAME + ") VALUES (?)";
	private static final String INSERT_ALBUM = "INSERT INTO " + TABLE_ALBUMS + "(" + COLUMN_ALBUM_NAME  + ", "+ COLUMN_ALBUM_ARTIST + ") VALUES (?, ?)";
	private static final String INSERT_SONG = "INSERT INTO " + TABLE_SONGS + "(" + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM + ") VALUES(?, ?, ?)" ;
	
	private static final String QUERY_ARTIST_ID = "SELECT " + COLUMN_ARTIST_ID + " FROM " + TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";
	private static final String QUERY_ALBUM_ID = "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";
	
	private static final String QUERY_ALBUM_BY_ARTIST_ID = "SELECT * FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_ARTIST + " = ?"
			                                             + " ORDER BY " + COLUMN_ALBUM_ARTIST;
	
	private static final String UPDATE_ARTIST_NAME = "UPDATE " + TABLE_ARTISTS + " SET " + COLUMN_ARTIST_NAME + " = ? WHERE " + COLUMN_ARTIST_ID + " = ?";  
	
	private static String[] keyGen = { "id" }; 
	
	public static DataSource getInstance() {
		return instance;
	}
	
	private static DataSource instance;
	
	static { 
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("Properties.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		instance = new DataSource(prop);
	}
	
	
	
	private DataSource(Properties prop) {
		connectionString = prop.getProperty("connection_string");
		user = prop.getProperty("user");
		pass = prop.getProperty("pass");
	}

	public boolean open() {
		try {
			conn = DriverManager.getConnection(connectionString, user, pass);
			return true;
		} catch (Exception e) {
			System.err.println("Exception opening SQL connection");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public void close() {
		try {
			if(conn != null)
				conn.close();
		} catch (Exception e) {
			System.err.println("Exception in closing SQL connection");
			System.err.println(e.getMessage());
		}
		
	}
	
	public List<Artist> getAllArtists(int orderBy) throws IOException{
		//Statement stmt = null;
		//ResultSet rs = null;
		StringBuilder sb = new StringBuilder("SELECT * FROM "+ TABLE_ARTISTS);
		if(orderBy != ORDER_BY_NONE) 
			sb.append(orderBySingle(COLUMN_ARTIST_NAME, orderBy));
		
		try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sb.toString())) {
/*		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM "+ TABLE_ARTISTS);*/
			
			List<Artist> artists = new ArrayList<>();
			
			while(rs.next()) {
				artists.add(new Artist(rs.getLong(INDEX_ARTIST_ID), rs.getString(INDEX_ARTIST_NAME)));
				try {
					Thread.sleep(20); // Artificial slowdown to show progressBar
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return artists;
			
		} catch (SQLException se) {
			System.err.println("Query failed: " + se.getMessage());

			System.err.println("Query: " + sb.toString());
			return null;
	    } /* finally {
			try {
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				System.err.println("Failed to close ResultSet: " + e.getMessage());
			}
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException se) {
				System.err.println("Failed to close Statement: " + se.getMessage());
			}
		}*/
		
		
	}
	
	public List<String> getAlbumNamesForArtist(String artistName, int orderBy){
		StringBuilder sb = new StringBuilder(ALBUM_QUERY_START + artistName + "'");
		if(orderBy != ORDER_BY_NONE) 
			sb.append(orderBySingle(TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME, orderBy));
		
		try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sb.toString())) {
			
			List<String> albumNames = new ArrayList<>();
			
			while(rs.next())
				albumNames.add(rs.getString(1));
			
			return albumNames;
		} catch (SQLException se) {
			System.err.println("Query failed: " + se.getMessage());
			
			System.err.println("Query: " + sb.toString());
			return null;
	    } 
	}
	
	public void queryMetadata(String table) {
		String sql = "SELECT * FROM " + table;
		
		try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumns = rsmd.getColumnCount();
			for(int i = 1; i <= numColumns; i++) {
				System.out.format("Column %d contains data for %s\n", i, rsmd.getColumnName(i));
			}
			
		} catch (SQLException e) {
			System.err.println("Query failed: " + e.getMessage());
			System.err.println("Query: " + sql);
		}
		
	}
	
	public int getRecordCount(String table) {
		String sql = "SELECT COUNT(*) FROM " + table;
		
		try(Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)){
			
			if(rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
			return -1;
		} catch (SQLException e) {
			System.err.println("Query failed: " + e.getMessage());
			System.err.println("Query: " + sql);
			return -1;
		}
	}
	
/*	private int insertArtist(String name) throws SQLException {
		
		PreparedStatement query = conn.prepareStatement(QUERY_ARTIST_ID);
		query.setString(1, name);
		ResultSet rs = query.executeQuery();
		
		if(rs.next()) {
			int ret = rs.getInt(1);
			query.close();
			rs.close();
			return ret;
		}
		else {
			PreparedStatement insert = conn.prepareStatement(INSERT_ARTIST, keyGen);
			insert.setString(1, name);
			int affectedRows = insert.executeUpdate();
			if(affectedRows != 1)
				throw new SQLException("Artist insert failed");
			
			ResultSet genKey = insert.getGeneratedKeys();
			if(genKey.next()) {
				int key = genKey.getInt(1);
				insert.close();
				genKey.close();
				return key;
			}
			
			throw new SQLException("Artist Key gen failed.");
		}
	}
	
	private int insertAlbum(String name, int artistId) throws SQLException {
		PreparedStatement query = conn.prepareStatement(QUERY_ALBUM_ID);
		query.setString(1, name);
		ResultSet rs = query.executeQuery();
		if(rs.next()) {
			int ret = rs.getInt(1);
			query.close();
			rs.close();
			return ret;
		}
		else {
			PreparedStatement insert = conn.prepareStatement(INSERT_ALBUM, keyGen);
			insert.setString(1, name);
			insert.setLong(2, artistId);
			int affectedRows = insert.executeUpdate();
			if(affectedRows != 1)
				throw new SQLException("Album insert failed");
			
			ResultSet genKey = insert.getGeneratedKeys();
			if(genKey.next()) {
				int key = genKey.getInt(1);
				insert.close();
				genKey.close();
				return key;
			}
			
			throw new SQLException("Album Key gen failed.");
		}
	}*/
	
	public List<Album> getAlbumsForArtist(long id) {
		PreparedStatement query = null;
		ResultSet rs = null;
		
		try{
			query = conn.prepareStatement(QUERY_ALBUM_BY_ARTIST_ID);
			query.setLong(1, id);
			
			rs = query.executeQuery();
			List<Album> albums = new ArrayList<>();
			while(rs.next()) {
				albums.add(new Album(rs.getLong(INDEX_ALBUM_ID), rs.getString(INDEX_ALBUM_NAME), rs.getLong(INDEX_ALBUM_ARTIST)));
			}
			return albums;
		} catch (SQLException e) {
			System.err.println("Query failed: " + QUERY_ALBUM_BY_ARTIST_ID);
			return null;
		} finally {
			if(query != null)
				try {
					query.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	
	public boolean updateArtistName(String name, long artistId) {
		PreparedStatement update = null;
		try {
			update = conn.prepareStatement(UPDATE_ARTIST_NAME);
			update.setString(1, name);
			update.setLong(2, artistId);
			
			int affected = update.executeUpdate();
			
			return affected == 1;
		} catch (SQLException se) {
			System.err.println("Query failed: " + UPDATE_ARTIST_NAME);
			return false;
		} finally {
			if (update != null)
				try {
					update.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	private String orderBySingle(String columnName, int orderBy) {
		StringBuilder sb = new StringBuilder(" ORDER BY " + columnName);
		if(orderBy == ORDER_BY_ASC)
			sb.append(" ASC");
		else if (orderBy == ORDER_BY_DESC)
			sb.append(" DESC");
		
		return sb.toString();
	}
	
	
}
