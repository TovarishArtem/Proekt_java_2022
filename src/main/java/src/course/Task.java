package src.course;

public class Task {
    private String name;
    private enumType taskType;
    private int maxScore;

    public Task(String name, enumType taskType, int maxScore) {
        this.name = name;
        this.taskType = taskType;
        this.maxScore = maxScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public enumType getTaskType() {
        return taskType;
    }

    public void setTaskType(enumType taskType) {
        this.taskType = taskType;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}