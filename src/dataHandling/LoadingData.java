package dataHandling;

import tech.tablesaw.api.Table;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;

public class LoadingData {

	public LoadingData() {
	}

	public static void main(String[] args) throws IOException {

		/**
		 * When loading the entire CSV sheet Give the path that the csv is located
		 */
		Table dataTable = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/HR_comma_sep.csv");

		// Getting the structure of the table using Table saw.
		Table structure = dataTable.structure();
		System.out.println("the structure of hr_comma_.csv ?");
		System.out.println(structure);

		/**
		 * Loading the data from the API by using the Stream API function Creating a
		 * function to load data from Stream API The data has to be passed through the
		 * Stream Interface the link to where the data is located.
		 */

		// https://github.com/Daham-Mustaf/Java-Data-Science/blob/main/HR_comma_sep.csv

		String location = "https://github.com/Daham-Mustaf/Java-Data-Science/blob/main/HR_comma_sep.csv";
		Table hrDataTable;
		try (InputStream input = new URL(location).openStream()) {
			hrDataTable = Table.read().csv(input, "bush");
		}
		Table streamStructure = hrDataTable.structure();
		System.out.println("the output from the API" + streamStructure);

		/**
		 * Getting the data from mysql database USING JDBC a table in my local database
		 * Database Name : Test Table Name : Person
		 */

		String DB_URL = "jdbc:mysql://localhost:3306/Test";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Table DBcustomers = null;
		try (Statement myStament = conn.createStatement()) {
			String sql = "SELECT * FROM Customer";
			try (ResultSet results = myStament.executeQuery(sql)) {
				DBcustomers = Table.read().db(results, "Person");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Table DBstructure = DBcustomers.structure();
		System.out.println(DBstructure);
		System.out.println("This two are good , off to streaming");

	}

}
