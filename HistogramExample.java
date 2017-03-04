import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

/**
 *export CLASSPATH=$CLASSPATH:/usr/share/jfreechart-1.0.19/lib/jfreechart-1.0.19.jar:/usr/share/jfreechart-1.0.19/lib/jcommon-1.0.23.jar
 */
 
/**
 * This demo shows a simple bar chart created using the {@link XYSeriesCollection} dataset.
 *
 */
public class HistogramExample extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public HistogramExample(final String title,final double[] hist,final String s) {
        super(title);
        IntervalXYDataset dataset = createDataset(hist);
        JFreeChart chart = createChart(dataset,s);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private IntervalXYDataset createDataset(final double[] hist) {
        final XYSeries series = new XYSeries("normalised values");
        int i=0;
        for(double d:hist){
                series.add(i, d);
                i++;
        }
        final XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A sample chart.
     */
    private JFreeChart createChart(IntervalXYDataset dataset,String s) {
        final JFreeChart chart = ChartFactory.createXYBarChart(
            "Histogram Plot: "+s,
            "Keyword index", 
            false,
            "frequency", 
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        XYPlot plot = (XYPlot) chart.getPlot();
        final IntervalMarker target = new IntervalMarker(400.0, 700.0);
        //target.setLabel("Target Range");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(222, 222, 255, 128));
        plot.addRangeMarker(target, Layer.BACKGROUND);
        return chart;    
    }
    
    public static void main(final double[] args,String s) {
        final HistogramExample demo = new HistogramExample("Histogram Plot",args,s);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
