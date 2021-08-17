package dataHandling;

import tech.tablesaw.api.Table;
import java.io.IOException;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.api.TimeSeriesPlot;
import static tech.tablesaw.aggregate.AggregateFunctions.*;

/**
 * Trend analysis is trying to recognize patterns from data that has been collected
 * overtime, predicting features using historical data.
 * features are characteristics being observed in the data.
 */
public class Trend {
    public Trend(){}


    public static void main(String[] args) throws IOException {
        Trend myTrend = new Trend();
        Table bitconPriceData = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/Bitcoin-usd.csv");
        Table bitprice = bitconPriceData.structure();
        System.out.println(bitprice);

        //Time series data trend Analysis
        // create(title, TableData, key Features x & y axis).
        // Plot.show prints out the plot
        Plot.show(TimeSeriesPlot
                .create("Bitcoin Exchange Prices in USD", bitconPriceData, "Date", "High"));

        /**
         * Visualizing different data forms
         * Text (Word cloud)
         * Number (bar charts)
         */

//Creating Bar Charts
        Table diabetesData = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/diabetes.csv");
        Table diabetes = diabetesData.structure();
        System.out.println(diabetes);
        Table ageAverage = diabetesData.summarize("Age", mean).by("Outcome");
        Plot.show(
                HorizontalBarPlot.create(
                        " Average Age by Outcome",		    // plot title
                        ageAverage,				                // Calculated averages of the age
                        "Outcome",					// grouping column name
                        "mean [Age]"));

         //Showcase AVG BMI by Age
        Table bmiAverage = diabetesData.summarize("BMI", mean).by("Age");
        Plot.show(
                HorizontalBarPlot.create(
                        " Average BMI by Age",	     	// plot title
                        bmiAverage,				            // Calculated averages of the aGE
                        "Age",					// grouping column name
                        "mean [BMI]"));


       //Using Pie Chart to showcase average age by IBM
        Plot.show(
                PiePlot.create("Average Age by SEX", ageAverage, "IBM", "mean [AGE]"));


    }
}
