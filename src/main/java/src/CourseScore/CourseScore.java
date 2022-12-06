package src.CourseScore;

import src.course.Course;

import java.util.ArrayList;
import java.util.Collection;

public class CourseScore{
    private Course course;
    private final int scoreOfActivities;
    private final int scoreOfExcersises;
    private final int scoreOfHomeWorks;
    private final int scoreOfSeminars;
    private Collection<ModuleScores> moduleScores;

    CourseScore(Course course, int scoreOfActivities, int scoreOfExcersises, int scoreOfHomeworks, int scoreOfSeminars, Collection<ModuleScores> moduleScores){
        this.course = course;
        this.scoreOfActivities = scoreOfActivities;
        this.scoreOfExcersises = scoreOfExcersises;
        this.scoreOfHomeWorks = scoreOfHomeworks;
        this.scoreOfSeminars = scoreOfSeminars;
        this.moduleScores = moduleScores;
    }

    public Course getNameCourse() {
        return course;
    }

    public int getScoreOfActivities() {
        return scoreOfActivities;
    }

    public int getScoreOfExcersises() {
        return scoreOfExcersises;
    }

    public int getScoreOfHomeWorks() {
        return scoreOfHomeWorks;
    }

    public int getScoreOfSeminars() {
        return scoreOfSeminars;
    }

    public Collection<ModuleScores> getModuleScores() {
        return moduleScores;
    }

    public String toString(){
        String string = course + " " + scoreOfActivities  + " " + scoreOfExcersises + " " + scoreOfHomeWorks + " " + scoreOfSeminars + " " + moduleScores;
        return string;
    }
}
