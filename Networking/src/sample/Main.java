package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
/*import java.net.URI;
import java.net.URISyntaxException;*/
import java.net.URL;
//import java.net.URLConnection;
/*import java.util.List;
import java.util.Map;
*/
public class Main {

	public static void main(String[] args) {
		try {
			//URI uri = new URI("http://username:password@myserver.com:5000/catalogue/phones?os=android#samsung"); // Absolute
			/*URI uri1 = new URI("/catalogue/phones?os=android#samsung"); // Relative
			URI uri2 = new URI("/catalogue/tvs?manufacturer=samsung");
			URI uri3 = new URI("/stores/locations?zip=12345");
			
			
			URI baseUri = new URI("http://username:password@myserver.com:5000");
			URI resUri1 = baseUri.resolve(uri1);
			URI resUri2 = baseUri.resolve(uri2);
			URI resUri3 = baseUri.resolve(uri3);
			
			URL url1 = resUri1.toURL();
			System.out.println("URL1: " + url1);
			URL url2 = resUri2.toURL();
			System.out.println("URL2: " + url2);
			URL url3 = resUri3.toURL();
			System.out.println("URL3: " + url3);
			
			URI relUri2 = baseUri.relativize(resUri2);
			System.out.println("Relativized URI2: " + relUri2);*/
			
			
			//URL url = new URL("http://example.org");
			URL url = new URL("https://api.flickr.com/services/feeds/photos_public.gne?tags=cats");
			HttpURLConnection hurlc = (HttpURLConnection) url.openConnection();
			hurlc.setRequestMethod("GET");
			hurlc.setRequestProperty("User-Agent", "Chrome");
			hurlc.setReadTimeout(30000);
			
			int responseCode = hurlc.getResponseCode();
			System.out.println("Response code: " + responseCode);
			
			if(responseCode != 200) {
				System.err.println("Error in reading webpage.");
				System.out.println(hurlc.getResponseMessage());
				return;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(hurlc.getInputStream()));
			
			String line = "";
			while((line = br.readLine()) != null) {				
				System.out.println(line);
			}
			br.close();
			
			/*
			URLConnection urlc = url.openConnection();
			urlc.setDoOutput(true);
			urlc.connect();
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			
			
			Map<String, List<String>> headerfields = urlc.getHeaderFields();
			for(Map.Entry<String, List<String>> entry: headerfields.entrySet()) {
				System.out.println("------------ Key: " + entry.getKey());
				for(String s : entry.getValue())
					System.out.println("Value: " + s);
			}*/
			/*String line = "";
			while(line != null) {
				line = br.readLine();
				
				System.out.println(line);
			}
			br.close();*/
			//URI uri = url.toURI();
			
			/*System.out.println("Scheme: " + uri.getScheme());
			System.out.println("Scheme-specific part: " + uri.getSchemeSpecificPart());
			System.out.println("Authority: " + uri.getAuthority());
			System.out.println("User info: " + uri.getUserInfo());
			System.out.println("Host: " + uri.getHost());
			System.out.println("Port: " + uri.getPort());
			System.out.println("Path: " + uri.getPath());
			System.out.println("Query: " + uri.getQuery());
			System.out.println("Fragment: " + uri.getFragment());*/
			
		} /*catch (URISyntaxException use) {
			System.err.println(use.getMessage());
		}*/ catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

}
