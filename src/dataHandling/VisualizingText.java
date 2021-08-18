package dataHandling;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import javax.swing.*;

public class VisualizingText extends JPanel {
	/*
	 * generate a circular Word Cloud. A tag cloud (word cloud or weighted list in
	 * visual design) is a visual representation of text data, typically used to
	 * visualize free form text. Tags are usually single words, and the importance
	 * of each tag is shown with font size or color
	 */


	public static void main(String[] args) throws IOException {
		final FrequencyAnalyzer fA = new FrequencyAnalyzer();
		// selecting the number of most frequency words to be visualized.
		fA.setWordFrequenciesToReturn(200);

		// load the text to a list.
		List<WordFrequency> wordFrequencies = fA.load("wordcloud.txt");

		// specify height & width of the output.png.
		final Dimension di = new Dimension(600, 600);

		// WordCloud(Dimension)
		final WordCloud wordCloud = new WordCloud(di, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);

		// set CircleBackground with the radius= 300.
		wordCloud.setBackground(new CircleBackground(300));
		wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1),
				new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
		wordCloud.setFontScalar(new SqrtFontScalar(10, 40));

		// sort the most frequent words.
		wordCloud.build(wordFrequencies);

		// Saving WordCloud to:
		wordCloud.writeToFile("wordCloud.png");

	}

}
