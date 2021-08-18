package dataHandling;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVReading {
        private static final String FILE_PATH = "/Users/m-store/eclipse-workspace/DataAnalysis/HR_comma_sep.csv";

        public static void main(String[] args) throws IOException {
            // Read a CSV file
            // Build reader instance
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);
            // Reading Records One by One in a String array
            String[] nRecord;
            List<String> sallary = new ArrayList();
            List<String> last_evaluation = new ArrayList<>();
            while ((nRecord = csvReader.readNext()) != null) {
                last_evaluation.addAll(Arrays.asList(nRecord[1]));
                sallary.addAll(Arrays.asList(nRecord[9]));

            }
            System.out.println(sallary);
            //System.out.println(last_evaluation);
            csvReader.close();
        }

    }


