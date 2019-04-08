package IPL.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 4/8/2019 12:45 PM.
 *
 * @author Aayush Shrivastava
 */
public class Participant {

    private Date timeStamp;
    private String name;
    private Map<Question.QuestionType, String> predictions;
    private int totalPoints;
    private Map<Question.QuestionType, Integer> points = new HashMap<>();

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

     public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Question.QuestionType, String> getPredictions() {
        return predictions;
    }

    public void setPredictions(Map<Question.QuestionType, String> predictions) {
        this.predictions = predictions;
    }

    @Override
    public String toString() {
        return "Name : " + this.getName() + " ," +
               "Total points : " + this.getTotalPoints()+ " ," +
               "Predictions : " + this.getPredictions();
    }

    public Map<Question.QuestionType, Integer> getPoints() {
        return points;
    }

    public void setPointsForEachQuestion(Question.QuestionType questionType, int point){
        this.getPoints().put(questionType, point);
    }

    public String[] getCalculatedCSVRow(){
        return new String[]{
            this.getTimeStamp().toString(),
            this.getName(),
            String.valueOf(this.getTotalPoints()),
            String.valueOf(this.getPoints().get(Question.QuestionType.MATCH_WINNER)),
            String.valueOf(this.getPoints().get(Question.QuestionType.HIGHEST_RUN_GETTER)),
            String.valueOf(this.getPoints().get(Question.QuestionType.HIGHEST_WICKET_TAKER)),
            String.valueOf(this.getPoints().get(Question.QuestionType.MOM)),
            String.valueOf(this.getPoints().get(Question.QuestionType.RUN_RANGE)),
            String.valueOf(this.getPoints().get(Question.QuestionType.BONUS)),
        };
    }
}
