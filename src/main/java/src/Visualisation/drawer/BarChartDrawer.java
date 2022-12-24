
package src.Visualisation.drawer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import src.Statistics.TaskStatistics;
import src.Visualisation.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BarChartDrawer extends JFrame {
    public BarChartDrawer(String title, LinkedList<TaskStatistics> playerList,  String nameModule) {
        super(title);
        setContentPane(createTaskPanel(playerList, nameModule));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(600, 300);
        setBounds(10, 20, 760, 520);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static JPanel createTaskPanel(LinkedList<TaskStatistics> playerList, String nameModule)
    {
        JFreeChart chart = createBarChart(ChartDataMapper.createScoreTasksModuleDataset(playerList),nameModule);
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        return new ChartPanel(chart);
    }

    private static JFreeChart createBarChart(CategoryDataset dataset,  String nameModule) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Модуль: " + nameModule ,
                "Название упражнения",
                " % студентов решивших ",
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                false,
                false
        );

        chart.setBackgroundPaint(Color.getHSBColor(92, 72, 20));
        chart.getTitle().setPaint(Color.BLACK);
        chart.setBorderPaint(Color.getHSBColor(172, 214, 2));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.getHSBColor(92, 72, 20));

        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.getHSBColor(200, 74, 0));


        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);


        GradientPaint gp = new GradientPaint(1.0f, 0.0f, Color.getHSBColor(189, 74, 177), 2.0f, 0.0f, new Color(200, 100, 0));
        renderer.setSeriesPaint(0, gp);

        TextTitle t = chart.getTitle();

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("Comic Sans MS", Font.BOLD, 26));
        domainAxis.setTickLabelFont(new Font("Comic Sans MS", Font.BOLD, 18));
        domainAxis.setAxisLinePaint(Color.BLACK);
        t.setPaint(Color.BLACK);
        t.setFont(new Font("Comic Sans MS", Font.BOLD, 26));

        return chart;
    }
}
