import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.*;

public class Extractors {
			
	public static List<String> pickJokes(String url) throws IOException {	
		
		String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";
		Document doc = Jsoup.connect(url).userAgent(ua)
				//.header("Accept-Language", "lt-LT")
				//.header("Accept-Encoding","gzip,deflate,sdch")
				.get();
		
		String textMarker = "post_content";
		List<String> jokes = textsExtractor (doc, textMarker);
	    return jokes;
	}
	
	public static List<String> textsExtractor (Document doc, String textMarker){
		// initialize the array of the texts/comments/jokes
		List<String> listOfTexts = new ArrayList<String>();
		Elements texts = doc.getElementsByClass(textMarker);
		for (Element text : texts) {
			System.out.println("JOKE: " + text.text());
			listOfTexts.add(text.text());
		}
		//System.out.println("Body:"+doc.body().html());
		return listOfTexts;		
	}
	
}