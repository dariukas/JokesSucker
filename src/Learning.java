import java.util.ArrayList;
import java.util.List;

public class Learning {


	// split by special letters
	public static void splitTheWord() {

		// http://unicode-table.com/en/#latin-1-supplement
		System.out.println("Kažkoks simbolis: "+"\u016B");
		
		String word = "turbūt";

		char c = word.charAt(4);
		System.out.println("Simbolis šioje pozicijoje: "+c);
		String iUTF8 = Integer.toHexString(c);
		System.out.println("Šio simbolio vieta UNICODE/UTF-8 sistemoje: "+String.valueOf(iUTF8));		
		int iHTML = (int)c;
		System.out.println("Šio simbolio vieta HTML sistemoje: "+String.valueOf(iHTML));
		
		String[] words = word.split("[\u016B]");
		System.out.println("Paskutinė žodžio dalis po suskaldymo: "+words[1]);
		
	}
	
	// split by special letters
	public static void splitTwoWords() {
		
		String a = "turbūt bus mūza";
		//http://stackoverflow.com/questions/1611979/remove-all-non-word-characters-from-a-string-in-java-leaving-accented-charact
		String[] b = a.split("[^\\p{L}\\p{Nd}]+");
		List<String> textWords = new ArrayList<String>();
		for (String textWord : b) {
			textWords.add(textWord.trim());
	        System.out.println(textWord);
		}
		
	}

	public static void a(){
		/*		InputStream input = new URL(url).openStream();
		        Document doc = Jsoup.parse(input, "CP1257", url);*/
		
		//check the encoding
		//System.out.println(doc.outputSettings().charset().displayName());
		
		// a.getClass().getName(); what type of the variable or the value
		// http://jsclass.jcoglan.com/equality.html
	}
	
}
