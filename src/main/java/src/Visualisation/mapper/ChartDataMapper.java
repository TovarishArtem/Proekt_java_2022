package src.Visualisation.mapper;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import src.Person.Person;
import src.Statistics.ModuleStatistics;
import src.Statistics.TaskStatistics;


import java.util.*;
import java.util.stream.Collectors;

public class ChartDataMapper {
    public static PieDataset createPlayerByTeamsDataset(LinkedHashMap<String, Integer> moduleStatistics) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        moduleStatistics.forEach(dataset::setValue);

        return dataset;
    }

    public static CategoryDataset createFollowersDataset(LinkedList<TaskStatistics> players) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        players.forEach(p -> dataset.setValue(p.getMaxScore(), "percent", p.getName()));
        return dataset;
    }

    public static CategoryDataset createFollowersDataset1(LinkedHashMap<Person, Integer> mapPerson ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        mapPerson.keySet().forEach(p -> dataset.setValue(mapPerson.get(p),  "percent", String.format("%s (%s)", p.getFullName() , p.getBirthDate())));
        return dataset;
    }
}
