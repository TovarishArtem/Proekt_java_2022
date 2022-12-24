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
import src.Person.Person;

import src.Visualisation.mapper.ChartDataMapper;

import javax.swing.*;
import java.awt.*;

import java.util.LinkedHashMap;


public class BarChartDrawer1 extends JFrame {
    public BarChartDrawer1(String title, LinkedHashMap<Person, Integer> mapPerson, String nameModule) {
        super(title);
        setContentPane(createPlayersByTeamsPanel(mapPerson, nameModule));
        setExtendedState(JFrame.HEIGHT);

        setSize(1920, 1080);
    }

    public static JPanel createPlayersByTeamsPanel(LinkedHashMap<Person, Integer> mapPerson,  String nameModule)
    {
        JFreeChart chart = createBarChart(ChartDataMapper.createFollowersDataset1(mapPerson), nameModule);
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));


        return new ChartPanel(chart);
    }

    private static JFreeChart createBarChart(CategoryDataset dataset,  String nameModule) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Модуль: " + nameModule ,
                "Имя студента",
                "Процентов сделано",
                dataset,

                PlotOrientation.HORIZONTAL,
                false,
                false,
                false
        );
        chart.setBackgroundPaint(Color.getHSBColor(94, 89, 87));


        chart.setBorderPaint(Color.getHSBColor(46, 31, 26));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.getHSBColor(159, 49, 46));
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.getHSBColor(200, 74, 0)); // цвет линии



        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);


        GradientPaint gp = new GradientPaint(1.0f, 0.0f, Color.getHSBColor(189, 74, 177), 2.0f, 0.0f, new Color(200, 100, 0));
        renderer.setSeriesPaint(0, gp);

        TextTitle t = chart.getTitle();
        t.setPaint(Color.BLACK);
        t.setFont(new Font("Comic Sans MS", Font.BOLD, 26));

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(new Font("Comic Sans MS", Font.BOLD, 26));
        domainAxis.setTickLabelFont(new Font("Comic Sans MS", Font.BOLD, 10));
        domainAxis.setAxisLinePaint(Color.BLACK);



        return chart;
    }
}