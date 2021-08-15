package dataHandling;

import java.io.IOException;
import tech.tablesaw.api.Table;

public class DataObject {

	public DataObject() throws IOException {
	}
	public static void main(String[] args) throws IOException {
		/*
		 * loading the data file.
		 */
		Table dataTable = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalytics/HR_comma_sep.csv");


		/**
		 *Accessing different objects from the data sets (Columns and Rows).
		 *Interacting with the table
		 */

		//Getting the table structure
		System.out.println("The structure of the loaded Data");
		Table tableStructure = dataTable.structure();
		System.out.println(tableStructure);


		/**
		 *Knowing the size of the table that loaded.
		 *Knowing the columns and the rows
		 */

		System.out.println("the total number of columns and rows");
		String tableShape = hrAnalytics.shape();
		System.out.println(tableShape);

	}
}


