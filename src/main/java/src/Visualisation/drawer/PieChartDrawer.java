package src.Visualisation.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;
import src.Statistics.ModuleStatistics;
import src.Visualisation.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PieChartDrawer extends JFrame {


    public PieChartDrawer(String title, LinkedHashMap<String, Integer> playerList) {
        super(title);
        setContentPane(createPlayersByTeamsPanel(playerList ));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600, 300);

        setVisible(true);
    }

    public static JPanel createPlayersByTeamsPanel(LinkedHashMap<String, Integer> playerList)
    {
        JFreeChart chart = createPieChart(ChartDataMapper.createPlayerByTeamsDataset(playerList));
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




        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.CENTER);
        t.setPaint(Color.white);
        t.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
        t.setText("Статистика модулей" );

        return chart;
    }

}
