package dataHandling;

import java.io.IOException;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.selection.Selection;

public class DataObject {

	public DataObject() throws IOException {
	}
	public static void main(String[] args) throws IOException {
		/*
		 * loading the data file.
		 */
		Table dataTable = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/HR_comma_sep.csv");


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
		 *Knowing the number and the size of (columns, rows)
		 */

		System.out.println("Total number of columns and rows");
		String tableShape = dataTable.shape();
		System.out.println(tableShape);

		//Creating a new table as a subset of original table.
		Table newSubTable = dataTable.select( "satisfaction_level", "last_evaluation", "number_project", "sales", "salary");
		System.out.println(newSubTable.columnNames());

		//combining tables: combination based on rows
		//Table mergedTables1 = dataTable.append(newSubTable);
		//Combining  based on columns
		//Table mergedTables2 = newSubTable.concat(dataTable);

		/**
		 *Handling of Columns
		 */

		//Printing all the column names
		System.out.println(dataTable.columnNames());

		//Retrieving a single column from the table

		StringColumn theSalary = (StringColumn) dataTable.column("salary");
		System.out.println(theSalary);

		//Selecting the columns that you want want to sort on.
		//Sorting the table with specific Columns( ascending or descending manner)
		Table ascendingHr = dataTable.sortAscendingOn("salary");
		System.out.println(ascendingHr.first(5));;

		/**
		 * Interacting with rows
		 */

		//Accessing the first 3 rows of the table
		System.out.println("First rows of the table");
		Table tableHead = dataTable.first(3);
		System.out.println(tableHead);

		//Accessing the last 3 rows of the table
		System.out.println("Last rows of the table");
		Table tableTail = dataTable.last(3);
		System.out.println(tableTail);

		/**
		 * Filtering unwanted  data
		 */

		// printing the structure of the table to identify the columns that we would like to remove
		System.out.println(dataTable.columnNames());


		//Creating a table from the loaded data set
		Table filteredTable = dataTable.select( "satisfaction_level", "last_evaluation", "left", "promotion_last_5years");
		System.out.println("the selected columns are: "+ filteredTable.columnNames());

		// Want table which fits a specific criteria
		//EXAMPLe: All employees whose has high salary.

		Table filteredSalary = dataTable.where(dataTable.stringColumn("salary").isEqualTo("high"));
		System.out.println("the high salary is "+ filteredSalary);
		//Accessing the first 5 rows of the filteredSalary.
		System.out.println("Printing the first rows of the filteredSalary");
		Table tableHead2 = filteredSalary.first(5);
		System.out.println(tableHead2);

		Table filtered = dataTable.where(
				dataTable.doubleColumn("satisfaction_level").isGreaterThan(0.5)
						.and(dataTable.stringColumn("sales").isEqualTo("hr")));
		//System.out.println(filtered);

		/**
		 * Handling the Null and the NAN data
		 */

		//Removing Columns with Missing data but may be it is unwanted.
		//dataTable.removeColumnsWithMissingValues();







	}
}


