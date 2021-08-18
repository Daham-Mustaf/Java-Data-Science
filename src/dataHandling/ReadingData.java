package dataHandling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadingData {
    public static void main(String[] args) {
        String filPath = "<filename with path goes here>";
        try(BufferedReader br = new BufferedReader(new FileReader(filPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // TODO ... do something with line
                // TODO ... parse line e.g. CSV, TSV, JSON
                // TODO ... check each value if required
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println(e);
        }

    }

}
