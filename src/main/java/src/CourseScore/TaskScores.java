package src.CourseScore;


import src.course.Task;

public class TaskScores {
    Integer uid;
    String name;
    Integer score;
    public TaskScores(Task task) {

        this.name = task.getName();
        this.score = 0;
    }

    public Integer getUid() {
        return this.uid;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        return  this.score;
    }
}