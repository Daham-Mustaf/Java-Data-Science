package dataHandling;

import tech.tablesaw.api.Table;

import java.io.IOException;

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

        }
    }

