package src.Statistics;

public class ModuleStatistics {


    private String name;
    private int score;



    private int maxScore;

    public ModuleStatistics(String name, int score , int maxScore) {
        this.name = name;
        this.score = score;
        this.maxScore = maxScore;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
