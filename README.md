# Java-Data-Science Introduction
# Table of contents
1 [Java-Data-Science Introduction:](#introduction)
- [Dealing with Real Data:](#work)
- [Parsing delimited strings:](#del)
- [Reading from a Text File:](#txt)
- [Tablesaw:](#tab)
- [Kumo:](#Kumo)




## Java-Data-Science Introduction <a name="introduction"></a>
Your ultimate goal is to retrieve data from its source, reduce the data via statistical analysis or learning, and then present some kind of knowledge about what was learned, usually in the form of a graph. However, even if your result is a single value such as the total revenue,
most engaged user, or a quality factor, you still follow the same protocol:
input data → reductive analysis → output data.
Data Models:
What form is the data in, and what form do you need to transform it to so you can move forward? 


## Dealing with Real Data: <a name="work"></a>
Real data is messy, incomplete, incorrect, and sometimes incoherent. If you are work‐ ing with a “perfect” dataset, it’s because someone else spent a great deal of time and effort in getting it that way.
It is also possible that your data is, in fact, not perfect, and you are unwittingly
performing analyses on junk data. The only way to be sure is to get data from the source and process it yourself. This way, if there is a mistake, you know who to blame.

## Parsing delimited strings: <a name="del"></a>
Considering the popularity of spreadsheets and database dumps, it is highly likely you will be given a CSV dataset at some point. Parsing this kind of file could not be easier! Consider the data in our example formatted as a CSV file:

class [CSVReading.java](https://github.com/Daham-Mustaf/Java-Data-Science/blob/main/src/dataHandling/CSVReading.java)

```java
public class CSVReading {
        private static final String FILE_PATH = "/Users/m-store/eclipse-workspace/DataAnalysis/HR_comma_sep.csv";

        public static void main(String[] args) throws IOException {
            // Read a CSV file
            // Build reader instance
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH));
            CSVReader csvReader = new CSVReader(reader);
            // Reading Records One by One in a String array
            String[] nRecord;
            List<String> sallary = new ArrayList();
            List<String> last_evaluation = new ArrayList<>();
            while ((nRecord = csvReader.readNext()) != null) {
                last_evaluation.addAll(Arrays.asList(nRecord[1]));
                sallary.addAll(Arrays.asList(nRecord[9]));

            }
            System.out.println(sallary);
            //System.out.println(last_evaluation);
            csvReader.close();
        }

``` 

## Parsing txt file: <a name="txt"></a>
The general approach for reading a text file is to create a `FileReader` instance sur‐ rounded by a `BufferedReader`  that enables reading each line. Here, `FileReader` takes the argument of String filename, but `FileReader` can also take a File object as its argument. The File object is useful when filenames and paths are dependent on the operating system. This is the generic form for reading files line by line with a `BufferedReader`:

```java
public class ParsingTxtFile {
    public static void main(String[] args) {

        final String PATH_STRING = "/Users/m-store/Desktop/Hadoo/data.txt";
        parseTxtFile(PATH_STRING);
    }

    private static void parseTxtFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // ONLY do this if columnNames exists
            String columnNames = br.readLine();
            System.out.println("column Names: " + columnNames);
            String line;
            while ((line = br.readLine()) != null) {
                if (true) {
                    /* parse each line */
                    String[] tokens = line.split(",");
                    // YYYY = tokens[0]
                    // MM = tokens[1]
                    // DD = tokens[2]
                    // temperature = tokens[3]
                    // utilize String.trim() to remove any pesky leading or trailing whitespaces.
                    int year = Integer.parseInt(tokens[0].trim());
                    int month = Integer.parseInt(tokens[1].trim());
                    int day = Integer.parseInt(tokens[2].trim());
                    int temp = Integer.parseInt(tokens[3].trim());
                    System.out.println(year + "-" + month + "-" + day + " : " + temp);
                }
            }
        } catch (Exception e) {

            System.err.println(e.getMessage());
        }
    }

    /*
     * the String.trim() method to remove any leading or trailing spaces around the input value
     * and then check its length. String.isEmpty()
     * returns true only if the string has zero length:
     */

    private static boolean checkBlanck(String line) {
        return line.trim().isEmpty();
    }

    /*
     * Null values appear in a variety of forms.
     *  "null", "NULL", or other string such as "na", dot, a null type or null literal),
     *   we want to keep track of these:
     */
    private boolean checkNull(String value) {
        return value == null || "null".equalsIgnoreCase(value);
    }
   ```
 ## Tablesaw: <a name="tab"></a> 
  is a dataframe and visualization library, as well as utilities for loading, transforming, filtering, and summarizing data. It's fast and careful with memory.
  If you work with data in Java, it may save you time and effort. 
Tablesaw also supports descriptive statistics and integrates well with the Smile machine learning library.
[Tablesaw](https://jtablesaw.github.io/tablesaw/)
```java
public class DataObject {

	public DataObject() throws IOException {
	}

	public static void main(String[] args) throws IOException {
		/*
		 * loading the data file.
		 */
		Table dataTable = Table.read().csv("/Users/m-store/eclipse-workspace/DataAnalysis/HR_comma_sep.csv");

		/**
		 * Accessing different objects from the data sets (Columns and Rows).
		 * Interacting with the table
		 */

		// Getting the table structure
		System.out.println("The structure of the loaded Data");
		Table tableStructure = dataTable.structure();
		System.out.println(tableStructure);

		/**
		 * Knowing the size of the table that loaded. Knowing the number and the size of
		 * (columns, rows)
		 */

		System.out.println("Total number of columns and rows");
		String tableShape = dataTable.shape();
		System.out.println(tableShape);

		// Creating a new table as a subset of original table.
		Table newSubTable = dataTable.select("satisfaction_level", "last_evaluation", "number_project", "sales",
				"salary");
		System.out.println(newSubTable.columnNames());

		// combining tables: combination based on rows
		// Table mergedTables1 = dataTable.append(newSubTable);
		// Combining based on columns
		// Table mergedTables2 = newSubTable.concat(dataTable);

		/**
		 * Handling of Columns
		 */

		// Printing all the column names
		System.out.println(dataTable.columnNames());

		// Retrieving a single column from the table

		StringColumn theSalary = (StringColumn) dataTable.column("salary");
		System.out.println(theSalary);

		// Selecting the columns that you want want to sort on.
		// Sorting the table with specific Columns( ascending or descending manner)
		Table ascendingHr = dataTable.sortAscendingOn("salary");
		System.out.println(ascendingHr.first(5));
		;

		/**
		 * Interacting with rows
		 */

		// Accessing the first 3 rows of the table
		System.out.println("First rows of the table");
		Table tableHead = dataTable.first(3);
		System.out.println(tableHead);

		// Accessing the last 3 rows of the table
		System.out.println("Last rows of the table");
		Table tableTail = dataTable.last(3);
		System.out.println(tableTail);

		/**
		 * Filtering unwanted data
		 */

		// printing the structure of the table to identify the columns that we would
		// like to remove
		System.out.println(dataTable.columnNames());

		// Creating a table from the loaded data set
		Table filteredTable = dataTable.select("satisfaction_level", "last_evaluation", "left",
				"promotion_last_5years");
		System.out.println("the selected columns are: " + filteredTable.columnNames());

		// Want table which fits a specific criteria
		// EXAMPLe: All employees whose has high salary.

		Table filteredSalary = dataTable.where(dataTable.stringColumn("salary").isEqualTo("high"));
		System.out.println("the high salary is " + filteredSalary);
		// Accessing the first 5 rows of the filteredSalary.
		System.out.println("Printing the first rows of the filteredSalary");
		Table tableHead2 = filteredSalary.first(5);
		System.out.println(tableHead2);

		Table filtered = dataTable.where(dataTable.doubleColumn("satisfaction_level").isGreaterThan(0.5)
				.and(dataTable.stringColumn("sales").isEqualTo("hr")));
		// System.out.println(filtered);

		/**
		 * Handling the Null and the NAN data
		 */

		// Removing Columns with Missing data but may be it is unwanted.
		// dataTable.removeColumnsWithMissingValues();

		// Removing Rows holding missed data based on specific columns. by iterating
		// over our data.
		// Creating variables of typ arrays to Hold columns.
		List<String> name = new ArrayList<>();
		List<Double> satisfaction_level = new ArrayList<Double>();
		List<Double> last_evaluation = new ArrayList<Double>();
		List<Integer> number_project = new ArrayList<Integer>();
		List<String> sales = new ArrayList<String>();
		List<String> salary = new ArrayList<String>();

		// To be used for interpolation, double arrays
		double[] satisfaction_level_raw = new double[20];
		double[] last_evaluation_raw = new double[20];
		int index = 0;

		// Iterating over the table to remove the rows with NANs
		for (Row row : dataTable) {
			// Extract data in the row
			Double satisfaction_levelS = row.getDouble("satisfaction_level");
			Double last_evaluationS = row.getDouble("last_evaluation");
			String salesS = row.getString("sales");
			String salaryS = row.getString("salary");

			System.out.println("The SL" + satisfaction_levelS);
			System.out.println("The LE is " + last_evaluationS);
			System.out.println("The sale is " + salesS);
			System.out.println("The salary is " + salaryS);

			// the case of (Integer & Double) size !=0
			// for the case of String length()!=0
			if (salesS.length() != 0 && salaryS.length() != 0 && satisfaction_levelS != 0 && last_evaluationS != 0) {
				// adding the filtered date set to the list.
				satisfaction_level.add(satisfaction_levelS);
				last_evaluation.add(last_evaluationS);
				sales.add(salesS);
				salary.add(salaryS);
			}

			else {

			}
			// Adding the data for Interpolation
			satisfaction_level_raw[index] = satisfaction_levelS;
			last_evaluation_raw[index] = last_evaluationS;
			index++;
		}

		// Creating columns to store the variables:
		String[] sallaryArr = salary.toArray(new String[satisfaction_level.size()]);
		StringColumn salry = StringColumn.create("salry", sallaryArr);
		// Adding the columns to the table
		Table droppedRows = Table.create("DroppedRows", salry);

		// compare newly created table from filtered data with th original data
		String tableShape2 = droppedRows.shape();
		String tableShape1 = dataTable.shape();
		System.out.println(tableShape2);
		System.out.println(tableShape);
		// Linear Interpolation
		// data over time increasing or decreasing
		// Satisfaction Level (y) depends on Last Evaluation(X)
		LinearInterpolator myLinearInterp = new LinearInterpolator();
		myLinearInterp.interpolate(satisfaction_level_raw, last_evaluation_raw);
		/**
		 * Formatting various data types
		 */

		// Getting the table structures
		System.out.println("Printing the structure of the table  loaded from my local machine");
		Table tableDataType = dataTable.structure();
		System.out.println(tableDataType);

		// Incase of Arrays with Int to double
		int[] age = { 30, 12, 14, 41, 20, 80 };
		double[] double_age = Arrays.stream(age).asDoubleStream().toArray();
	}
}
```
## Tablesaw: <a name="Kumo"></a> 
Kumo's goal is to create a powerful and user friendly Word Cloud API in Java. Kumo directly generates an image file without the need to create an applet as many other libraries do.
[Kumo](https://github.com/kennycason/kumo)
```java

public class JPanaWordCloud extends JPanel {
	public final WordCloud wordCloud;

	public JPanaWordCloud() throws IOException {
		wordCloud = generateWordCloud();
		final JLabel wordCloudLabel = new JLabel(new ImageIcon(wordCloud.getBufferedImage()));
		add(wordCloudLabel);
		repaint();
	}

	private static WordCloud generateWordCloud() throws IOException {
		final FrequencyAnalyzer fA = new FrequencyAnalyzer();
		// selecting the number of most frequency words to be visualized.
		fA.setWordFrequenciesToReturn(200);

		// load the text to a list.
		List<WordFrequency> wordFrequencies = fA
				.load(getInputStream("wordcloud.txt"));

		// specify height & width of the output.png.
		final Dimension di = new Dimension(600, 600);

		// WordCloud(Dimension)
		final WordCloud wordCloud = new WordCloud(di, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);

		// set CircleBackground with the radius= 300.
		wordCloud.setBackground(new PixelBoundryBackground(getInputStream("whale_small.png")));
		wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1),
				new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
		wordCloud.setFontScalar(new SqrtFontScalar(10, 40));

		// sort the most frequent words.
		wordCloud.build(wordFrequencies);
		return wordCloud;
	}

	private static InputStream getInputStream(final String path) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}
	public static void main(String[] args) throws IOException {
		JPanaWordCloud jWP = new JPanaWordCloud();
		final JFrame frame = new JFrame("JPanel WordCloud");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(jWP);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

```



