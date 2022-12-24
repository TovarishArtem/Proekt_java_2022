package src.Visualisation.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;
import src.Visualisation.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;

public class PieChartDrawer extends JFrame {


    public PieChartDrawer(String title, LinkedHashMap<String, Double> nameModule) {
        super(title);
        setContentPane(createModulesPanel(nameModule ));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600, 300);

        setVisible(true);
    }

    public static JPanel createModulesPanel(LinkedHashMap<String, Double> nameModule)
    {
        JFreeChart chart = createPieChart(ChartDataMapper.createModelsDataset(nameModule));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    private static JFreeChart createPieChart(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Статистика решений модулей курса: " ,
                dataset,
                true,
                true,
                false
        );




        chart.setBackgroundPaint(new Color(15, 8, 6));
        chart.getTitle().setPaint(Color.BLACK);


        PiePlot plot4 = (PiePlot) chart.getPlot();

        plot4.setSimpleLabels(false);

        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1}%  ", new DecimalFormat("#.##"), new DecimalFormat("0.00%"));
        System.out.println();
        plot4.setLabelGenerator(gen);


        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.CENTER);
        t.setPaint(Color.white);
        t.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        t.setText("Статистика модулей" );

        return chart;
    }

}
