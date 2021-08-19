package dataHandling;


import java.io.BufferedReader;
import java.io.FileReader;

public class ParsingTxtFile {
    public static void main(String[] args) {

        final String PATH_STRING = "/Users/m-store/Desktop/Hadoo/data.txt";
        parseTxtFile(PATH_STRING);
    }

    private static void parseTxtFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // ONLY do this if columnNames exists
            String columnNames = br.readLine();
            System.out.println("column Names: " + columnNames);
            String line;
            while ((line = br.readLine()) != null) {
                if (true) {
                    /* parse each line */
                    String[] tokens = line.split(",");
                    // YYYY = tokens[0]
                    // MM = tokens[1]
                    // DD = tokens[2]
                    // temperature = tokens[3]
                    // utilize String.trim() to remove any pesky leading or trailing whitespaces.
                    int year = Integer.parseInt(tokens[0].trim());
                    int month = Integer.parseInt(tokens[1].trim());
                    int day = Integer.parseInt(tokens[2].trim());
                    int temp = Integer.parseInt(tokens[3].trim());
                    System.out.println(year + "-" + month + "-" + day + " : " + temp);
                }
            }
        } catch (Exception e) {

            System.err.println(e.getMessage());
        }
    }

    /*
     * the String.trim() method to remove any leading or trailing spaces around the input value
     * and then check its length. String.isEmpty()
     * returns true only if the string has zero length:
     */

    private static boolean checkBlanck(String line) {
        return line.trim().isEmpty();
    }

    /*
     * Null values appear in a variety of forms.
     *  "null", "NULL", or other string such as "na", dot, a null type or null literal),
     *   we want to keep track of these:
     */
    private boolean checkNull(String value) {
        return value == null || "null".equalsIgnoreCase(value);
    }
}


