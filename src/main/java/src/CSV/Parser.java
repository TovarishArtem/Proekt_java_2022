package src.CSV;


import com.j256.ormlite.stmt.query.In;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import src.Person.Student;
import src.Statistics.ModuleStatistics;
import src.Statistics.TaskStatistics;
import src.Vk_API.VkRepository;

import src.course.Task;
import src.course.Module;
import src.course.TypeTask;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parser {

    public static LinkedHashMap<Student, ArrayList<String>> getReport(String path) throws IOException, ClientException, ApiException {
        Scanner sc = getScanner(path);

        var headersOfModules = sc.nextLine().split(";"); // название модулей
        var headersOfExercises = sc.nextLine().split(";"); // название упражнений и домашних работ
        var headersOfMaxScores = sc.nextLine().split(";"); // макс. значения

        LinkedHashMap<Student, ArrayList<String>> student = new LinkedHashMap<>();

        while (sc.hasNextLine()) {
            var headersOfScoresStudent = sc.nextLine().split(";"); // имя, оценки студента

            var name = headersOfScoresStudent[0];
            var group =  headersOfScoresStudent[1];
            Student stud = new Student(name, group);

            getTasksLists(headersOfExercises, headersOfScoresStudent, stud, student);

        }

        return student;

    }
    // вывод модулей курса
    public static  ArrayList<Module> getReport1(String path) throws IOException, ClientException, ApiException {
        Scanner sc = getScanner(path);

        var headersOfModules = sc.nextLine().split(";"); // название модулей
        var headersOfExercises = sc.nextLine().split(";"); // название упражнений и домашних работ
        var headersOfMaxScores = sc.nextLine().split(";"); // макс. значения

        ArrayList<Module> getModule = new ArrayList<Module>();

        getModule = getModuleList(headersOfModules, headersOfExercises, headersOfMaxScores);
        for (var i : getModule){
            System.out.println(i.getName());

            System.out.println(i.toString());
            for (var task : i.getTasks()){
                System.out.println(task.toString());
            }
       }

        return getModule;
    }
    public static  Double[] getReportForVis(String path) throws IOException, ClientException, ApiException {
        Scanner sc = getScanner(path);

        var headersOfModules = sc.nextLine().split(";"); // название модулей
        var headersOfExercises = sc.nextLine().split(";"); // название упражнений и домашних работ
        var headersOfMaxScores = sc.nextLine().split(";"); // макс. значения

        LinkedHashMap< List<Integer>, ArrayList<ModuleStatistics>>  moduleforVis = new LinkedHashMap<>();

         ArrayList<ModuleStatistics> module = new ArrayList<>();


        List<Double> list = new ArrayList<>();
        List<Double> listSum = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            listSum.add(0.0);
        }

        Double[] list3 = null;
        // преобразуем лист в массив
        Double[] listSum2 = new Double[listSum.size()];
        var listSum3 = listSum.toArray(listSum2);
        List<Double> list1 = new ArrayList<>();
        var index = 0;
        while (sc.hasNextLine()) {
            var headersOfScoresModulesStudent = sc.nextLine().split(";"); // имя, оценки студента

            var name = headersOfScoresModulesStudent[0];
            var group = headersOfScoresModulesStudent[1];
            Student stud = new Student(name, group);
            module = getModuleforVis(headersOfModules, headersOfExercises, headersOfScoresModulesStudent, headersOfMaxScores, stud);

            // получение максимальных баллов
            if (index == 0){
                for(var m : module){
                    list1.add(m.getMaxScore());

                }
            }
            Double[] wordsArray1 = new Double[list1.size()];
            list3 = list1.toArray(wordsArray1);

            for(var m : module){
                    list.add(m.getScore());
            }
            // преобразуем лист в массив
            Double[] wordsArray = new Double[list.size()];
            var list2 = list.toArray(wordsArray);

            for (var i = 0; i < listSum3.length; i++){
                for (var j = 0; j < list2.length; j++ ){
                    if (i == j){
                        listSum3[i] = (listSum3[i] + list2[j]); // сумма баллов всех студентов за этот модуль
                    }
                }
            }
            list.clear();
            index++;
            }
        for (var i = 0; i < list3.length; i++){
            listSum3[i] = (listSum3[i] * 100) /( list3[i] * index);
        }


        return listSum3;
    }

    public static  HashMap<String, Integer> getReportForVis2(String path, String name1) throws IOException, ClientException, ApiException {
        Scanner sc = getScanner(path);

        var headersOfModules = sc.nextLine().split(";"); // название модулей
        var headersOfExercises = sc.nextLine().split(";"); // название упражнений и домашних работ
        var headersOfMaxScores = sc.nextLine().split(";"); // макс. значения

        ArrayList<TaskStatistics> listTasks = new ArrayList<>();

        Integer[] list3 = null;
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> listSum = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            listSum.add(0);
        }

        Integer[] listSum2 = new Integer[listSum.size()];
        var listSum3 = listSum.toArray(listSum2);
        LinkedHashSet<String> unique = new LinkedHashSet<>();
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        var index = 0;
        while (sc.hasNextLine()) {
            var headersOfScoresModulesStudent = sc.nextLine().split(";"); // имя, оценки студента


            var name = headersOfScoresModulesStudent[0];
            var group = headersOfScoresModulesStudent[1];
            Student stud = new Student(name, group);


            listTasks = getModuleforVis2(headersOfModules, headersOfExercises, headersOfScoresModulesStudent, headersOfMaxScores, stud, name1);
            for(var task : listTasks){
                list.add(task.getScore());
                unique.add(task.getName()); // лист названий упражнений
            }

            // преобразую  листы в массивы
            Integer[] wordsArray = new Integer[list.size()];
            var list2 = list.toArray(wordsArray);

            Integer[] wordsArray1 = new Integer[list1.size()];
            list3 = list1.toArray(wordsArray1);
            var count = 1;
            // сумма решивших упражнений студента
                for (var j = 0; j < list2.length; j++ ){
                    if (list2[j] > 0){
                        listSum3[j] = (listSum3[j] + count) ;
                    }
            }
            index++;
            list.clear();

        }
        var i = 0;
            for (var title: unique){

                map.put(title, listSum3[i] * 100 / index ); // (название упр. ,  процентное соотношение  кол-во решивших  от всех студентов)
                i++;
            }
        return map;

    }
    public static  LinkedList< Integer> getReportForVis3(String path, String name1) throws IOException, ClientException, ApiException {
        Scanner sc = getScanner(path);

        var headersOfModules = sc.nextLine().split(";"); // название модулей
        var headersOfExercises = sc.nextLine().split(";"); // название упражнений и домашних работ
        var headersOfMaxScores = sc.nextLine().split(";"); // макс. значения

        LinkedList< Integer> scoreList = new LinkedList<>();

        while (sc.hasNextLine()) {
            var headersOfScoresModulesStudent = sc.nextLine().split(";"); // имя, оценки студента

            var name = headersOfScoresModulesStudent[0];
            var group = headersOfScoresModulesStudent[1];
            Student stud = new Student(name, group);
            var  scoreOfStudent = getModuleforVis3(headersOfModules, headersOfExercises, headersOfScoresModulesStudent, headersOfMaxScores, stud, name1);
            assert scoreOfStudent != null;
            scoreList.add(scoreOfStudent.getScore() * 100 / scoreOfStudent.getMaxScore()); // на сколько процентов решен модуль
        }

        return scoreList;

    }

    private static Scanner getScanner(String path) throws IOException {
        String content = Files.readString(Path.of(path), StandardCharsets.UTF_8);
        return new Scanner(content);
    }


    private static LinkedHashMap<Student, ArrayList<String>> getTasksLists( String[] headers2, String[] headersOfScoresStudent, Student stud, LinkedHashMap<Student, ArrayList<String>> student ){
        ArrayList<String> taskslist = new ArrayList<String>();

        for(int i = 10; i < headers2.length;i++){
            if (headers2[i].contains(":")) {
                String name = headers2[i].split(":")[1];
                TypeTask tasktype = new TypeTask(headers2[i]);

                    Task task = new Task(tasktype.getNameTask(), tasktype.getTypeTask(), Integer.parseInt(headersOfScoresStudent[i]));
                    String str = String.format ("%s  - %s", task.getName(), task.getMaxScore());
                    taskslist.add(str);
            }
        }
        student.put(stud, taskslist);

        return student;
    }
    private static ArrayList<ModuleStatistics> getModuleforVis(String[] headers,String[] headersOfExercises,  String[] headersOfScoresModulesStudent, String[] headersOfMaxScores, Student student  ){
        double maxscoreOfExercises = 0;
        double maxscoreOfActivities = 0;
        double maxScoreOfSeminars = 0;
        double maxScoreOfHomeworks = 0;
        double maxScoreOfActivitiesSudent = 0;
        double maxScoreOfHomeworksSudent = 0;
        double maxscoreOfExercisesSudent = 0;
        double maxScoreOfSeminarsStudent = 0;

        LinkedHashMap< ArrayList<ModuleStatistics>, ArrayList<ModuleStatistics>> moduleforVis = new LinkedHashMap<>();
        ArrayList<ModuleStatistics> modules = new ArrayList<>();

        int countModule = 8;
        Integer i = 0;
        String nameModule = null;
        for (int j = 7; j < headersOfExercises.length; j++){
            ArrayList<Task> tasks = new ArrayList<>();

            while (!(headersOfExercises[j].equals("Сем"))){

                if (headersOfExercises[j].equals("Акт")) {
                    maxScoreOfActivitiesSudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                    maxscoreOfActivities = Integer.parseInt(headersOfMaxScores[j]);

                }

                if (headersOfExercises[j].equals("ДЗ")) {
                    maxScoreOfHomeworksSudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                    maxScoreOfHomeworks = Integer.parseInt(headersOfMaxScores[j]);
                }
                if (headersOfExercises[j].equals("Упр")) {
                    maxscoreOfExercisesSudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                    maxscoreOfExercises = Integer.parseInt(headersOfMaxScores[j]);
                }
                j++;
            }

            if (headersOfExercises[j].equals("Сем")) {
                countModule = j + 1;
                if (i == 0){
                    maxScoreOfSeminars = Double.parseDouble(headersOfMaxScores[j]);
                    maxScoreOfSeminarsStudent = Double.parseDouble(headersOfScoresModulesStudent[j]);
                    nameModule = headers[countModule];
                    i++;
                    continue;
                }

                if (countModule > headers.length ){

                    countModule = headers.length - 1;
                }
                Double score = maxscoreOfExercisesSudent + maxScoreOfActivitiesSudent + maxScoreOfHomeworksSudent + maxScoreOfSeminarsStudent;
                Double max = maxscoreOfExercises + maxscoreOfActivities + maxScoreOfHomeworks + maxScoreOfSeminars;
                ModuleStatistics module = new ModuleStatistics(nameModule , score, max );
                modules.add(module);


                maxScoreOfSeminarsStudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                nameModule = headers[countModule];

            }

        }
        return modules;
    }
    private static ArrayList<TaskStatistics> getModuleforVis2(String[] headers,String[] headersOfExercises,  String[] headersOfScoresModulesStudent, String[] headersOfMaxScores, Student student, String name  ){
        int countModule = 8;
        Integer i = 0;
        String nameModule = null;
        ArrayList<TaskStatistics> tasks = new ArrayList<>();
        for (int j = 7; j < headers.length; j++){

            while (!(headersOfExercises[j].equals("Сем"))){
                TypeTask typetask = new TypeTask(headersOfExercises[j]);


                if (!((typetask.getNameTask()).equals("Ошибка"))){
                    TaskStatistics task = new TaskStatistics(headersOfExercises[j], Integer.parseInt(headersOfScoresModulesStudent[j]), Integer.parseInt(headersOfMaxScores[j]));
                    tasks.add(task);
                }
                j++;
            }

            if (headersOfExercises[j].equals("Сем")) {
                countModule = j + 1;
                if (i == 0){
                    nameModule = headers[countModule];
                    i++;
                    continue;
                }

                if (countModule > headers.length ){

                    countModule = headers.length - 1;
                }
                if (nameModule.equals(name)){
                    break;
                }

                nameModule = headers[countModule];
                tasks.clear();
            }
        }

        return tasks;
    }
    private static TaskStatistics getModuleforVis3(String[] headers,String[] headersOfExercises,  String[] headersOfScoresModulesStudent, String[] headersOfMaxScores, Student student, String name  ){
        int maxscoreOfExercises = 0;
        int maxScoreOfActivities = 0;
        int maxScoreOfSeminars = 0;
        int maxScoreOfHomeworks = 0;
        int maxScoreOfActivitiesSudent = 0;
        int maxScoreOfHomeworksSudent = 0;
        int maxscoreOfExercisesSudent = 0;
        int maxScoreOfSeminarsStudent = 0;

        LinkedHashMap< ArrayList<ModuleStatistics>, ArrayList<ModuleStatistics>> moduleforVis = new LinkedHashMap<>();
        ArrayList<ModuleStatistics> modules = new ArrayList<>();

        int countModule = 8;
        Integer i = 0;
        String nameModule = null;
        for (int j = 7; j < headersOfExercises.length; j++){
            ArrayList<Task> tasks = new ArrayList<>();

            while (!(headersOfExercises[j].equals("Сем"))){

                if (headersOfExercises[j].equals("Акт")) {
                    maxScoreOfActivitiesSudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                    maxScoreOfActivities = Integer.parseInt(headersOfMaxScores[j]);

                }

                if (headersOfExercises[j].equals("ДЗ")) {
                    maxScoreOfHomeworksSudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                    maxScoreOfHomeworks = Integer.parseInt(headersOfMaxScores[j]);
                }
                if (headersOfExercises[j].equals("Упр")) {
                    maxscoreOfExercisesSudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                    maxscoreOfExercises = Integer.parseInt(headersOfMaxScores[j]);
                }
                j++;
            }

            if (headersOfExercises[j].equals("Сем")) {
                countModule = j + 1;
                if (i == 0){
                    maxScoreOfSeminars = Integer.parseInt(headersOfMaxScores[j]);
                    maxScoreOfSeminarsStudent = Integer.parseInt(headersOfScoresModulesStudent[j]);
                    nameModule = headers[countModule];
                    i++;
                    continue;
                }

                if (countModule > headers.length ){

                    countModule = headers.length - 1;
                }
                if (nameModule.equals(name)){
                    var max = maxscoreOfExercises + maxScoreOfSeminars + maxScoreOfActivities + maxScoreOfHomeworks;
                    var maxStudent = maxscoreOfExercisesSudent + maxScoreOfActivitiesSudent + maxScoreOfHomeworksSudent + maxScoreOfSeminarsStudent;
                    TaskStatistics task = new TaskStatistics("", maxStudent , max);
                    return task;
                }
                nameModule = headers[countModule];
                maxScoreOfSeminars = Integer.parseInt(headersOfMaxScores[j]);

            }

        }
        return null;
    }

    public static ArrayList getModuleList(String[] headers, String[] headers2, String[] headers3) {

        int maxscoreOfExercises = 0;
        int maxScoreOfActivities = 0;
        int maxScoreOfSeminars = 0;
        int maxScoreOfHomeworks = 0;

        ArrayList<Module> modules = new ArrayList<>();
        int countModule = 8;
        Integer i = 0;
        String nameModule = null;
        for (int j = 7; j < headers.length; j++){
            ArrayList<Task> tasks = new ArrayList<>();

            while (!(headers2[j].equals("Сем"))){
                TypeTask typetask = new TypeTask(headers2[j]);

                if (headers2[j].equals("Акт")) {
                    maxScoreOfActivities = Integer.parseInt(headers3[j]);
                }


                if (headers2[j].equals("ДЗ")) {
                    maxScoreOfHomeworks = Integer.parseInt(headers3[j]);
                }
                if (headers2[j].equals("Упр")) {
                    maxscoreOfExercises = Integer.parseInt(headers3[j]);
                }

                if (!((typetask.getNameTask()).equals("Ошибка"))){
                    Task task = new Task(typetask.getNameTask(), typetask.getTypeTask(), Integer.parseInt(headers3[j]));
                    tasks.add(task);
                }
                j++;
            }

            if (headers2[j].equals("Сем")) {
                countModule = j + 1;
                if (i == 0){
                    maxScoreOfSeminars = Integer.parseInt(headers3[j]);
                    nameModule = headers[countModule];
                    i++;
                    continue;
                }

                if (countModule > headers.length ){

                    countModule = headers.length - 1;
                }
                Module module = new Module(nameModule , tasks, maxScoreOfActivities, maxscoreOfExercises, maxScoreOfHomeworks, maxScoreOfSeminars);

                modules.add(module);
                maxScoreOfSeminars = Integer.parseInt(headers3[j]);
                nameModule = headers[countModule];

            }
        }

        return modules;
    }


}