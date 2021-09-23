package dataHandling;

public class CleaningData {

	public CleaningData(){}

	public void cleaninText(String dirtytext){
		/*
		 * remove leading and trailing spaces, and split our words into a String array.
		 * The split method allows you to break apart text on a given delimiter. In this
		 * case, we chose to use the regular expression \\W, which represents anything
		 * that is not a word character:
		 */
		//System.out.println(dirtytext);
		String[] words = dirtytext.toLowerCase().trim().split("[\\W\\d]+");
		for (int i = 0; i < words.length; i++) {
			//System.out.print(words[i]);
		}
		System.out.println("the cleaned text:-");
		for (String clean : words) {
			System.out.print(clean + " ");
		}
	}
	public void validateInt(String test) {
		/*
		 * Sometimes need to validate whether a piece of data is of a specific
		 * type, such as integer or floating point data or not.
		 */

		try {
			int validIntTyp = Integer.parseInt(test);
			System.out.print(validIntTyp + " is a valid integer\n");
		} catch (NumberFormatException e) {
			System.out.print(test + " is not a valid integer\n");

		}
	}
	public String cleanText(String text){
//		text = text.replaceAll("[^p{ASCII}]","");
//		text = text.replaceAll("s+", " ");
//		text = text.replaceAll("p{Cntrl}", "");
//		text = text.replaceAll("[^p{Print}]", "");
//		text = text.replaceAll("p{C}", "");
		return text;
	}




public static void main(String[] args) {
		CleaningData cleaningDirtyText = new CleaningData();
		// "this*is#a*&very_dirty&String@ R %";
		final String dirtyText = "this*is#a*&very_dirty&String";
		System.out.println(dirtyText+ "dirtyText");
//		cleaningDirtyText.validateInt("1234");
//		cleaningDirtyText.validateInt("Ishmael");
		cleaningDirtyText.cleaninText(dirtyText);
	}
}


