package IPL.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 4/8/2019 11:59 PM.
 *
 * @author Aayush Shrivastava
 */
public class Answer {
    
    private String matchWinner;
    private String highestRunGetter;
    private String highestWicketTaker;
    private String mom;
    private String runRange;
    private String bonus;

    private static boolean isSet = true;

    private static final Map<Question.QuestionType, String> ANSWERS = new HashMap<>();

    public static String get(Question.QuestionType questionType){
        if(!isSet) throw new RuntimeException("Answers not set yet!");
        return ANSWERS.get(questionType);
    }

    public void set(){
        isSet = true;
        ANSWERS.put(Question.QuestionType.MATCH_WINNER, getMatchWinner());
        ANSWERS.put(Question.QuestionType.HIGHEST_RUN_GETTER, getHighestRunGetter());
        ANSWERS.put(Question.QuestionType.HIGHEST_WICKET_TAKER, getHighestWicketTaker());
        ANSWERS.put(Question.QuestionType.MOM, getMom());
        ANSWERS.put(Question.QuestionType.RUN_RANGE, getRunRange());
        ANSWERS.put(Question.QuestionType.BONUS, getBonus());
    }

    public String getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }

    public String getHighestRunGetter() {
        return highestRunGetter;
    }

    public void setHighestRunGetter(String highestRunGetter) {
        this.highestRunGetter = highestRunGetter;
    }

    public String getHighestWicketTaker() {
        return highestWicketTaker;
    }

    public void setHighestWicketTaker(String highestWicketTaker) {
        this.highestWicketTaker = highestWicketTaker;
    }

    public String getMom() {
        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    public String getRunRange() {
        return runRange;
    }

    public void setRunRange(String runRange) {
        this.runRange = runRange;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
}
