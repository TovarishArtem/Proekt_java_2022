package src.course;

import java.util.ArrayList;


public class Module {

    private String name;
    private ArrayList<Task> tasks;
    private int activitesScore;
    private int excerciseScore;
    private int homeworkScore;
    private int seminarsScore;

    public Module(String name, ArrayList<Task> tasks, int activitesScore, int excerciseScore, int homeworkScore, int seminarsScore) {
        this.name = name;
        this.tasks = tasks;
        this.activitesScore = activitesScore;
        this.excerciseScore = excerciseScore;
        this.homeworkScore = homeworkScore;
        this.seminarsScore = seminarsScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getActivitesScore() {
        return activitesScore;
    }

    public void setActivitesScore(int activitesScore) {
        this.activitesScore = activitesScore;
    }

    public int getExcerciseScore() {
        return excerciseScore;
    }

    public void setExcerciseScore(int excerciseScore) {
        this.excerciseScore = excerciseScore;
    }

    public int getHomeworkScore() {
        return homeworkScore;
    }

    public void setHomeworkScore(int homeworkScore) {
        this.homeworkScore = homeworkScore;
    }

    public int getSeminarsScore() {
        return seminarsScore;
    }

    public void setSeminarsScore(int seminarsScore) {
        this.seminarsScore = seminarsScore;
    }
}