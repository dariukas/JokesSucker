import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class TextsManipulation {

	// the method splits a text into its words
	public static List<String> splitIntoTheWords(String text) {

		String[] textWordsList = text.split("[^\\p{L}\\p{Nd}]++");
		
		// create the list of the words of the text
		List<String> textWords = new ArrayList<String>();
		for (String textWord : textWordsList) {
			textWords.add(textWord.trim());
			//System.out.println(textWord);
		}
		return textWords;
	}
		
	// the method splits texts into their words
	public static List<String> textsIntoTheWords(List<String> texts) {

		// initialize the array of the words of the texts
		List<String> textsWords = new ArrayList<String>();
		System.out.println("splitting...");
		for (String text : texts) {
			List<String> textWords = splitIntoTheWords(text);
			for (String textWord : textWords) {
				textsWords.add(textWord);
			}
		}
		return textsWords;
	}
	
	// combine lists
	public static List<String> combineLists(List<String> list1, List<String> list2) {
		List<String> list = list1;
		for (String frmList2 : list2) {
			list.add(frmList2);
		}
		return list;
	}
	
	// combine lists to map
	public static Map<String, String> combineListsToMap (List<String> list1, List<String> list2) {
	
    Map<String, String> map = new HashMap<String, String>();
	Iterator<String> i1 = list1.iterator();
	Iterator<String> i2 = list2.iterator();
	while (i1.hasNext() && i2.hasNext()) {
	    map.put(i1.next(), i2.next());
	}
	if (i1.hasNext() || i2.hasNext()) System.out.println("The lists have different length!");
	return map;
}
	
	
	// this method filters the lists: removes the repeated words, ignoring their cases
	public static List<String> avoidRepetition(List<String> list) {
		Set<String> s = new LinkedHashSet<String>(list);
		list.clear();
		list.addAll(s);
		return list;
	}

	// this method converts all the letters of the words from the list to lower
	// case
	public static List<String> convertToLowerCase(List<String> list) {
		for (String listElement1 : list) {
			list.set(list.indexOf(listElement1), listElement1.toLowerCase());
		}
		return list;
	}
	
}
