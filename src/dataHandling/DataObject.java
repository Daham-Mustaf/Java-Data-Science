package dataHandling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import tech.tablesaw.api.*;
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

		//Removing Rows holding missed data based on specific columns. by iterating over our data.
		//Creating variables of typ arrays to Hold columns.
		List<String> name = new ArrayList<>();
		List<Double>  satisfaction_level = new ArrayList<Double>();
		List<Double>  last_evaluation = new ArrayList<Double>();
		List<Integer>  number_project = new ArrayList<Integer>();
		List<Integer>  average_montly_hours = new ArrayList<Integer>();
		List<Integer>  time_spend_company = new ArrayList<Integer>();
		List<String>  sales = new ArrayList<String>();
		List<String>  salary= new ArrayList<String>();

		//To be used for interpolation, double arrays
		double [] satisfaction_level_raw = new double [20];
		double [] last_evaluation_raw = new double [20];
		int index =0;

		//Iterating over the table to remove the rows with NANs
		for (Row row : dataTable) {
			//Extract data in the row
			Double satisfaction_levelS = row.getDouble("satisfaction_level");
			Double last_evaluationS = row.getDouble("last_evaluation");
			String salesS= row.getString("sales");
			String salaryS = row.getString("salary");

			System.out.println("The SL" + satisfaction_levelS );
			System.out.println("The LE is " +last_evaluationS);
			System.out.println("The sale is " +salesS);
			System.out.println("The salary is " + salaryS);

			// the case of (Integer & Double) size !=0
			// for the case of String length()!=0
			if(salesS.length()!=0 && salaryS.length()!=0 && satisfaction_levelS!=0 && last_evaluationS!=0) {
				// adding the filtered date set to the list.
				satisfaction_level.add(satisfaction_levelS);
				last_evaluation.add(last_evaluationS);
				sales.add(salesS);
				salary.add(salaryS);
			}

			else {

			}
			//Adding the data for Interpolation
			satisfaction_level_raw[index] =satisfaction_levelS;
			last_evaluation_raw[index]=last_evaluationS;
			index++;
		}

		//Creating columns to store the variables:
		String[] sallaryArr =salary.toArray(new String[satisfaction_level.size()]);
		StringColumn salry = StringColumn.create("salry",sallaryArr);
		//Adding the columns to the table
		Table droppedRows =Table.create("DroppedRows",salry);

		//compare newly created table from filtered data with th original data
		String tableShape2 = droppedRows.shape();
		String tableShape1 = dataTable.shape();
		System.out.println(tableShape2);
		System.out.println(tableShape);
	    // Linear Interpolation
	    // data over time increasing or decreasing
	    //Satisfaction Level (y) depends on Last Evaluation(X)
	    LinearInterpolator myLinearInterp = new LinearInterpolator();
	    myLinearInterp.interpolate(satisfaction_level_raw, last_evaluation_raw);
	    /**
        *Formatting various data types
		 */

		//Getting the table structures
		System.out.println("Printing the structure of the table  loaded from my local machine");
		Table tableDataType = dataTable.structure();
		System.out.println(tableDataType);

		//Incase of Arrays with Int to double
		int[] age = {30, 12, 14, 41, 20, 80};
		double[] double_age = Arrays.stream(age).asDoubleStream().toArray();
}
}



