import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Delfi {
	

public static List<String> pickCommentsInDelfi(String url) throws IOException {
	List<String> comments=new ArrayList<String>();
	System.out.println("picking...");
	String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";
	Document doc = Jsoup.connect(url).userAgent(ua).get();
	Elements commentElements = doc.getElementsByClass("comment-body");
	String comment = null;
	for (Element commentElement : commentElements){	
	comment = commentElement.text();
	comments.add(comment);
	}
	System.out.println(comments);
	return comments;
	
}

	public static Map<String, String> pickCommentsWithIP(String url) throws IOException {

		String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";
		Document doc = Jsoup.connect(url).userAgent(ua).get();

		String comment = null;
		String commentIP = null;
		Elements commentElements = doc.getElementsByClass("comment-body");
		Elements commentIPElements = doc.getElementsByClass("comm-date");
		List<String> comments = new ArrayList<String>();
		List<String> commentsIP = new ArrayList<String>();

		for (Element commentIPElement : commentIPElements) {
			commentIP = commentIPElement.text().substring(21);
			commentsIP.add(commentIP);
		}

		for (Element commentElement : commentElements) {
			comment = commentElement.text();
			comments.add(comment);
		}

		Map<String, String> commentsWithIP = TextsManipulation
				.combineListsToMap(commentsIP, comments);
		return commentsWithIP;
	}

	
public static List<String> pickCommentsLinksInDelfi0(String url) throws IOException {
		
		List<String> commentsLinks=new ArrayList<String>();
		
	    String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";
		Document doc = Jsoup.connect(url).userAgent(ua).get();
		//pirmas linkas su straipsnio komentarais
		Element commentsLinkElement = doc.getElementsByClass("commentCount").first();
		String commentsLink = commentsLinkElement.attr("href");
		doc = Jsoup.connect(commentsLink).userAgent(ua).get();
		//Elements commentsLinkElement1 = doc.getElementsByAttribute("data-mode");
		String commentsLinkBase = doc.select("a[data-mode=1]").attr("href");
		
		//jeigu turi
		//Elements commentsLinkElement0 = doc.getElementsByClass("comment-list-paging-link");
		
		try {
		do{
			commentsLinks.add(commentsLinkBase);
			System.out.println(commentsLinkBase);
			doc = Jsoup.connect(commentsLinkBase).userAgent(ua).get();	
			commentsLinkBase = doc.select("a:containsOwn([kitas])").attr("href");	
		}
		while(doc.getElementsContainingOwnText("[kitas]") != null);
		} catch (Exception e){
			System.out.println("Visi linkai jau surinkti!");
		}
		
		return commentsLinks;		
	}
	
	
	public static List<String> pickCommentsLinksInDelfi(String url) throws IOException{
		List<String> commentsLinks=new ArrayList<String>();

	    String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";
		Document doc = Jsoup.connect(url).userAgent(ua).get();
		Element commentsNumberElement = doc.getElementsByClass("commentCount").first();
		String commentsNumberString = commentsNumberElement.text();
		int commentsNumber = Integer.parseInt(commentsNumberString.substring(1, commentsNumberString.length()-1));
		int pageNumber = commentsNumber/20;
		String urlOfComments0 = url + "&com=1&reg=0";
		commentsLinks.add(urlOfComments0);
		String urlOfComments = urlOfComments0 + "&s=2&no=";
		String linkOfComments = null;
		for (int i=1; i<=pageNumber; i++){
			linkOfComments = urlOfComments + i*20;
			commentsLinks.add(linkOfComments);
		}
		System.out.println(commentsLinks);
		
		return commentsLinks;	
	}
	
}
