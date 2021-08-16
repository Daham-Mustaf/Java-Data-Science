package dataHandling;

import tech.tablesaw.api.Table;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadingData {

    public LoadingData() {
    }

    public static void main(String[] args) throws IOException {

        /**
         * When loading the entire CSV sheet
         * Give the path that the csv is located
         */
            Table dataTable = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/HR_comma_sep.csv");

         //Getting the structure of the table using Table saw.
        Table structure = dataTable.structure();
        System.out.println("the structure of hr_comma_.csv ?");
        System.out.println(structure);

        /**
         * Loading the data from the API by using the Stream API function
         * Creating a function  to load data from  Stream API
         * The data has to be passed through the Stream Interface
         * the link to where the data is located.
         */

        // https://github.com/Daham-Mustaf/Java-Data-Science/blob/main/HR_comma_sep.csv

        String location =  "https://github.com/Daham-Mustaf/Java-Data-Science/blob/main/HR_comma_sep.csv";
        Table hrDataTable;
        try (InputStream input = new URL(location).openStream()) {
            hrDataTable = Table.read().csv(input, "bush");
        }
        Table streamStructure = hrDataTable.structure();
        System.out.println("the output from the API"+ streamStructure);

        }
    }

