package dataHandling;

import tech.tablesaw.api.Table;

import java.io.IOException;

public class Trend {
    public Trend(){}


    public static void main(String[] args) throws IOException {
        Trend myTrend = new Trend();
        Table diabetesData = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/Bitcoin-usd.csv");
        Table diabetes = diabetesData.structure();
        System.out.println(diabetes);


    }
}
