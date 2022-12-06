package src.course;

import java.util.ArrayList;
import java.util.Collection;

public class Course {

    private String nameCourse;
    private int activitiesScore;
    private int excerciseScore;
    private int homeworkScores;
    private int seminarsScores;
    private ArrayList<Module> modules;

    public Course(String name, int activitiesScore, int excerciseScore, int homeworkScores, int seminarsScores, ArrayList<Module> modules) {
        this.nameCourse = name;
        this.activitiesScore = activitiesScore;
        this.excerciseScore = excerciseScore;
        this.homeworkScores = homeworkScores;
        this.seminarsScores = seminarsScores;
        this.modules = modules;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public int getActivitiesScore() {
        return activitiesScore;
    }

    public void setActivitiesScore(int activitiesScore) {
        this.activitiesScore = activitiesScore;
    }

    public int getExcerciseScore() {
        return excerciseScore;
    }

    public void setExcerciseScore(int excerciseScore) {
        this.excerciseScore = excerciseScore;
    }

    public int getHomeworkScores() {
        return homeworkScores;
    }

    public void setHomeworkScores(int homeworkScores) {
        this.homeworkScores = homeworkScores;
    }

    public int getSeminarsScores() {
        return seminarsScores;
    }

    public void setSeminarsScores(int seminarsScores) {
        this.seminarsScores = seminarsScores;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }
}