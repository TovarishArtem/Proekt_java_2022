package src.CSV;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import src.Person.Student;
import src.Vk_API.VkRepository;
import src.course.Course;
import src.course.Task;
import src.course.Module;
import src.course.TypeTask;
import src.Person.Person;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Parser {

    public static LinkedHashMap<String, ArrayList<String>> getReport(String path) throws IOException, ClientException, ApiException {
        Scanner sc = getScanner(path);

        var headersOfModules = sc.nextLine().split(";"); // название модулей
        var headersOfExercises = sc.nextLine().split(";"); // название упражнений и домашних работ
        var headersOfMaxScores = sc.nextLine().split(";"); // макс. значения

        LinkedHashMap<String, ArrayList<String>> student = new LinkedHashMap<>();
        while (sc.hasNextLine()) {
            var headersOfScoresStudent = sc.nextLine().split(";"); // имя, оценки студента

            var name = headersOfScoresStudent[0];

            Student stud = new Student(name);


            getTasksLists(headersOfExercises, headersOfScoresStudent, stud, student);
            getModuleList(headersOfModules, headersOfExercises, headersOfMaxScores);
        }
        VkRepository vkRepository = new VkRepository();

        return student;

    }
    public static  ArrayList<Module> getReport1(String path) throws IOException, ClientException, ApiException {
        Scanner sc = getScanner(path);

        var headersOfModules = sc.nextLine().split(";"); // название модулей
        var headersOfExercises = sc.nextLine().split(";"); // название упражнений и домашних работ
        var headersOfMaxScores = sc.nextLine().split(";"); // макс. значения

        ArrayList<Module> getModule = new ArrayList<Module>();

        getModule = getModuleList(headersOfModules, headersOfExercises, headersOfMaxScores);


        return getModule;

    }
    private static Scanner getScanner(String path) throws IOException {
        String content = Files.readString(Path.of(path), StandardCharsets.UTF_8);
        return new Scanner(content);
    }

    /*private static ArrayList<String> getModulesList(String[] headers2) {
        var modules = new ArrayList<String>();
        for (int i = 2; i < headers2.length; i ++) {
            if(!headers2[i].equals("")) {
                modules.add(headers2[i]);
            }
        }
        return modules;
    }*/

    private static LinkedHashMap<String, ArrayList<String>> getTasksLists(String[] headers2, String[] headers3, Student stud, LinkedHashMap<String, ArrayList<String>> student ){
        ArrayList<String> taskslist = new ArrayList<String>();

        for(int i = 10; i < headers2.length;i++){
            if (headers2[i].contains(":")) {
                String name = headers2[i].split(":")[1];
                TypeTask tasktype = new TypeTask(headers2[i]);

                    Task task = new Task(tasktype.getNameTask(), tasktype.getTypeTask(), Integer.parseInt(headers3[i]));
                    String str = String.format ("%s:  - %s", task.getName(), task.getMaxScore());
                    taskslist.add(str);
            }
        }
        student.put(stud.getStudentName(), taskslist);

        return student;
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

    private static Course getCourse(String[] modules, String[] exercises, String[] scores){
        ArrayList<Module> moduleList = getModuleList(modules, exercises, scores);
        String name = "Основы программирования на C# часть 1";

        var maxscoreOfActivities = Integer.parseInt(scores[2]);
        var maxscoreOfExcersises = Integer.parseInt(scores[3]);
        var maxscoreOfHomeWorks = Integer.parseInt(scores[4]);
        var maxscoreOfSeminars = Integer.parseInt(scores[5]);


        return new Course(name, maxscoreOfActivities, maxscoreOfExcersises, maxscoreOfHomeWorks, maxscoreOfSeminars, moduleList);
    }

}