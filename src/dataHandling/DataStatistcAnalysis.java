package dataHandling;

import tech.tablesaw.api.NumberColumn;
import tech.tablesaw.api.Table;
import static tech.tablesaw.aggregate.AggregateFunctions.*;
import java.io.IOException;
import tech.tablesaw.plotly.api.ScatterPlot;


import static tech.tablesaw.aggregate.AggregateFunctions.*;
import java.lang.*;
import java.util.List;
import java.util.stream.Stream;

public class DataStatistcAnalysis {
    public DataStatistcAnalysis() { }

    public static void main(String[] args) throws IOException {
        DataStatistcAnalysis mayData = new DataStatistcAnalysis();
        Table diabetesData  = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/diabetes.csv");
        Table diabetes = diabetesData.structure();
        System.out.println(diabetes);

        /**
         * Calculating multiple values for one column
         * functions parameter is an array of AggregateFunction, so you can calculate multiple statistics with a single method invocation
         *Retrieving the AGE, BMI  columns to calculate the Mean, Max, Min and median.
         */

        NumberColumn age = (NumberColumn) diabetesData.nCol("Age");
        NumberColumn bmi = (NumberColumn) diabetesData.nCol("BMI");
        System.out.println(age + ""+ bmi);
        Table ageDistribution = diabetesData.summarize(age, mean, max, min, median).apply();
        System.out.println(ageDistribution);

        Table bmiDistribution = diabetesData.summarize(bmi, mean, max, min, median).apply();
        System.out.println(bmiDistribution);
        // Calculating the mean AGE, min ans max by Outcome
        Table avgAge  = diabetesData.summarize("Age", mean, max, min,median).by("Outcome");
        System.out.println(avgAge);

        /**
         * Correlation in the data
         * One feature ( Scatter plot) vs another feature
         * Many features (Heatmap)
         */
        //Creating the table with the columns that will be used for the scatter plot

    }

    }
