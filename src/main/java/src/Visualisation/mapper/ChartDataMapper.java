package src.Visualisation.mapper;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import src.Person.Person;
import src.Statistics.TaskStatistics;


import java.util.*;

public class ChartDataMapper {
    public static PieDataset createModelsDataset(LinkedHashMap<String, Double> moduleStatistics) {

        DefaultPieDataset dataset = new DefaultPieDataset();
        moduleStatistics.forEach(dataset::setValue);

        return dataset;
    }

    public static CategoryDataset createScoreTasksModuleDataset(LinkedList<TaskStatistics> tasksModule) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        tasksModule.forEach(p -> dataset.setValue(p.getMaxScore(), "percent", p.getName()));
        return dataset;
    }

    public static CategoryDataset createStudentByTasksModuleDataset(LinkedHashMap<Person, Integer> mapPerson ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        mapPerson.keySet().forEach(p -> dataset.setValue(mapPerson.get(p),  "percent", String.format("%s (%s)", p.getFullName() , p.getBirthDate())));
        return dataset;
    }
}
