package src.Statistics;

public class ModuleStatistics {


    private String name;
    private Double score;



    private Double maxScore;

    public ModuleStatistics(String name, Double score , Double maxScore) {
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
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }
}
