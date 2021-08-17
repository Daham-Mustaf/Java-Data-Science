package dataHandling;

import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.api.PiePlot;
import tech.tablesaw.plotly.api.TimeSeriesPlot;
import static tech.tablesaw.aggregate.AggregateFunctions.*;

/**
 * Trend analysis is trying to recognize patterns from data that has been
 * collected overtime, predicting features using historical data. features are
 * characteristics being observed in the data.
 */
public class Trend {
	public Trend() {
	}
	/**
	 * A function to write words from String Column from the table to a file .txt
	 * @param mywords
	 * @return void
	 */
	public static void writeToMyFile(StringColumn mywords) {
		
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter("wordcloud.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String [] myWordsArr = (String[]) mywords.asObjectArray();
		
	    for (int i = 0; i < myWordsArr.length; i++) {
	      try {
	    	  myWriter.write(myWordsArr[i] + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    try {
	    	myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Function adopted from KUMO library for creating wordcloud
	 * @param theFileWords( file containing words  for wordcloud to analyze)
	 * @param imageToSave(.png)
	 * @return void
	 */
	public static void createWordCloud(String theFileWords){
		final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		List<WordFrequency> wordFrequencies = null;
		try {
			wordFrequencies = frequencyAnalyzer.load(theFileWords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File was not found");
		}
		// A rectangular representation of the word cloud.
		final Dimension dimension = new Dimension(300, 300);
		final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);
		wordCloud.setPadding(0);
		wordCloud.setBackground(new RectangleBackground(dimension));
		wordCloud.setColorPalette(new ColorPalette(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE));
		wordCloud.setFontScalar(new LinearFontScalar(10, 40));
		wordCloud.build(wordFrequencies);
		wordCloud.writeToFile("wordcloudToSave1.png");
	}
	
	/**
	 * A function adopted from KUMO library for creating wordCloud
	 * @param theFileWords
	 * @return void
	 */
	public static void createWordCloud2(String theFileWords) {
		final FrequencyAnalyzer myFrequencyAnalyzer = new FrequencyAnalyzer();
		myFrequencyAnalyzer.setWordFrequenciesToReturn(300);
		myFrequencyAnalyzer.setMinWordLength(4);
		//myFrequencyAnalyzer.setStopWords(loadStopWords());

		List<WordFrequency> myWordFrequencies = null;
		try {
			myWordFrequencies = myFrequencyAnalyzer.load(theFileWords);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final Dimension myDimension = new Dimension(500, 312);
		final WordCloud myWordCloud = new WordCloud(myDimension, CollisionMode.PIXEL_PERFECT);
		myWordCloud.setPadding(2);
		
		
		myWordCloud.setBackground(new RectangleBackground(myDimension));
		myWordCloud.setColorPalette(new ColorPalette(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE));
		myWordCloud.setFontScalar(new LinearFontScalar(10, 40));
		myWordCloud.build(myWordFrequencies);
		myWordCloud.writeToFile("wordcloud2.png");
	}

	public static void main(String[] args) throws IOException {
//        //Trend myTrend = new Trend();
//        Table bitconPriceData = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/Bitcoin-usd.csv");
//        Table bitprice = bitconPriceData.structure();
//        System.out.println(bitprice);
//
//        //Time series data trend Analysis
//        // create(title, TableData, key Features x & y axis).
//        // Plot.show prints out the plot
//        Plot.show(TimeSeriesPlot
//                .create("Bitcoin Exchange Prices in USD", bitconPriceData, "Date", "High"));
//
//         /*
//         * Visualizing different data forms
//         * Text (Word cloud)
//         * Number (bar charts)
//         */
//
//         //Creating Bar Charts
//        Table diabetesData = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/diabetes.csv");
//        Table diabetes = diabetesData.structure();
//        System.out.println(diabetes);
//        Table ageAverage = diabetesData.summarize("Age", mean).by("Outcome");
//        Plot.show(
//                HorizontalBarPlot.create(
//                        " Average Age by Outcome",		    // plot title
//                        ageAverage,				                // Calculated averages of the age
//                        "Outcome",					// grouping column name
//                        "mean [Age]"));
//
//         //Showcase AVG BMI by Age
//        Table bmiAverage = diabetesData.summarize("BMI", mean).by("Age");
//        Plot.show(
//                HorizontalBarPlot.create(
//                        " Average BMI by Age",	     	// plot title
//                        bmiAverage,				            // Calculated averages of the BIM
//                        "Age",					// grouping column name
//                        "mean [BMI]"));
//
//
//       //Using Pie Chart to showcase average age by IBM
//        Plot.show(
//                PiePlot.create("Average BMI by age", ageAverage, "BMI", "mean [AGE]"));
		/*
		 * Visualizing words using word cloud
		 */

		// Loading a new Set of data
		Table kamitiData = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/KAMITI.csv");
		Table kamiti = kamitiData.structure();
		System.out.println(kamiti);

		/*
		 * Calling to writeToMyFile function
		 */
		
		// getting String StringColumn 
        StringColumn theText = (StringColumn) kamitiData.column("Text");
        writeToMyFile(theText);
        createWordCloud("wordcloud.txt");
        createWordCloud2("wordcloud.txt");

	}
}
