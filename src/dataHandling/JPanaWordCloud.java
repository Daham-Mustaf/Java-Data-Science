package dataHandling;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundryBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
