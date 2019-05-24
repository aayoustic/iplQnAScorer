package IPL.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created on 4/8/2019 12:45 PM.
 *
 * @author Aayush Shrivastava
 */
public class Participant {

    private Date timeStamp;
    private String email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Name : " + this.getEmail() + " ," +
               "Total points : " + this.getTotalPoints()+ " ," +
               "Predictions : " + this.getPredictions();
    }

    public Map<Question.QuestionType, Integer> getPoints() {
        return points;
    }

    public void setPointsForEachQuestion(Question.QuestionType questionType, int point){
        this.getPoints().put(questionType, point);
    }
}
