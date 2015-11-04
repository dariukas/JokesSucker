public class LTGrammar {

	// the method checks if the noun belongs to the category of masculine gender
	public static String nounGenderDetector(String word) {
		String word1 = null;
		if (word.endsWith("s")) {
			word1 = word;
		}
		return word1;
	}

	// the method finds out the declination of the noun
	public static void genderDetector(String word) {
		if (word.endsWith("uo")) {
			System.out.print("Penkta Linksniuote");
		} else if (!word.endsWith("s")) {
			System.out.print("Antra Linksniuote");
		} else if (word.endsWith("us")) {
			System.out.print("Ketvirta Linksniuote");
		} else if (word.endsWith("is")) {
			System.out.print("Trecia Linksniuote");
		} else if (word.endsWith("s")) {
			System.out.print("Pirma Linksniuote");
		} else {
			System.out.print("Isimtis");
		}

	}

}
