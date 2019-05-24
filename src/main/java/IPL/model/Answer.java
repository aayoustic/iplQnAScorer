package IPL.model;

/**
 * Created on 4/8/2019 11:59 PM.
 *
 * @author Aayush Shrivastava
 */
public class Answer {
    
    private String[] answerValue;

    public Answer(String... answerValue) {
        this.answerValue = answerValue;
    }

    public String[] getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String[] answerValue) {
        this.answerValue = answerValue;
    }
}
