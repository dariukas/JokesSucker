import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 
 */

/**
 * @author kristinaslekyte
 *
 */
public class Anekdotai {
	
	//collect the urls of different pages
	//this method just add the pages to the primary link
	//@numberOfPages - it is the total number of pages of the website
	public static List<String> urls (String url, Integer numberOfPages) throws IOException{
		List<String> urls = new ArrayList<String>();
		String elementas = "";		
		
		for(int i=1; i<numberOfPages; i++) {
			elementas = url+"/"+i;
			urls.add(elementas);
		}	
	    System.out.println(urls);
		return urls;
	}
	
	//get all jokes from cha.lt/anekdotai
	//the number of pages is got programically
	public static List<String> urlsOfCha(String url) throws IOException {
		List<String> urls = new ArrayList<String>();
		String elementas = "";
		String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";
		Document doc = Jsoup.connect(url).userAgent(ua).get();
		try {
			Element pageElement = doc.select(".page-numbers").last();
			int numberOfPages = Integer.valueOf(pageElement.text());
			for (int i = 1; i < numberOfPages; i++) {
				elementas = url + "/" + i;
				urls.add(elementas);
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("It could be the wrong name of the class");
		}
		System.out.println(urls);
		return urls;
	}
	
	//picking the jokes without getting the number of urls
	public static List<String> justPickJokesOfCha(String url, String nextPageMarker, String jokesMarker) throws IOException {
		
		url = "http://cha.lt/anekdotai";
		nextPageMarker = ".page_next";
		jokesMarker = "post_content";
		url = "www.juokeliai.lt";
		nextPageMarker = ".permalink";
		jokesMarker = "post_content";
		//lietuviski anekdotai
		
		
		List<String> jokes = new ArrayList<String>();
		
		String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";		
		try {
			while (true) {
				Document doc = Jsoup.connect(url).userAgent(ua).timeout(10000)
						.get();
				jokes = TextsManipulation.combineLists(jokes,
						Extractors.textsExtractor(doc, jokesMarker));
				// jokes = Extractors.textsExtractor (doc, "post_content");
				url = doc.select(nextPageMarker).first().attr("href");
			}
		} catch (Exception e) {
			System.out.println("It already reached the last page!");
			//e.printStackTrace();			
		}
		
		//the first joke
        System.out.println(jokes.get(0));
        //the last joke
        System.out.println(jokes.get(jokes.size()-1));
		return jokes;
	}
}
