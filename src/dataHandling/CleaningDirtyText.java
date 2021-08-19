package dataHandling;

public class CleaningDirtyText {
	private static final String dirtyText = "this*is#a*&very_dirty&String";

	public static void main(String[] args) {
		validateInt("1234");
		validateInt("Ishmael");
		cleaningText(dirtyText);

	}

	private static void cleaningText(String dirtytext2) {
		/*
		 * remove leading and trailing spaces, and split our words into a String array.
		 * The split method allows you to break apart text on a given delimiter. In this
		 * case, we chose to use the regular expression \\W, which represents anything
		 * that is not a word character:
		 */
		System.out.println(dirtyText);
		String[] words = dirtyText.toLowerCase().trim().split("[\\W\\d]+");
		for (int i = 0; i < words.length; i++) {
			System.out.print(words[i]);
		}
		System.out.println("\nsee the cleaned text:-");
		for (String clean : words) {
			System.out.print(clean + " ");
		}
	}

	private static void validateInt(String test) {
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
}