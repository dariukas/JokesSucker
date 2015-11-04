import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResultsManipulation {
	
	
	//output by the values in descending order
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
	    Comparator<K> valueComparator =  new Comparator<K>() {
	        public int compare(K k1, K k2) {
	            int compare = map.get(k2).compareTo(map.get(k1));
	            //if we exchange k1 and k2, it will get the ascending case
	            if (compare == 0) return 1;
	            else return compare;
	        }
	    };
	    Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
	}
	
	//output by the keys in ascending order
	public static Map sortMapByKeyAscending (Map unsortMap) {	    
		Map<String, String> treeMap = new TreeMap<String, String>(unsortMap);	
		return treeMap;
	}
	
	//output by the keys in descending order
	public static Map sortMapByKeyDescending (Map unsortMap) {	    
		Map<String, String> treeMap = new TreeMap<String, String>(
				new Comparator<String>() {
					 
					@Override
					public int compare(String s1, String s2) {
						int compare = s2.compareTo(s1);
						if (compare == 0) return 1;
						return compare;
					}
		 
				});
		treeMap.putAll(unsortMap);
		return treeMap;
	}
}
