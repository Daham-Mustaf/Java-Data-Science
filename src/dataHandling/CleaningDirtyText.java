package dataHandling;

public class CleaningDirtyText {
	private static final String dirtyText = "this*is#a*&very_dirty&String";

	public static void main(String[] args) {
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
		System.out.println("\nsee the cleand text:-");
		for (String clean : words) {
			System.out.print(clean + " ");
		}
	}
}