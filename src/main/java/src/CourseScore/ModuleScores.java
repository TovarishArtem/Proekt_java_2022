package src.CourseScore;


import java.util.Collection;

public class ModuleScores {

    private Module module;
    private Collection<TaskScores> taskScores;
    private int activitiesScore;
    private int excerciseScore;
    private int homeworkScore;
    private int seminarScore;

    public ModuleScores(Module module, Collection<TaskScores> taskScores, int activitiesScore, int excerciseScore, int homeworkScore, int seminarScore) {
        this.module = module;
        this.taskScores = taskScores;
        this.activitiesScore = activitiesScore;
        this.excerciseScore = excerciseScore;
        this.homeworkScore = homeworkScore;
        this.seminarScore = seminarScore;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Collection<TaskScores> getTaskScores() {
        return taskScores;
    }

    public void setTaskScores(Collection<TaskScores> taskScores) {
        this.taskScores = taskScores;
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

    public int getHomeworkScore() {
        return homeworkScore;
    }

    public void setHomeworkScore(int homeworkScore) {
        this.homeworkScore = homeworkScore;
    }

    public int getSeminarScore() {
        return seminarScore;
    }

    public void setSeminarScore(int seminarScore) {
        this.seminarScore = seminarScore;
    }
}
