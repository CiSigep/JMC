package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
	
	public static final String TABLE = "contacts";
	
	public static final String NAME_COLUMN = "name";
	public static final String PHONE_COLUMN = "phone";
	public static final String EMAIL_COLUMN = "email";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Properties prop = new Properties();
		prop.load(new FileInputStream("Properties.properties"));
	
		try(Connection con = DriverManager.getConnection(prop.getProperty("connection_string"), prop.getProperty("user"), prop.getProperty("pass"));
			Statement stmt = con.createStatement()){
			//con.setAutoCommit(false);
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM user_tables where table_name = 'CONTACTS'");
			
			int i = 0;
			
			if(rs.next())
				i = rs.getInt(1);
				
			if(i < 1)
				stmt.execute("CREATE TABLE " + TABLE
						   + "(" + NAME_COLUMN + " VARCHAR(30), "
						   + PHONE_COLUMN + " NUMBER,"
						   + EMAIL_COLUMN + " VARCHAR(30))");
			
			/*stmt.execute("INSERT INTO contacts(name, phone, email) VALUES ('Tommy Tims', 4556789, 'tom@email.com')");
			stmt.execute("INSERT INTO contacts(name, phone, email) VALUES ('Jimmy Tims', 4546789, 'jim@email.com')");
			stmt.execute("INSERT INTO contacts(name, phone, email) VALUES ('Bonny Bins', 4536789, 'bon@email.com')");*/
			
			//stmt.execute("UPDATE contacts SET phone = 4658243 WHERE name = 'Bonny Bins'");
			//stmt.execute("DELETE FROM contacts WHERE name = 'Jimmy Tims'");
			
			rs = stmt.executeQuery("SELECT * FROM " + TABLE);
			
			while(rs.next()) {
				System.out.println("NAME: " + rs.getString(NAME_COLUMN) + " PHONE: " + rs.getInt(PHONE_COLUMN) + " EMAIL: " + rs.getString(EMAIL_COLUMN));
			}
			
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

}
