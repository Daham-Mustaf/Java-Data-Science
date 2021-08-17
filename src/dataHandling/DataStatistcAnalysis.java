package dataHandling;

import smile.math.distance.CorrelationDistance;
import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.Table;
import static tech.tablesaw.aggregate.AggregateFunctions.*;
import java.io.IOException;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.Heatmap;
import tech.tablesaw.plotly.api.ScatterPlot;
import tech.tablesaw.plotly.components.Figure;



public class DataStatistcAnalysis {

	/**
	 * Correlation is a statistical measure that expresses the extent to which two variables are linearly related
	 * @param x an array of data feature
	 * @param y an array of data feature
	 * @return correlation coefficient which = 1 - the correlation between 2 features
	 */
	public DataStatistcAnalysis() {
	}
	public static double getCorrelation(double [] x, double []y) {
		CorrelationDistance correlationDistance = new CorrelationDistance();
		return 1-(correlationDistance.pearson(x,y));
	}

	public static void main(String[] args) throws IOException {
		DataStatistcAnalysis mayData = new DataStatistcAnalysis();
		Table diabetesData = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/diabetes.csv");
		Table diabetes = diabetesData.structure();
		System.out.println(diabetes);

		/**
		 * Calculating multiple values for one column functions parameter is an array of
		 * AggregateFunction, so you can calculate multiple statistics with a single
		 * method invocation Retrieving the AGE, BMI columns to calculate the Mean, Max,
		 * Min and median.
		 */

		NumberColumn age = (NumberColumn) diabetesData.nCol("Age");
		NumberColumn bmi = (NumberColumn) diabetesData.nCol("BMI");
		System.out.println(age + "" + bmi);
		Table ageDistribution = diabetesData.summarize(age, mean, max, min, median).apply();
		System.out.println(ageDistribution);

		Table bmiDistribution = diabetesData.summarize(bmi, mean, max, min, median).apply();
		System.out.println(bmiDistribution);
		// Calculating the mean AGE, min ans max by Outcome
		Table avgAge = diabetesData.summarize("Age", mean, max, min, median).by("Outcome");
		System.out.println(avgAge);

		/**
		 * Correlation in the data One feature ( Scatter plot) vs another feature Many
		 * features (Heatmap)
		 */
		// Creating the table with the columns that will be used for the scatter plot

		Table theScatterData = Table.create("theScatterData", age, bmi);
		Figure scatterFigure = ScatterPlot.create("Age by BMI ", theScatterData, "Age", "Bmi");
		Plot.show(scatterFigure);

		// Creating a heatMap of of the dataset
		// dropping the sex Column and creating a heatmap
		Figure theHeatMap = Heatmap.create("Correlation of the different variables ", diabetesData, "Age", "Bmi");
		Plot.show(theHeatMap);

	}

}
