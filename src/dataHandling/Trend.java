package dataHandling;

import tech.tablesaw.api.Table;
import java.io.IOException;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.TimeSeriesPlot;

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
    }
}
