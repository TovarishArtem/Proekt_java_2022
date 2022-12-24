package src;
import src.CSV.Parser;
import src.DataBase.DBRepository;
import src.DataBase.mapper.StudentFromDbMapper;
import src.Person.Person;
import src.Person.Student;
import src.Statistics.ModuleStatistics;
import src.Statistics.TaskStatistics;
import src.Visualisation.drawer.BarChartDrawer;
import src.Visualisation.drawer.BarChartDrawer1;
import src.Visualisation.drawer.PieChartDrawer;
import src.Vk_API.VkRepository;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> nameStudent = new ArrayList<>();
        List<String> groupStudent = new ArrayList<>();
        LinkedHashMap<String, Student> hashGruop = new LinkedHashMap<>();
        VkRepository vk = new VkRepository();

        var report = Parser.getReport("rawdata/basicprogramming_2.csv");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        for (var i : report.keySet()) {

            nameStudent.add(i.getStudentName());
            groupStudent.add(i.getGroup());

            hashGruop.put(i.getStudentName(), i);
            if (i.getStudentName().contains(name)) {
                System.out.println(i.getStudentName());

                for (var j : report.get(i)) {
                    System.out.println(j);
                }
            }
        }
        System.out.println();

        // вывод модулей
        var modules = Parser.getReport1("rawdata/basicprogramming_2.csv");
        // процент прохождения модулей
        var percentModules = Parser.getReportForVis("rawdata/basicprogramming_2.csv");

        LinkedHashMap<String, Double> nameMosules = new LinkedHashMap<>();
        var i = 0;
        for (var module : modules) {
            nameMosules.put(module.getName(), percentModules[i]);
            i++;
        }
        // на
        var min = 1000000.0;
        for (var j : nameMosules.keySet()){
            if (nameMosules.get(j) < min) {
                min = nameMosules.get(j);
                name = j;
            }
        }

        var perc = Parser.getReportForVis2("rawdata/basicprogramming_2.csv", name);

        LinkedList<TaskStatistics> task = new LinkedList<>();

        for(var p : perc.keySet()){
            task.add(new TaskStatistics(p, 545,  perc.get(p)));
        }
        var studentModule = Parser.getReportForVis3("rawdata/basicprogramming_2.csv", name);
        //  vk API
        /*var map = vk.report(nameStudent);
        vk.getReport(map);*/

        DBRepository dbRepository = new DBRepository();

        dbRepository.connect();
        /*dbRepository.dropTable();
        dbRepository.createTable();
        dbRepository.dropTable1();
        dbRepository.createTable1();

        dbRepository.saveStudents(map, hashGruop);*/
        dbRepository.close();
        var dbOrm = new DBRepository();
        var studentFromDB = new ArrayList<Person>();
        try {
            dbOrm.connect();
            studentFromDB = dbOrm.getStudents()
                    .stream()
                    .map(StudentFromDbMapper::map)
                    .collect(Collectors.toCollection(ArrayList::new));
            dbOrm.close();
        } catch (Exception throwable) {
            throwable.printStackTrace();
        }


        LinkedHashMap<Person, Integer> mapPerson = new LinkedHashMap();
        Integer[] wordsArray = new Integer[studentModule.size()];
        var listModuleStudent = studentModule.toArray(wordsArray);

        var index = 0;

        for ( var stud: studentFromDB){
            if(stud.getBirthDate() != null && !stud.getBirthDate().equals("-")) {
                mapPerson.put(stud, listModuleStudent[index]);
            }
            index++;
        }

       new PieChartDrawer("Модули", nameMosules).setVisible(true);

       new BarChartDrawer("Модуль", task, name).setVisible(true);
       new BarChartDrawer1("Модуль", mapPerson, name).setVisible(true);

    }

}
