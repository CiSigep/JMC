package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ApacheTest {

	public static void main(String[] args) {
		CloseableHttpClient chc = HttpClients.createDefault();
		
		HttpGet hg = new HttpGet("Http://example.org");
		hg.addHeader("User-Agent", "Chrome");
		
		CloseableHttpResponse chr = null;
		
		try {
			chr = chc.execute(hg);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(chr.getEntity().getContent()));
			
			String line = "";
			while((line = br.readLine()) != null) {				
				System.out.println(line);
			}
			br.close();
		} catch(IOException ie) {
			System.err.println(ie.getMessage());
		} finally {
			try {
				chr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
