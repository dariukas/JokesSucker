import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HuntingLinksDelfi {
	
	//To calculate connections
	static int i = 0;
	//how deep to navigate
	static int deep = 4;
	
	public static Map<String, String> pickingLinks(String url) throws IOException {
		System.out.println("Picking links...");
		Map<String, String> map = new HashMap<String, String>();
		Set<String> linkList = new LinkedHashSet<String>();
		linkList.add(url);
		for (int i=0; i<deep; i++){
			linkList = huntingLinks(linkList, map);
		}
		//links to the file but need the filepath correction
		//GUI.listWriter(links);
		System.out.println("After "+i+" connections "+map.size()+" links are picked.");
		System.out.println(map);
		return map;
	}
	
	public static Set<String> huntingLinks(Set<String> list, Map<String, String> map) throws IOException {
		Set<String> list2 = new LinkedHashSet<String>();
		for (String url : list){
			i++;
			Document doc = null;
			if(filterURL(url)){
			doc = connecting(url);
			}
			else{
				continue;
			}
			//map cannot contain duplicate values
			addToMap(map, doc);
			String clName = "artres-related-pitem-link";
			Elements linksElements = doc.getElementsByClass(clName);
			for (Element linkElement : linksElements){
				list2.add(linkElement.getElementsByTag("a").attr("abs:href"));
			}
		}
		return list2;
	}
	
	public static boolean addToMap(Map<String, String> map, Document doc) {
		String url = doc.location();
		String title = doc.title();
		String date = doc.getElementsByClass("delfi-source-date").get(0).text();
		if(filterDate(date)){
			map.put(title+"("+date+")", url);
		}		
		return true;
	}
	
	public static boolean filterURL (String s){
		if (s.contains("gyvenimas/meile")){
		return true;
		}
		return false;
	}
	
	public static boolean filterDate (String s){
		if (s.startsWith("2015 m. sausio")){
		return true;
		}
		return false;
	}
	
	public static Document connecting (String url) throws IOException {
		String ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.122 Safari/534.30";
		Document doc = Jsoup.connect(url).userAgent(ua).timeout(3000).get();
		System.out.println("Connecting to: "+doc.location());
		return doc;		
	}

	public static Map<String, String> mapFromFileBySeparator (String filepath, String separator) throws IOException {
		//use . to get current directory
		File dir = new File(".");
		File fin = new File(dir.getCanonicalPath() + File.separator + filepath);
		FileInputStream fis = new FileInputStream(fin);
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line = null;
		Map<String, String> map = new HashMap<String, String>();
		while ((line = br.readLine()) != null) {
			String[] ar=line.split(separator);
			if (ar.length==2) {
			map.put(ar[0], ar[1]);
			}
		}
		br.close();
		System.out.println("The content of the file \""+filepath+"\" is in the map.");
		return map;
	}
}
