import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;


public class GUI {
	
	public static void writer(String word) {
		BufferedWriter writer = null;
		String path = "/Users/kristinaslekyte/Desktop/data.txt";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path, true), "utf-8"));
			writer.write(word);
			writer.newLine();
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}
	}
	
	public static void listWriter (List<String> list){
		for (String list0 : list){
			writer(list0);			
		}		
	}
	
	public static void mapWriter (Map<String, Integer> map){
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			writer(entry.getKey() + " : " + entry.getValue());
		}
	}
}