import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Comparison {
	
	
	
	//This method checks how many times each word of one list of words repeated in another list of words  
	//@list1 - the first list of the words
	//@list2 - the second list of the words
	//map - the frequency of the words of the first list in the second list
	public static Map<String, Integer> comparison(List<String> list1, List<String> list2) {
		//List<String> list1 = Arrays.asList("sup1", "sup2", "sup3");
		//List<String> list2 = Arrays.asList("sup1", "sup1", "sup3", "sup1");
		Map<String, Integer> map = new HashMap<String, Integer>();
        int i;
		for (String elementOfList1 : list1) {
			 i = 0;
			for (String elementOfList2 : list2) {
				if (elementOfList2.equalsIgnoreCase(elementOfList1)) {
					i++;
				}
				
			}
				if (i > 0) {
					map.put(elementOfList1, i);
				}
		}
		/*
		// printing
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		*/
		return map;
	}
	
	//This method finds in how many pieces of text (jokes, comments) the words of the list are found
	//@list1 - list of the words to check in the jokes
	//@listOfSentences - list of the jokes
	//map - in how many jokes (separate pieces of text) each word from the given list1 is repeated
	public static Map<String, Integer> inHowManyComments(List<String> list1, List<String> listOfSentences) {
		Map<String, Integer> map = new HashMap<String, Integer>();
        int i;
		for (String elementOfList1 : list1) {
			 i = 0;
			for (String listOfSentences0 : listOfSentences) {
				
				List<String> words = TextsManipulation.splitIntoTheWords(listOfSentences0);
				
				for (String word : words){
					if (word.equalsIgnoreCase(elementOfList1)) {
						i++;
						break;
					}	
				}
			
			}
				if (i > 0) {
					map.put(elementOfList1, i);
				}
		}
		/*
		// printing
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}*/
		return map;
	}
	
//some people use only one words, some people use even two words, some - three words	
	
}