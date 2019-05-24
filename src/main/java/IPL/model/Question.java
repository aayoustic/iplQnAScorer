package IPL.model;

import IPL.form.AnswerForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 4/8/2019 1:28 PM.
 *
 * @author Aayush Shrivastava
 */
public class Question {

    public interface QuestionType{}

    private static boolean isAnswerSet = false;

    public enum StaticQuestions implements QuestionType {
        MATCH_WINNER, HIGHEST_RUN_GETTER, HIGHEST_WICKET_TAKER, MOM, RUN_RANGE
    }

    public enum BonusQuestions implements QuestionType {
        BONUS1, BONUS2, BONUS3, BONUS4, BONUS5, BONUS6, BONUS7,
        BONUS8, BONUS9, BONUS10, BONUS11, BONUS12, BONUS13, BONUS14,
        BONUS15, BONUS16, BONUS17, BONUS18, BONUS19, BONUS20, BONUS21,
        BONUS22, BONUS23, BONUS24;
    }

    private static final Map<QuestionType, Integer> POINTS = new HashMap<>();
    static {
        POINTS.put(StaticQuestions.MATCH_WINNER, 10);
        POINTS.put(StaticQuestions.HIGHEST_RUN_GETTER, 20);
        POINTS.put(StaticQuestions.HIGHEST_WICKET_TAKER, 20);
        POINTS.put(StaticQuestions.MOM, 25);
        POINTS.put(StaticQuestions.RUN_RANGE, 15);
        POINTS.put(BonusQuestions.BONUS1, 1);
        POINTS.put(BonusQuestions.BONUS2, 10);
        POINTS.put(BonusQuestions.BONUS3, 5);
        POINTS.put(BonusQuestions.BONUS4, 10);
        POINTS.put(BonusQuestions.BONUS5, 5);
        POINTS.put(BonusQuestions.BONUS6, 15);
        POINTS.put(BonusQuestions.BONUS7, 10);
        POINTS.put(BonusQuestions.BONUS8, 30);
        POINTS.put(BonusQuestions.BONUS9, 10);
        POINTS.put(BonusQuestions.BONUS10, 10);
        POINTS.put(BonusQuestions.BONUS11, 10);
        POINTS.put(BonusQuestions.BONUS12, 10);
        POINTS.put(BonusQuestions.BONUS13, 10);
        POINTS.put(BonusQuestions.BONUS14, 10);
        POINTS.put(BonusQuestions.BONUS15, 10);
        POINTS.put(BonusQuestions.BONUS16, 10);
        POINTS.put(BonusQuestions.BONUS17, 10);
        POINTS.put(BonusQuestions.BONUS18, 10);
        POINTS.put(BonusQuestions.BONUS19, 10);
        POINTS.put(BonusQuestions.BONUS20, 10);
        POINTS.put(BonusQuestions.BONUS21, 10);
        POINTS.put(BonusQuestions.BONUS22, 10);
        POINTS.put(BonusQuestions.BONUS23, 10);
        POINTS.put(BonusQuestions.BONUS24, 56);
    }

    public static Integer getPoints(QuestionType questionType){
        return POINTS.get(questionType);
    }

    private static final Map<QuestionType, Answer> ANSWERS = new HashMap<>();

    public static void setAnswers(AnswerForm answers){
        isAnswerSet = true;
        ANSWERS.put(StaticQuestions.MATCH_WINNER, new Answer(answers.getMatchWinner()));
        ANSWERS.put(StaticQuestions.MOM, new Answer(answers.getMom()));
        ANSWERS.put(StaticQuestions.RUN_RANGE, new Answer(answers.getRunRange()));
        ANSWERS.put(StaticQuestions.HIGHEST_RUN_GETTER, new Answer(answers.getHighestRunGetter()));
        ANSWERS.put(StaticQuestions.HIGHEST_WICKET_TAKER, new Answer(answers.getHighestWicketTaker()));
        ANSWERS.put(BonusQuestions.BONUS1, new Answer(answers.getBonus().getBonus1()));
        ANSWERS.put(BonusQuestions.BONUS2, new Answer(answers.getBonus().getBonus2()));
        ANSWERS.put(BonusQuestions.BONUS3, new Answer(answers.getBonus().getBonus3()));
        ANSWERS.put(BonusQuestions.BONUS4, new Answer(answers.getBonus().getBonus4()));
        ANSWERS.put(BonusQuestions.BONUS5, new Answer(answers.getBonus().getBonus5()));
        ANSWERS.put(BonusQuestions.BONUS6, new Answer(answers.getBonus().getBonus6()));
        ANSWERS.put(BonusQuestions.BONUS7, new Answer(answers.getBonus().getBonus7()));
        ANSWERS.put(BonusQuestions.BONUS8, new Answer(answers.getBonus().getBonus8()));
        ANSWERS.put(BonusQuestions.BONUS9, new Answer(answers.getBonus().getBonus9()));
        ANSWERS.put(BonusQuestions.BONUS10, new Answer(answers.getBonus().getBonus10()));
        ANSWERS.put(BonusQuestions.BONUS11, new Answer(answers.getBonus().getBonus11()));
        ANSWERS.put(BonusQuestions.BONUS12, new Answer(answers.getBonus().getBonus12()));
        ANSWERS.put(BonusQuestions.BONUS13, new Answer(answers.getBonus().getBonus13()));
        ANSWERS.put(BonusQuestions.BONUS14, new Answer(answers.getBonus().getBonus14()));
        ANSWERS.put(BonusQuestions.BONUS15, new Answer(answers.getBonus().getBonus15()));
        ANSWERS.put(BonusQuestions.BONUS16, new Answer(answers.getBonus().getBonus16()));
        ANSWERS.put(BonusQuestions.BONUS17, new Answer(answers.getBonus().getBonus17()));
        ANSWERS.put(BonusQuestions.BONUS18, new Answer(answers.getBonus().getBonus18()));
        ANSWERS.put(BonusQuestions.BONUS19, new Answer(answers.getBonus().getBonus19()));
        ANSWERS.put(BonusQuestions.BONUS20, new Answer(answers.getBonus().getBonus20()));
        ANSWERS.put(BonusQuestions.BONUS21, new Answer(answers.getBonus().getBonus21()));
        ANSWERS.put(BonusQuestions.BONUS22, new Answer(answers.getBonus().getBonus22()));
        ANSWERS.put(BonusQuestions.BONUS23, new Answer(answers.getBonus().getBonus23()));
        ANSWERS.put(BonusQuestions.BONUS24, new Answer(answers.getBonus().getBonus24()));
    }

    public static Answer getAnswer(QuestionType questionType){
        if(!isAnswerSet) throw new RuntimeException("Answers not set yet.");
        return ANSWERS.get(questionType);
    }
}
