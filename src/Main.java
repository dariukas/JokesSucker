import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.script.ScriptException;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */

	// pickingJokes
	public static List<String> pickingJokes(String url1) throws IOException {

		List<String> urls = Anekdotai.urls(url1, 3);

		List<String> allJokes = new ArrayList<String>();
		List<String> jokes = new ArrayList<String>();

		for (String url : urls) {
			System.out.println(url);
			jokes = Extractors.pickJokes(url);
			allJokes = TextsManipulation.combineLists(allJokes, jokes);
		}
		stampingTheNumberOfJokes(allJokes);
		return allJokes;
	}

	public static void stampingTheNumberOfJokes(List<String> allJokes) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		GUI.writer(">>>>>Number of jokes for " + dateFormat.format(date)
				+ " is: " + allJokes.size());
	}

	public static void run() throws IOException {
		// Locale lithuanian = new Locale("lt_LT");
		String url1 = "http://cha.lt/anekdotai";

		GUI.writer("\n.............start..........................\n");
		GUI.writer(url1);

		// pickingJokes
		List<String> allJokes = pickingJokes(url1);

		// splitting jokes into the words
		List<String> jokesWords = TextsManipulation.textsIntoTheWords(allJokes);
		jokesWords = TextsManipulation.convertToLowerCase(jokesWords);

		//analyzing
		Map<String, Integer> map1 = Comparison.comparison(jokesWords,
				jokesWords);
		map1 = ResultsManipulation.sortMapByKeyDescending(map1);
		GUI.mapWriter(map1);
		
		GUI.writer("\n.......................................\n");

		System.out.println("finalizing...");
		for (Map.Entry<String, Integer> entry : map1.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		//putting to Excel
		ResultsToExcel.createExcelDoc(map1);

		/*
		 * Map<String, Integer> map2 = Comparison.inHowManyComments(title,
		 * jokes); GUI.mapWriter(map2); }
		 */

	}
	
	public static void runDelfi() throws IOException{
		
		String url = "http://www.delfi.lt/gyvenimas/meile/apie-moteris-kurioms-patinka-vienos-nakties-seksas.d?id=66267354";
		List<String> commentsLinks=Delfi.pickCommentsLinksInDelfi(url);
		List<String> comments=new ArrayList<String>();
		for (String commentsLink : commentsLinks){
			comments = TextsManipulation.combineLists(Delfi.pickCommentsInDelfi(commentsLink), comments);
		}
		
		// splitting jokes into the words
		List<String> commentsWords = TextsManipulation.textsIntoTheWords(comments);
		commentsWords = TextsManipulation.convertToLowerCase(commentsWords);
		Map<String, Integer> map = Comparison.comparison(commentsWords,
				commentsWords);
		map = ResultsManipulation.sortByValues(map);
		ResultsToExcel.createExcelDoc(map);
		
	}
	
	public static void runForTests() throws IOException{
	
		//Anekdotai.justPickJokesOfCha();
		String url = "http://www.delfi.lt/gyvenimas/meile/vaikinas-nevilty-kaip-susigrazinti-mergina-kai-ji-tave-pasiuncia.d?id=66873076";
		HuntingLinksDelfi.pickingLinks(url);
	
	}
	
	public static void main(String[] args) throws IOException {
		// run();
		//runDelfi();
		runForTests();
	}
}
